import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class pars {


    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> prod = new ArrayList<>();
    ArrayList<String> document = new ArrayList<>();
    ArrayList<String> element = new ArrayList<>();
    ArrayList<String> name1 = new ArrayList<>();
    ArrayList<String> name2 = new ArrayList<>();
    ArrayList<String> price1 = new ArrayList<>();
    ArrayList<String> price2 = new ArrayList<>();
    HashMap<String, String> pizzaL = new HashMap<>();
    HashMap<String, String> sushiL = new HashMap<>();
    HashMap<String, String> burgerL = new HashMap<>();





    public void jdbc() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food", "account name", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from food.vn");

            while (resultSet.next()) {
                username.add(resultSet.getString("username"));
                prod.add(resultSet.getString("prod"));
                document.add(resultSet.getString("document"));
                element.add(resultSet.getString("element"));
                name1.add(resultSet.getString("name1"));
                name2.add(resultSet.getString("name2"));
                price1.add(resultSet.getString("price1"));
                price2.add(resultSet.getString("price2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*System.out.println(username);
        System.out.println(document);
        System.out.println(element);
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(price1);
        System.out.println(price2);*/


    }

    public void parser() throws IOException {
        for (int i = 0; i < prod.size(); i++){
            if (prod.get(i).equals("pizza")){
            Product p = new Product();
            p.parsing(username.get(i),
                    document.get(i),
                    element.get(i),
                    name1.get(i),
                    name2.get(i),
                    price1.get(i),
                    price2.get(i));
            p.addingToListP();
            }
            else if (prod.get(i).equals("sushi")){
                Product p = new Product();
                p.parsing(username.get(i),
                        document.get(i),
                        element.get(i),
                        name1.get(i),
                        name2.get(i),
                        price1.get(i),
                        price2.get(i));
                p.addingToListS();
            }
            else {
                Product p = new Product();
                p.parsing(username.get(i),
                        document.get(i),
                        element.get(i),
                        name1.get(i),
                        name2.get(i),
                        price1.get(i),
                        price2.get(i));
                p.addingToListB();
            }
        }

    }




/*
    void express_pizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(0),
                document.get(0),
                element.get(0),
                name1.get(0),
                name2.get(0),
                price1.get(0),
                price2.get(0)
        );
    }

    void la_ua_pizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(1),
                document.get(1),
                element.get(1),
                name1.get(1),
                name2.get(1),
                price1.get(1),
                price2.get(1)
        );
    }

    void prontop_pizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(2),
                document.get(2),
                element.get(2),
                name1.get(2),
                name2.get(2),
                price1.get(2),
                price2.get(2)
        );
    }

    void sushiwok() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(3),
                document.get(3),
                element.get(3),
                name1.get(3),
                name2.get(3),
                price1.get(3),
                price2.get(3)
        );
    }

    void sushiyama() throws IOException{
        Product p = new Product();
        p.parsing(
                username.get(4),
                document.get(4),
                element.get(4),
                name1.get(4),
                name2.get(4),
                price1.get(4),
                price2.get(4)
        );
    }

    void Expresspizzaburgers() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(5),
                document.get(5),
                element.get(5),
                name1.get(5),
                name2.get(5),
                price1.get(5),
                price2.get(5)
        );
    }

    void Expresspizzasushi() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(6),
                document.get(6),
                element.get(6),
                name1.get(6),
                name2.get(6),
                price1.get(6),
                price2.get(6)
        );
    }

    void Sametepizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(7),
                document.get(7),
                element.get(7),
                name1.get(7),
                name2.get(7),
                price1.get(7),
                price2.get(7)
        );

    }

    void Igogopizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(8),
                document.get(8),
                element.get(8),
                name1.get(8),
                name2.get(8),
                price1.get(8),
                price2.get(8)
        );

    }

    void Igogoburgers() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(9),
                document.get(9),
                element.get(9),
                name1.get(9),
                name2.get(9),
                price1.get(9),
                price2.get(9)
        );

    }

    void Igogosushi() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(10),
                document.get(10),
                element.get(10),
                name1.get(10),
                name2.get(10),
                price1.get(10),
                price2.get(10)
        );

    }

    void Smilefoodpizza() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(11),
                document.get(11),
                element.get(11),
                name1.get(11),
                name2.get(11),
                price1.get(11),
                price2.get(11)
        );

    }

    void Smilefoodrolls() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(12),
                document.get(12),
                element.get(12),
                name1.get(12),
                name2.get(12),
                price1.get(12),
                price2.get(12)
        );

    }

    void Smilefoodsushi() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(13),
                document.get(13),
                element.get(13),
                name1.get(13),
                name2.get(13),
                price1.get(13),
                price2.get(13)
        );

    }

    void Smilefoodsety() throws IOException {
        Product p = new Product();
        p.parsing(
                username.get(14),
                document.get(14),
                element.get(14),
                name1.get(14),
                name2.get(14),
                price1.get(14),
                price2.get(14)
        );

    }*/
}
