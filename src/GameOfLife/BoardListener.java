package GameOfLife;

import javax.swing.*;
import java.awt.*;

public class BoardListener {
    private JToggleButton button;
    private int x, y;

    public BoardListener(JToggleButton button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public void boardChanged(Board board) {
        SwingUtilities.invokeLater(() -> {
            if(board.isCellAlive(x,y)) button.setSelected(true);
            else button.setSelected(false);
        });
    }
}
