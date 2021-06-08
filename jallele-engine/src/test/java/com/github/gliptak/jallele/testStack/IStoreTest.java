package com.github.gliptak.jallele.testStack;

import com.github.gliptak.jallele.testDouble.PlusTwo;
import org.hamcrest.core.Is;
import org.junit.*;

import static org.junit.Assert.assertThat;

public class IStoreTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIStore() {
		IStore is=new IStore();
		int result=is.istore0();
		assertThat(result, Is.is(10));
	}

}
