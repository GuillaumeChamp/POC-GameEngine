package Universal.Stuff;

public class Equipment extends Item{
    enum Position {hand1,hand2,head,chestplate,arms,legs,foot,accessory}
    Position position;

    public Equipment(Position position){
        this.position = position;
    }
}
