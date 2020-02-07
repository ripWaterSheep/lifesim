package game.activity.inventorySystem;

public class Item  {

	private String name;
	public String getName() {
		return name;
	}

	private int price;
	public int getPrice() {
		return price;
	}


	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}



	void whileUsing(){}

	void onClick() {}
	
}
