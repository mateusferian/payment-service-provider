package br.com.geradordedevs.paymentserviceprovider.facades.impl;


import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.PayableResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.PayableEntity;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.facades.impl.TransactionsFacadeImpl;
import br.com.geradordedevs.paymentserviceprovider.mappers.TransactionsMapper;
import br.com.geradordedevs.paymentserviceprovider.repositories.TransactionsRepository;
import br.com.geradordedevs.paymentserviceprovider.services.PayableService;
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
public class TransactionsFacadeImpTest {

    @InjectMocks
    private TransactionsFacadeImpl transactionsFacade;

    @Mock
    private TransactionsService transactionsService;

    @Mock
    private PayableService payableService;

    @Mock
    private TransactionsMapper mapper;


    private  final Long MOCK_ID = 1l;
    private final BigDecimal MOCK_TRAMSACTION_AMOUNT = new BigDecimal(100);
    private  final String MOCK_TRANSACTION_DESCRIPTION = "fazendo teste unitario com tudo feito e funcionando corretamente";
    private  final PaymentMethodEnum MOCK_PAYMENT_METHOD_ENUM = PaymentMethodEnum.DEBIT_CARD;
    private  final  String MOCK_CARD_NUMBER = "****.****.****.6789";
    private  final  String MOCK_NAME = "carlos";
    private  final  String MOCK_CARD_EXPIRATION_DATE = "10/22";
    private  final  int MOCK_CVV = 888;

    private  final Long MOCK_ID_PAYABLE = 1l;
    private  final  String MOCK_STATUS= PaymentMethodEnum.DEBIT_CARD.getStatus();
    private  final  LocalDate MOCK_ID_PAYMENT_DATE= LocalDate.now();

    @Before
    public  void stupMock(){
        MockitoAnnotations.openMocks(this);
        when(transactionsService.save(returnObjectTransactionsEntity())).thenReturn(returnObjectTransactionsEntity());

        when(mapper.toDto(returnObjectTransactionsEntity())).thenReturn(returnObjectTransactionsResponseDTO());
        when(mapper.toEntity(returnObjectTransactionsRequestDTO())).thenReturn(returnObjectTransactionsEntity());
    }

    @Test
    public void saveTransactionsMustReturnOk() throws Exception{
      assertEquals(returnObjectTransactionsResponseDTO(),transactionsFacade.save(returnObjectTransactionsRequestDTO()));
    }

    private TransactionsEntity returnObjectTransactionsEntity(){
        return  new TransactionsEntity(MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,
                MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV,returnObjectPayableEntity());
    }

    private PayableEntity returnObjectPayableEntity (){
        return  new PayableEntity(MOCK_STATUS,MOCK_ID_PAYMENT_DATE);
    }

    private TransactionsRequestDTO returnObjectTransactionsRequestDTO(){
        return  new TransactionsRequestDTO(MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,
                MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsResponseDTO returnObjectTransactionsResponseDTO(){
        return  new TransactionsResponseDTO(MOCK_ID,MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,
                MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,returnObjectPayableResponseDTO());
    }

    private PayableResponseDTO returnObjectPayableResponseDTO (){
        return  new PayableResponseDTO(MOCK_STATUS,MOCK_ID_PAYMENT_DATE);
    }
}
