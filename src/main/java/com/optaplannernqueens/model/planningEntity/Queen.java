package com.optaplannernqueens.model.planningEntity;

import com.optaplannernqueens.model.problemFactEntity.ChessColumn;
import com.optaplannernqueens.model.problemFactEntity.ChessRow;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Queen {
    @PlanningId
    private Long id;
    @PlanningVariable(nullable = true)
    private ChessColumn column;
    @PlanningVariable(nullable = true)
    private ChessRow row;

    public Queen() {
    }

    public Queen(Long id, ChessColumn column, ChessRow row) {
        this.id = id;
        this.column = column;
        this.row = row;
    }


    public Long getId() {
        return id;
    }

    public Integer getColumnIndex() {
        if (this.column != null){
            return this.column.getIndex();
        }
        return null;
    }

    public Integer getRowIndex() {
        if (this.row != null){
            return this.row.getIndex();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Queen{" + "id=" + id + ", " + column + ", " + row + '}';
    }
}

