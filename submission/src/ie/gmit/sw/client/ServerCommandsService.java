package ie.gmit.sw.client;

/* ServerCommandsService 
 * The ServerCommandsService contains the various services the Server offers
 * Some of the services we require from the server need some sort of configuration first.
 * i.e a speicified file for a downloadRequest
 * 
 * The server should in my opinion should only take Request commands and provide responses 
 */



import java.io.*;
import java.net.*;
import java.util.Scanner;

import ie.gmit.sw.client.Context;
import ie.gmit.sw.requests.*;

public class ServerCommandsService{

	private Socket s;
	
	private String host;
	private int port;
	private String clientIp;
	
	private File[] filesList;
	private String downloadDir;
	
	
	public ServerCommandsService (Context ctx) {
		this.host = ctx.getServer_host();
		this.port = ctx.getServer_port();
		System.out.println(this.host+this.port);
	}
	
	public void connectToServer() {
		
		try {
			s = new Socket(host, port);
			
			//Get clientIp 
	        clientIp = s.getLocalAddress().getHostAddress();
			
	        //Serialise / marshal a request to the server
	        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
	        
	        out.writeObject(new ConnectionRequest(clientIp)); //Serialise
	        out.flush(); //Ensure all data sent by flushing buffers
	        
	        Thread.yield(); //Pause the current thread for a short time (not used much)
	        
	        //Handle response from server 
	        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
	        String response = (String) in.readObject(); //Deserialise
	        	        
	        //Display connection message to client
	        System.out.println(response);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listFiles() {
		try {
			s = new Socket(host, port);
			clientIp = s.getLocalAddress().getHostAddress();
			
			//Serialise / marshal a request to the server
	        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
	       
	        
	        out.writeObject(new ListFilesRequest(clientIp));
	        out.flush();

	        Thread.yield(); //Pause the current thread for a short time
	        
	        //Handle response
	        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
	        File[] filesList = (File[]) in.readObject(); //Deserialise
	        
	        for(int i = 0; i < filesList.length; i++) {
	        	System.out.println(filesList[i].getName());
	        	
	        }
		} catch (Exception e) {
			//System.out.println("There is no existing connection");
			e.printStackTrace();
		}
		
        
	}
	public void downloadFile(String downloadDir) throws IOException, ClassNotFoundException {
		Scanner console = new Scanner(System.in);
		
		System.out.print("Enter a file name from the list to download: ");
		String fileName = console.next();
		
		
				try {
					s = new Socket(host, port);
					clientIp = s.getLocalAddress().getHostAddress();
					
					//Serialise / marshal a request to the server
					ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			        
			        out.writeObject(new DownloadFileRequest(clientIp, fileName));
			        out.flush();
			
			        Thread.yield(); //Pause the current thread for a short time
			        
			        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			        String statusMsg = (String) in.readObject();
			        System.out.println(statusMsg);
			        if(statusMsg.equalsIgnoreCase("Success")){
			        	byte[] byteArray = (byte[]) in.readObject();
				        
				        File file =  new File(downloadDir+File.separator+fileName);
				        
				        file.getParentFile().mkdirs();
				        file.createNewFile();
				        
				        FileOutputStream fos = new FileOutputStream(file);
				        
				        fos.write(byteArray);
				        fos.close();
			        }
			        
			        
				} catch (FileNotFoundException s){
					System.out.println("File does not exist on the server. Returning");
					return;
				}
				catch (Exception e) {
					System.out.println("File does not exist on the server. Returning");
					e.printStackTrace();
				}
			}
		
	

}
