package br.com.geradordedevs.paymentserviceprovider.dtos.requests;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsRequestDTO {

    @NotNull(message = "{null.field}")
    @Min(value = 10, message = "{transaction.below.the.minimum}")
    private BigDecimal transactionAmount;

    @NotBlank(message = "{white.field}")
    @Size(min = 30 ,max = 100, message = "{size.invalid}")
    private  String transactionDescription;

    @NotNull(message = "{null.field}")
    private PaymentMethodEnum paymentMethod;

    @NotBlank(message = "{white.field}")
    @Size(min = 19 ,max = 19, message = "{transaction.card.number}")
    private String cardNumber;

    @NotBlank(message = "{white.field}")
    @Size(min = 3 ,max = 30, message = "{size.invalid}")
    private String name;

    @NotBlank(message = "{white.field}")
    @Size(min = 5 ,max = 5, message = "{Invalid.card.expiration.date}")
    private String cardExpirationDate;

    @Max(value = 999, message = "{transaction.above.the.maximum}")
    @Min(value = 1, message = "{transaction.above.the.minimum}")
    private  int cvv;

    @Override
    public String toString() {
        return "TransactionsRequestDTO{" +
                "transactionAmount=" + transactionAmount +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", cardExpirationDate='" + cardExpirationDate + '\'' +
                '}';
    }
}
