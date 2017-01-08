package ie.gmit.sw.client;
/*
 * Context.java is a class used to abstract a part of the xml parsing procedure
 * The class Context is a class simply for the storage of the xml config files values
 * 
 */
public class Context {
	public static final String CONFIG_FILE="ie/gmit/sw/client/ClientConf.xml";
	private String username;
	private String server_host;
	private int server_port;
	private String download_dir;
	
	/*
	 * Constructor for class
	 */
	
	public Context() {
		super();
	}
	
	/*
	 * Getters and Setters for the class variables
	 * Private variables accessed and modified via getters and setters
	 * is a form of encapsulation
	 */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServer_host() {
		return server_host;
	}

	public void setServer_host(String server_host) {
		this.server_host = server_host;
	}

	public int getServer_port() {
		return server_port;
	}

	public void setServer_port(String server_port) {
		this.server_port = Integer.parseInt(server_port);
	}

	public String getDownload_dir() {
		return download_dir;
	}

	public void setDownload_dir(String download_dir) {
		this.download_dir = download_dir;
	}

	@Override
	public String toString() {
		return "Context [username=" + username + ", server_host=" + server_host + ", server_port=" + server_port
				+ ", download_dir=" + download_dir + "]";
	}
	
	
	
}//Context