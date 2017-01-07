package ie.gmit.sw.requests;

import java.io.*;

import ie.gmit.sw.requests.Request;

public class ConnectionRequest extends Request {
	
	private static final long serialVersionUID = 1L;
	private static final String action = "Connection";
	
	public ConnectionRequest (String clientIp) {
		super(clientIp);
	}
	
	@Override
	public void run() {		
		try {
			String message = "Connection Successful";
			ObjectOutputStream out = new ObjectOutputStream(super.getSocket().getOutputStream());
			out.writeObject(message);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return action + " requested by " + super.getClient() + " at " + super.getDate().toString();
	}
	
}
