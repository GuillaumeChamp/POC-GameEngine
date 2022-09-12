package Graphic.Interface;

import java.util.ArrayList;

public class Options {
    public enum MenuType {main,test}
    public ArrayList<String> options;
    int selected;

    public Options(MenuType macro){
        switch (macro) {
            case main -> {
                options = new ArrayList<>();
                options.add("Heroes");
                options.add("Exit");
            }
            case test -> {
                options = new ArrayList<>();
                options.add("Test");
                options.add("Exit");
            }
        }
        selected=0;
    }
    public void changeSelected(String sens){
        if (sens.equals("UP")) selected++;
        if (sens.equals("DOWN")) selected--;
        if (selected>options.size()-1) selected=options.size()-1;
        if (selected<0) selected=0;
    }

    public int getSelected() {
        return selected;
    }
}
