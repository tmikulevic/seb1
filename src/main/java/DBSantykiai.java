import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSantykiai {
	
	private static final String SELECT_ALL = "SELECT * FROM SANTYKIAI";
	private static final String SELECT_RATES = "SELECT REIKSME2 FROM SANTYKIAI WHERE ID2 = ?";

	private static final String INSERT = "INSERT INTO SANTYKIAI (ID1, REIKSME1, ID2, REIKSME2) "
			+ "VALUES (?, ?, ?, ?)";
	
	private static PreparedStatement statement;
	
	public static ResultSet executeSelectAll() {
		try {
			statement = DBsasaja.getInstance().getConn().prepareStatement(SELECT_ALL);

			ResultSet result = statement.executeQuery();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Double getRates(int id2) {
		
		try {
			statement = DBsasaja.getInstance().getConn().prepareStatement(SELECT_RATES);
			statement.setInt(1, id2);
			ResultSet result = statement.executeQuery();
			result.next();
			
			return result.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void uzpildytiSantykiaiTable() {
		DBsasaja db = DBsasaja.getInstance();
		db.openConn();
		String[][] bandymas = ParseXML.getRates(Currency.GetCurrentFxRates());
		try {
			for (int row = 0; row < bandymas.length; row++) {

				ResultSet result = DBValiutos.getCurrency(bandymas[row][0]);
				result.next();
				result.getInt(1);
				fillStatementInsert(53, 1, result.getInt(1), Double.valueOf(bandymas[row][1]));
			}

			db.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConn();
	}
	
	public static void fillStatementInsert(int id1, int reiksme1, int id2, double reiksme2) {

		int indexState = 1;

		try {
			statement = DBsasaja.getInstance().getConn().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(indexState++, id1);
			statement.setInt(indexState++, reiksme1);
			statement.setInt(indexState++, id2);
			statement.setDouble(indexState++, reiksme2);

			statement.execute();

			System.out.println("done");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
