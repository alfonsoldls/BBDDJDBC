package modelo.entidad;

public class Coche {
	
	private int id;
	private String marca;
	private String modelo;
	private int yearProduccion;
	private int km;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getYearProduccion() {
		return yearProduccion;
	}
	public void setYearProduccion(int yearProduccion) {
		this.yearProduccion = yearProduccion;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	
	public Coche(int id, String marca, String modelo, int yearProduccion, int km) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.yearProduccion = yearProduccion;
		this.km = km;
	}
	
	public Coche() {
		super();
	}
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", yearProduccion=" + yearProduccion
				+ ", km=" + km + "]";
	}
	
}
