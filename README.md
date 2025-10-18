JAllele
==========
[![ci](https://github.com/gliptak/JAllele/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/gliptak/JAllele/actions/workflows/ci.yml?query=branch%3Amain)
[![codecov](https://codecov.io/gh/gliptak/JAllele/branch/main/graph/badge.svg)](https://codecov.io/gh/gliptak/JAllele)
[![GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl.html)

JAllele is a [mutation testing tool](https://en.wikipedia.org/wiki/Mutation_testing) for Java. By design, all
mutants generated are valid and it aspires to avoid equivalent mutants.

Here is the origin for the project's name:

http://en.wikipedia.org/wiki/Allele

Status
--------

Current status of the project is described in [bytecode mapping](bytecodes.md). Currently implemented bytecodes
display the corresponding handler.

An initial run will determine a list of all possible mutations.
The actual mutation run will choose one of the previously identified
mutations to validate that the unit test bed detects that change.

The "strength" of the test bed can be expressed as percentage of
the mutation runs failed over the number of mutations runs.

Usage
--------

Currently a command line access is made available.
The libraries have to be built locally, though snapshot versions are automatically published to [GitHub Package Registry](https://github.com/gliptak/JAllele/packages) for pull requests.
```
$ ./gradlew build
```
Run JUnit sample (packaged into uberjar):
```
$ java -Djdk.attach.allowAttachSelf=true -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --junit --count 10 \
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassJUnitTest
```

Run TestNG sample (packaged into uberjar):
```
$ java -Djdk.attach.allowAttachSelf=true -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --testng --count 10 \
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassTestNGTest
```

### Logging

You can control the logging level using the `--log-level` or `-loglevel` option. The default log level is `WARNING`.
Supported log levels: `OFF`, `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, `FINEST`, `ALL`.

Example with detailed logging:
```
$ java -Djdk.attach.allowAttachSelf=true -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --junit --count 10 \
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassJUnitTest --log-level FINE
```

Contributions, issues, pull requests are welcome.

## Development

Sources are on [GitHub](https://github.com/gliptak/JAllele).

### Future Work

- What is the best way to specify classes to mutate? (see [Ant JUnit task](http://ant.apache.org/manual/OptionalTasks/junit.html) include/exclude patterns)

## References

- [ASM Guide](https://asm.ow2.io/asm4-guide.pdf)
- [Java Virtual Machine Specification](http://docs.oracle.com/javase/specs/jvms/se8/jvms8.pdf)

