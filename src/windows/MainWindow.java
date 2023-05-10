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
 *
 * @author Sleptsov D.A.
 */
public class MainWindow extends JFrame {
    // объект для расчётов. Реализует интерфейс PhysMethods
    private static final Calculator calculator = new Calculator();
    // с помощью перменной index будет остлеживаться история решений
    private int index = 0;
    private Solution solution; // представляет одно решение, содержащее все входные и выходные параметры
    private SolutionsHistory history = new SolutionsHistory(); // объект представляющий историю решений

    // поля для хранения данных
    private double weightValue = 0;
    private double heightValue = 0;
    private double startSpeedValue = 0;
    private double maxSpeedValue = 0;
    private double energyValue = 0;
    private double fallTimeValue = 0;

    // Инициализация окна
    public MainWindow() {
        int a = 5;
        // Установка свойств главного окна
        setTitle("Кинематичека тела при свободном падении");
        setSize(1000, 618);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1100, 618));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar mnuHeader = new JMenuBar();
        JMenuItem mniAbout = new JMenuItem("О программе");
        JMenu mnuEdit = new JMenu("Дополнительно");
        JMenuItem mniExit = new JMenuItem("Выход");
        JMenuItem mniAuthor = new JMenuItem("Об авторе");

        // Создание верхнего меню
        mnuEdit.add(mniAbout);
        mnuEdit.add(mniAuthor);
        mnuEdit.add(mniExit);
        mnuHeader.add(mnuEdit);
        setJMenuBar(mnuHeader);

        JPanel pnlMain = new JPanel(new BorderLayout()); // основная панель, на которой будут размещаться остальные
        JPanel pnlRight = new JPanel(new BorderLayout());
        JPanel pnlLeft = new JPanel();
        JPanel pnlGraphics = new JPanel(new BorderLayout());
        JPanel pnlTopLeft = new JPanel(); // панель для размещения компонентов в верхней части левой панели

        // задание свойств основных панелей
        pnlMain.setBackground(Color.WHITE);
        pnlRight.setBackground(Color.WHITE);
        pnlGraphics.setPreferredSize(new Dimension(0, 200));
        pnlGraphics.setBackground(Color.WHITE);
        pnlLeft.setPreferredSize(new Dimension(370, 0));

        BoxLayout topPanelLayout = new BoxLayout(pnlTopLeft, BoxLayout.Y_AXIS);
        pnlTopLeft.setLayout(topPanelLayout);
        pnlTopLeft.setPreferredSize(new Dimension(370, 540));

        // панели графиков зависимости
        JPanel pnlGraphicSpeedHeight = new JPanel();
        JPanel pnlGraphicSpeedTime = new JPanel();
        JPanel pnlGraphicPotEnergyHeight = new JPanel();
        JPanel pnlGraphicPotEnergyTime = new JPanel();
        JPanel pnlGraphicKinEnergyHeight = new JPanel();
        JPanel pnlGraphicKinEnergyTime = new JPanel();

        // объекты для отрисовки грфиков (для каждого свой)
        GraphicsPainter speedHGraphicPainter = new GraphicsPainter("Скорость",
                "Скорость",
                "Высота");
        GraphicsPainter speedTGraphicPainter = new GraphicsPainter("Скорость",
                "Скорость",
                "Время");
        GraphicsPainter PotentialEnergyHGraphicPainter = new GraphicsPainter("Потенциальная энергия",
                "Потенциальная энергия",
                "Высота");
        GraphicsPainter KineticEnergyHGraphicPainter = new GraphicsPainter("Кинетическая энергия",
                "Кинетическая энергия",
                "Высота");

        GraphicsPainter KineticEnergyTGraphicPainter = new GraphicsPainter("Кинетическая энергия",
                "Кинетическая энергия",
                "Время");

        GraphicsPainter PotentialEnergyTGraphicPainter = new GraphicsPainter("Потенциальная энергия",
                "Потенциальная энергия",
                "Время");
        // объекты графков (предоставляется библиотекой FreeChart)
        JFreeChart chartSpeedHeight = speedHGraphicPainter.createChart(speedHGraphicPainter.createDataset());
        JFreeChart chartSpeedTime = speedTGraphicPainter.createChart(speedTGraphicPainter.createDataset());
        JFreeChart chartPotEnergyHeight = PotentialEnergyHGraphicPainter.createChart(PotentialEnergyHGraphicPainter.createDataset());
        JFreeChart chartPotEnergyTime = PotentialEnergyTGraphicPainter.createChart(PotentialEnergyTGraphicPainter.createDataset());
        JFreeChart chartKinEnergyHeight = KineticEnergyHGraphicPainter.createChart(KineticEnergyHGraphicPainter.createDataset());
        JFreeChart chartKinEnergyTime = KineticEnergyTGraphicPainter.createChart(KineticEnergyTGraphicPainter.createDataset());

        ChartPanel pnlSpeedHeight = new ChartPanel(chartSpeedHeight);
        ChartPanel pnlSpeedTime = new ChartPanel(chartSpeedTime);
        ChartPanel pnlPotEnergyHeight = new ChartPanel(chartPotEnergyHeight);
        ChartPanel pnlPotEnergyTime = new ChartPanel(chartPotEnergyTime);
        ChartPanel pnlKinEnergyHeight = new ChartPanel(chartKinEnergyHeight);
        ChartPanel pnlKinEnergyTime = new ChartPanel(chartKinEnergyTime);

        JTabbedPane tabForGraphics = new JTabbedPane(); // tabControl для удобного переключения между графиками

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

        JSplitPane splHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLeft, pnlRight); // разделение окна на 2 части

        splHorizontal.setResizeWeight(0); // устанавливаем вес разделителя
        splHorizontal.setEnabled(false); // запрещаем использовать разделитель

        JLabel lblInputMain = new JLabel("Вводные данные:");
        JLabel lblWeight = new JLabel("Масса тела (кг):");
        JLabel lblHeight = new JLabel("Высота (м):");
        JLabel lblStartSpeed = new JLabel("Начальная скорость (м/с):");
        JLabel lblOutputMain = new JLabel("Результаты вычислений:");
        JLabel lblMaxSpeed = new JLabel("Макс.скорость (м/c):");
        JLabel lblMaxEnergy = new JLabel("Макс. энергия (Дж):");
        JLabel lblFallTime = new JLabel("Время падения (с):");
        Font fontTitle = new Font("Arial", Font.BOLD, 18);
        Font styleForOptions = new Font("Arial", Font.BOLD, 16);
        lblInputMain.setFont(fontTitle);
        JTextField txtWeight = new JTextField();
        JTextField txtHeight = new JTextField();
        JTextField txtStartSpeed = new JTextField();
        JTextField txtMaxSpeed = new JTextField();
        JTextField txtMaxEnergy = new JTextField();
        JTextField txtFallTime = new JTextField();

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
        initFields(txtMaxSpeed, txtMaxEnergy, txtFallTime);

        txtWeight.addKeyListener(new TxtKeyListener());
        txtHeight.addKeyListener(new TxtKeyListener());
        txtStartSpeed.addKeyListener(new TxtKeyListener());

        JButton btnCalculate = new JButton("Рассчитать");
        JButton btnClear = new JButton("Очистить");

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
                        ErrorDialog.setMessage("Значениe полей \"масса\" и \"высота\" не может быть нулевым ");
                        ErrorDialog errorDialog = new ErrorDialog(getMainWindow(),"Ошибка");

                        if (weightValue == 0) {
                            txtWeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                        if (heightValue == 0) {
                            txtHeight.setBorder(BorderFactory.createLineBorder(new Color(255, 84, 84)));
                        }
                    } else if (weightValue > 10000 || heightValue > 10000 || startSpeedValue > 10000) {
                        ErrorDialog.setMessage("Значениe полей \"масса\", \"высота\" и \"начальная скорость\" не " +
                                "может быть больше чем 10000");
                        ErrorDialog errorDialog = new ErrorDialog(getMainWindow(),"Ошибка");
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
                        initFields(txtMaxSpeed, txtMaxEnergy, txtFallTime);
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
                    ErrorDialog.setMessage("Поля масса,  \"высота\" и \"начальная скорость\" не могут быть пустыми ");
                    ErrorDialog errorDialog = new ErrorDialog(getMainWindow(),"Ошибка");
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
                    if (index > 0) {
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
                    if (index < history.getHistorySize() - 1) {
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
     */
    private JFrame getMainWindow() {
        return this;
    }

    /**
     * Метод paintGraphic необходим для отрисовки графика зависимости
     *
     * @param painter - объект, ответсвенный за отрисовку графика
     * @param chart   - представляет холст для создания графика
     */
    private void paintGraphic(GraphicsPainter painter, JFreeChart chart) {
        painter.setStartSpeed(startSpeedValue);
        painter.setWeight(weightValue);
        painter.setHeight(heightValue);
        chart = painter.createChart(painter.updateDataset());
    }

    /**
     * Метод addGraphic необходим для добавления графика в панель
     *
     * @param pnlParent - панель-родитель, на которую будет добавляться график
     * @param pnlChild  - панель-потомок, которая будет содержать график
     */
    private void addGraphic(JPanel pnlParent, JPanel pnlChild) {
        pnlParent.setBackground(Color.WHITE);
        pnlParent.setLayout(new BorderLayout());
        pnlParent.setPreferredSize(new Dimension(0, 405));
        pnlParent.add(pnlChild, BorderLayout.CENTER);
    }

    /**
     * Метод roundToDecimalPlaces необходим для округления значения double до некоторого числа знаков после запятой
     *
     * @param d             - значение для округления
     * @param decimalPlaces - количество знаков после запятой
     */
    private double roundToDecimalPlaces(double d, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(d * factor) / factor;
    }

    /**
     * Метод findValues используя объект calculator находит искомые параметры
     */
    private void findValues() {
        maxSpeedValue = roundToDecimalPlaces(calculator.findSpeedUseHeight(heightValue, startSpeedValue), 3);
        energyValue = roundToDecimalPlaces(calculator.findMaxEnergy(weightValue, heightValue, startSpeedValue), 3);
        fallTimeValue = roundToDecimalPlaces(calculator.findFallTime(heightValue, startSpeedValue), 3);
    }

    /**
     * Метод addSolution добавляет новое решение в историю
     */
    private void addSolution() {
        history.add(new Solution(weightValue, heightValue, startSpeedValue,
                maxSpeedValue, energyValue, fallTimeValue));
        index = history.getHistorySize() - 1;
    }

    /**
     * Метод initFields устанавливает в текстовые поля результаты вычислений
     *
     */
    private void initFields(JTextField txtMaxSpeed, JTextField txtMaxEnergy, JTextField txtFallTime) {
        findValues();
        addSolution();
        txtMaxSpeed.setText(Double.toString(maxSpeedValue));
        txtMaxEnergy.setText(Double.toString(energyValue));
        txtFallTime.setText(Double.toString(fallTimeValue));
    }
}