# Sistema-de-Gestao-Academica-em-JAVA

<p>Desenvolvido por Iara Rodrigues Grigorio Oliveira Silva, pelo Windows.</p>

<p>O Sistema de Gestão Acadêmica busca oferecer aos docentes e discentes o gerenciamento da produção acadêmica de projetos conduzidos pelos seus laboratórios de pesquisa </p>
<p><b>Foram implementadas as seguintes classes:</b></p>
<ul>
  <li>Colaborador: Representa todos os discentes e doscentes que podem estar em algum projeto, sendo eles os Alunos de Graduação, Alunos de Mestrado, Pesquisadores e Professores</li>
  <li>AlunoGrad: Representa os alunos de graduação.</li>
  <li>AlunoMest: Representa os alunos de mestrado.</li>
  <li>Pesquisador: Representa os pesquisadores da faculdade.</li>
  <li>Professor: Representa os professores da faculdade.</li>
  <li>Orientacao: Representa as Produções Acadêmicas de Orientação.</li>
  <li>Publicacao: Representa as Produções Acadêmicas de Publicação.</li>
  <li>ProdAcademica: Contém as informações em comum da Orientação e Publicação.</li>
  <li>Programa: Representa o main do programa, ou seja, a parte executável.</li>
  <li>Projeto: Representa a ligação da lista de Produções Acadêmicas e da lista de seus Colaboradores.</li>
</ul>

<p><b>Observações desse Sistema de Gestão:</b></p>
<ul>
  <li>Projetos possuem as seguintes informações básicas: título, data de início, data de término, agência financiadora, valor financiado, objetivo, descrição, coordenador e participantes.</li>
  <li>Todo projeto de pesquisa possui obrigatoriamente um professor como coordenador.</li>
  <li>Todo projeto começa com o status “Em Elaboração”, e só o Professor pode alterar para “Em Andamento” quando possuir todas as informações básicas necessárias.</li>
  <li>A partir deste momento, o status do projeto somente poderá ser alterado pelo professor coordenador para “Concluído”, e para que isto aconteça devem existir publicações associadas ao projeto. </li>
  <li>Os colaboradores de um laboratório possuem dois tipos de produção acadêmica dentro do Projeto: publicações e orientações. </li>
  <li>Todos os colaboradores podem ter publicações, porém somente os professores podem ter orientações. </li>
  <li>Uma orientação é um trabalho conduzido por um discente sob orientação de um docente.</li>
  <li>Orientação possuem os seguintes dados: título, data de início, data de término, orientando e orientador.</li>
  <li>Uma publicação é produzida por um ou mais autores. </li>
  <li>Uma publicação possui título, nome da conferência onde foi publicada, ano de publicação e projeto de pesquisa associado.</li>
  <li>Uma publicação só poderá ser associada a um projeto quando o status do projeto estiver “Em andamento". </li>
</ul>


<p><b>Requisitos Funcionais</b><p> 
<ol type “1”>
  <li>O sistema permite o cadastro e edição de projetos de pesquisa.</li>
  <ol type “a”>
    <li>Alocação de participantes. Deve existir um professor coordenador para o projeto. Um aluno de graduação não pode participar de mais de dois projetos “Em andamento". A alocação será permitida apenas quando o projeto estiver “Em elaboração”. </li>
    <li>Alteração do status </li>
    <ol type “i”>
      <li>“Em elaboração” para “Em andamento". O professor coordenador pode iniciar um projeto apenas se constarem todas as informações básicas a respeito do projeto. </li>
      <li> “Em andamento” para "Concluído”. O coordenador pode concluir um projeto apenas se existirem publicações associadas ao projeto. </li>
    </ol>
  </ol>
  <li>O sistema permite a inclusão de informações referentes às produções acadêmicas. Publicações só podem ser associadas a um projeto quando o status do projeto estiver “Em andamento”.</li>
  <li> O sistema oferece as seguintes consultas: </li>
  <ol type “a”>
    <li>Consulta por colaborador: dado o nome de um colaborador, o sistema mostra suas informações: nome, e-mail, um histórico contendo a lista de projetos nos quais este colaborador participou, incluindo os projetos em andamento ordenados de forma decrescente pela data de término, incluindo também a lista de sua produção acadêmica. A produção acadêmica é ordenada de forma decrescente de data (ano). </li>
    <li>Consulta por projeto: dado o título de um projeto, o sistema mostra todos os dados do projeto, incluindo os colaboradores alocados e uma lista contendo toda a produção acadêmica do projeto, ordenada de forma decrescente de data (ano). </li>
  </ol>
  <li> O sistema fornece um relatório de produção acadêmica do laboratório, contendo:</li> 
  <ol type “i”>
    <li> Número de colaboradores </li>
    <li>Número de projetos em elaboração </li>
    <li> Número de projetos em andamento </li>
    <li>Número de projetos concluídos </li>
    <li> Número total de projetos </li>
    <li>Número de produção acadêmica por tipo de produção (orientação e publicação) </li>
  </ol>
</ol>

Esse Programa fez o uso das bibliotecas de coleção do Java usando <b>Polimorfismo Paramétrico</b>, assim como também <b>Tratamento de Exceção</b> para os seguintes casos: 
<ul>
  <li>O professor coordenador pode iniciar um projeto apenas se constarem todas as informações básicas a respeito do projeto cadastradas. </li>
  <li>O coordenador pode concluir um projeto apenas se existirem publicações associadas ao projeto.</li>
  <li>Publicações só podem ser associadas a um projeto quando o status do projeto estiver “Em andamento”.</li>
  <li>Um aluno de graduação não pode participar de mais de dois projetos “Em andamento”.</li>
  <li>A alocação de participantes deve ser permitida apenas quando o projeto estiver “Em elaboração”.</li>
</ul>
