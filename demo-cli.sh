#!/bin/bash
# Demo script showing JAllele CLI usage with different options
# This script tests JAllele against its own test classes
#
# NOTE: This demo uses the test JAR which includes both the JAllele engine
# and sample test classes (SimpleClass, SimpleClassJUnitTest) that can be used
# to demonstrate mutation testing. In a real-world scenario, you would use
# the production JAR and point to your project's target/classes directories.

set -e

# Using the test JAR for this demo as it includes sample classes
JALLELE_JAR="jallele-cmdline/build/libs/jallele-cmdline-test-*.jar"
JVM_OPTS="-Djdk.attach.allowAttachSelf=true"
COUNT=5
LOG_LEVEL="INFO"

echo "=========================================="
echo "JAllele CLI Demonstration"
echo "=========================================="
echo ""

echo "1. Explicit Class Names: Using --source-classes and --test-classes"
echo "   Command: java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \\"
echo "            --source-classes com.github.gliptak.jallele.SimpleClass \\"
echo "            --test-classes com.github.gliptak.jallele.SimpleClassJUnitTest \\"
echo "            --log-level $LOG_LEVEL"
echo ""
java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \
  --source-classes com.github.gliptak.jallele.SimpleClass \
  --test-classes com.github.gliptak.jallele.SimpleClassJUnitTest \
  --log-level $LOG_LEVEL 2>&1 | tail -5
echo ""
echo "=========================================="
echo ""

echo "2. Classpath Discovery: Using exact patterns"
echo "   (Note: Using test classes directory here for demo; in practice use target/classes)"
echo "   Command: java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \\"
echo "            --source-path jallele-cmdline/build/classes/java/test \\"
echo "            --source-patterns 'com.github.gliptak.jallele.SimpleClass' \\"
echo "            --test-path jallele-cmdline/build/classes/java/test \\"
echo "            --test-patterns 'com.github.gliptak.jallele.SimpleClassJUnitTest' \\"
echo "            --log-level $LOG_LEVEL"
echo ""
java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \
  --source-path jallele-cmdline/build/classes/java/test \
  --source-patterns 'com.github.gliptak.jallele.SimpleClass' \
  --test-path jallele-cmdline/build/classes/java/test \
  --test-patterns 'com.github.gliptak.jallele.SimpleClassJUnitTest' \
  --log-level $LOG_LEVEL 2>&1 | tail -5
echo ""
echo "=========================================="
echo ""

echo "3. Wildcard Patterns: Using ** to match across packages"
echo "   (Note: Using test classes directory here for demo; in practice use target/classes)"
echo "   Command: java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \\"
echo "            --source-path jallele-cmdline/build/classes/java/test \\"
echo "            --source-patterns '**.SimpleClass' \\"
echo "            --test-path jallele-cmdline/build/classes/java/test \\"
echo "            --test-patterns '**JUnitTest' \\"
echo "            --log-level $LOG_LEVEL"
echo ""
java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \
  --source-path jallele-cmdline/build/classes/java/test \
  --source-patterns '**.SimpleClass' \
  --test-path jallele-cmdline/build/classes/java/test \
  --test-patterns '**JUnitTest' \
  --log-level $LOG_LEVEL 2>&1 | tail -5
echo ""
echo "=========================================="
echo ""

echo "4. Package Patterns: Testing all classes in a package"
echo "   (Note: Using test classes directory here for demo; in practice use target/classes)"
echo "   Command: java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \\"
echo "            --source-path jallele-cmdline/build/classes/java/test \\"
echo "            --source-patterns 'com.github.gliptak.jallele.Simple*' \\"
echo "            --test-path jallele-cmdline/build/classes/java/test \\"
echo "            --test-patterns 'com.github.gliptak.jallele.Simple*JUnitTest' \\"
echo "            --log-level $LOG_LEVEL"
echo ""
java $JVM_OPTS -jar $JALLELE_JAR --count $COUNT --junit \
  --source-path jallele-cmdline/build/classes/java/test \
  --source-patterns 'com.github.gliptak.jallele.Simple*' \
  --test-path jallele-cmdline/build/classes/java/test \
  --test-patterns 'com.github.gliptak.jallele.Simple*JUnitTest' \
  --log-level $LOG_LEVEL 2>&1 | tail -5
echo ""
echo "=========================================="
echo ""

echo "Demo completed successfully!"
echo ""
echo "Key Features Demonstrated:"
echo "  ✓ Explicit class names"
echo "  ✓ Classpath-based discovery with exact patterns"
echo "  ✓ Wildcard patterns for flexible matching"
echo "  ✓ Package-level pattern matching"
echo ""
echo "Note: This demo uses test classes for convenience. In real projects:"
echo "  - Use target/classes or build/classes/java/main for source classes"
echo "  - Use target/test-classes or build/classes/java/test for test classes"
echo "  - See docs/cli-examples.md for production examples"
echo ""
echo "See docs/cli-examples.md for more examples including:"
echo "  - Multi-module projects"
echo "  - JAR file support"
echo "  - Real-world examples (Apache Commons Lang, Google Guava)"
echo "=========================================="
