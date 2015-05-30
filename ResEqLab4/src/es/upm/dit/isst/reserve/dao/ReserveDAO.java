package es.upm.dit.isst.reserve.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.reserve.model.Reserve;
import es.upm.dit.isst.resource.model.Resource;

public interface ReserveDAO {

	public void remove(long id);

	List<Reserve> listPropuestas();

	List<Reserve> getPropuestas();

	long add(String title, String description);

//	public List<Propuesta> getPropuestas(String nickname);

	public Reserve getPropuesta(long parseLong);

	public void update(long id,String title, String description);
	
	//public int[][] mapCheck(Programa[][] ResourceMap, Propuesta hora);
}
