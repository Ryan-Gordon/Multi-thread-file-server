package ie.gmit.sw.server;

import java.io.File;
import java.nio.file.Files;

public class ServerRunner {
	public static void main(String[] args) {
		//variables for port number and file path
		int port = 7777; //initialise the default value for port
		String filePath = null;
		
		try {
			//Take parameters and try to assign them to variables
			
			port = Integer.parseInt(args[0]);
			filePath = args[1];
		} catch(Exception e) {
			/*If we get here, something was not provided by the user as a parameter
			 * When this happens we can do a number of things
			 * If the user provided nothing then we can use default values
			 * if the user did provide something but we can't use it for port || filePath
			 * then we will close the program.
			 * */
//			if(args[0]==null || args[0] instanceof String){
//				System.out.println("No port number provided");
//			}
			//if no parameter provided
			if(args.length == 0){
				System.out.println("No arguements provided. Defaulting to port 7777 and desktop/files dir");
				String home = System.getProperty("user.home"); //get the home directory of the user
				port = 7777; //the default value for server
				filePath = "./server-files"; //the default directory is the desktop folder of the server. 
			}
			/*
			 * If we only get one parameter, we will check it and see if we can work with it
			 */
			else if(args.length ==1){
				System.out.println("Only 1 parameter provided");
				File file = new File(args[0]);
				//check if the parameter given is a directory
				if(file.isDirectory()){
					System.out.println("Parameter appears to be a directory. Using default port number and this directory");
					filePath = args[0];
					port = 7777; //the default value for server
				}
				//also check if the parameter is an int, it could be a port number
				else if(isInteger(args[0])){
					System.out.println("Parameter appears to be a port number. Using default directory and this number");

					//TODO maybe some more checking if its a valid port
					String home = System.getProperty("user.home");
					filePath = "./server-files"; //the default directory is the desktop folder of the server. 
					port = Integer.parseInt(args[0]);
				}
			}
				
			else{
				return;
			}
		}
		new WebServer(port, filePath);
	} //end main()
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
