JAllele
==========
[![ci](https://github.com/gliptak/JAllele/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/gliptak/JAllele/actions/workflows/ci.yml?query=branch%3Amain)
[![codecov](https://codecov.io/gh/gliptak/JAllele/branch/main/graph/badge.svg)](https://codecov.io/gh/gliptak/JAllele)
[![GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl.html)

JAllele is a [mutation testing tool](https://en.wikipedia.org/wiki/Mutation_testing) for Java. By design, all
mutants generated are valid and it aspires to avoid equivalent mutants.

## Documentation

For full documentation, usage examples, and development information, see [docs/](docs/).

## Quick Start

Build the project:
```
$ ./gradlew build
```

Run JUnit sample:
```
$ java -Djdk.attach.allowAttachSelf=true -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --junit --count 10 \
--source-classes com.github.gliptak.jallele.SimpleClass --test-classes com.github.gliptak.jallele.SimpleClassJUnitTest
```

Run with classpath discovery:
```
$ java -Djdk.attach.allowAttachSelf=true -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --junit --count 10 \
--source-path target/classes --source-patterns 'com.example.**' \
--test-path target/test-classes --test-patterns 'com.example.**Test'
```

For more examples and detailed usage, see [CLI Examples](docs/cli-examples.md).

## Command Line Interface

JAllele supports two formats for specifying classes to test:

### Explicit Class Names

Directly specify class names:
```bash
--source-classes com.example.MyClass --test-classes com.example.MyClassTest
```

### Classpath + Patterns

Specify classpaths (directories or JAR files) and patterns to match classes:
```bash
--source-path target/classes --source-patterns 'com.example.**' \
--test-path target/test-classes --test-patterns 'com.example.**Test'
```

#### Pattern Syntax

- `*` - Matches any characters within a single package segment
- `**` - Matches any number of package segments (including zero)

Examples:
- `com.example.**` - All classes in com.example and subpackages
- `**Test` - All classes ending with "Test"
- `com.example.*Utils` - Utility classes in com.example package only

**Note:** JUnit requires test classes to be public. Some projects (like Apache Commons Lang) use package-private test classes that cannot be run through JAllele's pattern-based discovery. In such cases, use explicit class names with `--source-classes` and `--test-classes`, or test your own projects where test classes are public.

### Multi-Module Projects

For projects with multiple modules, specify multiple paths:
```bash
--source-path module1/target/classes module2/target/classes module3/target/classes \
--test-path module1/target/test-classes module2/target/test-classes module3/target/test-classes
```

See [CLI Examples](docs/cli-examples.md) for comprehensive examples including Apache Commons Lang and Google Guava.

## Contributing

Contributions, issues, pull requests are welcome.

