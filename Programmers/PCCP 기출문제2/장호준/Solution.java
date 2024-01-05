import java.util.*;

class Solution {
    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int frag;
    static Map<Integer, Integer> map;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        frag = 1;
        map = new HashMap<>();
        visited = new boolean[N][M];
        
        // bfs를 통해서 해당영역을 번호 표시 및 넓이 구하기
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[j][i] && land[j][i] != 0){
                    bfs(j,i,land);
                }
            }
        }
        // 해당 줄에 해당하는 지역 확인(set으로 중복지역 제거)
        int answer = 0;
        for(int i=0; i<M; i++){
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for(int j=0; j<N; j++){
                if(land[j][i] > 0){
                    set.add(land[j][i]);
                }
            }
            // 해당 지역번호에 해당하는 영역 더하기 
            for(Integer val : set){
                sum += map.get(val);
            }
            // 그 중 영역이 제일 큰 부분 체크
            answer = Math.max(answer,sum);
        }
        return answer;
    }
    static void bfs(int x, int y, int[][] land){
        Queue<Oil> qu = new LinkedList<>();
        visited[x][y] = true;
        qu.add(new Oil(x,y));
        land[x][y] = frag;
        int cnt=1;
        while(!qu.isEmpty()){
            Oil oil = qu.poll();
            
            for(int i=0; i<4; i++){
                int px = oil.x + dx[i];
                int py = oil.y + dy[i];
                if(0 <= px && px < N && 0 <= py && py < M
                   && !visited[px][py] && land[px][py] != 0){
                        visited[px][py] = true;
                        land[px][py] = frag;
                        qu.add(new Oil(px,py));
                        cnt++;
                }
            }
        }
        // 해당지역에 넓이 저장후 지역번호 증가
        map.put(frag++, cnt);
    }
    static class Oil{ // Oil 객체
        int x,y,cnt;
        public Oil(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
