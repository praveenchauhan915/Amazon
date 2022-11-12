package Amazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseOps {
	Connection conn;

	public void initConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AmazonProducts", "root", "root");

	}

	public void readProductsFromDb() throws ClassNotFoundException, SQLException {

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from amazonproducts_info");

		while (rs.next()) {
			System.out.println(rs.getString("productname"));
			System.out.println(rs.getInt("productid"));
			System.out.println(rs.getInt("productcost"));

		}
		conn.close();
	}

	public void insertProductsInDb(List<Product> products) throws ClassNotFoundException, SQLException {
		Statement stmt = conn.createStatement();

		Product p;
		for (int i = 0; i < products.size(); i++) {
			p = products.get(i);
			String sql = "INSERT INTO amazonproducts_info VALUES ('" + p.productName + "', 123, " + p.productPrice
					+ ")";
			stmt.executeUpdate(sql);

		}
	}


}
