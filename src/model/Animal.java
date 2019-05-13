package model;

public class Animal {
	
	private String nome;
	private String caracteristica;
	private Habitat habitat;
	
	public Animal() {
		
	}
	
	public Animal(String nome, Habitat habitat) {
		this.nome = nome;
		this.habitat = habitat;
	}
	
	public Animal(String nome, Habitat habitat, String caracteristica) {
		this.nome = nome;
		this.habitat = habitat;
		this.caracteristica = caracteristica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Habitat getHabitat() {
		return habitat;
	}

	public void setHabitat(Habitat habitat) {
		this.habitat = habitat;
	}

	@Override
	public String toString() {
		return "Animal [nome=" + nome + ", caracteristica=" + caracteristica + ", habitat=" + habitat + "]";
	}
	
	
}
