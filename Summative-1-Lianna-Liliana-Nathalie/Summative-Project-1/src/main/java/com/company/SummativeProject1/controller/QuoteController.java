package com.company.SummativeProject1.controller;

import com.company.SummativeProject1.model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.*;

@RestController
public class QuoteController {
    private List<Quote> quoteList;
    private static int idCounter = 1;

    public QuoteController(){
        quoteList = new ArrayList<>();
        quoteList.add(new Quote("Mother Teresa", "Spread love everywhere you go. Let no one ever come to you without leaving happier.", idCounter++));
        quoteList.add((new Quote("Margaret Mead", "Always remember that you are absolutely unique. Just like everyone else.", idCounter++)));
        quoteList.add(new Quote("Nelson Mandela", "The greatest glory in living lies not in never falling, but in rising every time we fall", idCounter++));
        quoteList.add(new Quote("Mae West", "You only live once, but if you do it right, once is enough.", idCounter++));
        quoteList.add(new Quote("Dr.Seuss", "You have brains in your head. You have feet in your shoes. You can steer yourself any direction you choose.", idCounter++));
        quoteList.add(new Quote("Mark Twain", "The two most important days in your life are the day you are born and the day you find out why", idCounter++));
        quoteList.add(new Quote("Helen Keller", "When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us. ", idCounter++));
        quoteList.add(new Quote("Lao Tzu", "When I let go of what I am, I become what I might be.", idCounter++));
        quoteList.add(new Quote("Confucius", " It does not matter how slowly you go as long as you do not stop.", idCounter++));
        quoteList.add(new Quote("Dr.Seuss", "To the world you may be one person but to one person you may be the world.", idCounter++));
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus( value = OK)
    public List<Quote> getRandomQuote(){
        Random rand = new Random();
        List<Quote> tempQuoteList = new ArrayList<>();
        int randomIndex = rand.nextInt(quoteList.size());
        tempQuoteList.add(quoteList.get(randomIndex));

        return tempQuoteList;
    }
    }
