package com.optaplannernqueens.solver;

import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.ConstraintStreamImplType;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

import java.time.Duration;

public class CustomSolver {
    public static <T, E extends SolverEventListener<T>> T solve(T problem, Integer duration, Class<?> entityClass,
                                                                Class<? extends ConstraintProvider> constraintProviderClass,
                                                                E bestSolutionEventListener) {
        Class<?> problemClass = problem.getClass();
//        TerminationConfig terminationConfig =
//                new TerminationConfig()
//                        .withBestScoreLimit("0hard/4medium/-1000soft") // set the best score limit
//                                       .withBestScoreFeasible(true) // set to terminate on feasible solutions
//                                       .withScoreCalculationCountLimit(
//                                               100000L)
//                ; // set the maximum number of score calculations
        SolverFactory<T> solverFactory = SolverFactory.create(new SolverConfig().withSolutionClass(problemClass)
                                                                                .withEntityClasses(entityClass)
                                                                                .withConstraintProviderClass(
                                                                                        constraintProviderClass)
                                                                                .withTerminationSpentLimit(
                                                                                        Duration.ofSeconds(duration))
                                                                                .withConstraintStreamImplType(
                                                                                        ConstraintStreamImplType.BAVET)
//                                                                                .withTerminationConfig(
//                                                                                        terminationConfig)
                                                             );
        Solver<T> solver = solverFactory.buildSolver();
        if (bestSolutionEventListener != null) {
            solver.addEventListener(bestSolutionEventListener);
        }
        return solver.solve(problem);
    }
}
