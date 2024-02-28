package simu.framework;

/**
 * luokassa luodaan kello simuloinnin aikojen mittaamista varten
 * 
 * 
 * @author Hanna Kaimo
 *
 */
public class Kello {

	private double aika;
	private static Kello instanssi;
	
	/**
	 * alusetaan aika nollaksi
	 */
	private Kello(){
		aika = 0;
	}
	
	public static Kello getInstance(){
		if (instanssi == null){
			instanssi = new Kello();	
		}
		return instanssi;
	}
	
	/**
	 * @param aika joka sisältää ajan
	 */
	public void setAika(double aika){
		this.aika = aika;
	}

	/**
	 * 
	 * getteri ajalle
	 * 
	 * @return double tyyppisen muutujan aika
	 */
	public double getAika(){
		return aika;
	}
}
