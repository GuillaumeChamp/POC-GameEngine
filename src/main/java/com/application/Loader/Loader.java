package com.application.Loader;

import java.io.*;

public abstract class Loader<T> {
    /**
     * Create an object from the file name and save it
     * @param name name of the file to read
     */
    abstract T load(String name);

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
     * Use to reload a saved object
     * @param name name of the file
     * @return object if saved
     * @throws IOException if object never load before
     */
    protected T reload(String name) throws IOException {
            FileInputStream object = new FileInputStream(name);
            ObjectInputStream data = new ObjectInputStream(object);
        try {
            return (T) data.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
