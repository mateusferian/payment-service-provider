package br.com.geradordedevs.paymentserviceprovider.facades;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;

import java.util.List;

public interface TransactionsFacade {

    List<TransactionsResponseDTO> findAll();

    TransactionsResponseDTO save(TransactionsRequestDTO request);


}
