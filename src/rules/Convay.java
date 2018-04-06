package rules;

import model.CellState;

public class Convay implements Rule {
    @Override
    public CellState next(CellState north, CellState northeast, CellState east, CellState southeast, CellState south, CellState southwest, CellState west, CellState northwest, CellState actual) {
        int numActive = countActive(north, northeast, east, southeast, south, southwest, west,northwest);

        if(actual == CellState.INACTIVE){
            if(numActive == 3) return CellState.ACTIVE;
            else return CellState.INACTIVE;
        }else {
            if(numActive <= 1) return CellState.INACTIVE;
            if(numActive <= 3) return CellState.ACTIVE;
            else return CellState.INACTIVE;
        }
    }
}
