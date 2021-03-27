package pl.sda.iss;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.iss.model.Coordinates;
import pl.sda.iss.model.PassTime;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coordinates.class)
                .addAnnotatedClass(PassTime.class)
                .buildSessionFactory();

        Menu menu = new Menu(sessionFactory);
        menu.showMenu();







   }
}
