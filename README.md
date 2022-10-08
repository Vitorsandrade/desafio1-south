<div style="display: inline_block"><br>
  <img align="center" alt="Vitor-Java" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
    <h1>
   		 South Store
    </h1>
</div>



## Desafio ##

- O impulsionando deverá construir um Loja Online *Simples* utilizando os conhecimentos adquiridos durante o módulo.

## Descrição ##
Programa criado para o armazenamento e manipulação dos Produtos da loja, o programa é capaz de gerenciar, inserir, excluir, editar ou importar produtos de forma simples, toda alteração da lista de produtos é inclusa no arquivo .csv que armazena os produtos (storage.csv), existe um produto que é criado ao ser iniciado o programa chamado de MODELO, esse produto não pode ser excluído ou editado.

## Aplicação

#### Após inicio do programa será exibido a Logo da loja, e a Data/Horário no qual foi iniciado o programa.

##### Ex:     

![](C:\Users\vitor\OneDrive\Área de Trabalho\logo.png)



#### Logo abaixo será exibido o menu do programa, aguardando a opção de escolha do usuário. 

##### Ex: 

![](C:\Users\vitor\OneDrive\Área de Trabalho\menuo.png)



#### Caso usuário digite [1] Registrar produto

##### Será solicitado os  campos: 

Nome(Não aceita passar em branco e Não aceita nomes de produtos que já estão na lista)

Categoria(Não aceita passar em branco)

Descrição(Não aceita passar em branco)

Cor(Não pode aceita em branco)

Material(Não aceita passar em branco)

Preço(Não aceita passar em branco, Não aceita valores negativos e só aceita valores numéricos)

Quantidade(Não aceita passar em branco, Não aceita valores negativos e só aceita valores numéricos)

##### Ao serem passados os dados, o programa irá perguntar para o usuário se quer confirmar a operação [1] para confirmar / [2] para cancelar.



#### Caso usuário digite [2] Editar produto

##### Se a lista estiver vazia será passada a mensagem: PRIMEIRO PREENCHA A LISTA DE PRODUTOS!

##### Será informada a lista de produtos para o usuário e solicitado Id do produto.

##### Caso usuário digite ID inexistente ou ID do produto MODELO, será exibido ID inválido e perguntara novamente até ser passado um ID válido.

##### Passando um ID válido será informado as informações do produto e perguntara qual dado deve ser modificado.

##### Ex:

![](C:\Users\vitor\OneDrive\Área de Trabalho\edit.png)



#### Caso usuário digite [3] Excluir produto

##### Se a lista estiver vazia será exibido a mensagem: PRIMEIRO PREENCHA A LISTA DE PRODUTOS!

##### Será informada a lista de produtos para o usuário e solicitado Id do produto.

##### Caso usuário digite ID inexistente ou ID do produto MODELO, será exibido ID inválido e perguntara novamente até ser passado um ID válido.

##### Passando um ID válido será perguntado se quer confirmar [1] ou cancelar[2] a operação.



#### Caso usuário digite [4] Importar produtos

##### Será perguntado se quer confirmar [1] ou cancelar[2] a operação.

##### Caso confirme a operação escreva: src/main/resources/mostruario_fabrica.csv

##### Ex:

![](C:\Users\vitor\OneDrive\Área de Trabalho\importo.png)

##### Caso usuário digite o caminho do arquivo errado será exibido a mensagem: ARQUIVO INVÁLIDO! e será solicitado mais uma vez o caminho do arquivo.



#### Caso usuário digite [5] Listar produtos

##### Será exibido a lista de produtos.



#### Caso usuário digite [6] Sair

##### O programa será encerrado.

##### Será exibido a Logo da loja, e a Data/Horário no qual foi encerrado o programa.

##### Ex:

![](C:\Users\vitor\OneDrive\Área de Trabalho\encer.png)



##### Executar programa com: mvnw spring-boot:run

##### Executar testes com: mvnw test