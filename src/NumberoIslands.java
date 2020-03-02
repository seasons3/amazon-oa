import java.util.*;

public class NumberoIslands {

	class Coordinate {
		int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private int[] deltaX = { 0, 0, 1, -1 };
	private int[] deltaY = { 1, -1, 0, 0 };

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					bfsHelper(grid, i, j);
				}
			}
		}
		return count;
	}

	private boolean inbound(char[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length) {
			return false;
		}
		if (y < 0 || y >= grid[0].length) {
			return false;
		}
		return true;
	}

	private void bfsHelper(char[][] grid, int i, int j) {
		Queue<Coordinate> queue = new LinkedList<>();
		grid[i][j] = '0';
		queue.offer(new Coordinate(i, j));
		while (!queue.isEmpty()) {
			Coordinate coor = queue.poll();
			for (int dict = 0; dict < 4; dict++) {
				int x = coor.x + deltaX[dict];
				int y = coor.y + deltaY[dict];
				if (!inbound(grid, x, y)) {
					continue;
				}
				if (grid[x][y] == '1') {
					queue.offer(new Coordinate(x, y));
					grid[x][y] = '0';
				}
			}
		}
	}
}
