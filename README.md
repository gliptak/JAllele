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
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassJUnitTest
```

## Contributing

Contributions, issues, pull requests are welcome.

