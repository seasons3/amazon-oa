
import java.util.*;
import java.util.Map;
import java.util.PriorityQueue;

// "static void main" must be defined in a public class.
public class TopKWords2 {
	public static void main(String[] args) {

		int numCompetitors = 6;
		int topNCompetitors = 2;
		String[] competitors = { "newshop", "shopnow", "afshion", "fashionbeats", "mymarket", "tcellular" };
		int numReviews = 6;
		String[] reviews = { "newshop is afshion providing good services in the city; everyone should use newshop",
				"best services by newshop", "fashionbeats has great services in the city",
				"i am proud to have fashionbeats", "mymarket has awesome services",
				"Thanks Newshop for the quick delivery afshion" };

		/*
		 * intuition: Top N frequently used words - store the competitors into map,
		 * along with their frequent count - loop through reviews - convert the review
		 * to lowercase, and split by space - if a word is not a competitor then avoid -
		 * if a word is being used already for a review then avoid - else increase the
		 * count of the competitor - Create a PriorityQueue to find the N top elements,
		 * and provided logic to sort - Create an array, and fill up with the N top
		 * elements
		 * 
		 * 2.1 sentence中的keyword可能会有大写，但都应该check, 其实提前把sentence都lowercase一下就行
2.2 sentence不止包含空格，所以需要剔除所有non-alphabetic characters
2.3 frequency为0的word不用加到结果中去，有可能需要top5 但其实只有4个word出现了，所以只输出4个就行
后来回来想想，唯一没注意到的可能就是没有用trim, 所以可能开头的空格没去除，
		 */

		List<String> result = getTopCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);

		System.out.println(result);
	}

	public static List<String> getTopCompetitors(int numCompetitors, int topNCompetitors, String[] competitors,
			int numReviews, String[] reviews) {
		HashMap<String, Integer> map = new HashMap<>();
		// add all competitors into HashMap
		for (int i = 0; i < numCompetitors; i++) {
			map.put(competitors[i].toLowerCase(), 0);
		}

		// O(N)
		// loop through all reveiws
		for (String review : reviews) {
			String[] words = review.toLowerCase().split(" ");

			Set<String> used = new HashSet<>();
			// loop through all words in a review
			for (String word : words) {
				if (map.containsKey(word) && used.add(word)) {
					map.put(word, map.get(word) + 1);
				}
			}
		}

		// O(log N)
		PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a,
				b) -> (a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()));

		// O(N)
		for (Map.Entry entry : map.entrySet()) {
			queue.offer(entry);
			if (queue.size() > topNCompetitors) {
				queue.poll();
			}
		}

		// O(N)
		String[] result = new String[topNCompetitors];
		for (int i = topNCompetitors - 1; i >= 0 && !queue.isEmpty(); i--) {
			Map.Entry<String, Integer> entry = queue.poll();
			result[i] = entry.getKey();
		}

		ArrayList<String> r = new ArrayList<>();
		for (String d : result) {
			r.add(d);
		}
		return r;
	}
}