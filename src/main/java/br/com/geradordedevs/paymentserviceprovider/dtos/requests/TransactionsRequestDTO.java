package br.com.geradordedevs.paymentserviceprovider.dtos.requests;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsRequestDTO {


    private BigDecimal transactionAmount;

    private  String transactionDescription;

    private PaymentMethodEnum paymentMethod;

    private String cardNumber;

    private String cardExpirationDate;

    private  int cvv;

}
