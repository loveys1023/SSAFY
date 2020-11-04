import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class SWEA_1953_탈주범검거 {
    static int T;
    static int N;   // 지하터널 세로
    static int M;   // 지하터널 가로
    static int R;   // 맨홀 세로
    static int C;   // 맨홀 가로
    static int L;   // 탈출 후 소요된 시간
    static int[][] map;
    static boolean[][] visited;
    static int answer;
     
    static int[] dr = {-1, 1, 0, 0};    // 상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int[][] manhole = {{},
                                {0,1,2,3},
                                {0,1},
                                {2,3},
                                {0,3},
                                {1,3},
                                {1,2},
                                {0,2}};
     
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
            visited = new boolean[N][M];
            answer = 0;
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
 
            answer = bfs(R, C);
            System.out.println("#" + t + " " + answer);
        }
    }
     
    static boolean[][] go = {{false, true,true, false, true, false, false, true},   // 상
                            {false, true, true, false, false, true, true, false},   // 하
                            {false, true, false, true, false, false, true, true},   // 좌
                            {false, true, false, true, true, true, false, false}    // 우
    };
     
    static boolean[][] come = {go[1], go[0], go[3], go[2]};
 
    private static int bfs(int r, int c) {
        int time = 0;
        int size = 0;
        Queue<int[]> que = new LinkedList<>();
        visited[r][c] = true;
        que.offer(new int[] {r, c});
         
        while(!que.isEmpty()) {
            if(time == L) {
                return size;
            }
             
            int que_size = que.size();
            size += que.size();
             
            for (int i = 0; i < que_size; i++) {
                int[] cur = que.poll();
                int cr = cur[0];    // 현재 위치
                int cc = cur[1];
                 
                int[] dir = manhole[map[cr][cc]];   // 현재 위치에서 갈 수 있는 방향
                for (int d = 0; d < dir.length; d++) {   // 내가 갈 수 있는 방향 확인
                    int nr = cr + dr[dir[d]];   // 다음 위치
                    int nc = cc + dc[dir[d]];
                     
                    if(!check(nr, nc)) continue;
                    if(visited[nr][nc]) continue;
                    if(map[nr][nc] == 0) continue;
                    if(!come[dir[d]][map[nr][nc]]) continue;
                     
                    visited[nr][nc] = true;
                    que.offer(new int[] { nr, nc });
//                  size++;
                }
            }
            time++;
        }
         
        return size;
    }
 
    private static boolean check(int r, int c) {
        if(r>=0 && r<N && c>=0 && c<M) {
            return true;
        } else {
            return false;
        }
    }
}