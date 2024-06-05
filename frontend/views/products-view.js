import { ProductsService } from "./../services/products-service.js";

export class ProductsView {
    constructor(){}

    async displayProducts() {
        try {
            const products = await ProductsService.findAll();
            products.forEach(product => this.displayProduct(product));
        } catch (error) {}
    }

    displayProduct(product) {
        const productsDiv = document.querySelector('.products');
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');
        productDiv.dataset.productId = product.id;
        productDiv.innerHTML = `
            ${product.name} ${product.owner} <span class="bid-value">${product.bid.toFixed(2)}</span> € 
            <button class="bid-btn" data-product-id="${product.id}">Enchérir</button>
        `;
        productsDiv.appendChild(productDiv);
        productDiv.querySelector('.bid-btn').addEventListener('click', async () => {
            const newBid = await ProductsService.bid(product.id);
            if (newBid !== null) {
                productDiv.querySelector('.bid-value').textContent = newBid.toFixed(2);
            }
        });
    }

    updateBid(data) {
        console.log(data);
        const productDiv = document.querySelector(`.product[data-product-id="${data.id}"]`);
        if (productDiv) {
            productDiv.querySelector('.bid-value').textContent = data.bid.toFixed(2);
        }
    }


}