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
}
