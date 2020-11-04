import java.util.Scanner;

public class BOJ_3055_탈출 {
	static int R, C;
	static char[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] c = sc.nextLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = c[j];
			}
		}
		
		bfs_water();
		bfs_hedgehog();
		
		
		
	}
}
