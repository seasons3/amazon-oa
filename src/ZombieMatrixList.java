import java.util.*;

public class ZombieMatrixList {

	static class Coordinate {
		int x;
		int y;

		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int PERSON = 0;
	private static final int ZOMBIE = 1;
	private static int[] deltaX = { 0, 0, 1, -1 };
	private static int[] deltaY = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Integer[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };

		List<List<Integer>> lists = new ArrayList<>();
		for (Integer[] ints : grid) {
			lists.add(Arrays.asList(ints));
		}

		System.out.println(minHours(4, 4, lists));
	}

	private static boolean inbound(List<List<Integer>> grid, int rows, int columns, Coordinate coor) {
		if (coor.x < 0 || coor.x >= rows) {
			return false;
		}
		if (coor.y < 0 || coor.y >= columns) {
			return false;
		}
		return grid.get(coor.x).get(coor.y) == PERSON;

	}

	public static int minHours(int rows, int columns, List<List<Integer>> grid) {
		// todo
		if (grid == null || rows == 0 || columns == 0) {
			return 0;
		}
		int personCount = 0;
		Queue<Coordinate> queue = new LinkedList<>();
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				if (grid.get(i).get(j) == PERSON) {
					personCount++;
				} else {
					queue.offer(new Coordinate(i, j));
				}
			}
		if (personCount == 0) {
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
					if (!inbound(grid, rows, columns, adj)) {
						continue;
					}
					personCount--;
					if (personCount == 0) {
						return days;
					}
					queue.offer(adj);
					grid.get(x).set(y, ZOMBIE);

				}
			}
		}

		return -1;
	}
}
