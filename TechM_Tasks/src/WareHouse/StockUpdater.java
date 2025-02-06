package WareHouse;

public class StockUpdater extends Thread {
	private InventoryManager inventoryManager;
	private String id;
	private int quantity;
	private boolean increase;
	
	public StockUpdater(InventoryManager inventoryManager,String id,int quantity,boolean increase) 
	{
		this.inventoryManager = inventoryManager;
		this.id = id;
		this.quantity = quantity;
		this.increase = increase;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			if(increase) 
			{
				inventoryManager.increaseStock(id, quantity);
			}
			else 
			{
				inventoryManager.decreaseStock(id, quantity);
			}
			
		}
		catch (OutOfStockException e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	

}
