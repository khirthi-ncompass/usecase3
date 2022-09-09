package com.springboot.usecase3.users.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class UserIdGenerator {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789";

    public static String generateUserId(int length) {
        return generateRandomString(length);
    }

    private static String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for(int i =0; i<length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

}
