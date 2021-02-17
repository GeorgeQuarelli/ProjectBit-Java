package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;
import it.bit.gestionalecorsi.entities.Gender;
import it.bit.gestionalecorsi.entities.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {

    public static List<Enrollment> getData(String pathfile) {

        //apertura file

        Scanner sc = null;
        try {
            sc = new Scanner(new File(pathfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(",");   //sets the delimiter pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        List<Enrollment> list = new ArrayList<>();
        while (sc.hasNext())  //returns a boolean value
        {

            //System.out.print(sc.next());  //find and returns the next complete token from this scanner
            String name = sc.next();
            String surname = sc.next();
            String gender = sc.next();
            LocalDate localDate = LocalDate.parse(sc.next(), formatter);
            int idCourseEdition = sc.nextInt();
            boolean payed = sc.nextBoolean();
            double grade = sc.nextDouble();

        }
        sc.close();  //closes the scanner

        //lettura file riga per riga


        //istanziare i vari enrollment


        //aggiungere alla lista


        //ritornare la lista

        return null;
    }

    public Enrollment createEnroll(String name, String surname,String gender, LocalDate localDate,
                                   int idCourseEdition, boolean payed, double grade) {

        Gender g = Gender.MALE;
        g.

        Student s = new Student(name,surname);




        Enrollment sign_student = new Enrollment();
        return sign_student;
    }

}
