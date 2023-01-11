package br.com.geradordedevs.paymentserviceprovider.exceptions;

import br.com.geradordedevs.paymentserviceprovider.exceptions.enums.ClientEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ClientException extends  PayableServiceProviderException{

    public ClientException(ClientEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private  final ClientEnum error;
}
