package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Idea is to maintain a hash table while generating substring and checking the
 * number of unique characters using that hash table
 * 
 * O(n*n)
 * 
 * @author leen
 *
 */
public class CountKSubStr {

	public int countkDist(String str, int k) {
		if (str == null || str.length() == 0 || k <= 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			Map<Character, Integer> map = new HashMap<>();
			int distCount = 0;
			for (int j = i; j < str.length(); j++) {
				char c = str.charAt(j);
				if (!map.containsKey(c)) {
					distCount++;
				}
				map.put(c, map.getOrDefault(c, 0) + 1);
				if (distCount == k) {
					res++;
				}
			}
		}
		return res;

	}

	public static void main(String[] args) {
		CountKSubStr ob = new CountKSubStr();
		String ch = "abcbaa";
		int k = 3;
		System.out.println( ob.countkDist(ch, k));
		System.out.println( ob.countkDist("pqpqs", 2));
		System.out.println( ob.countkDist("aabab", 3));
	}
}
