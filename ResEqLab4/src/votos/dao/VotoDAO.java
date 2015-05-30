package votos.dao;

import java.util.List;

import es.upm.dit.isst.user.model.AppUser;

public interface VotoDAO {

	public void add(String user_id, String propuesta_id);

	public void remove(String voto_id);

}
