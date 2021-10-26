package Universal;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageBuilder {
    /**
     * Build the Images Array cropping on the specific path
     * @param filename prefix of the image to load
     * @return Image Array
     */
    public Image[] build(String filename){
        Image file = new Image(".//Resources//skin//"+filename);
        int column = (int) (file.getWidth()/40);
        int line = (int) (file.getHeight()/40);
        Image[] output = new Image[line*column];
        PixelReader reader = file.getPixelReader();
        for (int i=0;i<line;i++){
            for (int l=0;l<column;l++) {
                output[i*column + l] = new WritableImage(reader, 40 * l, 40 * i, 40, 40);
            }
        }
        return output;
    }
}
