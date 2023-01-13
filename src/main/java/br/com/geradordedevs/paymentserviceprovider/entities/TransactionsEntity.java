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

    @OneToOne
    private PayableEntity payables;

    public TransactionsEntity(BigDecimal transactionAmount, String transactionDescription, PaymentMethodEnum paymentMethod, String cardNumber, String name, String cardExpirationDate, int cvv, PayableEntity payables) {
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.name = name;
        this.cardExpirationDate = cardExpirationDate;
        this.cvv = cvv;
        this.payables = payables;
    }

    @Override
    public String toString() {
        return "TransactionsEntity{" +
                "id=" + id +
                ", transactionAmount=" + transactionAmount +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", cardExpirationDate='" + cardExpirationDate + '\'' +
                ", payables=" + payables +
                '}';
    }
}
