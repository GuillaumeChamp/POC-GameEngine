package Game.Universal;
import Game.Fight.Hero;
import Game.OutDoor.Level;
import Game.OutDoor.PlayerMovement;
import javafx.scene.image.Image;

public class Player {
    public PlayerMovement skin;
    public Level location;
    private Hero[] heroes;
    private TriggerList progression;

    public Player(){
        location = new Level(false);
        skin = new PlayerMovement("Skin/player.png", 1,location);
    }
}
