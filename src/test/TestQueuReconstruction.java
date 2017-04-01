package test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Link:https://leetcode.com/problems/queue-reconstruction-by-height/
 * 
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note: The number of people is less than 1,100.
 * 
 * Example
 * 
 * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * 
 * @author Yocn
 * 
 * 
 *         ð�ݷ��в�ͨ����Ϊ����ȷ��λ�ã��տ�ʼ��������ʱ������кܶ���ܵ�ֵ���ʺ�λ����n�ĵ�ġ�����[7,1]��[6,1]���ʺϺܶ�λ��
 * 
 *         ���ð������˼��Ļ�����һ�������ʺ��ڵ�һ���ġ�ջ�׵�k������0
 * 
 *         ��һ��
 * 
 *         [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]] size==6
 * 
 *         1��[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 *         2��[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 *         3��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         4��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         5��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 * 
 *         �ڶ��飬���ʺ��ڵڶ���λ�õġ���ô�ң���h��h�������ջ�׵�h��k��0��������1��
 * 
 *         1��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         2��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         3��
 * 
 *         4��
 * 
 *         �����飬���ʺ��ڵ�����λ�õġ�node3��h���С��ǰ������k��2�����ֻС��1������k��1�����������ǰ����ֵ������0��
 * 
 *         1��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         2��[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         3��
 * 
 * 
 *         ��n�飬���ʺ��ڵ�n��λ�õġ�n��h���С��ǰn-1����k��n-1�����ֻС��n-2������k��n-2
 * 
 */

class Bean {
	private int[] array;
	private int position;

	public Bean() {
		super();
	}

	public Bean(int[] array, int position) {
		super();
		this.array = array;
		this.position = position;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "bean [array=" + Arrays.toString(array) + ", position=" + position + "]";
	}

}

public class TestQueuReconstruction {
	public static int[][] people = { { 5, 0 }, { 7, 0 }, { 4, 4 }, { 7, 1 }, { 6, 1 }, { 5, 2 } };

	public static int[][] tempPeople = { { 5, 0 }, { 7, 0 }, { 4, 4 }, { 7, 1 }, { 6, 1 }, { 5, 2 } };
	public static int[][] exceptPeople = { { 5, 0 }, { 7, 0 }, { 5, 2 }, { 6, 1 }, { 4, 4 }, { 7, 1 } };

	public static void main(String[] args) {
		// people = exceptPeople;
		// System.out.println(getFrontNum(5, 5));
		reconstructQueue(people);
	}

	public static int[][] reconstructQueue(int[][] people) {
		for (int i = 0; i < people.length; i++) {
			ArrayList<Bean> intList = new ArrayList();
			// HashMap<Integer, int[]> intMap = new HashMap<Integer, int[]>();
			int[] tempPeople = people[i];// �ݴ浱ǰλ��i������
			Println(people);
			label: for (int j = i; j < people.length; j++) {
				if (i == 0) {
					if (people[j][1] == 0) {
						// Print(people[j]);
						if (people[i][0] > people[j][0]) {
							int[] temp = people[i];
							people[i] = people[j];
							people[j] = temp;
						}
					}
				}
				// System.out.println("i---" + i + "---j---" + j);

				if (i > 0) {
					/** ��ȡ���ʺϵ��ڴ�λ�õ����飬����������ʺ������λ�õģ�һ��һ���ԣ������ķ������ĺ��涼Ӧ�ó��� */
					int current = people[j][1];
					int front = getFrontNum(j, i);
					System.out.println("i---" + i + "---j---" + j + "---[" + people[j][0] + "," + people[j][1] + "]"
							+ "---current---" + current + "---front---" + front);
					if (current == front) {
						/** ���������Ĵ�������һ��һ�����ԣ���������ط��Ƿ���� */
						intList.add(new Bean(people[j], j));
						// intMap.put(j, people[j]);
						System.out.println("����---------------------------------------��" + j + "��item��" + "size-----"
								+ intList.size());
						if (intList.size() == 1) {
							int[] temp = people[i];
							people[i] = people[j];
							people[j] = temp;
							/** ���ֻ��һ�����У���ֱ���滻 */
							break label;
						}
					}

					boolean expect = true;
					Bean bean = new Bean();
					for (int x = 0; x < intList.size(); x++) {
						int[] tempTarget = intList.get(x).getArray();
						/** ��x������Ϊ��ǰλ�õ����飬������������Ϊn+1�Ƿ���ʵ� */
						people[i] = tempTarget;

						for (int y = 0; y < intList.size(); y++) {
							/** ������������������n+1�Ƿ���� */
							if (i + 1 < people.length && x != y) {
								if (!isExpect(i + 1, tempTarget)) {
									expect = false;
								}
							}
						}

						if (expect) {
							bean = intList.get(x);
						}
					}

					people[i] = people[bean.getPosition()];
					people[bean.getPosition()] = tempPeople;

				}
			}
		}
		Print(people);
		return people;
	}

	/**
	 * ��ȡ��ǰ����[h, k]�����������У�hС��ǰ��λ��item��h�ĸ���
	 * 
	 * ��Ҫ�Ƚϵ�indexλ�õ�������targetIndexλ��ǰ��Ĵ��ڵ����Լ�h�ĸ���
	 * 
	 * @param index
	 *            ��ǰ��Ҫ�Ƚϵ�λ��
	 * @param targetIndex
	 *            ��Ҫ�ŵ���λ��
	 * @return ����
	 */
	private static int getFrontNum(int index, int targetIndex) {
		int x = 0;
		for (int i = 0; i < people.length && i < targetIndex; i++) {
			if (people[index][0] <= people[i][0]) {
				x++;
			}
		}
		return x;
	}

	/**
	 * 
	 * @param target
	 *            Ŀ������
	 * @param targetIndex
	 *            ��Ҫ�ŵ���λ��
	 * @return ����
	 */
	private static int getFrontNum(int[] target, int targetIndex) {
		int x = 0;
		for (int i = 0; i < people.length && i < targetIndex; i++) {
			if (target[0] <= people[i][0]) {
				x++;
			}
		}
		return x;
	}

	/**
	 * Ŀ��target�ڴ��Ƿ���Է��ڴ�λ��
	 * 
	 * @param index
	 *            ��ǰ��Ҫ�Ƚϵ�λ��
	 * @param targetIndex
	 *            ��Ҫ�ŵ���λ��
	 * @param target
	 *            Ŀ������
	 * @return �Ƿ��ʺ�
	 */
	private static boolean isExpect(int targetIndex, int[] target) {
		boolean isExpect = false;
		if (getFrontNum(target, targetIndex) <= target[1]) {
			isExpect = true;
		}
		return isExpect;
	}

	public static void Print(int[][] t) {
		System.out.print("\n");
		for (int[] i : t) {
			Print(i);
		}
		System.out.print("\n");
	}

	public static void Print(int[] t) {
		System.out.print(" [" + t[0] + "," + t[1] + "] ");
	}

	public static void Println(int[][] t) {
		for (int[] i : t) {
			Print(i);
		}
		System.out.println("\n");
	}

	public static void Println(int[] t) {
		System.out.println(" [" + t[0] + "," + t[1] + "] ");
	}
}
