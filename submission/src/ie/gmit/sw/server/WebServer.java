package ie.gmit.sw.server;

/* This class provides a very simple implementation of a web server. As a web server
 * must be capable of handling multiple requests from web browsers at the same time,
 * it is essential that the server is threaded, i.e. that the web server can perform
 * tasks in parallel and serially (one request at a time, after another).
 * 
 * In programming languages, all network communication is handled using sockets. A 
 * socket is a software abstraction of a connection between one computer on a network
 * and another. A server-socket is a process that listens on a port number for 
 * incoming client requests. For example, the standard port number for a HTTP server (a
 * web server) is port 80. Most of the commonly used Java networking classes are 
 * available in the java.net package. The java.io package contains a set of classes
 * designed to handle Input/Output (I/O) activity. We will use both packages in the web
 * server class below.  
 */

import java.io.*; //Contains classes for all kinds of I/O activity
import java.net.*; //Contains basic networking classes
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.requests.*;

public class WebServer {
	private ServerSocket ss; //A server socket listens on a port number for incoming requests
	private int port;
	private String filePath;
	
	//The boolean value keepRunning is used to control the while loop in the inner class called
	//Listener. The volatile keyword tells the JVM not to cache the value of keepRunning during
	//optimisation, but to check it's value in memory before using it.
	private volatile boolean keepRunning = true;
	
	//Used for blocking queue. We will allow up to 10 logged requests in the blocking queue
	private static final int QUEUE_SIZE = 10;
	//A blocking queue used to queue logs. Many producer threads add to the blocking queue and 1 consumer thread takes these requests and logs them to a file 
	BlockingQueue<Request> logRequestQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
	
	/*
	 * A constructor for the WebSever class
	 * We make this public because we are accessing it from another file ServerRunner.java
	 * In the WebServer in class example this stays as private because we access it from within the class
	 */
	public WebServer(int port, String filePath){
		try { //Try the following. If anything goes wrong, the error will be passed to the catch block
			
			this.port = port;
			this.filePath = filePath;
			
			ss = new ServerSocket(port); //Start the server socket listening on port 7777 as default or whatever port provided by user
			
			/* A Thread is a worker. A runnable is a job. We'll give the worker thread called "server"
			 * the job of handling incoming requests from clients.
			 * Note: calling start results in a new JVM stack being created. The run() method of the Thread
			 * or Runnable will be placed on the new stack and executed when the Thread Scheduler (consider
			 * this a cantankerous and uncommunicative part of the JVM) decides so. There is absolutely NO
			 * GUARANTEE of either order or execution time. We can however ask the Thread Scheduler 
			 * (politely) to run a thread as a max, min or normal priority. 
			 */
			Thread server = new Thread(new Listener(), "Web Server Listener"); //We can also name threads
			server.setPriority(Thread.MAX_PRIORITY); //Ask the Thread Scheduler to run this thread as a priority
			server.start(); //The Hollywood Principle - Don't call us, we'll call you
			
			
			new Thread(new LogFileRequest(logRequestQueue), "RequestLogger").start();
			System.out.println("Server started and listening on port " + port);
			
		} catch (IOException e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
			System.out.println("Error starting server! Error Message: " + e.getMessage());
		}
	}//End constructor
	
	
	/* The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. The job that
	 * the class has been given is to intercept incoming client requests and farm them out to other
	 * threads. Each client request is in the form of a socket and will be handled by a separate new thread.
	 */
	private class Listener implements Runnable{ //A Listener IS-A Runnable
		
		//The interface Runnable declare the method "public void run();" that must be implemented
		public void run() {
			int counter = 0; //A counter to track the number of requests
			while (keepRunning){ //Loop will keepRunning is true. Note that keepRunning is "volatile"
				try { //Try the following. If anything goes wrong, the error will be passed to the catch block
					
					Socket s = ss.accept(); //This is a blocking method, causing this thread to stop and wait here for an incoming request
					
					ObjectInputStream in = new ObjectInputStream(s.getInputStream());
					Request request = (Request) in.readObject();
					
					//if we want to do a download or a printing of available files we will need to set the filepath first
					if(request instanceof ListFilesRequest) {
	                	((ListFilesRequest) request).setFilePath(filePath); //Initialize the file path before firing the request
	                	
	                }
					else if (request instanceof DownloadFileRequest) {
	                	((DownloadFileRequest) request).setFilePath(filePath); //Initialize the file path before firing the request
	                	
	                }
					
					//Run the job on its own thread
	                request.setSocket(s);
	                /* If we get to this line, it means that a client request was received and that the socket "s" is a real network
					 * connection between some computer and this programme. We'll farm out this request to a new Thread (worker), 
					 * allowing us to handle the next incoming request (we could have many requests hitting the server at the same time),
					 * so we have to be able to handle them quickly.
					 */
		             new Thread(request, "Request-" + counter).start(); //Give the new job to the new worker and tell it to start work
		             logRequestQueue.put(request); //Add the request to the blocking queue invoking the toString method for the request thread
	                
	             
	                counter++; //Increment counter
				} catch (Exception e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
					System.out.println("Error handling incoming request..." + e.getMessage());
				}
			}//End of while
		}//End of run
	}//End of inner class Listener
}//End of class WebServer