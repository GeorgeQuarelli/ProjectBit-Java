package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;

import java.util.List;


public class Startup {

    public static void main(String[] args) {

        String path = "dati_iscrizione.csv" ;
        List<Enrollment> iscrizioni = ReadFromFile.getData(path);


    }
}
