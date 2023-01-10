package br.com.geradordedevs.paymentserviceprovider.services;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;

import java.util.List;

public interface TransactionsService {
    List<TransactionsEntity> findAllByName(String name);
    TransactionsEntity save(TransactionsEntity entity);

    List<TransactionsEntity> findByBalanceByName(String name);
}
