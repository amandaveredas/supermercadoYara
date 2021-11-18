# Projeto Supermercado Vila Yara
___
## Backend
___

__
### Funcionalidades

1. Produtos

	1.1. Cadastro de produtos
	 
	 * Assinatura	 	
	 	*  POST(/produtos)
 
	* Parâmetros de entrada
	 	
	 	* ProdutoRequest:	 		
	 		* nome*
			* idCategoria*
			* descricao*
			* quantidade*
			* precoUnitario*
			* imagem
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 201 CREATED
		* Body: Vazio
		
	* Comportamentos:
		* A aplicação deve obter a categoria através do Id informado, para poder vincular ao Produto antes de persistir.
		* Caso nenhuma categoria seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```Nenhuma categoria com id '{SUBSTITUIR-PELO-ID-INFORMADO}' foi encontrada.'.```

		 
	* Regras:
		* Os atributos marcados com um "*" são obrigatórios e as mensagens de retorno serão do tipo: ```"Campo '{campo}' não pode ser nulo."```
		´
		* Não deve ser possível cadastrar mais de um produto com o mesmo nome.
			* Caso já exista outro usuário com o e-mail informado, deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```Já existe um produto cadastrado com o nome '{substituir-por-nome-informado}'.```


	2.1. Listagem de produtos
	 
	 * Assinatura	 	
	 	*  GET/produtos
 
	* Parâmetros de entrada
	 	
	 	* Body: Vazio
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: List < ProdutoResponse >

		* ProdutoResponse:	 		
	 		* nome
			* nomeCategoria
			* descricao
			* quantidade
			* precoUnitario
			* imagem
		
	* Comportamentos:
		*  Caso nenhum produto seja localizado, retorna uma lista vazia. 

		 
	