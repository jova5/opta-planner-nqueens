package com.optaplannernqueens.controller;

import com.optaplannernqueens.service.ChessQueenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chess-queen-controller")
public class ChessQueenController {
    private final ChessQueenService service;

    public ChessQueenController(ChessQueenService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> solve() {
        return ResponseEntity.ok(service.solve());
    }
}
