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

## Real-World Example: Simple Calculator Project

Here's a complete working example you can try:

### Step 1: Create the Project

```bash
# Create project structure
mkdir -p calculator-project/src/main/java/com/example
mkdir -p calculator-project/src/test/java/com/example
cd calculator-project

# Create Calculator.java
cat > src/main/java/com/example/Calculator.java << 'EOF'
package com.example;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return a / b;
    }
}
EOF

# Create CalculatorTest.java
cat > src/test/java/com/example/CalculatorTest.java << 'EOF'
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
    
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2));
    }
    
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
    }
    
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(6, 3));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        calc.divide(5, 0);
    }
}
EOF

# Create pom.xml
cat > pom.xml << 'EOF'
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>calculator</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
EOF

# Compile the project
mvn clean test-compile
```

### Step 2: Run JAllele Mutation Testing

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 10 \
  --junit \
  --source-path target/classes \
  --source-patterns 'com.example.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.**Test' \
  --log-level INFO
```

**Expected Output:**
- JAllele will discover the Calculator class and CalculatorTest
- It will introduce 10 mutations to the Calculator class
- The tests should detect most or all of the mutations
- You'll see a summary like `Results: 10/10 (1.0)` indicating all mutations were caught

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
