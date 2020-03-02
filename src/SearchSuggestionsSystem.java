import java.util.*;

public class SearchSuggestionsSystem {

	public static void main(String[] args) {
		String[] productions = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
		System.out.println(suggestedProducts(productions, "mouse"));
	}

	static class Trie {
		Trie[] sub = new Trie[26];
		ArrayList<String> suggestions = new ArrayList<>();
	}

	public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> suggestions = new ArrayList<>();
		Arrays.sort(products);
		Trie root = new Trie();
		for (String product : products) {
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
		Trie cur = root;
		for (char c : searchWord.toCharArray()) {
			if (cur != null) {
				cur = cur.sub[c - 'a'];
			}
			if (cur == null) {
				suggestions.add(new ArrayList<>());
			} else {
				suggestions.add(cur.suggestions);
			}

		}
		return suggestions;

	}
}
