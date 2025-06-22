public class MinAddOrSubtractPowerOfTwoToReduceToZero {
    public static int minAddOrSubtractPowerOfTwoToReduceToZero(int n) {
        return Integer.bitCount(n ^ (n*3));
    }   

    /*
     * n & 3 means: check the last 2 bits of n in binary. 3 in binary is 11
     * So this condition checks: Are the last two bits of n both 1?
     * Example: if n = 7 (binary 111), last 2 bits are 11 → true
     * Why do we care? If the last two bits are 11, then subtracting a power of 2 is not ideal.
     * Instead, we "carry over" (like in addition) by doing n++.
     * This will convert something like ...11 into ...00 in a higher position (e.g. 0111 + 1 = 1000)
     * That makes future steps simpler.
     * If the last two bits are not both 1: n & 1 checks if the last bit is 1
     * If yes, we subtract 1 (a power of 2) → counts as one operation
     * If last bit is 0, we do nothing, just move on
     * Then we divide n by 2 (n >>= 1 means right shift, which is same as n = n / 2)
     * This is like peeling off the rightmost bit and moving to the next
     */
    public static int minOperations(int n) {
        int result = 0;
        //Keep reducing n until it becomes 0.
        while (n > 0) {
            if ((n & 3) == 3) {
                n++;  //add 1 which is adding a carry
                result++; // 1 operation
            } else {
                result += n & 1; // subtracting 1 (a power of 2) , 1 operation
                n >>= 1; // n/2 peeling off the right most bit and moving to the next.
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 39;
        System.out.println(minAddOrSubtractPowerOfTwoToReduceToZero(n));
    }
}
