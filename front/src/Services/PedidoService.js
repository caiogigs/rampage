import CrudService from "./CRUDService";

class PedidoService extends CrudService {
    static _instance = null;
    constructor(){
        super("/")

        if(PedidoService._instance)
            return PedidoService._instance;

        PedidoService._instance = this;
    }

    finalizarPedido = (order) => {
        this.doPost("", order);
    }
}

const pedidoService = new PedidoService();
export default pedidoService;