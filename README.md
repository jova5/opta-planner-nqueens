# opta-planner-nqueens
OptaPlanner NQueens with more queens than needed

This is implementation of OptaPlanner for solving a problem of NQueens but with more queens than necessary. Example:

ChessBoaer size = 4x4
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
            "columnIndex": 3,
            "rowIndex": null
        },
        {
            "id": 1,
            "columnIndex": null,
            "rowIndex": 1
        },
        {
            "id": 2,
            "columnIndex": null,
            "rowIndex": null
        },
        {
            "id": 3,
            "columnIndex": 1,
            "rowIndex": 3
        },
        {
            "id": 4,
            "columnIndex": 0,
            "rowIndex": 1
        }
    ],
    "score": {
        "initScore": 0,
        "hardScore": -1,
        "softScore": 0,
        "feasible": false,
        "zero": false,
        "solutionInitialized": true
    }
}
```

But I am expecting this: 
```json
{
   "queenList":[
      {
         "id":0,
         "columnIndex":null,
         "rowIndex":null
      },
      {
         "id":1,
         "columnIndex":2,
         "rowIndex":0
      },
      {
         "id":2,
         "columnIndex":0,
         "rowIndex":1
      },
      {
         "id":3,
         "columnIndex":3,
         "rowIndex":2
      },
      {
         "id":4,
         "columnIndex":1,
         "rowIndex":3
      }
   ]
}
```

This is still work in progress, I hope that open source community will be able to help
