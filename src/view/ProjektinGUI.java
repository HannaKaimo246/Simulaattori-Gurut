package view;

import java.awt.Dimension;

import controller.IKontrolleri;

import controller.Kontrolleri;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.*;

/**
 * tässä yksinkertainen graafinen kyttöliittymä
 * 
 * @author Hanna Kaimo
 *
 */
public class ProjektinGUI extends Application implements ProjektiView {

	private IKontrolleri kontrolleri;

	private AsiakasAccessObject asiakas1 = new AsiakasAccessObject();

	private Asiakas a1 = new Asiakas();

	public void start(Stage primaryStage) {

		try {

			primaryStage.setTitle("Yhden kävijän tiedot");
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(20, 20, 25, 20)); // marginaalit ylä, oikea, ala, vasen

			Text text = new Text("Käyttäjän tietojen hakeminen ID:n avulla");
			text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			BorderPane.setAlignment(text, Pos.TOP_CENTER);
			root.setTop(text);
			root.setStyle("-fx-background-color: #ffbab5");

			Label tulosLabel = new Label("Tulos");
			tulosLabel.setPrefSize(100, 20);

			TextField numero = new TextField();
			numero.setPromptText("Anna id");

			TextField tiedot = new TextField();
			tiedot = new TextField("Kävijän tiedot");

			tiedot.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

			Button nappula = new Button("Kenen tiedot haluat?");

			nappula.setPrefSize(140, 70);

			// int value = Integer.parseInt(numero.getText());

			nappula.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Text text = new Text(numero.getText().replace("[", "") + asiakas1.palautaAsiakas(numero));
					
					text.toString().replace("[", "").replace("]", "");

					text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
					BorderPane.setAlignment(text, Pos.TOP_CENTER);
					root.setBottom(text);

				}
			});

			root.setCenter(numero);
			root.setBottom(nappula);

			Scene scene = (new Scene(root, 780, 500));

			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * käyttöliittymä luodaan
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void init() {

		Trace.setTraceLevel(Level.INFO);

		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public double getsaapumisaika() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getpoistumisaika() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getraportti() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setid(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setsaapumisaika() {
		// TODO Auto-generated method stub

	}

}