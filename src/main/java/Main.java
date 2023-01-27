import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.*;

public class Main {

    public static HashMap<String, String> FinalMapPizza = new HashMap<String, String>();
    public static HashMap<String, String> FinalMapSushi = new HashMap<String, String>();
    public static HashMap<String, String> FinalMapBurger = new HashMap<String, String>();

    public static List<String> pizzaList = new ArrayList<String>();
    public static List<String> sushiList = new ArrayList<String>();
    public static List<String> burgerList = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        pars pars = new pars();
        pars.jdbc();
        pars.parser();

        /*for (String name: FinalMapPizza.keySet()) {
            String key = name;
            String value = FinalMapPizza.get(name);
            System.out.println(key + " " + value);
        }
        System.out.println();
        for (String name: FinalMapSushi.keySet()) {
            String key = name;
            String value = FinalMapSushi.get(name);
            System.out.println(key + " " + value);
        }
        System.out.println();
        for (String name: FinalMapBurger.keySet()) {
            String key = name;
            String value = FinalMapBurger.get(name);
            System.out.println(key + " " + value);
        }*/

        Main.sortedFinalMapAndWriteInFinalList(FinalMapPizza, pizzaList);
        Main.sortedFinalMapAndWriteInFinalList(FinalMapBurger, burgerList);
        Main.sortedFinalMapAndWriteInFinalList(FinalMapSushi, sushiList);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot2());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        /*System.out.println(FinalMapBurger);
        System.out.println(FinalMapPizza);
        System.out.println(FinalMapSushi);*/

        System.out.println(pizzaList);
    }

    static void sortedFinalMapAndWriteInFinalList(HashMap<String, String> FinalMap, List<String> listName){
        LinkedHashMap<String, String> sFM = new LinkedHashMap<String, String>();
        ArrayList<String> lF = new ArrayList<String>();
        for (Map.Entry<String, String> entry : FinalMap.entrySet()) {
            lF.add(entry.getValue());
        }
        Collections.sort(lF, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : lF) {
            for (Map.Entry<String, String> entry : FinalMap.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sFM.put(entry.getKey(), str);
                }
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("sleep err");
        }
        for (String s : sFM.keySet()) {
            listName.add(FinalMap.get(s)/* + "UAH " */ + " " + s);
        }
    }

}
