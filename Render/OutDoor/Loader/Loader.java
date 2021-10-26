package OutDoor.Loader;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public abstract class Loader {
    /**
     * Create an object from the file name and save it
     * @param name name of the file to read
     */
    abstract void load(String name);

    /**
     * Save the specified object in a file. Called during load.
     * @param obj object to save
     * @param name name of the file (most of the time the name of the object)
     */
    protected void save(Object obj,String name){
        try {
            FileOutputStream save = new FileOutputStream(name);
            ObjectOutputStream saver =new ObjectOutputStream(save);
            saver.writeObject(obj);
            save.close();
        }catch (Exception ee){
            ee.printStackTrace();
        }
    }

    /**
     * Convert the specific String list from a reader to an int list
     * @param string String list to convert
     * @return the converted int list
     */
    protected int[] convert(String[] string){
        int[] ans = new int[string.length];
        for (int i=0;i<string.length;i++) {
            ans[i] = Integer.parseInt(string[i]);
        }
        return ans;
    }
}
