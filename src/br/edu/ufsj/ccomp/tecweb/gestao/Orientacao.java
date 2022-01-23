package br.edu.ufsj.ccomp.tecweb.gestao;

/**
 * @author Iara Rodrigues;
 */
public class Orientacao extends ProdAcademica{
	private int diaFim = 0;
	private int mesFim = 0;
	private int anoFim = 0;
	
	public Orientacao(String titulo, int diaInicio, int mesInicio, int anoInicio, int diaFim,
			int mesFim, int anoFim) {
		super (titulo, diaInicio, mesInicio, anoInicio);
		if(diaFim>=1 && diaFim<=31) {
			this.diaFim = diaFim;
		}
		if(mesFim>=1 && mesFim<=12) {
			this.mesFim = mesFim;
		}
		if(anoFim>=1900 && anoFim<=2100) {
			this.anoFim = anoFim;	
		}
	}	

	public int getDiaFim() {
		return diaFim;
	}

	public void setDiaFim(int diaFim) {
		this.diaFim = diaFim;
	}

	public int getMesFim() {
		return mesFim;
	}

	public void setMesFim(int mesFim) {
		this.mesFim = mesFim;
	}

	public int getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
	}
	
	public String imprimir() {
		String imprimir = super.imprimirOrie() + 
		//"- Data Início: " + this.diaInicio+"/" + this.mesInicio +"/" + this.anoInicio+"\n"+
		"- Data de Término: "+ this.diaFim+"/" + this.mesFim +
		"/"+this.anoFim+"\n";

		return imprimir;
	}
}

