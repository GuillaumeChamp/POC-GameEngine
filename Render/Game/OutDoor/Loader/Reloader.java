package Game.OutDoor.Loader;

public class Reloader extends Loader{
    @Override
    void load(String name) {
        pnjLoader pload = new pnjLoader();
        //for file in pnj
        pload.load("");
        //for file in Level
        //for file in mob
    }

    public static void main(String[] args) {
        new Reloader().load("");
    }
}
