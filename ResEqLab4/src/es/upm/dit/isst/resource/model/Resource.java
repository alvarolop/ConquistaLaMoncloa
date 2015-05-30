package es.upm.dit.isst.resource.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long user;
	private String titulo;
	private String url_foto;
	private List<Long> propuestas;

	public Resource(Long user) {
		this.user = user;
	}

	public Resource(Long user, String titulo, String img_url) {
		this.user = user;
		this.titulo = titulo;
		this.url_foto = img_url;
	}

	public Long getId() {
		return id;
	}

	public List<Long> getPropuestas() {
		return propuestas;
	}

	public void addPropuesta(Long propuesta) {
		this.propuestas.add(propuesta);
	}

	public void removePropuesta(String propuestaId) {
		propuestas.remove(propuestaId);
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

}
