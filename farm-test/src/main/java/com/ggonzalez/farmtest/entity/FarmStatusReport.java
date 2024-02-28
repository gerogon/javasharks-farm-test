package com.ggonzalez.farmtest.entity;

public record FarmStatusReport(int money, long chickens, long eggs,
                               int chickensToDieOnTheNextDay, int eggsToBeBrokenOnTheNextDay,
                               int eggsToBeLaidOnTheNextDay) {}