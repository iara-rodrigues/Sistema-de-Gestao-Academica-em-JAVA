package br.edu.ufsj.ccomp.tecweb.gestao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iara Rodrigues;
 */
public class Colaborador {
		private String nome;
		private String email;
		private List<Projeto> projetos;
		
		public Colaborador(String nome, String email) {
			this.nome = nome;
			this.email = email;
			
			this.projetos = new ArrayList<Projeto>();
		}
		
		public String getNome() {
			return this.nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		/** Essa função foi criada para adicionar um Projeto ao Colaborador;
		 * 
		 * @param projeto
		 */
		public void associarProjeto(Projeto projeto) {
				this.projetos.add(projeto);
		}
		
		/** Essa função foi criada para remover um Projeto do Colaborador, já que é permitido a edição de 
		 * participantes de um projeto, foi necessário criar essa função;
		 * 
		 * @param projeto
		 */
		public void desassociarProjeto(Projeto projeto) {
			this.projetos.remove(projeto);
		}
		
		public List<Projeto> getProjetosEmColab() {
			return this.projetos;
		}
		
		public String imprimirColab() {
			String imprimir = "Nome: " + this.nome + "- Email: " + this.email;
			//imprimir += "Get: "+getProjetosEmColab().size();
			//for (int i = 0; i < getProjetosEmColab().size(); i++) {
			//	imprimir += projetos.get(i).getTitulo();
			//	imprimir += projetos.get(i).getStatusString();
			//}
			return imprimir;
		}	

}
