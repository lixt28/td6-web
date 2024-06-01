import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductsDAO;
import database.PolyBayDatabase;
import models.Product;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ProductsDAO productsDAO = new ProductsDAO();
            ArrayList<Product> listOfProducts = productsDAO.findAll();

            for (Product product : listOfProducts) {
                System.out.println(product);
            }
        }
        catch (Exception e) {}
    }
}    
