# Veículos Monitor - Cadastro e Monitoramento de Veículos

Este é um projeto simples para o cadastro e monitoramento de veículos utilizando Java, Hibernate, H2 Database e JavaFX. A aplicação permite realizar operações CRUD (Criar, Ler, Atualizar, Deletar) em veículos, além de visualizar a lista de veículos cadastrados no banco de dados.

## Funcionalidades

- **Adicionar Veículo**: Permite cadastrar um novo veículo fornecendo informações como placa, modelo, marca e tipo.
- **Atualizar Veículo**: Permite editar as informações de um veículo já cadastrado utilizando sua placa como identificador.
- **Excluir Veículo**: Exclui um veículo do banco de dados com base na placa fornecida.
- **Listar Veículos**: Exibe todos os veículos cadastrados no banco de dados.

## Tecnologias Usadas

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Hibernate**: Framework ORM (Object-Relational Mapping) para interação com o banco de dados.
- **H2 Database**: Banco de dados embutido em Java utilizado para armazenar as informações dos veículos.
- **JavaFX**: Framework para criação da interface gráfica de usuário (GUI).

## Requisitos

- **JDK 17** ou superior instalado no seu sistema.
- **Maven** para gerenciamento de dependências (caso deseje fazer o build manualmente).

## Instalação

1. Clone este repositório:
    ```bash
    git clone https://github.com/seu-usuario/veiculos-monitor.git
    cd veiculos-monitor
    ```

2. Compile e execute o projeto:
   Se você estiver utilizando o **Maven**, execute o seguinte comando para compilar e rodar a aplicação:

    ```bash
    mvn clean install
    mvn javafx:run
    ```

   Ou, caso esteja utilizando uma IDE como o **IntelliJ IDEA**, basta importar o projeto e rodá-lo diretamente.

## Configuração do Banco de Dados H2

O banco de dados H2 está configurado para rodar em modo embutido. Ao iniciar a aplicação, ele utiliza o arquivo `test.mv.db` localizado no diretório de usuário (`C:/Users/PC/test.mv.db`).

- Para acessar a interface Web do H2 e visualizar os dados, abra o navegador e vá até o endereço [http://localhost:8090](http://localhost:8090).
- As credenciais para login são:
    - **Usuário**: sa
    - **Senha**: (deixe em branco)

## Como testar a aplicação

1. **Adicione um novo veículo**: Preencha os campos de placa, modelo, marca e tipo e clique em "Adicionar Veículo".
2. **Atualize um veículo**: Para atualizar as informações de um veículo, insira a placa no campo correspondente e altere as informações desejadas.
3. **Exclua um veículo**: Insira a placa do veículo a ser excluído e clique em "Excluir Veículo".
4. **Liste os veículos cadastrados**: Clique em "Listar Veículos" para visualizar todos os veículos salvos no banco de dados.

## Contribuição

Se desejar contribuir para este projeto, fique à vontade! Sinta-se livre para abrir *issues* ou enviar *pull requests* com melhorias, correções ou novos recursos.

## Licença

Este projeto é licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
