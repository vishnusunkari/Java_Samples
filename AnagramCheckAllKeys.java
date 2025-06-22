public class AnagramCheckAllKeys {

    //Time complexity O(n)
    //Space Complexity O(1)
    public static boolean isAnagram(String s, String t){
        if(s.length() != t.length()) return false;
        int [] count = new int [128];
        for(char c : s.toCharArray()){
            count[c]++;
        }
        for(char c : t.toCharArray()){
            count[c]--;
            if(count[c]<0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str1 = "adfasdkfjasd;lf@#$%@#$%@#$^%:;~)(*kjasdf";
        String str2 = "adfasdkfjasd;lf@#$%@#$%@#$^%:;~)(*kjasdf";
        boolean result = isAnagram(str1, str2);
        System.out.println("Given strings anagrams: " + result );
    }
}
