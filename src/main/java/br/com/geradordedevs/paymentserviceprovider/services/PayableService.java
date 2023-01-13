package br.com.geradordedevs.paymentserviceprovider.services;

import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;

public interface PayableService {

    PayableEntity savePayable(PaymentMethodEnum paymentMethodEnum);
}
