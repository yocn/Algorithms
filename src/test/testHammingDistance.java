package test;

/**
 * 
 * Link:https://leetcode.com/problems/hamming-distance
 * 
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 * 
 * Given two integers x and y, calculate the Hamming distance.
 * 
 * Note: 0 ≤ x, y < 231.
 * 
 * Example:
 * 
 * Input: x = 1, y = 4
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 1 (0 0 0 1)
 * 
 * _____↑___↑
 * 
 * 4 (0 1 0 0)
 * 
 * The above arrows point to positions where the corresponding bits are
 * different.
 * 
 * @author Yocn
 * 
 */
public class testHammingDistance {
	public static void main(String[] args) {
		System.out.println("" + hammingDistance(0, 2147483647));
	}

	/**
	 * int转换成二进制字符串,使用num除以2，从31位开始除，如果比2^31大，就加1，并减去2^31
	 * 
	 * @param num
	 *            需要转换的数字
	 * @return 输出的字符串
	 */
	public static int hammingDistance(int x1, int y1) {
		/** int值会有问题，2的31次方会减少1 */
		long x = (long) x1;
		long y = (long) y1;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int distance = 0;
		int bit = 31;// 位，表示当前32位的第几位，也就是2的第多少次方如果bit=31，则2^31
		while (bit >= 0) {
			/** bit<0的时候会结束 */
			int c1 = 0;
			int c2 = 0;
			long tempPow = (long) 1 << bit;
			// long tempPow = (long) Math.pow(2, bit);
			System.out.println("tempPow--" + tempPow);
			if (x >= tempPow) {
				/** 如果当前num大于2的bit次方，则此位标记为1 */
				c1 = 1;
				x = x - tempPow;
			} else {
				c1 = 0;
			}
			if (y >= tempPow) {
				/** 如果当前num大于2的bit次方，则此位标记为1 */
				c2 = 1;
				y = y - tempPow;
			} else {
				c2 = 0;
			}
			sb.append(c1);
			sb2.append(c2);

			if (c1 != c2) {
				distance++;
			}
			bit--;
		}
		System.out.println(sb.toString());
		System.out.println(sb2.toString());
		return distance;
	}

}
