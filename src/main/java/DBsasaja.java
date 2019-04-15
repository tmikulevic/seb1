import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBsasaja {
	private static DBsasaja INSTANCE = null;

	// Database connection
	private static final String url = "jdbc:h2:./DB";
	private static final String user = "sa";
	private static final String passwd = "no";

	private Connection conn;

	private DBsasaja() {
	}

	public static synchronized DBsasaja getInstance() {

		if (null == INSTANCE) {
			INSTANCE = new DBsasaja();
		}
		return INSTANCE;
	}

	public Connection getConn() {
		return conn;
	}

	public void openConn() {
		//Class.forName("org.h2.Driver");
		try {
			conn = DriverManager.getConnection(url, user, passwd);

			conn.setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
