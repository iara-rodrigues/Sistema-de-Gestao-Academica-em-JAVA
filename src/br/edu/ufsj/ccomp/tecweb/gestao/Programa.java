package br.edu.ufsj.ccomp.tecweb.gestao;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.AddPubliException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.EditarPartException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.InfoBasicasProjException;
import br.edu.ufsj.ccomp.tecweb.gestao.excecoes.StatusException;
import br.edu.ufsj.ccomp.tecweb.gestao.util.Console;

/**A classe Programa � o Laborat�rio de Pesquisa;
 * @author Iara Rodrigues G. O. Silva
 */
public class Programa {

	private static List<Projeto> projetos = new ArrayList<Projeto>();
	private static int numProjeto = 0;
	private static int numPubli = 0;
	private static int numOrientacao = 0;
	
	private static List<Colaborador> colaboradores = new ArrayList<Colaborador>();

	
	public static void main(String[] args) {		
		
		int opcao = 0;

	    do {
	    	System.out.println("");
	    	System.out.println("************ MENU ************");
	        
	        System.out.println(" 1 - Cadastrar um Projeto");
	        System.out.println(" 2 - Listar Projetos");	
	        System.out.println(" 3 - Editar um Projeto");
	        System.out.println(" 4 - Cadastrar uma Orienta��o");	
	        System.out.println(" 5 - Cadastrar uma Publica��o");
	        System.out.println(" 6 - Imprimir Relat�rio");
	        System.out.println(" 7 - Pesquisar Colaborador");
	        System.out.println(" 30 - Sair");


	        System.out.println("Escolha uma opcao: ");
			opcao = Integer.parseInt(Console.readLine());
			System.out.println("");

		     switch ( opcao ) {
	            case 1 :
	            	System.out.println("Cadastar um Projeto");
	            	cadastrarProjeto();
	                break;	
	            case 2 :
	            	System.out.println("Imprimir um Projeto");
	            	imprimirProjeto();
	                break;
	            case 3 :
	            	System.out.println("Editar um Projeto");
	            	editarProjeto();
	                break;
	            case 4 :
	            	System.out.println("Cadastrar uma Orienta��o");
	            	cadastrarOrientacao();
	                break;
	            case 5 :
	            	System.out.println("Cadastrar uma publica��o");
	            	cadastrarPublicacao();
	                break;
	            case 6 :
	            	System.out.println("Imprimir Relatorio");
	            	imprimirRelatorio();
	                break;
	            case 7 :
	            	System.out.println("Pesquisar Colaborador");
	            	pesquisarColaborador();
	                break;
	        }
			
	    }while (opcao != 30);
	}
	
	/** Essa Fun��o foi implementada para cadastrar os Participantes, ela � chamada em CadastrarProjeto().
	 * 
	 * Primeiro eu verifico se o participante j� existe no vetor 'colaboradores' criado aqui nessa classe,
	 * se j� existir, eu adiciono esse mesmo colaborador no projeto e adiciono esse projeto no colaborador.
	 * Caso o colaborador n�o exista ainda, ent�o eu o crio em sua sub-classe (Professor, AlunoGrad...), e
	 * ent�o adiciono esse projeto nele e o adiciono no Array de colaborador criado aqui tamb�m;
	 * 
	 * Fiz essa rela��o bidirecional entre Projeto e Colaborador para eu poder realizar o tratamento de excess�o
	 * (na classe Projeto) de adicionar um AlunoGrad apenas em dois Projetos Em Andamento ao mesmo tempo.
	 * 
	 * @param projeto
	 */
	public static void cadastrarParticipante(Projeto projeto) {
		System.out.println("Nome do Professor Cordenador: ");
	    String nome = Console.readLine();
	    System.out.println("Email: ");
	    String email = Console.readLine();
	    int warning = 0;
	    
	    Professor professor;
	    for (Colaborador colab : colaboradores) {
	    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Professor) {
	    		projeto.adicionarColaborador(colab);
	    		colab.associarProjeto(projeto);
	    		warning = 1;
	    		break;
	    	}
	    }
	    if (warning == 0) {
	    	professor = new Professor (nome, email);
			professor.associarProjeto(projeto);
			projeto.adicionarColaborador(professor);
			colaboradores.add(professor);
	    }
  
	    System.out.println("Deseja adicionar algum participante? ");
		System.out.println("1- Sim; 2- N�o; ");
		int op = Integer.parseInt(Console.readLine());

	    while (op == 1) {
	    	System.out.println("Nome do participante: ");
		    nome = Console.readLine();
		    System.out.println("Email: ");
		    email = Console.readLine();
		    System.out.println("O participante �: ");
		    System.out.println("1-Aluno de Gradua��o; ");
		    System.out.println("2-Aluno de Mestrado; ");
		    System.out.println("3-Professor; ");
		    System.out.println("4-Pesquisador. ");
		    int tipo = Integer.parseInt(Console.readLine());
		    
		    switch (tipo){
		    case 1:
		    	AlunoGrad alunoGrad;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof AlunoGrad) {
			    		projeto.adicionarColaborador(colab);
			    		colab.associarProjeto(projeto);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	alunoGrad = new AlunoGrad (nome, email);
					alunoGrad.associarProjeto(projeto);
					projeto.adicionarColaborador(alunoGrad);
					colaboradores.add(alunoGrad);
			    }
		    	break;
		    	
		    case 2:
		    	AlunoMest alunoMest;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof AlunoMest) {
			    		projeto.adicionarColaborador(colab);
			    		colab.associarProjeto(projeto);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	alunoMest = new AlunoMest (nome, email);
			    	alunoMest.associarProjeto(projeto);
					projeto.adicionarColaborador(alunoMest);
					colaboradores.add(alunoMest);
			    }
		    	break;
		    	
		    case 3:
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Professor) {
			    		projeto.adicionarColaborador(colab);
			    		colab.associarProjeto(projeto);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	professor = new Professor (nome, email);
					professor.associarProjeto(projeto);
					projeto.adicionarColaborador(professor);
					colaboradores.add(professor);
			    }
		    	break;
		    	
		    case 4:
		    	Pesquisador pesquisador;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Pesquisador) {
			    		projeto.adicionarColaborador(colab);
			    		colab.associarProjeto(projeto);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	pesquisador = new Pesquisador (nome, email);
			    	pesquisador.associarProjeto(projeto);
					projeto.adicionarColaborador(pesquisador);
					colaboradores.add(pesquisador);
			    }
		    	
		    	break;
		    	
		    }
		    System.out.println("Deseja adicionar outro participante: ");
			System.out.println("1- Sim; 2- N�o; ");
			op = Integer.parseInt(Console.readLine());
	    }
	}
	
	/** Essa fun��o foi implementada com o objetivo de cadastrar os Projetos;
	 * 
	 * Nela s�o cadastrados todos os dados do Projeto e ela verifica se h� algum dado vazio, caso tenha,
	 * ela exibir� uma mensagem de erro e n�o adicionar� o Projeto.
	 * 
	 * Aqui eu chamo a fun��o cadastrarParticipante(projeto) para adicionar os Participantes nesse projeto e 
	 * para o projeto ser adicionado em cada participantes, realizando assim uma rela��o bidirecional.
	 */
	public static void cadastrarProjeto() {
		System.out.println("T�tulo: ");
	    String titulo = Console.readLine();
	    System.out.println("Data de In�cio: ");
	    System.out.println("Dia (entre 1 e 31): ");
	    String diaI = Console.readLine();
	    while(Integer.parseInt(diaI) < 1 || Integer.parseInt(diaI) > 31) {
	    	System.out.println("Dia (entre 1 e 31): ");
	    	diaI = Console.readLine();
	    }
	    System.out.println("M�s (entre 1 e 12): ");
	    String mesI = Console.readLine();
	    while(Integer.parseInt(mesI) < 1 || Integer.parseInt(mesI) > 12) {
	    	System.out.println("M�s (entre 1 e 12): ");
		    mesI = Console.readLine();
	    }
	    System.out.println("Ano (entre 1900 e 2100):  ");
	    String anoI = Console.readLine();
	    while(Integer.parseInt(anoI) < 1900 || Integer.parseInt(anoI) > 2100) {
	    	System.out.println("Ano (entre 1900 e 2100):  ");
		    anoI = Console.readLine();
	    }
	    System.out.println("Data de T�rmino: ");
	    System.out.println("Dia (entre 1 e 31): ");
	    String diaF = Console.readLine();
	    while(Integer.parseInt(diaF) < 1 || Integer.parseInt(diaF) > 31) {
	    	System.out.println("Dia (entre 1 e 31): ");
	    	diaF = Console.readLine();
	    }
	    System.out.println("M�s (entre 1 e 12): ");
	    String mesF = Console.readLine();
	    while(Integer.parseInt(mesF) < 1 || Integer.parseInt(mesF) > 12) {
	    	System.out.println("M�s (entre 1 e 12): ");
		    mesF = Console.readLine();
	    }
	    System.out.println("Ano (entre 1900 e 2100):  ");
	    String anoF = Console.readLine();
	    while(Integer.parseInt(anoF) < 1900 || Integer.parseInt(anoF) > 2100 || Integer.parseInt(anoF) < Integer.parseInt(anoI)) {
	    	System.out.println("Ano (entre 1900 e 2100):  ");
		    anoF = Console.readLine();
	    }
	    System.out.println("Agencia Financiadora: ");
	    String agFinan = Console.readLine();
	    
	    System.out.println("Valor financiado: ");
	    String valorFinan = Console.readLine();
	    
	    System.out.println("Objetivo: ");
	    String objetivo = Console.readLine();
	    
	    System.out.println("Descri��o: ");
	    String desc = Console.readLine();
	    
	    try {
	    	Projeto projeto = new Projeto (Integer.parseInt(diaI), Integer.parseInt(mesI),
	    			Integer.parseInt(anoI), Integer.parseInt(diaF), Integer.parseInt(mesF),
	    			Integer.parseInt(anoF), titulo, 1, agFinan, Integer.parseInt(valorFinan),
	    			objetivo, desc, numProjeto-1);
		    	    
		    projetos.add(projeto);
		    
		    cadastrarParticipante(projeto);
		    
	    } catch (InfoBasicasProjException ibpe) {
	    	System.out.println("FALHA: Algumas informa��es importantes do Projeto est�o vazias.");
	    } 
	}
	
	/** Essa fun��o foi implementada com o objetivo de imprimir os projetos;
	 * 
	 * Primeiro � pedido o nome do projeto que deseja imprimir, o c�digo faz a busca em todo o vetor de projetos e,
	 * quando encontra, essa fun��o imprime na tela os dados do projeto;
	 * 
	 * Posteriormente � conferido se possui alguma Produ��o Academica, se sim, a fun��o mostra um MENU para que o 
	 * usu�rio escolha a maneira que ele deseja imprimir:
	 * - Primeira Maneira: � impresso na tela as Orienta��es e Publica��es separadamente;
	 * - Segunda Maneira: � impresso na tela as Orienta��es e Publica��es juntas ordenadas de maneira descrescente
	 *  de Data, usando a fun��o bubbleSortProd(), apresentada mais abaixo;
	 */
	public static void imprimirProjeto() {
		System.out.println("Digite o nome do projeto: ");
		String titulo = Console.readLine();
		System.out.println("");
		for (Projeto projeto : projetos) {
			if(projeto.getTitulo().equalsIgnoreCase(titulo)) {
				System.out.println(projeto.imprimirProjeto());

				List<Colaborador> participantes = new ArrayList<Colaborador>();
				participantes= projeto.getParticipantes();
				
				System.out.println("Coordenador e Participantes: ");
				for (Colaborador colaborador : participantes) {
					System.out.println (colaborador.imprimirColab());
				}
				
				
				List<ProdAcademica> prodAcads;
				int numProdAcad = projeto.getProducoesAcad().size();
				
				System.out.println("");
				if (numProdAcad > 0) {
					System.out.println("Deseja imprimir: ");
					System.out.println("1- Orienta��o e Publica��es separados");
					System.out.println("2- Orienta��o e Publica��es juntos ordenados de forma decrescente de data");
					int op = Integer.parseInt(Console.readLine());
					prodAcads = projeto.getProducoesAcad();
					switch (op) {
					case 1: 
						//Orienta��es
						System.out.println("");
						System.out.println("Orienta��es neste projeto: ");
						for (ProdAcademica prodAcad : prodAcads) {
							if(prodAcad instanceof Orientacao) {
								System.out.println(prodAcad.imprimir());
							}
						}
						
						//Publica��es
						System.out.println("");
						System.out.println("Publica��es neste projeto: ");
						for (ProdAcademica prodAcad : prodAcads) {
							if(prodAcad instanceof Publicacao) {
								System.out.println(prodAcad.imprimir());
							}
						}
						break;
					case 2:
						for (int h = 0; h < projeto.getProducoesAcad().size(); h++) {
							System.out.println("");
							System.out.println(bubbleSortProd(prodAcads, numProdAcad).get(h).imprimir());
						}
						break;
					}
				}
			}
		}
	}
	
	/** Essa fun��o foi criada para n�o deixar a fun��o editarProjeto() muito lotada, at� porque s� � poss�vel
	 * editar os participantes de um Projeto se seu status estiver Em Elabora��o;
	 *  
	 * Nessa fun��o eu come�o percorrendo o vetor de participantes do Projeto e desassocio esse projeto de cada
	 * participante, fa�o isso usando a fun��o desassociaProjeto(projeto) da classe Colaborador. Caso esse 
	 * colaborador tenha apenas esse projeto associado a ele, ent�o eu o removo do Array de colaboradores criado
	 * aqui nessa classe. At� pq n�o preciso deix�-lo adicionado se ele n�o pertence � Projeto nenhum.
	 * 
	 * Ap�s feito isso, eu realizo praticamente as mesmas opera��es do cadastrarParticipante, mas fazendo o 
	 * tratamento da excess�o EditarPartException(), que s� me permite editar os participantes caso o Status do 
	 * Projeto == 1. Nunca cai na mensagem de FALHA, pois essa fun��o s� � chamada quando o status==1;
	 * 
	 * @param i = n�mero do projeto que est� sendo editado; 
	 */
	public static void editarParticipantesProj(int i) {
		List<Colaborador> participante = new ArrayList<Colaborador>();
		Projeto proj = projetos.get(i);
		
		for(int x = 0; x < proj.getParticipantes().size(); x++) {
			if (proj.getParticipantes().get(x).getProjetosEmColab().size() > 1) {
				proj.getParticipantes().get(x).desassociarProjeto(proj);
			} else {
				colaboradores.remove(proj.getParticipantes().get(x));
			}
		}
		
		System.out.println("Nome do Professor Cordenador: ");
		String nome = Console.readLine();
		System.out.println("Email: ");
		String email = Console.readLine();
		int warning = 0;
		
		Professor professor;
	    for (Colaborador colab : colaboradores) {
	    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Professor) {
	    		proj.adicionarColaborador(colab);
	    		colab.associarProjeto(proj);
	    		participante.add(colab);
	    		warning = 1;
	    		break;
	    	}
	    }
	    if (warning == 0) {
	    	professor = new Professor (nome, email);
	    	//professor.setEmail(email);
			//professor.setNome(nome);
			professor.associarProjeto(proj);
			proj.adicionarColaborador(professor);
			colaboradores.add(professor);
			participante.add(professor);
	    }		
		
		System.out.println("Deseja adicionar outro participante: ");
		System.out.println("1- Sim; 2- N�o; ");
		int op = Integer.parseInt(Console.readLine());
		
		while (op == 1) {
		System.out.println("Nome do participante: ");
		nome = Console.readLine();
		System.out.println("Email: ");
		email = Console.readLine();
		System.out.println("O participante �: ");
		System.out.println("1-Aluno de Gradua��o; ");
		System.out.println("2-Aluno de Mestrado; ");
		System.out.println("3-Professor; ");
		System.out.println("4-Pesquisador. ");
		int tipo = Integer.parseInt(Console.readLine());
			
		switch (tipo){
			case 1:
				AlunoGrad alunoGrad;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof AlunoGrad) {
			    		proj.adicionarColaborador(colab);
			    		colab.associarProjeto(proj);
			    		participante.add(colab);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	alunoGrad = new AlunoGrad (nome, email);
			    	//alunoGrad.setEmail(email);
					//alunoGrad.setNome(nome);
					alunoGrad.associarProjeto(proj);
					proj.adicionarColaborador(alunoGrad);
					colaboradores.add(alunoGrad);
					participante.add(alunoGrad);
			    }
			    break;
			    
		    case 2:
		    	AlunoMest alunoMest;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof AlunoMest) {
			    		proj.adicionarColaborador(colab);
			    		colab.associarProjeto(proj);
			    		participante.add(colab);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	alunoMest = new AlunoMest (nome, email);
			    	//alunoMest.setEmail(email);
					//alunoMest.setNome(nome);
					alunoMest.associarProjeto(proj);
					proj.adicionarColaborador(alunoMest);
					colaboradores.add(alunoMest);
					participante.add(alunoMest);
			    }
			    break;
			    
		    case 3:
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Professor) {
			    		proj.adicionarColaborador(colab);
			    		colab.associarProjeto(proj);
			    		participante.add(colab);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	professor = new Professor (nome, email);
			    	//professor.setEmail(email);
					//professor.setNome(nome);
					professor.associarProjeto(proj);
					proj.adicionarColaborador(professor);
					colaboradores.add(professor);
					participante.add(professor);
			    }
			    break;
			    
		    case 4:
			    Pesquisador pesquisador;
			    for (Colaborador colab : colaboradores) {
			    	if ((email.equalsIgnoreCase(colab.getEmail())) && colab instanceof Pesquisador) {
			    		proj.adicionarColaborador(colab);
			    		colab.associarProjeto(proj);
			    		participante.add(colab);
			    		warning = 1;
			    		break;
			    	}
			    }
			    if (warning == 0) {
			    	pesquisador = new Pesquisador (nome, email);
			    	//pesquisador.setEmail(email);
					//pesquisador.setNome(nome);
					pesquisador.associarProjeto(proj);
					proj.adicionarColaborador(pesquisador);
					colaboradores.add(pesquisador);
					participante.add(pesquisador);
			    }
			    break;
		    } 
		System.out.println("Deseja adicionar outro participante: ");
		System.out.println("1- Sim; 2- N�o; ");
		op = Integer.parseInt(Console.readLine());
	    }
		try {
			proj.setParticipantes(participante);
		} catch (EditarPartException epe){
			System.out.println("FALHA: O projeto n�o est� Em Elabora��o, logo n�o � poss�vel editar os Participantes.");
		}
		
	}
	
	/**
	 * Essa fun��o foi feita para que seja poss�vel a edi��o de projetos;
	 * 
	 * Nela � pedido o nome do projeto que deseja editar e ent�o abre um MENU que permite alterar todos os dados
	 * do projeto;
	 * 
	 * Nessa fun��o eu usei as fun��es set da classe Projeto para apenas alterar os valores dos dados desse
	 * projeto ao inv�s de ter que apag�-lo e criar outro;
	 *	
	 * Caso queira alterar o Status, � feita o tratamento da Excess�o StatusException():
	 * -Se o status for mudado para 2 � preciso verificar se possui algum AlunoGrad em mais de 2 projetos Em 
	 * Andamento ao mesmo tempo, caso possua, o projeto continuar� Em Elabora��o;
	 * -Se o status for mudado para 3 � preciso verificar se o Projeto possui alguma publica��o, caso n�o possua,
	 * o projeto continuar� Em Andamento;
	 * Somente depois de verificar tudo isso, o Status do projeto � de fato settado em Projetos;
	 */
	public static void editarProjeto() {
		System.out.println("Digite o nome do projeto: ");
		String titulo = Console.readLine();
		System.out.println("");
		
		for (Projeto projeto : projetos) {
			if(projeto.getTitulo().equalsIgnoreCase(titulo)) {
				if(projeto.getStatusInt() == 3) {
					System.out.println("O projeto j� foi Conclu�do.");
				} else {
					int opcao = 0; 
					while (opcao != 10) {
						System.out.println("********Deseja editar: ******** ");
						System.out.println(" 1 - Titulo");
						System.out.println(" 2 - Data de Inicio");
						System.out.println(" 3 - Data de T�rmino");
						System.out.println(" 4 - Agencia Financiadora");
						System.out.println(" 5 - Valor financiado");
						System.out.println(" 6 - Objetivo");
						System.out.println(" 7 - Descri��o");
						System.out.println(" 8 - Status ou Participantes");
						System.out.println("10 - SAIR");
						opcao = Integer.parseInt(Console.readLine());
						
						switch (opcao) {
						
							case 1:
								System.out.println("Titulo novo ou igual: ");
							    String tit = Console.readLine();
							    try {
							    	 projeto.setTitulo(tit);
							    } catch (InfoBasicasProjException ibpe) {
							    	System.out.println("FALHA: N�o � poss�vel atualizar o T�tulo do Projeto por um campo vazio.");
							    }
							    break;
							
							case 2:	
								System.out.println("Data de Inicio: ");
								System.out.println("Dia (entre 1 e 31): ");
							    String diaI = Console.readLine();
							    while(Integer.parseInt(diaI) < 1 || Integer.parseInt(diaI) > 31) {
							    	System.out.println("Dia (entre 1 e 31): ");
							    	diaI = Console.readLine();
							    }
							    System.out.println("M�s (entre 1 e 12): ");
							    String mesI = Console.readLine();
							    while(Integer.parseInt(mesI) < 1 || Integer.parseInt(mesI) > 12) {
							    	System.out.println("M�s (entre 1 e 12): ");
								    mesI = Console.readLine();
							    }
							    System.out.println("Ano (entre 1900 e 2100):  ");
							    String anoI = Console.readLine();
							    while(Integer.parseInt(anoI) < 1900 || Integer.parseInt(anoI) > 2100) {
							    	System.out.println("Ano (entre 1900 e 2100):  ");
								    anoI = Console.readLine();
							    }
							    projeto.setDiaInicio(Integer.parseInt(diaI));
						    	projeto.setMesInicio(Integer.parseInt(mesI));
						    	projeto.setAnoInicio(Integer.parseInt(anoI));
						    	break;
						    	
							case 3:
						    	System.out.println("Data de T�rmino: ");
						    	System.out.println("Dia (entre 1 e 31): ");
							    String diaF = Console.readLine();
							    while(Integer.parseInt(diaF) < 1 || Integer.parseInt(diaF) > 31) {
							    	System.out.println("Dia (entre 1 e 31): ");
							    	diaF = Console.readLine();
							    }
							    System.out.println("M�s (entre 1 e 12): ");
							    String mesF = Console.readLine();
							    while(Integer.parseInt(mesF) < 1 || Integer.parseInt(mesF) > 12) {
							    	System.out.println("M�s (entre 1 e 12): ");
								    mesF = Console.readLine();
							    }
							    System.out.println("Ano (entre 1900 e 2100):  ");
							    String anoF = Console.readLine();
							    while(Integer.parseInt(anoF) < 1900 || Integer.parseInt(anoF) > 2100 || Integer.parseInt(anoF) < projeto.getAnoInicio() ) {
							    	System.out.println("Ano (entre 1900 e 2100):  ");
								    anoF = Console.readLine();
							    }
							    projeto.setDiaFim(Integer.parseInt(diaF));
						    	projeto.setMesFim(Integer.parseInt(mesF));
						    	projeto.setAnoFim(Integer.parseInt(anoF));
						    	break;
						    	
							case 4: 
							    System.out.println("Agencia Financiadora: ");
							    String agFinan = Console.readLine();
							    try {
							    	 projeto.setAgFinan(agFinan);
							    } catch (InfoBasicasProjException ibpe) {
							    	System.out.println("FALHA: N�o � poss�vel atualizar a Agencia Financiadora do Projeto por um campo vazio.");
							    }
							    break;
						    
							case 5:
							    System.out.println("Valor financiado: ");
							    String valorFinan = Console.readLine();
							    try {
							    	 projeto.setValorFinan(Integer.parseInt(valorFinan));
							    } catch (InfoBasicasProjException ibpe) {
							    	System.out.println("FALHA: N�o � poss�vel atualizar o Valor Financiado do Projeto por um campo vazio.");
							    }
							    break;
						   
							case 6:
							    System.out.println("Objetivo: ");
							    String objetivo = Console.readLine();
							    try {
							    	projeto.setObjetivo(objetivo);
							    } catch (InfoBasicasProjException ibpe) {
							    	System.out.println("FALHA: N�o � poss�vel atualizar o Objetivo do Projeto por um campo vazio.");
							    }
							    break;
						    
							case 7:
							    System.out.println("Descri��o: ");
							    String desc = Console.readLine();
							    try {
							    	 projeto.setDescricao(desc);
							    } catch (InfoBasicasProjException ibpe) {
							    	System.out.println("FALHA: N�o � poss�vel atualizar a Descri��o do Projeto por um campo vazio.");
							    }
							    break;
							    
							case 8:
							    int obterStatus = projeto.getStatusInt();
							    int opStatus, status = obterStatus;
						    	
							    System.out.println("Deseja manter os mesmos participantes e cordenador?");
							    System.out.println("1-Sim, 2-N�o");
							    int op = Integer.parseInt(Console.readLine());
							    if(op==2) {
							    	editarParticipantesProj(projetos.indexOf(projeto)); //passa o index desse projeto
							    }
						     
							    switch (obterStatus) {
								    case 1:
								    	System.out.println("Deseja mudar o status de Em Elabora��o para Em Andamento? ");
								    	System.out.println("1-Sim; 2-N�o; ");
								    	opStatus = Integer.parseInt(Console.readLine());
								    	if(opStatus == 1) {
								    		status = 2;
								    	} else {
								    		status = 1;
								    	}
								    	break;
								    case 2: 
								    	System.out.println("Deseja mudar o status de Em Andamento para Conclu�do? ");
								    	System.out.println("1-Sim; 2-N�o; ");
								    	opStatus = Integer.parseInt(Console.readLine());
								    	if(opStatus == 1) {
								    		status = 3;
								    	} else {
								    		status = 2;
								    	}	
								    	break;
							    }
			
							    try {
					    			projeto.setStatus(status);
					    		} catch (StatusException se) {
					    			if (status == 3) {
					    				System.out.println("FALHA: O projeto "+ projeto.getTitulo()+" n�o possui nenhuma Publica��o. Logo n�o pode ser Conclu�do.");
					    			} else {
					    				System.out.println("FALHA: Possui algum Aluno de Gradua��o participante desse Projeto que j� est� em mais de 2 Projetos Em Andamento.");
					    			}
					    		}
							}
					}
				}
			}
		}
	}
	
	/**Nessa fun��o, dado o nome de um projeto existente,� inserido todos os dados da Orienta��o, alguns desses
	 * dados s�o inseridos em Orienta��o e outros em ProdAcademica, escolhi fazer assim para poder compar�-los
	 * mais facilmente com os dados das Publica��es;
	 * 
	 * Se o Orientador ou Orientando n�o forem Professor e Aluno, respectivamente, ou n�o possuir participantes
	 * com esses nomes no Projeto escolhido, a fun��o n�o cria uma nova Orienta��o;
	 */
	public static void cadastrarOrientacao() {
		System.out.println("Digite o nome do projeto: ");
		String tituloProcurado = Console.readLine();
		System.out.println("");
		for (Projeto projeto : projetos) {
			if(projeto.getTitulo().equalsIgnoreCase(tituloProcurado)) {
				System.out.println("T�tulo da Orienta��o: ");
			    String titulo = Console.readLine();
			    System.out.println("Data de In�cio: ");
			    System.out.println("Dia (entre 1 e 31): ");
			    String diaI = Console.readLine();
			    while(Integer.parseInt(diaI) < 1 || Integer.parseInt(diaI) > 31) {
			    	System.out.println("Dia (entre 1 e 31): ");
			    	diaI = Console.readLine();
			    }
			    System.out.println("M�s (entre 1 e 12): ");
			    String mesI = Console.readLine();
			    while(Integer.parseInt(mesI) < 1 || Integer.parseInt(mesI) > 12) {
			    	System.out.println("M�s (entre 1 e 12): ");
				    mesI = Console.readLine();
			    }
			    System.out.println("Ano (entre 1900 e 2100):  ");
			    String anoI = Console.readLine();
			    while(Integer.parseInt(anoI) < 1900 || Integer.parseInt(anoI) > 2100) {
			    	System.out.println("Ano (entre 1900 e 2100):  ");
				    anoI = Console.readLine();
			    }
			    System.out.println("Data de T�rmino: ");
			    System.out.println("Dia (entre 1 e 31): ");
			    String diaF = Console.readLine();
			    while(Integer.parseInt(diaF) < 1 || Integer.parseInt(diaF) > 31) {
			    	System.out.println("Dia (entre 1 e 31): ");
			    	diaF = Console.readLine();
			    }
			    System.out.println("M�s (entre 1 e 12): ");
			    String mesF = Console.readLine();
			    while(Integer.parseInt(mesF) < 1 || Integer.parseInt(mesF) > 12) {
			    	System.out.println("M�s (entre 1 e 12): ");
				    mesF = Console.readLine();
			    }
			    System.out.println("Ano (entre 1900 e 2100):  ");
			    String anoF = Console.readLine();
			    while(Integer.parseInt(anoF) < 1900 || Integer.parseInt(anoF) > 2100 || Integer.parseInt(anoF) < Integer.parseInt(anoI)) {
			    	System.out.println("Ano (entre 1900 e 2100):  ");
				    anoF = Console.readLine();
			    }
			    
			    List<Colaborador> participante = projeto.getParticipantes();		   
			    
			    Orientacao orientacao = new Orientacao (titulo, Integer.parseInt(diaI), Integer.parseInt(mesI), Integer.parseInt(anoI),
			    		Integer.parseInt(diaF), Integer.parseInt(mesF), Integer.parseInt(anoF));
			    
		    	
			    int warning = 0;
			    int add = 0;
			    
			    System.out.println("Orientador: ");
			    String nomeOrientador = Console.readLine();
			    for (int j = 0; j < projeto.getParticipantes().size(); j++) {
			    	if(participante.get(j) instanceof Professor) {
			    		if((nomeOrientador.equalsIgnoreCase(participante.get(j).getNome()))) {
				    		orientacao.adicionarColaboradorProd(participante.get(j));
				    		warning = 0;
				    		break;
			    			
					    } else {
					    	warning = 1;
					    }
			    	}
			    }
			    
			    if (warning == 1) {
			    	System.out.println("FALHA: O Orientador digitado n�o est� neste projeto ou n�o � um Professor. ");
			    	add = 1;
			    }
			    
			    System.out.println("Orientando: ");
			    String nomeOrientando = Console.readLine();
			    
			    for (int j = 0; j < projeto.getParticipantes().size(); j++) {
			    	if((participante.get(j) instanceof AlunoGrad)||(participante.get(j) instanceof AlunoMest)) {
			    		if((nomeOrientando.equalsIgnoreCase(participante.get(j).getNome()))) {
			    			orientacao.adicionarColaboradorProd(participante.get(j));
				    		warning = 0;
				    		break;
			    			
					    } else {
					    	warning = 1;
					    }
			    	} else {
				    	warning = 2;
				    }
			    }
			    if (warning == 1) {
			    	System.out.println("FALHA: N�o possui aluno com esse nome neste projeto. ");
			    	add = 1;
			    } else {
			    	if (warning == 2) {
			    		System.out.println("FALHA: N�o possui alunos neste projeto. ");
			    		add = 1;
			    	}
			    }
			    
			  
			    if (add == 0) {
			    	try {
			    		projeto.adicionarProdAcad(orientacao);
			    		numOrientacao++;
			    	} catch (AddPubliException p) {
			    		System.out.println("FALHA: Ocorreu um erro inesperado. ");
			    	}
			    } else {
			    	System.out.println("FALHA: A orienta��o n�o pode ser criada. ");
			    }
			}
		}
	}
	
	/**Nessa fun��o, dado o nome de um projeto existente,� inserido todos os dados da Publica��o, alguns desses
	 * dados s�o inseridos em Orienta��o e outros em ProdAcademica, escolhi fazer assim para poder compar�-los
	 * mais facilmente com os dados das Orienta��es;
	 * 
	 * Se o nome de algum autor n�o existir no Projeto, a fun��o n�o cria uma nova Publica��o; 
	 */
	public static void cadastrarPublicacao() {
		System.out.println("Digite o nome do projeto: ");
		String tituloProcurado = Console.readLine();
		System.out.println("");
		for (Projeto projeto : projetos) {
			if(projeto.getTitulo().equalsIgnoreCase(tituloProcurado)) {
				System.out.println("T�tulo da Publica��o: ");
				String titulo = Console.readLine();
				System.out.println("Nome da Confer�ncia onde foi publicada: ");
				String nomeConf = Console.readLine();
				System.out.println("Ano de Publica��o (entre 1900 e 2100):  ");
				String ano = Console.readLine();
				while(Integer.parseInt(ano) < 1900 || Integer.parseInt(ano) > 2100) {
				    System.out.println("Ano (entre 1900 e 2100):  ");
					ano = Console.readLine();
				}
			    System.out.println("Projeto de Pesquisa Associado: ");
				String pesqAssociada = Console.readLine();
    
				List<Colaborador> participante = projeto.getParticipantes();
				String nomeAutor; 
				    
				Publicacao publicacao = new Publicacao (titulo, nomeConf, Integer.parseInt(ano),
				    		pesqAssociada);
				    
	
			    int warning = 0;
				    
				int op = 1;
				while (op == 1) {
					System.out.println("Autor: ");
					nomeAutor = Console.readLine();
					for(int h = 0; h < projeto.getParticipantes().size(); h++) {
						if(nomeAutor.equalsIgnoreCase(participante.get(h).getNome())) {
							if(participante.get(h) instanceof AlunoGrad) {					    		
					    		AlunoGrad alunoGrad = new AlunoGrad (participante.get(h).getNome(), participante.get(h).getEmail());
					    		publicacao.adicionarColaboradorProd(alunoGrad);
						    	warning = 0; 
						    	break;
					    	} else {
					    		if(participante.get(h) instanceof AlunoMest) {
						    		AlunoMest alunoMest = new AlunoMest (participante.get(h).getNome(), participante.get(h).getEmail());
							    	publicacao.adicionarColaboradorProd(alunoMest);
							    	warning = 0; 
							    	break;
					    		} else {
					    			if(participante.get(h) instanceof Professor) { 
							    		Professor professor = new Professor (participante.get(h).getNome(), participante.get(h).getEmail());
								    	publicacao.adicionarColaboradorProd(professor);
								    	warning = 0; 
								    	break;
					    			} else {
					    				if(participante.get(h) instanceof Pesquisador) {
								    		Pesquisador pesquisador = new Pesquisador (participante.get(h).getNome(), participante.get(h).getEmail());
									    	publicacao.adicionarColaboradorProd(pesquisador);
									    	warning = 0; 
									    	break;
					    				}
					    			}
					    		}
					    	}
					    } else {
					    	warning = 1;
					    }
					}
					System.out.println("Deseja adicionar mais um autor? ");
					System.out.println("1- Sim; 2- N�o; ");
					op = Integer.parseInt(Console.readLine());
				}
				if (warning == 1) {
				    System.out.println("FALHA: O autor digitado n�o est� neste projeto.");
				} else {
					try {
				    	projeto.adicionarProdAcad(publicacao);
				    	numPubli++;
				    } catch (AddPubliException p){
				    	System.out.println("FALHA: O status do projeto precisa ser Em Andamento.");
				    }   
				}
			}
		}
	}
	
	/** Essa fun��o foi implementada pra realizar a op��o 6 do Menu Principal; 
	 * 
	 * No item:
	 * i. Antes era chamada a fun��o contarColaborador(), mas como agora eu criei um Array de colaboradores nessa
	 * classe e apago os colaboradores que n�o est�o associados � Projeto nenhum, passei apenas a imprimir o 
	 * size desse Array;
	 * ii. percorre todos os projetos e vai somando +1 ao contador criado quando o status do projeto for igual a 1;
	 * iii. feito da mesma maneira do item ii mas observando se o status � igual a 2; 
	 * iv. feito da mesma maneira do item ii mas observando se o status � igual a 3;
	 * v. imprime o valor da variavel global numProjeto (ela � incrementada toda vez que adiciona um novo projeto);
	 * vi. imprime o valor da variavel global numOrie e numPubli (elas s�o incrementadas toda vez que adiciona uma
	 * novo orienta��o ou publica��o, respectivamente);
	 */
	public static void imprimirRelatorio() {
		//Imprimindo:
		//i. N�mero de colaboradores 
		System.out.println("N�mero total de Colaboradores: " + colaboradores.size());
		//System.out.println("N�mero total de Colaboradores: "+ contarColaborador());
		
		//ii. N�mero de projetos em elabora��o 
		int numProjetoElab = 0;
		for (Projeto projeto : projetos) {
			if (projeto.getStatusInt() == 1) {
				numProjetoElab ++;
			}
		}
		System.out.println("N�mero total de Projetos Em Elabora��o: "+ numProjetoElab);
		
		//iii. N�mero de projetos em andamento
		int numProjetoAnd = 0;
		for (Projeto projeto : projetos) {
			if (projeto.getStatusInt() == 2) {
				numProjetoAnd ++;
			}
		}
		System.out.println("N�mero total de Projetos Em Andamento: "+ numProjetoAnd);
		
		//iv. N�mero de projetos conclu�dos 
		int numProjetoConcluido = 0;
		for (Projeto projeto : projetos) {
			if (projeto.getStatusInt() == 3) {
				numProjetoConcluido ++;
			}
		}
		System.out.println("N�mero total de Projetos Conclu�dos: "+ numProjetoConcluido);
		
		//v. N�mero total de projetos 
		System.out.println("N�mero total de Projetos: "+ projetos.size());
		
		//vi. N�mero de produ��o acad�mica por tipo de produ��o (orienta��o e publica��o)
		System.out.println("N�mero total de Orienta��es: "+ numOrientacao);
		System.out.println("N�mero total de Publicacoes: "+ numPubli);
 
	}
	
	/**Essa fun��o serve para, dado um nome de um colaborador, ela imprime:
	 * 1) todos os dados dessa pessoa (nome e emial);
	 * 2) todos os projetos relacionados a ela, com os projetos Em Andamento ordenados de maneira decrescente 
	 * pela data de t�rmino com o uso da fun��o bubbleSortProjeto(array dos projetos em andamento, tamanho dele);
	 * 3) e todas as produ��es academicas ordenadas decrescentemente;
	 * 
	 * Com a refatorada que dei no c�digo, facilitou essas impress�es, agora eu basicamente pego o size das coisas
	 * que quero e j� chamo o fun��o de imprimir;
	 * 
	 * Como eu n�o refatorei o bubleSort, eu precisei criar um outro array para pegar apenas as coisas espec�ficas
	 * que eu queria para poder orden�-las e imprim�-las; 
	 */
	public static void pesquisarColaborador() {
		System.out.println("Digite o nome do colaborador: ");
		System.out.println("");
		String nomeProcurado = Console.readLine();
		List<ProdAcademica> prodAcad = new ArrayList<ProdAcademica>();
		List<Projeto> projetoColabAnd = new ArrayList<Projeto>();
		
		for(Colaborador colab : colaboradores) {
			if (nomeProcurado.equalsIgnoreCase(colab.getNome())) {
				System.out.println(colab.imprimirColab());
				System.out.println("");
				System.out.println("Projetos Em Elabora��o e Conclu�dos:\n");
				
				//Imprimindo os projetos Em Elabora��o
				for (int h = 0; h < colab.getProjetosEmColab().size() ; h++) {
					if(colab.getProjetosEmColab().get(h).getStatusInt() == 1){
							System.out.println(colab.getProjetosEmColab().get(h).imprimirProjeto());
					}
				}
				
				//Imprimindo os projetos Conclu�do
				for (int h = 0; h < colab.getProjetosEmColab().size(); h++) {
					if(colab.getProjetosEmColab().get(h).getStatusInt() == 3){
						System.out.println(colab.getProjetosEmColab().get(h).imprimirProjeto());
					}
				}	
				
				//Imprimindo os projetos Em Andamento
				System.out.println("Projetos Em Andamento (ordenados decrescentemente pela data de t�rmino): ");
				int contAnd = 0;
				for (int h = 0; h < colab.getProjetosEmColab().size(); h++) {
					if( colab.getProjetosEmColab().get(h).getStatusInt() == 2){
						projetoColabAnd.add(colab.getProjetosEmColab().get(h));
						contAnd++;
					}
				}
				for (int h = 0; h < contAnd; h++) {
					System.out.println(bubbleSortProjeto(projetoColabAnd, contAnd).get(h).imprimirProjeto());
				}
				
				//Imprimindo Prod Academicas
				int contProd = 0;
				for (int x = 0; x < colab.getProjetosEmColab().size(); x++) {
					if (colab.getProjetosEmColab().get(x).getProducoesAcad().size() > 0) {
						System.out.println("");
						System.out.println("Produ�oes academicas ordenadas de forma decrescente de data (t�rmino): ");
						for(int z = 0; z < colab.getProjetosEmColab().get(x).getProducoesAcad().size(); z++) {
							for (int y = 0; y < colab.getProjetosEmColab().get(x).getProducoesAcad().get(z).getColaboradorProd().size(); y++) {
								if (nomeProcurado.equalsIgnoreCase(colab.getProjetosEmColab().get(x).getProducoesAcad().get(z).getColaboradorProd().get(y).getNome())){
									prodAcad.add(colab.getProjetosEmColab().get(x).getProducoesAcad().get(z));
									contProd++;
								}	
							}
						}
					}	
				}
				for (int h = 0; h < contProd; h++) {
					System.out.println(bubbleSortProd(prodAcad, contProd).get(h).imprimir());
				}
			}
		}
	}

	
	/** Essa fun��o foi implementada para ordenar os Projetos associados a um Colaborador 
	 * quando for pesquisar por nome de Colaborador - Op��o 7 do Menu Principal;
	 * Escolhi implementar um bubbleSort que compara os anos, meses e dias da data e ordena
	 * o novo vetor da ordem do ultimo projeto a acabar at� o mais proximo de acabar;
	 * 
	 * @param array do tipo Projeto[], ser� passado nesse par�metro o array de projetos em 
	 * andamento para que ele seja ordenado;
	 * @param contProj do tipo int, esse � o total de elementos do array passado;
	 * @return array[contProj] j� ordenado decrescentemente;
	 */
	public static List<Projeto> bubbleSortProjeto(List<Projeto> array, int contProj) {  
		 Projeto temp; 
		 for (int i = 1; i < contProj; i++) {
			    for (int j = 0; j < i; j++) {
			        if (array.get(i).getAnoFim() > array.get(j).getAnoFim()) {
			            temp = array.get(i);
			            array.set(i, array.get(j));
			            array.set(j, temp);
			        } else {
			        	if (array.get(i).getAnoFim() == array.get(j).getAnoFim()) {
			        		if (array.get(i).getMesFim() > array.get(j).getMesFim()) {
			        			temp = array.get(i);
					            array.set(i, array.get(j));
					            array.set(j, temp);
			        		} else {
			        			if (array.get(i).getMesFim() == array.get(j).getMesFim()) {
			        				if (array.get(i).getDiaFim() > array.get(j).getDiaFim()) {
			        					temp = array.get(i);
							            array.set(i, array.get(j));
							            array.set(j, temp);
			        				}
			        			}
			        		}
			        	}
			        }
			    }
			}
	        return array;
	    }  

	/**Essa fun��o foi implementada para ordenar as Produ��es Academicas associadas ao Projeto 
	 * que deseja imprimir - Op��o 7 e 2 do Menu Principal;
	 * Escolhi implementar um bubbleSort que compara os anos, meses e dias da data e ordena
	 * o novo vetor da ordem da ultima produ��o academica a acabar at� a mais proximo de acabar, 
	 * independente se for Orienta��o ou Publica��o, o que muda � que Publica��o recebe como dia e m�s
	 * valores iguais a zero; 
	 * 
	 * @param array do tipo ProdAcademica[], ser� passado nesse par�metro o array contendo
	 * todas as produ��es Academicas do projeto (no caso da op��o 2 do menu) ou
	 * contendo toda a produ��o relacionada ao Colaborador pesquisado (no caso da op��o 7 do menu);
	 * andamento para que ele seja ordenado;
	 * @param numProd, tamanho do array
	 * @return array[numProd] j� ordenado decrescentemente;
	 */
	public static List<ProdAcademica> bubbleSortProd(List<ProdAcademica> array, int numProd) {
		 ProdAcademica temp; 
		 for (int i = 1; i < numProd; i++) {
			    for (int j = 0; j < i; j++) {
			        if (array.get(i).getAnoProd() > array.get(j).getAnoProd()) {
			            temp = array.get(i);
			            array.set(i, array.get(j));
			            array.set(j, temp);
			            
			        } else {
			        	if (array.get(i).getAnoProd() == array.get(j).getAnoProd()) {
			        		if (array.get(i).getMesProd() > array.get(j).getMesProd()) {
			        			temp = array.get(i);
			        			array.set(i, array.get(j));
			        			array.set(j, temp);
			        		} else {
			        			if (array.get(i).getMesProd() == array.get(j).getMesProd()) {
			        				if (array.get(i).getDiaProd() > array.get(j).getDiaProd()) {
			        					temp = array.get(i);
			        					array.set(i, array.get(j));
			        					array.set(j, temp);
			        				}
			        			}
			        		}
			        	}
			        }
			    }
			}
	        return array;
	    }  

}