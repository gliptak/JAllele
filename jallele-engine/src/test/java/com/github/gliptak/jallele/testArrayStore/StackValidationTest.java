package com.github.gliptak.jallele.testArrayStore;

/**
 * Test class specifically designed to validate stack behavior after array store mutations.
 * This class contains methods that would fail if extra values are left on the stack.
 * 
 * The test methods are designed to generate specific bytecode instructions that directly
 * test stack behavior with operations like iconst_*, iadd, lconst_*, dconst_*, etc.
 * 
 * <h3>Design Philosophy:</h3>
 * <ul>
 *   <li>Use small integer constants (0-5) that compile to iconst_* instructions</li>
 *   <li>Use direct arithmetic operations (iadd, imul) immediately after array stores</li>
 *   <li>Employ stack-sensitive operations that will fail if extra values are present</li>
 *   <li>Test immediate operations to catch stack corruption as soon as possible</li>
 * </ul>
 * 
 * <h3>Bytecode Strategy:</h3>
 * The methods intentionally use constants and operations that translate to direct
 * JVM stack instructions:
 * <ul>
 *   <li>iconst_0 through iconst_5 for integer constants</li>
 *   <li>lconst_0, lconst_1 for long constants</li>
 *   <li>dconst_0, dconst_1 for double constants</li>
 *   <li>iadd, imul, ladd, dadd for arithmetic</li>
 *   <li>bipush for slightly larger constants</li>
 * </ul>
 * 
 * These direct stack operations are extremely sensitive to stack corruption and will
 * fail immediately if array store mutations leave unexpected values on the stack.
 */
public class StackValidationTest {
    
    /**
     * Test that uses direct integer constants and arithmetic to validate stack state.
     * Generates bytecode: iconst_*, iastore, iconst_*, iadd
     */
    public int testIntArrayStoreWithDirectStackOperations() {
        int[] array = new int[3];
        
        // Use direct integer constants that translate to iconst_* bytecode
        array[0] = 5; // iconst_5, iastore - if mutated to POP2, leaves 1 value on stack
        
        // Immediate arithmetic using direct constants - sensitive to stack corruption
        int result = 3 + 7; // iconst_3, bipush 7, iadd - will fail if stack has extra values
        
        array[1] = result; // Should be 10
        
        // Direct arithmetic validation
        int sum = array[0] + array[1]; // Should be 15 if stack was clean during operations
        
        return sum;
    }
    
    /**
     * Test that uses direct long constants to validate stack state after lastore.
     * Generates bytecode: lconst_*, lastore, iconst_*, i2l, ladd
     */
    public long testLongArrayStoreWithDirectStackOperations() {
        long[] array = new long[3];
        
        // Use direct long constants
        array[0] = 1L; // lconst_1, lastore - if mutated to POP2, leaves 2 values on stack
        
        // Direct arithmetic that's sensitive to stack corruption
        long result = 2L + 3L; // lconst_1 (for 2), ldc 3L, ladd - sensitive to stack state
        
        array[1] = result; // Should be 5
        
        // Validate with direct arithmetic
        return array[0] + array[1]; // Should be 6
    }
    
    /**
     * Test that uses direct double constants to validate stack state.
     * Generates bytecode: dconst_*, dastore, dconst_*, dadd
     */
    public double testDoubleArrayStoreWithDirectStackOperations() {
        double[] array = new double[3];
        
        // Use direct double constants  
        array[0] = 1.0; // dconst_1, dastore - if mutated to POP2, leaves 2 values on stack
        
        // Direct double arithmetic sensitive to stack state
        double result = 0.5 + 0.5; // ldc 0.5, ldc 0.5, dadd - sensitive to stack corruption
        
        array[1] = result; // Should be 1.0
        
        return array[0] + array[1]; // Should be 2.0
    }
    
    /**
     * Test sequential array operations with direct stack-sensitive operations.
     * Uses iconst_*, iadd, imul to create stack-sensitive arithmetic.
     */
    public int testSequentialArrayOperationsWithDirectArithmetic() {
        int[] ints = new int[3];
        
        // Array stores that could leave values on stack if mutations are incorrect
        ints[0] = 2; // iconst_2, iastore → POP2 mutation leaves 1 slot
        ints[1] = 3; // iconst_3, iastore → POP2 mutation leaves 1 slot
        
        // Direct arithmetic operations very sensitive to stack corruption
        // These use iconst_* which push exact values and are sensitive to stack state
        int a = 4;     // iconst_4
        int b = 5;     // iconst_5  
        int product = a * b; // imul - will fail if stack has unexpected values
        
        ints[2] = product; // Should be 20
        
        // Final calculation using direct arithmetic
        int sum = ints[0] + ints[1] + ints[2]; // iadd operations - stack sensitive
        
        return sum; // Should be 2 + 3 + 20 = 25
    }
    
    /**
     * Test method that performs immediate arithmetic after array store using
     * direct constant instructions that generate iconst_*, iadd bytecode.
     */
    public int testImmediateArithmeticAfterArrayStore() {
        int[] array = new int[2];
        
        // Array store that could corrupt stack
        array[0] = 1; // iconst_1, iastore - if mutated incorrectly, leaves values on stack
        
        // Immediate arithmetic using direct constants - extremely stack sensitive
        // This should generate: iconst_2, iconst_3, iadd
        int result = 2 + 3; // iconst_2, iconst_3, iadd - will fail with stack corruption
        
        array[1] = result; // Should be 5
        
        return result; // Should be exactly 5
    }
    
    /**
     * Test that validates stack behavior with mixed operations and direct constants.
     * Uses combinations of iconst_*, bipush, iadd, imul to stress-test stack state.
     */
    public int testMixedDirectStackOperations() {
        int[] array = new int[4];
        
        // Multiple array stores with small constants (iconst_*)
        array[0] = 1; // iconst_1, iastore
        array[1] = 2; // iconst_2, iastore  
        array[2] = 3; // iconst_3, iastore
        
        // Complex arithmetic using direct bytecode instructions
        int base = 10;        // bipush 10
        int multiplier = 2;   // iconst_2
        int addition = 5;     // iconst_5
        
        // Arithmetic chain that's very sensitive to stack corruption
        int result = base * multiplier + addition; // imul, iadd - sensitive to stack state
        
        array[3] = result; // Should be 25
        
        // Sum using direct addition operations
        int total = array[0] + array[1] + array[2] + array[3]; // Multiple iadd - stack sensitive
        
        return total; // Should be 1 + 2 + 3 + 25 = 31
    }
    
    /**
     * Test that uses boolean operations to validate stack state.
     * Generates bytecode with iconst_0, iconst_1 for booleans.
     */
    public boolean testBooleanOperationsAfterArrayStore() {
        int[] array = new int[2];
        
        // Array store with boolean conversion
        array[0] = 1; // iconst_1, iastore - potential stack corruption
        
        // Boolean operations using direct constants
        boolean a = true;  // iconst_1 (for true)
        boolean b = false; // iconst_0 (for false)  
        boolean result = a && !b; // Complex boolean logic sensitive to stack state
        
        array[1] = result ? 1 : 0; // Conditional with iconst_1/iconst_0
        
        return result; // Should be true
    }
}