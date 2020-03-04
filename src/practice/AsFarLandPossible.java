package practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * We start from all the lands and start exploring the water layer by layer
 * until all the water are explored.
 * 
 * @author leen
 *
 */
public class AsFarLandPossible {
	class Coordinate {
		int x;
		int y;

		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	int[] deltaX = { 0, 0, -1, 1 };
	int[] deltaY = { 1, -1, 0, 0 };

	private boolean inbound(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length) {
			return false;
		}
		if (y < 0 || y >= grid[0].length) {
			return false;
		}
		return grid[x][y] == 0;
	}

	public int maxDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		Queue<Coordinate> queue = new LinkedList<>();
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					queue.offer(new Coordinate(i, j));
				}
			}
		int steps = 0;
		while (!queue.isEmpty()) {
			steps++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Coordinate coor = queue.poll();
				for (int dict = 0; dict < 4; dict++) {
					int x = coor.x + deltaX[dict];
					int y = coor.y + deltaY[dict];
					if (!inbound(grid, x, y)) {
						continue;
					}
					grid[x][y] = 1;
					queue.offer(new Coordinate(x, y));

				}
			}
		}
		return steps == 1 ? -1 : steps - 1;

	}
}
