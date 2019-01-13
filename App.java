package Tetris;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * It's time for Tetris! This is the  main class to get things started.
  *
  * The main method of this application calls the start method. You
  * will need to fill in the start method to instantiate your game.
  *
  * Class comments here...
  *
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
    	stage.setTitle("Tetris");
    	PaneOrganizer paneOrganizer = new PaneOrganizer();
    	Scene scene = new Scene(paneOrganizer.getRoot(),
    		Constants.APP_WIDTH, Constants.APP_HEIGHT);
    	stage.setScene(scene);
    	stage.show();
        // Create top-level object, set up the scene, and show the stage here.
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
