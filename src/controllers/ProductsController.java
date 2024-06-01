package controllers;
import java.util.ArrayList;

import dao.ProductsDAO;
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
            ArrayList<Product> listOfProducts = productsDAO.findAll();
            float newBid = 0;
            for (Product product : listOfProducts) {
                if (product.getId() == productId) {
                    newBid = product.getBid();
                    break;
                }
            }
            context.getResponse().json(newBid);
        } else {
            context.getResponse().serverError("Failed to update bid.");
        }
    }
}