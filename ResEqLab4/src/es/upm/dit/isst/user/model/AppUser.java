package es.upm.dit.isst.user.model;
import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String googleId;
	private String correo;
	private boolean isCandidato;
	
	public AppUser(String googleId, String name, boolean isCandidato){
		this.setGoogleId(googleId);
		this.setCorreo(name);
		this.setCandidato(isCandidato);
	}
	public Long getId() {
		return id;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public boolean isCandidato() {
		return isCandidato;
	}
	public void setCandidato(boolean candidato) {
		this.isCandidato = candidato;
	}

}