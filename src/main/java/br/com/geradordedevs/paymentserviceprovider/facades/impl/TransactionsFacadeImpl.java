package br.com.geradordedevs.paymentserviceprovider.facades.impl;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.facades.TransactionsFacade;
import br.com.geradordedevs.paymentserviceprovider.mappers.TransactionsMapper;
import br.com.geradordedevs.paymentserviceprovider.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<TransactionsResponseDTO> findAll() {
        return mapper.toDtoList(transactionsService.findAll());
    }

    @Override
    public TransactionsResponseDTO save(TransactionsRequestDTO request) {
         request.setCardNumber(hideNumber(request.getCardNumber()));
        return mapper.toDto(transactionsService.save(mapper.toEntity(request)));
    }

    public  String hideNumber(String num ){
        REDUCED_NUMBER = num.substring(num.length() -4);
        return   HIDDEN_CARD_NUMBER+REDUCED_NUMBER;
    }
}
