package com.optaplannernqueens.constraintProvider;

import com.optaplannernqueens.model.planningEntity.Queen;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class ChessTableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{oneQueenInTheField(constraintFactory), rowConflict(constraintFactory),
                columnConflict(constraintFactory), diagonalConflict(constraintFactory),
                onePositionNotNullOtherNull(constraintFactory), positionNotNull(constraintFactory)};
    }

//    FIRST SOLUTION

//    Constraint oneQueenInTheField(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class)
//                                .join(Queen.class, Joiners.equal(Queen::getRowIndex),
//                                      Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
//                                .penalize(HardSoftScore.ONE_HARD)
//                                .asConstraint("One Queen In Field");
//    }
//
//    Constraint rowConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class).join(Queen.class, Joiners.equal(Queen::getRowIndex))
////                                .filter((queen1, queen2) -> Objects.equals(queen1.getRowIndex(), queen2.getRowIndex
////                                ()))
//                                .penalize(HardSoftScore.ONE_HARD).asConstraint("Row Constraint");
//    }
//
//    Constraint columnConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class).join(Queen.class, Joiners.equal(Queen::getColumnIndex))
////                                .filter((queen1, queen2) -> Objects.equals(queen1.getColumnIndex(), queen2
////                                .getColumnIndex()))
//                                .penalize(HardSoftScore.ONE_HARD).asConstraint("Column Constraint");
//    }
//
//    Constraint diagonalConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class).join(Queen.class).filter((queen1, queen2) -> {
//            int row = Math.abs(queen1.getRowIndex() - queen2.getRowIndex());
//            int column = Math.abs(queen1.getColumnIndex() - queen2.getColumnIndex());
//            return row == column;
//        }).penalize(HardSoftScore.ONE_HARD).asConstraint("Diagonal Constraint");
//    }


//    SECOND SOLUTION

//    Constraint oneQueenInTheField(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class)
//                                .join(Queen.class, Joiners.equal(Queen::getRowIndex),
//                                      Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
//                                .penalize(HardSoftScore.ONE_HARD)
//                                .asConstraint("One queen at most in the field");
//    }
//
//    Constraint positionNotNull(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class)
//                                .filter((queen) -> queen.getRowIndex() == null && queen.getColumnIndex() == null)
//                                .penalize(HardSoftScore.ONE_HARD)
//                                .asConstraint("positionNotNull");
//    }
//
//    Constraint rowConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class)
//                                .join(Queen.class, Joiners.equal(Queen::getRowIndex), Joiners.lessThan(Queen::getId))
//                                .penalize(HardSoftScore.ONE_HARD)
//                                .asConstraint("Row conflict");
//    }
//
//    Constraint columnConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class)
//                                .join(Queen.class, Joiners.equal(Queen::getColumnIndex), Joiners.lessThan
//                                (Queen::getId))
//                                .penalize(HardSoftScore.ONE_HARD)
//                                .asConstraint("Column conflict");
//    }
//
//    Constraint diagonalConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class).join(Queen.class)
//                                .filter((queen1, queen2) -> queen1.getRowIndex() != null && queen1.getColumnIndex()
//                                != null
//                                                            && queen2.getRowIndex() != null && queen2
//                                                            .getColumnIndex() != null)
//                                .filter((queen1, queen2) -> {
//            int row = Math.abs(queen1.getRowIndex() - queen2.getRowIndex());
//            int column = Math.abs(queen1.getColumnIndex() - queen2.getColumnIndex());
//            return row == column;
//        }).penalize(HardSoftScore.ONE_HARD).asConstraint("Diagonal conflict");
//    }
//
//    Constraint onePositionNotNullOtherNull(ConstraintFactory constraintFactory) {
//        return constraintFactory.forEach(Queen.class).filter(queen -> {
//            if (queen.getColumnIndex() == null && queen.getRowIndex() != null) {
//                return true;
//            } else if (queen.getColumnIndex() != null && queen.getRowIndex() == null) {
//                return true;
//            } else {
//                return false;
//            }
//        }).penalize(HardSoftScore.ONE_HARD).asConstraint("onePositionNotNullOtherNull");
//    }

//    THIRD SOLUTION

    Constraint oneQueenInTheField(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getRowIndex),
                                      Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
                                .penalize(HardSoftScore.ONE_HARD)
                                .asConstraint("One queen at most in the field");
    }

    Constraint rowConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getRowIndex), Joiners.lessThan(Queen::getId))
                                .penalize(HardSoftScore.ONE_HARD)
                                .asConstraint("Queens on same row");
    }


    Constraint columnConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .join(Queen.class, Joiners.equal(Queen::getColumnIndex), Joiners.lessThan(Queen::getId))
                                .penalize(HardSoftScore.ONE_HARD)
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
                                .penalize(HardSoftScore.ONE_HARD)
                                .asConstraint("Queens on same diagonal");
    }

    Constraint positionNotNull(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class)
                                .filter(queen -> queen.getRowIndex() == null && queen.getColumnIndex() == null)
                                .penalize(HardSoftScore.ONE_HARD)
                                .asConstraint("Position must not be null");
    }

    Constraint onePositionNotNullOtherNull(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Queen.class).filter(queen -> {
            if (queen.getColumnIndex() == null && queen.getRowIndex() == null) {
                return false;
            }
            return queen.getRowIndex() == null || queen.getColumnIndex() == null;
        }).penalize(HardSoftScore.ONE_HARD).asConstraint("Only one position is null");
    }
}
