package WareHouse;

public class Product {
	private String id;
	private String name;
	private int quantity;
	private Location location;
	
	public Product(String id,String name,int quantity,Location location) 
	{
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", location=" + location + "]";
	}
	
	
}
