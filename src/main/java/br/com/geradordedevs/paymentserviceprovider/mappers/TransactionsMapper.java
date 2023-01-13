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

import java.math.BigDecimal;

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
        return  mapper.map(entity, TransactionsResponseDTO.class);
    }

    public TransactionsEntity toEntity(TransactionsRequestDTO request){
        log.info("converting dto{} to entity", request);

        TransactionsEntity transactionsEntity = mapper.map(request,TransactionsEntity.class);
        PayableEntity payableEntity = payableService.savePayable(transactionsEntity.getPaymentMethod());
        transactionsEntity.setPayables(payableEntity);

        if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.CREDIT_CARD) {
            BigDecimal creditDiscount = transactionsEntity.getTransactionAmount().multiply(CREDIT_RATE);//encontrando 5% do valor
            transactionsEntity.setTransactionAmount(transactionsEntity.getTransactionAmount().subtract(creditDiscount));//valor - 5
        }

        if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.DEBIT_CARD) {
            BigDecimal debitDiscount = transactionsEntity.getTransactionAmount().multiply(DEBIT_RATE);//encontrando 3% do valor
            transactionsEntity.setTransactionAmount(transactionsEntity.getTransactionAmount().subtract(debitDiscount));
        }

        return  transactionsEntity;
    }
}
