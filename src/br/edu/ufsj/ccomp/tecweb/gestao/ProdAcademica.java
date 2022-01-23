package br.edu.ufsj.ccomp.tecweb.gestao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iara Rodrigues;
 */
public class ProdAcademica {
	private String titulo;
	private List<Colaborador> colab;
	private int diaI = 0, mesI = 0, anoI = 0;
	
	/**
	 * O construtor inicializa o vetor de colaboradores da Produção Acadêmica 
	 * e inicializa o numero de colaborador como 0;
	 * @param titulo
	 */
	public ProdAcademica(String titulo, int diaI, int mesI, int anoI) {
		this.titulo = titulo;
		this.colab = new ArrayList<Colaborador>();
		if(diaI>=1 && diaI<=31) {
			this.diaI = diaI;
		}
		if(mesI>=1 && mesI<=12) {
			this.mesI = mesI;
		}
		if(anoI>=1900 && anoI<=2100) {
			this.anoI = anoI;	
		}

	}
	
	public String imprimir() {
		String imprimir = "Título: " + this.titulo;

		return imprimir;
	}
	
	public String imprimirPubli() {
		String imprimir = "Título: " + this.titulo+"\n";
		for (Colaborador colaborador : colab) {
			imprimir += "- Autor: " + colaborador.imprimirColab()+"\n";
		}
		imprimir += "- Ano de Publicação: " + this.anoI + "\n";
		return imprimir;
	}
	
	public String imprimirOrie() {
		String imprimir = "Título: " + this.titulo+"\n";

		//imprimir += "- Orientador: " + this.colab[0].imprimirColab()+"\n";
		imprimir += "- Orientador: " + this.colab.get(0).imprimirColab()+"\n";
		//imprimir += "- Orientando: " + this.colab[1].imprimirColab()+"\n";
		imprimir += "- Orientando: " + this.colab.get(1).imprimirColab()+"\n";
		imprimir += "- Data Início: " + this.diaI+"/"+ this.mesI +"/"+ this.anoI + "\n";
		return imprimir;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getNumColab() {
		int numColab = this.colab.size();
		return numColab;
		//return this.numColab;
	}
	
	/**Fiz essa função para que eu conseguir acessar os Colaboradores das Produções Acadêmicas e fazer a comparação
	 * para procurar um determinado Colaborar; Como é o caso da opção 7 do Menu Principal;
	 * @param colaborador do tipo Colaborador
	 */
	public void adicionarColaboradorProd(Colaborador colaborador) {
		this.colab.add(colaborador);
	}
	
	public Colaborador editarColaboradorProd(Colaborador orientando, int i, Colaborador colaborador) {
		orientando = this.colab.set(i, colaborador);
		return orientando;
	}
	
	public List<Colaborador> getColaboradorProd() {
		return this.colab;
	}
	
	
	/**
	 * 
	 * @return Int dia
	 */
	public int getDiaProd() {
		return this.diaI;
	}
	
	/**
	 * 
	 * @return Int mes
	 */
	public int getMesProd() {
		return this.mesI;
	}
	
	/**
	 * 
	 * @return Int ano
	 */
	public int getAnoProd() {
		return this.anoI;
	}
	
}
