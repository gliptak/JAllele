JAllele
==========
[![ci](https://github.com/gliptak/JAllele/workflows/ci/badge.svg)](https://github.com/gliptak/JAllele/actions?query=branch%3Amaster)
[![codecov](https://codecov.io/gh/gliptak/JAllele/branch/master/graph/badge.svg)](https://codecov.io/gh/gliptak/JAllele)
[![GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl.html)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.gliptak.jallele/jallele-engine.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.gliptak.jallele/jallele-engine)

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

### Maven/Gradle Dependencies

JAllele is published to Maven Central. You can include it in your project:

**Maven:**
```xml
<dependency>
    <groupId>com.github.gliptak.jallele</groupId>
    <artifactId>jallele-engine</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'com.github.gliptak.jallele:jallele-engine:0.1-SNAPSHOT'
```

### Command Line Usage

Currently a command line access is made available.
For development, the libraries need to be built:
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

Contributions, issues, pull requests are welcome.

## Notes

https://asm.ow2.io/asm4-guide.pdf

