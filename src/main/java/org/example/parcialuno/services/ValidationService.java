package org.example.parcialuno.services;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean validarADN(String[] dna) {
        for (String fila : dna) {
            if (fila == null || fila.length() != dna.length || !fila.matches("[ATGC]+")) {
                return false;
            }
        }

        return true;
    }
}