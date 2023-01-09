package br.com.geradordedevs.paymentserviceprovider.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDTO {

    private  String bearerName;
    private BigDecimal available;
    private BigDecimal waitingFunds;
}
