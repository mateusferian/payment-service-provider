package br.com.geradordedevs.paymentserviceprovider.mappers;

import br.com.geradordedevs.paymentserviceprovider.dtos.requests.TransactionsRequestDTO;
import br.com.geradordedevs.paymentserviceprovider.dtos.responses.TransactionsResponseDTO;
import br.com.geradordedevs.paymentserviceprovider.entities.TransactionsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionsMapper {

    @Autowired
    private final ModelMapper mapper;

    public TransactionsResponseDTO toDto(TransactionsEntity entity){
        log.info("converting entity{} to dto", entity);
        return  mapper.map(entity, TransactionsResponseDTO.class);
    }


    public TransactionsEntity toEntity(TransactionsRequestDTO request){
        log.info("converting dto{} to entity", request);
        return  mapper.map(request, TransactionsEntity.class);
    }




    public List<TransactionsResponseDTO> toDtoList(Iterable<TransactionsEntity> lista){
        log.info("converting entity list{} to dto list", lista);
        List<TransactionsEntity> resultado = new ArrayList<>();
        lista.forEach(resultado::add);
        return  resultado.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
