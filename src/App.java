import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.PolyBayDatabase;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            PolyBayDatabase connexion = new PolyBayDatabase();
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM product");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String owner = results.getString("owner");
                float bid = results.getFloat("bid");
                System.out.println("id : " + id + ", name : " + name + ", owner : " + owner + ", bid : " + bid);
            }
        }
        catch (SQLException e){}
    }
}
