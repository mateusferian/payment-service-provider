package br.com.geradordedevs.paymentserviceprovider.services.impl;

import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.repositories.PayableRepository;
import br.com.geradordedevs.paymentserviceprovider.services.PayableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PayableServiceImpl implements PayableService {

    @Autowired
    private PayableRepository payableRepository;
    @Override
    public PayableEntity savePayable(PaymentMethodEnum paymentMethodEnum) {
            PayableEntity payableEntity = new PayableEntity();
        if (paymentMethodEnum == PaymentMethodEnum.CREDIT_CARD) {
                payableEntity.setPaymentDate(LocalDate.now().plusDays(30));
                payableEntity.setStatus(PaymentMethodEnum.CREDIT_CARD.getStatus());

        }
        if (paymentMethodEnum == PaymentMethodEnum.DEBIT_CARD) {
                payableEntity.setPaymentDate(LocalDate.now());
                payableEntity.setStatus(PaymentMethodEnum.DEBIT_CARD.getStatus());
        }
        return payableRepository.save(payableEntity);
    }
}
