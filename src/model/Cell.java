package model;

public class Cell {

    /**
     * The actual state of the cell.
     */
    private CellState state;

    /**
     * Set the cellstate to active with the given probability.
     * A higher probability will result in more active fields.
     * @param probability between 0.0 and 1.0
     */
    public Cell(double probability){
        this.state = Math.random() < probability ? CellState.ACTIVE : CellState.INACTIVE;
    }

    public Cell(CellState state){
        this.state = state;
    }

    public CellState getState() {
        return state;
    }
}
