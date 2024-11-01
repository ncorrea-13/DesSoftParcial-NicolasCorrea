package org.example.parcialuno.services;

import org.example.parcialuno.dto.DtoDnaInput;
import org.example.parcialuno.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class MutantServiceTest {

    @Autowired
    private MutantService mutantService;

    @MockBean
    private ValidationService validationService;

    @MockBean
    private DnaRepository dnaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // ====================================================================================================
    // Tests cubriendo todas las secuencias de matriz 6x6
    @Test
    public void testRows() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCTCTA",
                "TCACTG"
        ));

        // Configurar el mock para llamar al método real
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();

        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        // Verificar que el método validarADN fue llamado
        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testColumns() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "AGAATG",
                "TGCAGT",
                "GCTTCC",
                "GTCCTC",
                "GTAGTC",
                "GGTCAC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testMainDiagonals() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "AGAATG",
                "TACAGT",
                "GCAGCC",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testSecondaryLeftDiagonals() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATAATG",
                "GTTAGT",
                "GGCTCG",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testSecondaryRightDiagonals() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATAATG",
                "GTATGA",
                "GCTTAG",
                "TTTAGG",
                "GTAGTC",
                "AGTCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testTertiaryLeftDiagonals() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATGATG",
                "GTAGTA",
                "CCTTGG",
                "TCTAGG",
                "GGCGTC",
                "AGTCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testTertiaryRightDiagonals() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATGATG",
                "GTATTA",
                "AATTGG",
                "ACTAGT",
                "GGAGTC",
                "AGGCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testNonMutant() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "ATGATG",
                "GTCTTA",
                "AATTGG",
                "ACTAGT",
                "GGATTC",
                "AGGCAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }


    // ====================================================================================================
    // Tests brindados por el profesor
    @Test
    public void testMutant1() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testNonMutant1() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testMutant2() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testNonMutant2() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testNonMutant3() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertFalse(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testMutant3() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTACG",
                "ACGAGTGCT",
                "AGACACATG",
                "GAATTCCAA",
                "ACTACGACC",
                "TGAGTATCC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }

    @Test
    public void testMutant4() throws Exception {
        DtoDnaInput dna = new DtoDnaInput();
        dna.setDna(List.of(
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCAGT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCAT",
                "GCAGTCCCC"
        ));
        when(validationService.validarADN(any(String[].class))).thenCallRealMethod();
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));

        verify(validationService).validarADN(any(String[].class));
        assertTrue(mutantService.isMutant(dna.getDna().toArray(new String[0])));
    }
}
