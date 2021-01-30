package com.github.gliptak.jallele;

import com.github.gliptak.jallele.testStack.IStore;
import org.junit.Test;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class ClassReaderTest {
    @Test
    public void testClassReader() throws IOException {
        ClassReader cr = new ClassReader(IStore.class.getName());
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(final int access,
                                             final String name, final String desc,
                                             final String signature, final String[] exceptions) {
                MethodVisitor mv=super.visitMethod(access, name, desc, signature,
                        exceptions);
                return null;
            }
        };
        cr.accept(cv, 0);
        System.out.println(bytesToHex(cw.toByteArray()));
    }

    @Test
    public void testTrace() throws IOException {
        PrintWriter pw = new PrintWriter(System.out);
        ClassReader cr = new ClassReader(IStore.class.getName());
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, pw);
        cr.accept(cv, 0);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
