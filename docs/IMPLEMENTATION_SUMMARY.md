# Summary of CLI Improvements for JAllele

This document summarizes the improvements made to the JAllele command-line interface.

## Problem Statement

The original CLI only supported explicit class names, which was limiting for real-world projects that:
- Have compiled classes in JAR files or directories
- Use multi-module structures
- Need to test many classes matching patterns
- Want to avoid manually listing all classes

## Solution

### 1. New Command-Line Options

#### Source Classes
- `--source-path` / `--source-classpath`: Specify directories or JAR files containing source classes
- `--source-patterns`: Glob patterns to match source classes (e.g., `com.example.**`)
- `--source-classes`: Explicit class names

#### Test Classes
- `--test-path` / `--test-classpath`: Specify directories or JAR files containing test classes
- `--test-patterns`: Glob patterns to match test classes (e.g., `**Test`)
- `--test-classes`: Explicit class names

### 2. Pattern Syntax

- `*` - Matches any characters within a single package segment
- `**` - Matches any number of package segments (including zero)

Examples:
- `com.example.**` - All classes in com.example and subpackages
- `**Test` - All classes ending with "Test"
- `com.example.*Utils` - Utility classes in com.example only

### 3. Implementation Details

#### ClassDiscovery Utility
New utility class that:
- Scans directories for .class files
- Extracts classes from JAR files
- Matches classes against glob patterns
- Converts file paths to fully qualified class names

#### Main Updates
- Discovers classes using ClassDiscovery when classpath options are used
- Falls back to explicit class names when provided
- Validates that required combinations are provided

#### CommandLineArgs Updates
- Added new option fields for classpath and patterns
- Added validation in Main.parseArguments()

### 4. Testing

#### Unit Tests
- `ClassDiscoveryTest`: 9 tests covering:
  - Directory scanning
  - JAR file scanning
  - Pattern matching (exact, wildcards, double-star)
  - Multiple classpath entries
  - Error handling

- `MainTest`: Tests covering:
  - New command-line options
  - Class discovery integration
  - Validation logic

### 5. Documentation

#### docs/cli-examples.md
Comprehensive guide with:
- Basic usage examples
- Pattern syntax reference
- Real-world examples (Apache Commons Lang, Google Guava)
- Multi-module project examples
- Troubleshooting tips

#### README.md
Updated with:
- Quick start examples for both formats
- Pattern syntax overview
- Link to comprehensive examples

#### demo-cli.sh
Executable demonstration script showing:
- Legacy format
- New explicit class format
- Classpath with exact patterns
- Wildcard patterns
- Package-level patterns

## Usage Examples

### Explicit Class Names
```bash
java -jar jallele.jar --count 100 --junit \
  --source-classes com.example.Class1 com.example.Class2 com.example.Class3 \
  --test-classes com.example.Test1 com.example.Test2 com.example.Test3
```

### Classpath + Patterns
```bash
java -jar jallele.jar --count 100 --junit \
  --source-path target/classes \
  --source-patterns 'com.example.**' \
  --test-path target/test-classes \
  --test-patterns 'com.example.**Test'
```

### Multi-Module
```bash
java -jar jallele.jar --count 100 --junit \
  --source-path module1/target/classes module2/target/classes \
  --source-patterns 'com.example.**' \
  --test-path module1/target/test-classes module2/target/test-classes \
  --test-patterns '**Test'
```

## Benefits

1. **Ease of Use**: No need to manually list all classes
2. **Flexibility**: Supports various project structures (Maven, Gradle, multi-module)
3. **Maintainability**: Patterns automatically include new classes
4. **Real-World Ready**: Tested with patterns matching popular projects

## Files Changed

### New Files
- `jallele-cmdline/src/main/java/com/github/gliptak/jallele/ClassDiscovery.java`
- `jallele-cmdline/src/test/java/com/github/gliptak/jallele/ClassDiscoveryTest.java`
- `docs/cli-examples.md`
- `demo-cli.sh`

### Modified Files
- `jallele-cmdline/src/main/java/com/github/gliptak/jallele/CommandLineArgs.java`
- `jallele-cmdline/src/main/java/com/github/gliptak/jallele/Main.java`
- `jallele-cmdline/src/test/java/com/github/gliptak/jallele/MainTest.java`
- `README.md`

## Testing Results

- All unit tests pass
- All integration tests pass
- CodeQL security scan: 0 issues
- Demo script runs successfully

## Future Enhancements

Potential future improvements:
1. Support for exclusion patterns (e.g., `--exclude-patterns`)
2. Configuration file support for complex setups
3. Integration with build tools (Maven plugin, Gradle plugin already exists)
4. Auto-detection of project structure
