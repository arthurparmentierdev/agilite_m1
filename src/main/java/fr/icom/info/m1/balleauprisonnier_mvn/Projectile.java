package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile {
    Image balloon;
    GraphicsContext graphicsContext;

    private Projectile() {
    }

    /** Instance unique non préinitialisée */
    private static Projectile INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static Projectile getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Projectile();
        }
        return INSTANCE;
    }

    void display(GraphicsContext gc, double x, double y) {
        this.graphicsContext = gc;
        balloon = new Image("assets/ball.png");
        graphicsContext.save(); // saves the current state on stack, including the current transform
        graphicsContext.drawImage(balloon, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }
}
