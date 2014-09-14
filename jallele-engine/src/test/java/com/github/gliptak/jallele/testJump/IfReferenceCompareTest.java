/**
 * 
 */
package com.github.gliptak.jallele.testJump;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;

/**
 * @author gliptak
 *
 */
public class IfReferenceCompareTest {

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfReferenceCompare#ifReferenceCompare(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIfReferenceCompare() {
		IfReferenceCompare irc=new IfReferenceCompare();
		assertThat(irc.ifReferenceCompare("foo", "foo"), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfReferenceCompare#ifReferenceCompare(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIfReferenceCompareNeg() {
		IfReferenceCompare irc=new IfReferenceCompare();
		assertThat(irc.ifReferenceCompare("foo", "bar"), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfReferenceCompare#ifNotReferenceCompare(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIfNotReferenceCompare() {
		IfReferenceCompare irc=new IfReferenceCompare();
		assertThat(irc.ifNotReferenceCompare("foo", "bar"), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfReferenceCompare#ifNotReferenceCompare(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIfNotReferenceCompareNot() {
		IfReferenceCompare irc=new IfReferenceCompare();
		assertThat(irc.ifNotReferenceCompare("foo", "foo"), Is.is("false"));
	}

}
