package co.com.mercadolibre.usecase.util;

import co.com.mercadolibre.model.specie.Specie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DnaSequenceUtilTest {

    private DnaSequenceUtil dnaSequenceUtil;
    private static final String[] DNA_1 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String[] DNA_2 = {"TAGCGA", "CAGTGC", "TTATGT", "AGAAGG", "ATCCTA", "TCACTG"};
    private static final String[] DNA_3 = {"TAGCGA", "CAGTGC", "TTATGT", "RGAAGG", "ATCCTA", "TCACTG"};

    @BeforeEach
    void setUp() {
        dnaSequenceUtil = new DnaSequenceUtil();
    }

    @DisplayName("JUnit test for validate dna sequence when is false")
    @Test
    void isValidDnaSequenceFalseTest() {
        assertFalse(dnaSequenceUtil.isValidDnaSequence(DNA_3));
    }

    @DisplayName("JUnit test for validate dna sequence when is true")
    @Test
    void isValidDnaSequenceTrueTest() {
        assertTrue(dnaSequenceUtil.isValidDnaSequence(DNA_1));
    }

    @DisplayName("JUnit test for validate dna mutant")
    @Test
    void validateDNAMutantTest() {
        assertTrue(dnaSequenceUtil.validateDNA(DNA_1));
    }

    @DisplayName("JUnit test for validate dna human")
    @Test
    void validateDNAHumanTest() {
        assertFalse(dnaSequenceUtil.validateDNA(DNA_2));
    }

    @DisplayName("JUnit test for validate stats dna")
    @Test
    void countMutantDna() {
        assertArrayEquals(new Integer[]{2, 1}, dnaSequenceUtil.countMutantDna(buildListSpecies()));
    }

    private List<Specie> buildListSpecies() {
        Specie specie1 = Specie.builder().dna("dna1").species("human").build();
        Specie specie2 = Specie.builder().dna("dna2").species("mutant").build();
        Specie specie3 = Specie.builder().dna("dna3").species("mutant").build();
        return Arrays.asList(specie1,specie2,specie3);
    }
}
