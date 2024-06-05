import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.ProductsController;
import dao.ProductsDAO;
import database.PolyBayDatabase;
import models.Product;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            WebServer webserver = new WebServer();
            webserver.listen(8080);
            webserver.getRouter().get(
                "/products", 
                (WebServerContext context) -> { ProductsController.findAll(context); }
            );
            webserver.getRouter().post(
                "/products/bid/:productId",
                (WebServerContext context) -> { ProductsController.bid(context); }
            );
        } 
        catch (Exception e) {}
    }
}    
