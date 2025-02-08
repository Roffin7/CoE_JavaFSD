package WareHouse;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
        InventoryManager im = new InventoryManager();
        
        im.loadInventoryFromFile("C:\\Users\\Roffin Jason\\git\\CoE_JavaFSD\\TechM_Tasks\\src\\WareHouse\\inventory.txt");
       

        Order o1 = new Order("O1", Arrays.asList("1", "2"), Order.Priority.STANDARD);
        Order o2 = new Order("O2", Arrays.asList("3"), Order.Priority.EXPEDITED);
        Order o3 = new Order("O3", Arrays.asList("1", "3"), Order.Priority.STANDARD);

        im.addOrder(o1);
        im.addOrder(o2);
        im.addOrder(o3);

        System.out.println("Processing orders:");
        im.processOrders();
        
        System.out.println("------------------------------");
        im.saveInventoryToFile("C:\\Users\\Roffin Jason\\git\\CoE_JavaFSD\\TechM_Tasks\\src\\WareHouse\\updated_inventory.txt");

        
	}

}
