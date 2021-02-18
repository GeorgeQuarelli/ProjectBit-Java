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

        while (sc.hasNext())
        {
            //lettura file riga per riga

              //find and returns the next complete token from this scanner
            String name = sc.next();
            String surname = sc.next();
            String gender = sc.next();
            LocalDate localDate = LocalDate.parse(sc.next(), formatter);
            int idCourseEdition = sc.nextInt();
            String courseTitle = sc.next();
            boolean payed = sc.nextBoolean();
            double grade = Double.parseDouble(sc.next().trim());
//            System.out.print(name);

            //istanziare enrollment e aggiungere alla lista
            list.add(createEnroll(name, surname,gender, localDate,idCourseEdition, payed, grade));
        }
        sc.close();  //closes the scanner

        //ritornare la lista
        return list;
    }

    private static Enrollment createEnroll(String name, String surname,String gender, LocalDate localDate,
                                   int idCourseEdition, boolean payed, double grade) {

        Gender g = gender.equals("m")?Gender.MALE:Gender.FEMALE;
        Student s = new Student(name,surname, g);

        Enrollment sign_student = new Enrollment(s, null, localDate, payed, grade);
        return sign_student;
    }

}
