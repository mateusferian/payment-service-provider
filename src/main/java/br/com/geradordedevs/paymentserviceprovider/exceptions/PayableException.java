package br.com.geradordedevs.paymentserviceprovider.exceptions;

import br.com.geradordedevs.paymentserviceprovider.exceptions.enums.PayableEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class PayableException extends  PayableServiceProviderException{

    public PayableException (PayableEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private  final  PayableEnum error;
}
