package simu.model;

import java.util.Random;

import controller.IKontrolleri;

import eduni.distributions.Bernoulli;
import eduni.distributions.LogNormal;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

/**
 * 
 * luokassa luodaan palvelupisteet, jonot ja toiminnot jonoille yleensä ottaen
 * asiakkaan kulku järjestelmässä/ järjestelmän läpi käytetään normmali-
 * jakaumaa ja negexp- jakaumaaa palvelupisteiden asiointiaikoja arvioidessa
 * niiden parametreja muuttamalla saa muutettua läpimenojen aikoja
 * 
 * @author Hanna Kaimo
 *
 */
public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;
	public static AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
	private int g1, g2, g3, g4, g5, g6, g7, g8;

	/**
	 * @param kontrolleri järjestelmän palvelupisteiden esittely
	 */
	public OmaMoottori(IKontrolleri kontrolleri) {

		super(kontrolleri);
		// luodaan palvelupisteet
		palvelupisteet = new Palvelupiste[11];

		// TapahtumanTyyppi.SAAPUMINEN;
		palvelupisteet[0] = new Palvelupiste(new Normal(4, 2), tapahtumalista, TapahtumanTyyppi.INFO);
		palvelupisteet[1] = new Palvelupiste(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.NEUVOJA1);
		palvelupisteet[2] = new Palvelupiste(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.NEUVOJA2);
		palvelupisteet[3] = new Palvelupiste(new Normal(14, 3), tapahtumalista, TapahtumanTyyppi.GURU1);
		palvelupisteet[4] = new Palvelupiste(new Normal(14, 3), tapahtumalista, TapahtumanTyyppi.GURU2);
		palvelupisteet[5] = new Palvelupiste(new Normal(14, 3), tapahtumalista, TapahtumanTyyppi.GURU3);
		palvelupisteet[6] = new Palvelupiste(new Normal(15, 3), tapahtumalista, TapahtumanTyyppi.GURU4);
		palvelupisteet[7] = new Palvelupiste(new Normal(15, 3), tapahtumalista, TapahtumanTyyppi.GURU5);
		palvelupisteet[8] = new Palvelupiste(new Normal(15, 3), tapahtumalista, TapahtumanTyyppi.GURU6);
		palvelupisteet[9] = new Palvelupiste(new Normal(15, 3), tapahtumalista, TapahtumanTyyppi.GURU7);
		palvelupisteet[10] = new Palvelupiste(new Normal(15, 3), tapahtumalista, TapahtumanTyyppi.GURU8);

		saapumisprosessi = new Saapumisprosessi(new Negexp(10, 6), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);

	}

	/**
	 *generoidaan seuraava asiakas
	 *
	 */
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	/**
	 *asiakkaan läpimeno järjestelmässä
	 */
	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		String asia;
		String kysymys1 = "Liikeoppi", kysymys2, kysymys3, kysymys4, kysymys5, kysymys6, kysymys7, kysymys8;

		Bernoulli jako = new Bernoulli(0.4);
		double start = 1;
		double end = 100;

		double random = new Random().nextDouble();
		double x = start + (random * (end - start));
		double x2 = start + (random * (end - start));
		int summa = 0;

		Asiakas a;

		switch (t.getTyyppi()) {

		case SAAPUMINEN:
			palvelupisteet[0].lisaaJonoon(new Asiakas());
			saapumisprosessi.generoiSeuraava();
			kontrolleri.visualisoiAsiakas();

			break;
		case INFO:

			if (jako.sample() != 1) {
				a = palvelupisteet[0].otaJonosta();
				palvelupisteet[1].lisaaJonoon(a);

			} else {
				a = palvelupisteet[0].otaJonosta();
				palvelupisteet[2].lisaaJonoon(a);

			}

			break;
		case NEUVOJA1:
			asia = "Fysiikka";

			if (x <= 10) {
				a = palvelupisteet[1].otaJonosta();
				palvelupisteet[3].lisaaJonoon(a);

			} else if (x > 10 && x <= 50) {
				a = palvelupisteet[1].otaJonosta();
				palvelupisteet[4].lisaaJonoon(a);

			} else {
				a = palvelupisteet[1].otaJonosta();
				palvelupisteet[5].lisaaJonoon(a);
			}

			break;
		case NEUVOJA2:
			asia = "Matematiikka";

			if (x2 <= 10) {
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[6].lisaaJonoon(a);

			} else if (x2 > 10 && x2 <= 20) {
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[7].lisaaJonoon(a);

			} else if (x2 > 20 && x2 <= 30) {
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[8].lisaaJonoon(a);

			} else if (x2 > 30 && x2 <= 60) {
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[9].lisaaJonoon(a);

			} else {
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[10].lisaaJonoon(a);
			}

			break;
		case GURU1:

			kysymys1 = "LiikeOppi";
			a = palvelupisteet[3].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			// a.tulokset();

			System.out.println("Asiakas " + a.getId() + " oli guru 1 ");

			g1++;

			summa = g1 + summa;

			Kayntienmaara(summa);

			a.raportti();
		    asiakasDAO.tallennaAsiakas(a);
			break;
		case GURU2:
			kysymys2 = "ModerniFysiikka";
			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			System.out.println("Asiakas " + a.getId() + " oli guru 2");
			// a.tulokset();
			g2++;

			summa = g2 + summa;

			Kayntienmaara2(summa);
			a.raportti();
			asiakasDAO.tallennaAsiakas(a);
			break;
		case GURU3:
			kysymys8 = "SahkoMagnetismi";
			a = palvelupisteet[5].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());

			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 3");

			g3++;

			summa = g3 + summa;

			Kayntienmaara3(summa);
			a.raportti();
		    asiakasDAO.tallennaAsiakas(a);
			break;

		case GURU4:
			kysymys4 = "Algebra";
			a = palvelupisteet[6].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());

			// System.out.println(a.getPoistumisaika());
			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 4");

			g4++;

			summa = g4 + summa;

			Kayntienmaara4(summa);
			a.raportti();
			asiakasDAO.tallennaAsiakas(a);
			break;
		case GURU5:
			kysymys5 = "Trigonometria";
			a = palvelupisteet[7].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 5");

			g5++;

			summa = g5 + summa;

			Kayntienmaara5(summa);

			a.raportti();
			asiakasDAO.tallennaAsiakas(a);
			break;
		case GURU6:
			kysymys6 = "TilastotJaTod";
			a = palvelupisteet[8].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 6");

			g6++;

			summa = g6 + summa;

			Kayntienmaara6(summa);

			a.raportti();
			asiakasDAO.tallennaAsiakas(a);
			break;
		case GURU7:
			kysymys7 = "Laskenta";
			a = palvelupisteet[9].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 7");

			g7++;

			summa = g7 + summa;

			Kayntienmaara7(summa);
			a.raportti();
			asiakasDAO.tallennaAsiakas(a);

			break;
		case GURU8:
			kysymys3 = "Geometria";
			a = palvelupisteet[10].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			// System.out.println(a.getPoistumisaika());
			// a.tulokset();
			System.out.println("Asiakas " + a.getId() + " oli guru 8");

			g8++;

			summa = g8 + summa;

			Kayntienmaara8(summa);

			a.raportti();
			asiakasDAO.tallennaAsiakas(a);
			break;
		}
	}

	public void Kayntienmaara(int summa) {

		System.out.println("Guru 1: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara2(int summa) {

		System.out.println("Guru 2: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara3(int summa) {

		System.out.println("Guru 3: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara4(int summa) {

		System.out.println("Guru 4: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara5(int summa) {

		System.out.println("Guru 5: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara6(int summa) {

		System.out.println("Guru 6: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara7(int summa) {

		System.out.println("Guru 7: n luona asiakkaita " + summa);
	}

	public void Kayntienmaara8(int summa) {

		System.out.println("Guru 8: n luona asiakkaita " + summa);
	}

	/**
	 * loppuraportti simuloinnin päättymisaika 
	 */
	@Override
	protected void tulokset() {
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());

		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());

	}

}
