package com.optaplannernqueens.service.impl;

import com.optaplannernqueens.constraintProvider.ChessTableConstraintProvider;
import com.optaplannernqueens.model.planningEntity.Queen;
import com.optaplannernqueens.model.planningSolution.ChessTable;
import com.optaplannernqueens.model.problemFactEntity.ChessColumn;
import com.optaplannernqueens.model.problemFactEntity.ChessRow;
import com.optaplannernqueens.service.ChessQueenService;
import com.optaplannernqueens.solver.CustomSolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChessQueenServiceImpl implements ChessQueenService {
    @Value("${chess.boardSize}")
    private String chessBoardSize;
    @Value("${chess.numOfQueens}")
    private String numOfQueens;
    @Value("${chess.solveDuration}")
    private String solveDuration;

    @Override
    public ChessTable solve() {
        ChessTable problem = getChessTable();
//        return problem;

        return CustomSolver.solve(problem, Integer.parseInt(solveDuration), Queen.class,
                                  ChessTableConstraintProvider.class, null);
    }

    private List<ChessRow> getRows() {
        int chessBoard = Integer.parseInt(chessBoardSize);
        List<ChessRow> list = new ArrayList<>();
        for (int i = 0; i < chessBoard; i++) {
            list.add(new ChessRow(i));
        }
        return list;
    }

    private List<ChessColumn> getColumns() {
        int chessBoard = Integer.parseInt(chessBoardSize);
        List<ChessColumn> list = new ArrayList<>();
        for (int i = 0; i < chessBoard; i++) {
            list.add(new ChessColumn(i));
        }
        return list;
    }

    private List<Queen> getQueens() {
        int queen = Integer.parseInt(numOfQueens);
        List<Queen> list = new ArrayList<>();
        for (int i = 0; i < queen; i++) {
            list.add(new Queen((long) i, new ChessColumn(), new ChessRow()));
        }
        return list;
    }

    private ChessTable getChessTable() {
        return new ChessTable(getRows(), getColumns(), getQueens());
    }
}
