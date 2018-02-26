/**
 * Created by lcy on 2018/2/26.
 */
public class solutionTest {
	public String frequencySort(String s) {
		if(s.isEmpty()) return "";

		int n = s.length();
		int[] frequency = new int[256];
		for(int i=0;i<n;i++) {
			frequency[s.charAt(i)]++;
		}

		char[] res = new char[n];

		int i = 0;
		while(i<n) {
			int max = 0;
			int index = 0;

			for(int j=0;j<256;j++) {
				if(frequency[j]>max) {
					max=frequency[j];
					index=j;
				}
			}
			while(max>0) {
				res[i++]=(char)index;
				max--;
			}
			frequency[index] = 0;
		}

		return String.valueOf(res);
	}

	public String longestPalindrome(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		int start = 0, max = 1;

		for(int i = n - 1; i >= 0; i--) {
			for(int j = i; j < n; j++) {
				if((s.charAt(i) == s.charAt(j)) && (i+1>j-1 || dp[i+1][j-1])) {
					dp[i][j] = true;
					if(j-i+1>max) {
						max = j-i+1;
						start = i;
					}
				}
			}
		}

		return s.substring(start,start+max);
	}

	public String convert(String s, int numRows) {
		char[] c = s.toCharArray();
		int len = c.length;
		StringBuffer[] sb = new StringBuffer[numRows];
		for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

		int i = 0;
		while (i < len) {
			for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
				sb[idx].append(c[i++]);
			for (int idx = numRows-2; idx >= 1 && i < len; idx--) // obliquely up
				sb[idx].append(c[i++]);
		}
		for (int idx = 1; idx < sb.length; idx++)
			sb[0].append(sb[idx]);
		return sb[0].toString();
	}

	//https://segmentfault.com/a/1190000005751185
	public String convertOther(String s, int numRows) {
		if (numRows == 1)
			return s;
		StringBuilder sb = new StringBuilder();
		int magic = numRows * 2 - 2;
		int initialDistance = magic;
		for (int i = 0; i < numRows; i++) {
			fill(sb, i, initialDistance, magic, s);
			initialDistance = initialDistance - 2; //对应思路讲解第5条
		}
		return sb.toString();
	}

	public void fill(StringBuilder sb, int start, int initialDistance, int magic, String s) {
		StringBuffer outStr = new StringBuffer();
		for(int i=start;i>0;i--) {
			outStr.append(" ");
		}
		while (start < s.length()) {
			if (initialDistance == 0)    //对应思路讲解第6条
				initialDistance = magic;
			outStr.append(s.charAt(start));
			for(int i=initialDistance;i>1;i--) {
				outStr.append(" ");
			}
			sb.append(s.charAt(start));
			start = start + initialDistance;
			initialDistance = magic - initialDistance;//对应思路讲解第4条
		}
		System.out.println(outStr.toString());
	}

	public int reverse(int x) {
		long val = 0;
		do {
			val = 10*val+x%10;
			x/=10;
		}while (x != 0);

		return (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) ? 0 : (int) val;
	}
}
