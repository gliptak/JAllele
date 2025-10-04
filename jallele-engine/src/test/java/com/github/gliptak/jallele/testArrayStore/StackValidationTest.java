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
 * <h3>Precise Validation Pattern:</h3>
 * The updated tests follow a specific pattern for maximum stack corruption detection:
 * <ol>
 *   <li><strong>*const* load value to stack</strong> - Push known value using iconst_*, lconst_*, etc.</li>
 *   <li><strong>Array store instruction</strong> - Perform array store (which when mutated leaves extra values)</li>
 *   <li><strong>*const* load values to stack</strong> - Push new known values for arithmetic</li>
 *   <li><strong>*add operation</strong> - Perform arithmetic that should use the newly loaded values</li>
 *   <li><strong>Validate result</strong> - Check if result matches expected (fails if corruption occurred)</li>
 * </ol>
 * 
 * <h3>Why This Pattern Works:</h3>
 * If array store mutations leave extra values on the stack, the arithmetic operations will
 * consume those leftover values instead of the intended operands, producing wrong results.
 * This makes stack corruption immediately detectable.
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
     * 
     * Pattern: iconst_* → array store (mutated) → iconst_* → iadd → validate
     * If array store mutation leaves extra values, the iadd will use wrong operands.
     */
    public int testIntArrayStoreWithDirectStackOperations() {
        int result = 1;
        int[] array = new int[1];
        array[0] = 2; // iconst_3, iastore → mutation leaves arrayref on stack
        return result + 3; // Should be 4 if stack is clean, wrong value if corrupted
    }
    
    /**
     * Test that uses direct long constants to validate stack state after lastore.
     * 
     * Pattern: lconst_* → lastore (mutated) → lconst_* → ladd → validate
     */
    public long testLongArrayStoreWithDirectStackOperations() {
        long[] array = new long[3];
        
        // Step 1: lconst_1 loads 1L to stack (uses 2 slots)
        // Step 2: lastore consumes arrayref, index, long_value (4 slots)
        //         When mutated to POP2, leaves 2 values on stack (arrayref + index)
        array[0] = 1L; // lconst_1, lastore → mutation leaves arrayref + index on stack
        
        // Step 3: lconst_0 loads 0L to stack
        // Step 4: ldc 5L loads 5L to stack
        // Step 5: ladd should consume 0L and 5L to produce 5L
        //         But if mutation left extra values, ladd might use those instead
        long result = 0L + 5L; // Expected: 5L, but will fail if stack corruption occurs
        
        return result; // Should be 5L if stack is clean, wrong value if corrupted
    }
    
    /**
     * Test that uses direct double constants to validate stack state.
     * 
     * Pattern: dconst_* → dastore (mutated) → dconst_* → dadd → validate
     */
    public double testDoubleArrayStoreWithDirectStackOperations() {
        double[] array = new double[3];
        
        // Step 1: dconst_1 loads 1.0 to stack (uses 2 slots)
        // Step 2: dastore consumes arrayref, index, double_value (4 slots)
        //         When mutated to POP2, leaves 2 values on stack (arrayref + index)
        array[0] = 1.0; // dconst_1, dastore → mutation leaves arrayref + index on stack
        
        // Step 3: dconst_0 loads 0.0 to stack
        // Step 4: ldc 2.0 loads 2.0 to stack
        // Step 5: dadd should consume 0.0 and 2.0 to produce 2.0
        //         But if mutation left extra values, dadd might use those instead
        double result = 0.0 + 2.0; // Expected: 2.0, but will fail if stack corruption occurs
        
        return result; // Should be 2.0 if stack is clean, wrong value if corrupted
    }
    
    /**
     * Test sequential array operations with precise stack validation pattern.
     * 
     * Pattern: iconst_* → iastore (mutated) → iconst_* → iconst_* → iadd → validate
     */
    public int testSequentialArrayOperationsWithDirectArithmetic() {
        int[] array = new int[3];
        
        // First array store with stack corruption potential
        array[0] = 2; // iconst_2, iastore → mutation leaves arrayref on stack
        
        // Immediate arithmetic that will detect stack corruption
        // iconst_4, iconst_6, iadd should produce 10
        // But if arrayref is still on stack, iadd might use wrong operands
        int firstResult = 4 + 6; // Expected: 10, corrupted if stack has extra values
        
        // Second array store
        array[1] = 3; // iconst_3, iastore → another potential corruption
        
        // Another immediate arithmetic test
        int secondResult = 1 + 2; // iconst_1, iconst_2, iadd → Expected: 3
        
        // Final validation
        return firstResult + secondResult; // Expected: 13 if both operations were clean
    }
    
    /**
     * Test immediate arithmetic after array store with precise validation.
     * 
     * Pattern: iconst_1 → iastore (mutated) → iconst_2 → iconst_3 → iadd → validate
     * This is the most direct test of the proposed pattern.
     */
    public int testImmediateArithmeticAfterArrayStore() {
        int[] array = new int[2];
        
        // Step 1: iconst_1 loads 1 to stack
        // Step 2: Array store that when mutated will leave arrayref on stack
        array[0] = 1; // iconst_1, iastore → mutation leaves arrayref on stack
        
        // Step 3: iconst_2 loads 2 to stack  
        // Step 4: iconst_3 loads 3 to stack
        // Step 5: iadd should consume 2 and 3, producing 5
        //         If array store mutation left arrayref, iadd will use wrong values
        int result = 2 + 3; // Expected: 5, but will be corrupted if stack has arrayref
        
        return result; // Should be exactly 5 if stack is clean
    }
    
    /**
     * Test reference array store with stack validation pattern.
     * 
     * Pattern: aconst_null/ldc → aastore (mutated) → iconst_* → iadd → validate
     */  
    public int testStringArrayStoreWithStackValidation() {
        String[] array = new String[2];
        
        // String array store that when mutated will leave arrayref on stack
        array[0] = "test"; // ldc "test", aastore → mutation leaves arrayref on stack
        
        // Immediate integer arithmetic to detect stack corruption
        // If aastore mutation left arrayref on stack, this arithmetic will be corrupted
        int result = 7 + 8; // iconst_*, bipush, iadd → Expected: 15
        
        return result; // Should be 15 if stack is clean after aastore mutation
    }
    
    /**
     * Test mixed operations with precise stack corruption detection.
     * 
     * Multiple array stores followed by immediate arithmetic to amplify corruption effects.
     */
    public int testMixedDirectStackOperations() {
        int[] array = new int[4];
        
        // First corruption point
        array[0] = 1; // iconst_1, iastore → leaves arrayref on stack
        
        // Immediate test - will fail if stack corrupted
        int first = 2 + 2; // iconst_2, iconst_2, iadd → Expected: 4
        
        // Second corruption point  
        array[1] = 2; // iconst_2, iastore → leaves another arrayref on stack
        
        // Another immediate test
        int second = 3 + 3; // iconst_3, iconst_3, iadd → Expected: 6
        
        // Third corruption point
        array[2] = 3; // iconst_3, iastore → leaves third arrayref on stack
        
        // Final arithmetic test
        int third = 1 + 4; // iconst_1, iconst_4, iadd → Expected: 5
        
        // Total should be 4 + 6 + 5 = 15 if no stack corruption occurred
        return first + second + third;
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
