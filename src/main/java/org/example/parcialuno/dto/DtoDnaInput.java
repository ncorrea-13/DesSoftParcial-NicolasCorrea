package org.example.parcialuno.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DtoDnaInput {

    // El DTO permite encapsular el JSON enviado

    // Es la lista de strings, Java interpreta de esta forma al JSON
    private List<String> dna;

    @JsonCreator
    public DtoDnaInput(@JsonProperty("dna") List<String> dna) {
        this.dna = dna;
    }
}
