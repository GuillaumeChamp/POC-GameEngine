package com.application.Game.Universal;

import com.application.Graphic.Graphic_Const;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class must be call instead of create new image
 */
public class ImageHolder {
    static ArrayList<Image> images = new ArrayList<>();
    static ArrayList<String> tag = new ArrayList<>();

    /**
     * get an image using is memory or create it if not yet saved
     * @param name path of the image (like on javafx image)
     * @return the requested image
     */
    public static Image getImage(String name){
        if (tag.contains(name)){
            return images.get(tag.indexOf(name));
        }
        Image im = new Image(name, Graphic_Const.TILES_SIZE,Graphic_Const.TILES_SIZE,false,false);
        images.add(im);
        tag.add(name);
        return im;
    }

    public static Image[] getSprites(String name,int animationID,int xStep,int yStep){
        if (tag.contains(name)){
            ArrayList<Image> ans = new ArrayList<>();
            for (int i = tag.indexOf(name); i <= tag.lastIndexOf(name); i++) {
                ans.add(images.get(i));
            }
            return ans.toArray(new Image[0]);
        }
        Image[] im = ImageCropper.crop(name,animationID,xStep,yStep);
        images.addAll(Arrays.asList(im));
        for (int i = 0;i<im.length;i++) tag.add(name);
        return im;
    }

}
