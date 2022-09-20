package Game.Universal;

import Game.Fight.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class PlayerData {
    static String FILENAME = "Resources//Data//save.properties";
    static String HERO_REP = "Resources//Data//heroes";
    static Player player;
    private static ArrayList<Hero> heroes;

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

    public static ArrayList<Hero> getHeroes() {
        if (heroes == null) {
            try {
                loadHeroes();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return heroes;
    }

    private static void loadHeroes() throws IOException {
        File heroRep = new File(HERO_REP);
        heroes = new ArrayList<>();
        if (!heroRep.exists() || Objects.requireNonNull(heroRep.listFiles()).length<1) {
            createHeroes();
            return;
        }
        for (File f : Objects.requireNonNull(heroRep.listFiles())) {
            FileInputStream fileInputStream = new FileInputStream(HERO_REP+File.separator+ f.getName());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                Hero h = (Hero) objectInputStream.readObject();
                h.debuff();
                heroes.add(h);
            }catch (InvalidClassException | ClassNotFoundException e){
                System.out.println("class Hero have been changed, create a new one");
                addHero(f.getName().replace(".hero",""));
            }
        }
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createHeroes() {
        File heroRep = new File(HERO_REP);
        heroRep.mkdirs();
        addHero("1");

    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void addHero(String name) {
        Hero hero = new Hero(name);
        heroes.add(hero);
        File heroFile = new File(HERO_REP+File.separator+name+".hero");
        if (heroFile.exists()) heroFile.delete();
        try {
            heroFile.createNewFile();
            saveHeroes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveHeroes() throws IOException {
        if (heroes==null) return;
        for (Hero h:heroes) {
            if (h==null) continue;
            FileOutputStream outputStream = new FileOutputStream(HERO_REP+File.separator+ h.getName()+".hero");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(h);
            objectOutputStream.flush();
        }
    }
}
