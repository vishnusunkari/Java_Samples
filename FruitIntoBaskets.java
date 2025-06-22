import java.util.HashMap;
import java.util.Map;

//Sliding Window Problem
public class FruitIntoBaskets { 
    public static int totalFruit(int[] fruits){
        int left=0, right=0, maxLen=Integer.MIN_VALUE;
        Map<Integer, Integer> indexMap = new HashMap();
        int n= fruits.length;

        while(right < n) {
            indexMap.put(fruits[right], indexMap.getOrDefault(fruits[right], 0)+1);
            while(indexMap.size()>2){
                indexMap.put(fruits[left], indexMap.get(fruits[left])-1);
                if(indexMap.get(fruits[left]) == 0){
                    indexMap.remove(fruits[left]);
                }
                left++;
            }
            if(indexMap.size() <= 2) {
                maxLen = Math.max(maxLen, right-left+1);
                right++;
            }
        }
        return maxLen;

    }
    
    public static void main(String[] args) {
        int [] fruits = {1, 2, 3, 2, 2 };
        int result = totalFruit(fruits);
        System.out.println("Total Fruit : " + result);
    }
}
