package practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The idea is to user Breadth First Search.
 * 
 * we can conquer this in two steps
 * 1)  Find all coordinates of the zombies, meanwhile count the number of people too.
 * 2)  BFS in level order, how many level is how many days it will take to convert all people to zombies.
 * if the people count is 0 that means all is converted, otherwise return -1
 *
 * 
 * Space Complexity: O(r * c ).
 * 
 * Time complexity: O(r * c).
 * 
 * @author leen
 *
 */
public class ZombieInMatrix {
	class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	int[] deltaX = { 0, 0, 1, -1 };
	int[] deltaY = { 1, -1, 0, 0 };

	private boolean inbound(List<List<Integer>> grid, Coordinate coor) {
		if (coor.x < 0 || coor.x >= grid.size()) {
			return false;
		}
		if (coor.x < 0 || coor.y >= grid.get(0).size()) {
			return false;
		}
		return grid.get(coor.x).get(coor.y) == 0;
	}

	int minHours(int rows, int columns, List<List<Integer>> grid) {
		// todo
		if (rows == 0 || columns == 0) {
			return 0;
		}
		Queue<Coordinate> queue = new LinkedList<>();
		int needsUpdated = 0;
		// put the position of all the updated servers
		// count number of outdate servers;
		for (int i = 0; i < grid.size(); i++)
			for (int j = 0; j < grid.get(i).size(); j++) {
				if (grid.get(i).get(j) == 1) {

					queue.offer(new Coordinate(i, j));

				} else {
					needsUpdated++;
				}
			}
		// if count of out of date severs is zero --> return 0
		if (needsUpdated == 0) {
			return 0;
		}
		int days = 0;
		// bfs starting from initially updated servers
		while (!queue.isEmpty()) {
			int size = queue.size();
			days++;
			for (int i = 0; i < size; i++) {
				Coordinate coor = queue.poll();
				for (int dict = 0; dict < 4; dict++) {
					int x = coor.x + deltaX[dict];
					int y = coor.y + deltaY[dict];
					Coordinate adj = new Coordinate(x, y);
					// if x or y is out of bound
					// or server at (x , y) is aleady updated do nothing.
					if (!inbound(grid, adj)) {
						continue;
					}

					needsUpdated--;
					if (needsUpdated == 0) {
						return days;
					}
					queue.offer(adj);
					// mark the server at (x , y) as the updated server
					grid.get(x).set(y, 1);

				}
			}
		}
		return -1;
	}
}
