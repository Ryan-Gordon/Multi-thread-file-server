package ie.gmit.sw.client;

/* 
 * @description ClientRunner is main class for the client package
 * It makes use of all the other classes within the ie.gmit.sw.client package
 * 
 * The Client handles logic processing for which files it wants, what IP and Port to try connecting to etc
 * Requests sent to the server are straightforward 'I want this' - 'Here you go' i.e the server does not handle logic for the client just delivers whats asked
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientRunner {
	
	public static void main(String[]args) throws Throwable{
		Context conf = new Context(); //a new context object used to hold the XML values
		//Instantiate a new XMLParser and invoke the parse method
		XMLParser parser = new XMLParser(conf); 
		parser.parse();//used to parse the xml and assign its valeus to conf object
		UI ui = new UI(); //a new UI object used to display the menu and do commands
		ServerCommandsService commands = new ServerCommandsService(conf);
		System.out.println(conf);		
		String choice;
		
		Scanner in = new Scanner(System.in);
		
		while(ui.isActive()){
			ui.displayMenu();	
			choice = in.next();
			
			switch(choice){
			case "1":
				//connect to server
				commands.connectToServer();
				break;
			case "2":
				//get file list from server
				commands.listFiles();
				break;
			case "3":
				//prompt for input, then attempt to find the file
				commands.downloadFile(conf.getDownload_dir());
				break;
			case "4":
				//quit
				ui.quit();
				break;
			default:
				System.out.println("Invalid input. Please select a valid option");
			}//
			
		}//run this while loop until the ui.quit() method is called. Handle invalid cases and return to menu
		
		System.out.println("Client connection closing! Thank you.");
		in.close(); //good manners 
	}//main
}//clientRunner


