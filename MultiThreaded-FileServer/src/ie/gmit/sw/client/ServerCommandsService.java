package ie.gmit.sw.client;

import java.io.*;
import java.net.*;
import ie.gmit.sw.client.Context;
import ie.gmit.sw.requests.*;

public class ServerCommandsService{

	private Socket s;
	
	private String host;
	private int port;
	
	private String clientIp;
	
	
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

}
