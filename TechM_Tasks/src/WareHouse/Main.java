package WareHouse;

public class Main {
	
	public static void main(String[] args) {
		Product p1 = new Product("1","Mobile",2,new Location(1,1,1));
		Product p2 = new Product("2","Buds",2,new Location(2,1,2));
		InventoryManager im = new InventoryManager();
		im.addProduct(p1);
		im.addProduct(p2);
		System.out.println(im.getProduct(p1.getId()));
		
		im.increaseStock("1", 3);
		
		try {
			im.decreaseStock("1", 2);
		} catch (OutOfStockException e) {
			System.out.println(e.getMessage());
		}
	}

}
