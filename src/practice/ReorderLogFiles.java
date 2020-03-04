package practice;

import java.util.Arrays;

/**
 * The idea is instead of sorting in the default order, we'll override compare
 * method in a custom order we specify. The rules are: 
 * 1)Letter-logs come before digit-logs; 
 * 2)Letter-logs are sorted alphanumerically, by content then identifier; 
 * 3)Digit-logs remain in the same order. 
 * 
 * Time complexity: O(nlogn)
 * 
 * 
 * @author leen
 *
 */
public class ReorderLogFiles {
	public String[] reorderLogFiles(String[] logs) {
		if (logs == null || logs.length == 0) {
			return new String[0];
		}
		Arrays.sort(logs, (s1, s2) -> {
			String[] word1 = s1.split(" ", 2);
			String[] word2 = s2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(word1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(word2[1].charAt(0));
			if (!isDigit1 && !isDigit2) {
				int comp = word1[1].compareTo(word2[1]);
				if (comp != 0) {
					return comp;
				}
				return word1[0].compareTo(word2[0]);
			}
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
		});
		return logs;
	}

}
