package br.com.geradordedevs.paymentserviceprovider.services.impl;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.repositories.TransactionsRepository;
import br.com.geradordedevs.paymentserviceprovider.services.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public List<TransactionsEntity> findAllByName(String name){
        log.info("listing transactions");
        return transactionsRepository.findByNameContaining(name);
    }

    @Override
    public TransactionsEntity save(TransactionsEntity entity) {
        log.info("registering a new transactions {}",entity);
        return transactionsRepository.save(entity);
    }

    @Override
    public List<TransactionsEntity> findByBalanceByName(String name) {
        return transactionsRepository.findByNameContaining(name);
    }
}
