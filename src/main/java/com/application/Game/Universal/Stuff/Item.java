package com.application.Game.Universal.Stuff;

public abstract class Item {
    protected String name;
    protected String description;
    protected int number = 1;

    public void add(int number){
        this.number=+number;
    }
    public void remove(int number) throws Exception{
        if (number> this.number) throw new Exception("notEnoughItem");
        this.number = this.number-number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
