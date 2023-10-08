package LamCoCaro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class Board extends JPanel {
	private static final int N = 3;
	private static final int M = 3;
    public static final int ST_Draw = 0;
    public static final int ST_Win = 1;
    public static final int ST_Nomal = 2;
    private EndGameListenner endGameListenner;
	private Image imgX;
	private Image imgO;
	private Cell[][] matran= new Cell[N][M];
	private String curenValue = Cell.EMTY_value;
  public Board (String player) {
	  this();
	  this.curenValue = player;
  }
	public Board ()  {
		// Khởi tạo matran
		
	this.initMatran();
		
		addMouseListener(new MouseAdapter() {@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
			int X = e.getX();
			int Y = e.getY();
			 if (curenValue.equals(Cell.EMTY_value)) {
				return;
			}
			// phát ra âm thamh
			soundClick();
			// Tính toán xem x, y rơi vào đoạn nào trong Board sau đó vẽ hình x o tùy ý
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					Cell cell = matran[i][j];
					int cXStart = cell.getX();
					int cYStart = cell.getY();
					
					int cXEnd = cXStart + cell.getW();
					int cYEnd = cYStart + cell.getH();
					if (X >= cXStart && X <= cXEnd  && Y >= cYStart && Y<=cYEnd) {
						
						if (cell.getValue().equals(Cell.EMTY_value)) {
							cell.setValue(curenValue);
							int result  = Check(curenValue);
							if (endGameListenner!= null) {
								endGameListenner.end(curenValue, result);
							}
							if (result == ST_Nomal) {
								curenValue = curenValue.equals(Cell.O_Value) ? Cell.X_Value : Cell.O_Value;
							}
							
							
							repaint();
						}
						
					}
					
				}
		}}});
		try {
			imgX = ImageIO.read(getClass().getResource("Xcaro.jpg"));
			imgO = ImageIO.read(getClass().getResource("Ocaro.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private synchronized void  soundClick() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("Mousclik.wav"));
					clip.open(audioInputStream);
					clip.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		thread.start();
	}
		
	
	

	public void initMatran() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Cell cell = new Cell();
				matran[i][j] = cell;
			}	
		}
	
	}
	
	public void Resest() {
		this.initMatran();;
		this.setCurenValue(Cell.EMTY_value);
		repaint();
	}
	// 0 : Hòa , 1: người chơi thắng, 2 chưa thắng còn đánh tiếp
	public int Check(String player) {
		// duong cheos thuws nhat
		if (this.matran[0][0].getValue().equals(player)&&this.matran[1][1].getValue().equals(player)&&this.matran[2][2].getValue().equals(player) ) {
			return ST_Win;
		}
		// duong cheo thu 2
		if (this.matran[0][2].getValue().equals(player)&&this.matran[1][1].getValue().equals(player)&&this.matran[2][0].getValue().equals(player) ) {
			return ST_Win;
		}
		//dong thu 1
		if (this.matran[0][0].getValue().equals(player)&&this.matran[0][1].getValue().equals(player)&&this.matran[0][2].getValue().equals(player) ) {
			return ST_Win;
		}
		//dong thu 2
		if (this.matran[1][0].getValue().equals(player)&&this.matran[1][1].getValue().equals(player)&&this.matran[1][2].getValue().equals(player) ) {
			return ST_Win;
		}
		//dongthu 3
		if (this.matran[2][0].getValue().equals(player)&&this.matran[2][1].getValue().equals(player)&&this.matran[2][2].getValue().equals(player) ) {
			return ST_Win;
		}
		// cột thứ 1
		if (this.matran[0][1].getValue().equals(player)&&this.matran[1][1].getValue().equals(player)&&this.matran[2][1].getValue().equals(player) ) {
			return ST_Win;
		}
		//cột thứ 2
		if (this.matran[0][2].getValue().equals(player)&&this.matran[1][2].getValue().equals(player)&&this.matran[2][2].getValue().equals(player) ) {
			return ST_Win;
		}
		
		if (this.isFull()) {
			return ST_Draw;
		}
		return ST_Nomal;
	}
	private boolean isFull() {
		int number = N*M;
		int k = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Cell cell = matran[i][j] ;
		
				if (cell.getValue().equals(cell.EMTY_value)) {
					k++;
				}
				}
			}
		if (k==number) {
			return true;
		}else {
			return false;
		}		
		
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;

		int w = getWidth() / 3;
		int h = getHeight() / 3;

		int k = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Color color = k % 2 == 0 ? Color.BLUE : Color.RED;
				int x = j * w;
				int y = i * h;
				// Cập nhật lại ma trận
				Cell cell = matran[i][j];
				cell.setX(x);
				cell.setY(y);
				cell.setW(w);
				cell.setH(h);

				graphics2d.setColor(color);
				graphics2d.fillRect(x, y, w, h);
				
				if (cell.getValue().equals(Cell.X_Value)) {
					Image img = imgX;

					 graphics2d.drawImage( img, x, y, w, h, this);
				}else if (cell.getValue().equals(Cell.O_Value)) {
					Image img = imgO;
					graphics2d.drawImage( img, x, y, w, h, this);
				}
				// Image img = k%2 ==0 ? imgX:imgO;

				// graphics2d.drawImage( img, x, y, w, h, this);
				k++;

			}
		}
	}
	public String getCurenValue() {
		return curenValue;
	}
	public void setCurenValue(String curenValue) {
		this.curenValue = curenValue;
	}
	public void setEndGameListenner(EndGameListenner endGameListenner) {
		this.endGameListenner = endGameListenner;
	}
	
}
