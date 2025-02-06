package WareHouse;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
	PriorityQueue<Order> orderQueue;
	Map<String,Product> products;
	
	public InventoryManager() 
	{
		this.products = new ConcurrentHashMap<>();
		this.orderQueue = new PriorityQueue<>();
	}
	
	public void addProduct(Product product) 
	{
		products.put(product.getId(), product);
	}
	
	public Product getProduct(String id) 
	{
		return products.get(id);
	}
	
	public void addOrder(Order order) 
	{
		orderQueue.add(order);
		System.out.println("Order added: " + order);
	}
	
	public void processOrders() 
	{
		while (!orderQueue.isEmpty()) 
		{
			Order order = orderQueue.poll();
			System.out.println("Processing " + order);
			fulfillOrder(order);
		}
	}
	
	private void fulfillOrder(Order order)
	{
		for(String productId : order.getProductIds()) 
		{
			try {
				decreaseStock(productId,1);
			} catch (OutOfStockException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public synchronized  void increaseStock(String id,int quantity)
	{
		Product product = products.get(id);
		
		if (product != null) 
		{
			product.setQuantity(product.getQuantity() + quantity);
			System.out.println("Stock increased for product: " + product.getName() +". New Quantity: " + product.getQuantity());
		}
		else 
		{
			System.out.println("Product Not Found: " + product.getId());
		}
	} 
	
	public synchronized void decreaseStock(String id,int quantity) throws OutOfStockException 
	{
		Product product = products.get(id);
		
		if (product != null) 
		{
			if (product.getQuantity() >= quantity) 
			{
				product.setQuantity(product.getQuantity() - quantity);
				System.out.println("Stock decreased for product: " + product.getName() + ". New Quantity: " + product.getQuantity());
			}
			else 
			{
				throw new OutOfStockException("Insufficient stock for product: " + product.getName());
			}
		}
		else 
		{
			System.out.println("Product Not Found: " + product.getId());
		}
	}

}
