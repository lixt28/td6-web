export class ProductsService {
    static async findAll() {
        const response = await fetch("http://localhost:8080/products");

        if(response.status === 200){
            const data = await response.json();
            return data;
        }
    }

    static async bid(productId) {
        const response = await fetch(`http://localhost:8080/products/bid/${productId}`, {
            method: 'POST'
        });

        if(response.status === 200){
            const data = await response.json();
            return data;
        } else { 
            return null;
        }
    }
}

