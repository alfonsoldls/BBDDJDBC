package modelo.entidad;

public class Pasajero {

	int id;
	String nombre;
	int edad;
	float peso;
	
	public Pasajero() {
		super();
	}

	
	public Pasajero(int id, String nombre, int edad, float peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public int getEdad() {
		return edad;
	}


	public float getPeso() {
		return peso;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}



	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}



	
}
