import java.util.*;

public class CriticalRouters {

	private static int time = 1;

	public static void main(String[] args) {
		int numRouters1 = 7;
		int numLinks1 = 7;
		int[][] links1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 5, 6 }, { 3, 4 } };
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}

	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < numRouters; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		List<Integer> nodes = new ArrayList<>();
		int[] parent = new int[numRouters];
		int[] low = new int[numRouters];
		int[] disc = new int[numRouters];
		boolean[] visited = new boolean[numRouters];
		Arrays.fill(parent, -1);

		dfs(map, 0, parent, visited, disc, low, nodes);
		return nodes;
	}

	private static void dfs(Map<Integer, Set<Integer>> map, int v, int[] parent, boolean[] visited, int[] disc,
			int[] low, List<Integer> nodes) {
		visited[v] = true;
		disc[v] = low[v] = time++;
		int child = 0;
		for (Integer adj : map.get(v)) {
			if (parent[v] == adj) {
				continue;
			}

			if (!visited[adj]) {
				child++;
				parent[adj] = v;
				dfs(map, adj, parent, visited, disc, low, nodes);

				low[v] = Math.min(low[v], low[adj]);
				if (parent[v] == -1 && child > 1) {
					nodes.add(v);
				}
				// v is not root and low value of one of its child is more 
	            // than discovery value of v.
				if (parent[v] != -1 && disc[v] <= low[adj]) {
					nodes.add(v);
				}
			} else {
				low[v] = Math.min(low[v], disc[adj]);
			}
		}
	}
}
