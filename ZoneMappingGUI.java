import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class ZoneMappingGUI implements ActionListener{

    int WINDOW_WIDTH = 1000;
    int WINDOW_HEIGHT = 1000;
    int TOP_WIDTH = 900;
    int TOP_HEIGHT = 75;
    int CENTER_WIDTH = 900;
    int CENTER_HEIGHT = 825;
    int FONT_SIZE = 24;
    private int FONT_SIZE2 = 25;
    private int FONT_SIZE3 = 25;   
    private int FONT_SIZE4 = 25;

    //GUI components
    JFrame frame;
    JButton goB;
    JButton showB;
    JPanel topP;
    JPanel centerP;
    JPanel wordP;
    JPanel buttonP;
    JPanel meaningP;

    JLabel[] zoneLabels;
    JPanel[] zones;



    JFileChooser fc = new JFileChooser();
    Color panelColor = new Color(194,214,235); //Metallic blue
    Color bgColor = new Color(255,250,240); //Light beige
    Font font = new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE);

    String fileName;


    //Create the GUI for our program
    @SuppressWarnings("unchecked")
    public void createGUI(){
        //Set up dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width * 7/8;
        WINDOW_HEIGHT = screenSize.height * 7/8;
        TOP_WIDTH = WINDOW_WIDTH-100;
        TOP_HEIGHT = WINDOW_HEIGHT * 1 / 10;
        CENTER_WIDTH = TOP_WIDTH;
        CENTER_HEIGHT = WINDOW_HEIGHT;
        FONT_SIZE = WINDOW_WIDTH / 36;
        FONT_SIZE2 = WINDOW_WIDTH/40;
        FONT_SIZE3 = WINDOW_WIDTH/50;
        FONT_SIZE4 = WINDOW_WIDTH/55;

        Font topFont = new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE3);
        Font topFontSml = new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE4);

        //Set up frame
        frame = new JFrame("ZONE MAPPING GUI");
        frame.setBounds(25, 25, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main JPanel
        JPanel mainP = new JPanel(); //Use default border layout
        mainP.setBackground(panelColor);

        //Set up top panel with button
        topP = new JPanel();
        topP.setPreferredSize(new Dimension(TOP_WIDTH, TOP_HEIGHT));
        topP.setLayout(new GridLayout(1, 3, 10, 0)); //1x3 with 10px hgap

        //add button to top panel
        goB = new JButton("im a booton press me to highlight zone 9");
        goB.addActionListener(this);
        topP.add(goB);

        //Set up structure of center panel for zone map (3x4)
        centerP = new JPanel();
        centerP.setPreferredSize(new Dimension(CENTER_WIDTH, CENTER_HEIGHT));
        centerP.setLayout(new GridLayout(3, 4, 10, 10)); //3x4 with 10px hgap 10px vgap
        centerP.setBackground(panelColor);

        //make zone labels with 0th (zone 12) label highlighted
        makeZoneLabels(0);

        mainP.add(topP, BorderLayout.PAGE_START);
        mainP.add(centerP, BorderLayout.CENTER);

        frame.add(mainP);
        frame.setVisible(true);

    }

    //Fill zoneLabels array with Zone strings,
    private void makeZoneLabels(int currZone){
        //Fill zoneLabels array with Zone strings
        zoneLabels = new JLabel[12];
        for(int i = 0; i < 12; i++){
            int zoneNum;
            if(i <= 3){
                zoneNum = (12 -i*3);
            }
            else if(i <= 7){
                zoneNum = (11 -(i-4)*3);
            }
            else{
                zoneNum = (10 -(i-8)*3);
            }
            zoneLabels[i] = new JLabel("ZONE " + zoneNum);
            zoneLabels[i].setFont(new Font("Verdana",1,20));

        }
        //Fill zones array with zone JPanels containing zoneLabels strings
        zones = new JPanel[12];
        for(int i = 0; i < 12; i++){
            zones[i] = new JPanel();
            zones[i].setLayout(new GridLayout(0, 1, 10, 10));
            zones[i].add(zoneLabels[i]);
            if(i == currZone){
                zones[i].setBackground(Color.red);
            }
            centerP.add(zones[i]);

        }
    }


    public void actionPerformed(ActionEvent e){
            centerP.removeAll();
            makeZoneLabels(1);

            //Force components to repaint
            centerP.revalidate();
            centerP.repaint();
    }
}
