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

## Real-World Examples with TestNG

Here are two complete working examples using TestNG that you can try. These demonstrate JAllele's TestNG support with library-style projects.

### Example 1: String Utilities Library

This example demonstrates a utility library similar to Apache Commons Lang.

#### Step 1: Create the StringUtils Project

```bash
# Create project structure
mkdir -p stringutils-project/src/main/java/com/example/utils
mkdir -p stringutils-project/src/test/java/com/example/utils
cd stringutils-project

# Create StringUtils.java
cat > src/main/java/com/example/utils/StringUtils.java << 'EOF'
package com.example.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static String defaultString(String str) {
        return str == null ? "" : str;
    }
    
    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }
    
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
    
    public static int countMatches(String str, char ch) {
        if (isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}
EOF

# Create StringUtilsTest.java
cat > src/test/java/com/example/utils/StringUtilsTest.java << 'EOF'
package com.example.utils;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    
    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
        assertFalse(StringUtils.isEmpty("test"));
    }
    
    @Test
    public void testIsNotEmpty() {
        assertFalse(StringUtils.isNotEmpty(null));
        assertFalse(StringUtils.isNotEmpty(""));
        assertTrue(StringUtils.isNotEmpty(" "));
        assertTrue(StringUtils.isNotEmpty("test"));
    }
    
    @Test
    public void testDefaultString() {
        assertEquals("", StringUtils.defaultString(null));
        assertEquals("", StringUtils.defaultString(""));
        assertEquals("test", StringUtils.defaultString("test"));
    }
    
    @Test
    public void testDefaultStringWithDefault() {
        assertEquals("default", StringUtils.defaultString(null, "default"));
        assertEquals("", StringUtils.defaultString("", "default"));
        assertEquals("test", StringUtils.defaultString("test", "default"));
    }
    
    @Test
    public void testCapitalize() {
        assertNull(StringUtils.capitalize(null));
        assertEquals("", StringUtils.capitalize(""));
        assertEquals("Test", StringUtils.capitalize("test"));
        assertEquals("Test", StringUtils.capitalize("Test"));
        assertEquals("TEST", StringUtils.capitalize("TEST"));
    }
    
    @Test
    public void testReverse() {
        assertNull(StringUtils.reverse(null));
        assertEquals("", StringUtils.reverse(""));
        assertEquals("tset", StringUtils.reverse("test"));
        assertEquals("GNtseT", StringUtils.reverse("TestNG"));
    }
    
    @Test
    public void testCountMatches() {
        assertEquals(0, StringUtils.countMatches(null, 'a'));
        assertEquals(0, StringUtils.countMatches("", 'a'));
        assertEquals(2, StringUtils.countMatches("test", 't'));
        assertEquals(2, StringUtils.countMatches("hello", 'l'));
        assertEquals(0, StringUtils.countMatches("hello", 'x'));
    }
}
EOF

# Create pom.xml
cat > pom.xml << 'EOF'
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>stringutils</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
            <scope>test</scope>
        </dependency>
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

#### Step 2: Run JAllele Mutation Testing

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 20 \
  --testng \
  --source-path target/classes \
  --source-patterns 'com.example.utils.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.utils.**Test' \
  --log-level INFO
```

**Expected Output:**
- JAllele will discover the StringUtils class and StringUtilsTest
- It will introduce 20 mutations to the StringUtils methods
- The TestNG tests should detect most mutations
- You'll see detailed mutation testing results

### Example 2: Math Utilities Library

This example demonstrates a mathematical utility library similar to Apache Commons Math.

#### Step 1: Create the MathUtils Project

```bash
# Create project structure
mkdir -p mathutils-project/src/main/java/com/example/math
mkdir -p mathutils-project/src/test/java/com/example/math
cd mathutils-project

# Create MathUtils.java
cat > src/main/java/com/example/math/MathUtils.java << 'EOF'
package com.example.math;

public class MathUtils {
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }
    
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers not allowed");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent < 0) {
            return 1.0 / power(base, -exponent);
        }
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
EOF

# Create MathUtilsTest.java
cat > src/test/java/com/example/math/MathUtilsTest.java << 'EOF'
package com.example.math;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.junit.Assert.*;

public class MathUtilsTest {
    
    @DataProvider(name = "gcdData")
    public Object[][] gcdData() {
        return new Object[][] {
            {12, 8, 4},
            {54, 24, 6},
            {100, 50, 50},
            {7, 13, 1},
            {0, 5, 5}
        };
    }
    
    @Test(dataProvider = "gcdData")
    public void testGcd(int a, int b, int expected) {
        assertEquals(expected, MathUtils.gcd(a, b));
    }
    
    @DataProvider(name = "lcmData")
    public Object[][] lcmData() {
        return new Object[][] {
            {12, 8, 24},
            {3, 7, 21},
            {10, 15, 30},
            {0, 5, 0}
        };
    }
    
    @Test(dataProvider = "lcmData")
    public void testLcm(int a, int b, int expected) {
        assertEquals(expected, MathUtils.lcm(a, b));
    }
    
    @DataProvider(name = "primeData")
    public Object[][] primeData() {
        return new Object[][] {
            {2, true},
            {3, true},
            {4, false},
            {17, true},
            {20, false},
            {1, false},
            {0, false}
        };
    }
    
    @Test(dataProvider = "primeData")
    public void testIsPrime(int n, boolean expected) {
        assertEquals(expected, MathUtils.isPrime(n));
    }
    
    @Test
    public void testFactorial() {
        assertEquals(1, MathUtils.factorial(0));
        assertEquals(1, MathUtils.factorial(1));
        assertEquals(120, MathUtils.factorial(5));
        assertEquals(5040, MathUtils.factorial(7));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        MathUtils.factorial(-1);
    }
    
    @Test
    public void testPower() {
        assertEquals(8.0, MathUtils.power(2.0, 3), 0.001);
        assertEquals(25.0, MathUtils.power(5.0, 2), 0.001);
        assertEquals(1.0, MathUtils.power(2.0, 0), 0.001);
        assertEquals(0.25, MathUtils.power(2.0, -2), 0.001);
    }
}
EOF

# Create pom.xml
cat > pom.xml << 'EOF'
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>mathutils</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
            <scope>test</scope>
        </dependency>
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

#### Step 2: Run JAllele Mutation Testing

```bash
java -Djdk.attach.allowAttachSelf=true -jar jallele.jar \
  --count 25 \
  --testng \
  --source-path target/classes \
  --source-patterns 'com.example.math.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.math.**Test' \
  --log-level INFO
```

**Expected Output:**
- JAllele will discover the MathUtils class and MathUtilsTest
- It will introduce 25 mutations to the MathUtils methods
- The TestNG tests (including data-driven tests) should detect most mutations
- You'll see comprehensive mutation testing results

### Key Features Demonstrated

Both examples showcase:
- **TestNG Integration**: Using `--testng` flag with JAllele
- **Realistic Library Code**: Utility classes similar to popular open-source libraries (Apache Commons Lang, Apache Commons Math)
- **Comprehensive Testing**: Multiple test methods covering edge cases
- **Data-Driven Testing**: TestNG's `@DataProvider` feature (in MathUtils example)
- **Pattern-Based Discovery**: Using `--source-patterns` and `--test-patterns`
- **Public Test Classes**: Following JUnit/TestNG best practices for test visibility

### Important Notes on TestNG Examples

**TestNG and JUnit Interoperability**: The examples above use TestNG's `@Test` annotations with JUnit's assertion methods (`import static org.junit.Assert.*`). This is a common pattern that works well because:
- JAllele's test JAR bundles both JUnit and TestNG
- JUnit assertions are widely familiar and work seamlessly with TestNG
- It avoids SLF4J dependency issues that can occur with TestNG's own assertions

**Running the Examples**: To successfully run these examples with JAllele:
1. Compile your project with Maven: `mvn clean test-compile`
2. Ensure the test classes are public (required for TestNG)
3. Use the command shown above with `--testng` flag
4. Both dependencies (TestNG and JUnit) should be in your project's pom.xml

**Why These Examples Matter**: While many popular Java open-source projects (like Apache Commons Lang, Google Guava) use JUnit for testing, these examples demonstrate:
- How to structure library-style code similar to real open-source projects
- How to use JAllele's TestNG support effectively
- Common patterns found in utility libraries that are good candidates for mutation testing

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
