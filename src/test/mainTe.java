package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainTe {

    public static void main(String[] args) {
        String str = "+00 +000000219 +000000219 +000000";
        String regex = "\\+\\d{9}";
        String regex1 = "^\\+\\d{9}$";
        // |^\d{9}$ ^\d{9}$ ^[0-9]*$
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String s = matcher.group(0);
            String temp = matcher.group(0).substring(1, s.length());
            int i = Integer.parseInt(temp);
            float f = (float) i / 1000;
            System.out.println(f);
        }
    }

    public float exeData(String source) {
        String regex = "\\+\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            String s = matcher.group(0);
            String temp = matcher.group(0).substring(1, s.length());
            int i = Integer.parseInt(temp);
            float f = (float) i / 1000;
            System.out.println(f);
            return f;
        }
        return 0;
    }

}
