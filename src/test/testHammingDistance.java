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
 * Note: 0 �� x, y < 231.
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
 * _____��___��
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
	 * intת���ɶ������ַ���,ʹ��num����2����31λ��ʼ���������2^31�󣬾ͼ�1������ȥ2^31
	 * 
	 * @param num
	 *            ��Ҫת��������
	 * @return ������ַ���
	 */
	public static int hammingDistance(int x1, int y1) {
		/** intֵ�������⣬2��31�η������1 */
		long x = (long) x1;
		long y = (long) y1;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int distance = 0;
		int bit = 31;// λ����ʾ��ǰ32λ�ĵڼ�λ��Ҳ����2�ĵڶ��ٴη����bit=31����2^31
		while (bit >= 0) {
			/** bit<0��ʱ������ */
			int c1 = 0;
			int c2 = 0;
			long tempPow = (long) 1 << bit;
			// long tempPow = (long) Math.pow(2, bit);
			System.out.println("tempPow--" + tempPow);
			if (x >= tempPow) {
				/** �����ǰnum����2��bit�η������λ���Ϊ1 */
				c1 = 1;
				x = x - tempPow;
			} else {
				c1 = 0;
			}
			if (y >= tempPow) {
				/** �����ǰnum����2��bit�η������λ���Ϊ1 */
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
