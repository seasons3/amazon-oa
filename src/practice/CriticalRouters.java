package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1) Apply DFS on a graph to get the DFS tree.
 * 2) In DFS tree, a vertex v is critical point if one of the following two conditions is true: 
 *    2.1) if v is root of DFS tree, and it has at least two children
 *    2.2) if v is not root of DFS tree, and there is not a child w of v such that 
 *         there are no back edges from the tree rooted at w that go strictly above v.
 * 
 * Solution: Tarjan 
 * complexity: O(v+e) Space complexity: O(v+e)
 * v is numRouters1
 * e is numLinks1.length
 * 
 * 
 */
public class CriticalRouters {
	private static int time = 0;

	public static void main(String[] args) {
		int numRouters1 = 7;
		int numLinks1 = 7;
		int[][] links1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 5, 6 }, { 3, 4 } };
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}

	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		List<Integer> nodes = new ArrayList<>();
		if (links == null || links.length == 0) {
			return nodes;
		}
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < numRouters; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		int[] low = new int[numRouters];
		int[] disc = new int[numRouters];
		int[] parent = new int[numRouters];
		Arrays.fill(parent, -1);
		Arrays.fill(disc, -1);
		for (int i = 0; i < numRouters; i++) {
			if (disc[i] == -1) {
				dfsHelper(map, i, parent, disc, low, nodes);
			}
		}

		return nodes;

	}

	private static void dfsHelper(Map<Integer, Set<Integer>> map, int v, int[] parent, int[] disc, int[] low,
			List<Integer> nodes) {
		disc[v] = low[v] = time++;
		int child = 0;
		// 1 ..2
		for (Integer adj : map.get(v)) {
			if (parent[v] == adj) {
				continue;
			}
			if (disc[adj] == -1) {
				child++;
				parent[adj] = v;
				dfsHelper(map, adj, parent, disc, low, nodes);
				low[v] = Math.min(low[v], low[adj]);
				if (parent[v] == -1 && child > 1) {
					nodes.add(v);
				}
				if (parent[v] != -1 && disc[v] <= low[adj]) {
					nodes.add(v);
				}
			} else {
				low[v] = Math.min(low[v], disc[adj]);
			}
		}
	}
}
