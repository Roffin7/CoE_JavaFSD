package Task1;

public class LinkedList {
	static class Node
	{
		int data;
		Node next;
		
		Node(int data)
		{
			this.data = data;
			this.next = null;
		}
	}
	
	public static boolean hasCycle(Node head) 
	{
		if (head == null || head.next == null) 
		{
			return false;
		}
		
		Node slow = head;
		Node fast = head;
		
		while (fast != null && fast.next != null) 
		{
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Node head = new Node(10);
		Node second = new Node(20);
		Node third = new Node(20);
		Node fourth = new Node(20);
		Node fivth = new Node(20);
		
		head.next = second;
		second.next = third;
		third.next = fourth;
		fourth.next = fivth;
		
		System.out.println("Has Cycle? "+hasCycle(head));
		
		fivth.next = third;
		System.out.println("Has Cycle? "+hasCycle(head));
	
	}

}
