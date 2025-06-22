import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosestEqualElementsQuery {
    public static int[] closestEqualElementsQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryIndex = queries[i];
            int num = nums[queryIndex];
            List<Integer> indices = indexMap.get(num);

            if (indices == null || indices.size() <= 1) {
                result[i] = -1;
                continue;
            }

            int minDistance = Integer.MAX_VALUE;
            for (int index : indices) {
                if (index != queryIndex) {  // skip where index != queryIndex to avoid calculate the distance between same element.
                    int distance = Math.abs(index - queryIndex); //first calculate distance of index from queryIndex
                    minDistance = Math.min(minDistance, Math.min(distance, n - distance));  //calculate min distrance, Since its a circular array, make sure you get min distance from other side.
                }
            }
            result[i] = minDistance;
        }
        return result;
    }
    public static void main(String[] args) {
        // Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
       int[] nums = {1, 3, 1, 4, 1, 3, 2};
       int[] queries = {0, 3, 5};  

        // int[] nums = {1, 2, 3, 4};
        // int[] queries = {0, 1, 2, 3};  

        int[] result = closestEqualElementsQueries(nums, queries);
        System.out.println("Result : " + Arrays.toString(result));
    }
        
}
