package watchurstaps;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
public class bon extends JButton {

    private static int size = 50;
    private int row=0;
    private int col=0;
    private int nextToHole=0; //maximum 4 
    private boolean hole=false;
    private boolean revealed=false;

    public bon(int rowc,int colc){
    row = rowc;
    col = colc;
    setPreferredSize(new Dimension(size,size));
    
    }

public int getRow(){
    return row;
}
public int getCol(){
    return col;
}
public boolean hasHole(){
    return hole;
}
public boolean hasRevealed(){
    return revealed;
}
public void setHole(boolean value){
    hole = value;
}
public void increasHole(){
    nextToHole++;
}
public boolean isNextToHole(){
    return nextToHole>0;
}

public void reveal(boolean value){
    revealed = value;
    if(revealed){
        if(hasHole())
            setBackground(Color.BLACK);
        else{
            setBackground(Color.CYAN);
            if(isNextToHole())
                setText(""+nextToHole);
        }
    }
    else{
        setBackground(null);
        setText("");
    }
  }

public void reset(){
 nextToHole=0; //maximum 4 
 hole=false;
 revealed=false;
 setBackground(null);
        setText("");
}
}
