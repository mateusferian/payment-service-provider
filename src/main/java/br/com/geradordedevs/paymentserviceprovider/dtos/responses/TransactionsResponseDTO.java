package br.com.geradordedevs.paymentserviceprovider.dtos.responses;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsResponseDTO {
    private Long id;

    private BigDecimal transactionAmount;

    private  String transactionDescription;

    private PaymentMethodEnum paymentMethod;

    private String cardNumber;

    private String name;

    private String cardExpirationDate;

    private PayableResponseDTO payables;
}
