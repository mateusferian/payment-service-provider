package br.com.geradordedevs.paymentserviceprovider.entities;

import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal transactionAmount;

    private  String transactionDescription;

    private PaymentMethodEnum  paymentMethod;

    private String cardNumber;

    private String name;

    private String cardExpirationDate;

    private  int cvv;

    @ManyToOne
    private PayableEntity payables;
}
