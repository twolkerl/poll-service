
# poll-service  
  
Serviço para gerenciamento de sessões de votação em pautas.  
  
Funcionalidades:  
  
- Cadastrar uma nova pauta  
  
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um  
tempo determinado na chamada de abertura ou 1 minuto por default)  
  
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada  
associado é identificado por um id único e pode votar apenas uma vez por pauta)  
  
- Contabilizar os votos e dar o resultado da votação na pauta  
  
## Banco de Dados  
  
O Banco de dados utilizado nesta aplicação é o MongoDB.  

## Integrações

Integração com sistema que verifica se o CPF pode votar (validação feita no momento do voto): https://user-info.herokuapp.com/users/{cpf}
  
## Métodos  
  
### Pauta  
  
URL Base: localhost:8080/poll  
  
- **Cadastrar pauta**  
  
Método: POST  
  
Payload exemplo:

    {
    
      "title": "Pauta Teste",
      "description": "Esta pauta é útil?"
    
    }

- **Listar pautas**

Método: GET

- **Buscar pauta por id**

Método: GET

Complemento url: `/{id}`

### Sessão

URL Base: localhost:8080/poll-session

- **Iniciar sessão**

Método: POST

Payload exemplo:

    {
      "sessionStart": "2020-03-15T12:53:00",
      "sessionEnd": "2020-05-20T12:53:00",
      "poll": {
        "id": "5e6f5d36586a384674740cab",
        "title": "Pauta Teste",
        "description": "Esta pauta é útil?"
      }
    }

- **Buscar sessão por id da pauta**

Método: GET

Complemento url: `/poll/{pollId}`

- **Buscar por id da sessão**

Método: GET

Complemento url: `/{id}`

- **Buscar resultados da votação na pauta pelo id da sessão**

Método: GET

Complemento url: `/results/{id}`

### Voto

URL base: localhost:8080/vote

- **Votar**

Método: POST

Payload exemplo:

    {
    
      "voteOption": false,
    
      "cpf": "10672835010",
    
      "poll": {
    
        "id": "5e6f5d36586a384674740cab",
    
        "title": "Pauta Teste",
    
        "description": "Esta pauta é útil?"
    
      }
    
    }
