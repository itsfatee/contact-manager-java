package application;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;




public class Main extends Application {
	
	
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
	
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/com/esmt/contactapp/view/Choix.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();
			Image icon = new Image("/com/esmt/contactapp/view/icons/contact.jpg");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Contact Manager Application");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

    
   
}
