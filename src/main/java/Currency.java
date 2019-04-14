import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Currency {

	public static void GetCurrencyList () {
		String url = "http://www.lb.lt/webservices/FxRates/FxRates.asmx?op=getCurrencyList";
		String xml =
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
				"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" + 
				"  <soap12:Body>" + 
				"    <getCurrencyList xmlns=\"http://www.lb.lt/WebServices/FxRates\" />" + 
				"  </soap12:Body>" + 
				"</soap12:Envelope>";
		
		SendRequest(url, xml);
	  }
	
	public static void GetCurrentFxRates() {
		String url = "http://www.lb.lt/webservices/FxRates/FxRates.asmx?op=getCurrentFxRates";
		String type="EU";
		String xml =
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"+
						"<soap12:Body>"+
						"<getCurrentFxRates xmlns=\"http://www.lb.lt/WebServices/FxRates\">"+
						"<tp>" + type + "</tp>"+
						"</getCurrentFxRates>"+
						"</soap12:Body>"+
						"</soap12:Envelope>";
		
		SendRequest(url, xml);
	  }
	
	private static void SendRequest(String url, String xml) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type","text/xml; charset=utf-8");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			
			wr.writeBytes(xml);
			wr.flush();
			wr.close();
			String responseStatus = con.getResponseMessage();
			System.out.println(responseStatus);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("response:" + response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}