
package com.example.kbcgame.controller;

import com.example.kbcgame.model.Question;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;

@Controller
public class GameController {

    private final Question[] questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"Paris", "Berlin", "London", "Rome"}, "Paris"),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Venus", "Jupiter"}, "Mars"),
            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, "Pacific"),
            new Question("Who wrote 'Hamlet'?", new String[]{"Shakespeare", "Dickens", "Hemingway", "Tolstoy"}, "Shakespeare"),
            new Question("Which element has the chemical symbol 'O'?", new String[]{"Oxygen", "Osmium", "Oxide", "Olive"}, "Oxygen")
    };

    private int currentQuestionIndex = 0;

    @GetMapping("/game")
    public String getGamePage(Model model) {
        model.addAttribute("question", questions[currentQuestionIndex].getQuestion());
        model.addAttribute("options", questions[currentQuestionIndex].getOptions());

        String qrUrl = "http://localhost:8080/player";
        byte[] qrImage = QRCode.from(qrUrl).stream().toByteArray();
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrImage);
        model.addAttribute("qrCode", "data:image/png;base64," + qrCodeBase64);

        return "game";
    }

    @GetMapping("/nextQuestion")
    public ResponseEntity<String> nextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
        return ResponseEntity.ok("Next question ready");
    }
}
