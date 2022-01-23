package br.edu.ufsj.ccomp.tecweb.gestao;

/**
 * @author Iara Rodrigues;
 */
public class Publicacao extends ProdAcademica{
	private String nomeConf;
	private String pesqAssoc;
	
	public Publicacao(String titulo, String nomeConf, int ano,
			String pesqAssoc) {
		super (titulo, 0, 0, ano);
		this.nomeConf = nomeConf;
		this.pesqAssoc = pesqAssoc;
	}
	
	public String imprimir() {
		String imprimir = super.imprimirPubli() + //super.imprimir()+
		"- Nome Conferência: " + this.nomeConf+"\n"+// "- Ano: "+this.ano+"\n"+
				"- Pesquisa Associada: " + this.pesqAssoc+"\n";

		return imprimir;
	}
}