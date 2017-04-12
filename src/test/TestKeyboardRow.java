package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author Yocn
 *
 */
public class TestKeyboardRow {
    private static HashSet<Character> rowSet1 = new HashSet<Character>();
    private static HashSet<Character> rowSet2 = new HashSet<Character>();
    private static HashSet<Character> rowSet3 = new HashSet<Character>();
    private static String[] source = { "Hello", "Alaska", "Dad", "Peace", "sadaASDAsdasd" };

    public static void main(String[] args) {
        for (String s : findWords(source)) {
            System.out.println(s);
        }
    }

    public static void initRow() {
        rowSet1.add('q');
        rowSet1.add('w');
        rowSet1.add('e');
        rowSet1.add('r');
        rowSet1.add('t');
        rowSet1.add('y');
        rowSet1.add('u');
        rowSet1.add('i');
        rowSet1.add('o');
        rowSet1.add('p');
        rowSet1.add('Q');
        rowSet1.add('W');
        rowSet1.add('E');
        rowSet1.add('R');
        rowSet1.add('T');
        rowSet1.add('Y');
        rowSet1.add('U');
        rowSet1.add('I');
        rowSet1.add('O');
        rowSet1.add('P');

        rowSet2.add('a');
        rowSet2.add('s');
        rowSet2.add('d');
        rowSet2.add('f');
        rowSet2.add('g');
        rowSet2.add('h');
        rowSet2.add('j');
        rowSet2.add('k');
        rowSet2.add('l');
        rowSet2.add('A');
        rowSet2.add('S');
        rowSet2.add('D');
        rowSet2.add('F');
        rowSet2.add('G');
        rowSet2.add('H');
        rowSet2.add('J');
        rowSet2.add('K');
        rowSet2.add('L');

        rowSet3.add('z');
        rowSet3.add('x');
        rowSet3.add('c');
        rowSet3.add('v');
        rowSet3.add('b');
        rowSet3.add('n');
        rowSet3.add('m');
        rowSet3.add('Z');
        rowSet3.add('X');
        rowSet3.add('C');
        rowSet3.add('V');
        rowSet3.add('B');
        rowSet3.add('N');
        rowSet3.add('M');
    }

    public static String[] findWords(String[] words) {
        initRow();
        List<String> list = new ArrayList<String>();
        for (String s : words) {
            if (!"".equals(isOneRow(s))) {
                list.add(s);
            }
        }
        int size = list.size();
        return (String[]) list.toArray(new String[size]);
    }

    private static String isOneRow(String source) {
        char index0 = source.charAt(0);
        HashSet<Character> tempSet = null;
        if (rowSet1.contains(index0)) {
            tempSet = rowSet1;
        } else if (rowSet2.contains(index0)) {
            tempSet = rowSet2;
        } else if (rowSet3.contains(index0)) {
            tempSet = rowSet3;
        }
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (!tempSet.contains(c)) {
                return "";
            }
        }
        return source;
    }

}
