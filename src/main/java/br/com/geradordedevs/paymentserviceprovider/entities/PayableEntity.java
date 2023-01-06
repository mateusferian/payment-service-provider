package br.com.geradordedevs.paymentserviceprovider.entities;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private PaymentMethodEnum status;
    private Instant TransactionCreationDate;
    private String paymentDate;
    private  double fee;
}
