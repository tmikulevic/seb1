import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBValiutos {
	private static final String SELECT_BY_ID = "SELECT * FROM VALIUTOS WHERE ID = ?";
	  private static final String SELECT_ALL = "SELECT * FROM VALIUTOS";
	  private static final String SELECT_CURR = "SELECT * FROM VALIUTOS WHERE VALIUTOS_KODAS = ?";
	  
	  private static final String INSERT = "INSERT INTO VALIUTOS (ID, VALIUTOS_KODAS, VALIUTA_LT, VALIUTA_EN) "
	      + "VALUES (?, ?, ?, ?)";
	  
	  private static final String UPDATE = "UPDATE VALIUTOS SET VALIUTOS_KODAS=?, VALIUTA_LT=?, VALIUTA_EN=? WHERE ID = ?";
	  
	  private static final String DELETE = "DELETE FROM VALIUTOS WHERE ID = ?";
	  
	  static int indexState;
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
	  
	  public static ResultSet executeSelectId(String id) {
		  try {
		      statement = DBsasaja.getInstance().getConn().prepareStatement(SELECT_BY_ID);

		      statement = fillStatementSelect(statement, id);

		      ResultSet result = statement.executeQuery();

		      
		      return result;

		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		return null;
	  }
	  
	  public static PreparedStatement fillStatementSelect(PreparedStatement statement, String id) {
		    
		    if (null == id || id.equals("")) {
		      id = "-1";
		    }

		    try {

		      statement.setInt(1, Integer.parseInt(id));

		    } catch (NumberFormatException | SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return statement;
		  }
	  
	  public static void fillStatementInsert(int id, String valiutosKodas,
			  String valiutaLT, String valiutaEN) {
	    
	    indexState = 1;
	    
	    try {
	        statement = DBsasaja.getInstance().getConn().prepareStatement(INSERT,
	            Statement.RETURN_GENERATED_KEYS);

	        statement.setInt(indexState++, id);//todo
	        statement.setString(indexState++, valiutosKodas);
	        statement.setString(indexState++, valiutaLT);
	        statement.setString(indexState++, valiutaEN);

	        statement.execute();
	       
	        System.out.println("done: "+valiutosKodas);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	  }
	  
	  public static void uzpildytiValiutosTable() {
		  DBsasaja db = DBsasaja.getInstance();
			db.openConn();
			
			String[][] bandymas = ParseXML.getList(Currency.GetCurrencyList());
			for(int row = 0; row<bandymas.length;row++) {
				DBValiutos.fillStatementInsert(row, bandymas[row][0], bandymas[row][1], bandymas[row][2]);
			}
			
			try {
				db.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			db.closeConn();
	  }
	  


	/*  public static List<DbTable> getBdlInDbByNature(String nature) {
	    
	    List<DbTable> bdlInDb = new ArrayList<>();

	    try {

	      PreparedStatement state =
	          AkkaDocsConnector.getInstance().getConn().prepareStatement(SELECT_BY_NATURE);
	      state.setString(1, nature);
	      ResultSet rs = state.executeQuery();

	      while (rs.next()) {
	        BDLModel bdlModel = new BDLModel();
	        
	        bdlModel.setBdlId(String.valueOf(rs.getInt("BDL_ID")));
	        bdlModel.setRefBdl(rs.getString("REF"));
	        bdlModel.setClient(rs.getString("CLIENT"));

	        BdlDbTable bdl = new BdlDbTable(bdlModel, true);

	        bdlInDb.add(bdl);
	      }


	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return bdlInDb;
	    
	  }*/
	  
	/*public static String getRefByBdlId(String id) {
	    
	    try {

	      PreparedStatement state =
	          DBsasaja.getInstance().getConn().prepareStatement(SELECT_BY_ID);
	      state.setString(1, id);
	      ResultSet rs = state.executeQuery();

	      while (rs.next()) {

	        return rs.getString("REF");//todo
	        
	      }

	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return null;
	    
	  }*/
}
