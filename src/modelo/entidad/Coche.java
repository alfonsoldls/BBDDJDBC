package modelo.entidad;

import java.util.Objects;

public class Coche {

	private int id, anioFabricacion;
	private double km;
	private String marca,modelo;
	
	
	
	public Coche() {
		super();
	}


	public Coche(int id, int anioFabricacion, double km, String marca, String modelo) {
		super();
		this.id = id;
		this.anioFabricacion = anioFabricacion;
		this.km = km;
		this.marca = marca;
		this.modelo = modelo;
	}

	
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAnioFabricacion() {
		return anioFabricacion;
	}


	public void setAnioFabricacion(int anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}


	public double getKm() {
		return km;
	}


	public void setKm(double km) {
		this.km = km;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", anioFabricacion=" + anioFabricacion + ", km=" + km + ", marca=" + marca
				+ ", modelo=" + modelo + "]";
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
		Coche other = (Coche) obj;
		return id == other.id;
	}
	
	
	
	
}
