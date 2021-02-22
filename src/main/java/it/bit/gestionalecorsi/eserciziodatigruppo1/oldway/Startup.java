package it.bit.gestionalecorsi.eserciziodatigruppo1.oldway;

import it.bit.gestionalecorsi.entities.Enrollment;
import it.bit.gestionalecorsi.eserciziodatigruppo1.Controller;
import it.bit.gestionalecorsi.eserciziodatigruppo1.CsvParser;
import it.bit.gestionalecorsi.eserciziodatigruppo1.Parser;

import javax.sound.midi.ControllerEventListener;
import java.nio.file.Path;
import java.util.List;


public class Startup {

    public static void main(String[] args) {

        String path = "dati_iscrizione.csv" ;
        List<Enrollment> iscrizioni = ReadFromFile.getData(path);

        Report report = new Report(iscrizioni);
        report.printReport();
    }
}
