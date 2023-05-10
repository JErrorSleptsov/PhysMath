package windows;

import listeners.TxtKeyListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import util.Calculator;
import util.GraphicsPainter;
import util.Solution;
import util.SolutionsHistory;
import windows.dialog.ErrorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Основной фрейм программы.
 * Класс включает в себя все визуальные компоненты, а также большинство
 * слушателей.
 * @author Sleptsov D.A.
 * */
public class MainWindow extends JFrame {
    Calculator calculator = new Calculator(); // объект для расчётов. Реализует интерфейс PhysMethods
    int index=0; // с помощью перменной index будет остлеживаться история решений
    Solution solution; // представляет одно решение, содержащее все входные и выходные параметры
    SolutionsHistory history = new SolutionsHistory(); // объект представляющий историю решений

    // поля для хранения данных
    private double weightValue = 0;
    private double heightValue = 0;
    private double startSpeedValue = 0;
    private double maxSpeedValue = 0;
    private double energyValue = 0;
    private double fallTimeValue = 0;


    private JMenuBar mnuHeader = new JMenuBar();
    private JMenuItem mniAbout = new JMenuItem("О программе");
    private JMenu mnuEdit = new JMenu("Дополнительно");
    private JMenuItem mniExit = new JMenuItem("Выход");
    private JMenuItem mniAuthor = new JMenuItem("Об авторе");

    private JPanel pnlMain = new JPanel(new BorderLayout()); // основная панель, на которой будут размещаться остальные
    private JPanel pnlRight = new JPanel(new BorderLayout());
    private JPanel pnlLeft = new JPanel();
    private JPanel pnlGraphics = new JPanel(new BorderLayout());
    private JPanel pnlTopLeft = new JPanel(); // панель для размещения компонентов в верхней части левой панели

    // панели графиков зависимости
    private JPanel pnlGraphicSpeedHeight = new JPanel();
    private JPanel pnlGraphicSpeedTime = new JPanel();
    private JPanel pnlGraphicPotEnergyHeight = new JPanel();
    private JPanel pnlGraphicPotEnergyTime = new JPanel();
    private JPanel pnlGraphicKinEnergyHeight = new JPanel();
    private JPanel pnlGraphicKinEnergyTime = new JPanel();

    private JSplitPane splHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLeft, pnlRight); // разделение окна на 2 части

    private JLabel lblInputMain = new JLabel("Вводные данные:");
    private JLabel lblWeight = new JLabel("Масса тела (кг):");
    private JLabel lblHeight = new JLabel("Высота (м):");
    private JLabel lblStartSpeed = new JLabel("Начальная скорость (м/с):");
    private JLabel lblOutputMain = new JLabel("Результаты вычислений:");
    private JLabel lblMaxSpeed = new JLabel("Макс.скорость (м/c):");
    private JLabel lblMaxEnergy = new JLabel("Макс. энергия (Дж):");
    private JLabel lblFallTime = new JLabel("Время падения (с):");

    private JTextField txtWeight = new JTextField();
    private JTextField txtHeight = new JTextField();
    private JTextField txtStartSpeed = new JTextField();
    private JTextField txtMaxSpeed = new JTextField();
    private JTextField txtMaxEnergy = new JTextField();
    private JTextField txtFallTime = new JTextField();

    private JButton btnCalculate = new JButton("Рассчитать");
    private JButton btnClear = new JButton("Очистить");


    private JTabbedPane tabForGraphics = new JTabbedPane(); // tabControl для удобного переключения между графиками

    // объекты для отрисовки грфиков (для каждого свой)
    private GraphicsPainter speedHGraphicPainter = new GraphicsPainter("Скорость",
            "Скорость",
            "Высота");
    private GraphicsPainter speedTGraphicPainter = new GraphicsPainter("Скорость",
            "Скорость",
            "Время");
    private GraphicsPainter PotentialEnergyHGraphicPainter = new GraphicsPainter("Потенциальная энергия",
            "Потенциальная энергия",
            "Высота");
    private GraphicsPainter KineticEnergyHGraphicPainter = new GraphicsPainter("Кинетическая энергия",
            "Кинетическая энергия",
            "Высота");

    private GraphicsPainter KineticEnergyTGraphicPainter = new GraphicsPainter("Кинетическая энергия",
            "Кинетическая энергия",
            "Время");

    private GraphicsPainter PotentialEnergyTGraphicPainter = new GraphicsPainter("Потенциальная энергия",
            "Потенциальная энергия",
            "Время");
    // объекты графков (предоставляется библиотекой FreeChart)
    private JFreeChart chartSpeedHeight = speedHGraphicPainter.createChart(speedHGraphicPainter.createDataset());
    private JFreeChart chartSpeedTime = speedTGraphicPainter.createChart(speedTGraphicPainter.createDataset());
    private JFreeChart chartPotEnergyHeight = PotentialEnergyHGraphicPainter.createChart(PotentialEnergyHGraphicPainter.createDataset());
    private JFreeChart chartPotEnergyTime = PotentialEnergyTGraphicPainter.createChart(PotentialEnergyTGraphicPainter.createDataset());
    private JFreeChart chartKinEnergyHeight = KineticEnergyHGraphicPainter.createChart(KineticEnergyHGraphicPainter.createDataset());
    private JFreeChart chartKinEnergyTime = KineticEnergyTGraphicPainter.createChart(KineticEnergyTGraphicPainter.createDataset());

    private ChartPanel pnlSpeedHeight = new ChartPanel(chartSpeedHeight);
    private ChartPanel pnlSpeedTime = new ChartPanel(chartSpeedTime);
    private ChartPanel pnlPotEnergyHeight = new ChartPanel(chartPotEnergyHeight);
    private ChartPanel pnlPotEnergyTime = new ChartPanel(chartPotEnergyTime);
    private ChartPanel pnlKinEnergyHeight = new ChartPanel(chartKinEnergyHeight);
    private ChartPanel pnlKinEnergyTime = new ChartPanel(chartKinEnergyTime);

    private Font fontTitle = new Font("Arial", Font.BOLD, 18);
    private Font styleForOptions = new Font("Arial", Font.BOLD, 16);


    // Инициализация окна
    public MainWindow() {
        // Установка свойств главного окна
        setTitle("Кинематичека тела при свободном падении");
        setSize(1000, 618);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1100, 618));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание верхнего меню
        mnuEdit.add(mniAbout);
        mnuEdit.add(mniAuthor);
        mnuEdit.add(mniExit);
        mnuHeader.add(mnuEdit);
        setJMenuBar(mnuHeader);

        // задание свойств основных панелей
        pnlMain.setBackground(Color.WHITE);
        pnlRight.setBackground(Color.WHITE);
        pnlGraphics.setPreferredSize(new Dimension(0, 200));
        pnlGraphics.setBackground(Color.WHITE);
        pnlLeft.setPreferredSize(new Dimension(370, 0));

        BoxLayout topPanelLayout = new BoxLayout(pnlTopLeft, BoxLayout.Y_AXIS);
        pnlTopLeft.setLayout(topPanelLayout);
        pnlTopLeft.setPreferredSize(new Dimension(370, 540));

        // создание графика скорость/высота
        addGraphic(pnlGraphicSpeedHeight, pnlSpeedHeight);
        tabForGraphics.addTab("Скорость/Высота", pnlGraphicSpeedHeight);

        // создание графика Скорость/Время
        addGraphic(pnlGraphicSpeedTime, pnlSpeedTime);
        tabForGraphics.addTab("Скорость/Время", pnlGraphicSpeedTime);

        // создание графика Пот.энергия/Высота
        addGraphic(pnlGraphicPotEnergyHeight, pnlPotEnergyHeight);
        tabForGraphics.addTab("Пот.энергия/Высота", pnlGraphicPotEnergyHeight);

        // создание графика Пот.энергия/Время
        addGraphic(pnlGraphicPotEnergyTime, pnlPotEnergyTime);
        tabForGraphics.addTab("Пот.энергия/Время", pnlGraphicPotEnergyTime);

        // создание графика Кин.энергия/Высота
        addGraphic(pnlGraphicKinEnergyHeight, pnlKinEnergyHeight);
        tabForGraphics.addTab("Кин.энергия/Высота", pnlGraphicKinEnergyHeight);

        // создание графика Кин.энергия/Время
        addGraphic(pnlGraphicKinEnergyTime, pnlKinEnergyTime);
        tabForGraphics.addTab("Кин.энергия/Время", pnlGraphicKinEnergyTime);

        pnlGraphics.add(tabForGraphics, BorderLayout.NORTH);

        splHorizontal.setResizeWeight(0); // устанавливаем вес разделителя
        splHorizontal.setEnabled(false); // запрещаем использовать разделитель


        lblInputMain.setFont(fontTitle);

        txtWeight.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));
        txtWeight.setText("1");

        txtHeight.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));
        txtHeight.setText("1000");

        txtStartSpeed.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));
        txtStartSpeed.setText("0");

        lblOutputMain.setFont(styleForOptions);

        txtMaxSpeed.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));
        txtMaxSpeed.setForeground(Color.BLACK);

        txtMaxEnergy.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));

        txtFallTime.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 25));

        weightValue = Double.parseDouble(txtWeight.getText());
        heightValue = Double.parseDouble(txtHeight.getText());
        startSpeedValue = Double.parseDouble(txtStartSpeed.getText());
        initFields();

        txtWeight.addKeyListener(new TxtKeyListener());
        txtHeight.addKeyListener(new TxtKeyListener());
        txtStartSpeed.addKeyListener(new TxtKeyListener());

        btnCalculate.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 30));
        btnClear.setMaximumSize(new Dimension(pnlLeft.getPreferredSize().width * 2, 30));

        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblInputMain, BorderLayout.CENTER);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblWeight);
        pnlTopLeft.add(txtWeight);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblHeight);
        pnlTopLeft.add(txtHeight);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblStartSpeed);
        pnlTopLeft.add(txtStartSpeed);
        pnlTopLeft.add(Box.createVerticalStrut(15));


        pnlTopLeft.add(lblOutputMain, BorderLayout.CENTER);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblMaxSpeed);
        pnlTopLeft.add(txtMaxSpeed);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblMaxEnergy);
        pnlTopLeft.add(txtMaxEnergy);
        pnlTopLeft.add(Box.createVerticalStrut(15));
        pnlTopLeft.add(lblFallTime);
        pnlTopLeft.add(txtFallTime);
        pnlTopLeft.add(Box.createVerticalStrut(65));

        pnlTopLeft.add(btnCalculate);
        pnlTopLeft.add(Box.createVerticalStrut(10));
        pnlTopLeft.add(btnClear);
        pnlTopLeft.add(Box.createVerticalStrut(100));
        pnlTopLeft.setPreferredSize(new Dimension(370, 900));


        pnlLeft.add(pnlTopLeft, BorderLayout.NORTH);


        pnlRight.add(pnlGraphics, BorderLayout.CENTER);

        pnlMain.add(splHorizontal, BorderLayout.CENTER);
        add(pnlMain);


        /* Обработчик для кнопки "Вычислить"
         *  Обработка события происходит в следующем порядке:
         *  1. Значения полей проходят валидацию(не пустое, не ноль)
         *  2. Конвертация из String в double и расчёт
         *  3. Вывод ответа и отрисовка графиков
         * */
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtWeight.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                txtHeight.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                txtStartSpeed.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                try {
                    weightValue = Double.parseDouble(txtWeight.getText());
                    heightValue = Double.parseDouble(txtHeight.getText());
                    startSpeedValue = Double.parseDouble(txtStartSpeed.getText());

                    if (weightValue == 0 || heightValue == 0) {
                        ErrorDialog dialog = new ErrorDialog(getMainWindow(), "Ошибка", "Значениe" +
                                " полей \"масса\" и \"высота\" не может быть нулевым ");
                        if (weightValue == 0) {
                            txtWeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                        if (heightValue == 0) {
                            txtHeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                    }
                    else if (weightValue > 10000 || heightValue > 10000 || startSpeedValue > 10000) {
                        ErrorDialog dialog = new ErrorDialog(getMainWindow(), "Ошибка", "Значениe" +
                                " полей \"масса\", \"высота\" и \"начальная скорость\" не может быть больше чем 10000");
                        if (weightValue > 10000) {
                            txtWeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                        if (heightValue > 10000) {
                            txtHeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                        if (startSpeedValue > 10000) {
                            txtStartSpeed.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                    } else {
                        initFields();
                        paintGraphic(speedHGraphicPainter, chartSpeedHeight);
                        paintGraphic(speedTGraphicPainter, chartSpeedTime);
                        paintGraphic(PotentialEnergyHGraphicPainter, chartPotEnergyHeight);
                        paintGraphic(PotentialEnergyTGraphicPainter, chartPotEnergyTime);
                        paintGraphic(KineticEnergyHGraphicPainter, chartKinEnergyHeight);
                        paintGraphic(KineticEnergyTGraphicPainter, chartKinEnergyTime);
                    }

                } catch (NumberFormatException ex) {
                    txtWeight.setText("0");
                    txtHeight.setText("0");
                    txtStartSpeed.setText("0");
                    txtWeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                    txtHeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                    txtStartSpeed.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                    ErrorDialog dialog = new ErrorDialog(getMainWindow(), "Ошибка", "Поля \"масса\"," +
                            " \"высота\" и \"начальная скорость\" не могут быть пустыми ");
                }
                repaint();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtWeight.setText(null);
                txtHeight.setText(null);
                txtStartSpeed.setText(null);
                txtMaxSpeed.setText(null);
                txtMaxEnergy.setText(null);
                txtFallTime.setText(null);

            }
        });
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z && e.getID() == KeyEvent.KEY_PRESSED) {
                    if (index>0){
                        index--;
                        solution = history.get(index);
                        txtWeight.setText(Double.toString(solution.getWeight()));
                        txtHeight.setText(Double.toString(solution.getHeight()));
                        txtStartSpeed.setText(Double.toString(solution.getStartSpeed()));
                        txtMaxSpeed.setText(Double.toString(solution.getMaxSpeed()));
                        txtMaxEnergy.setText(Double.toString(solution.getMaxEnergy()));
                        txtFallTime.setText(Double.toString(solution.getFallTime()));
                        return true; // Возвращаем true, чтобы указать, что это событие обработано
                    }
                }
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y && e.getID() == KeyEvent.KEY_PRESSED) {
                    if (index<history.getHistorySize()-1) {
                        index++;
                        solution = history.get(index);
                        txtWeight.setText(Double.toString(solution.getWeight()));
                        txtHeight.setText(Double.toString(solution.getHeight()));
                        txtStartSpeed.setText(Double.toString(solution.getStartSpeed()));
                        txtMaxSpeed.setText(Double.toString(solution.getMaxSpeed()));
                        txtMaxEnergy.setText(Double.toString(solution.getMaxEnergy()));
                        txtFallTime.setText(Double.toString(solution.getFallTime()));
                        return true; // Возвращаем true, чтобы указать, что это событие обработано
                    }
                }
                return false; // Возвращаем false, чтобы указать, что это событие не было обработано
            }
        });

        // добавление обработчика для изменение размера понели графиков
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int height = getHeight();  // получение высоты окна
                tabForGraphics.setPreferredSize(new Dimension(0, height - 65));  // установка новой высоты панели
                tabForGraphics.repaint();
                tabForGraphics.revalidate();  // перерисовка панели
            }
        });

        // Обработчик для вызова окна "Об авторе"
        mniAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthorWindow();
            }
        });
        // Обработчик для вызова окна "О программе"
        mniAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutProgramWindow();
            }
        });
        // Обработчик для выхода из программы
        mniExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    /**
     * Метод getMainWindow возвращает ссылку на главное окно
     * ПОлезно при создании объекта диалогового окна в анономном классе,
     * где невозможно получить ссылку на окно с помощью ключевого слова this
     * */
    private JFrame getMainWindow() {
        return this;
    }
    /**
     * Метод paintGraphic необходим для отрисовки графика зависимости
     * @param painter - объект, ответсвенный за отрисовку графика
     * @param chart - представляет холст для создания графика
     * */
    private void paintGraphic(GraphicsPainter painter, JFreeChart chart) {
        painter.setStartSpeed(startSpeedValue);
        painter.setWeight(weightValue);
        painter.setHeight(heightValue);
        chartSpeedHeight = painter.createChart(painter.updateDataset());
    }
    /**
     * Метод addGraphic необходим для добавления графика в панель
     * @param pnlParent - панель-родитель, на которую будет добавляться график
     * @param pnlChild - панель-потомок, которая будет содержать график
     * */
    private void addGraphic(JPanel pnlParent, JPanel pnlChild) {
        pnlParent.setBackground(Color.WHITE);
        pnlParent.setLayout(new BorderLayout());
        pnlParent.setPreferredSize(new Dimension(0, 405));
        pnlParent.add(pnlChild, BorderLayout.CENTER);
    }
    /**
     * Метод roundToDecimalPlaces необходим для округления значения double до некоторого числа знаков после запятой
     * @param d - значение для округления
     * @param decimalPlaces - количество знаков после запятой
     * */
    private double roundToDecimalPlaces(double d, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(d * factor) / factor;
    }
    /**
     * Метод findValues используя объект calculator находит искомые параметры
     * */
    private void findValues() {
        maxSpeedValue = roundToDecimalPlaces(calculator.findSpeedUseHeight(heightValue, startSpeedValue), 3);
        energyValue = roundToDecimalPlaces(calculator.findMaxEnergy(weightValue, heightValue, startSpeedValue), 3);
        fallTimeValue = roundToDecimalPlaces(calculator.findFallTime(heightValue, startSpeedValue), 3);
    }
    /**
     * Метод addSolution добавляет новое решение в историю
     * */
    private void addSolution(){
        history.add(new Solution(weightValue,heightValue,startSpeedValue,
                maxSpeedValue,energyValue,fallTimeValue));
        index = history.getHistorySize()-1;
    }
    /**
     * Метод initFields устанавливает в текстовые поля результаты вычислений
     * */
    private void initFields() {
        findValues();
        addSolution();
        txtMaxSpeed.setText(Double.toString(maxSpeedValue));
        txtMaxEnergy.setText(Double.toString(energyValue));
        txtFallTime.setText(Double.toString(fallTimeValue));
    }
}