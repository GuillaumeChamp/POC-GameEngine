package com.application.Game.Universal.Stuff;

import java.util.ArrayList;

public class Inventory {
    static ArrayList<Equipment> equipments;
    static ArrayList<Consumable> consumables;
    static ArrayList<Loot> loots;

    public static void add(Item item){
        if (item==null) return;
        if (item.getClass()==Consumable.class) {
            for (Consumable c: consumables) {
                if (c.getName().equals(item.getName())) c.add(item.getNumber());
            }
        }
        if (item.getClass()==Loot.class) {
            for (Loot c: loots) {
                if (c.getName().equals(item.getName())) c.add(item.getNumber());
            }
        }
        if (item.getClass()==Equipment.class) {
            equipments.add((Equipment) item);
        }
    }
}
