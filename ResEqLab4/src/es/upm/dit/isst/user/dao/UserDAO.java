package es.upm.dit.isst.user.dao;

import java.util.List;

import es.upm.dit.isst.user.model.AppUser;

public interface UserDAO {

	public void add(String googleId, String name, boolean isCandidato);

	public void remove(long id);

	public List<AppUser> getUsers();

	boolean appUserExists(String string);

	AppUser getUserId(String google_id);

}
