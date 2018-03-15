package com.leetCodeExercise;

import java.util.*;
import java.util.regex.Pattern;

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

	private void fill(StringBuilder sb, int start, int initialDistance, int magic, String s) {
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

	public int myAtoi(String str) {
		if(str==null || str.isEmpty()) return 0;

		str = str.trim();
		if(str.isEmpty()) return 0;

		int res = 0;
		int positive = 1;

		char first = str.charAt(0);
		if(first=='-') {
			positive = -1;
			str = str.substring(1);
		}
		else if(first=='+') {
			str = str.substring(1);
		}

		for(char s:str.toCharArray()) {
			if(s>='0' && s<='9') {
				if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && s - '0' > 7)) {
					return (positive == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				}
				res = 10*res + s - '0';
			}
			else {
				return res*positive;
			}
		}
		return res*positive;
	}

	//符号位(+/-???)、小数(?.?)、科学计数法({}e?(integer))
	public boolean isNumber(String s) {
		s=s.trim();
		if(s.isEmpty()) return false;

		return Pattern.matches("(\\+|-)?(\\d+(\\.\\d*)?|\\.\\d+)(e(\\+|-)?\\d+)?", s);
	}

	public int calculate(String s) {
		if (s == null) return 0;
		s = s.trim().replaceAll(" +", "");
		int length = s.length();

		int res = 0;
		long preVal = 0; // initial preVal is 0
		char sign = '+'; // initial sign is +
		int i = 0;
		while (i < length) {
			long curVal = 0;
			while (i < length && (int)s.charAt(i) <= 57 && (int)s.charAt(i) >= 48) { // int
				curVal = curVal*10 + (s.charAt(i) - '0');
				i++;
			}
			if (sign == '+') {
				res += preVal;  // update res
				preVal = curVal;
			} else if (sign == '-') {
				res += preVal;  // update res
				preVal = -curVal;
			} else if (sign == '*') {
				preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
			} else if (sign == '/') {
				preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
			}
			if (i < length) { // getting new sign
				sign = s.charAt(i);
				i++;
			}
		}
		res += preVal;
		return res;
	}

	public boolean isPalindrome(int x) {
		// 负数不可能为回文
		if(x<0) return false;
		int bitCounts = 0;
		int tmp = x;
		while (tmp>0) {
			bitCounts ++;
			tmp /= 10;
		}

		while (bitCounts > 0) {
			int high = (int) (x / Math.pow(10,bitCounts-1));
			int low = x%10;
			if(high != low) return false;
			x -= high * Math.pow(10,bitCounts-1);
			x /= 10;
			bitCounts -= 2;
		}
		return true;
	}

	//支持.和*的字符串匹配
	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();

		boolean[][] dp = new boolean[m + 1][n + 1];
		//initialize
		dp[0][0] = true;

		for (int i = 0; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (p.charAt(j - 1) != '*') {
					dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
				} else {
					dp[i][j] = dp[i][j - 2] || (i > 0 && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
				}
			}
		}

		return dp[m][n];
	}

	//非负数，水桶问题
	//超时，复杂度O(n2)
	//双向指针，复杂度O(n)
	public int maxAreaO(int[] height) {
		int max = 0;
		int l = 0, r = height.length - 1;
		while(l < r) {
			max = Math.max(max, (r-l)*Math.min(height[r],height[l]));
			if(height[l]<height[r]) {
				l++;
			}
			else {
				r--;
			}
		}

		return max;
	}

	//三数之和为0
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for(int i=0;i<nums.length-2;i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
				int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
				while (l < r) {
					if (nums[l] + nums[r] == sum) {
						res.add(Arrays.asList(nums[i], nums[l], nums[r]));
						while (l < r && nums[l] == nums[l + 1]) l++;
						while (l < r && nums[r] == nums[r - 1]) r--;
						l++;
						r--;
					} else if (nums[l] + nums[r] < sum) l++;
					else r--;
				}
			}
		}

		return res;
	}

	//三数之和最接近target
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int res = nums[0] + nums[1] + nums[2];
		for(int i=0;i<nums.length-2;i++) {
			int l=i+1,h=nums.length-1;
			while (l<h) {
				int sum=nums[i]+nums[l]+nums[h];
				if(Math.abs(target-res) >= Math.abs(target-sum)) {
					res = sum;
					if(sum == target) return sum;
				}
				if(sum > target) h--;
				else if(sum < target) l++;
			}
		}
		return res;
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for(int i = 0; i < nums.length-3; i++) {
			if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target) break;
			if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]<target) continue;
			if(i>0 && nums[i]==nums[i-1]) continue;
			for (int j = i+1; j < nums.length - 2; j++) {
				if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target) break;
				if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2]<target) continue;
				if(j>i+1 && nums[j]==nums[j-1]) continue;

				int l = j + 1, r = nums.length - 1;
				while (l < r) {
					int sum = nums[i] + nums[j] + nums[l] + nums[r];
					if (sum == target) {
						res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
						while (l < r && nums[l] == nums[l + 1]) l++;
						while (l < r && nums[r] == nums[r - 1]) r--;
						l++;
						r--;
					} else if (sum < target) l++;
					else r--;
				}
			}
		}

		return res;
	}

	public String intToRoman(int num) {
		if (num < 1 || num > 3999) return "";

		StringBuilder result = new StringBuilder();

		String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

		int i = 0;
		//iterate until the number becomes zero, NO NEED to go until the last element in roman array
		while (num >0) {
			while ( num >= values[i]) {
				num -= values[i];
				result.append(roman[i]);
			}
			i++;
		}
		return result.toString();
	}

	public int romanToInt(String s) {
		Map<Character, Integer> romeToNatural = new HashMap<>();
		romeToNatural.put('I', 1);
		romeToNatural.put('V', 5);
		romeToNatural.put('X', 10);
		romeToNatural.put('L', 50);
		romeToNatural.put('C', 100);
		romeToNatural.put('D', 500);
		romeToNatural.put('M', 1000);

		int result = 0;
		int index = s.length() - 1;
		int preInt = 0;
		while (index >= 0)
		{
			char ch = s.charAt(index);
			int curInt = romeToNatural.get(ch);
			if (curInt >= preInt)
				result += curInt;
			else
				result -= curInt;

			preInt = curInt;
			index--;
		}

		return result;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		for (int i = 0; i < strs[0].length() ; i++){
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j ++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		return strs[0];
	}

	public String longestCommonPrefixO(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		int minLen = Integer.MAX_VALUE;
		for (String str : strs)
			minLen = Math.min(minLen, str.length());
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle))
				low = middle + 1;
			else
				high = middle - 1;
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len){
		String str1 = strs[0].substring(0,len);
		for (int i = 1; i < strs.length; i++)
			if (!strs[i].startsWith(str1))
				return false;
		return true;
	}

	public List<String> letterCombinations(String digits) {
		LinkedList<String> res = new LinkedList<>();
		if(null == digits || digits.isEmpty()) return res;

		String[] buttons = new String[] {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

		res.add("");
		for(int i=0;i<digits.length();i++) {
			while (res.peek().length()==i) {
				int num = Character.getNumericValue(digits.charAt(i));
				String pre = res.remove();
				for(char ch:buttons[num].toCharArray()) {
					res.add(pre + ch);
				}
			}
		}

		return res;
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}

	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, String cur, int open, int close, int max){
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}

		if (open < max)
			backtrack(ans, cur+"(", open+1, close, max);
		if (close < open)
			backtrack(ans, cur+")", open, close+1, max);
	}

	public int removeDuplicates(int[] nums) {
		if(nums.length == 0) return 0;
		int j = 0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i] != nums[j]) nums[++j] = nums[i];
		}
		return ++j;
	}

	public int strStr(String s, String t) {
		if (t.isEmpty()) return 0; // edge case: "",""=>0  "a",""=>0
		for (int i = 0; i <= s.length() - t.length(); i++) {
			for (int j = 0; j < t.length() && s.charAt(i + j) == t.charAt(j); j++)
				if (j == t.length() - 1) return i;
		}
		return -1;
	}

	public boolean reaptedSubstringPattern(String str) {
		int len = str.length();
		for(int i=len/2 ; i>=1 ; i--) {
			if(len%i == 0) {
				int m = len/i;
				String subS = str.substring(0,i);
				int j;
				for(j=1;j<m;j++) {
					if(!subS.equals(str.substring(j*i,i+j*i))) break;
				}
				if(j==m)
					return true;
			}
		}
		return false;
	}

	public int lastRemaining(int n) {
		return removeInt(1, n, 1, true);
	}

	private int removeInt(int start, int end, int distance, boolean leftToRight) {
		if(start < end) {
			if(leftToRight) {
				start = start + distance;
				end = ((end - start)/(distance))%2 == 0 ? end : end - distance;
			} else {
				start = ((end - start)/(distance))%2 == 1 ? start : start + distance;
				end = end - distance;
			}
			distance = 2 * distance;
			leftToRight = !leftToRight;
			end = removeInt(start, end, distance, leftToRight);
		}
		return end;
	}

	public boolean isValidSudoku(char[][] board) {
		//1.horizental
		for(int i=0; i<9; i++) {
			char[] tmp = new char[] {board[i][0],board[i][1],board[i][2],board[i][3],board[i][4],board[i][5],board[i][6],board[i][7],board[i][8]};
			if(!isValidChars(tmp)) return false;
		}
		//2.vertical
		for(int i=0; i<9; i++) {
			char[] tmp = new char[] {board[0][i],board[1][i],board[2][i],board[3][i],board[4][i],board[5][i],board[6][i],board[7][i],board[8][i]};
			if(!isValidChars(tmp)) return false;
		}
		//3.box
		for(int i=0; i<9; i+=3) {
			for(int j=0; j<9; j+=3) {
				char[] tmp = new char[] {board[i][j],board[i+1][j],board[i+2][j],board[i][j+1],board[i+1][j+1],board[i+2][j+1],board[i][j+2],board[i+1][j+2],board[i+2][j+2]};

				if(!isValidChars(tmp)) return false;
			}
		}
		return true;
	}
	public boolean isValidChars(char[] chars) {
		Arrays.sort(chars);
		int[] bits = new int[126];
		for(int i=0; i<9; i++) {
			if(chars[i] != '.') {
				bits[chars[i]]++;
				if(bits[chars[i]] > 1) return false;
			}
		}
		return true;
	}
}
