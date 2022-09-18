package Game.Universal;

import Game.Universal.Stuff.Inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PlayerData {
    static String FILENAME = "Resources//Data//save.properties";
    static Player player;

    public PlayerData(){

    }
    private static void load(){
        Properties props = new Properties();
        if(! new File(FILENAME).exists()) new File(FILENAME);
        try {
            props.load(new FileInputStream(FILENAME));
            player=new Player(props.getProperty("x"),props.getProperty("y"),props.getProperty("level"));
        } catch (IOException e) {
            createData();
        }

    }
    public static void save() throws Exception{
        Properties props = new Properties();
        props.setProperty("x", String.valueOf(player.skin.getPositionX()));
        props.setProperty("y", String.valueOf(player.skin.getPositionY()));
        props.setProperty("level", String.valueOf(player.location.getName()));
        try {
            props.store(new FileOutputStream(FILENAME),"saving data");
        } catch (IOException e) {
            File file = new File(FILENAME);
            System.out.println(file.createNewFile());
            props.store(new FileOutputStream(file),"saving data");
        }
    }
    public static void createData(){
        player = new Player();
    }
    public static Player getPlayer(){
        if (player==null) load();
        return player;
    }
}
