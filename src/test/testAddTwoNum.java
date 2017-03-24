package test;

/**
 * https://leetcode.com/problems/add-two-numbers/#/description
 * 
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * @author Yocn
 * 
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(val);
		ListNode tempListNode = this;
		while (tempListNode.next != null) {
			tempListNode = tempListNode.next;
			sb.append("-" + tempListNode.val);
		}
		return sb.toString();
	}
}

public class testAddTwoNum {

	public static void main(String[] args) {

		ListNode l1 = getAListNode(2);
		ListNode l3 = getAListNode(3);
		ListNode l4 = getAListNode(4);
		l4.next = l3;
		l1.next = l4;

		ListNode l2 = getAListNode(5);
		ListNode l6 = getAListNode(6);
		ListNode l42 = getAListNode(4);
		l6.next = l42;
		l2.next = l6;
		System.out.println(l1);
		System.out.println(l2);

		addTwoNumbers(l1, l2);
	}

	private static ListNode getAListNode(int x) {
		return new ListNode(x);
	}

	/**
	 * [2,4,3]
	 * 
	 * [5,6,4]
	 * 
	 * [7,0,8]
	 * 
	 * 把l1，l2计算并挂到source上
	 * 
	 * @param source
	 * @param l1
	 * @param l2
	 */
	public static ListNode add(ListNode source1, ListNode l1, ListNode l2,
			boolean isHead) {
		ListNode source = new ListNode(0);
		if (l1.val + l2.val >= 10) {
			int i = (l1.val + l2.val) / 10;
			l1.next.val++;
			source.val = (l1.val + l2.val) % 10;
		} else {
			source.val = (l1.val + l2.val);
		}
		source1.val = source.val;
		return source1;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode source = new ListNode(0);
		source = add(source, l1, l2, true);
		ListNode tempSource = source;
		ListNode tempNode1 = l1;
		ListNode tempNode2 = l2;
		while (tempNode1.next != null) {
			tempSource = getNextNode(tempSource);
			tempNode1 = tempNode1.next;
			tempNode2 = tempNode2.next;
			add(tempSource, tempNode1, tempNode2, false);
		}
		return source;
	}

	private static ListNode getNextNode(ListNode node) {
		if (node.next == null) {
			ListNode node1 = new ListNode(0);
			node.next = node1;
			return node1;
		} else {
			return node.next;
		}

	}
}
