package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class TxtKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || c == '.')) {
            e.consume(); // отменяем ввод символа
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
