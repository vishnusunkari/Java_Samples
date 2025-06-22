import java.util.HashMap;
import java.util.Map;

public class SubArrayWithMedianKCount {
    public static int subArrayWithMedianKCount(int[] nums, int k) {
        int medianIndex = -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int balance = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == k) {
                medianIndex =i;  
                break;
            }            
        }   
        for(int i=medianIndex+1; i<nums.length; i++) {
            if(nums[i] > k) {
                balance++;
            } else if(nums[i] < k) {
                balance--;
            } 
            map.put(balance, map.getOrDefault(balance, 0) + 1);
        }
        balance = 0;
        for(int i=medianIndex; i>=0; i--) {
            if(nums[i] > k) {
                balance++;
            } else if(nums[i] < k) {
                balance--;
            }
            //use the difference of prefix sums to 
            count += map.getOrDefault(-balance, 0) + map.getOrDefault(1-balance, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {7,1, 2, 4, 3, 6, 5, 8};
        int k = 4;
        System.out.println(subArrayWithMedianKCount(nums, k));
    }
}
