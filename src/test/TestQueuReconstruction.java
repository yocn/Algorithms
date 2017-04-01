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
 *         冒泡法行不通，因为不能确定位置，刚开始向后遍历的时候可能有很多可能的值是适合位置是n的点的。比如[7,1]和[6,1]就适合很多位置
 * 
 *         如果冒泡排序思想的话，第一遍找最适合在第一个的。栈首的k必须是0
 * 
 *         第一遍
 * 
 *         [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]] size==6
 * 
 *         1、[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 *         2、[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 *         3、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         4、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         5、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 * 
 *         第二遍，找适合在第二个位置的。怎么找，看h，h如果大于栈首的h则k是0，否则是1。
 * 
 *         1、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         2、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         3、
 * 
 *         4、
 * 
 *         第三遍，找适合在第三个位置的。node3的h如果小于前两个则k是2，如果只小于1个，则k是1，如果都大于前两个值，则是0。
 * 
 *         1、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         2、[[5,0], [7,0], [4,4], [7,1], [6,1], [5,2]]
 * 
 *         3、
 * 
 * 
 *         第n遍，找适合在第n个位置的。n的h如果小于前n-1个则k是n-1，如果只小于n-2个，则k是n-2
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
			int[] tempPeople = people[i];// 暂存当前位置i的数组
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
					/** 获取最适合的在此位置的数组，如果有三个适合在这个位置的，一个一个试，其他的放在它的后面都应该成立 */
					int current = people[j][1];
					int front = getFrontNum(j, i);
					System.out.println("i---" + i + "---j---" + j + "---[" + people[j][0] + "," + people[j][1] + "]"
							+ "---current---" + current + "---front---" + front);
					if (current == front) {
						/** 符合条件的存起来，一个一个的试，放在这个地方是否合适 */
						intList.add(new Bean(people[j], j));
						// intMap.put(j, people[j]);
						System.out.println("命中---------------------------------------第" + j + "个item，" + "size-----"
								+ intList.size());
						if (intList.size() == 1) {
							int[] temp = people[i];
							people[i] = people[j];
							people[j] = temp;
							/** 如果只有一个命中，则直接替换 */
							break label;
						}
					}

					boolean expect = true;
					Bean bean = new Bean();
					for (int x = 0; x < intList.size(); x++) {
						int[] tempTarget = intList.get(x).getArray();
						/** 第x个设置为当前位置的数组，测试其他的作为n+1是否合适的 */
						people[i] = tempTarget;

						for (int y = 0; y < intList.size(); y++) {
							/** 测试数组里其他的在n+1是否合适 */
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
	 * 获取当前数组[h, k]在整个数组中，h小于前面位置item的h的个数
	 * 
	 * 需要比较第index位置的数组在targetIndex位置前面的大于等于自己h的个数
	 * 
	 * @param index
	 *            当前需要比较的位置
	 * @param targetIndex
	 *            需要放到的位置
	 * @return 个数
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
	 *            目标数组
	 * @param targetIndex
	 *            需要放到的位置
	 * @return 个数
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
	 * 目标target在此是否可以放在此位置
	 * 
	 * @param index
	 *            当前需要比较的位置
	 * @param targetIndex
	 *            需要放到的位置
	 * @param target
	 *            目标数组
	 * @return 是否适合
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
