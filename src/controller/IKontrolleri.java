package controller;

public interface IKontrolleri {
	
		// Rajapinta, joka tarjotaan  käyttöliittymälle:
	
		/**
		 * Käynnistetään simulointi
		 */
		public void kaynnistaSimulointi();
		public void nopeuta();
		public void hidasta();
		
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		public void visualisoiAsiakas();

}
