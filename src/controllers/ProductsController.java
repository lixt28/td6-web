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
}
