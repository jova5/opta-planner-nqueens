package com.optaplannernqueens.constraintProvider;

import com.optaplannernqueens.model.planningEntity.Queen;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class ChessTableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{oneQueenInTheField(constraintFactory), rowConflict(constraintFactory),
                columnConflict(constraintFactory), diagonalConflict(constraintFactory),
                rewardAssignedQueens(constraintFactory), anyPositionAssignedWithNull(constraintFactory)};
    }

    Constraint oneQueenInTheField(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getRowIndex),
                                      Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
                                .filter((queen1, queen2) -> queen1.getRowIndex() != null &&
                                                            queen1.getColumnIndex() != null &&
                                                            queen2.getRowIndex() != null &&
                                                            queen2.getColumnIndex() != null)
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("One queen at most in the field");
    }

    Constraint rowConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getRowIndex), Joiners.lessThan(Queen::getId))
                                .filter((queen, queen2) -> queen.getRowIndex() != null && queen2.getRowIndex() != null)
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("Queens on same row");
    }

    Constraint columnConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
                                .filter((queen, queen2) -> queen.getColumnIndex() != null &&
                                                           queen2.getColumnIndex() != null)
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("Queens on same column");
    }

    Constraint diagonalConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.lessThan(Queen::getId))
                                .filter((queen1, queen2) -> queen1.getRowIndex() != null &&
                                                            queen1.getColumnIndex() != null &&
                                                            queen2.getRowIndex() != null &&
                                                            queen2.getColumnIndex() != null)
                                .filter((queen1, queen2) -> Math.abs(queen1.getRowIndex() - queen2.getRowIndex()) ==
                                                            Math.abs(queen1.getColumnIndex() - queen2.getColumnIndex()))
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("Queens on same diagonal");
    }

    Constraint rewardAssignedQueens(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .filter((queen1) -> queen1.getRowIndex() != null && queen1.getColumnIndex() != null)
                                .reward(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("Reward Assigned Queens");
    }

    Constraint anyPositionAssignedWithNull(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .filter(queen -> queen.getRowIndex() == null || queen.getColumnIndex() == null)
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("Any Position Assigned With Null");
    }
}
