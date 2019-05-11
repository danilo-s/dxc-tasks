package it.danilo.main;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MainTaskTest {

	@Parameter(0)
	public int m1;
	@Parameter(1)
	public String result;

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 0, stringGenerator(0) }, { 1, stringGenerator(1) },
				{ 3, stringGenerator(3) }, { 4, stringGenerator(4) }, { 5, stringGenerator(5) },  { 8, stringGenerator(8) } , { 10, stringGenerator(10) }  };
		return Arrays.asList(data);
	}

	@Test
	public void testMultiplyException() {
		MainTask tester = new MainTask();
		assertEquals("Result", result, tester.starString(m1));
	}

	private static String stringGenerator(int n) {
		StringBuilder sb = new StringBuilder();
		double pow = Math.pow(2, n);
		int c = 0;
		while (c < pow) {
			sb.append("*");
			c++;
		}
		return sb.toString();
	}

}
