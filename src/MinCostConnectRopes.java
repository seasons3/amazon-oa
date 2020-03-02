import java.util.PriorityQueue;
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
		Queue<Integer> minHeap = new PriorityQueue<>();
		for (int file : files) {
			minHeap.offer(file);
		}
		int res = 0;
		while (minHeap.size() > 1) {
			int f1 = minHeap.poll();
			int f2 = minHeap.poll();
			int temp = f1 + f2;
			res += temp;
			minHeap.offer(temp);
		}
		return res;
	}
}
