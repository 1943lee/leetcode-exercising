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

	public String frequencySortOther(String s) {
		if(s.length() == 0) return "";
		char[] ch = s.toCharArray();
		int n = ch.length;
		char[] res = new char[n];
		int len = 256;
		int[] bucket = new int[len];

		for(int i = 0; i < n; i++){
			bucket[ch[i]]++;
		}
		int i = 0;
		while(i < n){
			int index = 0;
			int max = 0;
			for(int j = 0; j < len; j++){
				if(bucket[j] > max){
					max = bucket[j];
					index = j;
				}
			}
			while(max > 0){
				res[i++] = (char)index;
				max--;
			}
			bucket[index] = 0;
		}
		return String.valueOf(res);
	}
}
