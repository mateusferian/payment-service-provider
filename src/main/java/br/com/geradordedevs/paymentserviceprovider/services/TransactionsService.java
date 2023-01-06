package br.com.geradordedevs.paymentserviceprovider.services;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;

public interface TransactionsService {
    Iterable<TransactionsEntity> findAll();
    TransactionsEntity save(TransactionsEntity entity);
}
