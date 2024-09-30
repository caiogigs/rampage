package br.com.rampagestore.model;

public class ProductResponse {
    private ProductObj product;
    private String imageDirection;

    public ProductResponse(ProductObj product, String imageDirection) {
        this.product = product;
        this.imageDirection = imageDirection;
    }

    // Getters e Setters
    public ProductObj getProduct() {
        return product;
    }

    public String getImageDirection() {
        return imageDirection;
    }
}
