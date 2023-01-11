package br.com.geradordedevs.paymentserviceprovider.controllers;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.enums.PaymentMethodEnum;
import br.com.geradordedevs.paymentserviceprovider.facades.TransactionsFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionsFacade transactionsFacade;

    private  final String ROUTE_TRANSACTIONS ="/api/v1/transactions";
    private final  String ROUTE_NAME_TRANSACTIONS = "/api/v1/transactions/name?name=ana";
    private  final String ROUTE_BALANCE_TRANSACTIONS = "/api/v1/transactions/balance/name?name=ana";

    private final BigDecimal MOCK_TRAMSACTION_AMOUNT = new BigDecimal(100);
    private  final String MOCK_TRANSACTION_DESCRIPTION = "fazendo teste unitario com tudo feito e funcionando corretamente";
    private  final PaymentMethodEnum MOCK_PAYMENT_METHOD_ENUM = PaymentMethodEnum.DEBIT_CARD;
    private  final  String MOCK_CARD_NUMBER = "12345678";
    private  final  String MOCK_NAME = "carlos";
    private  final  String MOCK_CARD_EXPIRATION_DATE = "10/22";
    private  final  int MOCK_CVV = 888;

    //transactionAmount
    private TransactionsRequestDTO returnCorretTransactions(){
        return  new TransactionsRequestDTO(MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithTransactionAmountMin(){
        return  new TransactionsRequestDTO( new BigDecimal(9),MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithTransactionAmountNull(){
        return  new TransactionsRequestDTO( null,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    //transactionDescription
    private TransactionsRequestDTO returnTransactionsWithTransactionDescriptionMin(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,"esta descricao esta com o num",
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithTransactionDescriptionMax(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,"esta descricao esta com o numero de caracteres acima do limite isso retorna  erro para o teste unitar",
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnoTransactionsWithTransactionDescriptionNull(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,null,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    //paymentMethod
    private TransactionsRequestDTO returnoTransactionsWithPaymentMethodNull(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                null,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    //cardNumber
    private TransactionsRequestDTO returnTransactionsWithCardNumberMin(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,"1",MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithCardNumberMax(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,"12345678901234567890123456789123456789011",MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnoTransactionsWithCardNumberNull(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,null,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    //name
    private TransactionsRequestDTO returnTransactionsWithNameMin(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,"aa",MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithNameMax(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,"carlosanacarlosanacarlosanacarl",MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    private TransactionsRequestDTO returnoTransactionsWithNameNull(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,null,MOCK_CARD_EXPIRATION_DATE,MOCK_CVV);
    }

    //cardExpirationDate
    private TransactionsRequestDTO returnTransactionsWithCardExpirationDateMin(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,"0/11",MOCK_CVV);
    }

    private TransactionsRequestDTO returnTransactionsWithCardExpirationDateMax(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,"10/222",MOCK_CVV);
    }

    private TransactionsRequestDTO returnoTransactionsWithCardExpirationDateNull(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,null,MOCK_CVV);
    }

    //Cvv
    private TransactionsRequestDTO returnTransactionsWithCvvMin(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
               MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,0);
    }

    private TransactionsRequestDTO returnTransactionsWithCvvMax(){
        return  new TransactionsRequestDTO( MOCK_TRAMSACTION_AMOUNT,MOCK_TRANSACTION_DESCRIPTION,
                MOCK_PAYMENT_METHOD_ENUM,MOCK_CARD_NUMBER,MOCK_NAME,MOCK_CARD_EXPIRATION_DATE,1000);
    }

    @Test
    public  void findAllByNameMustReturnOk() throws  Exception{
        mockMvc.perform(get(ROUTE_NAME_TRANSACTIONS))
                .andExpect(status().isOk());
    }

    @Test
    public  void findBalanceByNameMustReturnOk() throws  Exception{
        mockMvc.perform(get(ROUTE_BALANCE_TRANSACTIONS))
                .andExpect(status().isOk());
    }
    @Test
    public void saveTransactionsMustReturnOk() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnCorretTransactions()))
        ).andExpect(status().isOk());
    }

    @Test
    public void saveTransactionsWithTransactionAmountMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithTransactionAmountMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithTransactionAmountNullMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithTransactionAmountNull()))
        ).andExpect(status().isBadRequest());
    }

    //transactionDescription
    @Test
    public void saveTransactionsWithTransactionDescriptionMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithTransactionDescriptionMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithTransactionDescriptionMaxMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithTransactionDescriptionMax()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithTransactionDescriptionNullMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnoTransactionsWithTransactionDescriptionNull()))
        ).andExpect(status().isBadRequest());
    }

    // paymentMethod
       @Test
        public void saveTransactionsWithPaymentMethodNullMustReturnBadRequest() throws Exception {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            mockMvc.perform(post(ROUTE_TRANSACTIONS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ow.writeValueAsString(returnoTransactionsWithPaymentMethodNull()))
            ).andExpect(status().isBadRequest());
    }

    //cardNumber
    @Test
    public void saveTransactionsWithCardNumberMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithNameMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithCardNumberMaxMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithNameMax()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithCardNumberNullMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnoTransactionsWithCardNumberNull()))
        ).andExpect(status().isBadRequest());
    }

    //name
    @Test
    public void saveTransactionsWithNameMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCardNumberMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithNameMaxMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCardNumberMax()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithNameNullMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnoTransactionsWithNameNull()))
        ).andExpect(status().isBadRequest());
    }

    //cardExpirationDate
    @Test
    public void saveTransactionsWithCardExpirationDateMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCardExpirationDateMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithCardExpirationDateMaxMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCardExpirationDateMax()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithCardExpirationDateNullMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnoTransactionsWithCardExpirationDateNull()))
        ).andExpect(status().isBadRequest());
    }

    //cvv
    @Test
    public void saveTransactionsWithCvvMinMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCvvMin()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionsWithCvvMaxMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE_TRANSACTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionsWithCvvMax()))
        ).andExpect(status().isBadRequest());
    }
}
