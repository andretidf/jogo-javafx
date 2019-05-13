package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.Habitat;

public class AnimalDAO {
	List<Animal> lista;

	public static List<Animal> find(Habitat habitat) throws IOException {

		List<Animal> listaAnimaisAquaticos = new ArrayList<>();
		List<Animal> listaAnimaisTerrestres = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader("in.txt"));
		String linha = br.readLine();
		while (linha != null) {
			String[] nomes = new String[3];
			nomes = linha.split(",");
			String nome = nomes[0];
			String caracteristica = nomes[1];
			String habita = nomes[2];
			System.out.println(habita);
			if (habita.contentEquals("AQUATICO")) {
				
				listaAnimaisAquaticos.add(new Animal(nome.toUpperCase(), Habitat.AQUATICO, caracteristica));
			} else {
				listaAnimaisTerrestres.add(new Animal(nome.toUpperCase(), Habitat.TERRESTRE, caracteristica));
			}

			linha = br.readLine();

		}
		br.close();
		if (habitat == Habitat.AQUATICO) {
			return listaAnimaisAquaticos;
		} else {
			return listaAnimaisTerrestres;
		}

	}
	
	public static List<Animal> findAll() throws IOException {

		
		List<Animal> listaAnimais = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader("in.txt"));
		String linha = br.readLine();
		while (linha != null) {
			String[] nomes = new String[3];
			nomes = linha.split(",");
			String nome = nomes[0];
			String caracteristica = nomes[1];
			String habita = nomes[2];
			
			if (habita.contentEquals("AQUATICO")) {
				
				listaAnimais.add(new Animal(nome.toUpperCase(), Habitat.AQUATICO, caracteristica));
			} else {
				listaAnimais.add(new Animal(nome.toUpperCase(), Habitat.TERRESTRE, caracteristica));
			}

			linha = br.readLine();

		}
		br.close();
		return listaAnimais;
	}


	public static void gravarBD(Animal animal) throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter("in.txt", true));

		String[] nomes = new String[3];
		nomes[0] = animal.getNome();
		nomes[1] = animal.getCaracteristica();
		String habitat;
		if (animal.getHabitat() == Habitat.AQUATICO) {
			habitat = "AQUATICO";
		} else {
			habitat = "TERRESTRE";
		}
		nomes[2] = habitat;
		br.newLine();
		String linha = nomes[0] + "," + nomes[1] + "," + nomes[2];
		br.write(linha);
		
		br.close();

	}

	public static void limpaArquivo() throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter("in.txt"));

		String linha = "CACHORRO,E O MELHOR AMIGO DO HOMEM,TERRESTRE";
		String linha1 = "TUBARAO,VIVE NO OCEANO,AQUATICO";
		br.write(linha);
		br.newLine();
		br.write(linha1);
		br.close();

	}
	
	public static void editarUltimo(Animal animalAtualizado) throws IOException {
		
		List<Animal> listaAnimais = findAll();
		int indice = listaAnimais.size() -2 ;
		Animal animal;
		BufferedWriter br = new BufferedWriter(new FileWriter("in.txt"));
		
		for(int i = 0; i<=indice; i++) {
			animal = listaAnimais.get(i);
		
			String[] nomes = new String[3];
			nomes[0] = animal.getNome();
			nomes[1] = animal.getCaracteristica();
			String habitat;
			if (animal.getHabitat() == Habitat.AQUATICO) {
				habitat = "AQUATICO";
			} else {
				habitat = "TERRESTRE";
			}
			nomes[2] = habitat;
			
			String linha = nomes[0] + "," + nomes[1] + "," + nomes[2];
			br.write(linha);
			if(i != indice) {
				br.newLine();
			}
			

		}
		
		br.close();
		gravarBD(animalAtualizado);
	}

}