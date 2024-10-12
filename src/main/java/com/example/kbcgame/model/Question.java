
package com.example.kbcgame.model;

import lombok.Data;

@Data
public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;
}
