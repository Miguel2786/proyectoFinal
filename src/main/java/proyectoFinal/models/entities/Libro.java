package proyectoFinal.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "libros")
public class Libro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 3, max = 35)
	private String titulo;
	
	@NotEmpty
	@Size(min = 3, max = 35)
	private String autor;
	
	@NotEmpty
	@Size(min = 3, max = 35)
	private String genero;
	
	@Column(name="cantidad_libros")
	@NotNull
	@Min(value = 0, message = " Cantidad de libros debe ser mayor que 0")
	@Max(value =1_000)
	private Integer cantidadLibros;

	private String foto;
	public String getFoto(){return foto;}
	public void setFoto(String foto){this.foto=foto;}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getCantidadLibros() {
		return cantidadLibros;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setCantidadLibros(Integer cantidadLibros) {
		this.cantidadLibros = cantidadLibros;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
