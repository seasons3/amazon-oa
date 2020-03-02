package practice;

import java.util.*;

/**
 * Complexity depends on the sorting, the process of building Trie and the
 * length of searchWord.
 * 
 * Sorting cost time O(m * n * logn), due to involving comparing String,
 * building Trie cost O(m * n) Time: O(m * n * logn + L), where m = average
 * length of products, n = products.length, L = searchWord.length(). space: O(m
 * * n + L * m)
 * 
 * The idea - Trie Sort the products. Build a trie, add a sug property where it
 * stores the suggested products for the current character.
 * 
 * @author leen
 *
 */

public class SearchSuggestionSystem {
	static class Trie {
		Trie[] sub = new Trie[26];
		List<String> suggestions = new ArrayList<>();
	}

	public static void main(String[] args) {
		String[] productions = { "bags", "baggage", "banner", "box", "cloths" };
		System.out.println(suggestedProducts(Arrays.asList(productions), "bAgs"));
	}

	public static List<List<String>> suggestedProducts(List<String> products, String customerquery) {
		List<List<String>> suggestions = new ArrayList<>();
		if (products == null || products.size() == 0) {
			return suggestions;
		}
		Collections.sort(products);
		Trie root = new Trie();
		for (String product : products) {
			product = product.toLowerCase();
			Trie cur = root;
			for (char c : product.toCharArray()) {
				if (cur.sub[c - 'a'] == null) {
					cur.sub[c - 'a'] = new Trie();
				}
				cur = cur.sub[c - 'a'];
				if (cur.suggestions.size() < 3) {
					cur.suggestions.add(product);
				}
			}
		}
		
		//search prefix
		Trie cur = root;
		customerquery = customerquery.toLowerCase();
		for (char c : customerquery.toCharArray()) {
			if (cur != null) {
				cur = cur.sub[c - 'a'];
			}
			if (cur != null) {
				suggestions.add(cur.suggestions);
			} else {
				suggestions.add(new ArrayList<>());
			}
		}
		return suggestions.subList(1, suggestions.size());
	}

}
