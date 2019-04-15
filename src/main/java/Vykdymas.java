import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vykdymas {
	public static void main(String[] args) throws SQLException {
		
		ArrayList<String> bandymas = new ArrayList<String>();
		bandymas = ParseXML.getRates(Currency.GetCurrentFxRates());
		for(int i = 0; i<bandymas.size();i++) {
			System.out.println(bandymas.get(i));
		}
		//DBValiutos.uzpildytiValiutosTable();
		
		/*DBsasaja db = DBsasaja.getInstance();
		db.openConn();
		
		//ResultSet result = DBValiutos.executeSelectAll();
		ResultSet result = DBValiutos.getCurrency("LTL");
		
	      ResultSetMetaData rsmd = result.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (result.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = result.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
		db.closeConn();*/
	}
}
