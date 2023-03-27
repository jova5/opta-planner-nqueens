package com.optaplannernqueens.model.planningSolution;

import com.optaplannernqueens.model.planningEntity.Queen;
import com.optaplannernqueens.model.problemFactEntity.ChessColumn;
import com.optaplannernqueens.model.problemFactEntity.ChessRow;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

import java.util.List;

@PlanningSolution
public class ChessTable {
    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<ChessRow> rowList;
    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<ChessColumn> columnList;
    @PlanningEntityCollectionProperty
    private List<Queen> queenList;
    @PlanningScore
    private HardMediumSoftScore score;

    public ChessTable() {
    }

    public ChessTable(List<ChessRow> rowList, List<ChessColumn> columnList, List<Queen> queenList) {
        this.rowList = rowList;
        this.columnList = columnList;
        this.queenList = queenList;
    }


    public List<ChessRow> getRowList() {
        return rowList;
    }

    public List<ChessColumn> getColumnList() {
        return columnList;
    }


    public List<Queen> getQueenList() {
        return queenList;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
    }
}
