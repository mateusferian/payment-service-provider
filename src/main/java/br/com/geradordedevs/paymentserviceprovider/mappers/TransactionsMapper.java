package br.com.geradordedevs.paymentserviceprovider.mappers;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionsMapper {

    @Autowired
    private final ModelMapper mapper;
    private static final BigDecimal CREDIT_RATE= new BigDecimal("0.05");
    private static final BigDecimal DEBIT_RATE = new BigDecimal("0.03");

    public TransactionsResponseDTO toDto(TransactionsEntity entity){
        log.info("converting entity{} to dto", entity);

            PayableEntity payableEntity = new PayableEntity();
        if (entity.getPaymentMethod() == PaymentMethodEnum.CREDIT_CARD) {
            log.info("if payment method is {}", entity.getPaymentMethod());
                payableEntity.setId(entity.getId());
                payableEntity.setPaymentDate(LocalDate.now().plusDays(30));
                payableEntity.setStatus(PaymentMethodEnum.CREDIT_CARD.getStatus());
                entity.setPayables(payableEntity);
                    BigDecimal CREDIT_DISCOUNT = entity.getTransactionAmount().multiply(CREDIT_RATE);//encontrando 5% do valor
                entity.setTransactionAmount(entity.getTransactionAmount().subtract(CREDIT_DISCOUNT));//valor - 5
        }
        if (entity.getPaymentMethod() == PaymentMethodEnum.DEBIT_CARD) {
            log.info("if payment method is{}", entity.getPaymentMethod());
                payableEntity.setId(entity.getId());
                payableEntity.setPaymentDate(LocalDate.now());
                payableEntity.setStatus(PaymentMethodEnum.DEBIT_CARD.getStatus());
                entity.setPayables(payableEntity);
                    BigDecimal DEBIT_DISCOUNT = entity.getTransactionAmount().multiply(DEBIT_RATE);//encontrando 3% do valor
                entity.setTransactionAmount(entity.getTransactionAmount().subtract(DEBIT_DISCOUNT)); // valor -3
        }
        log.info("adding payable field {}", entity);
        return  mapper.map(entity, TransactionsResponseDTO.class);
    }


    public TransactionsEntity toEntity(TransactionsRequestDTO request){
        log.info("converting dto{} to entity", request);
        return  mapper.map(request,TransactionsEntity.class);
    }

    public List<TransactionsResponseDTO> toDtoList(Iterable<TransactionsEntity> lista){
        log.info("converting entity list{} to dto list", lista);
        List<TransactionsEntity> resultado = new ArrayList<>();
        lista.forEach(resultado::add);
        return  resultado.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
