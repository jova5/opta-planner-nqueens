package com.optaplannernqueens.model.problemFactEntity;

public class ChessColumn {
    private Integer index;

    public ChessColumn() {
    }

    public ChessColumn(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "c=" + index;
    }
}
