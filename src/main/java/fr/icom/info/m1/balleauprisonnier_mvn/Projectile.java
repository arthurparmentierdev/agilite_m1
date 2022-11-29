package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Projectile {
    int vitesse;
    double direction;
    double x;
    double y;
    Image balloon;
    GraphicsContext graphicsContext;

    public Projectile(int vitesse, GraphicsContext gc, double direction, double xInit, double yInit) {
        x = xInit;
        y = yInit;
        this.vitesse = vitesse;
        this.direction = direction;
        graphicsContext = gc;
        balloon = new Image("assets/ball.png");
    }

    void display() {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        graphicsContext.drawImage(balloon, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }
}
