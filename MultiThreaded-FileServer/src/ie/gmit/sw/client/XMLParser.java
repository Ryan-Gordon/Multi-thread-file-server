package ie.gmit.sw.client;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLParser {
	private Context ctx;
	
	/*
	 * A constructor for the XMLParser class
	 * XML parser is used to initialize the config settings for connecting to the server. 
	 * 
	 */
	public XMLParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	//attempt to parse the client xml file for its attributes and elements
	public void parse() throws Throwable{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONFIG_FILE);
		
		//get the root element of the node tree
		Element root = doc.getDocumentElement();
		//get the root nodes children nodes
		NodeList children = root.getChildNodes();
		//find the elements
		NamedNodeMap attributes = root.getAttributes();
		//we want the attributes of the elements to find username
		for(int j=0;j<attributes.getLength();j++){
			if(attributes.item(j).getNodeName().equals("username")){
				// the D
				ctx.setUsername(attributes.item(0).getNodeValue());
			}
		}
		//iterate over the children elements from the XML
		for(int i=0;i<children.getLength();i++){
			Node next = children.item(i);
			//check if the next item is an Element
			//we do not want to load in something that is not an Element
			if(next instanceof Element){
				Element e = (Element)next;
				
				if(e.getNodeName().equals("server-host")){
					ctx.setServer_host(e.getTextContent());//take the host ip from the XML
				}
				else if(e.getNodeName().equals("server-port")){
					ctx.setServer_port(e.getTextContent());//take the host port from the XML
				}
				else if(e.getNodeName().equals("download-dir")){
					ctx.setDownload_dir(e.getTextContent());//take the clients download directory from the XML
				}
			}	
		}
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
	
	

}//XMLParser
