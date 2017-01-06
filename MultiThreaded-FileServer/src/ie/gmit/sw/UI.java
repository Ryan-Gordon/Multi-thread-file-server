package ie.gmit.sw;

import java.util.Scanner;

public class UI {
	
	public UI(){
		
	}
	
	public void displayMenu() {
		System.out.println("\n1. Connect to Server");
		System.out.println("2. Print File Listing");
		System.out.println("3. Download File");
		System.out.println("4. Quit");
		
		System.out.print("\nType Option [1-4] > ");
	}
}
