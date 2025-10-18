# GitHub Copilot Instructions for JAllele

## Project Overview

JAllele is a mutation testing tool for Java that generates valid mutants and aspires to avoid equivalent mutants. The project uses Java bytecode manipulation via ASM library to introduce controlled mutations into Java code and verify that unit tests detect those changes.

## Project Structure

- `jallele-engine/`: Core mutation testing engine
  - `src/main/java/com/github/gliptak/jallele/`: Main engine classes
    - `Agent.java`: Java instrumentation agent for bytecode transformation
    - `ClassRandomizer.java`: Manages class transformations and mutation selection
    - `MethodRandomizerVisitor.java`: ASM visitor for method-level mutations
    - `TestRunner.java`: Interface for test execution
    - `VisitStatus.java`: Tracks mutation status and location
  - `src/main/java/com/github/gliptak/jallele/spi/`: Instruction visitor implementations
    - Base class: `InstructionVisitor.java`
    - Specific visitors for different bytecode instructions (e.g., `IConstInstructionVisitor`, `IntegerOpInstructionVisitor`)
- `jallele-cmdline/`: Command-line interface
  - `Main.java`: Entry point for command-line execution
  - `CommandLineArgs.java`: Command-line argument parsing using args4j
- `jallele-gradle/`: Gradle plugin (if applicable)

## Code Style and Conventions

### General Guidelines

1. **Java Version**: Target Java 17 (project uses Java toolchain 17)
2. **Package Naming**: Use `com.github.gliptak.jallele` as the base package
3. **Logging**: Use `java.util.logging.Logger` for all logging needs
   - Available log levels: `OFF`, `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, `FINEST`, `ALL`
   - Default log level is `WARNING`
   - Use `logger.fine()` for detailed debug information
4. **License**: All files are under GPL v3

### Coding Patterns

1. **InstructionVisitor Pattern**: 
   - All instruction visitors extend `InstructionVisitor` abstract class
   - Constructor must accept `Random random` parameter
   - Implement `isMatch(VisitStatus vs)` method that returns a new `VisitStatus` with mutation
   - Use `getOpcodeName(int opCode)` helper to get human-readable opcode names
   - Log transformations using `logger.fine()` with format: `"Transform: {opcode} operand {old} -> {new}"`

2. **VisitStatus Usage**:
   - Always create new `VisitStatus` objects for mutations: `VisitStatus newVs = new VisitStatus(vs);`
   - Compare using `equals()` method to detect changes
   - Track className, methodName, lineNumber, opCode, and operand

3. **Testing Structure**:
   - Unit tests go in `src/test/java/` with package structure matching source
   - Test classes organized by bytecode category (e.g., `testLong`, `testDouble`, `testStack`)
   - Test class naming: `{FeatureName}Test.java` for unit tests, `{FeatureName}IntegrationTest.java` for integration tests
   - Use JUnit 4 for testing (imports from `org.junit`)
   - Use Hamcrest matchers: `assertThat(actual, Is.is(expected))`

4. **ClassRandomizer**:
   - Manages transformation lifecycle: recording phase and mutation phase
   - Use `recordMatches()` to identify all possible mutations
   - Use `randomizeRun()` to apply a single random mutation
   - Always remove transformer after use: `Agent.removeTransformer(cr)`

## Important Constraints

### JVM Verification Rules

⚠️ **CRITICAL**: All Load and Store instruction visitors have been **DELETED** due to JVM bytecode verification errors:
- **Load visitors** (ALoad, ILoad, DLoad, FLoad, LLoad): These replaced LOAD instructions with CONST instructions, creating stack map frame inconsistencies
- **Store visitors** (AStore, IStore, DStore, FStore, LStore): These replaced STORE instructions with POP instructions, leaving variable slots uninitialized

**DO NOT** reintroduce Load or Store instruction visitors without addressing these verification issues.

### Active Instruction Visitors

Currently implemented and safe instruction visitors:
- Constant visitors: `IConst`, `LConst`, `DConst`, `FConst`
- Conditional visitors: `If`, `IfNull`, `IfACompare`, `IfICompare`
- Arithmetic visitors: `DoubleOp`, `FloatOp`, `IntegerOp`, `LongOp`
- Shift visitors: `LongShift`
- Other visitors: `Neg`, `IPush`, `Iinc`

See `bytecodes.md` for comprehensive mapping of bytecode instructions to handlers.

## Build and Test

### Building the Project

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Code Coverage

```bash
./gradlew jacocoRootReport
```

Coverage reports are generated in `build/reports/jacoco/jacocoRootReport/`.

### Running Command-Line Tool

JUnit example:
```bash
java -Djdk.attach.allowAttachSelf=true \
  -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar \
  --junit --count 10 \
  --sources com.github.gliptak.jallele.SimpleClass \
  --tests com.github.gliptak.jallele.SimpleClassJUnitTest \
  --log-level FINE
```

TestNG example:
```bash
java -Djdk.attach.allowAttachSelf=true \
  -jar jallele-cmdline/build/libs/jallele-cmdline-test-*.jar \
  --testng --count 10 \
  --sources com.github.gliptak.jallele.SimpleClass \
  --tests com.github.gliptak.jallele.SimpleClassTestNGTest
```

## Dependencies

- ASM library for bytecode manipulation (see https://asm.ow2.io/asm4-guide.pdf)
- args4j for command-line parsing
- JUnit 4 for testing framework support
- TestNG for testing framework support
- Apache Commons Lang3 for utilities
- Hamcrest for test assertions

## CI/CD

- GitHub Actions workflow: `.github/workflows/ci.yml`
- Tests run on Java 17 and 21
- Code coverage reported to Codecov
- Snapshot artifacts published to GitHub Package Registry for PRs
- Mutation tests run as part of CI on Java 17

## Documentation

- `README.md`: Main project documentation (includes future work and development notes)
- `bytecodes.md`: Comprehensive mapping of Java bytecode instructions to JAllele handlers
- `docs/index.md`: Symlink to README.md for documentation site compatibility

## When Adding New Features

1. **New InstructionVisitor**:
   - Extend `InstructionVisitor` abstract class
   - Accept `Random random` in constructor and pass to super
   - Implement `isMatch(VisitStatus vs)` method
   - Add to `ClassRandomizer.initVisitors()` method
   - Create corresponding test classes in appropriate test package
   - Update `bytecodes.md` to document the new handler
   - Ensure mutations comply with JVM verification rules (avoid stack frame inconsistencies)

2. **New Test Cases**:
   - Follow existing test structure (separate test class from code under test)
   - Use `Helper.runRandomized()` for integration tests
   - Place in appropriate test package (`testLong`, `testDouble`, etc.)
   - Ensure tests run with both JUnit and TestNG where applicable

3. **Command-Line Options**:
   - Add new options to `CommandLineArgs.java` using `@Option` annotation
   - Update help text to document new options
   - Update `README.md` with usage examples
   - Consider adding log output at appropriate level

## References

- Java Virtual Machine Specification: http://docs.oracle.com/javase/specs/jvms/se8/jvms8.pdf
- ASM Guide: https://asm.ow2.io/asm4-guide.pdf
- Mutation Testing: https://en.wikipedia.org/wiki/Mutation_testing
- Allele (biology): http://en.wikipedia.org/wiki/Allele
