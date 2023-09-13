package utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarCodeUtil {
    public static int[][] getBarCodePos(){
        int[][] num = new int[4][2];
        try {
            // 调用CMD命令
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "python E:\\Java\\LibraryManageSystem\\src\\main\\main.py 1.jpg"); // /c参数表示执行后关闭CMD窗口
            processBuilder.redirectErrorStream(true); // 将错误输出流与标准输出流合并
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK")); // 设置编码为GBK
            String line;
            // 定义正则表达式来匹配坐标信息的行
            Pattern pattern = Pattern.compile("(\\d+) (\\d+)");
            num = new int[4][2];
            int t = 0;
            while ((line = reader.readLine()) != null) {
                // 使用正则表达式匹配坐标信息的行
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // 提取坐标信息
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    num[t][0] = x;
                    num[t][1] = y;
                    t = t + 1;
                    // 在这里处理坐标信息，可以将它们存储到数据结构中或进行其他操作
                    System.out.println("X: " + x + ", Y: " + y);
                } else {
                    // 输出警告信息或其他提示信息
                    System.out.println(line);
                }
            }
            System.out.println(num);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return num;
    }
}
