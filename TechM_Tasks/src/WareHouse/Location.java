package WareHouse;

public class Location {
	private int aisle;
	private int shelves;
	private int binNumbers;
	
	public Location(int aisle,int shelves,int binNumbers) 
	{
		this.aisle = aisle;
		this.shelves = shelves;
		this.binNumbers = binNumbers;
	}
	
	public int getAisles() 
	{
		return aisle;
	}

	public void setAisles(int aisle) 
	{
		this.aisle = aisle;
	}
	
	public int getShelves() 
	{
		return shelves;
	}
	
	public void setShelves(int shelves) 
	{
		this.shelves = shelves;
	}
	
	public int getBinNumbers() 
	{
		return binNumbers;
	}
	
	public void setBinNumbers(int binNumbers) 
	{
		this.binNumbers = binNumbers;
	}

	@Override
	public String toString() {
		return "Location [aisle=" + aisle + ", shelves=" + shelves + ", binNumbers=" + binNumbers + "]";
	}
	
	
}
