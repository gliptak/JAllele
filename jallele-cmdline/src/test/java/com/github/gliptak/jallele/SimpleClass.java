package com.github.gliptak.jallele;

/**
 * Comprehensive test class to exercise all instruction visitors in JAllele.
 * Each method is designed to trigger specific bytecode instructions that are mutated.
 */
public class SimpleClass {

	// Integer operations - tests IConst, IntegerOp, IPush, Iinc visitors
	// Note: ILoad and IStore visitors were deleted (CONST/LOAD mutations violate JVM verification)
	public int twoTimes(int i){
		return 2*i;
	}
	
	public int plusTwo(int i){
		return 2+i;
	}
	
	public int intOperations(int a, int b) {
		int result = a + b;  // IADD, ISTORE
		result = result * 2; // IMUL, ILOAD, ICONST
		result++;            // IINC
		return result;       // ILOAD, IRETURN
	}
	
	// Long operations - tests LConst, LongOp, LongShift visitors
	// Note: LLoad and LStore visitors were deleted (CONST/LOAD mutations violate JVM verification)
	public long longOperations(long a, long b) {
		long result = a + b;  // LADD, LSTORE
		result = result << 2; // LSHL, LLOAD, ICONST
		result = result * 2L; // LMUL, LCONST
		return result;        // LLOAD, LRETURN
	}
	
	// Double operations - tests DConst, DoubleOp visitors
	// Note: DLoad and DStore visitors were deleted (CONST/LOAD mutations violate JVM verification)
	public double doubleOperations(double a, double b) {
		double result = a + b;  // DADD, DSTORE
		result = result * 2.0;  // DMUL, DLOAD, DCONST
		result = -result;       // DNEG
		return result;          // DLOAD, DRETURN
	}
	
	// Float operations - tests FConst, FloatOp visitors
	// Note: FLoad and FStore visitors were deleted (CONST/LOAD mutations violate JVM verification)
	public float floatOperations(float a, float b) {
		float result = a + b;  // FADD, FSTORE
		result = result * 2.0f; // FMUL, FLOAD, FCONST
		result = -result;       // FNEG
		return result;          // FLOAD, FRETURN
	}
	
	// Reference operations - tests IfNull, IfACompare visitors
	// Note: ALoad and AStore visitors were deleted (CONST/LOAD mutations violate JVM verification)
	public String referenceOperations(String str1, String str2) {
		String result = str1;  // ALOAD, ASTORE
		if (result == null) {  // IFNULL
			result = str2;     // ALOAD, ASTORE
		}
		if (str1 == str2) {    // IF_ACMPEQ
			return "same";
		}
		return result;         // ALOAD, ARETURN
	}
	
	// Conditional operations - tests If, IfICompare visitors
	public int conditionalOperations(int a, int b) {
		int result = 0;         // ICONST, ISTORE
		if (a > b) {            // IF_ICMPGT
			result = a;
		} else if (a < b) {     // IF_ICMPLT
			result = b;
		} else {
			result = a + b;     // IADD
		}
		if (result > 0) {       // IFGT
			result++;           // IINC
		}
		return result;
	}
	
	// Array operations - tests additional store/load patterns
	public int[] arrayOperations(int[] arr) {
		int[] result = new int[arr.length]; // NEWARRAY, ASTORE
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i] * 2; // IALOAD, IASTORE, ILOAD, ICONST, IMUL
		}
		return result; // ALOAD, ARETURN
	}
}