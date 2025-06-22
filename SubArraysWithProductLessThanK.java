public class SubArraysWithProductLessThanK {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        int left = 0, right = 0, product = 1, count = 0;

        while (right < nums.length) {
            product *= nums[right];
            while (product >= k) 
                product /= nums[left++];
            count += 1 + (right - left);
            right++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        int result = numSubarrayProductLessThanK(nums, k);
        System.out.println("Number of subarrays with product less than " + k + ": " + result);
    }
}
