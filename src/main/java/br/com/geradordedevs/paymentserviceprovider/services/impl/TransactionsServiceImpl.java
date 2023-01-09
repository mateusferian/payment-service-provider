package br.com.geradordedevs.paymentserviceprovider.services.impl;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.repositories.TransactionsRepository;
import br.com.geradordedevs.paymentserviceprovider.services.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public Iterable<TransactionsEntity> findAll() {
        log.info("listing transactions");
        return transactionsRepository.findAll();
    }

    @Override
    public TransactionsEntity save(TransactionsEntity entity) {
        log.info("registering a new transactions {}",entity);
        return transactionsRepository.save(entity);
    }
}
