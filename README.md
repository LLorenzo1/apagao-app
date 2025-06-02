# 🔌 Assistente de Apoio em Falta de Energia

Este é um aplicativo Java feito com uma interface gráfica (JavaFX) que consome múltiplas APIs com intuito de fornecer suporte para usuários afetados por queda de energia elétrica. Ele faz o uso do CEP do usuário para realizar diversas ações úteis e informativas, como mostrar coordenadas, locais seguros e simular prejuízos financeiros.

---

## Desenvolvido por:
- **Lorenzo Ferreira -  RM 97318**
- **André Lambert - RM 99148**
- **Felipe Cortez - RM 99750**

---

## Funcionalidades

**Consulta de endereço por meio do CEP**  
Consulta a API pública do [ViaCEP](https://viacep.com.br/) e retorna o endereço completo.

**Conversão de endereço em coordenadas geográficas**  
Utiliza a API do [Nominatim / OpenStreetMap](https://nominatim.openstreetmap.org/) para transformar o endereço em latitude e longitude.

**Busca de locais seguros próximos**  
Consulta a [Overpass API](https://overpass-api.de/) para encontrar:
-  Hospitais
-  Delegacias
-  Abrigos Públicos

**Alerta de risco na região do CEP informado**  
Simula alertas de risco com base na UF (estado) do endereço consultado.

**Interface Gráfica (JavaFX)**  
O sistema possui uma interface visual, onde o usuário pode:
- Inserir o CEP
- Informar o tipo de estabelecimento (residencial ou comercial)
- Inserir a quantidade de horas que ficou sem energia
- Visualizar as informações de forma organizada

**Cálculo de prejuízo**  
Simula o impacto financeiro da falta de energia com base em:
- Tipo de estabelecimento (Residencial ou Comercial)
- Horas sem energia

---

## Estrutura do Projeto

```
apagao-app/
├── controller/         # Camada de controle (interage com o usuário)
├── service/            # Camada de serviço (lógica de negócio e APIs)
├── model/              # Camada de modelo (representação de dados)
├── AppFX.java          # Classe principal com interface gráfica JavaFX
├── App.java            # (opcional) Classe antiga para execução em terminal
└── pom.xml             # Arquivo de configuração Maven
```

---

## Como Executar o Projeto

1. Clone este repositório:

```bash
git clone https://github.com/LLrenzo1/apagao-app.git
```

2. Acesse a pasta do projeto:

```bash
cd apagao-app
```

3. Compile e execute com Maven, especificando a interface JavaFX:

```bash
mvn clean compile exec:java
```

---

## APIs que foram utilizadas

- **[ViaCEP](https://viacep.com.br/)** — Consulta de endereço a partir do CEP  
- **[Nominatim / OpenStreetMap](https://nominatim.openstreetmap.org/)** — Conversão de endereço em coordenadas geográficas  
- **[Overpass API](https://overpass-api.de/)** — Busca por locais seguros próximos (ex: hospitais, delegacias, abrigos)  
- **Simulação local** — Alertas da região e valores de tarifa simulados, baseados na UF

---
