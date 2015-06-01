package votos.dao;

import java.util.List;

import votos.model.Voto;
import es.upm.dit.isst.user.model.AppUser;

public interface VotoDAO {

	public boolean add(String user_id, String propuesta_id);

	public void remove(long voto_id);

	public int numVotoPropuesta(String propuesta_id);

	public String getVotoId(String user_id, String propuesta_id);

	public List<Voto> getVotos();

}
