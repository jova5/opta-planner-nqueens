package com.optaplannernqueens.model.problemFactEntity;

public class ChessRow {
    private Integer index;

    public ChessRow() {
    }

    public ChessRow(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "r=" + index;
    }
}
