package votos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.upm.dit.isst.resource.model.Resource;

@Entity
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String user_id;
	private String propuesta_id;

	public Voto(String user_id, String propuesta_id) {
		this.user_id = user_id;
		this.propuesta_id = propuesta_id;

	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPropuesta_id() {
		return propuesta_id;
	}

	public void setPropuesta_id(String propuesta_id) {
		this.propuesta_id = propuesta_id;
	}

}
