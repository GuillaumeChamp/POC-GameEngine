package com.application.Graphic.Interface;

import javafx.geometry.Rectangle2D;

public interface MenuType extends Controllable{

    void performController();

    Rectangle2D getPosition(double effectivePolice);

    Object getContent();
}
