package practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The idea is to user Breadth First Search.
 * Start the BFS from the node with value 1 and try all four directions to find 
 * any connected 1’s. Once BFS is completed, check if there is an unvisited node
 * present in the grid (node with value 1), if yes then start another BFS from that node. 
 * Keep counting no of BFS’s, this will be our answer- Number of Islands.
 * 
 * Time complexity : O(M×N) where M is the number of rows and N is the number of
 * columns.
 * 
 * Space complexity : O(min(M,N)) because in worst case where the grid is filled
 * with lands, the size of queue can grow up to min(M,N).
 * 
 * @author leen
 *
 */
public class NumberOfIslands {

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
		return grid[x][y] == '1';
	}

	private void bfsHelper(char[][] grid, int i, int j) {
		grid[i][j] = '0';
		Queue<Coordinate> queue = new LinkedList<>();
		queue.offer(new Coordinate(i, j));
		while (!queue.isEmpty()) {
			Coordinate coor = queue.poll();
			for (int dict = 0; dict < 4; dict++) {
				int x = coor.x + deltaX[dict];
				int y = coor.y + deltaY[dict];
				if (!inbound(grid, x, y)) {
					continue;
				}
				queue.offer(new Coordinate(x, y));
				grid[x][y] = '0';
			}
		}
	}

}
