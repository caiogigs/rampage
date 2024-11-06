import CrudService from "../CRUDService";

class AddressService extends CrudService {
  static _instance = null;

  constructor() {
    super("/address-controller");

    if (AddressService._instance) return AddressService._instance;

    AddressService._instance = this;
  }


}

const addressService = new AddressService();
export default addressService;
