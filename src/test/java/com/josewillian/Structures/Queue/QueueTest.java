package com.josewillian.Structures.Queue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.josewillian.Exceptions.StorageOverflowException;
import com.josewillian.Exceptions.StorageUnderflowException;

public class QueueTest {

    private Integer[] simpleInt;

    @BeforeEach
    public void setUp(){
        simpleInt = new Integer[]{1, 2, 3, 4, 5, 6, 7};
    }

    @ParameterizedTest
    @MethodSource("structure")
    public void enqueueTest(Queue<Integer> structure){

        assertAll(
            () -> assertEquals(0, structure.size()),
            () -> assertTrue(structure.enqueue(0)),

            () -> {
                for(Integer item : simpleInt){
                    structure.enqueue(item);
                }
            },

            () -> assertEquals(8, structure.size()),

            () -> {
                int initialSize = (20 - structure.size());

                for(int i = 0; i < initialSize; i++){
                    structure.enqueue(20 - structure.size());
                }
            },

            () -> assertThrows(StorageOverflowException.class, () -> structure.enqueue(21))

        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void dequeueTest(Queue<Integer> structure){

        assertAll(
            () -> assertThrows(StorageUnderflowException.class, () -> structure.dequeue()),

            () -> {
                for(Integer item : simpleInt){
                    structure.enqueue(item);
                }
            },

            () -> assertEquals(1, structure.dequeue()),

            () -> {
                Integer[] arrCompare = new Integer[6];

                for(int i = 0; i < 6; i++){
                    arrCompare[i] = structure.dequeue();
                }

                assertArrayEquals(arrCompare, new Integer[]{2, 3, 4, 5, 6, 7});
            }

        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void peekTest(Queue<Integer> structure){
        
        assertAll(
            () -> assertNull(structure.peek()),

            () -> {
                for(Integer item : simpleInt){
                    structure.enqueue(item);
                }
            },

            () -> assertEquals(1, structure.peek()),
            () -> assertEquals(7, structure.size()),

            () -> {
                for(int i = 0; i < 4; i++){
                    structure.dequeue();
                }
            },

            () -> assertEquals(5, structure.peek()),
            () -> assertEquals(3, structure.size())

        );

    }

    private static Stream<Queue<Integer>> structure(){
        return Stream.of(new LinkedQueue<>());
    }

}
