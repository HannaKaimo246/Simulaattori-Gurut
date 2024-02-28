package simu.framework;

/**
 * 
 * Kontrolleri käyttää tätä rajapintaa
 * 
 * @author Hanna Kaimo
 *
 */
public interface IMoottori {

	public void setSimulointiaika(double aika);

	public void setViive(long aika);

	public long getViive();
}
