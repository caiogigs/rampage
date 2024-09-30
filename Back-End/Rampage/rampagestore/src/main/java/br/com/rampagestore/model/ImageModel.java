package br.com.rampagestore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idProduto;
    private String originalName;
    private String direction;
    private boolean mainImage;
    private boolean status;
    
    public ImageModel() {
    }


    public ImageModel(long idProduto, String originalName, String direction, boolean mainImage, boolean status) {
        this.idProduto = idProduto;
        this.originalName = originalName;
        this.direction = direction;
        this.mainImage = mainImage;
        this.status = status;
    }

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }
    public String getOriginalName() {
        return originalName;
    }
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public boolean isMainImage() {
        return mainImage;
    }
    public void setMainImage(boolean mainImage) {
        this.mainImage = mainImage;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    


}
