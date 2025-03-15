# 📊 API de Transações - Desafio Itaú Unibanco

Esta é uma API REST desenvolvida com **Spring Boot** para processar transações financeiras e calcular estatísticas com base nas últimas transações registradas.

## 🚀 Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
- **Gradle (Groovy)**
- **JUnit 5** (para testes)

## ⚙️ Configuração e Execução

### 📌 **Pré-requisitos**
- **Java 17+** instalado
- **Gradle 7+** instalado (caso use a versão global)

### ▶️ **Executando a Aplicação**
1. **Clone o repositório**
   ```sh
   git clone https://github.com/fabricioaquiles/desafio-itau.git
   cd desafio-itau
2. **Executar usando IntelliJ IDE**

    * Abra o IntelliJ IDEA
    * Selecione "Open" ou "Import Project"
    * Navegue até o diretório do projeto e selecione-o
    * Aguarde a IDE importar e indexar o projeto
    * Encontre a classe principal (geralmente com anotação `@SpringBootApplication`)
    * Clique com o botão direito na classe e selecione "Run [DesafioitauApplication]"

# 📌 Endpoints da API

## 1️⃣ Criar uma Transação
**📌 POST /transacao**

**Descrição:** Registra uma nova transação.

**Exemplo de requisição:**
```json
{
  "valor": 123.45,
  "dataHora": "2024-03-13T12:34:56.789-03:00"
}
```
**Respostas**:
   * `201 Created`: Transação registrada com sucesso.
   * `422 Unprocessable Entity`: Transação inválida (futuro, valor negativo, etc.).
   * `400 Bad Request`: Requisição malformada.

## 2️⃣ Limpar Transações
**📌 DELETE /transacaoo**

**Descrição:** Apaga todas as transações registradas.

**Resposta**:
   * `200 OK`: Transações apagadas.

## 2️⃣ Obter Estatísticas
**📌 DELETE /estatistica**

**Descrição:** Retorna estatísticas das transações dos últimos 60 segundos.

**Parâmetros de consulta**:
  * `searchInterval` (opcional): Intervalo de tempo em segundos para calcular as estatísticas. Exemplo: `searchInterval=120` para os últimos 120 segundos. Se não for especificado, o padrão é 60 segundos.

**Exemplo de requisição**:
```http
GET /estatistica?searchInterval=120
```

**Exemplo de resposta**:
```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
```
**Resposta**: 
   * `200 OK`: Estatísticas calculadas com sucesso.
   * Se não houver transações dentro do intervalo configurado: todos os valores serão `0.0`.
