package watchurstaps;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WatchUrStaps extends JFrame implements ActionListener{
    private static int gridSize=10;
    private static int NUMBEROFHOLE=10;
    private bon[][] button = new bon[gridSize][gridSize];
    private static int totalReveald=0;
    private JMenuBar s;
    private JMenu myMenu,d;
    private JMenu myMenu1;
    int level=10;
    private JMenuItem a , b , c ;
    public WatchUrStaps(){
    s = new JMenuBar();
    myMenu = new JMenu("Setting");
    d = new JMenu("status :");
    myMenu1 = new JMenu("Incread Defficulty");
    myMenu1.add(a=new JMenuItem("low"));
    myMenu1.add(b=new JMenuItem("medium"));
    myMenu1.add(c=new JMenuItem("hard"));
    myMenu.add(myMenu1);
    s.add(d);
    s.add(myMenu);
    setJMenuBar(s);
    a.addActionListener(this);
    b.addActionListener(this);
    c.addActionListener(this);
    intGUI();
    setHole();
    pack();
    setLocationRelativeTo(null);
    }
    private void intGUI(){
    a.setBackground(Color.DARK_GRAY);
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(10,10));
    add(center,BorderLayout.CENTER);
    
    for(int i=0;i<gridSize;i++)
        for(int j=0;j<gridSize;j++)
        {    
            button[i][j]= new bon(i,j);
            center.add(button[i][j]);
            button[i][j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    bon s = (bon)e.getSource();
                    check(s,true);
                    gameOver();
                }
            });
            
        }
    setTitle("wach your stapes");
    setDefaultCloseOperation(3);
    setResizable(false);
    setVisible(true);
    
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(WatchUrStaps::new);
    }


    private void setHole() {
    NUMBEROFHOLE=level;        
    Random rand = new Random();
    for(int i=0;i<NUMBEROFHOLE;i++){
        int randRow = rand.nextInt(10);
        int randCol = rand.nextInt(10);
        while(button[randRow][randCol].hasHole()){
         randRow = rand.nextInt(10);
         randCol = rand.nextInt(10);
        }
        button[randRow][randCol].setHole(true);
        if(randRow!=0&&randCol!=0&&randCol!=9&&randRow!=9){
            button[randRow-1][randCol].increasHole();
            button[randRow+1][randCol].increasHole();
            button[randRow][randCol-1].increasHole();
            button[randRow][randCol+1].increasHole();
       }
        else{
            int x=0,x1=0,y1=0,y=0;
            if(randRow==0)
                x1=randRow+1;
            else if(randRow==9)
                x1=randRow-1;
            else{
                x=randRow+1;
                x1=randRow-1;
            }
            if(randCol==0)
                y1=randCol+1;
            else if(randCol==9)
                y1=randCol-1;
            else{
                y=randCol+1;
                y1=randCol-1;
            }
            if(x!=0){
            button[x][randCol].increasHole();
            button[x1][randCol].increasHole();
            button[randRow][y1].increasHole();
            }
            else if(y!=0){
            button[randRow][y].increasHole();
            button[randRow][y1].increasHole();
            button[x1][randCol].increasHole();
            }
            else{
            button[x1][randCol].increasHole();
            button[randRow][y1].increasHole();
            }
        }
        
    }
    
    }
    private void check(bon e,boolean tr){
if(!e.hasRevealed()){
        if(e.hasHole()&&tr){
        {  for(int i=0;i<10;i++)
               for(int j=0;j<10;j++)
                   button[i][j].reveal(true);
            }
         if(0==JOptionPane.showConfirmDialog(this, "Congra u hvae Lost\nDo u want to continiue ", "looser window", JOptionPane.ERROR_MESSAGE))
         restart();
     else
         System.exit(0);
        }
        else if(e.isNextToHole())
        {
            e.reveal(true);
        }
        else {         
            empty(e.getRow(),e.getCol());
        }
    }
}
    private void empty(int x ,int y) {
     if(x<0||x>9||y<0||y>9)return;
     if(button[x][y].hasRevealed())return;
     button[x][y].reveal(true);
     if(button[x][y].isNextToHole())return;
     empty(x-1,y);
     empty(x+1,y);
     empty(x,y-1);
     empty(x,y+1);
 }
   
   private void gameOver(){
     int x = 0;
     for(int i=0;i<10;i++)
     for(int j=0;j<10;j++)
         if(button[i][j].hasRevealed())
             x++;
     if(x==100-level)
     {x=JOptionPane.showConfirmDialog(this, "Congra u hvae Wone\nDo u want to continiue ", "winner", JOptionPane.ERROR_MESSAGE);
     if(x==0)
         restart();
     else
         System.exit(0);
   }
 }
private void restart(){
     for(int i=0;i<10;i++)
     for(int j=0;j<10;j++)
         button[i][j].reset();
    setHole();
}    

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem ss = (JMenuItem)e.getSource();
        
        if(e.getActionCommand().startsWith("low")&&level!=10)
            level = 10;
        else if(e.getActionCommand().startsWith("med")&&level!=15)
            level = 15;
        else if(e.getActionCommand().startsWith("har")&&level!=20)
            level = 20;
        else return;
        a.setBackground(null);
        b.setBackground(null);
        c.setBackground(null);
        ss.setBackground(Color.DARK_GRAY);
        restart();
        
        
    }
    
    
    
    
}
