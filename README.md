🧪 Testes Automatizados com Selenium WebDriver
Este repositório contém testes automatizados utilizando Selenium e JUnit.

📘 Curso: Testes Manuais e Automatizados
Atualmente estou realizando um curso para aprofundar meus conhecimentos em testes de software, tanto manuais quanto automatizados. O conteúdo do curso abrange os seguintes tópicos:

Introdução aos Testes de Software

Fundamentos de Testes Funcionais

Automação Web com Selenium WebDriver

Escrita de Casos de Teste com JUnit

Manipulação de Elementos Web (Formulários, Tabelas, Alertas, etc.)

Estratégias de Sincronização (Esperas Implícitas e Explícitas)

Testes Orientados a Dados (Data-Driven)

Execução de Testes em Diferentes Navegadores

Boas Práticas em Frameworks de Testes

Este repositório inclui exemplos práticos e exercícios baseados nos tópicos abordados no curso.

🔧 Pré-requisitos
Para executar os testes, é necessário ter:

Java (JDK 8 ou superior)

Navegador Firefox instalado

Gerenciador de dependências (Maven ou Gradle, se necessário)

O driver Gecko (Firefox), gerenciado automaticamente com WebDriverManager

✅ Funcionalidades Testadas
Os testes são executados em um arquivo HTML local que simula um formulário. A seguir, as funcionalidades validadas:

🖊️ testeTestField
Testa o preenchimento de um campo de texto comum (input).

📝 deveInteragirComTextArea
Verifica a escrita dentro de um campo do tipo textarea.

🔘 deveInteragirComRadioButton
Testa a seleção de um botão do tipo radio.

☑️ deveInteragirComCheckBox
Testa a marcação de uma opção do tipo checkbox.

🔽 deveInteragirComCombo
Seleciona uma opção em um menu suspenso (select com escolha única).

📋 deveVerificarValoresCombo
Valida se o menu suspenso contém uma quantidade esperada de opções e se uma opção específica (“Mestrado”) está presente.

🔀 deveVerificarValoresComboMultiplo
Seleciona e desmarca múltiplas opções em um select com seleção múltipla, e verifica o total de itens selecionados.

🗂️ Estrutura do Projeto
Os testes estão localizados na classe TesteCampoTreinamento.java.

O arquivo HTML utilizado nos testes está no caminho:

css
Copiar
Editar
src/main/resources/campo_treinamento/componentes.html
Esse arquivo deve conter os elementos com os seguintes IDs esperados:

elementosForm:nome

elementosForm:sugestoes

elementosForm:sexo:0

elementosForm:comidaFavorita:2

elementosForm:escolaridade

elementosForm:esportes

▶️ Como Executar os Testes
Você pode executar os testes por sua IDE (como Eclipse ou IntelliJ) ou via terminal (caso utilize Maven):

bash
Copiar
Editar
mvn test
💡 Observações Finais
A janela do navegador é redimensionada para 600x400 para garantir consistência visual nos testes.

O WebDriverManager cuida automaticamente da instalação e configuração do driver do Firefox.

Os testes usam Assert do JUnit para verificar os comportamentos esperados.

