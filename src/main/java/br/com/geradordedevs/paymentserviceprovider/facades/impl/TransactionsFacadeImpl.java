package br.com.geradordedevs.paymentserviceprovider.facades.impl;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.BalanceResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.facades.TransactionsFacade;
import br.com.geradordedevs.paymentserviceprovider.mappers.TransactionsMapper;
import br.com.geradordedevs.paymentserviceprovider.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionsFacadeImpl implements TransactionsFacade {

    @Autowired
    private TransactionsMapper mapper;

    @Autowired
    private TransactionsService transactionsService;

    public static final String HIDDEN_CARD_NUMBER="****.***.";

    public  static String REDUCED_NUMBER = " ";

    @Override
    public List<TransactionsResponseDTO> findAllByName(String name) {
        List<TransactionsResponseDTO> transactionsResponseDTOS = new ArrayList<>();
         transactionsService.findAllByName(name).forEach(transactionsEntity -> transactionsResponseDTOS.add(mapper.toDto(transactionsEntity)));
        return transactionsResponseDTOS;
    }

    @Override
    public TransactionsResponseDTO save(TransactionsRequestDTO request) {
         request.setCardNumber(hideNumber(request.getCardNumber()));
        return mapper.toDto(transactionsService.save(mapper.toEntity(request)));
    }

    @Override
    public BalanceResponseDTO findByBalanceByName(String name) {
        BigDecimal waitingFunds = BigDecimal.ZERO;
        BigDecimal available= BigDecimal.ZERO;

        BalanceResponseDTO balanceResponseDTO = new BalanceResponseDTO();
        for (TransactionsEntity transactionsEntity: transactionsService.findByBalanceByName(name)) {
            balanceResponseDTO.setBearerName(transactionsEntity.getName());

            if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.CREDIT_CARD) {
                waitingFunds = waitingFunds.add(transactionsEntity.getTransactionAmount());
            }

            if (transactionsEntity.getPaymentMethod() == PaymentMethodEnum.DEBIT_CARD) {
                available = available.add(transactionsEntity.getTransactionAmount());
            }
        }

        balanceResponseDTO.setWaitingFunds(waitingFunds);
        balanceResponseDTO.setAvailable(available);

        return balanceResponseDTO;
    }

    public  String hideNumber(String num ){
        REDUCED_NUMBER = num.substring(num.length() -4);
        return HIDDEN_CARD_NUMBER+REDUCED_NUMBER;
    }
}
