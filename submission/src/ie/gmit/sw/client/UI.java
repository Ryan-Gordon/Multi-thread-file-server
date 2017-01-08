package ie.gmit.sw.client;

/* 
 *	UI is an abstraction of what a user menu should be able to do
 *	Contains a boolean called active used for a while loop to keep the UI running
 *	Also has a menu with a number of options and a quit function which sets the boolean to false 
 */
public class UI {
	private boolean active; //used to show menu as long as user has not quit()
	
	public UI(){
		this.active = true; 
	}
	/*
	 * This is the standard menu for a client.
	 * Contains 4 basic operations:
	 * Connect to a server using conf.xml
	 * Print available files to screen (requires connection)
	 * Attempt to download an available files to screen (requires connection)
	 * Close client and connections
	 */
	public void displayMenu() {
		System.out.println("\n1. Connect to Server");
		System.out.println("2. Print File Listing");
		System.out.println("3. Download File");
		System.out.println("4. Quit");
		
		System.out.print("\n Type Option [1-4] > ");
	}
	public boolean isActive(){
		return active;
	}
	public void quit(){
		this.active = false; //user for option 4
	}
}
