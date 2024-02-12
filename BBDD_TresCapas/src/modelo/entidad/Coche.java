package modelo.entidad;

import java.util.List;

public class Coche {
	
	private String marca, modelo;
	private int id, añoFab;
	private float kms;
	private List<Pasajero> pasajeros;
	
		
	public List<Pasajero> getPasajeros() {
		return pasajeros;
	}


	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}


	public Coche() {
		super();
	}
	
		
	public Coche(int id, String marca, String modelo, int añoFab, float kms) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.añoFab = añoFab;
		this.kms = kms;
	}


	public int getId() {
		return id;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public int getAñoFab() {
		return añoFab;
	}
	public float getKms() {
		return kms;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setAñoFab(int añoFab) {
		this.añoFab = añoFab;
	}
	public void setKms(float kms) {
		this.kms = kms;
	}
	
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", añoFab=" + añoFab + ", kms=" + kms
				+ "]";
	}
	
	
}
