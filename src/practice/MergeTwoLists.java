package practice;

/**
 * solve the problem is defining a fake head. Then compare the first elements
 * from each list. Add the smaller one to the merged list. Finally, when one of
 * them is empty, simply append it to the merged list, since it is already
 * sorted.
 * time complexity O(m + n)
 * @author leen
 *
 */

public class MergeTwoLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		if (l1 != null) {
			cur.next = l1;
		}
		if (l2 != null) {
			cur.next = l2;
		}
		return dummy.next;
	}
}
