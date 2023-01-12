package br.com.geradordedevs.paymentserviceprovider.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayableResponseDTO {


    private String status;

    private LocalDate paymentDate;

}
