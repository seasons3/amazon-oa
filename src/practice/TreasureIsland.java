package practice;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS solution: traverse the grid using BFS and keep track of the level
 * 
 * Time: O(r *c) Space: O(min(r,c)) because in worst case where the grid is filled
 * with lands, the size of queue can grow up to min(M,N).
 * 
 * @author leen
 *
 */
public class TreasureIsland {
	static class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] deltaX = { 0, 0, 1, -1 };
	static int[] deltaY = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		char[][] grid = { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };
		System.out.println(minSteps(grid));
	}

	private static boolean inbound(char[][] island, Coordinate coor) {
		if (coor.x < 0 || coor.x >= island.length) {
			return false;
		}
		if (coor.y < 0 || coor.y >= island[0].length) {
			return false;
		}
		return true;
	}

	public static int minSteps(char[][] island) {

		if (island == null || island.length == 0 || island[0].length == 0 || island[0][0] == 'D') {
			return 0;
		}
		if(island[0][0] == 'X') {
			return 0;
		}
		/// Traverse the grid using BFS and keep track of the level
		Queue<Coordinate> queue = new LinkedList<>();
		queue.offer(new Coordinate(0, 0));
		island[0][0] = 'D'; // mark as visited
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

					if (!inbound(island, adj)) {
						continue;
					}
					if (island[x][y] == 'D') {
						continue;
					}
					System.out.println(island[x][y]);

					if (island[x][y] == 'X') {
						return steps;
					}
					island[x][y] = 'D';
					queue.offer(adj);
				}
			}
		}
		return -1;
	}
}
