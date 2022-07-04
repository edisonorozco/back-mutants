package co.com.mercadolibre.usecase.util;

import co.com.mercadolibre.model.specie.Specie;
import co.com.mercadolibre.usecase.dnasequencetypes.IDnaSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DnaSequenceUtil {

    public boolean validateDNA(String[] dna) {

        char[][] matrixDna = dnaToMatrix(dna);

        return Stream.of(createListFromLoopDiagonally(matrixDna), createListFromLoopHorizontally(dna), createListFromLoopVertically(matrixDna))
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(builder -> builder.toString().contains(IDnaSequence.SEQUENCE_1) ||
                        builder.toString().contains(IDnaSequence.SEQUENCE_2) ||
                        builder.toString().contains(IDnaSequence.SEQUENCE_3) ||
                        builder.toString().contains(IDnaSequence.SEQUENCE_4))
                .count() > 1;
    }

    private List<StringBuilder> createListFromLoopHorizontally(String[] dna) {
        return Stream.of(dna)
                .map(StringBuilder::new)
                .collect(Collectors.toList());
    }

    private char[][] dnaToMatrix(String[] dna) {
        return Stream.of(dna)
                .map(String::toCharArray)
                .collect(Collectors.toList())
                .toArray(char[][]::new);
    }

    public boolean isValidDnaSequence(String[] dna) {
        for (String dnaSequence : dna) {
            for (char value : dnaSequence.toCharArray()) {
                if (value != 'A' && value != 'T' && value != 'C' && value != 'G')
                    return false;
            }
        }
        return true;
    }

    public List<StringBuilder> createListFromLoopDiagonally(char[][] matrixDna) {

        StringBuilder stringBuilder = new StringBuilder();
        List<StringBuilder> stringBuilderList = new ArrayList<>();
        int rowsMatrix = matrixDna.length, columnsMatrix = matrixDna[0].length;

        for (int i = 1 - columnsMatrix; i < rowsMatrix; i++) {
            for (int j = Math.max(0, i), k = -Math.min(0, i); j < rowsMatrix && k < columnsMatrix; j++, k++) {

                if (j == 0 || k == 0)
                    stringBuilder = new StringBuilder();

                stringBuilder.append(matrixDna[j][k]);
            }
            stringBuilderList.add(stringBuilder);
        }

        return stringBuilderList.stream()
                .filter(builder -> builder.length() > 3)
                .collect(Collectors.toList());
    }

    private List<StringBuilder> createListFromLoopVertically(char[][] matrixDna) {

        int rowsMatrix = matrixDna.length, columnsMatrix = matrixDna[0].length;
        StringBuilder stringBuilder = new StringBuilder();
        List<StringBuilder> stringBuilderList = new ArrayList<>();

        for (int i = 0; i < columnsMatrix; i++) {
            for (int j = 0; j < rowsMatrix; j++) {

                if (j == 0)
                    stringBuilder = new StringBuilder();

                stringBuilder.append(matrixDna[j][i]);
            }

            stringBuilderList.add(stringBuilder);
        }

        return stringBuilderList;
    }

    public String arrayToString(String[] dna) {
        return Arrays.toString(dna);
    }

    public Integer[] countMutantDna(List<Specie> specieList) {
        long mutants = specieList.stream()
                .filter(specie -> specie.getSpecies().equalsIgnoreCase("mutant"))
                .count();

        long humans = specieList.stream()
                .filter(specie -> specie.getSpecies().equalsIgnoreCase("human"))
                .count();
        return new Integer[]{(int) mutants, (int) humans};
    }

}
