## Epsilon

```py
def solution(bandage, health, attacks):
    maxHealth = health
    rest = health
    healCast, healAmount, bonusHeal= bandage
    cnt = 0
    attackCycle = 0
    for i in range(attacks[-1][0]):
        # print('time:', i+1)
        if i == attacks[attackCycle][0]-1:
            cnt = 0
            rest -= attacks[attackCycle][1]
            attackCycle+=1
            if rest <= 0:
                return -1
            continue
        cnt+=1
        rest += healAmount
        if cnt == healCast:
            rest += bonusHeal
            cnt = 0
        rest = min(maxHealth, rest)
        # print('hp:', rest)
    return rest
```

## hojun
```java
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
```
