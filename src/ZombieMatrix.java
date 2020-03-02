import java.util.*;

public class ZombieMatrix {

	private final static int PERSON = 0;
	private final static int ZOMBIE = 1;
	private static int[] deltaX = { 1, -1, 0, 0 };
	private static int[] deltaY = { 0, 0, 1, -1 };

	static class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
		System.out.println(minDays(grid));
	}

	private static boolean inbound(int[][] grid, Coordinate coor) {
		if (coor.x < 0 || coor.x >= grid.length) {
			return false;
		}
		if (coor.y < 0 || coor.y >= grid[0].length) {
			return false;
		}
		return grid[coor.x][coor.y] == PERSON;
	}

	private static int minDays(int[][] grid) {

		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int personsCount = 0;
		Queue<Coordinate> queue = new LinkedList<>();
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == ZOMBIE) {
					queue.offer(new Coordinate(i, j));
				} else {
					personsCount++;
				}
			}
		if (personsCount == 0) {
			return 0;
		}
		int days = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			days++;
			for (int i = 0; i < size; i++) {
				Coordinate coor = queue.poll();
				for (int j = 0; j < 4; j++) {
					int x = coor.x + deltaX[j];
					int y = coor.y + deltaY[j];
					Coordinate adj = new Coordinate(x, y);
					if (!inbound(grid, adj)) {
						continue;
					}

					personsCount--;
					if (personsCount == 0) {
						return days;
					}
					queue.offer(adj);
					grid[x][y] = ZOMBIE;

				}
			}
		}

		return -1;
	}
}
