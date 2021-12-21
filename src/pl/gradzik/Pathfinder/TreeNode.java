package pl.gradzik.Pathfinder;

import pl.gradzik.MazeButton.MazeButton;

public class TreeNode
{
    private TreeNode top;
    private TreeNode right;
    private TreeNode bottom;
    private TreeNode left;
    private TreeNode prev;

    private MazeButton button;

    public TreeNode(TreeNode prev,MazeButton b)
    {
        this.button = b;
        this.prev = prev;
    }

    public MazeButton getButton() {
        return button;
    }

    public void setButton(MazeButton button) {
        this.button = button;
    }

    public TreeNode getTop() {
        return top;
    }

    public void setTop(TreeNode top) {
        this.top = top;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getBottom() {
        return bottom;
    }

    public void setBottom(TreeNode bottom) {
        this.bottom = bottom;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getPrev() {
        return prev;
    }

    public void setPrev(TreeNode prev) {
        this.prev = prev;
    }
}
