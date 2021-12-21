package pl.gradzik;

import pl.gradzik.MazeButton.MazeButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Maze implements ActionListener
{
    JPanel mazePanel;
    File mazeSave = new File(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\maze.txt");

    private MazeButton[][] maze;
    private MazeClickMode clickMode;
    private MazeButton currentStart;
    private MazeButton currentEnd;

    public Maze(JPanel mazePanel) throws IOException  //constructor
    {
        maze = new MazeButton[29][60];
        this.mazePanel = mazePanel;

        FileReader reader = new FileReader(mazeSave.getPath());
        int data = reader.read();

        clickMode = MazeClickMode.BLACKWHITE;


        for (int r = 0; r < 29; r++)
        {
            for (int c = 0; c < 60; c++)
            {

                maze[r][c] = new MazeButton();
                maze[r][c].setBounds(c * 30, r * 30, 30, 30);
                maze[r][c].addActionListener(this);
                maze[r][c].setXY(r,c);

                if((char)data == '\n')
                {
                    data = reader.read();
                }

                if((char)data == '1')
                {
                    maze[r][c].setIcon(MazeButton.black);
                }

                if((char)data == '4')
                {
                    maze[r][c].setIcon(MazeButton.yellow);
                    currentStart = maze[r][c];
                }

                if((char)data == '5')
                {
                    maze[r][c].setIcon(MazeButton.green);
                    currentEnd = maze[r][c];
                }

                data = reader.read();

                mazePanel.add(maze[r][c]);
            }
        }

        reader.close();
    }

    public MazeButton getCurrentStart() {
        return currentStart;
    }

    public MazeButton getCurrentEnd() {
        return currentEnd;
    }

    public MazeButton getMazeElement(int x, int y) {
        return maze[x][y];
    }

    public void setClickMode(MazeClickMode mode)
    {
        clickMode = mode;
    }


    @Override
    public void actionPerformed(ActionEvent e) //Buttons Event
    {
        for (int r = 0; r < 29; r++) {
            for (int c = 0; c < 60; c++)
            {
                if(e.getSource() == maze[r][c])
                {
                    if(clickMode == MazeClickMode.BLACKWHITE)
                    {
                        if(maze[r][c].getValue() == 0) maze[r][c].setIcon(MazeButton.black);
                        else if (maze[r][c].getValue() == 1) maze[r][c].setIcon(MazeButton.white);
                    }
                    else if(clickMode == MazeClickMode.START)
                    {
                        clearMaze();
                        maze[r][c].setIcon(MazeButton.yellow);
                        currentStart = maze[r][c];
                        if(currentEnd != null) currentEnd.setIcon(MazeButton.green);

                    }
                    else if(clickMode == MazeClickMode.END)
                    {
                        clearMaze();
                        maze[r][c].setIcon(MazeButton.green);
                        currentEnd = maze[r][c];
                        if(currentStart != null) currentStart.setIcon(MazeButton.yellow);
                    }
                    return;
                }
            }
        }
    }

    public void saveMaze() throws IOException //Closing method
    {
        FileWriter writer = new FileWriter(mazeSave.getPath());

        for (int r = 0; r < 29; r++)
        {
            for (int c = 0; c < 60; c++)
            {
                if(maze[r][c].getValue() == 1)
                {
                    writer.append("1");
                }
                else if(maze[r][c].getValue() == 4)
                {
                    writer.append("4");
                }
                else if(maze[r][c].getValue() == 5)
                {
                    writer.append("5");
                }
                else
                {
                    writer.append("0");
                }
            }
            writer.append("\n");
        }
        writer.close();
    }

    public void clearMaze()
    {
        for (int r = 0; r < 29; r++)
        {
            for (int c = 0; c < 60; c++)
            {
                if(maze[r][c].getValue() != 1)
                {
                    maze[r][c].setIcon(MazeButton.white);
                }
            }
        }
    }


}
