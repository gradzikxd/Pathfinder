package pl.gradzik.Pathfinder;

import pl.gradzik.MainFrame;
import pl.gradzik.Maze;
import pl.gradzik.MazeButton.MazeButton;;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PathfinderAlgorithm implements Runnable
{
    Maze maze;
    SearchType type;
    int delay = 10;

    public PathfinderAlgorithm(Maze maze)
    {
        this.maze = maze;
        this.type = SearchType.DEPTH;

    }

    public void findWay() throws InterruptedException {
        if(type == SearchType.DEPTH)
        {
            depthSearch();
        }
        if(type == SearchType.BFS)
        {
            breadthFirstSearch();
        }
    }

    private void depthSearch() throws InterruptedException {

        MazeButton currentButton;
        TreeNode currentNode;

        MazeButton currentButtonTop = null;
        MazeButton currentButtonRight = null;
        MazeButton currentButtonBottom= null;
        MazeButton currentButtonLeft = null;

        Tree tree = new Tree(new TreeNode(null,maze.getCurrentStart()));
        Stack<MazeButton> buttonStack = new Stack<>();
        Stack<TreeNode> nodeStack = new Stack<>();

        buttonStack.add(tree.getStartPoint().getButton());
        nodeStack.add(tree.getStartPoint());

        while (buttonStack.peek() != null)
        {
            currentButton = buttonStack.pop();
            currentNode = nodeStack.pop();

            if(currentButton != tree.getStartPoint().getButton())currentButton.setIcon(MazeButton.blue);

            if(currentButton.getRow()!= 0) currentButtonTop = maze.getMazeElement(currentButton.getRow() - 1,currentButton.getColumn());
            else currentButtonTop = currentButton;

            if(currentButton.getRow()!= 28) currentButtonBottom = maze.getMazeElement(currentButton.getRow() + 1,currentButton.getColumn());
            else currentButtonBottom = currentButton;

            if(currentButton.getColumn() != 0) currentButtonLeft = maze.getMazeElement(currentButton.getRow() ,currentButton.getColumn() - 1);
            else currentButtonLeft = currentButton;

            if(currentButton.getColumn() != 59) currentButtonRight = maze.getMazeElement(currentButton.getRow() ,currentButton.getColumn() + 1);
            else currentButtonRight = currentButton;


            if(currentButtonTop.getValue() == 5 || currentButtonBottom.getValue() == 5 || currentButtonLeft.getValue() == 5 || currentButtonRight.getValue() == 5)
            {
                findWayBack(currentNode);
                break;
            }

            if(currentButtonTop.getValue() == 0 )
            {
                currentButtonTop.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setTop(new TreeNode(currentNode,currentButtonTop));

                buttonStack.add(currentButtonTop);
                nodeStack.add(currentNode.getTop());
                Thread.sleep(delay);
            }

            if(currentButtonRight.getValue() == 0)
            {
                currentButtonRight.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setRight(new TreeNode(currentNode,currentButtonRight));

                buttonStack.add(currentButtonRight);
                nodeStack.add(currentNode.getRight());
                Thread.sleep(delay);
            }

            if(currentButtonBottom.getValue() == 0)
            {
                currentButtonBottom.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setBottom(new TreeNode(currentNode,currentButtonBottom));

                buttonStack.add(currentButtonBottom);
                nodeStack.add(currentNode.getBottom());
                Thread.sleep(delay);
            }

            if(currentButtonLeft.getValue() == 0)
            {
                currentButtonLeft.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setLeft(new TreeNode(currentNode,currentButtonLeft));

                buttonStack.add(currentButtonLeft);
                nodeStack.add(currentNode.getLeft());


                Thread.sleep(delay);
            }

        }
    }

    private void breadthFirstSearch() throws InterruptedException
    {
        MazeButton currentButton;
        TreeNode currentNode;

        MazeButton currentButtonTop = null;
        MazeButton currentButtonRight = null;
        MazeButton currentButtonBottom= null;
        MazeButton currentButtonLeft = null;

        Tree tree = new Tree(new TreeNode(null,maze.getCurrentStart()));
        Queue<MazeButton> buttonQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        buttonQueue.add(tree.getStartPoint().getButton());
        nodeQueue.add(tree.getStartPoint());
        while (buttonQueue.peek() != null)
        {
            currentButton = buttonQueue.poll();
            currentNode = nodeQueue.poll();

            if(currentButton != tree.getStartPoint().getButton())currentButton.setIcon(MazeButton.blue);

            if(currentButton.getRow()!= 0) currentButtonTop = maze.getMazeElement(currentButton.getRow() - 1,currentButton.getColumn());
            else currentButtonTop = currentButton;

            if(currentButton.getRow()!= 28) currentButtonBottom = maze.getMazeElement(currentButton.getRow() + 1,currentButton.getColumn());
            else currentButtonBottom = currentButton;

            if(currentButton.getColumn() != 0) currentButtonLeft = maze.getMazeElement(currentButton.getRow() ,currentButton.getColumn() - 1);
            else currentButtonLeft = currentButton;

            if(currentButton.getColumn() != 59) currentButtonRight = maze.getMazeElement(currentButton.getRow() ,currentButton.getColumn() + 1);
            else currentButtonRight = currentButton;


            if(currentButtonTop.getValue() == 5 || currentButtonBottom.getValue() == 5 || currentButtonLeft.getValue() == 5 || currentButtonRight.getValue() == 5)
            {
                findWayBack(currentNode);
                break;
            }

            if(currentButtonTop.getValue() == 0 )
            {
                currentButtonTop.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setTop(new TreeNode(currentNode,currentButtonTop));

                buttonQueue.add(currentButtonTop);
                nodeQueue.add(currentNode.getTop());
                Thread.sleep(delay);
            }

            if(currentButtonRight.getValue() == 0)
            {
                currentButtonRight.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setRight(new TreeNode(currentNode,currentButtonRight));

                buttonQueue.add(currentButtonRight);
                nodeQueue.add(currentNode.getRight());
                Thread.sleep(delay);
            }

            if(currentButtonBottom.getValue() == 0)
            {
                currentButtonBottom.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setBottom(new TreeNode(currentNode,currentButtonBottom));

                buttonQueue.add(currentButtonBottom);
                nodeQueue.add(currentNode.getBottom());
                Thread.sleep(delay);
            }

            if(currentButtonLeft.getValue() == 0)
            {
                currentButtonLeft.setIcon(MazeButton.lightBlue);
                assert currentNode != null;
                currentNode.setLeft(new TreeNode(currentNode,currentButtonLeft));

                buttonQueue.add(currentButtonLeft);
                nodeQueue.add(currentNode.getLeft());
                Thread.sleep(delay);
            }


        }
    }

  private TreeNode findWayBack(TreeNode node) throws InterruptedException
  {
      Thread.sleep(delay);

      if (node.getButton().getValue() == 4) return null;

      node.getButton().setIcon(MazeButton.red);
      return findWayBack(node.getPrev());
  }

    public void setType(SearchType type) {
        this.type = type;
    }


    @Override
    public void run()
    {
        try {
            findWay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


