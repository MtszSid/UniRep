import kosmos.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        EnumMap<Planeta, URL> map = new EnumMap<Planeta, URL>(Planeta.class);

        map.put(Planeta.MERKURY,    new URL("https://pl.m.wikipedia.org/wiki/Merkury#/media/Plik%3AMercury_in_true_color.jpg"));
        map.put(Planeta.WENUS,      new URL("https://pl.m.wikipedia.org/wiki/Wenus#/media/Plik%3AVenus-real_color.jpg"));
        map.put(Planeta.ZIEMIA,     new URL("https://pl.m.wikipedia.org/wiki/Ziemia#/media/Plik%3AThe_Earth_seen_from_Apollo_17.jpg"));
        map.put(Planeta.MARS,       new URL("https://pl.m.wikipedia.org/wiki/Mars#/media/Plik%3AMars_Valles_Marineris.jpeg"));
        map.put(Planeta.JOWISZ,     new URL("https://pl.m.wikipedia.org/wiki/Jowisz#/media/Plik%3AJupiter_and_its_shrunken_Great_Red_Spot_(cropped).jpg"));
        map.put(Planeta.SATURN,     new URL("https://pl.m.wikipedia.org/wiki/Saturn#/media/Plik%3ASaturn_during_Equinox_(cropped).jpg"));
        map.put(Planeta.URAN,       new URL("https://pl.m.wikipedia.org/wiki/Uran#/media/Plik%3AUranus2_(cropped)-1.jpg"));
        map.put(Planeta.NEPTUN,     new URL("https://pl.m.wikipedia.org/wiki/Neptun#/media/Plik%3ANeptune_-_Voyager_2_(29347980845)_flatten_crop.jpg"));

        map.forEach((k, v) -> System.out.println(k + " " + v));


        EnumSet<Zodiak> pasywne_1 = Arrays.stream(Zodiak.values()).filter(a -> a.getEnergia() == Energia.SLABA)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        EnumSet<Zodiak> powietrze_1 = Arrays.stream(Zodiak.values()).filter(a -> a.getZywiol() == Zywiol.POWIETRZE)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        EnumSet<Zodiak> kardynalne_1 = Arrays.stream(Zodiak.values()).filter(a -> a.getKrzyz() == Krzyz.KARDYNALNY)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));;

        EnumSet<Zodiak> kardynalne = EnumSet.of(Zodiak.BARAN, Zodiak.RAK, Zodiak.WAGA, Zodiak.KOZIOROŻEC);
        EnumSet<Zodiak> powietrze  = EnumSet.of(Zodiak.BLIŹNIĘTA, Zodiak.WAGA, Zodiak.WODNIK);
        EnumSet<Zodiak> pasywne    = EnumSet.of(Zodiak.BYK, Zodiak.RAK, Zodiak.PANNA, Zodiak.SKORPION, Zodiak.KOZIOROŻEC, Zodiak.RYBY);

        EnumSet<Zodiak> inter = kardynalne.stream().filter(powietrze::contains).filter(pasywne::contains).collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        System.out.println(inter);
        EnumSet<Zodiak> sum = Stream.concat(Stream.concat(kardynalne.stream(), powietrze.stream()), pasywne.stream()).collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        System.out.println(sum);

        EnumSet<Zodiak> inter_1 = kardynalne_1.stream().filter(powietrze_1::contains)
                                                     .filter(pasywne_1::contains)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        System.out.println(inter_1);
        EnumSet<Zodiak> sum_1 = Stream.concat(Stream.concat(kardynalne_1.stream(), powietrze_1.stream()), pasywne_1.stream())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Zodiak.class)));
        System.out.println(sum_1);

    }
}
