package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

/**
 * 
 * Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat
 * muuttujat)
 * 
 * @author Hanna Kaimo
 *
 */
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus

	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	// JonoStartegia strategia; //optio: asiakkaiden järjestys

	private boolean varattu = false;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;

	}

	/**
	 * 
	 * Jonon 1. asiakas aina palvelusssa
	 * 
	 * @param a
	 */
	public void lisaaJonoon(Asiakas a) {
		jono.add(a);

	}

	/**
	 * Poistetaan palvelussa ollut
	 * 
	 * @return jonon viimeinen
	 */
	public Asiakas otaJonosta() {
		varattu = false;
		return jono.poll();
	}

	/**
	 * Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
	 */
	public void aloitaPalvelu() {
		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
	}

	public boolean onVarattu() {
		return varattu;
	}

	public boolean onJonossa() {
		return jono.size() != 0;
	}

}
