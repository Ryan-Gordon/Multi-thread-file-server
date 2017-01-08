# MultiThreaded File Server
[![GitHub version](https://badge.fury.io/gh/boennemann%2Fbadges.svg)](http://badge.fury.io/gh/boennemann%2Fbadges)
[![Open Source Love](https://badges.frapsoft.com/os/mit/mit.svg?v=102)](https://github.com/ellerbrock/open-source-badge/)  
A java implementation of a multi-threaded file server and logging application that allows a client application to download files using a set of options presented in a terminal user interface. 
#### Features
+ MultiThreaded to handle multiple clients

+ BlockingQueue - Multiple producer / One consumer
+ Javadocs
+ 2 UML diagrams

+ Robust - Works with 1 parameter , no parameter or 2 

#### User Guide
###### Installation:
To run the web app , navagate to the directory that contains the oop.jar file and enter the commands:
###### Start the server

> $ java -cp .:./oop.jar ie/gmit/sw/server/ServerRunner 7777 path/to/my/files

Notes for Server:
You do not need to specify a port number or a file path. 
Ensure if you are providing parameters to enter them in the following format: 
> $ java -cp .:./oop.jar ie/gmit/sw/server/ServerRunner <port> <path>

If you only provide 1 parameter the server will attempt to use this and use a default value for the other.
> $ java -cp .:./oop.jar ie/gmit/sw/server/ServerRunner 8888  
> Will use port 8888 and default filePath  

Only the path
> $ java -cp .:./oop.jar ie/gmit/sw/server/ServerRunner path/to/my/files  
> Will use default port 7777 and path/to/my/files

We then need to start the client


###### Start the client

> $ java -cp .:./oop.jar ie/gmit/sw/server/ClientRunner 
 

Upon loading the client you will be prompted with a menu of options:  
1 Connect to server  
2 Print all files (requires connection)  
3 Download file (requires connection)  
4 Quit  
Once registered the dashboard appears. On the sidemenu are a number of quick link to different sections of the app aswell as buttons on the dashboard.  
###### Connect:  
The client parses a number of settings from the XML file using XMLParser. Once parsed the client attempts to connect to the host via sockets.
If successful a message is returned notifying and the client can perform other operations.
###### List all files:  
A connection is required for this

If a connection is made the client sends a ListFileRequest to the server. The server then returns a list of files.
###### DownloadFileRequest:
The client will prompt for a filename and once provided will attempt to find this file on the server. If the file is found the server will begin a transfer of bytes to the client.  
If not successful the server will inform the client and return another menu.

###### API:
To access the api:
API routes are available for a number of currencies to get there you will need to navagate to /api/currencyName. e.g 
`/api/sdc`  
 

### Roadmap
+ More options such as client upload to server. 
+ Download collection of files


#### Object Orientated Programming Project 2016

This repository contains code and information for a third-year undergraduate project for the module Object Orientated Programming. The module is taught to undergraduate students at GMIT in the Department of Computer Science and Applied Physics. The lecturer is Dr John Healy.

#### Requirements
• The client application should present a command-line user interface when started from a terminal window as follows: java -cp .:./oop.jar ie.gmit.sw.Client, where oop.jar is the name of the JAR archive containing the full set of classes (client and server) for the project. The following options should be offered to a user:1. Connect to Server 2. Print File Listing 3. Download File4. QuitType Option [1-4]> 

