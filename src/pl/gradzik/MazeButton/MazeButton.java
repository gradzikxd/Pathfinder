package pl.gradzik.MazeButton;

import javax.swing.*;

public class MazeButton extends JButton
{
    public final static ImageIcon white = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\WhiteIcon.png");
    public final static ImageIcon black = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\BlackIcon.png");
    public final static ImageIcon blue = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\BlueIcon.png");
    public final static ImageIcon lightBlue = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\LightBlueIcon.png");
    public final static ImageIcon yellow = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\YellowIcon.png");
    public final static ImageIcon green = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\GreenIcon.png");
    public final static ImageIcon red = new ImageIcon(System.getProperty("user.dir") + "\\src\\pl\\gradzik\\MazeButton\\RedIcon.png");

    private int value;
    private int row;
    private int column;


    public MazeButton()
    {
        //this.setFocusable(false);

        this.setIcon(white);
        this.setBorderPainted(false);

    }

    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);

        if(defaultIcon == white) value = 0;
        if(defaultIcon == black) value = 1;
        if(defaultIcon == blue) value = 2;
        if(defaultIcon == lightBlue) value = 3;
        if(defaultIcon == yellow) value = 4;
        if(defaultIcon == green) value = 5;
        if(defaultIcon == red) value = 6;
    }

    public int getValue()
    {
        return value;
    }

    public void setXY(int r, int c)
    {
        row = r;
        column = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


}
