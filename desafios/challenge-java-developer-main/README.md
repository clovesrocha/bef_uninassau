# Avaliação para admissão de Desenvolvedores para a Neurotech

## Instruções

- Realize o fork deste projeto para desenvolver a sua solução. Não serão aceitos commits diretamente para este repositório;
- Após o desenvolvimento da sua solução, nos avise, enviando o link do seu projeto para que iniciemos a avaliação. **Não crie Pull Requests!**
- A solução deve ser entregue em um prazo máximo de 3 dias. 

## Descrição

Trata-se de um projeto que avalia e aplica modalidades diferentes de crédito a clientes PF, de acordo com critérios específicos. As modalidades diferentes de crédito estão descritas a seguir:

-   Crédito com Juros fixos: Aplicado a clientes com idade entre 18 e 25 anos, independente de renda. Taxa de 5% a.a
-   Crédito com Juros variáveis: Aplicado a clientes com idade entre 21 e 65 anos, com renda entre R$ 5000,00 e R$ 15000,00.
-   Crédito Consignado: Aplicado a clientes acima de 65 anos, independente de renda.

O projeto deve ser implantado como uma API RESTful, utilizando a linguagem Java e o framework Springboot. Atentar para implementações típicas de uma API RESTful, como códigos HTTP para cada tipo de endpoint, validação de dados, Documentação utilizando Swagger, e também testes automáticos para os endpoints implementados.

De maneira obrigatória:

1. Os seguintes endpoints devem ser implementados:
  * Endpoint para cadastro de clientes: Deve receber Informações como Nome, idade, renda. Como retorno, uma entrada no header da resposta contendo a URL que identifica o cliente (Ex: [http://localhost/api/client/050](http://localhost/api/client/050)). O nome do header deve ser “Location”.
  * Endpoint para retornar os dados do cliente de acordo com seu ID, indicado na URL (Ex: [http://localhost/api/client/050](http://localhost/api/client/050)). O retorno deve ser um objeto JSON contendo os dados do cliente. Por exemplo:
```
{ 
  "Name": "Bob",
  "Age": 40,
  "Income": 10000
}
```
  * Endpoint para definir se um determinado cliente está apto a oferecer um crédito automotivo para determinado modelo de veículo.
    -   Hatch: Renda entre R$ 5000,00 e R$15000,00.
    -   SUV: Renda acima de R$8000,00 e idade superior a 20 anos.
2. Implementar um Dockerfile que permita a execução da aplicação em ambiente de containers.
    

Como adicional (desejável), mas não obrigatório: 

1. Implemente um endpoint para se determinar todos os clientes entre 23 e 49 anos que possuem Crédito com juros fixos e estão aptos a adquirirem crédito automotivo para veículos do tipo Hatch. O objeto de retorno deve conter uma lista com o nome e a renda de cada um destes clientes.
2. Crie um script Terraform que levante os serviços Elastic Beanstalk com plataforma Docker (para servir a aplicação), repositório ECR (para armazenamento das imagens) e a estrutura IAM para gerenciamento das permissões de acesso.
3. Crie um arquivo de deployment no k8s, para adicionar a possibilidade de servir a aplicação em um cluster Kubernetes.



Boa sorte!
