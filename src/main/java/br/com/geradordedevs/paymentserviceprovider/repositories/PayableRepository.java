package br.com.geradordedevs.paymentserviceprovider.repositories;

import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import org.springframework.data.repository.CrudRepository;

public interface PayableRepository extends CrudRepository<PayableEntity,Long> {
}
