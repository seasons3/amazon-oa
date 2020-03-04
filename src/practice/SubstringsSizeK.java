package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * start and end denote the range of the sliding window. Particularly, end is
 * the cursor that from 0 to n - 1.
 * 
 * @author leen
 *
 */

public class SubstringsSizeK {
	public static List<String> kSubstring(String s, int k) {
		if (s == null || s.length() == 0 || s.length() < k) {
			return new ArrayList<>();
		}
		int start = 0;
		Set<String> result = new HashSet<>();
		Set<Character> window = new HashSet<>();
		for (int end = 0; end < s.length(); end++) {
			char c = s.charAt(end);
			// remove all elements that is before the existing element
			while (window.contains(c)) {
				window.remove(s.charAt(start++));
			}
			window.add(c);
			if (window.size() == k) {
				result.add(s.substring(start, end + 1));
				window.remove(s.charAt(start++));
			}
		}
		return new ArrayList<>(result);
	}

	public static void main(String[] args) {
		System.out.println(kSubstring("awaglknagawunagwkwagl", 4));
	}
}
