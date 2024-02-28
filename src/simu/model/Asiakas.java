package simu.model;

import simu.framework.Kello;

import simu.framework.Trace;
import simu.model.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import eduni.distributions.Bernoulli;

/**
 * Luokka toteuttaa asiakkaan, jonka id:n avulla saa saapumis- ja lähtöaikoja ja
 * näistä taas voi laskea viipymisaikoja, keskiarvoja, ja gurujen onnistumisia
 * 
 * @author Hanna Kaimo
 * @version 1.0
 *
 */
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static int sum = 0;
	private int maara;

	public Asiakas() {
		id = i++;

		saapumisaika = Kello.getInstance().getAika();
		// Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":" + saapumisaika);

	}

	/**
	 * @param saapumisaika  Asiakkaan saapumisaika järjestelmästä
	 * @param poistumisaika Asiakkaan poisumisaika järjestelmästä
	 * @param id            asiakkaan yksilöiminen id:n avulla
	 */
	public Asiakas(double saapumisaika, double poistumisaika, int id) {
		super();
		this.saapumisaika = saapumisaika;
		this.poistumisaika = poistumisaika;
		this.id = id;
	}

	/**
	 * getteri id:lle
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * asettaa id:n
	 * 
	 * @param id int joka sisältää asiakkaan id:n
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getteri poistumisajalle
	 * 
	 * @return a double joka sisältää asiakkaan poistumisajan
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * luo asiakkaan, jolla tietty id
	 * 
	 * @param id asiakkaan id
	 */
	public Asiakas(int id) {
		super();
		this.id = id;
	}

	/**
	 * asettaa poistumisajan
	 * 
	 * @param poistumisaika joka sisältää asiakkaan poistumisajan
	 */
	public void setPoistumisaika(double poistumisaika) {

		this.poistumisaika = poistumisaika;
	}

	/**
	 * Getteri saapumisajalle
	 * 
	 * @return double joka sisältää asiakkaan saapumisajan
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * asettaa asiakkaan saapumisajan
	 * 
	 * @param saapumisaika joka sisältää asiakkaan saapumisajan
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	/**
	 * 
	 * metodi, jossa saadaan asiakkaan saapumisaika, lähtöaika ja lasketaan näiden
	 * avulla viipymisaika järjestelmässä lasketaan myös asiakkaiden läpimenojen
	 * keskiarvo summa avulla käytetään Bernoulin jakaumaa gurujen onnistumisien
	 * määrän laskemiseen tulostetaan kaikki arvot
	 * 
	 */
	public void raportti() {

		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui:" + Math.round(saapumisaika * 100.0) / 100.0);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui:" + Math.round(poistumisaika * 100.0) / 100.0);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi:"
				+ ((Math.round(poistumisaika * 100.0) / 100.0) - (Math.round(saapumisaika * 100.0) / 100.0)));
		sum += (poistumisaika - saapumisaika);

		double keskiarvo = sum / id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo " + keskiarvo);

		Bernoulli onnistuminen = new Bernoulli(0.9);
		for (int j = 0; j < i; j++) {

			if (onnistuminen.sample() != 0) {
				maara++;
			}
		}

		System.out.println("Gurujen onnistumisten määrä: " + maara);

	}

	/**
	 * @return a string muotoisen esityksen oliosta
	 */
	@Override
	public String toString() {
		return "Asiakas [saapumisaika=" + saapumisaika + ", poistumisaika=" + poistumisaika + ", id=" + id + ", maara="
				+ maara + "]";
	}

}
