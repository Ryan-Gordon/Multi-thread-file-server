package ie.gmit.sw.requests;

import java.io.*;

public class DownloadFileRequest extends Request {

	/*
	 * A downloadfilerequest is a job given to a thread.
	 * This job prompts the user for a filename
	 * the filename is then queried from the server
	 * if the file is found a transfer of bytes begins ending with a file being created with these bytes on client-side
	 * if the file is not found then the client is notified and returned to the menu
	 */
	private static final long serialVersionUID = 1L;
	
	

	private String filePath;
	private String fileName;
	
	private String command;
	public DownloadFileRequest(String ipAddress, String fileName) {
		super(ipAddress);
		this.fileName = fileName;
		this.command = "File: "+this.fileName+" - Download";
	}

	@Override
	public void run() {
		File file = new File(filePath + "/" + fileName);
		byte[] byteArray = new byte[(int)file.length()];
		
		try {
			FileInputStream fileIn = new FileInputStream(file);
			BufferedInputStream bufferedIn = new BufferedInputStream(fileIn);
			bufferedIn.read(byteArray,0,byteArray.length); // copied file into byteArray
			
			ObjectOutputStream out = new ObjectOutputStream(super.getSocket().getOutputStream());
			out.writeObject("Success");
			command = "File: "+fileName+" - Download";
			out.writeObject(byteArray);
			out.flush();
			
			bufferedIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found on server. Please check filename you provided");
			ObjectOutputStream out;
			
			/*Another try catch. This one is used to instantiate another ObjectOutputStream 
			*The Stream sends a message to the client notifying the user
			* We want to send a message to the user in either case success or failure
			* This is so the client does not just wait for a file forever!!
			* I opted to use this try catch because the other option was throwing IOException entirely
			* Good programmers handle exceptions - Bad ones ignore them
			*/			
			try {
				out = new ObjectOutputStream(super.getSocket().getOutputStream());
				out.writeObject("File not found");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (IOException e) {
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