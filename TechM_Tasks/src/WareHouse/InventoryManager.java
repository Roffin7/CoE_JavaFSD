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
import java.util.logging.*;

public class InventoryManager {
	private static final Logger logger = Logger.getLogger(InventoryManager.class.getName());
	private PriorityQueue<Order> orderQueue;
	private Map<String,Product> products;
	private ExecutorService executor;
	
	public InventoryManager() 
	{	
		setupLogger();
		this.products = new ConcurrentHashMap<>();
		this.orderQueue = new PriorityQueue<>();
		this.executor = Executors.newFixedThreadPool(3); 
		logger.info("InventoryManager initialized.");
	}
	
	private void setupLogger() {
	    try {
	        Logger rootLogger = Logger.getLogger("");
	        Handler[] handlers = rootLogger.getHandlers();
	        for (Handler handler : handlers) {
	            if (handler instanceof ConsoleHandler) {
	                rootLogger.removeHandler(handler);
	            }
	        }

	        FileHandler fileHandler = new FileHandler("warehouse.log", true);
	        fileHandler.setFormatter(new SimpleFormatter());
	        logger.addHandler(fileHandler);
	        logger.setLevel(Level.ALL);  // Log all levels
	    } catch (IOException e) {
	        System.out.println("Failed to setup logger: " + e.getMessage());
	    }
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
            logger.info("Inventory loaded successfully from " + filename);
        } catch (IOException e) {
        	logger.severe("Error loading inventory: " + e.getMessage());
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
            logger.info("Inventory saved to " + filename);
        } catch (IOException e) {
        	logger.severe("Error saving inventory: " + e.getMessage());
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

	
	public void addProduct(Product product) 
	{
		products.put(product.getId(), product);
		logger.info("Product added: " + product);
	}
	
	public Product getProduct(String id) 
	{
		return products.get(id);
	}
	
	public void addOrder(Order order) 
	{
		orderQueue.add(order);
		logger.info("Order added: " + order);
		System.out.println("Order added: " + order);
		
	}
	
	public void processOrders() 
	{
		logger.info("Order processing started.");
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
        	logger.warning("Order processing interrupted: " + e.getMessage());
            e.printStackTrace();
        }
		logger.info("Order processing completed.");
        printFinalStock();
	}
	
	private void fulfillOrder(Order order)
	{
		logger.info(Thread.currentThread().getName() + " processing order: " + order);
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
				logger.info("Stock decreased for " + product.getName() + ". New quantity: " + product.getQuantity());
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
			logger.warning("Product not found: " + id);
			System.out.println("Product Not Found: " + product.getId());
		}
	}
	
	public void printFinalStock() {
		logger.info("Final stock summary:");
        System.out.println("\nFinal stock summary:");
        for (Product product : products.values()) {
        	logger.info("Product: " + product.getName() + ", Quantity: " + product.getQuantity());
            System.out.println("Product: " + product.getName() + ", Quantity: " + product.getQuantity());
        }
    }

}
