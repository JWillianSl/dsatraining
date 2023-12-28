package com.josewillian.Structures.Lists;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.josewillian.Structures.Lists.ArrayList.ArrayList;
import com.josewillian.Structures.Lists.LinkedList.LinkedList;

public class ListTest {

    private Integer[] simpleArray;
    private String[] wordArray;

    @BeforeEach
    public void setUp(){

        simpleArray = new Integer[]{1, 2, 3, 4, 5, 6};
        wordArray = new String[]{"amazing", "electronic", "importation", "omelette", "unsorted"};

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void additionTest(List<Object> structure){
        
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> structure.add(1, 1)),
            () -> assertDoesNotThrow(() -> structure.addAll(simpleArray)),
            () -> assertTrue(structure.add(7)),

            () -> {
                Object[] completeArray = new Object[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
                structure.addAll(new Object[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21});

                assertArrayEquals(completeArray, structure.toArray());
            }
        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void getTest(List<Object> structure){

        assertAll(
            () -> structure.addAll(simpleArray),

            () -> {
                assertEquals(1, structure.get(0));
                assertEquals(6, structure.get(5));
                assertThrows(IndexOutOfBoundsException.class, () -> structure.get(-1));
            }

        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void removeTest(List<Object> structure){

        assertAll(
            () -> structure.addAll(wordArray),

            () -> {
                assertThrows(IndexOutOfBoundsException.class, () -> structure.remove(5));
                assertThrows(IndexOutOfBoundsException.class, () -> structure.remove(-1));

                assertEquals(true, structure.remove("amazing"));
                assertEquals("unsorted", structure.remove(3));
            }

        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void setTest(List<Object> structure){

        assertAll(
            () -> structure.addAll(wordArray),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> structure.set("apple", 5)),
            () -> assertEquals("amazing", structure.set("apple", 0)),
            () -> assertEquals("unsorted", structure.set("until", 4)),
            () -> {
                assertEquals("apple", structure.get(0));
                assertEquals("until", structure.get(4));
            }
        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void indexTest(List<Object> structure){

        assertAll(
            () -> {
                structure.add("train");
                structure.addAll(wordArray);
                structure.add("train");

                assertEquals(0, structure.indexOf("train"));
                assertEquals(1, structure.indexOf("amazing"));
                assertEquals(5, structure.indexOf("unsorted"));
            },

            () -> {
                structure.remove(0);
                assertEquals(5, structure.indexOf("train"));
                assertEquals(0, structure.indexOf("amazing"));
                assertEquals(-1, structure.indexOf("apple"));
            }
        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void containsTest(List<Object> structure){
        
        assertAll(
            () -> { 
                structure.add("train");
                structure.addAll(wordArray);
            },
            () -> assertFalse(structure.contains("apple")),
            () -> assertTrue(structure.contains("unsorted")),
            () -> assertTrue(structure.contains("train"))
        );

    }

    @ParameterizedTest
    @MethodSource("structure")
    public void sizeTest(List<Integer> structure){

        assertAll(
            () -> structure.addAll(simpleArray),
            () -> assertEquals(6, structure.size()),
            () -> structure.clear(),
            () -> assertEquals(0, structure.size()),
            () -> assertTrue(structure.isEmpty())
        );

    }

    private static Stream<List<Object>> structure(){
        return Stream.of(new ArrayList<>(), new LinkedList<>());
    }
}
