package br.com.geradordedevs.paymentserviceprovider.dtos.requests;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Size(min = 2 ,max = 40, message = "{size.invalid}")
    private String cardNumber;

    @NotBlank(message = "{white.field}")
    @Size(min = 2 ,max = 30, message = "{size.invalid}")
    private String bearerName;

    @NotBlank(message = "{white.field}")
    @Size(min = 5 ,max = 5, message = "{size.invalid}")
    private String cardExpirationDate;

    @NotNull(message = "{null.field}")
    @Max(value = 999, message = "{transaction.above.the.maximum}")
    private  int cvv;

}
