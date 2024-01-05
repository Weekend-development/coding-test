class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        // t초 -> t * x 만큼 체력 회복
        // t초 연속 성공 -> +y
        // -> 최대체력일때는 안오름
        int max = health;
        int curTime = 0;
        for(int i=0; i<attacks.length; i++){
            int diff = attacks[i][0] - curTime - 1;
            curTime = attacks[i][0];
            
            // 회복
            health += diff * bandage[1];
            health += (diff / bandage[0])*bandage[2];
            if(max < health){
                health = max;
            }
            
            // 공격
            health -= attacks[i][1];
            if(health <= 0) return -1;
        }
        return health;
    }
}
