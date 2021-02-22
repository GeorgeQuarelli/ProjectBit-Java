package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;
import it.bit.gestionalecorsi.entities.Gender;
import it.bit.gestionalecorsi.entities.Student;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CsvParser implements Parser {

    private Path path;

    private List<Enrollment> iscrizioni = new ArrayList<>();

    public CsvParser(Path filepath) {
        this.path = filepath;
    }

    @Override
    public void read() throws DataException {
        //apertura file
        try (Scanner sc = new Scanner(new File(path.toString()))) {

            sc.useDelimiter(",");   //sets the delimiter pattern

            // date formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            while (sc.hasNext()) {

                //find and returns the next complete token from this scanner
                String name = sc.next();
                String surname = sc.next();
                String gender = sc.next();
                LocalDate localDate = LocalDate.parse(sc.next(), formatter);
                int idCourseEdition = sc.nextInt();
                String courseTitle = sc.next();
                boolean payed = sc.nextBoolean();
                double grade = Double.parseDouble(sc.next().trim());

                //istanziare enrollment e aggiungere alla lista
                iscrizioni.add(createEnroll(name, surname, gender, localDate, idCourseEdition, payed, grade));
            }

        } catch (FileNotFoundException e) {
            throw new DataException("File non trovato!", e);
        }

    }

    private Enrollment createEnroll(String name, String surname, String gender, LocalDate localDate,
                                    int idCourseEdition, boolean payed, double grade) {

        Gender g = gender.equals("m") ? Gender.MALE : Gender.FEMALE;
        Student s = new Student(name, surname, g);

        return new Enrollment(s, null, localDate, payed, grade);
    }

    @Override
    public Collection<Enrollment> getEnrollments() {
        return iscrizioni;
    }
}
