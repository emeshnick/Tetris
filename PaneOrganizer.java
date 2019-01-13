package Tetris;

import javafx.scene.layout.BorderPane;

public class PaneOrganizer{
	
private BorderPane _root;
private Game _game;

	public PaneOrganizer() {
		_root = new BorderPane();
		_game = new Game();
		_root.setCenter(_game.getNode());
	}
	
	public BorderPane getRoot() {
		return _root;
	}
}
