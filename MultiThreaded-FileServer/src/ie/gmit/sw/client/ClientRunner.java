package ie.gmit.sw.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientRunner {
	
	
	public static void main(String[]args) throws Throwable{
		Context conf = new Context(); //a new context object used to hold the XML values
		UI ui = new UI(); //a new UI object used to display the menu and do commands
		XMLParser parser = new XMLParser(conf); 
		parser.parse();//used to parse the xml and assign its valeus to conf object
		ServerCommandsService commands = new ServerCommandsService(conf);
		System.out.println(conf);		
		int choice;
		
		Scanner in = new Scanner(System.in);

		
		while(ui.isActive()){
			ui.displayMenu();	
			choice = in.nextInt();
			
			switch(choice){
			case 1:
				//connect to server
				commands.connectToServer();
				break;
			case 2:
				//get file list from server
				break;
			case 3:
				//prompt for input, then attempt to find the file
				break;
			case 4:
				//quit
				ui.quit();
				break;
			}//switch
			
		}//run this while loop until the ui.quit() method is called
		
		System.out.println("Client connection closing! Thank you.");
		in.close();
	}//main
	
	
}//clientRunner


