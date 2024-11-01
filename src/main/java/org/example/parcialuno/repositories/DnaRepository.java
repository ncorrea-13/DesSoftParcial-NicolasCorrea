package org.example.parcialuno.repositories;

import org.springframework.stereotype.Repository;
import org.example.parcialuno.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {

    // Query para contar las secuencias de ADN que son mutantes
    long countByMutante(boolean mutante);

    // Query para buscar la existencia en la bd
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN false ELSE true END FROM Dna d WHERE d.ADN = :dna")
    boolean existsByDna(@Param("dna") String dna);
}
