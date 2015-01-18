package test.java;
 
import org.testng.annotations.Test;
 
public class VerifyTests extends TestBase {
 
	@Test
	public void test1() {
		verifyTrue(false);
		verifyEquals("pass", "fail");
		verifyFalse(true);
	}
 
	@Test
	public void test2() {
		verifyTrue(false);
		assertEquals("pass", "fail");
		verifyFalse(true);
	}
 
	@Test
	public void test3() {
		verifyTrue(true);
		verifyTrue(false);
		verifyTrue(true);
	}
 
	@Test
	public void test4() {
		assertTrue(true);
		assertTrue(false);
		assertTrue(true);
	}
 
}