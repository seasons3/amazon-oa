package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The way to do that is to find out the Minimum Spanning Tree(MST) of the map
 * of the cities.
 * 
 * Find small cost edge first, if two cities connected by the edge do no have
 * same ancestor, then union them.
 * 
 * When number of unions equal to 1, all cities are connected.
 * 
 * Sorting the edges: O(m log m). For each edge (roads available and new roads
 * to construct), calling union-find: O(m * alpha(n)), or basically just O(m)
 * with compression and union by weight, amatorize O(1). where m is
 * numNewRoadsContruct
 * 
 * Space: O(N)
 * 
 * @author leen
 *
 */
public class MinCostConnectNodes {
	static class UnionFind {
		int[] parent;
		int[] size;
		int component;

		public UnionFind(int size) {
			this.component = size;
			this.parent = new int[size + 1];
			this.size = new int[size + 1];
			for (int i = 0; i <= size; i++) {
				this.parent[i] = i;
				this.size[i] = 1;
			}
		}

		public boolean isConnected(int x, int y) {
			return find(x) == find(y);
		}

		// 1, 2, 3
		// 2 3 3
		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];

		}

		public void union(int x, int y) {
			int rootx = find(x);
			int rooty = find(y);
			if (size[rootx] > size[rooty]) {
				parent[rooty] = rootx;
				size[rootx] += size[rooty];
			} else {
				parent[rootx] = rooty;
				size[rooty] = size[rootx];
			}

			this.component--;
		}

	}

	public static int getMinimumCostConstruct(int numTotalAvailableCities, int numTotalAvaiableRoads,
			List<List<Integer>> roadsAvailable, int numNewRoadsContruct, List<List<Integer>> costNewRoadsConstruct) {
		if (numTotalAvailableCities < 2 || numTotalAvaiableRoads >= numTotalAvailableCities - 1) {
			return 0;
		}

		UnionFind uf = new UnionFind(numTotalAvailableCities);
		for (List<Integer> road : roadsAvailable) {
			int city1 = road.get(0);
			int city2 = road.get(1);
			uf.union(city1, city2);
		}
		int cost = 0;
		Queue<List<Integer>> pq = new PriorityQueue<>(numNewRoadsContruct, (l1, l2) -> {
			return l1.get(2) - l2.get(2);
		});
		pq.addAll(costNewRoadsConstruct);
		while (!pq.isEmpty()) {
			List<Integer> newRoad = pq.poll();
			if (!uf.isConnected(newRoad.get(0), newRoad.get(1))) {
				uf.union(newRoad.get(0), newRoad.get(1));
				cost += newRoad.get(2);
				if (uf.component == 1) {
					return cost;
				}
			}
		}
		return -1;

	}

	public static void main(String[] args) {
		int[][] roadsAvailable = { { 1, 4 }, { 4, 5 }, { 2, 3 } };
		int[][] costNewRoadsConstruct = { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 }, { 5, 6, 5 } };
		List<List<Integer>> roadsAvailableList = new ArrayList<>();
		for (int[] ints : roadsAvailable) {
			List<Integer> list = new ArrayList<>();
			for (int i : ints) {
				list.add(i);
			}
			roadsAvailableList.add(list);
		}
		List<List<Integer>> costNewRoadsConstructList = new ArrayList<>();
		for (int[] ints : costNewRoadsConstruct) {
			List<Integer> list = new ArrayList<>();
			for (int i : ints) {
				list.add(i);
			}
			costNewRoadsConstructList.add(list);
		}

		System.out.println(getMinimumCostConstruct(6, 3, roadsAvailableList, 4, costNewRoadsConstructList));
	}
}
