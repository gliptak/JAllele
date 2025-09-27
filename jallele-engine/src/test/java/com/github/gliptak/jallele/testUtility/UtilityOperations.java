package com.github.gliptak.jallele.testUtility;

/**
 * Simple class to test utility instructions that can be handled by UtilityInstructionVisitor
 */
public class UtilityOperations {

    public int incrementByOne(int value) {
        // This will generate an IINC instruction
        value++;  
        return value;
    }
    
    public int incrementByFive(int value) {
        // This should generate an IINC instruction with operand 5
        value += 5;
        return value;  
    }
    
    public void doNothing() {
        // This might generate NOP instructions (though compiler may optimize them away)
        ;
        ;
    }
    
    public int swapAndAdd(int a, int b) {
        // This may generate SWAP instructions depending on compilation
        // Simple operations to potentially trigger stack manipulations
        int temp = a;
        a = b;
        b = temp;
        return a + b;
    }
}