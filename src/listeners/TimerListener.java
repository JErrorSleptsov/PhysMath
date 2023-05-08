package listeners;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TimerListener implements ActionListener {
    private JLabel lblSeconds;
    private JButton btnExit;
    public TimerListener(JLabel lblSeconds,JButton btnExit){
        this.lblSeconds = lblSeconds;
        this.btnExit = btnExit;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int remainingTime = Integer.parseInt(lblSeconds.getText()) - 1;
        lblSeconds.setText(Integer.toString(remainingTime));
        btnExit.setText("Выход (" + lblSeconds.getText() +")");
        if (remainingTime == 0) {
            System.exit(0);
        }
    }
}
