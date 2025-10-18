# JAllele CLI Examples

This document demonstrates the improved command-line interface for JAllele with real-world examples.

## Basic Usage (Legacy Format)

The traditional way to use JAllele with explicit class names:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 10 \
  --junit \
  --sources com.example.MyClass \
  --tests com.example.MyClassTest
```

## New Classpath-Based Usage

### Example 1: Testing a Single Module Project

For a typical Maven or Gradle project with compiled classes:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path target/classes \
  --source-patterns 'com.example.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.**Test'
```

**Explanation:**
- `--source-path target/classes`: Points to compiled source classes
- `--source-patterns 'com.example.**'`: Matches all classes under com.example package and subpackages
- `--test-path target/test-classes`: Points to compiled test classes
- `--test-patterns 'com.example.**Test'`: Matches all test classes ending with 'Test'

### Example 2: Testing with JAR Files

When your project is packaged as JAR files:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 50 \
  --junit \
  --source-path myproject-1.0.jar \
  --source-patterns 'com.example.**' \
  --test-path myproject-tests-1.0.jar \
  --test-patterns 'com.example.**Test'
```

### Example 3: Multi-Module Project

For projects with multiple modules (e.g., Maven multi-module projects):

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path module1/target/classes module2/target/classes module3/target/classes \
  --source-patterns 'com.example.**' \
  --test-path module1/target/test-classes module2/target/test-classes module3/target/test-classes \
  --test-patterns 'com.example.**Test'
```

### Example 4: Mixed JAR and Directory Paths

You can mix JAR files and directories:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 50 \
  --junit \
  --source-path core/target/classes api.jar utils/target/classes \
  --source-patterns 'com.example.**' \
  --test-path core/target/test-classes api-tests.jar utils/target/test-classes \
  --test-patterns '**.*Test'
```

## Pattern Syntax

### Wildcards

- `*` - Matches any characters within a single package segment (does not cross package boundaries)
- `**` - Matches any number of package segments (including zero)

### Examples

| Pattern | Matches | Does Not Match |
|---------|---------|----------------|
| `com.example.*` | `com.example.MyClass` | `com.example.sub.MyClass` |
| `com.example.**` | `com.example.MyClass`<br>`com.example.sub.MyClass`<br>`com.example.sub.deep.MyClass` | `com.other.MyClass` |
| `**Test` | `com.example.MyTest`<br>`MyTest`<br>`org.test.SomeTest` | `com.example.MyTestHelper` |
| `*Test` | `MyTest`<br>`SimpleTest` | `com.example.MyTest` |
| `com.example.*.Test` | `com.example.MyTest`<br>`com.example.SimpleTest` | `com.example.sub.MyTest` |

## Real-World Example: Apache Commons Lang

Here's how you would test Apache Commons Lang if it was a project you were working on:

### Step 1: Build the Project

```bash
git clone https://github.com/apache/commons-lang.git
cd commons-lang
mvn clean test-compile
```

### Step 2: Run JAllele Mutation Testing

Test all classes in the `org.apache.commons.lang3.math` package:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 200 \
  --junit \
  --source-path target/classes \
  --source-patterns 'org.apache.commons.lang3.math.**' \
  --test-path target/test-classes \
  --test-patterns 'org.apache.commons.lang3.math.**Test' \
  --log-level INFO
```

Test specific utility classes:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path target/classes \
  --source-patterns 'org.apache.commons.lang3.StringUtils' \
  --test-path target/test-classes \
  --test-patterns 'org.apache.commons.lang3.StringUtilsTest' \
  --log-level INFO
```

Test all utility classes (those ending with 'Utils'):

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 150 \
  --junit \
  --source-path target/classes \
  --source-patterns 'org.apache.commons.lang3.*Utils' \
  --test-path target/test-classes \
  --test-patterns 'org.apache.commons.lang3.*UtilsTest' \
  --log-level INFO
```

## Real-World Example: Google Guava

For a multi-module project like Guava:

### Step 1: Build the Project

```bash
git clone https://github.com/google/guava.git
cd guava
mvn clean test-compile -pl guava
```

### Step 2: Run JAllele Mutation Testing

Test the collections package:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 200 \
  --junit \
  --source-path guava/target/classes \
  --source-patterns 'com.google.common.collect.**' \
  --test-path guava/target/test-classes \
  --test-patterns 'com.google.common.collect.**Test' \
  --log-level INFO
```

## TestNG Support

All examples work with TestNG as well - just replace `--junit` with `--testng`:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --testng \
  --source-path target/classes \
  --source-patterns 'com.example.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.**Test'
```

## Combining Explicit Classes and Patterns

You can also explicitly specify classes while using classpath for test discovery:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 50 \
  --junit \
  --source-classes com.example.MyClass com.example.OtherClass \
  --test-path target/test-classes \
  --test-patterns '**Test'
```

Or vice versa:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 50 \
  --junit \
  --source-path target/classes \
  --source-patterns 'com.example.**' \
  --test-classes com.example.MyTest com.example.OtherTest
```

## Tips and Best Practices

1. **Start with a small count**: Use `--count 10` initially to verify everything works
2. **Use specific patterns**: More specific patterns run faster (e.g., `com.example.utils.**` instead of `com.example.**`)
3. **Enable logging**: Use `--log-level FINE` for debugging class discovery issues
4. **Check discovered classes**: Set `--log-level FINE` to see which classes are discovered

## Troubleshooting

### No classes discovered

If you get an error about no classes being found:

1. Verify the paths exist: `ls -la target/classes`
2. Check the pattern matches: Use `--log-level FINE` to see discovery output
3. Ensure classes are compiled: Run `mvn test-compile` or `gradle testClasses`

### Pattern not matching

- Remember `*` does NOT cross package boundaries
- Use `**` to match across packages
- Patterns are matched against fully qualified class names (e.g., `com.example.MyClass`)

### Performance considerations

- More specific patterns = faster class discovery
- Limit the scope to the classes you actually want to test
- Use multiple targeted runs instead of one large run
