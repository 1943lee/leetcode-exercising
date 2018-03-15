package com.leetCodeExercise;

/** test main
 * Created by lcy on 2018/2/26.
 */
public class application {
	public static void main(String[] args) {
		//StringBuffer test = new StringBuffer();
		/*Random random = new Random();
		for(int i = 1000000;i>0;i--) {
			test.append((char)(33 + (126 - 33) * random.nextDouble()));
		}*/

		//String testStr = "PAYPALISHIRING";//test.toString();

		long start = System.currentTimeMillis();

		new solutionTest().isValidChars(new char[] {'2','1','.','9'});

		//List<String> res = new com.leetCodeExercise.solutionTest().generateParenthesis(3);

		long end = System.currentTimeMillis();

		//System.out.println(res);
		System.out.println(end-start + "ms");
	}
}
