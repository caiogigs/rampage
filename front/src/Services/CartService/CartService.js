class CartService {
  _instance = null;
  _cart;
  _products = [];
  _freight; // frete
  _objCart;

  constructor() {
    if (CartService._instance) {
      return CartService._instance;
    }

    CartService._instance = this;
    this._cart = this.getCart();
    this._products = this._cart?.products ? this._cart.products : [];
    this._freight = this._cart?.freight ? this._cart.freight : new Freight();
    this._objCart = new Cart(this._products, this._freight);
  }

  getCart() {
    return JSON.parse(localStorage.getItem("cart"));
  }

  getItems() {
    return this._products;
  }

  addItem(product) {
    const item = this._products.find((p) => p.id === product.id);
    if (item) item.quantityOrdered += product.quantityOrdered;
    else this._products.push(product);
    alert("Produto adicionado com sucesso.");
    this.saveCart();
  }

  removeItem(product) {
    const indexToRemove = this._products.findIndex(
      (item) => item.id === product.id
    );
    if (indexToRemove !== -1) {
      this._products.splice(indexToRemove, 1);

      this.saveCart();
    }
  }

  getFreight() {
    return this._freight;
  }

  setFreight(freight) {
    this._freight = freight;
    this.saveCart();
  }

  decreaseQuantity(product) {
    const item = this._products.find((p) => p.id === product.id);
    if (item && item.quantityOrdered > 1) {
      item.quantityOrdered -= 1;
      if (item.quantityOrdered === 0) {
        this.removeItem(item);
      }
      this.saveCart();
    }
  }

  getTotalPrice() {
    let total = this._products.reduce((total, product) => {
      total += product.productPrice * product.quantityOrdered;
      return total;
    }, 0);

    if (this._freight?.price) total += this._freight.price;
    
    return total;
  }

  getSubTotalPrice() {
    let total = this._products.reduce((total, product) => {
      total += product.productPrice * product.quantityOrdered;
      return total;
    }, 0);

    return total;
  }

  async _updateCartObj() {
    this._objCart.products = this._products;
    this._objCart.freight = this._freight;
  }

  saveCart = async () => {
    await this._updateCartObj().then(() => {
      localStorage.setItem("cart", JSON.stringify(this._objCart));      
    });
  };

  cleanCart() {
    localStorage.removeItem("cart");
  }
}

class Cart {
  products = [];
  freight = {};

  constructor(productsArray, freight) {
    this.products = productsArray;
    this.freight = freight;
  }
}

class Freight {
  deliveryTime = "";
  price = 0;

  constructor(deliveryTime, price) {
    this.deliveryTime = deliveryTime;
    this.price = price;
  }
}

const cartService = new CartService();
export default cartService;
