package ie.gmit.sw;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLParser {
	private Context ctx;

	public XMLParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	
	public void init() throws Throwable{
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
				ctx.setUsername(attributes.item(0).getNodeValue());
			}
		}
		
		for(int i=0;i<children.getLength();i++){
			Node next = children.item(i);
			//check if the next item is an Element
			//we do not want to load in something that is not an Element
			if(next instanceof Element){
				Element e = (Element)next;
				
				if(e.getNodeName().equals("server-host")){
					ctx.setServer_host(e.getTextContent());
				}
				else if(e.getNodeName().equals("server-port")){
					ctx.setServer_port(e.getTextContent());
				}
				else if(e.getNodeName().equals("download-dir")){
					ctx.setDownload_dir(e.getTextContent());
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
