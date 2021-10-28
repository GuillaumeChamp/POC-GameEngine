package Universal.Stuff;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Equipment> equipments;
    ArrayList<Consumable> consumables;
    ArrayList<Loot> loots;

    public void add(Item item){
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
