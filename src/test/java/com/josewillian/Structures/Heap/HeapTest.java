package com.josewillian.Structures.Heap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.josewillian.Sorting.Sort;
import com.josewillian.Sorting.Algorithms.MergeSort;
import com.josewillian.Structures.Heap.Comparators.MaxHeap;

public class HeapTest {

    private Integer[] simpleArray;
    private Integer[] totalSize;

    @BeforeEach
    public void setUp(){
        simpleArray = new Integer[]{4, 2, 6, 3, 5, 8, 7, 9, 10};
        totalSize = new Integer[]{4, 2, 6, 3, 5, 8, 7, 9, 10, 0, 22, 31, 11, 14, 13, 12, 16, 17, 19, 15};
    }

    @ParameterizedTest
    @MethodSource("sameInput")
    public void addTest(Heap<Integer> structure){

        assertAll(
            () -> assertEquals(0, structure.size()),
            () -> assertTrue(structure.add(1)),

            () -> fillHeap(structure, simpleArray),
            () -> assertEquals(10, structure.size()),

            () -> fillHeap(structure, totalSize),
            () -> assertEquals(30, structure.size()),
            () -> assertThrows(IllegalArgumentException.class, () -> structure.add(null))
        );

    }

    @ParameterizedTest
    @MethodSource("differentTypes")
    public void extractRootTest(Heap<Integer> minStructure, Heap<Integer> maxStructure){
        Integer[] arr = Arrays.copyOf(simpleArray, simpleArray.length);
        Sort<Integer> sort = new MergeSort<Integer>();
        sort.sort(arr);

        assertAll(
            () -> assertNull(minStructure.extractRoot()),

            () -> fillHeap(minStructure, simpleArray),
            () -> assertEquals(2, minStructure.extractRoot()),
            () -> assertEquals(8, minStructure.size()),

            () -> {
                minStructure.add(2);

                for(int i = 0; i < arr.length; i++){
                    Integer current = minStructure.extractRoot();

                    if(current != arr[i]){
                        fail("Not Equals : " + Integer.toString(current) + " " + Integer.toString(arr[i]));
                    }
                }

            }

        );

        assertAll(
            () -> assertNull(maxStructure.extractRoot()),

            () -> fillHeap(maxStructure, simpleArray),
            () -> assertEquals(10, maxStructure.extractRoot()),
            () -> assertEquals(8, maxStructure.size()),

            () -> {
                maxStructure.add(10);

                for(int i = arr.length - 1; i >= 0; i--){
                    Integer current = maxStructure.extractRoot();

                    if(current != arr[i]){
                        fail("Not Equals : " + Integer.toString(current) + " " + Integer.toString(arr[i]));
                    }
                }

            }
        );

    }

    @ParameterizedTest
    @MethodSource("differentTypes")
    public void heapSortTest(Heap<Integer> minStructure, Heap<Integer> maxStructure){
        Integer[] arr = Arrays.copyOf(totalSize, totalSize.length);
        
        Sort<Integer> sort = new MergeSort<Integer>();
        sort.sort(arr);

        assertAll(
            () -> fillHeap(minStructure, totalSize),

            () -> {
                Integer[] heapOutput = new Integer[totalSize.length];

                for(int i = 0; i < heapOutput.length; i++){
                    heapOutput[i] = minStructure.extractRoot();
                }

                assertArrayEquals(arr, heapOutput);
            }
        );

        assertAll(
            () -> fillHeap(maxStructure, arr),
            
            () -> {
                Integer[] heapOutput = new Integer[totalSize.length];

                for(int i = heapOutput.length - 1; i >= 0; i--){
                    heapOutput[i] = maxStructure.extractRoot();
                }

                assertArrayEquals(arr, heapOutput);
            }
        );

    }

    @ParameterizedTest
    @MethodSource("sameInput")
    public void sizeTest(Heap<Integer> structure){

        assertAll(
            () -> assertEquals(0, structure.size()),

            () -> fillHeap(structure, totalSize),
            () -> assertEquals(20, structure.size()),

            () -> structure.extractRoot(),
            () -> assertEquals(19,  structure.size()),

            () -> {
                for(int i = 0; i < 19; i++){
                    structure.extractRoot();
                }
            },

            () -> structure.extractRoot(),
            () -> assertEquals(0, structure.size())
        );

    }

    @ParameterizedTest
    @MethodSource("differentTypes")
    public void getRoot(Heap<Integer> minStructure, Heap<Integer> maxStructure){

        assertAll(
            () -> assertNull(minStructure.getRoot()),
            
            () -> fillHeap(minStructure, totalSize),
            () -> assertEquals(0, minStructure.getRoot()),
            () -> assertEquals(20, minStructure.size())
        );

        assertAll(
            () -> assertNull(maxStructure.getRoot()),
            
            () -> fillHeap(maxStructure, totalSize),
            () -> assertEquals(31, maxStructure.getRoot()),
            () -> assertEquals(20, maxStructure.size())
        );

    }

    @ParameterizedTest
    @MethodSource("differentTypes")
    public void toArrayTest(Heap<Integer> minStructure, Heap<Integer> maxStructure){

        assertAll(
            () -> assertArrayEquals(new Integer[0], minStructure.toArray()),

            () -> fillHeap(minStructure, simpleArray),
            () -> assertArrayEquals(new Integer[]{2, 3, 6, 4, 5, 8, 7, 9, 10}, minStructure.toArray())
        );

        assertAll(
            () -> assertArrayEquals(new Integer[0], maxStructure.toArray()),

            () -> fillHeap(maxStructure, simpleArray),
            () -> assertArrayEquals(new Integer[]{10, 9, 7, 8, 3, 4, 6, 2, 5}, maxStructure.toArray())
        );

    }

    @Test
    public void buildHeapTest(){

        Heap<Integer> structure = new Heap<Integer>(simpleArray);

        assertAll(
            () -> assertEquals(9, structure.size()),
            () -> assertEquals(2, structure.getRoot()),

            () -> structure.setComparator(new MaxHeap<Integer>()),
            () -> assertEquals(10, structure.getRoot())
        );

    }

    private void fillHeap(Heap<Integer> structure, Integer[] arr){
        for(Integer item : arr){
            structure.add(item);
        }
    }

    /**
     * Provides Arguments containing Heap instances.
     * Each Heap instance contains a comparator, a Min comparator and a Max comparator.
     * 
     * @return Arguments containing Heap instances
     */
    private static Stream<Arguments> differentTypes(){
        return Stream.of(
            Arguments.arguments(new Heap<Integer>(), new Heap<Integer>(new MaxHeap<>()))
            );
    }
    
    /**
     * Provides Heap instances for shared tests.
     * Each Heap instance contains a comparator, a Min comparator and a Max comparator.
     * 
     * @return Heap instances
     */
    private static Stream<Heap<Integer>> sameInput(){
        return Stream.of(
            new Heap<Integer>(), 
            new Heap<Integer>(new MaxHeap<>())
        );
    }
}
