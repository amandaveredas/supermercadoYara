# Projeto Supermercado Vila Yara
___
## Backend
___

### Funcionalidades

1. Produtos
	* Para todas as operações dessa seção, o usuário deve estar logado, caso contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso negado. Usuário precisa logar no sistema."```
	
	* Para as operações de cadastro, alteração, e exclusão, o usuário precisa ser um ADMINISTRADOR. Caso 	contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso Negado! Usuário não possui acesso a essa funcionalidade."```


	1.1. Cadastro de produtos
	 
	 * Assinatura	 	
	 	*  POST(/produtos)
 
	* Parâmetros de entrada
	 	
	 	* ProdutoRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 201 CREATED
		* Body: Vazio
		
	* Comportamentos:
		* Caso nenhuma categoria seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"Nenhuma categoria com nome '{SUBSTITUIR-PELO-NOME}' foi encontrada.".```

		 
	* Regras:
		* Não deve ser possível cadastrar mais de um produto com o mesmo nome: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe um produto cadastrado com o nome '{substituir-por-nome-informado}'".```


	1.2. Listagem de produtos
	 
	 * Assinatura	 	
	 	*  GET(/produtos)
 
	* Sem parâmetros de entrada
	 	
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: List < ProdutoResponse >

	
	* Comportamentos:
		*  Caso nenhum produto seja localizado, retorna uma lista vazia. 
		

	1.3. Alterar produtos
	 
	 * Assinatura	 	
	 	*  PUT(/produtos/{id})
 
	* Parâmetros de entrada
	 	
	 	* ProdutoRequest	
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: ProdutoResponse
		
	* Comportamentos:
		* Caso nenhuma categoria seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"Nenhuma categoria com nome '{SUBSTITUIR-PELO-NOME-INFORMADO}' foi encontrada."```

		* Caso o produto não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O produto com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```
			

	1.4. Excluir produtos
	
	* Assinatura	 	
	 	*  DELETE(/produtos/{id})
 
	* Sem parâmetros de entrada

	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Vazio
		
	* Comportamentos:
	
		* Caso o produto não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O produto com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```	

	1.5. Exportar Inventário
	
	* Assinatura	 	
	 	*  POST(/produtos/exportar)
 
	* Sem parâmetros de entrada

	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Vazio
		

	1.6. Busca produto pelo id
	 
	 * Assinatura	 	
	 	*  GET(/produtos/{id})
 
	* Sem parâmetros de entrada
	 	
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: ProdutoResponse

	
	* Comportamentos:
	
		* Caso o produto não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O produto com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```
		
		
_____

2. Categorias

	* Para todas as operações dessa seção, o usuário deve estar logado, caso contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso negado. Usuário precisa logar no sistema."```
	
	* Para operações de cadastro, o usuário precisa ser um ADMINISTRADOR. Caso 	contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso Negado! Usuário não possui acesso a essa funcionalidade."```

	2.1. Cadastro de categorias
	 
	 * Assinatura	 	
	 	*  POST(/categorias)
 
	* Parâmetros de entrada
	 	
	 	* CategoriaRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 201 CREATED
		* Body: Vazio
			 
	* Regras:
		* Não deve ser possível cadastrar mais de uma categoria com o mesmo nome: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe uma categoria cadastrada com o nome '{substituir-por-nome-informado}'".```


	2.2. Listagem de categorias
	 
	 * Assinatura	 	
	 	*  GET(/produtos)
 
	* Sem parâmetros de entrada
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: List < Categoria >
	 		
		
	* Comportamentos:
		*  Caso nenhuma categoria seja localizado, retorna uma lista vazia. 

	2.3. Busca categoria pelo id
	 
	 * Assinatura	 	
	 	*  GET(/categorias/{id})
 
	* Sem parâmetros de entrada
	 	
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Categoria

	
	* Comportamentos:
	
		* Caso a categoria não seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"A categoria com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrada.".```
		
_____
3. Usuários

	* Para todas as operações dessa seção, o usuário deve estar logado, caso contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso negado. Usuário precisa logar no sistema."```
	
	* Para todas as operações dessa seção, o usuário precisa ser um ADMINISTRADOR. Caso 	contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso Negado! Usuário não possui acesso a essa funcionalidade."```

	3.1. Cadastro de usuários
	 
	 * Assinatura	 	
	 	*  POST(/usuarios)
 
	* Parâmetros de entrada
	 	
	 	* UsuarioRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 201 CREATED
		* Body: Vazio
		

	* Comportamentos:
		* Caso nenhuma função seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"Nenhuma função com nome '{SUBSTITUIR-PELO-NOME}' foi encontrada.".```

			 
	* Regras:
		* Não deve ser possível cadastrar mais de um usuário com o mesmo username: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe um usuario cadastrado com o username '{USERNAME-INFORMADO}}'.".```

		* Não deve ser possível cadastrar mais de um usuário com o mesmo email: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe um usuario cadastrado com o email '{EMAIL-INFORMADO}}'.".```


	3.2. Listagem de usuários
	 
	 * Assinatura	 	
	 	*  GET(/usuarios)
 
	* Sem parâmetros de entrada
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: List < UsuarioResponse >
	 		
		
	* Comportamentos:
		*  Caso nenhum usuário seja localizado, retorna uma lista vazia. 
		
	3.3. Alterar usuários
	 
	 * Assinatura	 	
	 	*  PUT(/usuarios/{id})
 
	* Parâmetros de entrada
	 	
	 	* UsuarioRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: UsuarioResponse
		
	* Comportamentos:
		* Caso nenhuma função seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"Nenhuma função com nome '{SUBSTITUIR-PELO-NOME}' foi encontrada.".```

		* Caso o usuário não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O usuário com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```	
			 
	* Regras:
		* Não deve ser possível atualizar o username para um que já exista: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe um usuario cadastrado com o username '{USERNAME-INFORMADO}}'.".```
		* Não deve ser possível atualizar o email para um que já exista: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe um usuario cadastrado com o email '{EMAIL-INFORMADO}}'.".```
		

	3.4. Excluir usuário
	
	* Assinatura	 	
	 	*  DELETE(/usuario/{id})
 
	* Sem parâmetros de entrada

	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Vazio
		
	* Comportamentos:
	
		* Caso o usuário não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O usuário com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```	

	3.5. Busca usuário pelo id
	 
	 * Assinatura	 	
	 	*  GET(/usuarios/{id})
 
	* Sem parâmetros de entrada
	 	
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: UsuarioResponse

	
	* Comportamentos:
	
		* Caso o usuário não seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"O usuário com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrado.".```

____
4. Funções

	* Para todas as operações dessa seção, o usuário deve estar logado, caso contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso negado. Usuário precisa logar no sistema."```
	
	* Para todas as operações dessa seção, o usuário precisa ser um ADMINISTRADOR. Caso 	contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: ``` "Acesso Negado! Usuário não possui acesso a essa funcionalidade."```

	4.1. Cadastro de funções
	 
	 * Assinatura	 	
	 	*  POST(/funcao)
 
	* Parâmetros de entrada
	 	
	 	* FunçãoRequest 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 201 CREATED
		* Body: Vazio
			 
	* Regras:
		* Não deve ser possível cadastrar mais de uma função com o mesmo nome: deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Já existe uma função cadastrada com o nome '{substituir-por-nome-informado}'."```


	4.2. Listagem de funções
	 
	 * Assinatura	 	
	 	*  GET(/funcao)
 
	* Sem parâmetros de entrada
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: List < Funcao >
	 		
		
	* Comportamentos:
		*  Caso nenhuma categoria seja localizado, retorna uma lista vazia. 

	4.3. Busca função pelo id
	 
	 * Assinatura	 	
	 	*  GET(/funcao/{id})
 
	* Sem parâmetros de entrada
	 	
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Funcao

	
	* Comportamentos:
	
		* Caso a função não seja localizada, deve lançar uma exceção com o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"A função com id '{SUBSTITUIR-PELO-ID-INFORMADO}' não foi encontrada.".```

___
5. Login

	
	5.1. Login
	 
	 * Assinatura	 	
	 	*  POST(/login)
 
	* Parâmetros de entrada
	 	
	 	* LoginUsuarioRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Long tokenLogin
			 
	* Comportamento:
		* Caso o email ou a senha informados não sejam corretas:deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema.
			* Mensagem: ```"Acesso Negado! E-mail ou senha incorretos."```


	5.2. Logout
	 
	 * Assinatura	 	
	 	*  GET(/login/logout)
 
	* Sem parâmetros de entrada
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Vazio
	 		
		
	* Comportamentos:
		*  Caso o usuário não esteja logado, uma exceção 401 (UNAUTHORIZED) será lançada com uma mensagem informando o problema.
			* Mensagem: ``` "Acesso negado. Usuário precisa logar no sistema."```
			
6. Redefinição de senha

	* Para a operações Gerar Token, o usuário precisa ser um ADMINISTRADOR. Caso contrário uma exceção 401 (UNAUTHORIZED) será lançada com a seguinte mensagem: "Acesso Negado! Usuário não possui acesso a essa funcionalidade."
	
	6.1. Gerar Token
	 
	 * Assinatura	 	
	 	*  POST(/redefinicao/token)
 
	* Parâmetros de entrada
	 	
	 	* GeraTokenRedefineRequest	 		
	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Long tokenSenha
			 
	* Comportamento:
		* Caso o email não exista:deve lançar uma exceção que retorne o status 404 e uma mensagem informando o problema.
			* Mensagem: ```""O usuário com email '{SUBSTITUIR-POR-EMAIL-INFORMADO}' não foi encontrado."```


	6.2. Redefinir Senha
	 
	 * Assinatura	 	
	 	*  POST(/redefinicao)
 
	* Parâmetros de entrada
	 	
	 	* RedefineSenhaRequest	 		
	 		 
	* Saída esperada em caso de sucesso:
		* Status: 200 OK
		* Body: Vazio
	 		
		
	* Comportamento:
		* Caso o email não exista:deve lançar uma exceção que retorne o status 404 e uma mensagem informando o problema.
			* Mensagem: ```""O usuário com email '{SUBSTITUIR-POR-EMAIL-INFORMADO}' não foi encontrado."```
			
		* Caso o token ou email não estejam corretos:deve lançar uma exceção que retorne o status 404 e uma mensagem informando o problema.
			* Mensagem: ```"Não foi possível criar nova senha!"```
