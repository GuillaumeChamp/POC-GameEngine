package OutDoor;
import Fight.Hero;
import Universal.TriggerList;
import javafx.scene.image.Image;

public class Player {
    public PlayerMovement skin;
    public Level location;
    private Hero[] heroes;
    private TriggerList progression;

    public Player(){
        Image[] player_frame = new Image[1];
        player_frame[0] = new Image(".//Resources//skin//player.png");
        skin = new PlayerMovement(player_frame, 1);
        location = new Level(false);
    }
}
