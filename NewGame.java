/* Game.java			Rishi  Upadhyay/Aditesh Kumar   4-20-15
Game in 5 weeks project */
import java.awt.*; //import classes
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Thread;
public class NewGame{//game class header

    public static void main(String [] args){//main method header
        NewGame g = new NewGame();//create instance of Game
    }
    public NewGame(){//game constructor header
        JFrame alg = new JFrame("Algebra 2tor");
        alg.pack();
        alg.setDefaultCloseOperation(alg.DISPOSE_ON_CLOSE);//set the default close operation
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = 4*screenSize.width/5;
        int ySize = 4*screenSize.height/5;
        alg.setSize(xSize,ySize);//set size to 1000 by 1000
        alg.setLocationRelativeTo(null);
        alg.setResizable(true);//set resizable to true
        Paneltoholdpanels pthp = new Paneltoholdpanels(xSize,ySize);//create instance of Title
        alg.setContentPane(pthp);//set the content pane as the instance of title
        alg.setVisible(true);//set visible to true
    }
}
class Paneltoholdpanels extends JPanel{//paneltoholdpanels class header
    CardLayout cards;//declare cardlayout cards
    Levelselect ls;
    Title t;
    Gamepanel gp;
    GameOver go;
    ActualInstructions ai;
    public Paneltoholdpanels(int xSize, int ySize){//paneltoholdpanels constructor
        ProblemsAndAnswers pa = new ProblemsAndAnswers();        					        //instantiate the instance of ProblemsAndAnswers
        cards = new CardLayout();//initialize the card layout
        setLayout(cards);//set the layout to Card layout
        t = new Title(this,xSize,ySize);//create an instance of JPanel Title
        add(t, "First");//add the instance of JPanel title to the card layout
        ai = new ActualInstructions(this,xSize,ySize);//create an instance of JPanel Actual Instructions 0
        add(ai, "Second");//add the instance of JPanel actualInstructions
        ls = new Levelselect(this,pa,xSize,ySize);//create an instance of JPanel Levelselect
        add(ls,"Fourth");//add the instance of the JPanel Levelselect
        gp = new Gamepanel(this,pa,xSize,ySize,ls);//create an instance of JPanel Gamepanel
        add(gp, "Third");//add the instance of JPanel Gamepanel
        go = new GameOver(this,pa,xSize,ySize);//create an instance of JPanel GameOver
        add(go,"Five");
    }
}
class Title extends JPanel implements ActionListener{//Title class header
    private JButton instructions;//declare JButton instructions
    private JButton play;//declare JButton play
    private JLabel highscore;//declare JLabel highscore
    private JLabel title;//declare JLabel title
    private Font title2;//declare Font title2
    private Font high;//declare Font high
    private boolean pclicked = false;//declare 2 booleans for the buttons
    private boolean iclicked = false;
    private JPanel cards2;//declare a JPanel
    int xSize2;
    int ySize2;

    public Title(JPanel cards,int xSize, int ySize){//title constructer header
        this.xSize2 = xSize;
        this.ySize2 = ySize;
        setLayout(null);//set layout to null
        play = new JButton("Play");//instantiate the play button
        play.setBounds(5*xSize/16,3*ySize/7,3*xSize/8,ySize/9);//set bounds for the play button
        play.addActionListener(this);
        instructions = new JButton("Instructions");//instantiate the instruction button
        instructions.setBounds(5*xSize/16,4*ySize/7,3*xSize/8,ySize/9);//set bounds for the instructions button
        instructions.addActionListener(this);
        //highscore = new JLabel("High Score: 0");//instantiate the highscore label
        //highscore.setBounds(300,700,200,50);//set bounds for the highscore label
        title = new JLabel("Algebra 2tor");//instantiate the title label
        title.setBounds(xSize/4,ySize/7,5*xSize/8,ySize/7);//set bounds for the title label
        title2 = new Font("Helvetica",Font.PLAIN,96);//instantiat the font as helvetica size 60
        title.setFont(title2);//set the title labels font to helvetica size 60
        //high = new Font("Helvetica",Font.PLAIN,24);//instantiate the font as helvetica size 24
        //highscore.setFont(high);//set the highscore label's font to helvetica size 24
        add(play);//add play button
        add(instructions);//add instructions button
        //add(highscore);//add highscore label
        add(title);//add title label
        cards2 = cards;
    }
    public void actionPerformed(ActionEvent evt){//action performed method header
        if(evt.getSource() == play){//if the button is the play button
            pclicked = true;//change pclicked to true
        }else if(evt.getSource() == instructions){//if the button is the instructions
            iclicked = true;//change iclicked to true
        }
        switchCards();//call method switchCards
    }
    public void switchCards(){//method switchCards header
        if(pclicked){//if pclicked do nothing
            ((CardLayout)cards2.getLayout()).show(cards2,"Fourth");
            pclicked = false;
        }
        if(iclicked){//if iclicked
            ((CardLayout)cards2.getLayout() ).next(cards2);//switch to the instructions card
            iclicked = false;
        }
    }

    public void paintComponent(Graphics g) {//paint component method header
        super.paintComponent(g);//call paint component
        Image image = Toolkit.getDefaultToolkit().getImage("SquareRoot.png");
        Image image2 = Toolkit.getDefaultToolkit().getImage("Exponent.png");
        g.drawImage(image2, 5 * xSize2 / 6, 5 * ySize2 / 7, xSize2/10, ySize2/10, this);
        g.drawImage(image, xSize2 / 10, ySize2 / 6, xSize2/10, ySize2/10, this);
        setBackground(Color.GREEN);//set background to green
    }
}
class ActualInstructions extends JPanel implements ActionListener{//class Actual Instructions header
    JLabel title;//declare JLabel title
    JTextArea objective;//declare JTextArea objective
    JTextArea difficulty;//declare JTextArea difficulty
    JTextArea points;//declare JTextArea points
    String string1;//declare String string1
    String string2;//declare String string2
    Font titleFont;//declare Font titleFont
    Font normalFont;//declare Font normalFont
    JButton back;//declare JButton back
    JPanel c2;//declare JPanel c2

    ActualInstructions(JPanel cards,int xSize,int ySize){//constructor header
        setLayout(null);//set the layout to null
        string1 = "You are stuck in an abandoned building. There are exactly 15 hallways between you and the exit "+
        ",each with its own door. However, the bats have stolen the keys and you must use your skills from Algebra 2."+
        " To open the door, you must shoot the bat with the key you need(the correct answer).";//declare both strings to instructions
        string2 = "You will score +10 points for getting a question right, and -5 points for getting one wrong."+
                " You will lose 2 points if you don't answer in time";
        titleFont = new Font("Times New Roman",Font.BOLD,64);//declare the fonts as size 60 Times New Roman
        normalFont = new Font("Comic Sans",Font.PLAIN,24);//declare the font as size 24 Comic Sans
        title = new JLabel("Instructions");//declare the title JLabel
        title.setFont(titleFont);//set the font as size 60 Times New Roman
        title.setBounds(xSize/3,ySize/20,xSize/3,ySize/8);//set the bounds of title
        add(title);//add the title
        objective = new JTextArea(string1);//declare a new TextArea
        objective.setFont(normalFont);//set the font to 24 Comic Sans
        objective.setBounds(xSize/8,ySize/4,3*xSize/4,ySize/4);//set the bounds of the text area
        objective.setEditable(false);//set editable to false
        objective.setLineWrap(true);
        objective.setWrapStyleWord(true);
        objective.setBackground(Color.GREEN);
        add(objective);//add the text area
        difficulty = new JTextArea("The difficulty of the problems will increase as you go further into the problem set."+
        " You will be able to select the category of Algebra 2/Trig that you wish to practice");//declare the new Text Area to more instructions
        difficulty.setFont(normalFont);//set the font to 24 Comic Sans
        difficulty.setBounds(xSize/6,11*ySize/20,2*xSize/3,ySize/6);//set the bounds of the text area
        difficulty.setEditable(false);//set editable to false
        difficulty.setLineWrap(true);
        difficulty.setWrapStyleWord(true);
        difficulty.setBackground(Color.GREEN);
        add(difficulty);//add the text area
        points = new JTextArea(string2);//declare the new Text Area to more instructions
        points.setFont(normalFont);//set the font to 24 Comic Sans
        points.setBounds(xSize/8,3*ySize/4,3*xSize/4,ySize/7);//set the bounds of the text area
        points.setEditable(false);//set editable to false
        points.setLineWrap(true);
        points.setWrapStyleWord(true);
        points.setBackground(Color.GREEN);
        add(points);//add the text area
        back = new JButton("Back");//declare the JButton
        back.setFont(normalFont);//set the font to 24 Comic Sans
        back.setBounds(4*xSize/9,25*ySize/28,xSize/9,ySize/24);//set the bounds of the JButton
        back.addActionListener(this);//add an Action Listener to the button
        add(back);//add the button
        c2 = cards;//declare the JPanel as the panel that was sent as an argument
    }
    public void paintComponent(Graphics g){//paint component method header
        setBackground(Color.GREEN);
        super.paintComponent(g);//call paint component

    }
    public void actionPerformed(ActionEvent e){//action performed method header
        ((CardLayout)c2.getLayout() ).first(c2);//use card layout to go back to the first page
    }
}
class Levelselect extends JPanel implements ActionListener{
    JButton Exponents;
    JButton Equations;
    JButton AbsoluteValues;
    JButton Trigonometry;
    JButton Logarithms;
    JButton back;
    JPanel cards2;
    JLabel title;
    Font titleFont;
    String type;
    ProblemsAndAnswers pa;

    public Levelselect(JPanel cards, ProblemsAndAnswers pa, int xSize, int ySize){
        titleFont = new Font("Times New Roman",Font.BOLD,64);//declare the fonts as size 60 Times New Roman

        setLayout(null);
        Exponents = new JButton("Exponents");//instantiate Exponents button
        Exponents.setBounds(xSize/22,4*ySize/7,6*xSize/22,ySize/14);//set the bounds for the Exponents button
        add(Exponents);//add the Exponents button
        Exponents.addActionListener(this);//add an action listener to the Exponents button
        Equations = new JButton("Equations");//instantiate Equations button
        Equations.setBounds(8*xSize/22,4*ySize/7,6*xSize/22,ySize/14);//set the bounds for the Equations button
        Equations.addActionListener(this);//add an action listener to the Equations button
        add(Equations);//add the Equations button
        AbsoluteValues = new JButton("Absolute Values");//instantiate AbsoluteValues button
        AbsoluteValues.setBounds(15*xSize/22,4*ySize/7,6*xSize/22,ySize/14);//set the bounds for the AbsoluteValues button
        add(AbsoluteValues);//add the AbsoluteValues button
        AbsoluteValues.addActionListener(this);//add an action listener to the AbsoluteValues button
        Trigonometry = new JButton("Trigonometry");//instantiate Trigonometry button
        Trigonometry.setBounds(xSize/22,5*ySize/7,6*xSize/22,ySize/14);//set the bounds for the Trigonometry button
        add(Trigonometry);//add the Trigonometry button
        Trigonometry.addActionListener(this);//add an action listener to the Trigonometry button
        Logarithms = new JButton("Logarithms");//instantiate Logarithms button
        Logarithms.setBounds(8*xSize/22,5*ySize/7,6*xSize/22,ySize/14);//set the bounds for the Logarithms button
        add(Logarithms);//add the Logarithms button
        Logarithms.addActionListener(this);//add an action listener to the Logarithms button
        back = new JButton("Back");
        back.setBounds(15*xSize/22,5*ySize/7,6*xSize/22,ySize/14);
        add(back);
        back.addActionListener(this);

        title = new JLabel("Problem Select");//instantiate title label
        title.setBounds(3*xSize/10,ySize/20,2*xSize/5,ySize/8);//set the bounds for the title label
        title.setFont(titleFont);
        add(title);//add the label label

        cards2 = cards;//save the JPanel passed from Paneltoholdpanel to this JPanel
        this.pa = pa;
    }
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == Exponents)
            type = "Exponents";
        if(evt.getSource() == Equations)
            type = "Equations";
        if(evt.getSource() == AbsoluteValues)
            type = "AbsoluteValues";
        if(evt.getSource() == Trigonometry)
            type = "Trigonometry";
        if(evt.getSource() == Logarithms)
            type = "Logarithms";
        if(evt.getSource() == back){
            ((CardLayout)cards2.getLayout() ).first(cards2);
        }
        else{
            ((CardLayout)cards2.getLayout() ).show(cards2,"Third");//use card layout to go back to the first page
        }
        pa.qFile = type+".txt";
        pa.aFile = type+"A.txt";
    }
}
class Gamepanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener{//class header
    int change = 0;//declare all variables
    int x;
    int y;
    int x1;
    int y1;
    JPanel cards2;
    JButton back;
    JLabel answer11;
    JLabel answer12;
    JLabel answer13;
    JLabel problem;
    JLabel type;
    boolean mouseInside;         				                        //declare booleans for mouse animations
    boolean pew;
    int targetX;                                 				        //declare ints for tracing mouse location
    int targetY;
    ProblemsAndAnswers pa;                 				                //an instance of class ProblemsAndAnswers
    GameData gd;                                                        //an instance of class GameData
    Levelselect ls;
    Font displayFont;				                                    //declare a font variable
    Font scoreFont;				                                    	//declare a font variable
    Font actionFont;				                                    //declare a font variable
    Font bigFont;				                                        //declare a font variable
    boolean correct;                                                    //declare a boolean called correct
    static boolean clicked;                                             //declare a boolean called clicked
    int score;                                                          //declare an int for score
    static boolean nextWanted;                                                 //declare a boolean called nextWanted
    JButton next;                                                       //declare a JButton called next
    int xSize;
    int ySize;
    JTextField answer11Color;
    JTextField answer12Color;
    JTextField answer13Color;


    public Gamepanel(JPanel cards, ProblemsAndAnswers pa,int xSize,int ySize,Levelselect ls){//constructor header
        setLayout(null);

        this.xSize = xSize;
        this.ySize = ySize;
        this.pa = pa;
        this.ls = ls;
        type = new JLabel("asdf");
        type.setBounds(xSize/100,ySize/200,xSize/5,ySize/10);

        answer11 = new JLabel("YOUR ANSWER HERE");//instantiate the answer label
        answer11.setBounds(255,100,100,50);//set the bounds for the answer
        add(answer11);//add the label
        answer12 = new JLabel("YOUR ANSWER HERE");//instantiate the answer label
        answer12.setBounds(655,200,100,50);//set the bounds for the answer
        add(answer12);//add the label
        answer13 = new JLabel("YOUR ANSWER HERE");//instantiate the answer label
        answer13.setBounds(455,500,100,50);//set the bounds for the answer
        add(answer13);//add the label
        problem = new JLabel("YOUR PROBLEM HERE");//instantiate the problem label
        problem.setBounds(3*xSize/8,17*ySize/20,xSize/3,ySize/20);//set the bounds for the problem
        add(problem);//add the label

        addMouseListener(this);//add the mouse listener to the class
        cards2 = cards;//declare the JPanel as the panel sent as an argument
        addMouseMotionListener(this);
        pew = false;                                    				//instantiate mouse booleans
        mouseInside = false;
        targetX = xSize/2;     				                                //instantiate mouse ints
        targetY = ySize/2;
        gd = new GameData(pa);                                            //instantiate the instance of GameData
        correct = false;                                                //set booleans correct and clicked to false
        clicked = false;

        next = new JButton("Next Problem");                             //instantiate JButton next
        next.setBounds(9*xSize/20,7*ySize/40,xSize/10,ySize/15);
        next.addActionListener(this);
        add(next);
        next.setVisible(false);                                         //set visibility of next to false

        nextWanted = true;                                              //set nextWanted to true
        score = 0;                                                      //set score to 0
        addMouseListener(this);                         				//add Mouse and MouseMotion Listener
        addMouseMotionListener(this);
        displayFont = new Font("Helvetica",Font.PLAIN,32);				//instantiate font
        scoreFont = new Font("Courrier",Font.BOLD,40);					//instantiate font
        actionFont = new Font("Arial",Font.ITALIC,12);					//instantiate font
        bigFont = new Font("Copperplate Gothic",Font.PLAIN,56);    //instantiate font;

        answer11Color = new JTextField();
        answer11Color.setBounds(2*xSize/7,ySize/2,xSize/10,ySize/100);
        answer11Color.setVisible(false);
        add(answer11Color);
        answer12Color = new JTextField();
        answer12Color.setBounds(3*xSize/7,ySize/2,xSize/10,ySize/100);
        answer12Color.setVisible(false);
        add(answer12Color);
        answer13Color = new JTextField();
        answer13Color.setBounds(4*xSize/7,ySize/2,xSize/10,ySize/100);
        answer13Color.setVisible(false);
        add(answer13Color);
    }
    public void addStuff(){
        setLayout(null);                                				//null layout
        answer11.setText(gd.answer1);
        answer11.setFont(displayFont);//set the font for the answer
        answer12.setText(gd.answer2);
        answer12.setFont(displayFont);//set the font for the answer
        answer13.setText(gd.answer3);
        answer13.setFont(displayFont);//set the font for the answer
        problem.setText(gd.question);
        problem.setFont(displayFont);//set the font for the problem
    }

    public void paintComponent(Graphics g){//paint component method header
        Color top = new Color(149,93,53);
        Color side = new Color(98,63,32);
        Color bottom = new Color(122,39,39);
        Color back = new Color(86,36,5);
        Color background = new Color(45,25,1);

        x = xSize/4-(change*xSize/500);//save the top left x value as y
        y = ySize/5-(change*ySize/621);//save the top left y value as y
        x1 = xSize/2+(change*xSize/250);//save the top right x value as x
        y1 = 2*ySize/5+(change*ySize/220);//save the bottom right y value as y

        int[] topX = {xSize/100,99*xSize/100,x+x1,x};
        int[] topY = {ySize/100,ySize/100,y,y};
        int[] botX = {xSize/100,99*xSize/100,x+x1,x};
        int[] botY = {94*ySize/100,94*ySize/100,y+y1,y+y1};
        int[] lSideX = {xSize/100,x,x,xSize/100};
        int[] lSideY = {ySize/100,y,y+y1,94*ySize/100};
        int[] rSideX = {99*xSize/100,x+x1,x+x1,99*xSize/100};
        int[] rSideY = {ySize/100,y,y+y1,94*ySize/100};

        setLayout(null);//set the layout to null
        setBackground(background);
        super.paintComponent(g);//call paint component
        PrimeThread pt = new PrimeThread();
        pt.start();
        answer11.setBounds(2 * xSize / 7 - (change * xSize / 500), 2 * ySize / 5 - (change * ySize / 500), 3 * xSize / 10, ySize / 10);//change the bounds of the JLabel
        answer12.setBounds(3*xSize/7+(change*xSize/440),2*ySize/5-(change*ySize/300),3*xSize/10,ySize/10);//change the bounds of the JLabel
        answer13.setBounds(4*xSize/7+(change*xSize/700),2*ySize/5+(change*ySize/300),3*xSize/10,ySize/10);//change the bounds of the JLabel

        if(2*xSize/4-(change*xSize/500)<=xSize/100||xSize/2+(change*xSize/250)>=1000){
            change = 0;
            nextWanted = true;
            score-=2;
            if(score<0){
                score = 0;
            }
        }
        try{
            pt.sleep(50);
            change++;
            repaint();
        }catch(InterruptedException e){
            System.out.println("JK The thread doesnt work");
            System.exit(1);
        }
        type.setText(ls.type);
        type.setFont(displayFont);
        add(type);

        String drawScore = " " + score + " ";                           //concatenate int score to string drawScore
        super.paintComponent(g);

        if(nextWanted){                                                 //if boolean nextWanted is true, call addProblem(), in class GameData
            gd.addProblem();
            nextWanted = false;                                         //change nextWanted to false
            answer11Color.setVisible(false);
            answer12Color.setVisible(false);
            answer13Color.setVisible(false);
        }

        g.setColor(back);
        g.fillRect(x, y, x1, y1);
        g.setColor(Color.GRAY);
        g.fillRect(22 * xSize / 50 - (change * xSize / 1000), 3 * ySize / 10 - (change * ySize / 621), 3 * xSize / 25 + (change * xSize / 500), 3 * ySize / 10 + (change * ySize / 220));
        g.setColor(Color.ORANGE);
        g.fillOval(27*xSize/50+(change*xSize/1200),9*ySize/20+(change*ySize/660),xSize/100+change*xSize/20000,ySize/100+(change*ySize/6600));
        g.setColor(top);
        g.fillPolygon(topX,topY,4);
        g.setColor(side);
        g.fillPolygon(lSideX,lSideY,4);
        g.fillPolygon(rSideX,rSideY,4);
        g.setColor(bottom);
        g.fillPolygon(botX,botY,4);

        Image bat;
        bat = Toolkit.getDefaultToolkit().getImage("bat.png");
        g.drawImage(bat,2*xSize/7-(change*xSize/500),2*ySize/5-(change*ySize/500),xSize/15,ySize/40,this);
        g.drawImage(bat,3*xSize/7+(change*xSize/440),2*ySize/5-(change*ySize/300),xSize/15,ySize/40,this);
        g.drawImage(bat,4*xSize/7+(change*xSize/700),2*ySize/5+(change*ySize/300),xSize/15,ySize/40,this);

        if(gd.question == null){                                        //if question in class GameData is null
            drawFinished(g);                                            //draw end screen
            GameOver.getScore(score);
            ((CardLayout)cards2.getLayout()).show(cards2,"Five");//change the card back to the first one
        }
        else{
            addStuff();                                                //call graphics method addStuff() to draw questions on screen
            g.setColor(Color.BLUE);                                     //set font and color, then draw Score on screen
            g.setFont(scoreFont);
            g.drawString(drawScore, 900, 100);
            if(mouseInside && !clicked){                                //if statement for drawing target on cursor
                g.setColor(Color.RED);
                g.drawOval(targetX - 20, targetY - 20, 40, 40);
            }
            if(pew && clicked){
                g.setColor(Color.RED);                                  //if statement for drawing "sound  effect"
                g.setFont(actionFont);
                g.drawString("Pew Pew", targetX - 30, targetY - 30);
                pew = false;
            }

            if(correct && clicked){                                     //if boolean correct is true, print correct on screen
                g.setColor(Color.GREEN);
                g.setFont(displayFont);
                g.drawString("Correct", 450, 100);
            }
            else if(!correct && clicked){                               //else print incorrect on screen
                g.setColor(Color.RED);
                g.setFont(displayFont);
                g.drawString("Incorrect", 450, 100);
                if(gd.correctAnswer==1){
                    answer11Color.setBackground(Color.GREEN);
                    answer12Color.setBackground(Color.RED);
                    answer13Color.setBackground(Color.RED);
                }
                else if(gd.correctAnswer==2){
                    answer12Color.setBackground(Color.GREEN);
                    answer13Color.setBackground(Color.RED);
                    answer11Color.setBackground(Color.RED);
                }
                else if(gd.correctAnswer==3){
                    answer13Color.setBackground(Color.GREEN);
                    answer11Color.setBackground(Color.RED);
                    answer12Color.setBackground(Color.RED);
                }
                answer11Color.setVisible(true);
                answer12Color.setVisible(true);
                answer13Color.setVisible(true);
            }
            if(clicked){                                                //if clicked is true, set visibility of next and solution to true
                next.setVisible(true);
                change = 0;
            }
            else{                                                       //if clicked is false, set visibility of next and solution to false
                next.setVisible(false);
            }
        }


    }
    public void drawFinished(Graphics g){                               //method drawFinished() prints out the end screen
        next.setVisible(false);
    }

    //MouseMotionListener methods overridden
    public void mouseMoved(MouseEvent e){               				//mouseMoved() on move save x and y of cursor and repaint
        if(mouseInside){		                                		//only function if mouse is inside panel
            targetX = e.getX();                                         //save mouse x and y, call repaint()
            targetY = e.getY();
            repaint();
        }
    }
    public void mouseDragged(MouseEvent e){
    }//mouse method- not used
    //MouseListener methods overridden
    public void mouseEntered(MouseEvent e){
        mouseInside = true;
        repaint();
    }//mouse method- not used
    public void mouseExited(MouseEvent e){
        mouseInside = false;

    }//mouse method- not used
    public void mouseClicked(MouseEvent e){
        int answerX = 0;                                                //declare the x and y values of the answers
        int answerY = 250;                                              //instantiate answerY to 250
        pew = true;                                                     //set pew to true

        if(gd.correctAnswer==1){                                        //set X coordinate or correct answer based on value of correctAnswer from class GameData
            answerX = 2*xSize/7-(change*xSize/500);
            answerY = 2*ySize/5-(change*ySize/500);
        }
        else if(gd.correctAnswer==2){
            answerX = 3*xSize/7+(change*xSize/440);
            answerY = 2*ySize/5-(change*ySize/300);
        }
        else if(gd.correctAnswer==3){
            answerX = 4*xSize/7+(change*xSize/700);
            answerY = 2*ySize/5+(change*ySize/300);
        }
        if(!clicked) {                                                  //if clicked is false, check for y-coordinate of click
            if((e.getY() >= answerY) && (e.getY() <= (answerY + ySize/10))){ //if y-coordinates are correct, check x-coordinates
                if((e.getX() >= answerX) && (e.getX() <= (answerX + 3*xSize/10))){
                    score += 10;                                        //if x coordinates are correct, increment score, set correct to true
                    correct = true;
                }
                else{                                                   //else x incorrect decrement score,correct is false
                    score -= 5;
                    correct = false;
                }
            }
            else{                                                       //else y incorrect decrement score,correct is false
                score -= 5;
                correct = false;
            }
            clicked = true;                                             //set clicked to true
        }
        if(score<0){                                                    //set score to 0, if less than 0
            score = 0;
        }
        repaint();
    }//mouse method- not used
    public void mousePressed(MouseEvent e){}//mouse method- not used
    public void mouseReleased(MouseEvent e){}//mouse method- not used
    //ActionListener methods overridden
    public void actionPerformed(ActionEvent evt){//action performed method
        if(evt.getSource()==next){                                        //check to see if next is clicked
            nextWanted = true;                                          //if so, set nextWanted to true, clicked to false
            clicked = false;
            repaint();
        }
    }
}
class PrimeThread extends Thread{
    public void run(){

    }
}
class GameOver extends JPanel implements ActionListener{
    JButton backbutton;
    JPanel cards2;
    Font displayFont;				                                    //declare a font variable
    Font titleFont;				                                    //declare a font variable
    static int score2;
    ProblemsAndAnswers pa;
    int xSize;
    int ySize;
    Gamepanel gp;
    public GameOver(JPanel cards,ProblemsAndAnswers pa,int xSize,int ySize){
        this.pa = pa;
        this.xSize = xSize;
        this.ySize = ySize;

        setLayout(null);
        backbutton = new JButton("Back");
        backbutton.setBounds(9*xSize/20,6*ySize/10,xSize/10,ySize/20);
        backbutton.addActionListener(this);
        add(backbutton);
        displayFont = new Font("Helvetica",Font.PLAIN,32);				//instantiate font
        titleFont = new Font("Times New Roman",Font.BOLD,64);//declare the fonts as size 60 Times New Roman
        cards2 = cards;
    }
    public static void getScore(int score){
        score2 = score;
    }
    public void paintComponent(Graphics g){
        setBackground(Color.GREEN);
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Level Complete!",3*xSize/10,3*ySize/20);
        g.setFont(displayFont);
        g.drawString("Congratulations, you finished the level!",xSize/4,4*ySize/10);
        g.drawString("Your Score was: " + score2,3*xSize/8,11*ySize/20);               //print score in end screen
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==backbutton) {
            ((CardLayout) cards2.getLayout()).show(cards2, "Fourth");//use card layout to go back to the first page
            pa.used = "~";
            pa.lineNumber = 0;
            resetVariables();
        }
    }
    public void resetVariables(){
        Gamepanel.nextWanted = true;
        Gamepanel.clicked = false;
    }
}
class GameData {                                                        //class GameData saves the values of each answer and question so it can be printed

    int whichAnswer;                 				                    //declare an int to set random location of answer
    int correctAnswer;                                                  //declare an int to keep track of the correct answer
    String question;                                    				//declare Strings that will be filled with question/answers
    String answer1;
    String answer2;
    String answer3;
    ProblemsAndAnswers pa;                 				                //an instance of class ProblemsAndAnswers

    GameData(ProblemsAndAnswers pa){                                    //constructor
        this.pa = pa;                                                   //instantiate instance of class
        correctAnswer = 1;                                              //set correct answer to 1
    }
    public void setAnswers(){                           				//setAnswers() decides which String will be the right answer and which ones will be random
        String [] answers = new String[3];                              //create an int array with length 3
        whichAnswer = (int)(Math.random()*3+1);         				//randomly generate an integer between 1 and 3
        answers[whichAnswer-1] = pa.pickAnswer();                       //call the pickAnswer to fill random spot in array with correct answer
        for(int x = 0;x<answers.length;x++){                            //use a for loop to fill the other 2 spots in the array
            if(x!=whichAnswer-1){                                       //don't fill the spot in the array with the correct answer
                answers[x] = pa.randomAnswers();
            }
        }
        correctAnswer = whichAnswer;                                    //set correctAnswer to the random number
        answer1 = answers[0];                                           //instantiate answer1 - answer3
        answer2 = answers[1];
        answer3 = answers[2];
    }

    public void addProblem() {                                          //method addProblem() gets question, calls set answers
        question = pa.pickProblem();                                    //call pickProblem() in ProblemsAndAnswers to get question and save
        if(pa.lineNumber > -1) {                                        //make sure question isn't null before calling setAnswers()
            setAnswers();
        }
    }
}
class ProblemsAndAnswers{/*class ProblemsAndAnswers randomly picks a problem and answer
and returns it to GamePanel. Also responsible for returning 2 random answers to GamePanel*/

    Scanner problems;                                   				//declare Scanners
    Scanner answers;
    int lineNumber;      				                                //declare int for randomization
    String selectedP;                    				                //declare String for problem
    String selectedA;                                   				//declare String for answer
    String used;                                        				//declare String for used problems
    int randomLine;                                     				//declare int for 2nd randomization
    int timesRun;
    String qFile;
    String aFile;

    public ProblemsAndAnswers(){                            			//ProblemsAndAnswers() constructor
        lineNumber = 0;                                 				//instantiate all previously declared global variables
        selectedA = "";
        selectedP = "";
        used = "~";
        randomLine = 1;
        timesRun = 0;
        qFile = "Exponents.txt";
        aFile = "ExponentsA.txt";
    }

    public void generateNumber(){            				            //generateNumber() creates a random number
        lineNumber = (int)(Math.random()*15+1);
        if(!used.contains("~"+lineNumber+"~")){             				//use .contains() to check if number has already been used
            used = used + lineNumber + "~~";            				//if not add used number to String for storage/elimination
        }
        else{               				                            //else call itself to run again
            if(used.length()<50) {                                      //make sure not all questions have been used
                generateNumber();
            }
            else {                                                      //if all have been used, set lineNumber to -1
                lineNumber = -1;
            }
        }
    }

    public void instantiateScanners(){      				            //instantiateScanners() instantiates a scanner for the 2 txt files used
        try{                                            				//try catch block for instantiation
            problems = new Scanner(new File(qFile));			//actual instantiation of 2 Scanners
            answers = new Scanner(new File(aFile));
        }
        catch(IOException f){                           				//catch file IO Exceptions for not found
            System.out.println(aFile+qFile);
            System.out.println("Change the File Name, Rishi!");			//notify user if file not found
            System.exit(1);                             				//exit
        }
    }

    public String pickProblem(){//pickProblem() picks a the problem based on random number chosen by generateNumber()
        instantiateScanners();                           				//call instantiateScanners() to instantiate Scanners
        generateNumber();                                               //call generateNumbers()
        if (lineNumber == -1) {                                         //if lineNumber is -1, return null
            return null;
        }
        int storage = lineNumber;                                       //set int storage to lineNumber
        while(storage!=0){                           					//decrement storage without affecting lineNumber
            selectedP = problems.nextLine();            				//set problem to next line
            storage--;             				 	             	    //decrease storage
        }
        answers.close();
        problems.close();                				                //close Scanners
        return selectedP;                                               //return Problem
    }

    public String pickAnswer(){/*pickProblem() picks a the problem and corresponding answer
    based on random number chosen by generateNumber() receives a boolean so it knows to return answer or question*/

        instantiateScanners();                           				//call instantiateScanners() to instantiate Scanners
        int answerLine = (lineNumber-1)*4+2;                            //set answerLine to a function of lineNumber

        while(answerLine!=0){                                           //decrement answerLine to find answer(won't affect line Number)
            selectedA = answers.nextLine();                             //set answer to nextLine
            answerLine--;                                               //decrement answerLine()
        }
        answers.close();
        problems.close();                                               //close Scanners
        selectedA = selectedA.substring(2);                             //take a substring to remove "a)"
        return selectedA;                                               //return Answer
    }

    public String randomAnswers(){          				            //randomAnswers() picks a random answer that isn't the correct one
        instantiateScanners();                          				//call instantiateScanners() to instantiate Scanners
        randomLine = (lineNumber-1)*4;			          				//pick randomNumber between 1 and 4 and save as randomLine
        String rReturned = "hi";                        				//declare a String called rReturned to return
        if(timesRun == 1){                                              //if 2nd points running, return "c)"
            randomLine = randomLine+4;
            timesRun--;                                                 //set timesRun to 0 (1st points)
        }
        else{                                                           //if 1st points running, return "b)"
            randomLine = randomLine+3;
            timesRun++;                                                 //set timesRun to 1 (2nd points)
        }

        while(randomLine!=0){                                           //while randomLine is not 0
            rReturned = answers.nextLine();                             //set the return String to nextLine()
            randomLine--;                                               //decrement randomLine (random number)
        }
        problems.close();
        answers.close();                                                //close Scanners
        rReturned = rReturned.substring(2);                             //take substring to remove "b)" or "c)"
        return rReturned;                                               //return rReturned
    }
}
