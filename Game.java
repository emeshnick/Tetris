package Tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game{
	
private Pane _gamePane, _piecePane;
private Piece _piece;
private TetrisSquare[][] _boardArray;
private Timeline _timeline;
private KeyHandler _keyHandler;
private TimeHandler _timeHandler;

	public Game() {
		_gamePane = new Pane();
		_boardArray = new TetrisSquare[Constants.BOARD_WIDTH*Constants.SQUARE_SIZE]
				[Constants.BOARD_HEIGHT*Constants.SQUARE_SIZE];
		_piecePane = new Pane();
		_keyHandler = new KeyHandler();
		_timeHandler = new TimeHandler();
		_timeHandler.generatePiece();
		_piecePane.setFocusTraversable(true);
		this.setupBoard();
		_gamePane.getChildren().add(_piecePane);
		_piecePane.addEventHandler(KeyEvent.KEY_PRESSED, _keyHandler);
		this.setupTimeline();
	}
	
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), 
				_timeHandler);
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}
	
	public void setupBoard(){
		for (int row = 0; row < Constants.BOARD_HEIGHT;
				row++) {
			for (int col = 0; col < (Constants.BOARD_WIDTH/5);
					col++) {
				TetrisSquare board = new TetrisSquare(Color.SLATEGRAY);
				board.setXLoc(col*Constants.SQUARE_SIZE);
				board.setYLoc(row*Constants.SQUARE_SIZE);
				_boardArray[col][row] = board;
				_gamePane.getChildren().add(board.getRect());
			}
			for (int col = ((Constants.BOARD_WIDTH*4)/5); col < Constants.BOARD_WIDTH;
					col++) {
				TetrisSquare board = new TetrisSquare(Color.SLATEGRAY);
				board.setXLoc(col*Constants.SQUARE_SIZE);
				board.setYLoc(row*Constants.SQUARE_SIZE);
				_boardArray[col][row] = board;
				_gamePane.getChildren().add(board.getRect());
			}
		}
		for (int row = ((Constants.BOARD_HEIGHT*4)/5); row < Constants.BOARD_HEIGHT;
				row++) {
			for (int col = (Constants.BOARD_WIDTH/5); col < ((Constants.BOARD_WIDTH*4)/5); 
					col++) {
				TetrisSquare board = new TetrisSquare(Color.SLATEGRAY);
				board.setXLoc(col*Constants.SQUARE_SIZE);
				board.setYLoc(row*Constants.SQUARE_SIZE);
				_boardArray[col][row] = board;
				_gamePane.getChildren().add(board.getRect());
			}
		}
	}
	
	public Pane getNode(){
		return _gamePane;
	}
	
private class KeyHandler implements EventHandler<KeyEvent> {

	@Override
	public void handle(KeyEvent event) {
			
		KeyCode keyPressed = event.getCode();
			
		if (keyPressed == KeyCode.LEFT) {
			if (_timeline.getStatus() == Animation.Status.RUNNING) {
				if(_timeHandler.canMoveLeft()) {
				_piece.moveXPosition(0 - Constants.SQUARE_SIZE);
				}
			}
		} else if (keyPressed == KeyCode.RIGHT) {
			if (_timeline.getStatus() == Animation.Status.RUNNING) {
				if (_timeHandler.canMoveRight()){
				_piece.moveXPosition(Constants.SQUARE_SIZE);
				}
			}
		} else if (keyPressed == KeyCode.UP) {
			if (_timeline.getStatus() == Animation.Status.RUNNING) {
				if (_timeHandler.canRotate()){
					_piece.rotate();
				}
			}
		} else if (keyPressed == KeyCode.P) {
			if (_timeline.getStatus() == Animation.Status.PAUSED){
				_timeline.play();
			} else if (_timeline.getStatus() == Animation.Status.RUNNING)
				_timeline.pause();
		} else if (keyPressed == KeyCode.SPACE) {
			if (_timeline.getStatus() == Animation.Status.RUNNING) {
			while (_timeHandler.canMoveDown()) {
				_piece.moveYPosition(Constants.SQUARE_SIZE);
			 }
			}
		}
		event.consume();	
	}
	

		
}
	
private class TimeHandler implements EventHandler<ActionEvent> {
	
	@Override
	public void handle(ActionEvent event) {
	
		
		for (int col = (Constants.BOARD_WIDTH/5); col < ((Constants.BOARD_WIDTH*4)/5); col++){
			if (_boardArray[col][3] != null){
				_timeline.stop();
				System.out.println("Game Over");
			}
		}
		
		if (this.canMoveDown()){ 
			_piece.moveYPosition(Constants.SQUARE_SIZE);			
		} else {
			for (int v = 0; v < 4; v++) {
				_boardArray[(int) ((_piece.getPiece(v).getXLoc()) / Constants.SQUARE_SIZE)]
						[(int) ((_piece.getPiece(v).getYLoc()) / Constants.SQUARE_SIZE)] = _piece.getPiece(v);	
			}
			this.generatePiece();
		}
	
		
		for (int row = ((Constants.BOARD_HEIGHT*4)/5) - 1; row >= 0; row--){
			boolean fullRow = true;
			int line = 0;
			for (int col = (Constants.BOARD_WIDTH/5); col < ((Constants.BOARD_WIDTH*4)/5); col++){
				line = row;
				if (_boardArray[col][row] == null){
					fullRow = false;
				}		
				}	
			if (fullRow){
				for (int col = (Constants.BOARD_WIDTH/5); col < ((Constants.BOARD_WIDTH*4)/5); col++){
					_piecePane.getChildren().remove(_boardArray[col][line].getRect());
					_boardArray[col][line] = null;
					for (int y = line - 1; y >= 0; y--){
						if (_boardArray[col][y] != null){
						_boardArray[col][y].setYLoc(_boardArray[col][y].getYLoc() + Constants.SQUARE_SIZE);
						}
						_boardArray[col][y + 1] = _boardArray[col][y];
					}
				}	
			}
			}
		}
	
	
	public boolean canMoveDown() {
		for (int i = 0; i < 4; i++) {
			int x = (int) ((_piece.getPiece(i).getXLoc()) / Constants.SQUARE_SIZE);
			int y = (int) ((_piece.getPiece(i).getYLoc()) / Constants.SQUARE_SIZE);
			if (y >= -1){	
				if(_boardArray[x][y + 1] != null){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canRotate() {
		double corx = _piece.getPiece(0).getXLoc();
		double cory = _piece.getPiece(0).getYLoc();
		for (int i = 0; i < 4; i++){
			int x = (int) ((corx - cory + _piece.getPiece(i).getYLoc()) / Constants.SQUARE_SIZE);
			int y = (int) ((corx + cory - _piece.getPiece(i).getXLoc()) / Constants.SQUARE_SIZE);
			if (y > -1) {
				if (_boardArray[x][y] != null){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canMoveRight() {
		for (int i = 0; i < 4; i++) {
			int x = (int) ((_piece.getPiece(i).getXLoc()) / Constants.SQUARE_SIZE);
			int y = (int) ((_piece.getPiece(i).getYLoc()) / Constants.SQUARE_SIZE);
			if (y > -1){	
				if(_boardArray[x + 1][y] != null){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canMoveLeft() {
		for (int i = 0; i < 4; i++) {
			int x = (int) ((_piece.getPiece(i).getXLoc()) / Constants.SQUARE_SIZE);
			int y = (int) ((_piece.getPiece(i).getYLoc()) / Constants.SQUARE_SIZE);
			if (y > -1){	
				if(_boardArray[x - 1][y] != null){
					return false;
				}
			}
		}
		return true;
	}
	
	public void generatePiece(){
		int rand = (int) (Math.random() * 6);
		_piece = null;
		switch (rand) {
			case 0:
			_piece = new Piece(_piecePane, Color.RED);
				break;
			case 1:
			_piece = new Piece(_piecePane, Color.YELLOW);
				break;
			case 2:
			_piece = new Piece(_piecePane, Color.BLUE);
				break;
			case 3:
			_piece = new Piece(_piecePane, Color.ORANGE);
				break;	
			case 4:
			_piece = new Piece(_piecePane, Color.GREEN);
				break;
			case 5:
			_piece = new Piece(_piecePane, Color.PINK);
				break;
			case 6:
			_piece = new Piece(_piecePane, Color.PURPLE);
				break;
			default:
			_piece = new Piece(_piecePane, Color.BLUE);
			break;
		}

	}

}
}

