package WareHouse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
	Map<String,Product> products;
	
	public InventoryManager() 
	{
		this.products = new ConcurrentHashMap<>();
	}
	
	public void addProduct(Product product) 
	{
		products.put(product.getId(), product);
	}
	
	public Product getProduct(String id) 
	{
		return products.get(id);
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
