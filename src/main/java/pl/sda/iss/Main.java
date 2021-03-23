package pl.sda.iss;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.iss.model.Coordinates;
import pl.sda.iss.model.PassTime;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                 .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coordinates.class)
                .addAnnotatedClass(PassTime.class)
                .buildSessionFactory();




    }
}
