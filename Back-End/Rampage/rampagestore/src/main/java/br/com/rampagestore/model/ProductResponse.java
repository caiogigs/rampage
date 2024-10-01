package br.com.rampagestore.model;

import java.util.List;

public class ProductResponse {
    private ProductObj product;
    private List<String> imageDirection;

    public ProductResponse(ProductObj product,  List<String> imageDirection) {
        this.product = product;
        this.imageDirection = imageDirection;
    }

    // Getters e Setters
    public ProductObj getProduct() {
        return product;
    }

    public List<String> getImageDirection() {
        return imageDirection;
    }
}
