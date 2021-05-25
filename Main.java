import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The program is implementation of Conway's Game of Life
 *
 * @author Rafal Karwowski
 */

public class Main {

    static final int nRows = 40; // Number of rows
    static final int nCols = 80; // Number of columns
    static final int cellSize = 20;
    static final int margin = 20;

    static Timer timer; // Timer for animation
    static boolean timerStatus; // is timer on?


    /**
     * Initialise generating game view and displaying it.
     * Also listens when a button is clicked or cell activated/deactivated.
     */
    public static void main(String[] args) {

        Generate g = new Generate(nRows,nCols);
        MainView mv = new MainView(nRows, nCols, cellSize, margin);
        mv.setBoardGame(g.getBoard());

        JButton buttonReadFile = mv.getButtonReadFile();
        JButton buttonSave = mv.getButtonSave();
        JButton buttonNextStep = mv.getButtonNextStep();
        JButton buttonAnimate = mv.getButtonAnimate();
        JButton buttonClear = mv.getButtonClear();
        JTextField textSpeed = mv.getSpeed();

        buttonReadFile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileNameView(nRows, nCols, "Load from file", g, mv);
            }
        });

        buttonSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileNameView(nRows, nCols, "Save to file", g, mv);
            }
        });

        buttonNextStep.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.step();
                mv.setBoardGame(g.getBoard());
                mv.animate();
            }
        });


        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == timer)
                {
                    g.step();
                    mv.setBoardGame(g.getBoard());
                    mv.animate();
                }

            }
        };

        timerStatus = false;

        buttonAnimate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timerStatus = !timerStatus;
                int speed = Integer.parseInt(textSpeed.getText());
                timer = new Timer(speed, al);

                animateOnTime();

            }
        });

        buttonClear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.clearBoard();
                mv.setBoardGame(g.getBoard());
                mv.animate();
            }
        });

        mv.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new Thread(() -> {

                    g.setCell(((e.getY()+(cellSize*3))/cellSize-1), ((e.getX()+(cellSize*3))/cellSize-1));
                    mv.setBoardGame(g.getBoard());
                    mv.animate();

                }).start();
            }
        });

    }


    /**
     * Change the status of animation to the opposite one
     */
    static public void animateOnTime()
    {
        if (timerStatus) {
            timer.start();
        } else {
            timer.stop();
        }
    }

}
