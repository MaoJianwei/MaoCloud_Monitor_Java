package org.mao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mao.core.Core;
import org.mao.gui.MainPageGuiController;

import java.net.URL;

/**
 * Hello world!
 */
public final class AppEntry extends Application
{
    private Core core;
    private FXMLLoader mainPageLoader;

    public MainPageGuiController getMainPageGuiController(){
        return mainPageLoader.getController();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = getClass().getResource("/javafx/MainPage.fxml");
            mainPageLoader = new FXMLLoader(url);
            Parent root = mainPageLoader.load();

            primaryStage.setTitle("Mao Cloud Control Center v0.1");
            primaryStage.setScene(new Scene(root, 320, 240));
            primaryStage.setFullScreenExitHint("BigMao Radio Station 2016.10.01");
            primaryStage.setFullScreen(true);
            primaryStage.show();

            getMainPageGuiController().addStage("main", primaryStage);

            core = new Core(this);
            core.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
        core.shutdown();
    }


    public static void main( String[] args )
    {
        launch(args);
    }
}
