package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Idea is 
 * 1)store the competitors(toys) into map, along with their frequent count and quote count(review count)
 * 2)loop through reviews(quotes):
       - convert the review to lowercase, and split by space
       - if a word is not a competitor(toy) then do nothing
       - if a word is being used already for the current review(quote), then avoid to increase quote count
       - else increase the freq count and quote count of the competitor(toy)
 * 3) create a PriorityQueue(minheap), and provide the logic to sort the top words. 
 * Time complexity: O(W)+O(NlogK) 
 * W - total number of words(quotes * words in each quote) to build map
 * N total number of toys.
 * K max number of toys to return;
 * 
 * @author leen
 *
 */
public class TopNToys {
	static class WordStats {
		int freqCount;
		int quoteCount;

		public WordStats(int freqCount, int quoteCount) {
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
		// Base case: if toys list is empty or quotes list is empty, the output list
		// should also be []
		if (toys == null || toys.length == 0) {
			return result;
		}
		if (quotes == null || quotes.length == 0) {
			return result;
		}
		// Dict to store toy buzzword as key and value as class in form of frequency and
		// quote counts
		Map<String, WordStats> map = new HashMap<>();
		for (String toy : toys) {
			map.put(toy, new WordStats(0, 0));
		}
		// Iterate through all the quotes, and update count
		for (String quote : quotes) {
			String[] words = quote.toLowerCase().split("\\W+");
			Set<String> used = new HashSet<>();
			for (String word : words) {
				word = word.replaceAll("[^a-z]", "");
				if (!map.containsKey(word)) {
					continue;
				}
				map.get(word).freqCount++;
				if (!used.contains(word)) {
					map.get(word).quoteCount++;
				}
			}
		}
		PriorityQueue<String> pq = new PriorityQueue<String>((t1, t2) -> {
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
					pq.offer(toy);
				}
			}
		} else {
			for (String toy : toys) {
				pq.offer(toy);
				if (pq.size() > topToys) {
					pq.poll();
				}
			}
		}
		while (!pq.isEmpty()) {
			result.add(0, pq.poll());
		}
		return result;
	}
}
