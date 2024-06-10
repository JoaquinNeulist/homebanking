package com.mindhubbrothers.homebanking.utils;

import java.util.Random;

public class CVVGenerator {

    public static int generate() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }
}

