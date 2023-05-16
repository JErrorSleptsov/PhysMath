package windows;

import listeners.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс представляющий фрейм приветсвенного окна.
 * В нем содержится информация титульного листа курсовой работы
 *
 * @author Sleptsov D.A.
 */
public class SplashScreenWindow extends JFrame {


    public SplashScreenWindow() {

        JPanel pnlContent = new JPanel();
        JPanel pnlCW = new JPanel();
        pnlCW.setPreferredSize(new Dimension(420, 100));
        pnlContent.setLayout(new FlowLayout());

        JLabel lblSeconds = new JLabel("60");
        JLabel lblBNTU = new JLabel("Белорусский национальный технический университет");
        JLabel lblFaculty = new JLabel("Факультет информационных технологий и робототехники");
        JLabel lblKaphedra = new JLabel("Кафедра программаного обеспечения информационных систем и технологий");
        JLabel lblCW = new JLabel("Курсовая работа");
        JLabel lblSub = new JLabel("по дисциплине \"Программирование на языке Java\"");
        JLabel lblTheme = new JLabel("\"Расчёт физических характеристик тела при свободном падении\"");
        JLabel lblStudent = new JLabel("<html>Выполнил: студент группы 10702420" +
                "<br/>Слепцов Даниил Алексеевич<html>");
        JLabel lblTeacher = new JLabel("<html>Преподаватель: к.ф.-м.н., доц." +
                "<br/>Сидорик Валерий Владимирович<html>");
        JLabel lblCity = new JLabel("Минск 2023");

        JButton btnExit = new JButton("Выход (" + lblSeconds.getText() + ")");
        JButton btnNext = new JButton("Далее");
        Timer timer = new Timer(1000, new TimerListener(lblSeconds, btnExit));
        timer.start();

        lblBNTU.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
        lblBNTU.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFaculty.setFont(new Font("Montserrat Medium", Font.BOLD, 16));
        lblFaculty.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblKaphedra.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblKaphedra.setFont(new Font("Montserrat Medium", Font.PLAIN, 12));
        lblCW.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
        pnlCW.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStudent.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
        lblTeacher.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
        lblCity.setFont(new Font("Montserrat Medium", Font.BOLD, 14));

        btnNext.setPreferredSize(new Dimension(200, 50));
        btnNext.addActionListener(e -> {
            dispose();
            MainWindow form = new MainWindow();
            timer.stop(); // Остановим таймер
            form.setVisible(true);
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setPreferredSize(new Dimension(200, 50));

        pnlCW.add(Box.createVerticalStrut(20));
        pnlCW.add(lblCW, BorderLayout.NORTH);
        pnlCW.add(lblSub, BorderLayout.CENTER);
        pnlCW.add(lblTheme, BorderLayout.SOUTH);
        JPanel pnlImage = new JPanel();
        JPanel pnlNames = new JPanel();
        JPanel pnlButtons = new JPanel();
        JPanel pnlCity = new JPanel();
        pnlNames.setLayout(new BoxLayout(pnlNames, BoxLayout.Y_AXIS));
        pnlNames.add(lblStudent);
        pnlNames.add(Box.createVerticalStrut(15));
        pnlNames.add(lblTeacher);
        ImageIcon imgMainIcon = new ImageIcon("resources/images/ApplicationIcon.png");
        JLabel lblMainImage = new JLabel(imgMainIcon);
        pnlImage.setLayout(new FlowLayout());
        pnlImage.add(lblMainImage);
        pnlImage.add(Box.createHorizontalStrut(20));
        pnlImage.add(pnlNames);

        pnlCity.setPreferredSize(new Dimension(400, 70));
        pnlCity.add(lblCity, BorderLayout.CENTER);

        pnlButtons.add(btnExit);
        pnlButtons.add(btnNext);

        pnlContent.add(lblBNTU);
        pnlContent.add(lblFaculty);
        pnlContent.add(lblKaphedra);
        pnlContent.add(pnlCW);
        pnlContent.add(pnlImage);
        pnlContent.add(pnlCity);

        pnlContent.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlContent);

        // Установка параметров окна
        setIconImage(imgMainIcon.getImage());
        setTitle("Курсовая работа");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}