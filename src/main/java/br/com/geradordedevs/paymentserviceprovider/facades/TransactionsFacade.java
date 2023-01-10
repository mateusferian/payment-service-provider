package br.com.geradordedevs.paymentserviceprovider.facades;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.BalanceResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionsFacade {

    List<TransactionsResponseDTO> findAllByName(String name);
    TransactionsResponseDTO save(TransactionsRequestDTO request);
    BalanceResponseDTO findByBalanceByName(String name);

}
