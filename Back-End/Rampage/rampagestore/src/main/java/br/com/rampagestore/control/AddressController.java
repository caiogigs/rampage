package br.com.rampagestore.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.model.user.UserAddress;
import br.com.rampagestore.service.AddressService;
import jakarta.validation.Valid;

@RestController
public class AddressController {

    
    @Autowired
    private AddressService addressService;

    @GetMapping("selecionar_endereço_faturamento/{id}")//id do cliente
    public ResponseEntity<?> selectBillingAddress(@PathVariable Long id){
        return addressService.selectBillingdAddres(id);
    }

    //Seleciona todos os endereços de entrega
    @GetMapping("selecionar_endereços/{id}")//id do cliente
    public ResponseEntity<?> selectAllAddress(@PathVariable Long id){
        return addressService.selectAllDeliveryAdrress(id);
    }


    @PutMapping("/indicate_billing_address/{id}")//id do endereço q sera alterado
    public ResponseEntity<?> indicateBillingAddress(@PathVariable Long id){
        return addressService.selectBillingdAddres(id);
    }

    //Indicar endereço padrão de entrega
    @PutMapping("/indicate-standard-addres/{id}")//id do endereço q sera alterado 
    public ResponseEntity<?> indicateStandardAddres(@PathVariable Long id) {
        return addressService.selectStandardAddres(id);    
    }

    //Registrar novo Endereço
    @PostMapping("/register_Addres/{id}")//id co cliente
    public ResponseEntity<?> registerAddres(@PathVariable Long id, @RequestBody @Valid UserAddress userAddress) {
        return addressService.registerNewAddres(userAddress, id);
    }

}
