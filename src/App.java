import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductsDAO;
import database.PolyBayDatabase;
import models.Product;
import webserver.WebServer;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            WebServer webserver = new WebServer();
            webserver.listen(8080);
        }
        catch (Exception e) {}
    }
}    
