import java.util.*;

public class CriticalConnections {
	private static int time = 0;

	public static void main(String[] args) {
		int[][] links1 = { { 1, 2 }, { 1, 3 }, { 3, 2 }, { 3, 4 } };
		System.out.println(findCriticalConn(4, 4, links1));
	}

	public static List<List<Integer>> findCriticalConn(int serversNum, int connectionsNum, int[][] connections) {
		List<List<Integer>> result = new ArrayList<>();
		if (serversNum <= 1) {
			return result;
		}
		Map<Integer, Set<Integer>> map = new HashMap<>();
		int[] low = new int[serversNum + 1];
		int[] disc = new int[serversNum + 1];
		int[] parent = new int[serversNum + 1];
		Arrays.fill(parent, -1);
		boolean[] visited = new boolean[serversNum + 1];
		for (int i = 1; i <= serversNum; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] connection : connections) {
			if (connection.length != 2) {
				return result;
			}
			map.get(connection[0]).add(connection[1]);
			map.get(connection[1]).add(connection[0]);

		}
		for (int i = 1; i <= serversNum; i++) {
			if (!visited[i]) {
				dfs(map, 1, parent, visited, disc, low, result);
			}
		}

		return result;

	}

	private static void dfs(Map<Integer, Set<Integer>> map, int v, int[] parent, boolean[] visited, int[] disc,
			int[] low, List<List<Integer>> result) {
		visited[v] = true;
		low[v] = disc[v] = time++;
		for (Integer adj : map.get(v)) {
			if (parent[v] == adj) {
				continue;
			}
			if (!visited[adj]) {

				parent[adj] = v;
				dfs(map, adj, parent, visited, disc, low, result);
				low[v] = Math.min(low[v], low[adj]);

				if (disc[v] < low[adj]) {
					List<Integer> temp = new ArrayList<>();
					temp.add(v);
					temp.add(adj);
					result.add(temp);
				}
			} else {

				low[v] = Math.min(low[v], disc[adj]);

			}

		}

	}
}