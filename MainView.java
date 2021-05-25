import javax.swing.*;
import java.awt.*;

/**
 * Main view displays board and buttons
 */
public class MainView extends JPanel {

    int nCols;
    int nRows;
    int cellSize;
    int margin;

    JButton buttonReadFile;
    JButton buttonSave;
    JButton buttonNextStep;
    JButton buttonAnimate;
    JButton buttonClear;
    JTextField speed;

    boolean[][] boardGame;


    public MainView(int r, int c, int size, int margin)
    {

        nRows = r;
        nCols = c;
        cellSize = size;
        this.margin = margin;


        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Game of Life!");
        f.add(this, BorderLayout.CENTER);
        f.pack();
        f.setSize(cellSize*(nCols-6)+margin+50,cellSize*(nRows-6)+margin+100);
        f.setLocationRelativeTo(null);


        JToolBar toolBar = new JToolBar();
        JPanel panel = new JPanel();
        buttonReadFile = new JButton("Load from a file");
        buttonSave = new JButton("Save to a file");
        buttonNextStep = new JButton("Next step");
        buttonAnimate = new JButton("Animate");
        buttonClear = new JButton("Clear the board");
        speed = new JTextField("500",5);

        panel.add(buttonReadFile);
        panel.add(buttonSave);
        panel.add(buttonNextStep);
        panel.add(buttonAnimate);
        panel.add(speed);
        panel.add(buttonClear);
        toolBar.add(panel);
        f.add(toolBar,BorderLayout.NORTH);


        f.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.gray);
        g.setStroke(new BasicStroke(3));

        // draw horizontal lines
        for (int r = 0; r < nRows-5; r++) {

            int y = margin + r * cellSize;

            g.drawLine(margin, y, cellSize*(nCols-6)+margin, y);

        }

        // draw vertical lines
        for (int c = 0; c < nCols-5; c++) {

            int x = margin + c * cellSize;

            g.drawLine(x, margin, x, cellSize*(nRows-6)+margin);

        }

        // draw cells
        g.setColor(Color.red);
        for(int r = 3; r < nRows-3; r++)
        {
            for(int c = 3; c < nCols-3; c++)
            {
                if(boardGame[r][c])
                {
                    int y = margin + (r-3) * cellSize + 2;
                    int x = margin + (c-3) * cellSize + 2;

                    g.fillRect(x,y,cellSize-3,cellSize-3);
                }
            }
        }

    }

    void animate()
    {
        repaint();
    }

    JButton getButtonReadFile()
    {
        return this.buttonReadFile;
    }

    JButton getButtonSave()
    {
        return this.buttonSave;
    }

    JButton getButtonNextStep()
    {
        return this.buttonNextStep;
    }

    JTextField getSpeed()
    {
        return this.speed;
    }

    JButton getButtonAnimate()
    {
        return this.buttonAnimate;
    }

    JButton getButtonClear()
    {
        return this.buttonClear;
    }

    void setBoardGame(boolean[][] b)
    {
        this.boardGame = b;
    }

}
