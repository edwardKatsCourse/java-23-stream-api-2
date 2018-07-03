package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CustomCollectionMain {

    static String[] languages = {
            "English",
            "Hebrew",
            "Spanish",
            "Danish",
            "Romanian",
            "French"
    };

    public static void main(String[] args) {
        Arrays.stream(languages)
//                .collect(Collectors.toCollection(TreeSet::new));
                .collect(Collectors.toCollection(MySuperList::new));
//                .collect(Collectors.toCollection(LinkedList::new));
    }
}


class MySuperList<T> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        //more code....
        return super.add(t);
    }
}