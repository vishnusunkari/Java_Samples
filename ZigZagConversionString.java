import java.util.ArrayList;
import java.util.List;

public class ZigZagConversionString {
    
    public static String convert(String str, int numRows){
        int idx=0, d=1;

        List<Character> [] rows = new ArrayList[numRows];
        for(int i=0; i<numRows; i++){
            rows[i] = new ArrayList<>();
        }

        for(char c : str.toCharArray()){
            rows[idx].add(c);
            if(idx==0) {
                d = 1;
            } else if(idx == numRows-1){
                d = -1;
            }
            idx += d;

        }

        StringBuilder result = new StringBuilder();
        for(List<Character> row : rows) {
            for(char c : row){
                result.append(c);
            }
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        int rows = 3;
        System.out.println("Result : " + convert(str, rows));
    }
}