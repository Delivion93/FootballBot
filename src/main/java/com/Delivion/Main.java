package com.Delivion;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> countryFlags = new HashMap<>();

        countryFlags.put("Україна", "\uD83C\uDDFA\uD83C\uDDE6");
        countryFlags.put("США", "\uD83C\uDDFA\uD83C\uDDF8");
        countryFlags.put("Канада", "\uD83C\uDDE8\uD83C\uDDE6");
        countryFlags.put("Велика Британія", "\uD83C\uDDEC\uD83C\uDDE7");
        countryFlags.put("Німеччина", "\uD83C\uDDE9\uD83C\uDDEA");
        countryFlags.put("Франція", "\uD83C\uDDEB\uD83C\uDDF7");
        countryFlags.put("Італія", "\uD83C\uDDEE\uD83C\uDDF9");
        countryFlags.put("Іспанія", "\uD83C\uDDEA\uD83C\uDDF8");
        countryFlags.put("Японія", "\uD83C\uDDEF\uD83C\uDDF5");
        countryFlags.put("Китай", "\uD83C\uDDE8\uD83C\uDDF3");

        // Виведення всіх прапорів
        for (String country : countryFlags.keySet()) {
            System.out.println(country + ": " + countryFlags.get(country));
        }
    }
}