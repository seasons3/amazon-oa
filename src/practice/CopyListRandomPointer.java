package practice;

/**
 * 1) Iterate the original list and duplicate each node. The duplicate of each
 * node follows its original immediately. 2) Iterate the new list and assign the
 * random pointer for each duplicated node. 3)Restore the original list and
 * extract the duplicated nodes.
 * 
 * space complexity O(1) and linear time complexity O(N)
 * 
 * @author leen
 *
 */
public class CopyListRandomPointer {
	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};
	// 1->2->3->4 1->1'-2->2'3->3->4->4'

	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode cur = head;
		while (cur != null) {
			RandomListNode copy = new RandomListNode(cur.label);
			copy.next = cur.next;
			cur.next = copy;
			cur = cur.next.next;
		}

		cur = head;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		RandomListNode dummy = new RandomListNode(-1);
		dummy.next = head;
		cur = dummy;
		// cur 1--1'
		while (cur != null && cur.next != null) {
			cur.next = cur.next.next;
			cur = cur.next;
		}
		/**
		 *  cur =  head;
        Node copyHead = head.next;
        Node copy = copyHead;
        while ( copy.next!=null) {
            cur.next = cur.next.next;
            cur = cur.next;
            
            copy.next = copy.next.next;
            copy = copy.next;
        }
        cur.next = cur.next.next;
  
        return copyHead;
		 */
		return dummy.next;

	}

}
