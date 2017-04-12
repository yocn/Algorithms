package test;

/**
 * 
 * url: https://leetcode.com/problems/number-complement/#/description
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer¡¯s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * @author Yocn
 *
 */
public class TestNumComplement {

    public static void main(String[] args) {

        System.out.println(Integer.highestOneBit(5));
        System.out.println(findComplement(10));
    }

    public static int findComplement(int target) {
        int length = Integer.toBinaryString(target).length();
        String bin = Integer.toBinaryString(~target).substring(32 - length, 32);
        System.out.println("Integer.toBinaryString(target)---" + Integer.toBinaryString(target));
        System.out.println("length---" + length);
        System.out.println("bin---" + bin);
        return Integer.parseInt(bin, 2);
    }

    public static int findComplement2(int target) {
        return Integer.parseInt(
                Integer.toBinaryString(~target).substring(32 - Integer.toBinaryString(target).length(), 32), 2);
    }

}
