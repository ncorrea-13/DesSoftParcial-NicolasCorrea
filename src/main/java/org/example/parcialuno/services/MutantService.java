package org.example.parcialuno.services;

import java.util.HashSet;
import java.util.Set;

import org.example.parcialuno.entities.Dna;
import org.example.parcialuno.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.stream.IntStream;

@Service
public class MutantService {

    @Autowired
    protected DnaRepository dnaRepository;

    @Autowired
    protected ValidationService validationService;

    private Set<String> secuence = new HashSet<>();

    // Constructor que llama al servicio
    @Autowired
    public MutantService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    // Método pedido por Magneto
    public boolean isMutant(String[] dna) throws Exception {

        // Validación que no sean arreglos nulos y la validacion de casos de DNA
        if (dna == null || dna.length == 0 || !validationService.validarADN(dna)) {
            throw new Exception("Error en la declaración del ADN.");
        }

        secuence.clear();

        // Verificación de que no sea mutante
        boolean retorno = verificarMutante(dna);
        // Guardado en la Base de datos
        save(dna, retorno);
        return retorno;
    }

    // Método de verificación del mutante
    // Retorna falso si no hay mutágeno, verdadero si por lo menos en alguna de las
    // formas lo es
    private boolean verificarMutante(String[] dna) {
        int n = dna.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            count = verificacionHorizontal(dna, i) ? count + 1 : count;

            count = verificacionVertical(dna, i) ? count + 1 : count;

            if (count > 1) {
                return true;
            }
        }

        for (int i = 0; i < 4; i++) {
            count = verificacionDiagonalDerecha(dna) ? count + 1  : count;
            count = verificacionDiagonalIzquierda(dna) ? count + 1  : count;
        }

        return count > 1;

    }

    // Métodos de verificacion del Json
    private boolean verificacionVertical(String[] dna, int i) {
        return IntStream.range(0, dna.length - 3)
                .anyMatch(j -> verificacionSecuencia(dna[j].charAt(i), dna[j + 1].charAt(i), dna[j + 2].charAt(i),
                        dna[j + 3].charAt(i)));
    }

    private boolean verificacionHorizontal(String[] dna, int i) {
        return IntStream.range(0, dna.length - 3)
                .anyMatch(j -> verificacionSecuencia(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
                        dna[i].charAt(j + 3)));
    }

    // para cada diagonal de DNA a la función
    private boolean verificacionDiagonalDerecha(String[] dna) {
        int n = dna.length;
        return IntStream.range(0, n - 3)
                .anyMatch(i -> IntStream.range(0, n - 3)
                        .anyMatch(j -> verificacionSecuencia(dna[i].charAt(j), dna[i + 1].charAt(j + 1),
                                dna[i + 2].charAt(j + 2),
                                dna[i + 3].charAt(j + 3))));

    }

    private boolean verificacionDiagonalIzquierda(String[] dna) {
        int n = dna.length;
        return IntStream.range(0, n - 3)
                .anyMatch(i -> IntStream.range(3, n)
                        .anyMatch(j -> verificacionSecuencia(dna[i].charAt(j), dna[i + 1].charAt(j - 1),
                                dna[i + 2].charAt(j - 2),
                                dna[i + 3].charAt(j - 3))));
    }

    // Método genérico de verificacion de carácteres
    private boolean verificacionSecuencia(char a, char b, char c, char d) {
        // primero verifica que el 1 y el 4 sean iguales, en caso de no serlo, no hay
        // posibilidad de mutante
        if (a == d) {
            if (a == b && a == c) {
                // Verifica con un HashSet que no se repita la secuencia
                return secuence.add("" + a + b + c + d) ? true : false;

            }
        }
        return false;
    }

    @Transactional
    public void save(String[] string, boolean isMutant) throws Exception {
        // Verifica que no se repita el ADN en la base de datos
        boolean guardar = verificarBaseDeDatos(String.join(",", string));

        try {
            if (guardar) {
                // En caso de que no está repetido, lo guarda en la bd
                Dna dna = new Dna(String.join(",", string), isMutant);
                dnaRepository.save(dna);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Busca la existencia en la bd
    public boolean verificarBaseDeDatos(String dna) {
        return dnaRepository.existsByDna(dna);
    }

}
