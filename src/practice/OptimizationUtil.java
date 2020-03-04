package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The idea is to use two pointers.
 * Sort forwardRouteList and returnRouteList by its second element as first.
 * 
 * Use two pointers and traverse two sorted arrays to find the max value (and
 * the combination of course).
 * 
 * Time complexity : Since the sorting part complexity is O(MlogM + NlogN) and
 * two-pointer traversal is O(M + N), the final complexity can be regarded as
 * O(KlogK) where K is the longest input array.
 * 
 * @author leen
 *
 */
public class OptimizationUtil {
	public static void main(String[] args) {
		List<int[]> list1 = new ArrayList<>();
		List<int[]> list2 = new ArrayList<>();
		int[][] array1 = { { 1, 2 }, { 2, 4 }, { 3, 6 } };
		int[][] array2 = { { 1, 2 } };
		for (int[] a : array1) {
			list1.add(a);
		}
		for (int[] a : array2) {
			list2.add(a);
		}

		List<int[]> result = getPairs(list1, list2, 7);
		for (int[] a : result) {
			System.out.println(a[0] + ":" + a[1]);
		}

		int[][] array3 = { { 1, 3 }, { 2, 5 }, { 3, 7 }, { 4, 10 } };
		int[][] array4 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		List<int[]> list3 = new ArrayList<>();
		List<int[]> list4 = new ArrayList<>();
		for (int[] a : array3) {
			list3.add(a);
		}
		for (int[] a : array4) {
			list4.add(a);
		}
		result = getPairs(list3, list4, 10);
		for (int[] a : result) {
			System.out.println(a[0] + ":" + a[1]);
		}
	}

	private static List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {

		if (a == null || a.size() == 0) {
			return new ArrayList<>();
		}

		if (b == null || b.size() == 0) {
			return new ArrayList<>();
		}
		Collections.sort(a, (t1, t2) -> {
			return t1[1] - t2[1];
		});
		Collections.sort(b, (t1, t2) -> {
			return t1[1] - t2[1];
		});
		List<int[]> result = new ArrayList<>();
		int i = 0, j = b.size() - 1;
		int max = Integer.MIN_VALUE;
		while (i < a.size() && j >= 0) {
			int sum = a.get(i)[1] + b.get(j)[1];
			if (sum > target) {
				j--;
			} else {
				if (sum >= max) {
					if (sum > max) {
						max = sum;
						result.clear();

					}
					result.add(new int[] { a.get(i)[0], b.get(j)[0] });
					int index = j - 1;
					while (index >= 0 && b.get(index)[1] == b.get(index + 1)[1]) {
						result.add(new int[] { a.get(i)[0], b.get(j)[0] });
						index--;

					}
				}
				i++;
			}
		}
		return result;
	}

}
