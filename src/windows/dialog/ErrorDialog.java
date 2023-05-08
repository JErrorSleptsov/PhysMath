package windows.dialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorDialog extends JDialog {
    private JPanel pnlContent = new JPanel();
    private ImageIcon imgError = new ImageIcon("resources/images/error.png");
    private JLabel lblErrImg = new JLabel(imgError);
    private JLabel lblMessage = new JLabel();
    private Font font = new Font("Montserrat Medium", Font.PLAIN, 12);
    private JButton btnOk = new JButton("Хорошо");
    public ErrorDialog(JFrame parent, String title, String message) {
        super(parent, title, true);
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
        pnlContent.setBackground(Color.WHITE);


        lblMessage.setText(message);
        lblMessage.setFont(font);
        btnOk.setFont(font);

        // установка выравнивания элементов по центру
        lblErrImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnOk.setPreferredSize(new Dimension(150, 35));
        btnOk.setForeground(Color.WHITE);
        btnOk.setBackground(new Color(255, 84, 84));
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        // добавление элементов на панель содержимого окна
        pnlContent.add(Box.createVerticalGlue()); // пространство сверху
        pnlContent.add(lblErrImg);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 20))); // отступ между изображением и текстом
        pnlContent.add(lblMessage);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 20))); //
        pnlContent.add(Box.createVerticalGlue()); // пространство между текстом и кнопкой
        pnlContent.add(btnOk);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 20))); //
        add(pnlContent);

        setTitle("Неверный формат параметров");
        // установка размеров и запрет изменения размеров окна
        setSize(500,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}