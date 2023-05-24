package com.neoris.app.application.port.out.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGeneric<T> {
    private T objectDto;
    private List<T> listObjectDto;

    public ResponseGeneric(T objectDto) {
        this.objectDto = objectDto;
    }

    public ResponseGeneric(List<T> listObjectDto) {
        this.listObjectDto = listObjectDto;
    }

    public T getObjectDto() {
        return objectDto;
    }

    public void setObjectDto(T objectDto) {
        this.objectDto = objectDto;
    }

    public List<T> getListObjectDto() {
        return listObjectDto;
    }

    public void setListObjectDto(List<T> listObjectDto) {
        this.listObjectDto = listObjectDto;
    }
}
