package proyectoFinal.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty  // obligatorio para String (importante coger el de jakarta, el de hibernate deprecado)
	@Size(min = 4, max = 12)  // Número de caracteres
	private String nombre;
	
	@NotEmpty
	@Size(min = 4, max = 12)
	private String apellido;
	
	@NotEmpty
	@Email // formato de email
	private String email;

	// nombre del campo de la base de datos
	@Column(name = "created_at")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate createdAt;

	private String foto;
	public String getFoto(){return foto;}
	public void setFoto(String foto){this.foto=foto;}
	
	//relacion usuario y prestamos de 1 a muchos bidireccional
	@OneToMany(mappedBy = "usuario" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;

	//constructor vacio del usuario
	public Usuario() {
		prestamos = new ArrayList<Prestamo>();
	}
	// Método para añadir prestamo  al usuario (añade elemento a la lista de prestamos)
	public void addPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
	}
	
	//Getter y setter de los prestamos

	public List<Prestamo> getPrestamos(){
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos=prestamos;
	}
	
	//Getter y setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
