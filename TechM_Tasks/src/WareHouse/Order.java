package WareHouse;

import java.util.List;

public class Order implements Comparable<Order> {
	private String orderId;
	private List<String> productIds;
	private Priority priority;
	
	public enum Priority
	{
		STANDARD,EXPEDITED;
	}
	
	public Order(String orderId,List<String> productIds,Priority priority) 
	{
		this.orderId = orderId;
		this.productIds = productIds;
		this.priority = priority;
	}
	
	public String getOrderId() 
	{
		return orderId;
	}
	
	public List<String> getProductIds()
	{
		return productIds;
	}
	
	public Priority getPriority() 
	{
		return priority;
	}

	@Override
	public int compareTo(Order o) {
		return o.priority.compareTo(this.priority);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productIds=" + productIds + ", priority=" + priority + "]";
	}
	
}
