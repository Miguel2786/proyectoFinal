package proyectoFinal.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lineas_prestamos")
public class LineaPrestamo implements Serializable{

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*@Column(name = "cantidad_disponible")
	private Integer cantidadDisponible;
*/
	@Column(name = "cantidad_prestada")
	private Integer cantidadPrestada;

		// RELACION LINEA DE PEDIDO Y LIBRO MUCHOS A 1 (UNIDIRECCIONAL LINEA -> LIBRO)  	
		// Artículo de la línea
		// El enlace se produce utilizando el campo tienda de la clase Pedido y es bidireccional
		// Descarga los elementos relacionados de forma NO perezosa
		// El borrado se produce en cascada
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="libro_id")
		private Libro libro;

		// getter y setter libro

		public Libro getLibro() {
			return libro;
		}

		public void setLibro(Libro libro) {
			this.libro = libro;
		}
		
		//Calcular la cantidad disponible y prestada
		
		/*public void prestarLibro() {
			cantidadDisponible--;
			cantidadPrestada++;
		}
		
		public void devolverrLibro() {
			cantidadDisponible++;
			cantidadPrestada--;
		}
*/
		//getter y setter

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		/*public Integer getCantidadDisponible() {
			return cantidadDisponible;
		}

		public void setCantidadDisponible(Integer cantidadDisponible) {
			this.cantidadDisponible = cantidadDisponible;
		}

		*/public Integer getCantidadPrestada() {
			return cantidadPrestada;
		}

		public void setCantidadPrestada(Integer cantidadPrestada) {
			this.cantidadPrestada = cantidadPrestada;
		}
		
		
}
