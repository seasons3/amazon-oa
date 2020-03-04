package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Idea is to use Max-Heap of size k for maintaining K minimum distances. We
 * repeatedly add elements to the heap. When the size is greater than K, we
 * remove the largest element.
 * 
 * Space: O(k) - max heap of size k
 * 
 * Time: O(n logk + k)
 * 
 * @author leen
 *
 */

public class CloestKPoints {
	public static List<List<Integer>> topK(int numDestinations, List<List<Integer>> locations, int numDeliveries) {
		if (locations == null || locations.size() == 0) {
			return new ArrayList<>();
		}
		List<List<Integer>> ans = new ArrayList<>();
		Queue<List<Integer>> pq = new PriorityQueue<List<Integer>>(numDeliveries, (t1, t2) -> {
			int dist2 = t2.get(0) * t2.get(0) + t2.get(1) * t2.get(1);
			int dist1 = t1.get(0) * t1.get(0) + t1.get(1) * t1.get(1);
			return dist2 - dist1;

		});

		for (List<Integer> location : locations) {
			pq.offer(location);
			if (pq.size() > numDeliveries) {
				pq.poll();
			}
		}
		while (!pq.isEmpty()) {
			ans.add(0, pq.poll());
		}
		return ans;
	}

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(Arrays.asList(1, 2));
		input.add(Arrays.asList(3, 4));
		input.add(Arrays.asList(1, -1));
		int n = 3;
		int k = 2;
		System.out.println(topK(n, input, k));
	}

}
