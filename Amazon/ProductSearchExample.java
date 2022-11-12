package Amazon;

import java.awt.List;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSearchExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		WebDriver driver = new ChromeDriver();

		ProductSearchPage ps = new ProductSearchPage();
		
		ps.setDriver(driver);

		ps.searchAndCaptureProduct("Sony Bravia 55 inches tv");
		
		List products = (List) ps.getProductList();
		
		DatabaseOps dop = new DatabaseOps();
		dop.initConnection();
		dop.insertProductsInDb((java.util.List<Product>) products);
		dop.readProductsFromDb();
	}


	

}
