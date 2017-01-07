package ie.gmit.sw.client;


public class UI {
	private boolean active;
	
	public UI(){
		this.active = true;
	}
	
	public void displayMenu() {
		System.out.println("\n1. Connect to Server");
		System.out.println("2. Print File Listing");
		System.out.println("3. Download File");
		System.out.println("4. Quit");
		
		System.out.print("\nType Option [1-4] > ");
	}
	public boolean isActive(){
		return active;
	}
	public void quit(){
		this.active = false;
	}
}
