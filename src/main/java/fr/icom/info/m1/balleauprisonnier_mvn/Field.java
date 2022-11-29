package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 */
public class Field extends Canvas {

    /**
     * Joueurs
     */
    Player [] equipe1 = new Player[3];
    Player [] equipe2 = new Player[3];

    /**
     * Couleurs possibles
     */
    String[] colorMap = new String[]{"blue", "green", "orange", "purple", "yellow"};
    /**
     * Tableau traçant les evenements
     */
    ArrayList<String> input = new ArrayList<String>();


    final GraphicsContext gc;
    final int width;
    final int height;

    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w     largeur du canvas
     * @param h     hauteur du canvas
     */
    public Field(Scene scene, int w, int h) {
        super(w, h);
        width = w;
        height = h;

        /** permet de capturer le focus et donc les evenements clavier et souris */
        this.setFocusTraversable(true);

        gc = this.getGraphicsContext2D();

        /** On initialise le terrain de jeu */
        equipe1[0] = new PlayerAI(gc, colorMap[0], (w/2 + w/4), 20, "top");
        equipe1[0].display();

        equipe1[1] = new Player(gc, colorMap[1], w/2, 20, "top");
        equipe1[1].display();

        Projectile balloon1 = new Projectile(5, gc,30, w/2, 150);

        equipe1[2] = new PlayerAI(gc, colorMap[2], w/4, 20, "top");
        equipe1[2].display();

        equipe2[0] = new PlayerAI(gc, colorMap[0], (w/2 + w/4), h-50, "bottom");
        equipe2[0].display();

        equipe2[1] = new Player(gc, colorMap[1], w/2, h-50, "bottom");
        equipe2[1].display();

        Projectile balloon2 = new Projectile(5, gc, 30, w/2, h-100);

        equipe2[2] = new PlayerAI(gc, colorMap[2], w/4, h-50, "bottom");
        equipe2[2].display();



        /**
         * Event Listener du clavier
         * quand une touche est pressee on la rajoute a la liste d'input
         *
         */
        this.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        // only add once... prevent duplicates
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        /**
         * Event Listener du clavier
         * quand une touche est relachee on l'enleve de la liste d'input
         *
         */
        this.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

        /**
         *
         * Boucle principale du jeu
         *
         * handle() est appelee a chaque rafraichissement de frame
         * soit environ 60 fois par seconde.
         *
         */
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // On nettoie le canvas a chaque frame
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(0, 0, width, height);

                // Deplacement et affichage des joueurs
                for (int i = 0; i < equipe1.length; i++)
                {
                    if (equipe1[i] instanceof PlayerAI) {
                    } else {
                        if (i == 0 && input.contains("LEFT")) {
                            equipe1[i].moveLeft();
                        }
                        if (i == 0 && input.contains("RIGHT")) {
                            equipe1[i].moveRight();
                        }
                        if (i == 0 && input.contains("UP")) {
                            equipe1[i].turnLeft();
                        }
                        if (i == 0 && input.contains("DOWN")) {
                            equipe1[i].turnRight();
                        }
                        if (i == 1 && input.contains("Q")) {
                            equipe1[i].moveLeft();
                        }
                        if (i == 1 && input.contains("D")) {
                            equipe1[i].moveRight();
                        }
                        if (i == 1 && input.contains("Z")) {
                            equipe1[i].turnLeft();
                        }
                        if (i == 1 && input.contains("S")) {
                            equipe1[i].turnRight();
                        }
                        if (i == 0 && (input.contains("NUMPAD0") || input.contains("DIGIT0"))) {
                            equipe1[i].shoot();
                        }
                        if (i == 1 && input.contains("SPACE")) {
                            equipe1[i].shoot();
                            balloon1.display();
                        }
                    }

                    equipe1[i].display();
                }
                // Deplacement et affichage des joueurs de l'équipe 2
                for (int i = 0; i < equipe2.length; i++)
                {
                    if (equipe2[i] instanceof PlayerAI) {
                    } else {
                        if (i == 1 && input.contains("LEFT")) {
                            equipe2[i].moveLeft();
                        }
                        if (i == 1 && input.contains("RIGHT")) {
                            equipe2[i].moveRight();
                        }
                        if (i == 1 && input.contains("UP")) {
                            equipe2[i].turnLeft();
                        }
                        if (i == 1 && input.contains("DOWN")) {
                            equipe2[i].turnRight();
                        }
                        if (i == 1 && (input.contains("NUMPAD0") || input.contains("DIGIT0"))) {
                            equipe2[i].shoot();
                            balloon2.display();
                        }
                    }

                    equipe2[i].display();
                }
            }
        }.start(); // On lance la boucle de rafraichissement

    }

    public Player[] getJoueurs() {
        return equipe1;
    }

    public Player[] getEquipe2() {
        return equipe2;
    }
}
