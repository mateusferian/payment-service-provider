package br.com.geradordedevs.paymentserviceprovider.controllers;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.facades.TransactionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsFacade transactionsFacade;

    @PostMapping
    public TransactionsResponseDTO save(@Valid @RequestBody TransactionsRequestDTO request){
        return  transactionsFacade.save(request);
    }
    @GetMapping
    List<TransactionsResponseDTO> findAll(){
        return  transactionsFacade.findAll();
    }
}
