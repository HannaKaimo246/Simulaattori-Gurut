package simu.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.ProjektiView;
import simu.model.*;

/**
 * 
 * Tässä luokassa luodaan tietokantayhteys, tallennetaan tiedot tietokantaan ja
 * esitetään yhden asiakkaan tiedot konsolissa ja graafisessa käyttöliittymässä
 * 
 * @author Hanna Kaimo
 *
 */
public class AsiakasAccessObject {

	final String URL = "jdbc:mariadb://127.0.0.1/Asiakkaat";
	final String USERNAME = "root";
	final String PASSWORD = "cxhkaPe";
	Connection connection;

	{

		try {

			connection = DriverManager.getConnection(URL + "?user=" + USERNAME + "&password=" + PASSWORD);

			System.out.println("Connected");

		} catch (SQLException e) {

			do {
				System.out.println("Viesti" + e.getMessage());
				System.out.println("Virhekoodi: " + e.getErrorCode());
				System.out.println("SQL-tilakoodi: " + e.getSQLState());

			} while (e.getNextException() != null);
			System.exit(-1);
			e.printStackTrace();

		}

	}

	/**
	 * asikas olioiden tiedot tallennetaan siis tässä
	 * 
	 * @param a2
	 */
	public void tallennaAsiakas(Asiakas a2) {

		try {

			String query = "INSERT INTO Kavijat (saapumisAika, lahtoAika,id) VALUES (?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(query);

			stmt.setDouble(1, a2.getSaapumisaika());
			stmt.setDouble(2, a2.getPoistumisaika());
			stmt.setInt(3, a2.getId());

			stmt.execute();

			System.out.println("Data saved!");

			stmt.close();

		} catch (SQLException e) {

			do {
				System.out.println("Viesti" + e.getMessage());
				System.out.println("Virhekoodi: " + e.getErrorCode());
				System.out.println("SQL-tilakoodi: " + e.getSQLState());

			} while (e.getNextException() != null);
			System.out.println("Virhe");
			System.exit(-1);
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * Asikkaita voi etsiä id:n perusteella tietokannasta
	 * 
	 * @param numero(ID)
	 * @return asiakas olio
	 */
	public Asiakas palautaAsiakas(TextField numero) {

		Asiakas a1 = null;

		try {

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Kavijat WHERE id =" + numero.getText());

			while (rs.next()) {

				System.out.println("ID: " + rs.getInt(3) + ", SAAPUMISAIKA:" + rs.getDouble(1) + ", LÄHTÖAIKA: "
						+ rs.getDouble(2));
				
				a1 = new Asiakas(rs.getDouble(1), rs.getDouble(2), rs.getInt(3));

			}

			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return a1;
	}

}