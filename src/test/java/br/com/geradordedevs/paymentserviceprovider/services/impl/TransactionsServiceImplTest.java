package br.com.geradordedevs.paymentserviceprovider.services.impl;

import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.repositories.TransactionsRepository;
import br.com.geradordedevs.paymentserviceprovider.services.TransactionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionsServiceImplTest {

    @InjectMocks
    private  TransactionsServiceImpl transactionsService;

    @Mock
    private TransactionsRepository transactionsRepository;

    private  final Long MOCK_ID = 1l;
    private final BigDecimal MOCK_TRAMSACTION_AMOUNT = new BigDecimal(100);
    private  final String MOCK_TRANSACTION_DESCRIPTION = "fazendo teste unitario com tudo feito e funcionando corretamente";
    private  final PaymentMethodEnum MOCK_PAYMENT_METHOD_ENUM = PaymentMethodEnum.DEBIT_CARD;
    private  final  String MOCK_CARD_NUMBER = "1234567890123456789";
    private  final  String MOCK_NAME = "carlos";
    private  final  String MOCK_CARD_EXPIRATION_DATE = "10/22";
    private  final  int MOCK_CVV = 888;

    private  final Long MOCK_ID_PAYABLE = 1l;
    private  final  String MOCK_STATUS= PaymentMethodEnum.DEBIT_CARD.getStatus();
    private  final LocalDate MOCK_PAYMENT_DATE= LocalDate.now();

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transactionsRepository.save(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionEntity());
        when(transactionsRepository.findByNameContaining(MOCK_NAME)).thenReturn(returnListAllTransactionEntity());
    }

    @Test
    public void findAllByNameTransactionsMustReturnOk(){
        assertEquals(returnListAllTransactionEntity(),transactionsService.findAllByName(MOCK_NAME));
    }

    @Test
    public void saveTransactionMustReturnOk(){
        assertEquals(returnObjectTransactionEntity(),transactionsService.save(returnObjectTransactionEntity()));
    }

    @Test
    public void findAllBalanceByNameMustReturnOk(){
        assertEquals(returnListAllTransactionEntity(),transactionsService.findAllByName(MOCK_NAME));
    }

    private List<TransactionsEntity> returnListAllTransactionEntity() {
        List<TransactionsEntity> transactionsEntityList = new ArrayList<>();
        transactionsEntityList.add(returnObjectTransactionEntityWithId());

        return transactionsEntityList;
    }

    private TransactionsEntity returnObjectTransactionEntity(){
        return  new TransactionsEntity(MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,
                MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV, returnObjectPayableEntity());
    }

    private PayableEntity returnObjectPayableEntity(){
        return  new PayableEntity(MOCK_STATUS,MOCK_PAYMENT_DATE);
    }

    private TransactionsEntity returnObjectTransactionEntityWithId(){
        return  new TransactionsEntity(MOCK_ID,MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,
                MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV,  returnObjectPayableEntityWithId());
    }

    private PayableEntity returnObjectPayableEntityWithId(){
        return  new PayableEntity(MOCK_ID_PAYABLE,MOCK_STATUS,MOCK_PAYMENT_DATE);
    }
}
