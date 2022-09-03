package Graphic.Interface;

import java.util.ArrayList;

public class Options {
    ArrayList<String> options;
    int selected;

    public Options(){
        options=new ArrayList<>();
        options.add("Test");
        options.add("Exit");
        selected=0;

    }
    public void changeSelected(String sens){
        if (sens.equals("UP")) selected++;
        if (sens.equals("DOWN")) selected--;
        if (selected>options.size()-1) selected=options.size()-1;
        if (selected<0) selected=0;
    }
}
