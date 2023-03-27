# opta-planner-nqueens
OptaPlanner NQueens with more queens than necessary.

I used OptaPlanner for native Java app, not SpringBoot implementation of OptaPlanner, because of my future needs.

This is implementation of OptaPlanner for solving a problem of NQueens but with more queens than necessary. Example:

ChessBoard size = 4x4
Number of queens = 5

This is exampe of response that I get:
```json
{
    "rowList": [
        {
            "index": 0
        },
        {
            "index": 1
        },
        {
            "index": 2
        },
        {
            "index": 3
        }
    ],
    "columnList": [
        {
            "index": 0
        },
        {
            "index": 1
        },
        {
            "index": 2
        },
        {
            "index": 3
        }
    ],
    "queenList": [
        {
            "id": 0,
            "rowIndex": 1,
            "columnIndex": 3
        },
        {
            "id": 1,
            "rowIndex": 3,
            "columnIndex": 2
        },
        {
            "id": 2,
            "rowIndex": 0,
            "columnIndex": 1
        },
        {
            "id": 3,
            "rowIndex": 2,
            "columnIndex": 0
        },
        {
            "id": 4,
            "rowIndex": null,
            "columnIndex": 2
        }
    ],
    "score": {
        "initScore": 0,
        "hardScore": 0,
        "mediumScore": 4,
        "softScore": 0,
        "feasible": true,
        "zero": false,
        "solutionInitialized": true
    }
}
```

Be aware that you probably wouldn't get the same response, but 4 queens should be assigned while 5th queen should have either row or column assigned as null or both of them assigned as null.
