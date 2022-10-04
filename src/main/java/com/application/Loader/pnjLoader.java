package com.application.Loader;

import com.application.Game.Level.LevelElements.PNJ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class pnjLoader extends Loader<PNJ>{
    static final String path = "Data"+File.separator+"pnj"+ File.separator;
    @Override
    public PNJ load(String name) {
        try{
            return reload(name);
        } catch (IOException e) {
            try {
                FileReader reader = new FileReader(path+ name);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String pnj_name = bufferedReader.readLine();
                String pnj_Skin = bufferedReader.readLine();
                String[] dialogues = bufferedReader.readLine().split(",");
                int[] startX = convert(bufferedReader.readLine().split(","));
                int[] startY = convert(bufferedReader.readLine().split(","));

                PNJ pnj = new PNJ(pnj_name, pnj_Skin, dialogues, startX, startY);
                save(pnj, name);
                return pnj;
            } catch (Exception ee) {
                ee.printStackTrace();
                System.out.println(name);
                return null;
            }
        }
    }
}
