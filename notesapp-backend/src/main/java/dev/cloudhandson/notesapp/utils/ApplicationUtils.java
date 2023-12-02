package dev.cloudhandson.notesapp.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class ApplicationUtils {

    private final Random RANDOM = new SecureRandom();

    private final String KEY = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateNoteId(int length) {
        return generateRandomString(length);
    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomString.append(KEY.charAt(RANDOM.nextInt(KEY.length())));
        }
        return new String(randomString);
    }


}
