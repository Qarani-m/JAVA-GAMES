import java.awt.*;
import java.awt.event.*;
public class Paddle extends Rectangle {
    int id;
    int yVelocity;
//    int speed =10;

    Paddle(int x,int y,int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;

    }
    public void keyPressed(KeyEvent e){
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-10);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(10);
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-10);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(10);
                    move();
                }
            }
        }

    }
    public void keyReleased(KeyEvent e){
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
            }
        }

    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
        y=y+yVelocity;

    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.blue);
            g.fillRect(x,y,width,height);


        }else{
            g.setColor(Color.red);
            g.fillRect(x,y,width,height);

        }
//        g.fillRect(x,y,width,height);


    }
}
