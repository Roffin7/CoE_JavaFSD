package WareHouse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryManager {
	private PriorityQueue<Order> orderQueue;
	private Map<String,Product> products;
	private ExecutorService executor;
	
	public InventoryManager() 
	{
		this.products = new ConcurrentHashMap<>();
		this.orderQueue = new PriorityQueue<>();
		this.executor = Executors.newFixedThreadPool(3); 
	}
	
	// Load inventory from a file
    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0].trim();
                String name = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());
                int aisle = Integer.parseInt(parts[3].trim());
                int shelf = Integer.parseInt(parts[4].trim());
                int bin = Integer.parseInt(parts[5].trim());
                Product product = new Product(id, name, quantity, new Location(aisle, shelf, bin));
                addProduct(product);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    // Save inventory to a file
    public void saveInventoryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : products.values()) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getQuantity() + ","
                        + product.getLocation().getAisles() + "," + product.getLocation().getShelves() + ","
                        + product.getLocation().getBinNumbers() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
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
			executor.execute(()->fulfillOrder(order));
		}
		executor.shutdown();
		try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Some tasks did not finish in time.");
            } else {
                System.out.println("All orders processed successfully.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
        printFinalStock();
	}
	
	private void fulfillOrder(Order order)
	{
		System.out.println(Thread.currentThread().getName() + " processing " + order);
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
				System.out.println(Thread.currentThread().getName() + " - Stock decreased for " + product.getName() + ": " + product.getQuantity());
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
	
	public void printFinalStock() {
        System.out.println("\nFinal stock summary:");
        for (Product product : products.values()) {
            System.out.println("Product: " + product.getName() + ", Quantity: " + product.getQuantity());
        }
    }

}
