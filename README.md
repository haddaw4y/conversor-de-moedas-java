Conversor de Moedas em Java
📝 DescriçãoEste é um conversor de moedas simples, desenvolvido em Java para ser executado no console. O programa se conecta à API ExchangeRate-API para obter taxas de câmbio em tempo real e realizar conversões entre diferentes pares de moedas.O usuário interage com a aplicação através de um menu no terminal, onde pode escolher qual conversão deseja realizar, inserir o valor e obter o resultado instantaneamente.
✨ FuncionalidadesConversão em Tempo Real: Utiliza uma API para buscar as taxas de câmbio mais recentes.Menu Interativo: Interface de linha de comando amigável para o usuário.Múltiplos Pares de Moedas: Suporta a conversão entre:Dólar Americano (USD)Real Brasileiro (BRL)Yuan Chinês (CNY)Rublo Russo (RUB)Validação de Entrada: O sistema trata entradas inválidas do usuário (como textos em vez de números), evitando que a aplicação quebre.Fácil de Sair: O usuário pode encerrar o programa de forma limpa selecionando a opção "Sair".
🛠️ Tecnologias UtilizadasJava 17+Biblioteca Gson: Para fazer o parsing da resposta JSON da API.Java HTTP Client: Para realizar as requisições à API.⚙️ Pré-requisitosAntes de começar, você precisará ter o seguinte instalado em sua máquina:Java Development Kit (JDK) - Versão 17 ou superior.Uma chave de API da ExchangeRate-API: O serviço oferece um plano gratuito que é suficiente para este projeto.
🚀 Como ExecutarClone o repositório (ou baixe os arquivos):git clone https://github.com/seu-usuario/seu-repositorio.git cd seu-repositorio
Obtenha a biblioteca Gson:Você pode baixar o arquivo .jar do Gson no site oficial e colocá-lo em uma pasta lib no seu projeto.Insira sua chave da API:Abra o arquivo Principal.java e substitua o valor da variável suaChaveApi pela chave que você obteve no site da ExchangeRate-API.// Dentro do método buscarTaxaDeCambio
String suaChaveApi = "SUA_CHAVE_API_VAI_AQUI";
Compile o código:Se você salvou o Gson em uma pasta lib, use o seguinte comando no terminal (estando na raiz do projeto):# Para Windows
javac -cp ".;lib/gson-2.10.1.jar" Principal.java TaxaDeCambio.java

# Para Linux/macOS
javac -cp ".:lib/gson-2.10.1.jar" Principal.java TaxaDeCambio.java
Observação: Certifique-se de que a classe TaxaDeCambio também esteja presente no mesmo diretório ou pacote.Execute a aplicação:# Para Windows
java -cp ".;lib/gson-2.10.1.jar" Principal

# Para Linux/macOS
java -cp ".:lib/gson-2.10.1.jar" Principal
Use o conversor:O menu será exibido no console. Basta digitar o número da opção desejada, pressionar Enter, e seguir as instruções!🏛️ Estrutura do CódigoPrincipal.java: A classe principal que contém o método main. É responsável pelo menu, pela interação com o usuário e por orquestrar as chamadas de conversão.TaxaDeCambio.java: Uma classe record (ou POJO) que representa a estrutura do JSON retornado pela API, facilitando o parsing dos dados com a biblioteca Gson.// Exemplo da classe TaxaDeCambio.java
public record TaxaDeCambio(String base_code, String target_code, double conversion_rate) {
}
📄 LicençaEste projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
