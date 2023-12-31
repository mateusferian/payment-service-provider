package br.com.geradordedevs.paymentserviceprovider.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserEnum {

    USER_NOT_FOUD("USER_NOT_FOUD","usuario não encontrado",404);

    private  String code;
    private String message;
    private  Integer statusCode;
}
