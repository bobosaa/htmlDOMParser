import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by heymn on 22.01.2019.
 */
public class Main {

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };

        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }


    public static void main(String[] args) {

        try {
            Document document = Jsoup.parse(new URL("https://www.google.com/"), 10000);
            Elements elements = document.body().select("*");
            HashMap<String, Integer> wordMap = new HashMap<>();



            for (Element element : elements) {

                String strLine = element.ownText();

                strLine = strLine.trim();
                strLine = strLine.toLowerCase();



                if (!"".equals(strLine)) {
                    String[] words = strLine.split(" ");

                    for (String word : words) {
                        word = word.replaceAll("(?ui)[^а-яёa-z]","");

                        if ("".equals(word)){
                            break;
                        }

                        if (wordMap.containsKey(word)) {
                            wordMap.put(word, wordMap.get(word) + 1);
                        } else {
                            wordMap.put(word, 1);
                        }
                    }
                }
            }

            Map sortedMap = sortByValues(wordMap);

            Set set = sortedMap.entrySet();
            Iterator i = set.iterator();


            if (sortedMap.isEmpty()) {
                System.out.println("Site is empty");
            }
            else {
                int countToExit = 1;
                int value = 0;
                String allKey = "";

                while((i.hasNext()) && (countToExit != 20)) {
                    Map.Entry answer = (Map.Entry)i.next();

                    if (value == 0){
                        value = (int) answer.getValue();
                        allKey = (String) answer.getKey()+"; ";

                    }else {
                        if ( (int) answer.getValue() == value){
                            allKey += (String) answer.getKey() + "; ";

                        } else {
                            allKey = allKey.substring(0, allKey.lastIndexOf(" ") - 1)+ " ";
                            System.out.println(allKey + " count " + value);
                            value = (int) answer.getValue();
                            allKey = (String) answer.getKey()+"; ";
                            countToExit ++;
                        }

                    }

                }

                allKey = allKey.substring(0, allKey.lastIndexOf(" ") - 1)+ " ";
                System.out.println(allKey + " count " + value);
            }


        }catch(IOException e){
            System.out.println("error");
            e.printStackTrace();
        }
    }

}


