package LamCoCaro;

public class Cell {
  private int x;
  private int y;
  private int w;
  private int h;
  private String value;
  public static final String X_Value = "X";
  public static final String O_Value = "O";
  public static final String EMTY_value = " ";
public Cell() {
	this.value = EMTY_value;
}
public Cell(int x, int y, int w, int h, String value) {
	super();
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.value = value;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getW() {
	return w;
}
public void setW(int w) {
	this.w = w;
}
public int getH() {
	return h;
}
public void setH(int h) {
	this.h = h;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
  
}

