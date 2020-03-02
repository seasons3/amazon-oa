package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1)Apply DFS on a graph. Get the DFS tree. 2)A node which is visited earlier
 * is a "parent" of those nodes which are reached by it and visited later. 3)Any
 * child of a node does not have a path to any of the ancestors of its parent,
 * it means that removing this node would make this child disjoint from the
 * graph. 4)There is an exception: the root of the tree. If it has more than one
 * child, then it is an articulation point, otherwise not. Solution: Tarjan Time
 * complexity: O(v+e) Space complexity: O(v+e)
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
