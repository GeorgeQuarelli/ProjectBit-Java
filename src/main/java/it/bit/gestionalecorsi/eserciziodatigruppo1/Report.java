package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Report {

    private List<Enrollment> iscrizioni;


    public Report(List<Enrollment> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    public void printReport() {
        System.out.println("**************Report***************");
        System.out.println("Data di iscrizione più recente: " + getMostRecentDate().toString());
        System.out.println("Data di iscrizione più recente: " + getDate(Comparator.naturalOrder()));
        System.out.println("Data di iscrizione più vecchia: " + getDate(Comparator.reverseOrder()));
        System.out.println("Data di iscrizione più vecchia: " + getOldestDate().toString());
        System.out.println("Voto più alto: " + getMaxGrade());
        System.out.println("Voto più alto: " + getGrade((d1, d2) -> (int) (d1 * 100 - d2 * 100)));
        System.out.println("Voto più basso: " + getGrade((d1, d2) -> (int) (d2 * 100 - d1 * 100)));
        System.out.println("Voto più basso: " + getMinGrade());
        System.out.println("Voto medio: " + ((double) Math.round(getAverageGrade() * 100)) / 100);
    }

    public LocalDate getDate(Comparator<LocalDate> comparator) {
        LocalDate date = iscrizioni.get(0).getEnrollDate();
        for (Enrollment e : iscrizioni) {
            if (comparator.compare(e.getEnrollDate(), date) > 0) {
                date = e.getEnrollDate();
            }
        }
        return date;
    }

    public LocalDate getMostRecentDate() {
        LocalDate mostRecent = iscrizioni.get(0).getEnrollDate();
        for (Enrollment e : iscrizioni) {
            int compare = e.getEnrollDate().compareTo(mostRecent);
            if (compare > 0) {
                mostRecent = e.getEnrollDate();
            }
        }
        return mostRecent;
    }

    public LocalDate getOldestDate() {
        LocalDate oldest = iscrizioni.get(0).getEnrollDate();
        for (Enrollment e : iscrizioni) {
            int compare = e.getEnrollDate().compareTo(oldest);
            if (compare < 0) {
                oldest = e.getEnrollDate();
            }
        }
        return oldest;

    }

    public double getGrade(Comparator<Double> comparator) {
        double grade = iscrizioni.get(0).getGrade();
        for (Enrollment g : iscrizioni) {
            if (comparator.compare(g.getGrade(), grade) > 0)
                grade = g.getGrade();
        }
        return grade;
    }

    public double getMaxGrade() {
        double maxGrade = iscrizioni.get(0).getGrade();
        for (Enrollment g : iscrizioni) {

            if (g.getGrade() > maxGrade) {
                maxGrade = g.getGrade();
            }
        }
        return maxGrade;
    }

    public double getMinGrade() {
        double minGrade = iscrizioni.get(0).getGrade();
        for (Enrollment g : iscrizioni) {

            if (g.getGrade() < minGrade) {
                minGrade = g.getGrade();
            }
        }
        return minGrade;
    }

    public double getAverageGrade() {
        double somma = 0;


        for (Enrollment m : iscrizioni) {
            somma += m.getGrade();
        }
        return somma / iscrizioni.size();
    }

}
