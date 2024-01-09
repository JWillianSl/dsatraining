package com.josewillian.Structures.Stack;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.josewillian.Exceptions.StorageOverflowException;
import com.josewillian.Exceptions.StorageUnderflowException;
import com.josewillian.Structures.Lists.List;
import com.josewillian.Structures.Lists.LinkedList.LinkedList;

public class StackTest {

    private Stack<Integer> stackInstance;

    private Integer[] numbers;
    
    @BeforeEach
    public void setUp(){
        stackInstance = new Stack<Integer>(10);

        numbers = new Integer[]{2, 4, 3, 12, 6, 0, 16, 10, 8};
    }

    @Test
    public void pushTest(){

        assertAll(
            () -> assertTrue(stackInstance.push(7)),

            () -> {
                fillArray(numbers, stackInstance);

                assertEquals(10, stackInstance.size());
                assertThrows(StorageOverflowException.class, () -> stackInstance.push(11));
            }
        );

    }

    @Test
    public void popTest(){

        assertAll(
            () -> assertThrows(StorageUnderflowException.class, () -> stackInstance.pop()),

            () -> {
                fillArray(numbers, stackInstance);
                
                assertEquals(8, stackInstance.pop());
                assertEquals(10, stackInstance.pop());

                assertEquals(7, stackInstance.size());
            }
        );

    }

    @Test
    public void peekTest(){
        fillArray(numbers, stackInstance);

        assertAll(
            () -> assertEquals(8, stackInstance.peek()),
            () -> assertEquals(9, stackInstance.size()),

            () -> {

                for(int i = 0; i < 4; i++){
                    stackInstance.pop();
                }

            },

            () -> assertEquals(6, stackInstance.peek()),
            () -> assertEquals(5, stackInstance.size()),
            () -> {
                for(int i = 0; i < 5; i++){
                    stackInstance.pop();
                }
            },

            () -> assertNull(stackInstance.peek())
        );

    }

    @Test
    public void contains(){
        fillArray(numbers, stackInstance);

        assertAll(
            () -> assertTrue(stackInstance.contains(2)),
            () -> assertFalse(stackInstance.contains(-1)),
            
            () ->{
                assertEquals(9, stackInstance.size());
                assertArrayEquals(numbers, stackInstance.toArray()); 
            }
        );

    }

    @Test
    public void toArrayTest(){
        List<Integer> aux = new LinkedList<Integer>();
        Integer[] simple1 = new Integer[]{2, 4, 3, 12, 6};
        Integer[] simple2 = new Integer[]{2, 4, 3, 12, 6, 8, 10, 16, 0};
        fillArray(numbers, stackInstance);
        
        assertAll(
            () -> assertArrayEquals(numbers, stackInstance.toArray()),

            () -> {

                for(int i = 0; i < simple1.length - 1; i++){
                    aux.add(stackInstance.pop());
                }

                assertArrayEquals(simple1, stackInstance.toArray());
            },
            
            () -> {
                for(int i = 0; i < simple1.length - 1; i++){
                    stackInstance.push(aux.remove(0));
                }
                
                assertArrayEquals(simple2, stackInstance.toArray());
            },

            () -> {
                for(int i = 0; i < simple2.length; i++){
                    stackInstance.pop();
                }

                assertArrayEquals(new Integer[0], stackInstance.toArray());
            }

        );

    }

    private void fillArray(Integer[] arr, Stack<Integer> inst){
        for(Integer item : arr){
            inst.push(item);
        }
    }

}
