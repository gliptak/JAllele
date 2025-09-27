# JAllele mapping of Java bytecode instructions

| JAllele Handler | Mnemonic | Opcode (hex) | Other bytes | Stack [before]→[after] | Description |
|-----------------|----------|--------------|-------------|------------------------|-------------|
| **A** | | | | | |
| TBD | aaload | 32 |   | arrayref, index → value | loads onto the stack a reference from an array |
| TBD | aastore | 53 |   | arrayref, index, value → | stores into a reference to an array |
| TBD | aconst_null | 01 |   | → null | pushes a null reference onto the stack |
| TBD | aload | 19 | index | → objectref | loads a reference onto the stack from a local variable #index |
| TBD | aload_0 | 2a |   | → objectref | loads a reference onto the stack from local variable 0 |
| TBD | aload_1 | 2b |   | → objectref | loads a reference onto the stack from local variable 1 |
| TBD | aload_2 | 2c |   | → objectref | loads a reference onto the stack from local variable 2 |
| TBD | aload_3 | 2d |   | → objectref | loads a reference onto the stack from local variable 3 |
| TBD | anewarray | bd | indexbyte1, indexbyte2 | count → arrayref | creates a new array of references of length count and component type identified by the class reference index (indexbyte1 << 8 + indexbyte2) in the constant pool |
| TBD | areturn | b0 |   | objectref → [empty] | returns a reference from a method |
| TBD | arraylength | be |   | arrayref → length | gets the length of an array |
| TBD | astore | 3a | index | objectref → | stores a reference into a local variable #index |
| TBD | astore_0 | 4b |   | objectref → | stores a reference into local variable 0 |
| TBD | astore_1 | 4c |   | objectref → | stores a reference into local variable 1 |
| TBD | astore_2 | 4d |   | objectref → | stores a reference into local variable 2 |
| TBD | astore_3 | 4e |   | objectref → | stores a reference into local variable 3 |
| TBD | athrow | bf |   | objectref → [empty], objectref | throws an error or exception (notice that the rest of the stack is cleared, leaving only a reference to the Throwable) |
| **B** | | | | | |
| TBD | baload | 33 |   | arrayref, index → value | loads a byte or Boolean value from an array |
| TBD | bastore | 54 |   | arrayref, index, value → | stores a byte or Boolean value into an array |
| IPushInstructionVisitor | bipush | 10 | byte | → value | pushes a byte onto the stack as an integer value |
| **C** | | | | | |
| TBD | caload | 34 |   | arrayref, index → value | loads a char from an array |
| TBD | castore | 55 |   | arrayref, index, value → | stores a char into an array |
| TBD | checkcast | c0 | indexbyte1, indexbyte2 | objectref → objectref | checks whether an objectref is of a certain type, the class reference of which is in the constant pool at index (indexbyte1 << 8 + indexbyte2) |
| **D** | | | | | |
| TBD | d2f | 90 |   | value → result | converts a double to a float |
| TBD | d2i | 8e |   | value → result | converts a double to an int |
| TBD | d2l | 8f |   | value → result | converts a double to a long |
| DoubleOpInstructionVisitor | dadd | 63 |   | value1, value2 → result | adds two doubles |
| TBD | daload | 31 |   | arrayref, index → value | loads a double from an array |
| TBD | dastore | 52 |   | arrayref, index, value → | stores a double into an array |
| TBD | dcmpg | 98 |   | value1, value2 → result | compares two doubles |
| TBD | dcmpl | 97 |   | value1, value2 → result | compares two doubles |
| TBD | dconst_0 | 0e |   | → 0.0 | pushes the constant 0.0 onto the stack |
| TBD | dconst_1 | 0f |   | → 1.0 | pushes the constant 1.0 onto the stack |
| DoubleOpInstructionVisitor | ddiv | 6f |   | value1, value2 → result | divides two doubles |
| TBD | dload | 18 | index | → value | loads a double value from a local variable #index |
| TBD | dload_0 | 26 |   | → value | loads a double from local variable 0 |
| TBD | dload_1 | 27 |   | → value | loads a double from local variable 1 |
| TBD | dload_2 | 28 |   | → value | loads a double from local variable 2 |
| TBD | dload_3 | 29 |   | → value | loads a double from local variable 3 |
| DoubleOpInstructionVisitor | dmul | 6b |   | value1, value2 → result | multiplies two doubles |
| TBD | dneg | 77 |   | value → result | negates a double |
| DoubleOpInstructionVisitor | drem | 73 |   | value1, value2 → result | gets the remainder from a division between two doubles |
| TBD | dreturn | af |   | value → [empty] | returns a double from a method |
| TBD | dstore | 39 | index | value → | stores a double value into a local variable #index |
| TBD | dstore_0 | 47 |   | value → | stores a double into local variable 0 |
| TBD | dstore_1 | 48 |   | value → | stores a double into local variable 1 |
| TBD | dstore_2 | 49 |   | value → | stores a double into local variable 2 |
| TBD | dstore_3 | 4a |   | value → | stores a double into local variable 3 |
| DoubleOpInstructionVisitor | dsub | 67 |   | value1, value2 → result | subtracts a double from another |
| TBD | dup | 59 |   | value → value, value | duplicates the value on top of the stack |
| TBD | dup_x1 | 5a |   | value2, value1 → value1, value2, value1 | inserts a copy of the top value into the stack two values from the top |
| TBD | dup_x2 | 5b |   | value3, value2, value1 → value1, value3, value2, value1 | inserts a copy of the top value into the stack two (if value2 is double or long it takes up the entry of value3, too) or three values (if value2 is neither double nor long) from the top |
| TBD | dup2 | 5c |   | {value2, value1} → {value2, value1}, {value2, value1} | duplicate top two stack words (two values, if value1 is not double nor long; a single value, if value1 is double or long) |
| TBD | dup2_x1 | 5d |   | value3, {value2, value1} → {value2, value1}, value3, {value2, value1} | duplicate two words and insert beneath third word (see explanation above) |
| TBD | dup2_x2 | 5e |   | {value4, value3}, {value2, value1} → {value2, value1}, {value4, value3}, {value2, value1} | duplicate two words and insert beneath fourth word |
| **F** | | | | | |
| TBD | f2d | 8d |   | value → result | converts a float to a double |
| TBD | f2i | 8b |   | value → result | converts a float to an int |
| TBD | f2l | 8c |   | value → result | converts a float to a long |
| FloatOpInstructionVisitor | fadd | 62 |   | value1, value2 → result | adds two floats |
| TBD | faload | 30 |   | arrayref, index → value | loads a float from an array |
| TBD | fastore | 51 |   | arreyref, index, value → | stores a float in an array |
| TBD | fcmpg | 96 |   | value1, value2 → result | compares two floats |
| TBD | fcmpl | 95 |   | value1, value2 → result | compares two floats |
| TBD | fconst_0 | 0b |   | → 0.0f | pushes 0.0f on the stack |
| TBD | fconst_1 | 0c |   | → 1.0f | pushes 1.0f on the stack |
| TBD | fconst_2 | 0d |   | → 2.0f | pushes 2.0f on the stack |
| FloatOpInstructionVisitor | fdiv | 6e |   | value1, value2 → result | divides two floats |
| TBD | fload | 17 | index | → value | loads a float value from a local variable #index |
| TBD | fload_0 | 22 |   | → value | loads a float value from local variable 0 |
| TBD | fload_1 | 23 |   | → value | loads a float value from local variable 1 |
| TBD | fload_2 | 24 |   | → value | loads a float value from local variable 2 |
| TBD | fload_3 | 25 |   | → value | loads a float value from local variable 3 |
| FloatOpInstructionVisitor | fmul | 6a |   | value1, value2 → result | multiplies two floats |
| TBD | fneg | 76 |   | value → result | negates a float |
| FloatOpInstructionVisitor | frem | 72 |   | value1, value2 → result | gets the remainder from a division between two floats |
| TBD | freturn | ae |   | value → [empty] | returns a float |
| TBD | fstore | 38 | index | value → | stores a float value into a local variable #index |
| TBD | fstore_0 | 43 |   | value → | stores a float value into local variable 0 |
| TBD | fstore_1 | 44 |   | value → | stores a float value into local variable 1 |
| TBD | fstore_2 | 45 |   | value → | stores a float value into local variable 2 |
| TBD | fstore_3 | 46 |   | value → | stores a float value into local variable 3 |
| FloatOpInstructionVisitor | fsub | 66 |   | value1, value2 → result | subtracts two floats |
| **G** | | | | | |
| TBD | getfield | b4 | index1, index2 | objectref → value | gets a field value of an object objectref, where the field is identified by field reference in the constant pool index (index1 << 8 + index2) |
| TBD | getstatic | b2 | index1, index2 | → value | gets a static field value of a class, where the field is identified by field reference in the constant pool index (index1 << 8 + index2) |
| TBD | goto | a7 | branchbyte1, branchbyte2 | [no change] | goes to another instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | goto_w | c8 | branchbyte1, branchbyte2, branchbyte3, branchbyte4 | [no change] | goes to another instruction at branchoffset (signed int constructed from unsigned bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4) |
| **I** | | | | | |
| TBD | i2b | 91 |   | value → result | converts an int into a byte |
| TBD | i2c | 92 |   | value → result | converts an int into a character |
| TBD | i2d | 87 |   | value → result | converts an int into a double |
| TBD | i2f | 86 |   | value → result | converts an int into a float |
| TBD | i2l | 85 |   | value → result | converts an int into a long |
| TBD | i2s | 93 |   | value → result | converts an int into a short |
| IntegerOpInstructionVisitor | iadd | 60 |   | value1, value2 → result | adds two ints together |
| TBD | iaload | 2e |   | arrayref, index → value | loads an int from an array |
| IntegerOpInstructionVisitor | iand | 7e |   | value1, value2 → result | performs a bitwise and on two integers |
| TBD | iastore | 4f |   | arrayref, index, value → | stores an int into an array |
| IConstInstructionVisitor | iconst_m1 | 02 |   | → -1 | loads the int value -1 onto the stack |
| IConstInstructionVisitor | iconst_0 | 03 |   | → 0 | loads the int value 0 onto the stack |
| IConstInstructionVisitor | iconst_1 | 04 |   | → 1 | loads the int value 1 onto the stack |
| IConstInstructionVisitor | iconst_2 | 05 |   | → 2 | loads the int value 2 onto the stack |
| IConstInstructionVisitor | iconst_3 | 06 |   | → 3 | loads the int value 3 onto the stack |
| IConstInstructionVisitor | iconst_4 | 07 |   | → 4 | loads the int value 4 onto the stack |
| IConstInstructionVisitor | iconst_5 | 08 |   | → 5 | loads the int value 5 onto the stack |
| IntegerOpInstructionVisitor | idiv | 6c |   | value1, value2 → result | divides two integers |
| IfACompareInstructionVisitor | if_acmpeq | a5 | branchbyte1, branchbyte2 | value1, value2 → | if references are equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfACompareInstructionVisitor | if_acmpne | a6 | branchbyte1, branchbyte2 | value1, value2 → | if references are not equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmpeq | 9f | branchbyte1, branchbyte2 | value1, value2 → | if ints are equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmpne | a0 | branchbyte1, branchbyte2 | value1, value2 → | if ints are not equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmplt | a1 | branchbyte1, branchbyte2 | value1, value2 → | if value1 is less than value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmpge | a2 | branchbyte1, branchbyte2 | value1, value2 → | if value1 is greater than or equal to value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmpgt | a3 | branchbyte1, branchbyte2 | value1, value2 → | if value1 is greater than value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfICompareInstructionVisitor | if_icmple | a4 | branchbyte1, branchbyte2 | value1, value2 → | if value1 is less than or equal to value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | ifeq | 99 | branchbyte1, branchbyte2 | value → | if value is 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | ifne | 9a | branchbyte1, branchbyte2 | value → | if value is not 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | iflt | 9b | branchbyte1, branchbyte2 | value → | if value is less than 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | ifge | 9c | branchbyte1, branchbyte2 | value → | if value is greater than or equal to 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | ifgt | 9d | branchbyte1, branchbyte2 | value → | if value is greater than 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | ifle | 9e | branchbyte1, branchbyte2 | value → | if value is less than or equal to 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfNullInstructionVisitor | ifnonnull | c7 | branchbyte1, branchbyte2 | value → | if value is not null, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| IfNullInstructionVisitor | ifnull | c6 | branchbyte1, branchbyte2 | value → | if value is null, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) |
| TBD | iinc | 84 | index, const | [No change] | increment local variable #index by signed byte const |
| TBD | iload | 15 | index | → value | loads an int value from a variable #index |
| TBD | iload_0 | 1a |   | → value | loads an int value from variable 0 |
| TBD | iload_1 | 1b |   | → value | loads an int value from variable 1 |
| TBD | iload_2 | 1c |   | → value | loads an int value from variable 2 |
| TBD | iload_3 | 1d |   | → value | loads an int value from variable 3 |
| IntegerOpInstructionVisitor | imul | 68 |   | value1, value2 → result | multiply two integers |
| TBD | ineg | 74 |   | value → result | negate int |
| TBD | instanceof | c1 | indexbyte1, indexbyte2 | objectref → result | determines if an object objectref is of a given type, identified by class reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | invokedynamic | ba | indexbyte1, indexbyte2, 0, 0 | [arg1, [arg2 ...]] → | invokes a dynamic method identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | invokeinterface | b9 | indexbyte1, indexbyte2, count, 0 | objectref, [arg1, arg2, ...] → | invokes an interface method on object objectref, where the interface method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | invokespecial | b7 | indexbyte1, indexbyte2 | objectref, [arg1, arg2, ...] → | invoke instance method on object objectref, where the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | invokestatic | b8 | indexbyte1, indexbyte2 | [arg1, arg2, ...] → | invoke a static method, where the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | invokevirtual | b6 | indexbyte1, indexbyte2 | objectref, [arg1, arg2, ...] → | invoke virtual method on object objectref, where the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| IntegerOpInstructionVisitor | ior | 80 |   | value1, value2 → result | bitwise int or |
| IntegerOpInstructionVisitor | irem | 70 |   | value1, value2 → result | logical int remainder |
| TBD | ireturn | ac |   | value → [empty] | returns an integer from a method |
| IntegerOpInstructionVisitor | ishl | 78 |   | value1, value2 → result | int shift left |
| IntegerOpInstructionVisitor | ishr | 7a |   | value1, value2 → result | int arithmetic shift right |
| TBD | istore | 36 | index | value → | store int value into variable #index |
| TBD | istore_0 | 3b |   | value → | store int value into variable 0 |
| TBD | istore_1 | 3c |   | value → | store int value into variable 1 |
| TBD | istore_2 | 3d |   | value → | store int value into variable 2 |
| TBD | istore_3 | 3e |   | value → | store int value into variable 3 |
| IntegerOpInstructionVisitor | isub | 64 |   | value1, value2 → result | int subtract |
| IntegerOpInstructionVisitor | iushr | 7c |   | value1, value2 → result | int logical shift right |
| IntegerOpInstructionVisitor | ixor | 82 |   | value1, value2 → result | int xor |
| **J** | | | | | |
| TBD | jsr | a8 | branchbyte1, branchbyte2 | → address | jump to subroutine at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) and place the return address on the stack |
| TBD | jsr_w | c9 | branchbyte1, branchbyte2, branchbyte3, branchbyte4 | → address | jump to subroutine at branchoffset (signed int constructed from unsigned bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4) and place the return address on the stack |
| **L** | | | | | |
| TBD | l2d | 8a |   | value → result | converts a long to a double |
| TBD | l2f | 89 |   | value → result | converts a long to a float |
| TBD | l2i | 88 |   | value → result | converts a long to a int |
| TBD | ladd | 61 |   | value1, value2 → result | add two longs |
| TBD | laload | 2f |   | arrayref, index → value | load a long from an array |
| TBD | land | 7f |   | value1, value2 → result | bitwise and of two longs |
| TBD | lastore | 50 |   | arrayref, index, value → | store a long to an array |
| TBD | lcmp | 94 |   | value1, value2 → result | compares two longs values |
| LConstInstructionVisitor | lconst_0 | 09 |   | → 0L | pushes the long 0 onto the stack |
| LConstInstructionVisitor | lconst_1 | 0a |   | → 1L | pushes the long 1 onto the stack |
| TBD | ldc | 12 | index | → value | pushes a constant #index from a constant pool (String, int or float) onto the stack |
| TBD | ldc_w | 13 | indexbyte1, indexbyte2 | → value | pushes a constant #index from a constant pool (String, int or float) onto the stack (wide index is constructed as indexbyte1 << 8 + indexbyte2) |
| TBD | ldc2_w | 14 | indexbyte1, indexbyte2 | → value | pushes a constant #index from a constant pool (double or long) onto the stack (wide index is constructed as indexbyte1 << 8 + indexbyte2) |
| TBD | ldiv | 6d |   | value1, value2 → result | divide two longs |
| TBD | lload | 16 | index | → value | load a long value from a local variable #index |
| TBD | lload_0 | 1e |   | → value | load a long value from a local variable 0 |
| TBD | lload_1 | 1f |   | → value | load a long value from a local variable 1 |
| TBD | lload_2 | 20 |   | → value | load a long value from a local variable 2 |
| TBD | lload_3 | 21 |   | → value | load a long value from a local variable 3 |
| TBD | lmul | 69 |   | value1, value2 → result | multiplies two longs |
| TBD | lneg | 75 |   | value → result | negates a long |
| TBD | lookupswitch | ab | <0-3 bytes padding>, defaultbyte1, defaultbyte2, defaultbyte3, defaultbyte4, npairs1, npairs2, npairs3, npairs4, match-offset pairs... | key → | a target address is looked up from a table using a key and execution continues from the instruction at that address |
| TBD | lor | 81 |   | value1, value2 → result | bitwise or of two longs |
| TBD | lrem | 71 |   | value1, value2 → result | remainder of division of two longs |
| TBD | lreturn | ad |   | value → [empty] | returns a long value |
| TBD | lshl | 79 |   | value1, value2 → result | bitwise shift left of a long value1 by value2 positions |
| TBD | lshr | 7b |   | value1, value2 → result | bitwise shift right of a long value1 by value2 positions |
| TBD | lstore | 37 | index | value → | store a long value in a local variable #index |
| TBD | lstore_0 | 3f |   | value → | store a long value in a local variable 0 |
| TBD | lstore_1 | 40 |   | value → | store a long value in a local variable 1 |
| TBD | lstore_2 | 41 |   | value → | store a long value in a local variable 2 |
| TBD | lstore_3 | 42 |   | value → | store a long value in a local variable 3 |
| TBD | lsub | 65 |   | value1, value2 → result | subtract two longs |
| TBD | lushr | 7d |   | value1, value2 → result | bitwise shift right of a long value1 by value2 positions, unsigned |
| TBD | lxor | 83 |   | value1, value2 → result | bitwise exclusive or of two longs |
| **M** | | | | | |
| TBD | monitorenter | c2 |   | objectref → | enter monitor for object ("grab the lock" - start of synchronized() section) |
| TBD | monitorexit | c3 |   | objectref → | exit monitor for object ("release the lock" - end of synchronized() section) |
| TBD | multianewarray | c5 | indexbyte1, indexbyte2, dimensions | count1, [count2,...] → arrayref | create a new array of dimensions dimensions with elements of type identified by class reference in constant pool index (indexbyte1 << 8 + indexbyte2); the sizes of each dimension is identified by count1, [count2, etc] |
| **N** | | | | | |
| TBD | new | bb | indexbyte1, indexbyte2 | → objectref | creates new object of type identified by class reference in constant pool index (indexbyte1 << 8 + indexbyte2) |
| TBD | newarray | bc | atype | count → arrayref | creates new array with count elements of primitive type identified by atype |
| TBD | nop | 00 |   | [No change] | performs no operation |
| **P** | | | | | |
| TBD | pop | 57 |   | value → | discards the top value on the stack |
| TBD | pop2 | 58 |   | {value2, value1} → | discards the top two values on the stack (or one value, if it is a double or long) |
| TBD | putfield | b5 | indexbyte1, indexbyte2 | objectref, value → | set field to value in an object objectref, where the field is identified by a field reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| TBD | putstatic | b3 | indexbyte1, indexbyte2 | value → | set static field to value in a class, where the field is identified by a field reference index in constant pool (indexbyte1 << 8 + indexbyte2) |
| **R** | | | | | |
| TBD | ret | a9 | index | [No change] | continue execution from address taken from a local variable #index (the asymmetry with jsr is intentional) |
| TBD | return | b1 |   | → [empty] | return void from method |
| **S** | | | | | |
| TBD | saload | 35 |   | arrayref, index → value | load short from array |
| TBD | sastore | 56 |   | arrayref, index, value → | store short to array |
| IPushInstructionVisitor | sipush | 11 | byte1, byte2 | → value | pushes a signed integer (byte1 << 8 + byte2) onto the stack |
| TBD | swap | 5f |   | value2, value1 → value1, value2 | swaps two top words on the stack (note that value1 and value2 must not be double or long) |
| **T** | | | | | |
| TBD | tableswitch | aa | [0-3 bytes padding], defaultbyte1, defaultbyte2, defaultbyte3, defaultbyte4, lowbyte1, lowbyte2, lowbyte3, lowbyte4, highbyte1, highbyte2, highbyte3, highbyte4, jump offsets... | index → | continue execution from an address in the table at offset index |
| **W** | | | | | |
| TBD | wide | c4 | opcode, indexbyte1, indexbyte2 or iinc, indexbyte1, indexbyte2, countbyte1, countbyte2 | [same as for corresponding instructions] | execute opcode, where opcode is either iload, fload, aload, lload, dload, istore, fstore, astore, lstore, dstore, or ret, but assume the index is 16 bit; or execute iinc, where the index is 16 bits and the constant to increment by is a signed 16 bit short |
| **Unused** | | | | | |
| N/A | breakpoint | ca |   |   | reserved for breakpoints in Java debuggers; should not appear in any class file |
| N/A | impdep1 | fe |   |   | reserved for implementation-dependent operations within debuggers; should not appear in any class file |
| N/A | impdep2 | ff |   |   | reserved for implementation-dependent operations within debuggers; should not appear in any class file |
| N/A | (no name) | cb-fd |   |   | these values are currently unassigned for opcodes and are reserved for future use |
| N/A | xxxunusedxxx | ba |   |   | this opcode is reserved "for historical reasons" |
