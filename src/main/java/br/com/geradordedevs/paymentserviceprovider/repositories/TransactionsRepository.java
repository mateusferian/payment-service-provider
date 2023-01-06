package br.com.geradordedevs.paymentserviceprovider.repositories;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<TransactionsEntity,Long> {
}
