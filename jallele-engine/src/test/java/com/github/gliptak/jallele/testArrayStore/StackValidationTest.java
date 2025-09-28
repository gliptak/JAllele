package com.github.gliptak.jallele.testArrayStore;

/**
 * Test class specifically designed to validate stack behavior after array store mutations.
 * This class contains methods that would fail if extra values are left on the stack.
 */
public class StackValidationTest {
    
    public int testIntArrayStoreWithStackValidation() {
        int[] array = new int[3];
        array[0] = 42; // iastore - if mutated to POP2, leaves 1 value on stack
        // This operation should not be affected by leftover stack values
        int result = 100; // If stack is corrupted, this might fail
        array[1] = result;
        return array[0] + array[1]; // Should return 142
    }
    
    public long testLongArrayStoreWithStackValidation() {
        long[] array = new long[3];
        array[0] = 123456789L; // lastore - if mutated to POP2, leaves 2 values on stack
        // This operation should not be affected by leftover stack values
        long result = 987654321L; // If stack is corrupted, this might fail
        array[1] = result;
        return array[0] + array[1]; // Should return correct sum
    }
    
    public double testDoubleArrayStoreWithStackValidation() {
        double[] array = new double[3];
        array[0] = 3.14159; // dastore - if mutated to POP2, leaves 2 values on stack
        // This operation should not be affected by leftover stack values
        double result = 2.71828; // If stack is corrupted, this might fail
        array[1] = result;
        return array[0] + array[1]; // Should return correct sum
    }
    
    public String testStringArrayStoreWithStackValidation() {
        String[] array = new String[3];
        array[0] = "Hello"; // aastore - if mutated to POP2, leaves 1 value on stack
        // This operation should not be affected by leftover stack values
        String result = "World"; // If stack is corrupted, this might fail
        array[1] = result;
        return array[0] + " " + array[1]; // Should return "Hello World"
    }
    
    /**
     * This method tests multiple array operations in sequence to detect
     * stack corruption from accumulated leftover values.
     * Critical: Operations after array stores must use exact stack expectations.
     */
    public int testSequentialArrayOperationsWithStackValidation() {
        int[] ints = new int[2];
        long[] longs = new long[2];
        String[] strings = new String[2];
        
        // Each array store operation has specific stack effects:
        // iastore: consumes arrayref, index, value (3 slots) → if mutated to POP2, leaves 1 slot
        // lastore: consumes arrayref, index, long (4 slots) → if mutated to POP2, leaves 2 slots  
        // aastore: consumes arrayref, index, ref (3 slots) → if mutated to POP2, leaves 1 slot
        
        ints[0] = 10;        // iastore → POP2 mutation leaves arrayref on stack
        longs[0] = 20L;      // lastore → POP2 mutation leaves arrayref + index on stack  
        strings[0] = "test"; // aastore → POP2 mutation leaves arrayref on stack
        
        // Now perform operations that require a clean stack
        // If mutations leave extra values, these calculations will be wrong
        int baseValue = 100;
        int calculatedValue = baseValue; // This should be 100
        
        ints[1] = calculatedValue;
        longs[1] = calculatedValue; 
        strings[1] = String.valueOf(calculatedValue);
        
        // Final calculation - if stack was corrupted, this will give wrong result
        return ints[0] + ints[1] + (int)longs[0] + (int)longs[1] + 
               Integer.parseInt(strings[1]);
    }
    
    /**
     * Test method that performs arithmetic operations immediately after array stores
     * to validate that the stack is in the correct state
     */
    public int testArithmeticAfterArrayStore() {
        int[] array = new int[2];
        
        // Push values for arithmetic
        int a = 5;
        int b = 10;
        
        // Array store operation that could corrupt stack
        array[0] = 42; // iastore - if mutated incorrectly, might leave values on stack
        
        // Arithmetic operation that requires clean stack
        int result = a + b; // Should be 15 - will fail if stack has extra values
        
        array[1] = result;
        return result;
    }
}