package it.bit.gestionalecorsi.eserciziodatigruppo1.oldway;

import it.bit.gestionalecorsi.entities.Enrollment;
import it.bit.gestionalecorsi.entities.Gender;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {

    private List<Enrollment> iscrizioni;


    public Report(List<Enrollment> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    public void printReport() {
        System.out.println("**************Report***************");
//        System.out.println("Data di iscrizione più recente: " + getDate(Comparator.naturalOrder()));
        System.out.println("Data di iscrizione più recente: " + iscrizioni.stream().max(Comparator.comparing(Enrollment::getEnrollDate)).get().getEnrollDate());
//        System.out.println("Data di iscrizione più vecchia: " + getDate(Comparator.reverseOrder()));
        System.out.println("Data di iscrizione più vecchia: " + iscrizioni.stream().min(Comparator.comparing(Enrollment::getEnrollDate)).get().getEnrollDate());
        System.out.println("Voto più alto: " + iscrizioni.stream().max(Comparator.comparing(Enrollment::getGrade)).get().getGrade());
//        System.out.println("Voto più alto: " + getGrade(Comparator.naturalOrder()));
//        System.out.println("Voto più basso: " + getGrade(Comparator.reverseOrder()));
        System.out.println("Voto più basso: " + iscrizioni.stream().min(Comparator.comparing(Enrollment::getGrade)).get().getGrade());
        System.out.println("Voto medio: " + iscrizioni.stream().reduce(0.0, (sum, element) -> sum + element.getGrade(), Double::sum) / iscrizioni.size());
//        iscrizioni.stream().mapToDouble(Enrollment::getGrade).average().getAsDouble();
//        System.out.println("Voto medio: " + ((double) Math.round(getAverageGrade() * 100)) / 100);
        System.out.println("La mediana è: " + getMediana());
        System.out.println("Il voto più ricorrente è: " + getModa());
        System.out.println("Il voto più ricorrente è : " +
                iscrizioni.stream()
                  .collect(Collectors.groupingBy(Enrollment::getGrade, Collectors.counting()))
                  .entrySet()
                  .stream()
                  .max(Map.Entry.comparingByValue())
                  .get()
                  .getKey());

//        Stream<Enrollment> d = iscrizioni.stream().filter(s -> s.getStudent().getGender() == Gender.FEMALE);
//        long n = d.filter(el -> el.getEnrollDate().isAfter(LocalDate.now())).count();
//        long p = d.filter(el -> el.getEnrollDate().isBefore(LocalDate.now())).count();  // stream già utilizzato
//
//        long n2 = iscrizioni.stream().filter(s -> s.getStudent().getGender() == Gender.FEMALE)
//                .filter(el -> el.getEnrollDate().isAfter(LocalDate.now()))
//                .count();
//        long p2 = iscrizioni.stream().filter(s -> s.getStudent().getGender() == Gender.FEMALE)
//                .filter(el -> el.getEnrollDate().isBefore(LocalDate.now()))
//                .count();
//
//        List<Enrollment> lista = iscrizioni.stream()
//                .filter(s -> s.getStudent().getGender() == Gender.FEMALE)
//                .collect(Collectors.toList());
//        long n3 = lista.stream()
//                .filter(el -> el.getEnrollDate().isBefore(LocalDate.now()))
//                .count();
//        long p3 = lista.stream()
//                .filter(el -> el.getEnrollDate().isAfter(LocalDate.now()))
//                .count();
        System.out.println("Il minimo voto dei maschi è più alto del massimo delle femmine? " + isSexismAlive());

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
//
//    public LocalDate getMostRecentDate() {
//        LocalDate mostRecent = iscrizioni.get(0).getEnrollDate();
//        for (Enrollment e : iscrizioni) {
//            int compare = e.getEnrollDate().compareTo(mostRecent);
//            if (compare > 0) {
//                mostRecent = e.getEnrollDate();
//            }
//        }
//        return mostRecent;
//    }
//
//    public LocalDate getOldestDate() {
//        LocalDate oldest = iscrizioni.get(0).getEnrollDate();
//        for (Enrollment e : iscrizioni) {
//            int compare = e.getEnrollDate().compareTo(oldest);
//            if (compare < 0) {
//                oldest = e.getEnrollDate();
//            }
//        }
//        return oldest;
//
//    }

    public double getGrade(Comparator<Double> comparator) {

        double grade = iscrizioni.get(0).getGrade();
        for (Enrollment g : iscrizioni) {
            if (comparator.compare(g.getGrade(), grade) > 0)
                grade = g.getGrade();
        }
        return grade;
    }

//    public double getMaxGrade() {
//        double maxGrade = iscrizioni.get(0).getGrade();
//        for (Enrollment g : iscrizioni) {
//
//            if (g.getGrade() > maxGrade) {
//                maxGrade = g.getGrade();
//            }
//        }
//        return maxGrade;
//    }
//
//    public double getMinGrade() {
//        double minGrade = iscrizioni.get(0).getGrade();
//        for (Enrollment g : iscrizioni) {
//
//            if (g.getGrade() < minGrade) {
//                minGrade = g.getGrade();
//            }
//        }
//        return minGrade;
//    }

    public double getAverageGrade() {
        double somma = 0;


        for (Enrollment m : iscrizioni) {
            somma += m.getGrade();
        }
        return somma / iscrizioni.size();
    }

    public double getMediana() {
        iscrizioni.sort(Comparator.comparing(Enrollment::getGrade));
        //iscrizioni.forEach((o) -> System.out.println(o.getGrade()));
        if (iscrizioni.size() % 2 == 0) {
            int primoIndice = iscrizioni.size() / 2 - 1;   //prendo l'indice del primo elemento centrale
            int secondoIndice = iscrizioni.size() / 2;   //prendo l'indice del secondo elemento centrale

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
            // altra implementazione
//            Integer value = map.putIfAbsent(e.getGrade(), 1);
//            if(value != null) {
//                map.put(e.getGrade(), value + 1);
//            }
        }

        //map.forEach((k,v)-> System.out.println(k + " " + v));
        // obsoleto
        int ricorrenzaMaggiore = 0;
        double moda = 0;
//        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > ricorrenzaMaggiore) {
//                ricorrenzaMaggiore = entry.getValue();
//                moda = entry.getKey();
//            }
//        }

        Set<Map.Entry<Double, Integer>> set = map.entrySet();
//        moda = set.stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
        moda = set.stream().max(Map.Entry.comparingByValue()).get().getKey();

        return moda;
    }

    public boolean isSexismAlive() {

        double maxFemmine = iscrizioni.stream()
                .filter((en) -> en.getStudent().getGender() == Gender.FEMALE)
                .max(Comparator.comparing(Enrollment::getGrade)).get().getGrade();
        double minMaschi = iscrizioni.stream()
                .filter((en) -> en.getStudent().getGender() == Gender.MALE)
                .min(Comparator.comparing(Enrollment::getGrade)).get().getGrade();

        return minMaschi > maxFemmine;
    }


}
