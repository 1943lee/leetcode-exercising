import java.util.Date;
import java.util.Random;

/**
 * Created by lcy on 2018/2/26.
 */
public class main {
	public static void main(String[] args) {
		StringBuffer test = new StringBuffer();
		Random random = new Random();
		for(int i = 1000000;i>0;i--) {
			test.append((char)(33 + (126 - 33) * random.nextDouble()));
		}

		String testStr = test.toString();

		long start = new Date().getTime();

		String res = new solutionTest().frequencySortOther(testStr);

		long end = new Date().getTime();

		System.out.println(res);
		System.out.println(end-start + "ms");

		long start1 = new Date().getTime();

		String res1 = new solutionTest().frequencySort(testStr);

		long end1 = new Date().getTime();

		System.out.println(res1);
		System.out.println(end1-start1 + "ms");
	}
}
