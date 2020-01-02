import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
;

public class CM101 {
    public static void main(String[] args){
        ConcurrentHashMap<String, Long> cm = new ConcurrentHashMap<>();
        ConcurrentSkipListSet<String> skipList = new ConcurrentSkipListSet<>();
//        List<String> al = new LinkedList<>();

        System.out.println("-------------- CM101 ------------------");
        for(int i=0;i<400; i++){

            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));

            long leftLimit = 1L;
            long rightLimit = 10L;
            long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

            cm.put(generatedString,  generatedLong);
            skipList.add(generatedString);
        }

        cm.put("one",  1L);
        skipList.add("one");

        Long startTime1 = System.nanoTime();
        Long value1 = cm.get("one");
        Long endTime1 = System.nanoTime();
        System.out.println("ConcurrentHashMap.get" + ":" + value1 + " : " + (endTime1-startTime1));

        Long startTime2 = System.nanoTime();
        Boolean b2 = cm.keySet().contains("one");
        Long endTime2 = System.nanoTime();
        System.out.println("ConcurrentHashMap.keySet"+":"+b2 + " : " + (endTime2-startTime2));

        Long startTime4 = System.nanoTime();
        Boolean b4 = skipList.contains("one");
        Long endTime4 = System.nanoTime();

        System.out.println("skipList.contains"+":"+b4 + " : " + (endTime4-startTime4));

        String key = "abc";
        String value = "def";
        Node<String, String> node1 = new Node(key.hashCode(), key, value, null);
        Node<String, String> node2 = new Node(key.hashCode(), key, value, node1);
        System.out.println(node1);
        System.out.println(node2);

    }
}