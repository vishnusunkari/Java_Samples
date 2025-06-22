import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CronjobParser {
  
    private final String expression;
    private final String[] fields;
    private final Map<CronField, List<Integer>> parsedFields = new HashMap<>();

    public CronjobParser(String expression) {
        this.expression = expression;
        this.fields = expression.split(" ");
        if (fields.length != 5) {
            throw new IllegalArgumentException("Invalid cron expression");
        }
    }

    public void parse() {
        CronField[] cronFields = CronField.values();
        for (int i = 0; i < 5; i++) {
            parsedFields.put(cronFields[i], expandField(fields[i], cronFields[i]));
        }
    }

    public void printExpanded() {
        for (CronField field : CronField.values()) {
            System.out.printf("%-14s", field.name().toLowerCase().replace("_", " "));
            parsedFields.get(field).forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    private List<Integer> expandField(String token, CronField field) {
        int min = CronFieldRange.ranges.get(field)[0];
        int max = CronFieldRange.ranges.get(field)[1];
        List<Integer> result = new ArrayList<>();

        if (token.equals("*")) {
            for (int i = min; i <= max; i++) result.add(i);
        } else if (token.contains("/")) {
            String[] parts = token.split("/");
            int start = parts[0].equals("*") ? min : Integer.parseInt(parts[0]);
            int step = Integer.parseInt(parts[1]);
            for (int i = start; i <= max; i += step) result.add(i);
        } else if (token.contains(",")) {
            for (String part : token.split(",")) {
                result.addAll(expandField(part, field));
            }
        } else if (token.contains("-")) {
            String[] range = token.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            for (int i = start; i <= end; i++) result.add(i);
        } else {
            result.add(Integer.parseInt(token));
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        String cronExpr = "0/5 8,12 1 * 1-5";
        CronjobParser parser = new CronjobParser(cronExpr);
        parser.parse();
        parser.printExpanded();
    }
}

enum CronField {
    MINUTE, HOUR, DAY, MONTH, DAY_OF_WEEK;
}  

class CronFieldRange {
    static Map<CronField, int[]> ranges = Map.of(
        CronField.MINUTE, new int[]{0, 59},
        CronField.HOUR, new int[]{0, 23},
        CronField.DAY, new int[]{1, 31},
        CronField.MONTH, new int[]{1, 12},
        CronField.DAY_OF_WEEK, new int[]{0, 6}  // Sunday to Saturday
    );

    
}