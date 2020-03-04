package practice;

/**
 * we could solve it in O(n^2)O(n 2 ) time using only constant space.
 * 
 * We observe that a palindrome mirrors around its center. Therefore, a
 * palindrome can be expanded from its center,
 * 
 * @author leen
 *
 */

public class LongestPalindromicSubstring {

	private int getLength(String s, int start, int end) {
		while (start >= 0 && end < s.length()) {
			if (s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			} else {
				break;
			}
		}
		return end - start - 1;
	}

	public String longestPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		int max = 0;
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			int length = Math.max(getLength(s, i, i), getLength(s, i, i + 1));
			if (length > max) {
				max = length;
				start = i - (length - 1) / 2;
			}
		}
		return s.substring(start, start + max);

	}
}
