package com.ggonzalez.farmtest.entity;

public class FarmStatusReport {

    private int money;
    private long chickens;
    private long eggs;
    private int chickensToDieOnTheNextDay;
    private int eggsToBeBrokenOnTheNextDay;
    private int eggsToBeLaidOnTheNextDay;

    public FarmStatusReport() {}

    public FarmStatusReport(int money, long chickens, long eggs, int chickensToDieOnTheNextDay, int eggsToBeBrokenOnTheNextDay, int eggsToBeLaidOnTheNextDay) {
        this.money = money;
        this.chickens = chickens;
        this.eggs = eggs;
        this.chickensToDieOnTheNextDay = chickensToDieOnTheNextDay;
        this.eggsToBeBrokenOnTheNextDay = eggsToBeBrokenOnTheNextDay;
        this.eggsToBeLaidOnTheNextDay = eggsToBeLaidOnTheNextDay;
    }

    public int getMoney() {
        return money;
    }

    public long getChickens() {
        return chickens;
    }

    public long getEggs() {
        return eggs;
    }

    public int getChickensToDieOnTheNextDay() {
        return chickensToDieOnTheNextDay;
    }

    public int getEggsToBeBrokenOnTheNextDay() {
        return eggsToBeBrokenOnTheNextDay;
    }

    public int getEggsToBeLaidOnTheNextDay() {
        return eggsToBeLaidOnTheNextDay;
    }
}
