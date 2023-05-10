package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
/**
 * Класс представляющий окно "О программе".
 * В нем имеется 3 раздела, для затрагивающие разные аспекты программы, так как:
 * общая информация, работа с графиками и формулы
 * @author Sleptsov D.A.
 * */
public class AboutProgramWindow extends JFrame {
    private JPanel pnlMenu = new JPanel();
    private JLabel lblAbout = new   JLabel("О программе");
    private JLabel lblVersion = new   JLabel("Версия 1.1");
    private JButton btnInfo = new JButton("Общая информация о программе");
    private JButton btnGraphics = new JButton("Работа с графиками");
    private JButton btnFormulas = new JButton("Формулы и детали рассчёта");
    private Font fontButtons = new Font("Microsoft", Font.PLAIN, 14);
    private Font fontMainLabel = new Font("Microsoft", Font.BOLD, 16);
    private  Color lightBlue = new Color(66,133,180);
    String infoString = "Общая информация о программе"+ "\n" + "\n"+
            "Программа \"Расчет кинетических характеристик тела при свободном падении\" " +
            "- это удобное и эффективное средство для исследования кинематики тела сброшенного вертикально вниз" + "\n" + "\n"+
            "Программа в своей работе использует следущие данные:"+ "\n"+
            "Входные данные:" + "\n"+
            "   1. Масса тела, m (по умолчанию равна 1 кг)" + "\n"+
            "   2. Высота с которой тело будет сброшено, n (по умолчанию равна 1000 м)" + "\n"+
            "   3. Начальная скорость тела, V0 (по умолчанию равна 0 м/c)" + "\n"+
            "Выходные данные:" + "\n"+
            "   1. Максимальная скорость, м/с" + "\n"+
            "   2. Максимальная энергия, Дж " + "\n"+
            "   3. Время падения, с" + "\n"+ "\n"+
            "Возможности программы:"+ "\n" +
            "   1. Определение максимальной скорости, максимальной энергии и времени  падения тела" + "\n" +
            "   2. Отображение и сохраниние графиков зависимости (подробнее в разделе \"Работа с графиками\" )"+ "\n";
    String graphicsString = "Работа с графиками"+ "\n" + "\n"+
            "Программа позволяет создавать графики зависимости скорости/энергии от высоты/времени"+ "\n" +
            "Ход работы программы:" + "\n" +
            "   1. Рассчёт основных данных(макс. скорость, энергия и время падения)"+ "\n" +
            "   2. Формирование массива точек для составления графика"+ "\n" +
            "         2.1 Разделение оси X (высота/время) на 1000 участков"+ "\n" +
            "         2.2 Вычисление значения функции(скорость/энергия) в граничных точках участков"+ "\n" +
            "         2.3 Запись всех вычесленных координат в массив точек"+ "\n" +
            "   3. Создание холста на котором будет изображен график"+ "\n" +
            "   4. Построение графика по точкам из массива"+ "\n" +"\n"+
            "Для построения графиков в программе использована библиотека FreeChart, которая предоставляет широкий набор " +
            "инструментов для удобной работы, настройки и сохранения графиков" +"\n"+
            "Предоставляемые программой возможности для взаимодействия с графиками и их настройки: "+"\n"+
            "   1. Масштабирвоание участка графика посредством выделения участка масштабирвоания удержанием левой кнопки мыши "+ "\n" +
            "   2. Изменение цвета графика, толщины и стиля линии, стиля координатных осей и др."+ "\n" +
            "   3. Сохраниение графиков в форматах PDF, PNG и SVG"+ "\n";

    private JPanel pnlTextArea = new JPanel(new BorderLayout());
    private JTextArea textArea = new JTextArea();
    private ImageIcon imgMainIcon = new ImageIcon("resources/images/Безымянный.png");
    private JLabel lblMainImage = new JLabel(imgMainIcon);


    public AboutProgramWindow() {
        // Задаем размеры окна
        setSize(920, 490);
        // Устанавливаем заголовок
        setTitle("О программе");
        // Устанавливаем действие при закрытии окна
        setResizable(false);
        setLocationRelativeTo(null);


        pnlMenu.setBackground(Color.WHITE);
        pnlMenu.setPreferredSize(new Dimension(300, getHeight()));
        // Добавляем панель в окно

        // Устанавливаем прозрачный цвет фона кнопок
        btnInfo.setBackground(new Color(0, 0, 0, 0));
        btnInfo.setBorder(null);
        btnGraphics.setBackground(new Color(0, 0, 0, 0));
        btnGraphics.setBorder(null);
        btnFormulas.setBackground(new Color(0, 0, 0, 0));
        btnFormulas.setBorder(null);
        textArea.setText(infoString);
        textArea.setFont(new Font("Microsoft", Font.PLAIN, 14));


        lblAbout.setBorder(BorderFactory.createEmptyBorder(15, 0, 50, 0));
        lblVersion.setBorder(BorderFactory.createEmptyBorder(250, 20, 25, 0));
        // Устанавливаем цвет текста кнопок
        btnInfo.setForeground(lightBlue);
        btnGraphics.setForeground(lightBlue);
        btnFormulas.setForeground(lightBlue);

        lblAbout.setFont(fontMainLabel);
        lblVersion.setFont(fontMainLabel);

        // Устанавливаем шрифт текста кнопок
        btnInfo.setFont(fontButtons);
        btnGraphics.setFont(fontButtons);
        btnFormulas.setFont(fontButtons);


        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(true);
                lblMainImage.setVisible(false);
                textArea.setText(null);
                textArea.setText(infoString);
            }
        });
        btnGraphics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(true);
                lblMainImage.setVisible(false);
                textArea.setText(null);
                textArea.setText(graphicsString);
            }
        });
        btnFormulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false);
                lblMainImage.setVisible(true);
            }
        });

        // Добавляем панель с текстовой областью на окно
        pnlTextArea.add(scrollPane, BorderLayout.CENTER);
        pnlTextArea.setBackground(Color.WHITE);
        lblMainImage.setVisible(false);
        pnlTextArea.add(lblMainImage, BorderLayout.NORTH);
        add(pnlTextArea, BorderLayout.CENTER);

        // Добавляем кнопки на панель
        pnlMenu.add(lblAbout);
        pnlMenu.add(btnInfo);
        pnlMenu.add(btnGraphics);
        pnlMenu.add(btnFormulas);
        pnlMenu.add(lblVersion);


        // Устанавливаем действие при наведении на кнопки
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setForeground(Color.GRAY);
                button.setBackground(Color.WHITE); // Добавляем светло-серый цвет фона
            }
            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setForeground(lightBlue);
                button.setBackground(Color.WHITE); // Возвращаем прозрачный цвет фона
            }
        };
        btnInfo.addMouseListener(mouseAdapter);
        btnGraphics.addMouseListener(mouseAdapter);
        btnFormulas.addMouseListener(mouseAdapter);
        add(pnlMenu, BorderLayout.WEST);
        // Отображаем окно
        setVisible(true);
    }
}
