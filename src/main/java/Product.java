
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Product {
    String Name, Price;
    HashMap<String, String> map = new HashMap<>();
    LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
    ArrayList<String> list = new ArrayList<>();

    void parsing(String username,String document,String element, String name1, String name2, String price1, String price2) throws IOException {
        //System.out.println("\n\n" + username);
        Document doc = Jsoup.connect(document).get();
        Elements products = doc.getElementsByClass(element);
        for (Element product : products) {

            if(name2 !=null) Name = product.getElementsByAttributeValue(name1, name2).text();
            else Name = product.getElementsByClass(name1).text();

            if(price2!=null) Price = product.getElementsByAttributeValue(price1, price2).text();
            else Price = product.getElementsByClass(price1).text();
            map.put(Name + "(" + username+")", Price);
        }
        /*for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sortedMap.put(entry.getKey(), str);
                }
            }
        }
        for (String s : sortedMap.keySet()) {
            System.out.println(map.get(s) + "\t" + s);
        }*/
    }

    void addingToListP(){
        for (String s : map.keySet()){
        Main.FinalMapPizza.put(s, map.get(s));
        }
    }

    void addingToListB(){
        for (String s : map.keySet()){
            Main.FinalMapBurger.put(s, map.get(s));
        }

    }void addingToListS(){
        for (String s : map.keySet()){
            Main.FinalMapSushi.put(s, map.get(s));
        }
    }

}
