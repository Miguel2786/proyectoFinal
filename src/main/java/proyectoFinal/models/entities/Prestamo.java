package proyectoFinal.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable{

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_entregar")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaEntrega;
	
	@Column(name = "fecha_devolver")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaDevolucion;
	
	private String referencia;

	
	@ManyToOne(fetch = FetchType.EAGER)  // Carga NO perezosa
	public Usuario usuario;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	// RELACION PRESTAMO Y LINEAS DE PRESTAMOO 1 A MUCHOS (UNIDIRECCIONAL PRESTAMO -> LINEA) 	
		// Lista de prestamos
		// El enlace se produce utilizando el campo usuario de la clase Prestamo y es bidireccional
		// Descarga los elementos relacionados de forma NO perezosa
		// El borrado se produce en cascada
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "prestamo_id")
	private List<LineaPrestamo> lineas;
	
	//getter y setter lineaprestamo
	
	
	public List<LineaPrestamo> getLineas(){
		return lineas;
	}
	
	public void setLineas(List<LineaPrestamo> lineas) {
		this.lineas=lineas;
	}
	
	// Constructor sin parámetros inicializa las lineas de prestamo
	public Prestamo() {
		lineas =new ArrayList<LineaPrestamo>();
	}
	
	// Método para añadir una línea de prestamo a un prestamo

	public void addLineaPrestamo(LineaPrestamo lineaPrestamo) {
		lineas.add(lineaPrestamo);
		}

	//getter y setter

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public @NotNull LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega( LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public @NotNull LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
}
