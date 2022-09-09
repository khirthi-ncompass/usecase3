package com.springboot.usecase3.questions.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class QuestionUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789";

    public static String generateQuestionId(int length) {
        return generateRandomString(length);
    }

    public static String generateDate(){
        return date();
    }

    private static String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for(int i =0; i<length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    private static String date() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
        return formatter.format(date);
    }

}
