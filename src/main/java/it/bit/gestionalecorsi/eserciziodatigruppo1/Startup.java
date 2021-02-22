package it.bit.gestionalecorsi.eserciziodatigruppo1;

import java.nio.file.Path;


public class Startup {

    public static void main(String[] args) {

        String path = "dati_iscrizione.csv" ;

        // creazione parser
        Parser parser = new CsvParser(Path.of(path));

        try {
            // iniezione del parser sul controller
            Controller controller = new Controller(parser);

            // stampo il report
            controller.printReport();

        } catch (DataException e) {
            e.printStackTrace();
        }

    }
}
