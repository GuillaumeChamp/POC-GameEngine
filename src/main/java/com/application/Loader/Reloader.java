package com.application.Loader;

public class Reloader extends Loader{
    @Override
    public Object load(String name) {
        pnjLoader pload = new pnjLoader();
        //for file in pnj
        pload.load("");
        //for file in Level
        //for file in mob
        return null;
    }

    public static void main(String[] args) {
        new Reloader().load("");
    }
}
