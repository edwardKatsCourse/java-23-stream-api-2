package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollinsWordCountMain {

    static String bookPart = "When I wake up, the other side of the bed is cold. My fingers\n" +
            "stretch out, seeking Prim’s warmth but finding only the\n" +
            "rough canvas cover of the mattress. She must have had bad\n" +
            "dreams and climbed in with our mother. Of course, she did.\n" +
            "This is the day of the reaping.\n" +
            "I prop myself up on one elbow. There’s enough light in the\n" +
            "bedroom to see them. My little sister, Prim, curled up on her\n" +
            "side, cocooned in my mother’s body, their cheeks pressed together.\n" +
            "In sleep, my mother looks younger, still worn but not\n" +
            "so beaten-down. Prim’s face is as fresh as a raindrop, as lovely\n" +
            "as the primrose for which she was named. My mother was\n" +
            "very beautiful once, too. Or so they tell me.\n" +
            "Sitting at Prim’s knees, guarding her, is the world’s ugliest\n" +
            "cat. Mashed-in nose, half of one ear missing, eyes the color of\n" +
            "rotting squash. Prim named him Buttercup, insisting that his\n" +
            "muddy yellow coat matched the bright flower. I le hates me.\n" +
            "Or at least distrusts me. Even though it was years ago, I think\n" +
            "he still remembers how I tried to drown him in a bucket when\n" +
            "Prim brought him home. Scrawny kitten, belly swollen with\n" +
            "worms, crawling with fleas. The last thing I needed was\n" +
            "another mouth to feed. But Prim begged so hard, cried even, I\n" +
            "had to let him stay. It turned out okay. My mother got rid of \n" +
            "5\n" +
            "the vermin and he’s a born mouser. Even catches the occasional\n" +
            "rat. Sometimes, when I clean a kill, I feed Buttercup the\n" +
            "entrails. He has stopped hissing at me.\n" +
            "Entrails. No hissing. This is the closest we will ever come to\n" +
            "love.\n" +
            "I swing my legs off the bed and slide into my hunting boots.\n" +
            "Supple leather that has molded to my feet. I pull on trousers, a\n" +
            "shirt, tuck my long dark braid up into a cap, and grab my forage\n" +
            "bag. On the table, under a wooden bowl to protect it from\n" +
            "hungry rats and cats alike, sits a perfect little goat cheese\n" +
            "wrapped in basil leaves. Prim’s gift to me on reaping day. I put\n" +
            "the cheese carefully in my pocket as I slip outside.\n" +
            "Our part of District 12, nicknamed the Seam, is usually\n" +
            "crawling with coal miners heading out to the morning shift at\n" +
            "this hour. Men and women with hunched shoulders, swollen\n" +
            "knuckles, many who have long since stopped trying to scrub\n" +
            "the coal dust out of their broken nails, the lines of their sunken\n" +
            "faces. But today the black cinder streets are empty. Shutters\n" +
            "on the squat gray houses are closed. The reaping isn’t until\n" +
            "two. May as well sleep in. If you can.\n" +
            "Our house is almost at the edge of the Seam. I only have to\n" +
            "pass a few gates to reach the scruffy field called the Meadow.\n" +
            "Separating the Meadow from the woods, in fact enclosing all\n" +
            "of District 12, is a high chain-link fence topped with barbedwire\n" +
            "loops. In theory, it’s supposed to be electrified twentyfour\n" +
            "hours a day as a deterrent to the predators that live in the\n" +
            "woods — packs of wild dogs";


    static String[] NON_WORDS = {".", "-", ",", "—", ";"};


    public static void main(String[] args) {
        String [] rawWords = bookPart
                .replaceAll("\n", " ")
                .split(" ");

        List<String> words = Arrays.stream(rawWords)
                .filter(x -> isWord(x))
                .map(x -> x.trim())
                .map(x -> cleanUpEndCharacter(x))
                .map(x -> cleanUpStartCharacter(x))
                .map(x -> x.toLowerCase())
                .collect(Collectors.toList());

        Map<String, Long> wordOccurrences = words
                .stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));


        wordOccurrences
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(x -> System.out.printf("%s - %s\n", x.getKey(), x.getValue()));

        System.out.printf("Total words: %s",
                wordOccurrences
                        .entrySet()
                        //(key, value)
                        .stream()
                        //(value:long)
                        .map(x -> x.getValue())
                        .reduce((a, b) -> a + b)
                        .orElse(0L));
    }


    private static String cleanUpEndCharacter(String initialWord) {
        long count = Arrays.stream(NON_WORDS)
                .filter(x -> initialWord.endsWith(x) || initialWord.endsWith(x + "\n"))
                .count();

        boolean containsNonWordCharacter = count > 0;

        if (containsNonWordCharacter) {
            return initialWord.substring(0, initialWord.length() - 1);
        }


        return initialWord;
    }

    private static String cleanUpStartCharacter(String initialWord) {
        long count = Arrays.stream(NON_WORDS)
                .filter(x -> initialWord.startsWith(x))
                .count();

        boolean containsNonWordCharacter = count > 0;

        if (containsNonWordCharacter) {
            return initialWord.substring(1, initialWord.length());
        }
        return initialWord;
    }

    //                                      is
    private static boolean isWord(String possibleWord) {
        String wordSearchResult = Arrays.stream(NON_WORDS)
                .filter(x -> x.equals(possibleWord))
                //Optional
                .findAny()
                .orElse(null);

        return wordSearchResult == null;
    }
}
