
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_BOJ_3055_탈출 {
	static class Animal {
		int r;
		int c;
		int distance;

		public Animal() {
			super();
		}

		public Animal(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}

	static int R, C;
	static char[][] map;
	static int[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static Queue<Animal> beabers = new LinkedList<>();
	static Queue<Animal> waters = new LinkedList<>();
	static int Dr, Dc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = cs[j];
				if (map[i][j] == 'D') {
					Dr = i;
					Dc = j;
				} else if (map[i][j] == 'S') {
					beabers.offer(new Animal(i, j, 0));
				} else if (map[i][j] == '*') {
					waters.offer(new Animal(i, j, 0));
				}
			}
		}

		while (true) {
			if (beabers.size() == 0) {
				System.out.println("KAKTUS");
				break;
			}
			dfsWater(); // 물의 확장
			int count = dfsGosum(); // 고슴도치 이동
			if (count > 0) {
				System.out.println(count);
				break;
			}
		}
	}

	private static int dfsGosum() {
		int size = beabers.size();
		while (size-- > 0) {
			Animal gosum = beabers.poll();
			int cr = gosum.r;
			int cc = gosum.c;
			int depth = gosum.distance;
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (!check(nr, nc))
					continue;
				if (map[nr][nc] == 'X')
					continue;
				if (nr == Dr && nc == Dc) {
					return depth + 1;
				}
				if (map[nr][nc] == '.') {
					map[nr][nc] = 'S';
//					map[cr][cc] = '.';
					beabers.offer(new Animal(nr, nc, depth + 1));
				}
			}

		}
		return -1;
	}

	private static void dfsWater() {

		int size = waters.size();
		while (size-- > 0) {
			Animal water = waters.poll();
			int cr = water.r;
			int cc = water.c;
			int depth = water.distance;
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (!check(nr, nc))
					continue;
				if (map[nr][nc] == 'X')
					continue;
				if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
					map[nr][nc] = '*';
					waters.offer(new Animal(nr, nc, depth + 1));
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
