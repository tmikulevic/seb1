import java.util.ArrayList;

public class Vykdymas {
	public static void main(String[] args) {
		
		//System.out.println(Currency.GetCurrencyList());
		
		//System.out.println(Currency.GetCurrentFxRates());
		
		/*ArrayList<String> bandymas = new ArrayList<String>();
		bandymas = ParseXML.getRates(Currency.GetCurrentFxRates());
		for(int i = 0; i<bandymas.size();i++) {
			System.out.println(bandymas.get(i));
		}*/
		
		
		ArrayList<String> bandymas = new ArrayList<String>();
		bandymas = ParseXML.getList(Currency.GetCurrencyList());
		for(int i = 0; i<bandymas.size();i++) {
			System.out.println(bandymas.get(i));
		}
	}
}
