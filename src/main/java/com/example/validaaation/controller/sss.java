package com.example.validaaation.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class sss {

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2023, 3, 9); // mardi
        LocalDate endDate = LocalDate.of(2023, 3, 16); // mercredi

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        int numWeekendDays = 0;
        for (int i = 0; i <= daysBetween; i++) {
            LocalDate day = startDate.plusDays(i);
            if (day.getDayOfWeek().getValue() >= 6) {
                numWeekendDays++;
            }
        }
        long businessDaysBetween = daysBetween - numWeekendDays;

        System.out.println("Durée de temps ouvrable entre " + startDate + " et " + endDate + ": " + businessDaysBetween);
    }
}
