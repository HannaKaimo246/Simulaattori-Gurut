package simu.framework;

import java.util.PriorityQueue;


/**
 * luokka luo eri tapahtumista Tapahtuma- tyyppisen listan
 * 
 * @author Hanna Kaimo
 *
 */
public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	/**
	 * oletus- konstruktori
	 */
	public Tapahtumalista(){
	 
	}
	
	/**
	 * @return poistettavan (seuraavan) tapahtuman
	 */
	public Tapahtuma poista(){
		//Trace.out(Trace.Level.INFO,"Tapahtumalistasta poisto " + lista.peek());
		return lista.remove();
	}
	
	/**
	 * @param t yksi tapahtuma
	 */
	public void lisaa(Tapahtuma t){
		lista.add(t);
	}
	
	/**
	 * @return double tyyppisen ajan seuraavalle asiakkaalle
	 */
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
	
}
