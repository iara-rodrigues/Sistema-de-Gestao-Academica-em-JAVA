package br.edu.ufsj.ccomp.tecweb.gestao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.AddPubliException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.EditarPartException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.InfoBasicasProjException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.StatusException;

/**
 * @author Iara Rodrigues;
 */
public class Projeto {
	private List<ProdAcademica> producoesAcad;
	
	private int diaInicio = 0;
	private int mesInicio = 0;
	private int anoInicio = 0;
	private int diaFim = 0;
	private int mesFim= 0;
	private int anoFim = 0;
	
	private int status; //1-Em elaboração; 2-Em andamento; 3-Concluído;
	private String titulo;
	private String agFinan = ""; //agencia financiadora
	private int valorFinan = -1; 
	private String objetivo = "";
	private String descricao = "";
	private int numProjeto;

	private List<Colaborador> participantes;
	
	/**
	 * Esse construtor foi criado para o usuário digitar todos os dados de seu projeto e poder mudar seu status;
	 * Os dias só são aceitos de 1 até 31, os meses de 1 até 12 e os anos de 1900 até 2100;
	 * 
	 * É aqui que eu dou um "throw" no tratamento de erro que me permite apenas adicionar um Projeto quando ele
	 * não possui nenhuma informação vazia;
	 * 
	 * @param diaInicio do tipo int
	 * @param mesInicio do tipo int
	 * @param anoInicio do tipo int
	 * @param diaFim do tipo int
	 * @param mesFim do tipo int
	 * @param anoFim do tipo int
	 * @param titulo do tipo String
	 * @param status do tipo int
	 * @param agFinan do tipo String
	 * @param valorFinan do tipo int
	 * @param objetivo do tipo String
	 * @param descricao do tipo String
	 * @param numProjeto do tipo int
	 * @throws InfoBasicasProjException
	 */
	public Projeto(int diaInicio, int mesInicio, int anoInicio, int diaFim, int mesFim, int anoFim, String titulo,
			int status, String agFinan, int valorFinan, String objetivo, String descricao, int numProjeto)
			throws InfoBasicasProjException{
	
		if(diaInicio>=1 && diaInicio<=31) {
			this.diaInicio = diaInicio;
		} 
		if(mesInicio>=1 && mesInicio<=12) {
			this.mesInicio = mesInicio;
		}
		if(anoInicio>=1900 && anoInicio<=2100) {
			this.anoInicio = anoInicio;	
		}
		
		if(diaFim>=1 && diaFim<=31) {
			this.diaFim = diaFim;
		}
		if(mesFim>=1 && mesFim<=12) {
			this.mesFim = mesFim;
		}
		if(anoFim>=1900 && anoFim<=2100) {
			this.anoFim = anoFim;	
		}
		
		if (titulo.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.titulo = titulo;
		}
		
		if((status>=1) || (status <= 3)) {
			this.status = status;
		} 
		
		if (agFinan.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.agFinan = agFinan;
		}
		
		if (valorFinan < 0) {
			throw new InfoBasicasProjException();
		} else {
			this.valorFinan = valorFinan;
		}
		
		if (objetivo.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.objetivo = objetivo;
		}
		
		if (descricao.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.descricao = descricao;
		}
		
		this.producoesAcad = new ArrayList<ProdAcademica>();
		this.participantes = new ArrayList<Colaborador>();
		this.numProjeto = numProjeto;
	}
	
	/**
	 * Essa função foi implementada com o objetivo de relacionar os Colaboradores ao Projeto em que estão inseridos;
	 * @param colaborador
	 */
	public void adicionarColaborador(Colaborador colaborador) {
			this.participantes.add(colaborador);
	}
	
	/*public void realocarProjeto (int i, Projeto p) {
		List<Projeto> proj = new ArrayList<Projeto>();
		proj.set(i, p);
	}*/
	
	public List<Colaborador> getParticipantes() {
		return this.participantes;
	}
	
	/**
	 * Nessa função eu verifico se o status é Em Elaboração, pois somente assim é permitido editar um participante;
	 * 
	 * @param participantes
	 * @throws EditarPartException
	 */
	public void setParticipantes(List<Colaborador> participantes) throws EditarPartException{
		if (getStatusInt() == 1) {
			this.participantes = participantes;
		} else {
			throw new EditarPartException();
		}
	}
	
	public List<ProdAcademica> getProducoesAcad() {
		return this.producoesAcad;
	}

	public void setProducoesAcad (List<ProdAcademica> producoesAcad) {
		this.producoesAcad = producoesAcad;
	}
	
	/**
	 * Essa função foi implementada com o objetivo de relacionar as Produções academicas com o Projeto em que
	 * ela está inserida, independente se é um Orientação ou Publicação;
	 * @param prodAcad
	 */
	public void adicionarProdAcad(ProdAcademica prodAcad) throws AddPubliException {
		if (prodAcad instanceof Orientacao) {
			this.producoesAcad.add(prodAcad);
		} else {
			if (getStatusInt() == 2) {
				this.producoesAcad.add(prodAcad);
			} else {
				throw new AddPubliException();
			}
		}
	}

	public int getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(int diaInicio) {
		this.diaInicio = diaInicio;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
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

	public int getStatusInt() {
		return status;
	}
	public String getStatusString() {
		if(status == 1) {
			return "Em Elaboração";
		} else {
			if(status == 2) {
				return "Em Andamento";
			} else {
				return "Concluído";
			}
		}
	}

	/**É aqui nessa função em que fiz a chamada para as excessões de alterar o status;
	 * 
	 * Para verificar se um AlunoGrad está em mais de dois Projeto Em Andamento ao mesmo tempo, eu percorro o Array
	 * de participantes desse projeto e percorro o vetor de projetos de cada AlunoGrad deste projeto, se o contador
	 * der maior que 2 ou igual a 2, então não pode mudar o status;
	 * 
	 * E eu também percorro o Array de ProdAcademica para ver se tem alguma instanceof Publicações para poder mudar
	 * o status para Concluído;
	 * 
	 * Essas excessões são tratadas em EditarProjeto() na classe principal;
	 * 
	 * @param status
	 * @throws StatusException
	 */
	public void setStatus(int status) throws StatusException {
		int warning = 1;
		if(status==1) {
			this.status = status;
		} else {
			if (status==2) {
				List<Projeto> proj;
				int contador= 0;
				for (int i = 0; i < getParticipantes().size(); i++) {
					if (getParticipantes().get(i) instanceof AlunoGrad) {
						proj = getParticipantes().get(i).getProjetosEmColab();
						for (int j = 0; j < proj.size(); j++) {
							if (proj.get(j).getStatusInt() == 2) {
								contador++;
							}
							if (contador >= 2) {
								warning = 2;
								break;
							} else {
								warning = 1;
							}
						}
					}
				}
				if(warning == 1) {
					this.status = status;
				} else {
					throw new StatusException();
				}
			} else {
				for (int i = 0; i < producoesAcad.size(); i++) {
					if((status == 3) && (producoesAcad.get(i) instanceof Publicacao) ){
						this.status = status;
						warning = 2;
						break;
					} else {
						warning = 1;
					}
				}
				if(warning == 2) {
					this.status = status;
				} else {
					throw new StatusException();
				}
			}
		}
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) throws InfoBasicasProjException {
		if (titulo.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.titulo = titulo;
		}
	}

	public String getAgFinan() {
		return agFinan;
	}

	public void setAgFinan(String agFinan) throws InfoBasicasProjException {
		if (agFinan.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.agFinan = agFinan;
		}
	}

	public int getValorFinan() {
		return valorFinan;
	}

	public void setValorFinan(int valorFinan) throws InfoBasicasProjException {
		if (valorFinan < 0) {
			throw new InfoBasicasProjException();
		} else {
			this.valorFinan = valorFinan;
		}

	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) throws InfoBasicasProjException {
		if (objetivo.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.objetivo = objetivo;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws InfoBasicasProjException{
		if (descricao.equals("")) {
			throw new InfoBasicasProjException();
		} else {
			this.descricao = descricao;
		}
	}
	
	public int obterNumProjeto() {
		return this.numProjeto;
	}
	
	/**
	 * Essa função foi implementada para imprimir projetos de qualquer status que já possuem todas as informações;
	 * 
	 * @return String
	 */
	public String imprimirProjeto() {
		return "Título: " + this.titulo +"\n"+"- Data Início: " + this.diaInicio+"/"+this.mesInicio+
				"/"+this.anoInicio+"\n"+"- Data Fim: " + this.diaFim+"/"+this.mesFim+
				"/"+this.anoFim+"\n"+"- Status: "+getStatusString()+"\n"+"- Agencia Financiadora: "+ this.agFinan+
				"\n"+"- Valor Financiado: "+this.valorFinan+"\n"+"- Objetivo: "+this.objetivo+"\n"+"- Descrição: "+
				this.descricao + "\n";
		
	}
	
}
