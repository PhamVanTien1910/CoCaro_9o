package LamCoCaro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Main {
	
	private static int sec = 0;
	 private static Timer  timer  =  new Timer();
	 private static JLabel jLabel_Time = new JLabel();
	 private static Board board = new Board();
	 private  static JButton jButton_Start  = new JButton();
    public static void main(String[] args) {
//    	Timer timer  =  new Timer();
//    	timer.scheduleAtFixedRate(new TimerTask() {
//			
//			@Override
//			public void run() {
//		sec++;
//		System.out.println(sec /60 + ":" + sec % 60);
//				
//			}
//		}, 1000, 1000);
//    	Board board ;
//    int choice =	 JOptionPane.showConfirmDialog(null, "Chọn O trước đúng ko", "Ai đi trước", JOptionPane.YES_NO_OPTION);
//    
   //board = (choice == 0)? new Board(Cell.O_Value) : new Board(Cell.X_Value);
    	 board = new Board();
    	 board.setEndGameListenner(new EndGameListenner() {
			
			@Override
			public void end(String player, int it) {
				// TODO Auto-generated method stub
				if (it  == board.ST_Win) {
					//System.err.println("Nguoi choi"+ curenValue +" thang");
					JOptionPane.showMessageDialog(null, "Nguoi choi " + player +" thang");
					stopGame();
				}else if(it == board.ST_Draw) {
					//System.out.println("Hoa roi");
					JOptionPane.showMessageDialog(null,"Hoa roi");
					stopGame();
				}
				
			}
		});
  //  System.out.println(choice);
    	JPanel jPanel = new JPanel();
    	
    	BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
    	jPanel.setLayout(boxLayout);
    	
    	
    	board.setPreferredSize(new Dimension(300,300));
    	
    	FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
    	JPanel jbuttonpanel = new JPanel();
    	jbuttonpanel.setLayout(flowLayout);
//    	jbuttonpanel.setPreferredSize(new Dimension(300,50));
    	jbuttonpanel.setBackground(Color.WHITE);
    	jPanel.add(board);
    	jPanel.add(jbuttonpanel);
    	
    	 jButton_Start = new JButton("Start");
         jLabel_Time = new JLabel("00:00");
    	
    	jbuttonpanel.add(jLabel_Time);
    	jbuttonpanel.add(jButton_Start);
    	
    	jButton_Start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jButton_Start.getText().equals("Start")) {
				    StartGame();
				}else {
					stopGame();
				}
			
			}
		});
    	
    	
		JFrame jFrame = new JFrame();
		 jFrame.setTitle(" Game cờ Caro 9 ô");
		jFrame.setSize(400, 500);
		 jFrame.setLocationRelativeTo(null);
		 jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jFrame.setResizable(true);
		 jFrame.add(jPanel);
		 
		 
		  
		  
		 jFrame.setVisible(true);
    }
    public static void StartGame () {
    	// hỏi ai đi trước
    	int choice =	 JOptionPane.showConfirmDialog(null, "Chọn O trước đúng ko", "Ai đi trước", JOptionPane.YES_NO_OPTION);
    String curenValue = (choice == 0) ? Cell.O_Value : Cell.X_Value;
    board.Resest();
     board.setCurenValue(curenValue);
     
		// Đếm ngược
     
        sec = 0;
		jLabel_Time.setText("00:00");
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
		sec++;
		String value = sec /60 + ":" + sec % 60;
		jLabel_Time.setText(value);
			}
		}, 1000, 1000);
		
		jButton_Start.setText("Stop");
    }
    private static void stopGame() {
    	jButton_Start.setText("Start");
    	 sec = 0;
 		jLabel_Time.setText("00:00");
 		timer.cancel();
 		timer = new Timer();
 	   board.Resest();
 
    }
}
