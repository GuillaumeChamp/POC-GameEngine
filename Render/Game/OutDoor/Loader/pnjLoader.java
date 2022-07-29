package Game.OutDoor.Loader;
import Game.OutDoor.LevelElements.PNJ;
import java.io.BufferedReader;
import java.io.FileReader;

public class pnjLoader extends Loader{
    @Override
    void load(String name) {
        try {
            FileReader reader = new FileReader("Data/pnj/" + name);
            //maybe switch with a file instead of a path
            BufferedReader bufferedReader = new BufferedReader(reader);

            String pnj_name = bufferedReader.readLine();
            String pnj_Skin = bufferedReader.readLine();
            String[] dialogues = bufferedReader.readLine().split(",");
            int[] startX = convert(bufferedReader.readLine().split(","));
            int[] startY = convert(bufferedReader.readLine().split(","));

            PNJ pnj = new PNJ(pnj_name,pnj_Skin,dialogues,startX,startY);
            save(pnj,name);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(name);
        }
    }
}
