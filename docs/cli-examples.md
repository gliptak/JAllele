# JAllele CLI Examples

This document demonstrates the command-line interface for JAllele with real-world examples.

## Basic Usage

Specify classes explicitly:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 10 \
  --junit \
  --source-classes com.example.MyClass \
  --test-classes com.example.MyClassTest
```

## Reproducible Testing with Seed

JAllele uses a random number generator to select which mutations to apply. By default, each run uses a different random seed, resulting in different mutations. To make mutation testing reproducible (useful for debugging or continuous integration), you can specify a seed value:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 10 \
  --junit \
  --seed 12345 \
  --source-classes com.example.MyClass \
  --test-classes com.example.MyClassTest
```

Running with the same seed value will always produce the same sequence of mutations. This is particularly useful when:
- **Debugging**: Reproduce a specific mutation that caused an issue
- **CI/CD**: Ensure consistent behavior across test runs
- **Research**: Compare results across different versions with identical mutations

To see which mutations are being applied, use a higher log level:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 10 \
  --junit \
  --seed 12345 \
  --log-level FINE \
  --source-classes com.example.MyClass \
  --test-classes com.example.MyClassTest
```

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
| `*Test` | `MyTest`<br>`SimpleTest` | `com.example.sub.MyTest` |
| `com.example.*.Test` | `com.example.MyTest`<br>`com.example.SimpleTest` | `com.example.sub.MyTest` |

## Real-World Examples: Testing Popular Open-Source Libraries

JAllele can be used to validate test coverage strength of real open-source libraries. Here are detailed instructions for testing two popular libraries that use TestNG.

### Example 1: Testing JCommander (Command-Line Parsing Library)

[JCommander](https://github.com/cbeust/jcommander) is a popular command-line parsing library used by many Java projects. It uses TestNG for its test suite and provides excellent mutation testing opportunities.

#### Step 1: Clone and Build JCommander

```bash
# Clone the repository
git clone https://github.com/cbeust/jcommander.git
cd jcommander

# Build the project (uses Gradle)
./gradlew clean compileJava compileTestJava
```

**What gets built:**
- Main classes: `build/classes/java/main/`
- Test classes: `build/classes/java/test/`
- 43 test classes covering command-line parsing functionality

#### Step 2: Download SLF4J Implementation

TestNG requires SLF4J for logging. Download the SLF4J Simple implementation:

```bash
# Download SLF4J Simple (lightweight implementation)
wget https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/2.0.16/slf4j-simple-2.0.16.jar
```

#### Step 3: Run JAllele Against Specific Classes

Test a core class like `ParameterDescription.java` using explicit class names:

```bash
java -Djdk.attach.allowAttachSelf=true \
  -cp "slf4j-simple-2.0.16.jar:build/classes/java/main:build/classes/java/test:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 50 \
  --testng \
  --source-classes com.beust.jcommander.ParameterDescription \
  --test-classes com.beust.jcommander.JCommanderTest \
  --log-level INFO
```

**Critical**: The `-cp` flag MUST include:
1. `slf4j-simple-2.0.16.jar` (for TestNG logging)
2. `build/classes/java/main` (compiled source classes)
3. `build/classes/java/test` (compiled test classes)
4. `/path/to/jallele.jar` (JAllele uber JAR)

#### Step 4: Run JAllele With Pattern-Based Discovery

If using `--source-path` and `--source-patterns`, you MUST still include the compiled classes in `-cp`:

```bash
java -Djdk.attach.allowAttachSelf=true \
  -cp "slf4j-simple-2.0.16.jar:build/classes/java/main:build/classes/java/test:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 50 \
  --testng \
  --source-path build/classes/java/main \
  --source-patterns 'com.beust.jcommander.Parameter*' \
  --test-path build/classes/java/test \
  --test-patterns 'com.beust.jcommander.**Test' \
  --log-level INFO
```

**Expected Results:**
- JAllele will test JCommander's parsing logic and test suite
- Mutations will target command-line parsing, validation, and error handling
- The test suite should catch most mutations, revealing test coverage quality
- Results will show mutation detection rate (e.g., `Results: 42/50 (0.84)`)

**Important Notes:**
- **SLF4J Required**: TestNG needs SLF4J. Always include `slf4j-simple-2.0.16.jar` in the classpath
- **Use -cp flag**: Must use `-cp` flag (not `-jar`) to include all dependencies in classpath
- **Main class**: Call `com.github.gliptak.jallele.Main` directly instead of using `-jar`
- **Compiled classes in -cp**: ALWAYS include `build/classes/java/main` and `build/classes/java/test` in `-cp`, even when using `--source-path`
- **Explicit Class Names**: Use `--source-classes` and `--test-classes` for most reliable results

**Troubleshooting "First run failed":**

If you see `SEVERE: First run failed`, check:
1. **Missing SLF4J**: Error mentions "No SLF4J providers" → Add `slf4j-simple-2.0.16.jar` to `-cp`
2. **Missing compiled classes**: No other error but still fails → Add `build/classes/java/main:build/classes/java/test` to `-cp`
3. **Test failures**: Some tests fail when run standalone → Try different source/test class combinations
4. **Classpath order**: Put SLF4J first in `-cp` for best results

**Working Example:**
```bash
# This complete command should work:
java -Djdk.attach.allowAttachSelf=true \
  -cp "slf4j-simple-2.0.16.jar:build/classes/java/main:build/classes/java/test:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 20 \
  --testng \
  --source-classes com.beust.jcommander.ParameterDescription \
  --test-classes com.beust.jcommander.JCommanderTest \
  --log-level INFO
```

**Key Classes to Test:**
- `com.beust.jcommander.ParameterDescription` - Parameter metadata (reliable for testing)
- `com.beust.jcommander.DefaultUsageFormatter` - Usage text formatting
- `com.beust.jcommander.converters.*` - Type converters

### Example 2: Testing Apache Commons Lang (Utility Library with JUnit)

[Apache Commons Lang](https://github.com/apache/commons-lang) is one of the most widely-used Java utility libraries, providing highly reusable utility methods for String manipulation, number handling, reflection, and more. It uses JUnit for testing.

#### Step 1: Clone and Build Commons Lang

```bash
# Clone the repository
git clone https://github.com/apache/commons-lang.git
cd commons-lang

# Build the project (uses Maven)
mvn clean test-compile -DskipTests
```

**What gets built:**
- Main classes: `target/classes/`
- Test classes: `target/test-classes/`
- 267 test classes covering comprehensive utility functionality

#### Step 2: Run JAllele Against String Utilities

Test the StringUtils class (one of the most used classes):

```bash
java -Djdk.attach.allowAttachSelf=true \
  -cp "target/classes:target/test-classes:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 50 \
  --junit \
  --source-classes org.apache.commons.lang3.StringUtils \
  --test-classes org.apache.commons.lang3.StringUtilsTest \
  --log-level INFO
```

**Critical**: The `-cp` flag MUST include:
1. `target/classes` (compiled source classes)
2. `target/test-classes` (compiled test classes)
3. `/path/to/jallele.jar` (JAllele uber JAR)

#### Step 3: Run JAllele Against Array Utilities

Test array manipulation utilities:

```bash
java -Djdk.attach.allowAttachSelf=true \
  -cp "target/classes:target/test-classes:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 50 \
  --junit \
  --source-classes org.apache.commons.lang3.ArrayUtils \
  --test-classes org.apache.commons.lang3.ArrayUtilsTest \
  --log-level INFO
```

#### Step 4: Run JAllele Against Multiple Utility Classes

Test several related utility classes together:

```bash
java -Djdk.attach.allowAttachSelf=true \
  -cp "target/classes:target/test-classes:/path/to/jallele.jar" \
  com.github.gliptak.jallele.Main \
  --count 100 \
  --junit \
  --source-classes org.apache.commons.lang3.StringUtils org.apache.commons.lang3.ArrayUtils org.apache.commons.lang3.ObjectUtils \
  --test-classes org.apache.commons.lang3.StringUtilsTest org.apache.commons.lang3.ArrayUtilsTest org.apache.commons.lang3.ObjectUtilsTest \
  --log-level INFO
```

**Expected Results:**
- JAllele will test Apache Commons Lang's core utility methods
- Mutations will target string operations, array manipulations, and object comparisons
- High mutation detection rates expected due to comprehensive test coverage
- Results reveal test coverage quality of one of Java's most popular libraries
- Typical results: `Results: 78/100 (0.78)` or better

**Important Notes:**
- **Package-Private Tests**: Apache Commons uses package-private test classes, so you must use `--source-classes` and `--test-classes` (explicit class names) instead of pattern-based discovery
- **Use -cp flag**: Must use `-cp` flag (not `-jar`) to include compiled classes in classpath
- **Main class**: Call `com.github.gliptak.jallele.Main` directly instead of using `-jar`
- **Compiled classes in -cp**: Include `target/classes` and `target/test-classes` in `-cp`
- **JUnit Framework**: Uses JUnit (not TestNG), so no SLF4J configuration needed

**Key Classes to Test:**
- `org.apache.commons.lang3.StringUtils` - String manipulation utilities
- `org.apache.commons.lang3.ArrayUtils` - Array operation utilities
- `org.apache.commons.lang3.ObjectUtils` - Object comparison and null-safe operations
- `org.apache.commons.lang3.math.NumberUtils` - Number parsing and comparison
- `org.apache.commons.lang3.time.DateUtils` - Date/time utilities

### Understanding the Results

For both libraries, JAllele's output shows:

**Mutation Detection Rate:**
```
Results: 82/100 (0.82)
```
- `82` mutations were caught by tests
- `100` total mutations attempted
- `0.82` (82%) detection rate indicates good test coverage

**What a Good Score Means:**
- **0.8-1.0 (80-100%)**: Excellent test coverage - tests catch most code changes
- **0.6-0.8 (60-80%)**: Good coverage - most critical paths tested
- **0.4-0.6 (40-60%)**: Moderate coverage - gaps in testing exist
- **< 0.4 (< 40%)**: Weak coverage - significant testing gaps

**Improving Coverage:**
If mutations aren't caught, it indicates:
1. Missing test cases for certain code paths
2. Tests that check wrong conditions
3. Dead code that isn't tested or needed

### Tips for Testing Real Libraries

1. **Start Small**: Test individual classes first before running against entire packages
2. **Use Specific Patterns**: Target specific classes to get focused results
3. **Increase Count Gradually**: Start with 20-50 mutations, then increase based on class complexity
4. **Check Public Classes**: Ensure test classes are public (JAllele requirement)
5. **Build First**: Always compile the library before running JAllele
6. **Review Output**: Use `--log-level FINE` to see which classes are discovered

### Benefits of Testing Real Libraries

Testing popular open-source libraries with JAllele provides:
- **Real-world validation** of mutation testing effectiveness
- **Benchmark comparison** against well-tested codebases
- **Learning opportunities** from seeing how popular projects test their code
- **Coverage insights** that complement traditional code coverage tools
- **Practical examples** of mutation testing in production-quality code

## Real-World Example: Your Own Project

JAllele works best with projects that follow standard Java testing conventions (public test classes). Here's how to use it with your Maven or Gradle project:

### Maven Projects

```bash
# Build your project
mvn clean test-compile

# Run JAllele
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path target/classes \
  --source-patterns 'com.yourcompany.yourproject.**' \
  --test-path target/test-classes \
  --test-patterns 'com.yourcompany.yourproject.**Test' \
  --log-level INFO
```

### Gradle Projects

```bash
# Build your project
./gradlew compileJava compileTestJava

# Run JAllele
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path build/classes/java/main \
  --source-patterns 'com.yourcompany.yourproject.**' \
  --test-path build/classes/java/test \
  --test-patterns 'com.yourcompany.yourproject.**Test' \
  --log-level INFO
```

### Multi-Module Projects

For multi-module Maven or Gradle projects:

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 100 \
  --junit \
  --source-path module1/target/classes module2/target/classes \
  --source-patterns 'com.yourcompany.**' \
  --test-path module1/target/test-classes module2/target/test-classes \
  --test-patterns 'com.yourcompany.**Test' \
  --log-level INFO
```

**Note on Test Class Visibility:** JUnit requires test classes to be public. Some open-source projects (like Apache Commons Lang) use package-private test classes which cannot be discovered and run through JAllele's pattern-based approach. If you encounter this, use explicit class names with `--source-classes` and `--test-classes` options instead.

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
