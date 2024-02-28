package controller;

import simu.model.*;

import javafx.application.Platform;
import simu.framework.IMoottori;
import view.ISimulaattorinUI;
import view.ProjektinGUI;

/**
 * 
 * luokka toteuttaa IKontrolleri- rajapinnan
 * 
 * @author Hanna Kaimo
 *
 */
public class Kontrolleri implements IKontrolleri {

	private IMoottori moottori;
	private ISimulaattorinUI gui;
	private ProjektinGUI gui2;

	public Kontrolleri(ISimulaattorinUI gui) {
		this.gui = gui;
	}

	public Kontrolleri(ProjektinGUI gui2) {
		this.gui2 = gui2;
	}

	/**
	 * Moottorin ohjausta: luodaan uusi moottorisäie jokaista simulointia varten
	 */
	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this);
		moottori.setSimulointiaika(gui.getAika());
		moottori.setViive(gui.getViive());
		gui.getVisualisointi().tyhjennaNaytto();
		((Thread) moottori).start();

	}

	/**
	 *hidastetaan moottorisäiettä
	 */
	@Override
	public void hidasta() {  
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	/**
	 * nopeutetaan moottorisäiettä
	 */
	@Override
	public void nopeuta() { 
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	

	/**
	 * Simulointitulosten välittämistä käyttöliittymään.
	 *  Koska gui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata
	 * JavaFX-säikeeseen:
	 */
	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(() -> gui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable() {
			public void run() {
				gui.getVisualisointi().uusiAsiakas();
			}
		});
	}

}
