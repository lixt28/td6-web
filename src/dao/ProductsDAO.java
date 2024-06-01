package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyBayDatabase;
import models.Product;

public class ProductsDAO {
    public ArrayList<Product> findAll() {
        ArrayList listOfProducts = new ArrayList<>();
        try {
            PolyBayDatabase connexion = new PolyBayDatabase();
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM product");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String owner = results.getString("owner");
                float bid = results.getFloat("bid");
                Product newProduct = new Product(id, name, owner, bid);
                listOfProducts.add(newProduct);
            }
        }
        catch (SQLException e){}
        return listOfProducts;
    }

    public boolean bid(int productId) {
        try {
            PolyBayDatabase connexion = new PolyBayDatabase();
            PreparedStatement statement = connexion.prepareStatement("UPDATE product SET bid = bid + 50 WHERE id = ?");
            statement.setInt(1, productId);
            int success = statement.executeUpdate();
            return success > 0;
        }
        catch (SQLException e){ return false; }
    }
}