package pl.sda.iss;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.iss.DAO.JsonPassTimesReader;
import pl.sda.iss.DAO.PassTimesDAO;
import pl.sda.iss.model.Coordinates;
import pl.sda.iss.model.PassTime;
import pl.sda.iss.repository.CoordinatesRepository;
import pl.sda.iss.repository.PassTimeRepository;


import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coordinates.class)
                .addAnnotatedClass(PassTime.class)
                .buildSessionFactory();




        PassTimesDAO passTimesDAO = new PassTimesDAO();
        Coordinates coordinates = new Coordinates(11.34,-23.567, new ArrayList<>());
        String json = passTimesDAO.getPassTimes(coordinates.getLatitude(), coordinates.getLongitude(), 7);
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonPassTimesReader jsonPassTimesReader = objectMapper
                .readValue(json, JsonPassTimesReader.class);

        String response = jsonPassTimesReader.getResponse().toString();

        List<PassTime> passTimes = objectMapper.readerForListOf(PassTime.class).readValue(response);

        EntityManager entityManager = sessionFactory.createEntityManager();

        CoordinatesRepository coordinatesRepository = new CoordinatesRepository(entityManager);
        PassTimeRepository passTimeRepository = new PassTimeRepository(entityManager);




        coordinatesRepository.save(coordinates);

        for (PassTime passTime : passTimes) {
            passTime.setCoordinates(coordinates);
            passTimeRepository.save(passTime);

        }










    }
}
