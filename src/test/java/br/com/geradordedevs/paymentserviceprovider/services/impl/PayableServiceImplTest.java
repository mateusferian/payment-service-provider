package br.com.geradordedevs.paymentserviceprovider.services.impl;

import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.repositories.PayableRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PayableServiceImplTest {

    @InjectMocks
    private PayableServiceImpl payableService;

    @Mock
    private PayableRepository payableRepository;

    @Before
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        when(payableRepository.save(returnObjectPayableEntityMethodDebit())).thenReturn(returnObjectPayableEntityMethodDebit());
        when(payableRepository.save(returnObjectPayableEntityMethodCredit())).thenReturn(returnObjectPayableEntityMethodCredit());
    }

    @Test
    public void savePayableWithCardOnDebitMethod() {
        assertEquals(returnObjectPayableEntityMethodDebit(), payableService.savePayable(PaymentMethodEnum.DEBIT_CARD));
    }

    @Test
    public void savePayableWithCardOnCreditMethod() {
        assertEquals(returnObjectPayableEntityMethodCredit(), payableService.savePayable(PaymentMethodEnum.CREDIT_CARD));
    }

    private PayableEntity returnObjectPayableEntityMethodDebit(){
        return new PayableEntity(PaymentMethodEnum.DEBIT_CARD.getStatus(), LocalDate.now());
    }

    private PayableEntity returnObjectPayableEntityMethodCredit(){
        return new PayableEntity(PaymentMethodEnum.CREDIT_CARD.getStatus(),LocalDate.now().plusDays(30));
    }
}

