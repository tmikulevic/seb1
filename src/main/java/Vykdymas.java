import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vykdymas {
	public static void main(String[] args) throws SQLException {
		
		
		//DBValiutos.uzpildytiValiutosTable();
		//DBSantykiai.uzpildytiSantykiaiTable();
		
		DBsasaja db = DBsasaja.getInstance();
		db.openConn();
		ResultSet result = DBValiutos.executeSelectAll();
		//ResultSet result = DBValiutos.getCurrency("EUR");
		
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

		
		System.out.println("Pasirink valiutos id: ");
		Scanner sc = new Scanner(System.in);
		Double santykis;
		santykis = DBSantykiai.getRates(sc.nextInt());
		System.out.println("Irasyk suma eurais: ");
		Scanner sc2 = new Scanner(System.in);
		Double eurais = sc2.nextDouble();
		System.out.println(eurais + " eurais yra " + santykis * eurais + " kita valiuta ");
		
		db.closeConn();
	}
}
