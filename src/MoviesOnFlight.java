import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoviesOnFlight {
	static class Pair {
		int index;
		int value;

		public Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	public static List<Integer> findMovies(int[] list, int target) {

		List<Integer> result = new ArrayList<>();
		if (list == null || list.length == 0) {
			return result;
		}
		Pair[] pairs = new Pair[list.length];
		for (int i = 0; i < pairs.length; i++) {
			pairs[i] = new Pair(i, list[i]);
		}
		Arrays.sort(pairs, (p1, p2) -> {
			return p1.value - p2.value;
		});
		result.add(-1);
		result.add(-1);
		int left = 0, right = pairs.length - 1;
		target = target - 30;
		int max = Integer.MIN_VALUE;
		while (left < right) {
			int sum = pairs[left].value + pairs[right].value;
			if (sum > target) {
				right--;
			} else {
				if (sum > max) {
					max = sum;
					result.set(0, pairs[left].index);
					result.set(1, pairs[right].index);
				}

				left++;
			}

		}
		return result;

	}

	public static void main(String[] args) {
		System.out.println(findMovies(new int[] { 90, 85, 75, 60, 120, 150, 125 }, 250));
		System.out.println(findMovies(new int[] { 90, 85, 75, 60, 155, 150, 125 }, 250));
		System.out.println(findMovies(new int[] { 90, 85, 75, 60, 120, 110, 110, 150, 125 }, 250));
		System.out.println(findMovies(new int[] { 95, 85, 75, 60, 120, 110, 110, 150, 125 }, 250));
		System.out.println(findMovies(new int[] { 1, 10, 25, 35, 60 }, 90));
		System.out.println(findMovies(new int[] { 20, 50, 40, 25, 30, 10 }, 90));
		System.out.println(findMovies(new int[] { 5, 55, 40, 20, 30, 30 }, 90));
		/*
		 * [0, 6] [3, 4] [5, 6] [0, 8] [2, 3] [5, 1] [0, 1]
		 * 
		 */
	}
}
