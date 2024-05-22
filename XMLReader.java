import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    public static void main(String[] args) {
        try {
            // Load XML file
            File file = new File("data.xml");

            // Create DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse XML file
            Document doc = builder.parse(file);

            // Normalize XML structure
            doc.getDocumentElement().normalize();

            // Get all record nodes
            NodeList nodeList = doc.getElementsByTagName("record");

            // Iterate through each record
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Print field values
                    System.out.println("Name: " + getNodeValue(node, "name"));
                    System.out.println("Postal/Zip: " + getNodeValue(node, "postalZip"));
                    System.out.println("Region: " + getNodeValue(node, "region"));
                    System.out.println("Country: " + getNodeValue(node, "country"));
                    System.out.println("Address: " + getNodeValue(node, "address"));
                    System.out.println("List: " + getNodeValue(node, "list"));
                    System.out.println("--------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to get node value
    private static String getNodeValue(Node node, String tagName) {
        Node childNode = ((org.w3c.dom.Element) node).getElementsByTagName(tagName).item(0);
        return childNode.getTextContent();
    }
}
