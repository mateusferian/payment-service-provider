package br.com.geradordedevs.paymentserviceprovider.mappers;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.services.PayableService;
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

    @Autowired
    private  final PayableService payableService;

    private static final BigDecimal CREDIT_RATE= new BigDecimal("0.05");
    private static final BigDecimal DEBIT_RATE = new BigDecimal("0.03");

    public TransactionsResponseDTO toDto(TransactionsEntity entity){
        log.info("converting entity{} to dto", entity);

        log.info("adding payable field {}", entity);
        return  mapper.map(entity, TransactionsResponseDTO.class);
    }


    public TransactionsEntity toEntity(TransactionsRequestDTO request){
        log.info("converting dto{} to entity", request);

         BigDecimal CREDIT_RATE= new BigDecimal("0.05");
         BigDecimal DEBIT_RATE = new BigDecimal("0.03");

        TransactionsEntity transactionsEntity = mapper.map(request,TransactionsEntity.class);
        PayableEntity payableEntity = payableService.savePayable(transactionsEntity.getPaymentMethod());

        transactionsEntity.setPayables(payableEntity);

        if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.CREDIT_CARD) {
                BigDecimal CREDIT_DISCOUNT = transactionsEntity.getTransactionAmount().multiply(CREDIT_RATE);//encontrando 5% do valor
                transactionsEntity.setTransactionAmount(transactionsEntity.getTransactionAmount().subtract(CREDIT_DISCOUNT));//valor - 5

        }

        if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.DEBIT_CARD) {
                BigDecimal DEBIT_DISCOUNT = transactionsEntity.getTransactionAmount().multiply(DEBIT_RATE);//encontrando 3% do valor
                transactionsEntity.setTransactionAmount(transactionsEntity.getTransactionAmount().subtract(DEBIT_DISCOUNT));

        }

        return  transactionsEntity;
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
