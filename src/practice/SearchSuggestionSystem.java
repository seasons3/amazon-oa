package practice;

import java.util.*;

/**
 * Time complexity: 
 * 
 * 1) sorting cost time O(m * n * logn), due to involving comparing String. 
 * 2) building Trie cost O(m * n) 
 * 3) searching L
 * 
 * Time: O(m * n * logn + L)
 * 
 * m = average length of products, n = products.length, L = searchWord.length().
 * 
 * space: O(m* n + L * m) including return list res, where m = average length of
 * products
 * 
 * The idea is to sort the products. Build a trie, add a suggestions property
 * where it stores the suggested products for the current prefix.
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
		//insert current product to trie
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

		// search prefix
		Trie cur = root;
		customerquery = customerquery.toLowerCase();
		for (char c : customerquery.toCharArray()) {
			// if there exist products with current prefix
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
