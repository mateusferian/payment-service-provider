package br.com.geradordedevs.paymentserviceprovider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentMethodEnum {

    DEBIT_CARD("paid"),
    CREDIT_CARD("waiting funds");

    private String status;

}
