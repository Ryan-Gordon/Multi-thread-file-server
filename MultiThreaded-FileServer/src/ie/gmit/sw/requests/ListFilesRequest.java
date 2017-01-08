package ie.gmit.sw.requests;

import java.io.*;

public class ListFilesRequest extends Request {
	/*
	 * A list request is a job given to a thread.
	 * This job simply lists all the filenames at a specified filepath
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private static final String command = "List all files";
	
	private String filePath;
	
	public ListFilesRequest(String client) {
		super(client);
	}

	@Override
	public void run() {
		try {
			File folder = new File(filePath);
			File[] files = folder.listFiles();
		
			ObjectOutputStream out = new ObjectOutputStream(super.getSocket().getOutputStream());
			out.writeObject(files);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return command + " requested by " + super.getClient() + " at " + super.getDate().toString();
	}

}
