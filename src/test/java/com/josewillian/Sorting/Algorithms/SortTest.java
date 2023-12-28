package com.josewillian.Sorting.Algorithms;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.josewillian.Sorting.Sort;

public class SortTest {

    private Integer[] unsortedInt;
    private Integer[] sortedInt;
    private Integer[] descendingInt;
    private Integer[] negativeInt;
    private Integer[] equalInt;

    private Double[] unsortedDoub;
    private Double[] sortedDoub;
    private Double[] descendingDoub;
    private Double[] negativeDoub;
    private Double[] equalDoub;


    private Integer[] empty;

    @BeforeEach
    public void setUp(){
        empty = new Integer[]{};

        unsortedInt = new Integer[]{6, 55, 4, 8, 9, 1, 2, 31, 7, 0, -9};
        sortedInt = new Integer[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        descendingInt = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1};
        negativeInt = new Integer[]{-6, -3, -2, -5, -99, -10, -98, -1};
        equalInt = new Integer[]{1, 1, 1, 1, 1, 1, 1};

        unsortedDoub = new Double[]{7.6, 33.5, 9.8, 7.7, 0.1, -1.3, 6.5};
        sortedDoub = new Double[]{0.0, 1.2, 3.03, 3.04, 4.0, 5.23, 6.0};
        descendingDoub = new Double[]{0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1};
        negativeDoub = new Double[]{-0.6, -3.5, -22.99, -5.3, -99.0, -10.23, -98.66, -1.0};
        equalDoub = new Double[]{7.7, 7.7, 7.7, 7.7, 7.7};
    }


    
    @ParameterizedTest
    @MethodSource("algorithms")
    public void emptyTest(Sort<?> algorithm){

        assertTrue(algorithmAction(algorithm, empty));

    }
    
    @ParameterizedTest
    @MethodSource("algorithms")
    public void unsortedTest(Sort<?> algorithm){

        assertAll(
            ()-> assertTrue(algorithmAction(algorithm, unsortedInt)),
            ()-> assertTrue(algorithmAction(algorithm, unsortedDoub))
        );

    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void sortedTest(Sort<?> algorithm){

        assertAll(
            ()-> assertTrue(algorithmAction(algorithm, sortedInt)),
            ()-> assertTrue(algorithmAction(algorithm, sortedDoub))
        );

    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void descendingTest(Sort<?> algorithm){

        assertAll(
            ()-> assertTrue(algorithmAction(algorithm, descendingInt)),
            ()-> assertTrue(algorithmAction(algorithm, descendingDoub))
        );

    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void negativeTest(Sort<?> algorithm){

        assertAll(
            ()-> assertTrue(algorithmAction(algorithm, negativeInt)),
            ()-> assertTrue(algorithmAction(algorithm, negativeDoub))
        );

    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void equalElementTest(Sort<?> algorithm){

        assertAll(
            ()-> assertTrue(algorithmAction(algorithm, equalInt)),
            ()-> assertTrue(algorithmAction(algorithm, equalDoub))
        );

    }


    @SuppressWarnings("unchecked")
    private boolean algorithmAction(Sort algorithm, Object[] arr){
        Object[] arrCopy = Arrays.copyOf(arr, arr.length);
        
        algorithm.sort(arr);
        Arrays.sort(arrCopy);

        return Arrays.equals(arr, arrCopy);
    }

    private static Stream<Sort<?>> algorithms(){
        return Stream.of(new SelectionSort<>(), 
            new InsertionSort<>(), new MergeSort<>(), new QuickSort<>());
    }

}
