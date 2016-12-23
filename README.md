JAllele
==========
[![Build Status](https://travis-ci.org/gliptak/JAllele.svg?branch=master)](https://travis-ci.org/gliptak/JAllele)

JAllele is a [mutation testing tool](https://en.wikipedia.org/wiki/Mutation_testing) for Java. By design, all
mutants generated are valid and it aspires to avoid equivalent mutants.

Here is the origin for the project's name:

http://en.wikipedia.org/wiki/Allele

Status
--------

Current status of the project is described in [bytecode mapping](bytecodes.html). Currently implemented bytecodes
display the corresponding handler.

An initial run will determine a list of all possible mutations.
The actual mutation run will choose one of the previously identified
mutations to validate that the unit test bed detects that change.

The "strength" of the test bed can be expressed as percentage of
the mutation runs failed over the number of mutations runs.

Usage
--------

Currently a command line access is made available.
The libraries have to be built, as they are not yet published to a Maven repository.
```
$ ./gradlew build
```
Run JUnit sample (packaged into uberjar):
```
$ java -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --count 10 \
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassJUnitTest
```

Run TestNG sample (packaged into uberjar):
```
$ java -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar --count 10 \
--sources com.github.gliptak.jallele.SimpleClass --tests com.github.gliptak.jallele.SimpleClassTestNGTest
```

Contributions, issues, pull requests are welcome.

[![Travis](https://api.travis-ci.org/gliptak/JAllele.svg?branch=master)](https://travis-ci.org/gliptak/JAllele)
[![GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl.html)
