package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Split the paragraph and then clean up the fragment "Bob!" to be "bob".
 * 
 * For each word (lowercase, and free of punctuation), we'll update our count
 * and update the answer if the count of that word is highest (and the word is
 * not banned).
 * 
 * @author leen
 *
 */
public class MostCommonWord {
	public String mostCommonWord(String paragraph, String[] banned) {
		if (paragraph == null || paragraph.length() == 0) {
			return "";
		}
		Map<String, Integer> countMap = new HashMap<>();
		Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

		paragraph = paragraph.toLowerCase();
		String[] words = paragraph.split("\\W+");
		String ans = "";
		int mostCount = 0;
		for (String word : words) {
			word = word.replaceAll("[^a-z]", "");
			// System.out.println(word);
			if (!bannedSet.contains(word)) {
				int count = countMap.getOrDefault(word, 0) + 1;
				countMap.put(word, count);
				if (count > mostCount) {
					mostCount = count;
					ans = word;
				}
			}
		}
		return ans;

	}

}
