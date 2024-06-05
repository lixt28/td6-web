import { ProductsView } from "./views/products-view.js";
import { ProductsService } from "./services/products-service.js";
import { SSEClient } from "./lib/sse-client.js";


function run() {
    const view = new ProductsView;
    view.displayProducts();
    const mySSEClient = new SSEClient("localhost:8080");
    mySSEClient.connect();

    mySSEClient.subscribe("bids", (data) => {
        view.updateBid(data);
    });
}

window.addEventListener("load", run);
