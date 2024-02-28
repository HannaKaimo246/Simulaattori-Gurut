package view;

import simu.model.*;


public interface ProjektiView {
	
	public abstract double getsaapumisaika();
	
	public abstract double getpoistumisaika();
	
	public abstract double getid();
	
	public abstract double getraportti();
	
	public abstract void setid(int id);
	
	public abstract void setsaapumisaika();

}
