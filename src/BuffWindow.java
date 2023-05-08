import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BuffWindow extends JFrame implements KeyListener {
    private JPanel contentPane;

    public BuffWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
            // Изменение цвета фона
            contentPane.setBackground(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Не используется
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется
    }

    public static void main(String[] args) {
        new BuffWindow();
    }
}
