import java.util.*;

public class ZombieMatrix {

	static int[] deltaX = { 0, 0, 1, -1 };
	static int[] deltaY = { 1, -1, 0, 0 };

	static class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static boolean inbound(int[][] matrix, int x, int y) {
		if (x < 0 || x >= matrix.length) {
			return false;
		}
		if (y < 0 || y >= matrix[0].length) {
			return false;
		}
		return matrix[x][y] == 0;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		System.out.println(minDays(grid));
	}

	private static int minDays(int[][] matrix) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		Queue<Coordinate> queue = new LinkedList<>();
		int personCount = 0;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					queue.offer(new Coordinate(i, j));
				} else {
					personCount++;
				}
			}
		if (personCount == 0) {
			return 0;
		}
		System.out.println(personCount);
		int days = 0;
		while (!queue.isEmpty()) {
			days++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Coordinate coor = queue.poll();
				for (int dict = 0; dict < 4; dict++) {
					int x = coor.x + deltaX[dict];
					int y = coor.y + deltaY[dict];
					if (!inbound(matrix, x, y)) {
						continue;
					}
					personCount--;
					if (personCount == 0) {
						return days;
					}
					matrix[x][y] = 1;
					queue.offer(new Coordinate(x, y));
				}

			}
		}
		return -1;

	}
}
