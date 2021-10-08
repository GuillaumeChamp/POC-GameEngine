import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Figth_Scene extends Scene implements Game_Scene {
    private Image background = new Image(".//Resources/Level/town_land.png");

    Figth_Scene(Group root){
        super(root);
    }

    @Override
    public void Tick(double t) {

    }
}
