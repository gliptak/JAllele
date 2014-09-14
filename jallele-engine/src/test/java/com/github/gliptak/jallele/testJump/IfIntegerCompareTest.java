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
public class IfIntegerCompareTest {

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareEqual() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareEqual(10, 10), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareEqualNeg() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareEqual(10, 11), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareNotEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareNotEqual() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareNotEqual(10, 11), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareNotEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareNotEqualNeg() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareNotEqual(10, 10), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareLess(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareLess() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareLess(10, 11), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareLess(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareLessNeg() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareLess(11, 10), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareLessEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareLessEqual() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareLessEqual(10, 11), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareLessEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareLessEqualNeg() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareLessEqual(11, 10), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareGreater(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareGreater() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareGreater(11, 10), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareGreater(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareGreaterNot() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareGreater(10, 11), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareGreaterEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareGreaterEqual() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareGreaterEqual(11, 10), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfIntegerCompare#ifIntegerCompareGreaterEqual(int, int)}.
	 */
	@Test
	public final void testIfIntegerCompareGreaterEqualNeg() {
		IfIntegerCompare iic=new IfIntegerCompare();
		assertThat(iic.ifIntegerCompareGreaterEqual(10, 11), Is.is("false"));
	}

}
