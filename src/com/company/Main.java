package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //Stream API
        //Collectors

        //List<List<String>>
        //List<String[]>
        //List< Stream.of(List<String>)    > -> flatMap -> List<String>

        List<String> letters = new ArrayList<>();
        letters.add("a, b, c, d, e, f, g, h, i");
        letters.add("j, k, l, m, n, o, p");

//        Arrays
//                .stream(alphabetWithComma.split(", "))
//                .forEach(System.out::println);

        //Map<String, Long> -> string - letter, long - occurrences
//        Set<String> collect = letters.stream()
//                .flatMap(x -> Stream.of(x.split(", ")))
//                .collect(Collectors.toSet());

//        System.out.println(collect);


        List<String> notSeparatedLetters = new ArrayList<>();
        notSeparatedLetters.add("abcdefghijjknajknjwfwf");
        notSeparatedLetters.add("zyxwvutsrqknasjknjnasfjknasjkfnkjwqf");
        notSeparatedLetters.add("poakjhajshjkafuiqwuihfuiqhwfnmlkj");
        notSeparatedLetters.add("ihjzyxwvut");

        //unique letters
        //sorted letters
        //single collection

        //Stream<?>
        notSeparatedLetters
                .stream()
                .flatMap(x -> Stream.of(x.split("")))
                .sorted()
                .collect(Collectors.toSet())
                .forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();
        System.out.println();
        //Map<String, Integer> -> letter, count occurrences

        //Stream<String>
        /*Map<String, Long> collect = */
        notSeparatedLetters
                .stream()
                .flatMap(x -> Stream.of(x.split("")))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()))
                //Map<String, Long>
                .entrySet()
                .stream()
//                .sorted(Comparator.comparing(Map.Entry::getValue).reversed())
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .forEach(x -> System.out.printf("%s - %s\n",
                        x.getKey(),
                        x.getValue()));

    }

    private static List<Character> getGenericCharacters(char[] chars) {
        List<Character> characterList = new ArrayList<>();
        for (char c : chars) {
            characterList.add(c);
        }
        return characterList;
    }


}
