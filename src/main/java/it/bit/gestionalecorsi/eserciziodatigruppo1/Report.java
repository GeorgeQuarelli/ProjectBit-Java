package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;

import java.time.LocalDate;
import java.util.*;

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
        System.out.println("Voto più alto: " + getGrade(Comparator.naturalOrder()));
        System.out.println("Voto più basso: " + getGrade(Comparator.reverseOrder()));
        System.out.println("Voto più basso: " + getMinGrade());
        System.out.println("Voto medio: " + ((double) Math.round(getAverageGrade() * 100)) / 100);
        System.out.println("La mediana è: " + getMediana());
        System.out.println("Il voto più ricorrente è: " + getModa());


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

    public double getMediana() {
        Collections.sort(iscrizioni, Comparator.comparing(Enrollment::getGrade));
        //iscrizioni.forEach((o) -> System.out.println(o.getGrade()));
        if (iscrizioni.size() % 2 == 0) {
            int primoIndice = iscrizioni.size() / 2 - 1;   //prendo il primo elemento centrale
            int secondoIndice = iscrizioni.size() / 2;   //prendo il secondo elemento centrale

            double primoVoto = iscrizioni.get(primoIndice).getGrade();
            double secondoVoto = iscrizioni.get(secondoIndice).getGrade();

            return (primoVoto + secondoVoto) / 2;
        } else {
            return iscrizioni.get(iscrizioni.size() / 2).getGrade();
        }
    }

    public double getModa() {
        Map<Double, Integer> map = new HashMap<>();

        for (Enrollment e : iscrizioni) {
            if (!map.containsKey(e.getGrade())) {
                map.put(e.getGrade(), 1);
            } else {

                map.put(e.getGrade(), map.get(e.getGrade()) + 1);
            }
        }

        //map.forEach((k,v)-> System.out.println(k + " " + v));
        int ricorrenzaMaggiore = 0;
        double moda = 0;
        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
            if (entry.getValue() > ricorrenzaMaggiore) {
                ricorrenzaMaggiore = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }


}



