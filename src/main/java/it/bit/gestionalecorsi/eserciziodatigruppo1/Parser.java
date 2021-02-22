package it.bit.gestionalecorsi.eserciziodatigruppo1;

import it.bit.gestionalecorsi.entities.Enrollment;

import java.util.Collection;

public interface Parser {

    void read() throws DataException;

    Collection<Enrollment> getEnrollments();
}
