package com.application.game.universal;


import com.application.game.fight.Hero;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class PlayerData {
    private final static String sep= File.separator;
    final static String rep = "src"+sep+"main"+sep+"resources"+sep+ "data" +sep+"Save"+sep;
    static String FILENAME = rep+ "save.properties";
    static String HERO_REP = rep+"heroes"+sep;
    static Player player;
    private static ArrayList<Hero> heroes;

    /**
     * Load the player data (level,position) from the save file
     * Create it if not exist
     */
    private static void load(){
        Properties props = new Properties();
        if(! new File(FILENAME).exists()) new File(FILENAME);
        try {
            props.load(Files.newInputStream(Paths.get(FILENAME)));
            try {
                player=new Player(props.getProperty("x"),props.getProperty("y"),props.getProperty("level"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            createData();
        }

    }

    /**
     * Save the player data in a file creating him if not found
     * @throws Exception if an error occur while trying backup strategy
     */
    public static void save() throws Exception{
        Properties props = new Properties();
        props.setProperty("x", String.valueOf(player.skin.getPositionX()));
        props.setProperty("y", String.valueOf(player.skin.getPositionY()));
        props.setProperty("level", String.valueOf(player.location.getName()));
        try {
            props.store(Files.newOutputStream(Paths.get(FILENAME)),"saving data");
        } catch (IOException e) {
            File file = new File(FILENAME);
            System.out.println(file.createNewFile());
            props.store(Files.newOutputStream(file.toPath()),"saving data");
        }
    }

    /**
     * Create a new player (first launch or failsafe strategy)
     */
    public static void createData(){
        player = new Player();
    }

    /**
     * Return the player its hold should be call everytime we need it
     * @return the player
     */
    public static Player getPlayer(){
        if (player==null) load();
        return player;
    }

    /**
     * Endpoint of all Heroes (in fight player) access method
     * @return the list of all heroes
     */
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

    /**
     * Load the Heroes from a file must be only use by getHeroes or in case of gameOver
     * @throws IOException if the file do not exist and if it cannot be created
     */
    private static void loadHeroes() throws IOException {
        File heroRep = new File(HERO_REP);
        heroes = new ArrayList<>();
        if (!heroRep.exists() || Objects.requireNonNull(heroRep.listFiles()).length<1) {
            createHeroes();
            return;
        }
        for (File f : Objects.requireNonNull(heroRep.listFiles())) {
            FileInputStream fileInputStream = new FileInputStream(HERO_REP+sep+ f.getName());
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

    /**
     * Create heroes list and create the repository to save them later
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createHeroes() {
        File heroRep = new File(HERO_REP);
        heroRep.mkdirs();
        addHero("1");

    }

    /**
     * Add a Hero to the heroes list and create a file to save it later (also save him)
     * @param name system's name of the Hero (see Hero constructor)
     * Throw a runtime exception if it cannot create file
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void addHero(String name) {
        Hero hero = new Hero(name);
        heroes.add(hero);
        File heroFile = new File(HERO_REP+sep+name+".hero");
        if (heroFile.exists()) heroFile.delete();
        try {
            heroFile.createNewFile();
            saveHeroes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save all heroes
     * @throws IOException in case of file error preventing to write data on hero data file
     */
    public static void saveHeroes() throws IOException {
        if (heroes==null) return;
        for (Hero h:heroes) {
            if (h==null) continue;
            FileOutputStream outputStream = new FileOutputStream(HERO_REP+sep+ h.getName()+".hero");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(h);
            objectOutputStream.flush();
        }
    }
}
