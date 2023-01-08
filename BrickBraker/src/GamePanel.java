import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener,ActionListener {
    static final int WIDTH = 700;
    static final int HEIGHT = 600;
    static final Dimension SCREEN_SIZE =new Dimension(WIDTH,HEIGHT);
    private boolean play =false;
    private int score =0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int player1 = (WIDTH/2)-100;

    private int ballDirX = -1;
    private int ballDirY = -2;
    private int paddleWidth = 100;
    private int paddleHeight = 5;
    private int ballHeight = 15;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private Map map;

    GamePanel() {
        map = new Map(3,7);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        timer= new Timer(delay,this);
        timer.start();
    }


    public void paint(Graphics g) {
//        background
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
//        map

        map.draw((Graphics2D)g);

//        paddle
        g.setColor(Color.blue);
        g.fillRect(player1,590,paddleWidth,paddleHeight);

// ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX,ballPosY,ballHeight,ballHeight);
        g.dispose();
//        redo
        if(ballPosY > 600){
            if(play){
                System.out.println("plsy is still on");
                play =false;
                ballDirY =0;
                ballDirX =0;
                g.setColor(Color.white);
                int GAME_WIDTH =400;
                int GAME_HEIGHT =400;
                g.setFont(new Font("Consolas",Font.PLAIN,30));
                g.drawString("Enter to restart",100,300);


            }


        }


    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(player1>=WIDTH){
                player1 =WIDTH;
            }else {
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(player1<10){
                player1 =10;
            }else {
                moveLeft();
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play =true;
                ballPosX =120;
                ballPosY=350;
                ballDirY =-2;
                ballDirX =-1;
                player1 = 310;
                score =0;
                totalBricks =21;
                map = new Map(3,7);
                repaint();
            }

        }
    }

    private void moveLeft() {
        play =true;
        player1 -=20;
    }

    private void moveRight() {
        play =true;
        player1 +=20;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(player1,HEIGHT,WIDTH,5))){
                ballDirY = -ballDirY;
            }
            A:for(int i = 0;i< map.map.length;i++){
                for(int j = 0;j< map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brX = j*map.brickWidth+80;
                        int brY = i*map.brickWidth+50;
                        int brH = map.brickHeight;
                        int brW = map.brickWidth;

                        Rectangle rect = new Rectangle(brX,brY,brW,brH);
                        Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brRect = rect;

                        if(ballRect.intersects(brRect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score +=5;
                            if(ballPosX+19 <=brRect.x||ballPosX+1 >=brRect.x+brRect.width){
                                ballDirX = -ballDirX;
                            }else{
                                ballDirY = -ballDirY;
                            }
                            break A;
                        }

                    }

                }

            }



            System.out.println(ballPosY);

            ballPosX += ballDirX;
            ballPosY += ballDirY;
            if(ballPosX < 0){
                ballDirX= -ballDirX;
            }
            if(ballPosY < 0){
                ballDirY= -ballDirY;
                System.out.println("stuf");
            }
            if(ballPosY > 550){
//                ballDirY= -ballDirY;
//                System.out.println("stuf 2");
                new BreakerGame();
            }
            if(ballPosX > (HEIGHT-ballHeight)){
                ballDirX= -ballDirX;
            }
//            if(ballPosX > (HEIGHT-ballHeight)){
//                ballDirX= -ballDirX;
//            }
        }

        repaint();

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}


}
