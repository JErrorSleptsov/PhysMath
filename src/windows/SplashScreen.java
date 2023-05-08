package windows;
import listeners.TimerListener;
import windows.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame{
    private JPanel pnlContent = new JPanel();
    private JPanel pnlImage = new JPanel();
    private JPanel pnlNames = new JPanel();
    private JPanel pnlButtons = new JPanel();
    private JPanel pnlCity = new JPanel();
    private JPanel pnlCW = new JPanel();

    private ImageIcon imgMainIcon = new ImageIcon("resources/images/ApplicationIcon.png");
    private JLabel lblMainImage = new JLabel(imgMainIcon);


    private JLabel lblSeconds = new JLabel("60");
    private JLabel lblBNTU = new JLabel("Белорусский национальный технический университет");
    private JLabel lblFaculty = new JLabel("Факультет информационных технологий и робототехники");
    private JLabel lblKaphedra = new JLabel("Кафедра программаного обеспечения информационных систем и технологий");
    private JLabel lblCW = new JLabel("Курсовая работа");
    private JLabel lblSub = new JLabel("по дисциплине \"Программирование на языке Java\"");
    private JLabel lblTheme = new JLabel("\"Расчёт кинематических характеристик тела при свободном падении\"");
    private JLabel lblStudent = new JLabel("<html>Выполнил: студент группы 10702420" +
            "<br/>Слепцов Даниил Алексеевич<html>");
    private JLabel lblTeacher = new JLabel("<html>Преподаватель: к.ф.-м.н., доц." +
            "<br/>Сидорик Валерий Владимирович<html>");
    private JLabel lblCity = new JLabel("Минск 2023");

    private JButton btnExit = new JButton("Выход (" + lblSeconds.getText() + ")");
    private JButton btnNext = new JButton("Далее");

    public SplashScreen() {
        Timer timer = new Timer(1000, new TimerListener(lblSeconds,btnExit));
        timer.start();

        pnlCW.setPreferredSize(new Dimension(420,100));
        pnlContent.setLayout(new FlowLayout());

        lblBNTU.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
        lblBNTU.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFaculty.setFont(new Font("Montserrat Medium", Font.BOLD, 16));
        lblFaculty.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblKaphedra.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblKaphedra.setFont(new Font("Montserrat Medium", Font.PLAIN, 12));
        lblCW.setFont(new Font("Montserrat Medium",Font.BOLD,18));
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

        pnlCW.add(Box.createVerticalStrut(50));
        pnlCW.add(lblCW, BorderLayout.NORTH);
        pnlCW.add(lblSub, BorderLayout.CENTER);
        pnlCW.add(lblTheme, BorderLayout.SOUTH);

        pnlNames.setLayout(new BoxLayout(pnlNames,BoxLayout.Y_AXIS));
        pnlNames.add(lblStudent);
        pnlNames.add(Box.createVerticalStrut(15));
        pnlNames.add(lblTeacher);

        pnlImage.setLayout(new FlowLayout());
        pnlImage.add(lblMainImage);
        pnlImage.add(Box.createHorizontalStrut(20));
        pnlImage.add(pnlNames);

        pnlCity.setPreferredSize(new Dimension(400,70));
        pnlCity.add(lblCity, BorderLayout.CENTER);

        pnlButtons.add(btnExit);
        pnlButtons.add(btnNext);

        pnlContent.add(lblBNTU);
        pnlContent.add(lblFaculty);
        pnlContent.add(lblKaphedra);
        pnlContent.add(pnlCW);
        pnlContent.add(pnlImage);
        pnlContent.add(pnlCity);

        pnlContent.add(pnlButtons,BorderLayout.SOUTH);
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