package rules;

import model.CellState;

public interface Rule {

    /**
     * Calculates the next state based on the 8 neighbours.
     * CAUTION: Our actual field can be on a border, so some fields can be null.
     * @param north
     * @param northeast
     * @param east
     * @param southeast
     * @param south
     * @param southwest
     * @param west
     * @param northwest
     * @param actual
     * @return
     */
    public CellState next(
            CellState north,
            CellState northeast,
            CellState east,
            CellState southeast,
            CellState south,
            CellState southwest,
            CellState west,
            CellState northwest,
            CellState actual
    );

    default int countActive(
            CellState north,
            CellState northeast,
            CellState east,
            CellState southeast,
            CellState south,
            CellState southwest,
            CellState west,
            CellState northwest
    ){
        int numActive = 0;

        if(north == CellState.ACTIVE) numActive++;
        if(northeast == CellState.ACTIVE) numActive++;
        if(east == CellState.ACTIVE) numActive++;
        if(southeast == CellState.ACTIVE) numActive++;
        if(south == CellState.ACTIVE) numActive++;
        if(southwest == CellState.ACTIVE) numActive++;
        if(west == CellState.ACTIVE) numActive++;
        if(northwest == CellState.ACTIVE) numActive++;

        return numActive;
    }

}
