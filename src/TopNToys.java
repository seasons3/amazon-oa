import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TopNToys {
	static class ToyStats {
		int freqCount;
		int quoteCount;

		public ToyStats(int freqCount, int quoteCount) {
			this.freqCount = freqCount;
			this.quoteCount = quoteCount;
		}
	}

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

		List<String> result = new ArrayList<>();
		if (toys == null || toys.length == 0) {
			return result;

		}
		if (quotes == null || quotes.length == 0) {
			return result;
		}

		Map<String, ToyStats> map = new HashMap<>();
		for (String toy : toys) {
			map.put(toy.toLowerCase(), new ToyStats(0, 0));
		}

		for (String quote : quotes) {
			Set<String> used = new HashSet<>();
			String[] words = quote.toLowerCase().split("\\W+");
			for (String word : words) {
				word = word.replaceAll("[^a-z]", "");
				if (!map.containsKey(word)) {
					continue;
				}
				ToyStats stats = map.get(word);
				stats.freqCount++;
				if (!used.contains(word)) {
					stats.quoteCount++;
					used.add(word);
				}
			}
		}
		for (Map.Entry<String, ToyStats> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().freqCount + "-" + entry.getValue().quoteCount);
		}

		PriorityQueue<String> queue = new PriorityQueue<>((t1, t2) -> {
			if (map.get(t1).freqCount != map.get(t2).freqCount) {
				return map.get(t1).freqCount - map.get(t2).freqCount;
			}

			if (map.get(t1).quoteCount != map.get(t2).quoteCount) {
				return map.get(t1).quoteCount - map.get(t2).quoteCount;
			}
			return t2.compareTo(t1);
		});

		if (topToys > numToys) {
			for (String toy : toys) {
				if (map.get(toy).freqCount > 0) {
					queue.offer(toy);
				}
			}
		} else {
			for (String toy : toys) {
				queue.offer(toy);
				if (queue.size() > topToys) {
					queue.poll();
				}
			}
		}

		while (!queue.isEmpty()) {
			result.add(0, queue.poll());
		}

		return result;
	}
}
