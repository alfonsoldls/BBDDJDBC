package modelo.entidad;

import java.util.Objects;

public class Pasajero {

	private int id, edad;
	private float peso;
	private String nombre;
	private Coche coche;
	
	public Pasajero(int edad, float peso, String nombre) {
		super();
		this.edad = edad;
		this.peso = peso;
		this.nombre = nombre;
	}
	public Pasajero() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche idcoche) {
		this.coche = idcoche;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", edad=" + edad + ", peso=" + peso + ", nombre=" + nombre + ", coche=" + coche
				+ "]";
	}
	
	
	
}
