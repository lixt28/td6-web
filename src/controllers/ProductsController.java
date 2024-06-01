package controllers;
import webserver.WebServerContext;
import webserver.WebServerResponse;

public class ProductsController {
    public static void findAll(WebServerContext context) {
        context.getResponse().ok("Tous les produits");
    }
}
