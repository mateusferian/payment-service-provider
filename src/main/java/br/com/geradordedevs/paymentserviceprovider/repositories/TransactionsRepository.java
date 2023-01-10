package br.com.geradordedevs.paymentserviceprovider.repositories;

import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends CrudRepository<TransactionsEntity,Long> {

    List<TransactionsEntity> findByNameContaining (String name);
}
