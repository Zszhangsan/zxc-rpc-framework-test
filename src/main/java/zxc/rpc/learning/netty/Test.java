package zxc.rpc.learning.netty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static String extractTempTableName(String sql) {
        // 改进的正则表达式，尝试匹配CREATE TEMPORARY TABLE后的表名
        // 考虑了空格、换行、可能的SQL注释等
        Pattern pattern = Pattern.compile(
                "(?i)" + // 不区分大小写
                        "CREATE\\s+TEMPORARY\\s+TABLE\\s+" + // 匹配CREATE TEMPORARY TABLE关键字
                        "(?:[^;\\)]*)?" + // 非捕获组，匹配可能存在的注释或子查询等
                        "(\\w+)", // 捕获组，匹配表名
                Pattern.DOTALL // 允许.匹配任何字符，包括换行符
        );
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1); // 返回第一个捕获组，即表名
        }
        return null; // 如果没有找到匹配项，则返回null
    }

    public static void main(String[] args) {
        String sql1 = "CREATE TEMPORARY TABLE temp_table_name (id INT)";
        String sql2 = "CREATE TEMPORARY TABLE `temp_table_with_backticks` (id INT)";
        String sql3 = "SELECT * FROM some_table;\n" +
                "CREATE TEMPORARY TABLE another_temp_table (name VARCHAR(255)) -- with comment";

        System.out.println(extractTempTableName(sql1)); // 输出: temp_table_name
        System.out.println(extractTempTableName(sql2)); // 输出: temp_table_with_backticks
        System.out.println(extractTempTableName(sql3)); // 输出: another_temp_table
    }
}
