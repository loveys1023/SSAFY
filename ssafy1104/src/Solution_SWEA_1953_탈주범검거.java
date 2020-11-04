import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution_SWEA_1953_탈주범검거 {
    static int T, N, M, R, C, L;
    static int[][] map;
    static int[][] visited;
    static int cnt;
    static int[] dr = { -1, 0, 0, 1 }; // 상좌우하
    static int[] dc = { 0, -1, 1, 0 };
    static int[][] dd = { { 0 }, { 0, 1, 2, 3 }, { 0, 3 }, { 1, 2 }, { 0, 2 }, { 2, 3 }, { 1, 3 }, { 0, 1 } };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
 
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();
            map = new int[N][M];
            visited = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
 
            cnt = 0;
            bfs();
            counting();
            System.out.println("#" + t + " " + cnt);
        }
    }
 
    private static void counting() {
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] > 0 && visited[i][j] < L + 1) {
                    cnt++;
                }
            }
        }
    }
 
    private static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { R, C, map[R][C], 1 }); // r, c, d(뱡향), 시간
        visited[R][C] = 1; // 1 2 3 거리 자취
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int cr = cur[0];
            int cc = cur[1];
            int cd = cur[2]; // 현 방향
            int ct = cur[3]; // 현 시간
            if (ct == L)
                return;
 
            for (int d = 0; d < dd[cd].length; d++) {
                int dir = dd[cd][d]; // 다음 방향
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];
 
                if (!check(nr, nc))
                    continue;
                if (map[nr][nc] == 0)
                    continue;
                if (visited[nr][nc] != 0)
                    continue;
                // 현 방향(dir) + 다음 방향(p)의 파이브 연결가능?
                int p = map[nr][nc]; // 다음 방향
                if (dir == 0) { // 상
                    if (p == 3 || p == 4 || p == 7)
                        continue;
                } else if (dir == 1) { // 좌
                    if (p == 2 || p == 6 || p == 7)
                        continue;
                } else if (dir == 2) { // 우
                    if (p == 2 || p == 4 || p == 5)
                        continue;
                } else if (dir == 3) { // 하
                    if (p == 3 || p == 5 || p == 6)
                        continue;
                }
 
                visited[nr][nc] = visited[cr][cc] + 1;
                que.add(new int[] { nr, nc, map[nr][nc], ct + 1 });
 
            }
        }
 
    }
 
    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}