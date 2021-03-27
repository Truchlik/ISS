package pl.sda.iss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import pl.sda.iss.DAO.JsonPassTimes;
import pl.sda.iss.DAO.PassTimesDAO;
import pl.sda.iss.model.Coordinates;
import pl.sda.iss.model.PassTime;
import pl.sda.iss.repository.CoordinatesRepository;
import pl.sda.iss.repository.PassTimeRepository;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    SessionFactory sessionFactory;
    boolean exit = false;

    public Menu(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void showMenu() throws IOException {
        do {
            System.out.println("\n\nWybierz opcję :");
            System.out.println("1 - prędkość ISS");
            System.out.println("2 - lista nadchodzących przebiegów ISS dla określonej lpokalizacji");
            System.out.println("3 - ilość osób na ISS");
            System.out.println("4 - wyjście");

            chooseMenuOption();
        }while(!exit);
    }

    public void chooseMenuOption() throws IOException {

        String  option = "";


                option = scanner.nextLine();



        switch (option) {
            case "1":
                callIssSpeedOption();
                break;
            case "2":
                callPassTimeOption();
                break;
            case "3":
                callIssCrewOption();
                break;
            case "4":
                exit = true;
                break;
            default:
                System.out.println("Musisz podać liczbę całkowitą od 1 do 4, spróbuj jeszcze raz.");

        }
    }


    public void callIssSpeedOption(){

    }



    public void callPassTimeOption() throws IOException {
        System.out.print("Podaj szerokość geograficzną: ");
        double latitude = scanner.nextDouble();
        System.out.print("Podaj długość geograficzną: ");
        double longitude = scanner.nextDouble();
        System.out.print("Podaj ilość przebiegów: ");
        int numberOfPasses = scanner.nextInt();

        PassTimesDAO passTimesDAO = new PassTimesDAO();
        String json = passTimesDAO.getPassTimes(latitude, longitude, numberOfPasses);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonPassTimes jsonPassTimes = objectMapper
                .readValue(json, JsonPassTimes.class);
        String response = jsonPassTimes.getResponse().toString();

        List<PassTime> passTimes = objectMapper.readerForListOf(PassTime.class).readValue(response);

        EntityManager entityManager = sessionFactory.createEntityManager();

        CoordinatesRepository coordinatesRepository = new CoordinatesRepository(entityManager);
        PassTimeRepository passTimeRepository = new PassTimeRepository(entityManager);

        Coordinates coordinates = new Coordinates(latitude, longitude, new ArrayList<>());
        coordinatesRepository.save(coordinates);


        for (PassTime passTime : passTimes) {
            passTime.setCoordinates(coordinates);
            passTimeRepository.save(passTime);
        }
        System.out.println("\n\nNadchodzące przebiegi dla podanych koordynatów:");
        for (PassTime passTime : passTimes) {
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(passTime.getRisetime(),0, ZoneOffset.UTC);
            System.out.println(dateTime + " będzie trwać " + passTime.getDuration() + " sekund.");

        }
    }

    public void callIssCrewOption(){

    }


}
