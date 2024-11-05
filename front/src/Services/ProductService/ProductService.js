import CrudService from "../CRUDService";

class ProductService extends CrudService{

    static instance = null;

    constructor(){
        super("");

        if (ProductService.instance)
            return ProductService.instance; 
        ProductService.instance = this;
    }

    getProductByID = async (productId) => {
        return this.listById("/product-by-id", productId)
        .catch((err) => {
            window.alert("Erro ao buscar Produto.");
            console.error(err);
        });
    }

}

const productService = new ProductService();
export default productService;