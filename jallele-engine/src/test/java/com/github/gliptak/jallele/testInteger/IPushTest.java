package com.github.gliptak.jallele.testInteger;

import org.hamcrest.core.Is;
import org.junit.*;

import static org.junit.Assert.assertThat;

public class IPushTest {

	@Test
	public final void testIPushByte() {
		IPush ip = new IPush();
		byte result = ip.pushbyte();
		assertThat(result, Is.is((byte)10));
	}

	@Test
	public final void testIPushShort() {
		IPush ip = new IPush();
		short result = ip.pushshort();
		assertThat(result, Is.is((short)20));
	}

}
