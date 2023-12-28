package com.josewillian.Util;

public class Util {

    public static void swap(Object[]arr, int indexA, int indexB){
        Object temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

}
