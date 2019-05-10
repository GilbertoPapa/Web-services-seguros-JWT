package br.com.gilbertopapa.wsjwt.domian;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private Integer statusCode;
    private String statusMessage;
    private String erro;
}
