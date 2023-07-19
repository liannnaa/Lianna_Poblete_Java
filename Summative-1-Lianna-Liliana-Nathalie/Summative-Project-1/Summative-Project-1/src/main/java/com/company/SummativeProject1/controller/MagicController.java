package com.company.SummativeProject1.controller;

import com.company.SummativeProject1.model.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MagicController {
    private static List<String> answerList = new ArrayList<>(Arrays.asList(
            "It is certain.",
            "As I see it, yes.",
            "Reply hazy, try again.",
            "Don't count on it.",
            "My sources say no.",
            "Outlook not so good.",
            "Better not tell you now.",
            "Very doubtful.",
            "Yes - definitely.",
            "Signs point to yes."
    ));

    private static int idCounter = 0;
    private Random random = new Random();

    // URI: /magic
    // Method: POST
    // Request Body: Question
    // Response Body: Answer
    // The POST request for the Magic 8 Ball endpoint should include a user-provided question.
    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer magic(@RequestBody Optional<String> question) {
        String answer = answerList.get(random.nextInt(answerList.size()));
        if(question.isPresent()){
            return new Answer(idCounter++, question.get(), answer);
        }
        return new Answer(idCounter++, "No question", answer);
    }
}
