import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileNameView extends JPanel {

    int nRows;
    int nCols;


    /**
     * Displays window with asking user to which file he wants to save a board or from which file load the board.
     * @param r Number of rows
     * @param c Number of columns
     * @param action Whether save or load a board
     * @param g Class which controls board
     * @param mv Main view
     */
    public FileNameView(int r, int c, String action, Generate g, MainView mv)
    {
        nRows = r+6;
        nCols = c+6;

        JFrame f = new JFrame();
        f.setSize(320, 110);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle(action);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        f.add(panel);

        JLabel nameLabel = new JLabel("Name of the file: ");
        nameLabel.setBounds(10, 10, 110, 25);
        panel.add(nameLabel);

        JTextField name = new JTextField(20);
        name.setBounds(130, 10, 160, 25);
        panel.add(name);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(10, 40, 100, 25);
        panel.add(cancel);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(170, 40, 120, 25);
        panel.add(confirm);

        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = name.getText() + ".txt";

                if(action.contains("Save")) // Saves current state of the board to file
                {
                    g.saveToFile(fileName);
                }else // Reads board from a file and initialise displaying it
                {
                    try{
                        File myObj = new File(fileName);
                        Scanner myReader = new Scanner(myObj);

                        boolean[][] fileBoard = new boolean[nRows][nCols];
                        int x = 0;
                        int y = 0;

                        while(myReader.hasNextLine() && x < nRows)
                        {
                            String data = myReader.nextLine();
                            fileBoard[x][y] = data.contains("1");

                            y++;

                            if(y%nCols==0)
                            {
                                x++;
                                y = 0;
                            }
                        }

                        g.setBoard(fileBoard);

                        myReader.close();
                    }catch(FileNotFoundException ex){
                        System.out.println("An error occured!");
                        ex.printStackTrace();
                    }
                }

                f.dispose();
                mv.setBoardGame(g.getBoard());
                mv.animate();

            }
        });


        f.getRootPane().setDefaultButton(confirm);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

}
