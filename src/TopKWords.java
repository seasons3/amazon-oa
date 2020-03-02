import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// "static void main" must be defined in a public class.
public class TopKWords {
	public static void main(String[] args) {
		System.out.println(topToys(6, 2, new String[] { "elmo", "elsa", "legos", "drone", "tablet", "warcraft" }, 6,
				new String[] { "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
						"The new Elmo dolls are super high quality",
						"Expect the Elsa dolls to be very popular this year, Elsa!",
						"Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
						"For parents of older kids, look into buying them a drone",
						"Warcraft is slowly rising in popularity ahead of the holiday season" }));
	}

	public static List<String> topToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
		Map<String, int[]> freq = new HashMap<>();
		for (String toy : toys) {
			freq.put(toy, new int[] { 0, 0 });
		}

		for (String quote : quotes) {
			Set<String> used = new HashSet<>();

			String[] words = quote.toLowerCase().split("\\W+");
			for (String word : words) {
				if (!freq.containsKey(word)) {
					continue;
				}

				int[] nums = freq.get(word);

				nums[0]++;
				if (!used.contains(word)) {
					nums[1]++;
				}

				used.add(word);
			}
		}
		for(Map.Entry<String, int[]> entry: freq.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue()[0] + "-" +entry.getValue()[1]);
		} 

		PriorityQueue<String> pq = new PriorityQueue<>((t1, t2) -> {
			if (freq.get(t1)[0] != freq.get(t2)[0]) {
				return freq.get(t1)[0] - freq.get(t2)[0];
			}

			if (freq.get(t1)[1] != freq.get(t2)[1]) {
				return freq.get(t1)[1] - freq.get(t2)[1];
			}

			return t2.compareTo(t1);
		});

		if (topToys > numToys) {
			for (String toy : freq.keySet()) {
				if (freq.get(toy)[0] > 0) {
					pq.add(toy);
				}
			}
		} else {
			for (String toy : toys) {
				pq.add(toy);

				if (pq.size() > topToys) {
					pq.poll();
				}
			}
		}

		List<String> output = new ArrayList<>();
		while (!pq.isEmpty()) {
			output.add(pq.poll());
		}

		Collections.reverse(output);

		return output;
	}
}