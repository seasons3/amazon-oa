package practice;

import java.util.PriorityQueue;

/**
 * The idea is to use priority queue to solve the problem:  
 * every step you pick two shortest ropes from the priority queue and connect them, 
 * then put it back to the queue, 
 * you keep this process until there is only one left.
 * 
 *  O(NlogN) time complexity and O(N) space.
 */
import java.util.Queue;

public class MinCostConnectRopes {
	public static void main(String[] args) {
		int[] files1 = { 8, 4, 6, 12 };
		int[] files2 = { 20, 4, 8, 2 };
		int[] files3 = { 1, 2, 5, 10, 35, 89 };
		int[] files4 = { 2, 2, 3, 3 };
		System.out.println(mergeFiles(files1));
		System.out.println(mergeFiles(files2));
		System.out.println(mergeFiles(files3));
		System.out.println(mergeFiles(files4));
		/**
		 * 58 54 224 20
		 */
	}

	private static int mergeFiles(int[] files) {
		if (files == null || files.length <= 1) {
			return 0;
		}
		Queue<Integer> pq = new PriorityQueue<>();
		for (Integer file : files) {
			pq.offer(file);
		}
		int result = 0;
		while (pq.size() != 1) {
			int f1 = pq.poll();
			int f2 = pq.poll();
			int temp = f1 + f2;
			result += temp;
			pq.offer(temp);

		}
		return result;
	}
}
