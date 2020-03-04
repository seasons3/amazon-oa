package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * map store key = target - num[i], value = index i
 * 
 * maintain maximum to record current ans with larger number
 * 
 * Time complexity: O(n). Space complexity: O(n).
 * 
 * @author leen
 *
 */

public class FindPairWithGivenSum {
	public static void main(String[] args) {
		int[] nums1 = { 1, 10, 25, 35, 60 };
		int target1 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums1, target1)));
		int[] nums2 = { 20, 50, 40, 25, 30, 10 };
		int target2 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums2, target2)));
		int[] nums3 = { 50, 20, 10, 40, 25, 30 };
		int target3 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums3, target3)));
		int[] nums4 = { 1, 2 };
		int target4 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums4, target4)));
		int[] nums5 = { 0, 0 };
		int target5 = 30;
		System.out.println(Arrays.toString(Find2Sum(nums5, target5)));
		int[] nums6 = { 0, 1 };
		int target6 = 30;
		System.out.println(Arrays.toString(Find2Sum(nums5, target5)));
	}

	private static int[] Find2Sum(int[] nums, int target) {
		target = target - 30;
		if (nums == null || nums.length < 2) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int max = Integer.MIN_VALUE;
		// List<Integer> result = Arrays.asList(-1, -1);
		int[] res = new int[] { -1, -1 };
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (nums[i] > max || nums[map.get(nums[i])] > max) {
					res[0] = map.get(nums[i]);
					res[1] = i;
					max = Math.max(nums[i], nums[map.get(nums[i])]);
				}
			}
			map.put(target - nums[i], i);

		}
		if (res[0] != -1 && res[1] != -1) {
			return res;
		}
		return null;
	}
}
