import structures.OrderedList;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        OrderedList<Integer> ListOfInts_0 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_1 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_2 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_3 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_4 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_5 = new OrderedList<>();

        OrderedList<Integer> List = new OrderedList<>();
        List.insert(2);
        List.insert(3);
        List.insert(1);
        System.out.println(List);
        for(int i = 0; i < 4; i++ ){
            int rand = (int) (100 * Math.random());
            ListOfInts_0.insert(rand);
            ListOfInts_1.insert(rand);
            ListOfInts_2.insert(rand);

        }
        System.out.println(ListOfInts_0);
        for (Integer val : ListOfInts_0) {
            if(val % 2 == 0){
                ListOfInts_0.remove(val);
            }
        }
        System.out.println(ListOfInts_0);

        Iterator<Integer> it = ListOfInts_1.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            if(i % 2 == 0){
                it.remove();
            }
        }
        System.out.println(ListOfInts_1);

        System.out.println(ListOfInts_0.min());
        System.out.println(ListOfInts_0.max());
        System.out.println(ListOfInts_2.min());
        System.out.println(ListOfInts_2.max());

        for (int i = 0; i < 100; i++){
            if(ListOfInts_0.search(i)){
                ListOfInts_3.insert(i);
                ListOfInts_4.insert(ListOfInts_0.index(i));
                ListOfInts_5.insert(ListOfInts_0.at(ListOfInts_0.index(i)));
            }
        }

        System.out.println(ListOfInts_3);
        System.out.println(ListOfInts_5);
        System.out.println(ListOfInts_4);

        System.out.println(ListOfInts_0.size());
        System.out.println(ListOfInts_2.size());

        OrderedList<String> ListOfStrings_0 = new OrderedList<>();
        OrderedList<String> ListOfStrings_1 = new OrderedList<>();
        OrderedList<String> ListOfStrings_2 = new OrderedList<>();
        OrderedList<String> ListOfStrings_3 = new OrderedList<>();
        OrderedList<String> ListOfStrings_4 = new OrderedList<>();

        for(int i = 0; i < 20; i++ ){
            String rand = GeneratingRandomAlphabeticString();
            ListOfStrings_0.insert(rand);
            ListOfStrings_1.insert(rand);
            ListOfStrings_2.insert(rand);

        }
        System.out.println(ListOfStrings_0);
        for (String val : ListOfStrings_0) {
            if(val.contains("a")){
                ListOfStrings_1.remove(val);
            }
        }
        System.out.println(ListOfStrings_1);

        Iterator<String> it_2 = ListOfStrings_2.iterator();
        while(it_2.hasNext()){
            String s = it_2.next();
            if(s.contains("a")){
                it_2.remove();
            }
        }
        System.out.println(ListOfStrings_2);

        System.out.println(ListOfStrings_0.min());
        System.out.println(ListOfStrings_0.max());
        System.out.println(ListOfStrings_2.min());
        System.out.println(ListOfStrings_2.max());

        for (int i = 0; i < 20; i++){
            if(ListOfStrings_2.search(ListOfStrings_0.at(i))){
                ListOfStrings_3.insert(ListOfStrings_0.at(i));
                ListOfStrings_4.insert(String.valueOf(ListOfStrings_2.index(ListOfStrings_0.at(i))));
            }
        }
        System.out.println(ListOfStrings_2);
        System.out.println(ListOfStrings_3);
        System.out.println(ListOfStrings_4);


        System.out.println(ListOfStrings_0.size());
        System.out.println(ListOfStrings_3.size());


        OrderedList<Date> ListOfDates_0 = new OrderedList<>();
        OrderedList<Date> ListOfDates_1 = new OrderedList<>();
        OrderedList<Date> ListOfDates_2 = new OrderedList<>();
        OrderedList<Date> ListOfDates_3 = new OrderedList<>();
        OrderedList<Integer> ListOfInts_dates = new OrderedList<>();


        for(int i = 0; i < 20; i++ ){
            Date rand = new Date((long) (1000000000000000L * Math.random()));
            ListOfDates_0.insert(rand);
            ListOfDates_1.insert(rand);
            ListOfDates_2.insert(rand);

        }
        System.out.println(ListOfDates_0);
        for (Date val : ListOfDates_0) {
            if(val.getMonth() % 2 == 0){
                ListOfDates_1.remove(val);
            }
        }
        System.out.println(ListOfDates_1);

        Iterator<Date> it_3 = ListOfDates_2.iterator();
        while(it_3.hasNext()){
            Date s = it_3.next();
            if(s.getMonth() % 2 == 0){
                it_3.remove();
            }
        }
        System.out.println(ListOfDates_2);

        System.out.println(ListOfDates_0.min());
        System.out.println(ListOfDates_0.max());
        System.out.println(ListOfDates_2.min());
        System.out.println(ListOfDates_2.max());

        for (int i = 0; i < 20; i++){
            if(ListOfDates_2.search(ListOfDates_0.at(i))){
                ListOfDates_3.insert(ListOfDates_0.at(i));
                ListOfInts_dates.insert(ListOfDates_2.index(ListOfDates_0.at(i)));
            }
        }
        System.out.println(ListOfDates_2);
        System.out.println(ListOfDates_3);
        System.out.println(ListOfInts_dates);


        System.out.println(ListOfDates_0.size());
        System.out.println(ListOfDates_3.size());

    }

    static public String GeneratingRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
