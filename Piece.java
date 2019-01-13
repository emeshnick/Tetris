package Tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Piece {
	
private Color _color;
private Pane _piecePane;
private TetrisSquare[] _piece;

	public Piece(Pane piecePane, Color color) {
		_piecePane = new Pane();
		_piecePane = piecePane;
		_piece = new TetrisSquare[4];
		_color = color;
		for (int i = 0; i < 4; i++) {
			TetrisSquare square = new TetrisSquare(_color);
			_piece[i] = square;			
			_piecePane.getChildren().add(square.getRect());
		}
		this.setupShape();
	}
	
	public TetrisSquare getPiece(int x){
		return _piece[x];
	}
	
	public void setupShape() {
		if (_color == Color.RED) {
			this.setupLine();
		} 
		else if (_color == Color.YELLOW) {
			this.setupL();
		} else if (_color == Color.GREEN){
			this.setupS();
		} else if (_color == Color.BLUE){
			this.setupT();
		} else if (_color == Color.ORANGE){
			this.setupRL();
		} else if (_color == Color.PINK){
			this.setupSquare();
		} else if (_color == Color.PURPLE){
			this.setupZ();
		} 
		}
	
	public void setupLine(){
		for (int i = 0; i < 4; i++){
		_piece[i].setXLoc(Constants.STARTING_X);
		_piece[i].setYLoc((i+1)*Constants.SQUARE_SIZE + Constants.STARTING_Y);
		}
	}
	
	public void setupL(){
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);	
		_piece[2].setXLoc(2*Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[3].setXLoc(2*Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[0].setYLoc(Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
	}
	
	public void setupRL(){		
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[2].setXLoc(2*Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[3].setXLoc(Constants.STARTING_X);
		_piece[0].setYLoc(Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
	}
	
	public void setupS(){
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[2].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[3].setXLoc(2*Constants.SQUARE_SIZE  + Constants.STARTING_X);
		_piece[0].setYLoc(Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
	}
	
	public void setupZ() {
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[2].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[3].setXLoc(2*Constants.SQUARE_SIZE  + Constants.STARTING_X);
		_piece[0].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.STARTING_Y);
	}
	
	public void setupT() {
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[2].setXLoc(2*Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[3].setXLoc(Constants.SQUARE_SIZE  + Constants.STARTING_X);
		_piece[0].setYLoc(Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
	}
	
	public void setupSquare(){
		_piece[0].setXLoc(Constants.STARTING_X);
		_piece[1].setXLoc(Constants.SQUARE_SIZE + Constants.STARTING_X);
		_piece[2].setXLoc(Constants.STARTING_X);
		_piece[3].setXLoc(Constants.SQUARE_SIZE  + Constants.STARTING_X);
		_piece[0].setYLoc(Constants.STARTING_Y);
		_piece[1].setYLoc(Constants.STARTING_Y);
		_piece[2].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
		_piece[3].setYLoc(Constants.SQUARE_SIZE + Constants.STARTING_Y);
	}

	public void moveYPosition(double x) {
		for (int i = 0; i < 4; i++){
				_piece[i].setYLoc(_piece[i].getYLoc() + x);
			}
		}
	
	public void moveXPosition(double x) {
		for (int i = 0; i < 4; i++){
			_piece[i].setXLoc(_piece[i].getXLoc() + x);				
		}
	}

	public void rotate(){
		double corx = _piece[0].getXLoc();
		double cory = _piece[0].getYLoc();
		for (int i = 0; i < 4; i++){
			double x = corx - cory + _piece[i].getYLoc();
			double y = corx + cory - _piece[i].getXLoc();
			_piece[i].setXLoc(x);
			_piece[i].setYLoc(y);
		}
	}
}