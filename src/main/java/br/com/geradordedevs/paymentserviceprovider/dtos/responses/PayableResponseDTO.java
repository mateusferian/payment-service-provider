package br.com.geradordedevs.paymentserviceprovider.dtos.responses;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayableResponseDTO {

    private  Long id;

    private PaymentMethodEnum status;
    private Instant TransactionCreationDate;

    private String paymentDate;

    private  double fee;
}