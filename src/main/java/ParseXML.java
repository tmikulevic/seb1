import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParseXML {
	public static Document readXML(String xml) throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(xml));

			// Build Document
			Document document = builder.parse(src);

			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			return document;
	}

	public static String[][] getRates(String xml) {
		String[][] result;
		try {
			Document document = readXML(xml);
			Element root = document.getDocumentElement();
			NodeList nList = document.getElementsByTagName("FxRate");
			result = new String[nList.getLength()][2];
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element ccyAmt = (Element) node;

					//Element eElement = (Element) ccyAmt.getElementsByTagName("CcyAmt").item(0);
					//resultString=resultString+eElement.getElementsByTagName("Ccy").item(0).getTextContent();//EUR
					//resultString=resultString+eElement.getElementsByTagName("Amt").item(0).getTextContent();//1
					
					Element eElement1 = (Element) ccyAmt.getElementsByTagName("CcyAmt").item(1);
					result[temp][0] = eElement1.getElementsByTagName("Ccy").item(0).getTextContent();
					result[temp][1] = eElement1.getElementsByTagName("Amt").item(0).getTextContent();
				}

			}
			return result;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[][] getList(String xml) {
		String[][] result;
		try {
			Document document = readXML(xml);
			Element root = document.getDocumentElement();
			NodeList nList = document.getElementsByTagName("CcyNtry");
			result = new String[nList.getLength()][3];
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element CcyNtry = (Element) node;

					result[temp][0] = CcyNtry.getElementsByTagName("Ccy").item(0).getTextContent();

					Element CcyNm = (Element) CcyNtry.getElementsByTagName("CcyNm").item(0);
					//resultString=resultString+CcyNm.getAttribute("lang");
					result[temp][1] = CcyNm.getTextContent();
					
					CcyNm = (Element) CcyNtry.getElementsByTagName("CcyNm").item(1);
					//resultString=resultString+CcyNm.getAttribute("lang");
					result[temp][2] = CcyNm.getTextContent();
				}

			}
			return result;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
