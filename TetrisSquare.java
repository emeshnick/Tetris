package Tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrisSquare {
	
private Rectangle _rectangle;

	public TetrisSquare(Color color) {
		_rectangle = new Rectangle(Constants.SQUARE_SIZE,
				Constants.SQUARE_SIZE);
		_rectangle.setFill(color);
		_rectangle.setStroke(Color.BLACK);
	}
	
	public Rectangle getRect() {
		return _rectangle;
	}
	
	public void setXLoc(double x){
		_rectangle.setX(x);
	}
	
	public void setYLoc(double x){
		_rectangle.setY(x);
	}
	
	public double getXLoc() {
		return _rectangle.getX();
	}
	
	public double getYLoc() {
		return _rectangle.getY();
	}
	
}