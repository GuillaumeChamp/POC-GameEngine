package OutDoor;
import Universal.MovingAnimatedImage;
import javafx.scene.image.Image;

public class Player {
    public MovingAnimatedImage skin;
    public Level location;
    public int HP;
    //public State state; to add statue to the player

    public Player(){
        Image[] player_frame = new Image[1];
        player_frame[0] = new Image(".//Resources//skin//player.png");
        skin = new MovingAnimatedImage(player_frame, 1);
        location = new Level(false);
        HP = 20;
    }


}
