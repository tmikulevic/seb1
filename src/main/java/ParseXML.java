import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	public static ArrayList<String> getRates(String xml) {
		ArrayList<String> result = new ArrayList<String>();
		String resultString = "";
		try {
			Document document = readXML(xml);
			Element root = document.getDocumentElement();
			NodeList nList = document.getElementsByTagName("FxRate");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element ccyAmt = (Element) node;

					Element eElement = (Element) ccyAmt.getElementsByTagName("CcyAmt").item(0);
					/*System.out.println(eElement.getElementsByTagName("Ccy").item(0).getTextContent() + " : "
							+ eElement.getElementsByTagName("Amt").item(0).getTextContent());*/
					resultString=resultString+eElement.getElementsByTagName("Ccy").item(0).getTextContent();
					resultString=resultString+"|";
					resultString=resultString+eElement.getElementsByTagName("Amt").item(0).getTextContent();
					resultString=resultString+"|";
					Element eElement1 = (Element) ccyAmt.getElementsByTagName("CcyAmt").item(1);
					resultString=resultString+eElement1.getElementsByTagName("Ccy").item(0).getTextContent();
					resultString=resultString+"|";
					resultString=resultString+eElement1.getElementsByTagName("Amt").item(0).getTextContent();
					resultString=resultString+"|";
					/*System.out.println(eElement1.getElementsByTagName("Ccy").item(0).getTextContent() + " : "
							+ eElement1.getElementsByTagName("Amt").item(0).getTextContent());*/
					result.add(resultString);
					resultString = "";
				}

			}
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
		return result;
	}
	
	public static String[][] getList(String xml) {
		String[][] result;
		String resultString = "";
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
