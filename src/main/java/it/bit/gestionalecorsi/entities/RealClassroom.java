package it.bit.gestionalecorsi.entities;

public class RealClassroom extends Classroom{

    private Equipment tech;

    @Override
    public boolean hasComputer() {
        return tech == Equipment.PC || tech == Equipment.FULL;
    }

    @Override
    public boolean hasProjectors() {
        return tech == Equipment.PROJECTOR || tech == Equipment.FULL;
    }

}
