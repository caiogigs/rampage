import CrudService from "./CRUDService";

class PedidoService extends CrudService {
    static _instance = null;
    constructor(){
        super("/rampage/pedido")

        if(PedidoService._instance)
            return PedidoService._instance;

        PedidoService._instance = this;
    }

    finalizarPedido = (order) => {
        return this.doPost("/novo_pedido", order);
    }
}

const pedidoService = new PedidoService();
export default pedidoService;