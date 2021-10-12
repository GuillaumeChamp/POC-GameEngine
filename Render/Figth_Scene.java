import Figth.Enemy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Figth_Scene extends Scene implements Game_Scene {
    //TODO : might hold enemy but not the hero except on static field
    private final Image background = new Image(".//Resources/Level/town_land.png");
    private Enemy[] enemies;

    /**
     * Create a new scene to be ready for a fight
     * @param root root of the app
     */
    Figth_Scene(Group root){
        super(root);
    }

    @Override
    public void Tick(double t) {
        //TODO : make the attack selection and fight rules (speed)
    }
}
