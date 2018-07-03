package com.company.group;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupMain {

    static List<Person> personList = Arrays.asList(
            new Person("Yosi", "Rehovot"),
            new Person("Yosi", "Rehovot"),
            new Person("Yosi", "Rehovot"),
            new Person("Dani", "Rehovot"),
            new Person("Dani", "Rehovot"),
            new Person("Aron", "Rehovot"),
            new Person("Shir", "Rehovot"),

            new Person("Yosi", "Tel-Aviv"),
            new Person("Yosi", "Tel-Aviv"),
            new Person("Aron", "Tel-Aviv"),
            new Person("Aron", "Tel-Aviv"),
            new Person("Aron", "Tel-Aviv"),
            new Person("Aron", "Tel-Aviv"),
            new Person("Shir", "Tel-Aviv"),

            new Person("Yosi", "Jerusalem"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Dani", "Bat-Yam"),
            new Person("Yosi", "Haifa"),
            new Person("Shir", "Haifa"),
            new Person("Dani", "Haifa"),

            new Person("Meirav", "Ashdod"),
            new Person("Moshe", "Ashdod"),
            new Person("Shmulik", "Ashdod"),
            new Person("Dan", "Ashdod"),
            new Person("Alla", "Ashdod"),
            new Person("Ilana", "Ashdod"),
            new Person("Dvora", "Ashdod")
    );

    public static void main(String[] args) {
        //Most popular names in Israel
        //Most popular name in Israel
        //Most popular name in Tel-Aviv
        //Most populated city

        personList.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted((a,b) -> {

                    int result = b.getValue().compareTo(a.getValue());
                    if (result == 0) {
                        result = a.getKey().compareTo(b.getKey());
                    }
                    return result;
                })
                .forEach(x-> System.out.printf("%s - %s\n", x.getKey(), x.getValue()));

    }


    private static void mostPopularNameInTelAviv() {
        System.out.println("Most popular name in Tel-Aviv");
        Map<String, Long> names = personList
                .stream()
                .filter(x -> x.getCity().equals("Tel-Aviv"))
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.counting()
                ));
        names
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(x -> System.out.printf("%s - %s\n", x.getKey(), x.getValue()));

        System.out.println("Most popular name in Tel-Aviv");
        Optional<Map.Entry<String, Long>> nameInTelAviv = names
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .findFirst();

        if (nameInTelAviv.isPresent()) {
            System.out.printf("%s - %s\n", nameInTelAviv.get().getKey(), nameInTelAviv.get().getValue());
        } else {
            System.out.println("No name to display");

        }
    }

    private static void mostPopularNameInIsrael() {
        System.out.println("Most popular names in Israel");
        Map<String, Long> names = personList.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.counting()
                ));

        names
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(x -> System.out.printf("%s - %s\n", x.getKey(), x.getValue()));

        System.out.println("Most popular name");

        Optional<Map.Entry<String, Long>> mostPopularName = names
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .findFirst();

        if (mostPopularName.isPresent()) {
            System.out.printf("%s - %s", mostPopularName.get().getKey(), mostPopularName.get().getValue());
        } else {
            System.out.println("No name to print");
        }

    }
}
