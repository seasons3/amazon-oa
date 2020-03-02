import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland2 {

	static class Coordinate {
		int x;
		int y;

		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int[] deltaX = { 0, 0, -1, 1 };
	private static final int[] deltaY = { 1, -1, 0, 0 };

	private static boolean inbound(char[][] islands, Coordinate coor) {
		if (coor.x < 0 || coor.x >= islands.length) {
			return false;
		}
		if (coor.y < 0 || coor.y >= islands[0].length) {
			return false;
		}
		return islands[coor.x][coor.y] != 'D';
	}

	public static int shortestPath(char[][] islands) {
		if (islands == null || islands.length == 0 || islands[0].length == 0) {
			return -1;
		}
		Queue<Coordinate> queue = new LinkedList<>();
		for (int i = 0; i < islands.length; i++)
			for (int j = 0; j < islands[0].length; j++) {
				if (islands[i][j] == 'S') {
					queue.offer(new Coordinate(i, j));
					islands[i][j] = 'D';
				}
			}
		int steps = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			steps++;
			for (int i = 0; i < size; i++) {
				Coordinate coor = queue.poll();
				for (int dict = 0; dict < 4; dict++) {
					int x = coor.x + deltaX[dict];
					int y = coor.y + deltaY[dict];
					Coordinate adj = new Coordinate(x, y);
					if (!inbound(islands, adj)) {
						continue;
					}
					if (islands[x][y] == 'X') {
						return steps;
					}
					islands[x][y] = 'D';
					queue.offer(adj);

				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		char[][] testcase = { { 'S', 'O', 'O', 'S', 'S' }, { 'D', 'O', 'D', 'O', 'D' }, { 'O', 'O', 'O', 'O', 'X' },
				{ 'X', 'D', 'D', 'O', 'O' }, { 'X', 'D', 'D', 'D', 'O' } };
		System.out.println(shortestPath(testcase));
		testcase = new char[][] { { 'S', 'O', 'O', 'S', 'S' }, { 'D', 'O', 'D', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'X' },
				{ 'X', 'D', 'D', 'O', 'O' }, { 'X', 'D', 'D', 'D', 'O' } };
		System.out.println(shortestPath(testcase));
	}
}
