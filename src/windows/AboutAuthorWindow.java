package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс представляющий фрейм окна "Об авторе".
 * В нем содержится некоторая информация о студенте,
 * выполнившем работу
 *
 * @author Sleptsov D.A.
 */
public class AboutAuthorWindow extends JFrame {


    private JButton btnBack = new JButton("Вернуться");

    public AboutAuthorWindow() {
        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
        pnlContent.setBackground(Color.WHITE);

        JLabel lblGroup = new JLabel("Студент группы 10702420");
        JLabel lblName = new JLabel("Слепцов Даниил Алексеевич");
        JLabel lblEmail = new JLabel("danill.sleptsov.png@gmail.com");
        Font fontForInfo = new Font("Montserrat Medium", Font.PLAIN, 16);
        lblGroup.setFont(fontForInfo);
        lblName.setFont(fontForInfo);
        lblEmail.setFont(fontForInfo);
        btnBack.setFont(fontForInfo);
        ImageIcon imgAvatar = new ImageIcon("resources/images/author.png");
        JLabel lblForAvatar = new JLabel(imgAvatar);
        // установка выравнивания элементов по центру
        lblForAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGroup.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnBack.setPreferredSize(new Dimension(150, 35));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(126, 196, 255));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        // добавление элементов на панель содержимого окна
        pnlContent.add(Box.createVerticalGlue()); // пространство сверху
        pnlContent.add(lblForAvatar);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 40))); // отступ между изображением и текстом
        pnlContent.add(lblGroup);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 10))); //
        pnlContent.add(lblName);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 10))); //
        pnlContent.add(lblEmail);
        pnlContent.add(Box.createRigidArea(new Dimension(0, 10))); //
        pnlContent.add(Box.createVerticalGlue()); // пространство между текстом и кнопкой
        pnlContent.add(btnBack);
        pnlContent.add(Box.createVerticalGlue()); // пространство снизу
        add(pnlContent);

        setTitle("Об Авторе");
        // установка размеров и запрет изменения размеров окна
        setSize(350, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}