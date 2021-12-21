package pl.gradzik;

import pl.gradzik.MazeButton.MazeButton;
import pl.gradzik.Pathfinder.PathfinderAlgorithm;
import pl.gradzik.Pathfinder.SearchType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame  implements ActionListener
{
    JPanel topPanel;
    JPanel mazePanel;
    Maze maze;
    PathfinderAlgorithm pathfinder;

    JButton startButton;
    JButton clearButton;
    JButton setStartModeButton;
    JButton setEndModeButton;
    JButton setBWButton;
    JComboBox comboBox;



    public MainFrame() throws HeadlessException, IOException {
        this.setTitle("Pathfinding Algorithm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1816,939);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        createPanels();
        createButtons();
        createComboBox();

        maze = new Maze(mazePanel);
        pathfinder = new PathfinderAlgorithm(maze);

        SwingUtilities.updateComponentTreeUI(this);        //refresh frame

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {                           //instrukcje wykonujące sie po zamknięciu programu
                try {
                    maze.saveMaze();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

    }


    private void createPanels()
    {
        topPanel = new JPanel();
        topPanel.setBounds(0,0,1920,30);
        topPanel.setBackground(Color.gray);
        topPanel.setLayout(null);
        this.add(topPanel);

        mazePanel = new JPanel();
        mazePanel.setBounds(0,30,1920,1050);
        mazePanel.setLayout(null);
        this.add(mazePanel);
    }

    private void createButtons()
    {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\WhiteBlackIcon.png");

        startButton = new JButton();
        topPanel.add(startButton);
        startButton.setBounds(214,4,100,23);
        startButton.setVerticalAlignment(JButton.CENTER);
        startButton.setBackground(Color.white);
        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        clearButton = new JButton();
        topPanel.add(clearButton);
        clearButton.setBounds(324,4,100,23);
        clearButton.setVerticalAlignment(JButton.CENTER);
        clearButton.setBackground(Color.white);
        clearButton.setText("Clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);

        setBWButton = new JButton();
        topPanel.add(setBWButton);
        setBWButton.setBounds(1768,4,23,23);
        setBWButton.setVerticalAlignment(JButton.CENTER);
        setBWButton.setBackground(Color.white);
        setBWButton.setFocusable(false);
        setBWButton.setIcon(icon);
        setBWButton.addActionListener(this);


        setEndModeButton = new JButton();
        topPanel.add(setEndModeButton);
        setEndModeButton.setBounds(1738,4,23,23);
        setEndModeButton.setVerticalAlignment(JButton.CENTER);
        setEndModeButton.setBackground(Color.green);;
        setEndModeButton.setFocusable(false);
        setEndModeButton.addActionListener(this);


        setStartModeButton = new JButton();
        topPanel.add(setStartModeButton);
        setStartModeButton.setBounds(1708,4,23,23);
        setStartModeButton.setVerticalAlignment(JButton.CENTER);
        setStartModeButton.setBackground(Color.yellow);
        setStartModeButton.setFocusable(false);
        setStartModeButton.addActionListener(this);
    }

    private void createComboBox()
    {
        String[] algorithms = {"Depth search algorithm", "breadth-first search algorithm"};           //depth search algorithm - w głąb
                                                                                                      //breadth-first search algorithm - wszerz
        comboBox = new JComboBox(algorithms);
        topPanel.add(comboBox);
        comboBox.setBounds(4,4,200,23);
        comboBox.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) //Buttons Event
    {
        if(e.getSource() == startButton)
        {
            new Thread(pathfinder).start();
        }
        if(e.getSource() == clearButton)
        {
            maze.clearMaze();
            maze.getCurrentStart().setIcon(MazeButton.yellow);
            maze.getCurrentEnd().setIcon(MazeButton.green);
        }
        if(e.getSource() == setBWButton)
        {
            maze.setClickMode(MazeClickMode.BLACKWHITE);
        }
        if(e.getSource() == setEndModeButton)
        {
            maze.setClickMode(MazeClickMode.END);
        }
        if(e.getSource() == setStartModeButton)
        {
            maze.setClickMode(MazeClickMode.START);
        }

        //comboBox
        if(e.getSource() == comboBox)
        {
            if(comboBox.getSelectedIndex() == 0)
            {
                pathfinder.setType(SearchType.DEPTH);
            }
            if(comboBox.getSelectedIndex() == 1)
            {
                pathfinder.setType(SearchType.BFS);
            }
        }

    }
}
