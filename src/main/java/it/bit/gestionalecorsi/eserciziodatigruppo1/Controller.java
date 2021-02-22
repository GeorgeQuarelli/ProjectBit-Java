package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;
import it.bit.gestionalecorsi.entities.Gender;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private Parser parser;

    public Controller(Parser parser) throws DataException {
        this.parser = parser;
        this.parser.read();
    }

    public void printReport() {
        List<Enrollment> iscrizioni = (List<Enrollment>)parser.getEnrollments();

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
//      oppure iscrizioni.stream().mapToDouble(Enrollment::getGrade).average().getAsDouble();
//        System.out.println("Voto medio: " + ((double) Math.round(getAverageGrade() * 100)) / 100);
        System.out.println("La mediana è: " + getMediana(iscrizioni));
//        System.out.println("Il voto più ricorrente è: " + getModa(iscrizioni));
        System.out.println("Il voto più ricorrente è : " +
                iscrizioni.stream()
                        .collect(Collectors.groupingBy(Enrollment::getGrade, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey());

        System.out.println("Il minimo voto dei maschi è più alto del massimo delle femmine? " + isSexismAlive(iscrizioni));

    }

    private double getMediana(List<Enrollment> iscrizioni) {
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

    private double getModa(List<Enrollment> iscrizioni) {
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

        Set<Map.Entry<Double, Integer>> set = map.entrySet();
        return set.stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private boolean isSexismAlive(List<Enrollment> iscrizioni) {

        double maxFemmine = iscrizioni.stream()
                .filter((en) -> en.getStudent().getGender() == Gender.FEMALE)
                .max(Comparator.comparing(Enrollment::getGrade)).get().getGrade();
        double minMaschi = iscrizioni.stream()
                .filter((en) -> en.getStudent().getGender() == Gender.MALE)
                .min(Comparator.comparing(Enrollment::getGrade)).get().getGrade();

        return minMaschi > maxFemmine;
    }


}
