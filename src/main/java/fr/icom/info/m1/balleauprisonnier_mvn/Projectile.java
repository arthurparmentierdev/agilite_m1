package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile {
    int vitesse;
    double direction;
    Image projectile;

    GraphicsContext graphicsContext;

    public Projectile(int vitesse, double direction) {
        this.vitesse = vitesse;
        this.direction = direction;

        projectile = new Image("assets/ball.png");
    }

    void display() {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        graphicsContext.restore(); // back to original state (before rotation)
    }
}
