package br.com.geradordedevs.paymentserviceprovider.exceptions;

import br.com.geradordedevs.paymentserviceprovider.exceptions.enums.UserEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserException extends  PayableServiceProviderException{

    public UserException(UserEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private  final UserEnum error;
}
