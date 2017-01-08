package ie.gmit.sw.requests;

import java.io.*;
import java.util.concurrent.*;

public class LogFileRequest implements Runnable {
	private BlockingQueue<Request> logRequestQueue;
	private FileWriter fw;
	private boolean keepRunning = true;
	
	public LogFileRequest(BlockingQueue<Request> q) throws IOException {
		this.logRequestQueue = q;
		fw = new FileWriter(new File("log.txt"));
	}
	
	@Override
	public void run() {
		while(keepRunning) {
			try {
				fw = new FileWriter(new File("log.txt"),true);
				Request request = logRequestQueue.take();
				System.out.println(request.toString());
				fw.write(request.toString() + "\n");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
	}

}//End of LogFileRequest
