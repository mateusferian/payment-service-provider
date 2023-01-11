package br.com.geradordedevs.paymentserviceprovider.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String status;
    private LocalDate paymentDate;

    public PayableEntity(String status, LocalDate paymentDate) {
        this.status = status;
        this.paymentDate = paymentDate;
    }
}
