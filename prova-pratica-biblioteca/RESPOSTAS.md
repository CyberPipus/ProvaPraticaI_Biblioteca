# Respostas — Sistema de Emprestimo da Biblioteca

Nome completo: Pietro Lanzoni de Godoy  RA: 3111392523011

> Preencha **todos** os campos. Campo vazio é campo não avaliado.
> Não apague os títulos nem os rótulos em negrito: a correção procura por eles.

---

# Etapa 1 — Diagnóstico

## Problema 1 — a conversão do acervo central

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
O problema reside na duplicação da lógica de interpretação e conversão dos dados brutos retornados pelo serviço de acervo "String[][]" para "Exemplar.java", implementada independentemente em duas classes diferentes: "CatalogoExemplares.java" e "RelatorioColecao.java".
Com a rotina escrita em dois lugares, o tratamento de strings divergiu: "CatalogoExemplares.java" considera disponível apenas a letra maiúscula, exemplificado por "D" ou "B", enquanto a classe "RelatorioColecao.java" trata tanto de maiúsculas quanto minúsculas,"D" e "d" por exemplo. Isso permite o exemplar TOM-2003 aparecer indisponível no catálogo e disponível no relatório, gerando inconsistência nas informações apresentadas.
Quando o sistema crescer, novos módulos que necessitem consultar exemplares também serão obrigados a clonar essa rotina de extração e conversão. Se o serviço de acervo central alterar a ordem das colunas, acrescentar novos dados ou modificar a codificação de status, todas as classes consumidoras espalhadas pelo projeto precisarão ser alteradas manualmente, multiplicando desnecessariamente o esforço de manutenção e o risco de falhas por divergências de implementação.

**Princípio SOLID violado:**
Primariamente, o Princípio da Responsabilidade Única ("Single Responsibility Principle - SRP)", pois "CatalogoExemplares.java" e "RelatorioColeca.java" acumulam tanto a sua responsabilidade de domínio/apresentação quanto a responsabilidade de baixo nível de fazer parsing, conversão e saneamento de dados de infraestrutura externa.
Por fim, o Princípio da Inversão de Dependência ("Dependency Inversion Principle" - DIP), uma vez que módulos de alto nível dependem diretamente da classe concreta de baixo nível "AcervoLegadoWS.java" em vez de dependerem de uma abstração.

**Classes afetadas (pelo nome):**
As classes "CatalogoExemplares.java" e "RelatorioColeca.java".

**Que outra dependência indevida essas classes têm em relação ao
acervo central, e por que ela é um problema:**
As classes possuem acoplamento direto com a classe concreta "AcervoLegadoWS.java" ao invés de uma interface e com a estrutura interna dos dados brutos "String[][]", estando cientes que o índice 0 é código, 1 é descrição e 2 é situação.
Isso é um problema porque impede a substituição do serviço legado por outra implementação, seja um banco de dados relacional, uma nova API REST ou até mocks para testes unitários, sem reescrever as classes consumidoras. Além disso, detalhes e fragilidades do contrato externo vazam para a lógica de negócio interna do SIGA.

## Problema 2 — o ciclo de vida de Emprestimo

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
O problema consiste no controle do ciclo de vida e das regras de transição de estado da classe "Emprestimo.java" ser feito por meio de estruturas condicionais, "if/else" básico, baseadas em literais de texto ,"String situacao", distribuídas dentro de cada método público,"emprestar()", "restituir()" e "anular()". Não existe um ponto centralizado onde seja possível ler ou documentar o fluxo completo do ciclo de vida.
Quando o sistema crescer, seja ao adicionar novos estados ou novas ações, torna-se necessário alterar todos os métodos existentes com novos blocos condicionais. Além disso, o uso de "String" impede a checagem no tempo de compilação: qualquer erro de digitação nos literais só aparecerá após a execução e constação da equipe.

**Princípio SOLID violado:**
Em primeiro lugar, o Princípio Aberto/Fechado (Open/Closed Principle - OCP), pois a classe "Emprestimo.java" não está fechada para modificações diretas no código porque para incluir um novo estado ou alterar uma transição, é necessário modificar o própio código dos métodos já implementados.
Depois, o Princípio da Responsabilidade Única (de novo), pois "Emprestimo.java" acumula a gestão dos dados do empréstimo com a lógica detalhada de validação E transição de cada estado individual do seu ciclo de vida.

**Classes afetadas (pelo nome):**
Somente a classe "Emprestimo.java".


---

# Etapa 4 — Justificativa

## Padrão aplicado ao Problema 1

**O problema que ele resolve (descreva antes de nomear o padrão):**


**Nome do padrão:**


**Alternativa descartada e por que ela não resolveria por completo:**


## Padrão aplicado ao Problema 2

**O problema que ele resolve (descreva antes de nomear o padrão):**


**Nome do padrão:**


**Alternativa descartada e por que ela não resolveria por completo:**
