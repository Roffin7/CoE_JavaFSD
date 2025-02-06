package WareHouse;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		Product p1 = new Product("1","Mobile",2,new Location(1,1,1));
		Product p2 = new Product("2","Buds",2,new Location(2,1,2));
		Product p3 = new Product("3","Tab",2,new Location(2,2,2));
		
		InventoryManager im = new InventoryManager();
		
		im.addProduct(p1);
		im.addProduct(p2);
		im.addProduct(p3);
		
		Order o1 = new Order("O1",Arrays.asList("1","2"),Order.Priority.STANDARD);
		Order o2 = new Order("O2",Arrays.asList("3"),Order.Priority.EXPEDITED);
		Order o3 = new Order("O3",Arrays.asList("1","3"),Order.Priority.STANDARD);
		
		im.addOrder(o1);
		im.addOrder(o2);
		im.addOrder(o3);
		
		System.out.println("Processing Orders");
		im.processOrders();
		
		System.out.println("Final Stock For Mobile: " + im.getProduct("1").getQuantity());
		System.out.println("Final Stock For Buds: " + im.getProduct("2").getQuantity());
		System.out.println("Final Stock For Tab: " + im.getProduct("3").getQuantity());
	}

}
