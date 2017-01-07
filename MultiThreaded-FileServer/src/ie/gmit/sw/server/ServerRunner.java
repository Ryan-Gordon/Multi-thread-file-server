package ie.gmit.sw.server;

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
			
			
			System.out.println("Arguements missing. Defaulting to port 7777 and downloads dir");
//			if(args[0]==null || args[0] instanceof String){
//				System.out.println("No port number provided");
//			}
			//if no parameter provided
			if(args.length == 0){
				String home = System.getProperty("user.home"); //get the home directory of the user
				port = 7777; //the default value for server
				filePath = home+"/Downloads/"; //the default directory is the downlaods folder of the user
			}
			else{
				return;
			}
		}
		new WebServer(port, filePath);
	} //end main()

}
