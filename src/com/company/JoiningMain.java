package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoiningMain {

    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();
        cities.add("Tel-Aviv");
        cities.add("Haifa");
        cities.add("Tiberias");
        cities.add("Eilat");
        cities.add("Jerusalem");
        cities.add("Rehovot");

        String citiesRow = cities
                .stream()
                .collect(Collectors.joining(", "));
        //Israeli cities are:
        System.out.printf("Israeli cities are: %s\n", citiesRow);

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String sentence = letters
                .stream()
                .collect(Collectors.joining(", ", "START [", "] END"));

        System.out.println(sentence);



    }
}
