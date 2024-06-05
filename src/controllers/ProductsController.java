package controllers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import dao.ProductsDAO;
import database.PolyBayDatabase;
import models.Product;
import webserver.WebServerContext;
import webserver.WebServerResponse;

public class ProductsController {
    public static void findAll(WebServerContext context) {
        ProductsDAO productsDAO = new ProductsDAO();
        ArrayList<Product> listOfProducts = productsDAO.findAll();
        context.getResponse().json(listOfProducts);
    }

    public static void bid(WebServerContext context) {
        ProductsDAO productsDAO = new ProductsDAO();
        String idFromRequest = context.getRequest().getParam("productId");
        int productId = Integer.parseInt(idFromRequest);
        boolean result = productsDAO.bid(productId);

        if (result) {
            Product product = null;
            float newBid = 0;
            try {
                PolyBayDatabase connexion = new PolyBayDatabase();
                PreparedStatement statement = connexion.prepareStatement("SELECT * FROM product");
                ResultSet results = statement.executeQuery();

                while(results.next()) {
                    int id = results.getInt("id");
                    String name = results.getString("name");
                    String owner = results.getString("owner");
                    float bid = results.getFloat("bid");
                    if (id == productId) {
                        product = new Product(id, name, owner, bid);
                        newBid = product.bid();
                        break;

                    }
                }
            }
            catch (SQLException e){}
            
            // PAS LE DROIT DE PRENDRE SUR FINDALL 
            // FAIRE UNE METHODE FINDONE OU 

            Gson g = new Gson();
            String sseMessage = g.toJson(product);
            context.getSSE().emit("bids", sseMessage);
            context.getResponse().json(newBid);
        } else {
            context.getResponse().serverError("Failed to update bid.");
        }
    }
}