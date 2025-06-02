# 🔌 Assistente de Apoio em Falta de Energia

Este é um aplicativo Java com interface gráfica (JavaFX) que consome múltiplas APIs para fornecer suporte a usuários afetados por queda de energia elétrica. Ele utiliza o CEP do usuário para realizar diversas ações úteis e informativas, como exibir coordenadas, locais seguros e simular prejuízos financeiros.

---

## 👨‍💻 Desenvolvido por:
- **Lorenzo Silva**
- **[Nome Completo do Integrante 2]**
- **[Nome Completo do Integrante 3]**

---

## 🚀 Funcionalidades

✅ **Consulta de endereço via CEP**  
Consulta a API pública do [ViaCEP](https://viacep.com.br/) e retorna o endereço completo.

✅ **Conversão de endereço em coordenadas geográficas**  
Utiliza a API do [Nominatim / OpenStreetMap](https://nominatim.openstreetmap.org/) para transformar o endereço em latitude e longitude.

✅ **Busca de locais seguros próximos**  
Consulta a [Overpass API](https://overpass-api.de/) para encontrar:
- 🏥 Hospitais
- 🚓 Delegacias
- 🛡️ Abrigos

✅ **Alerta de risco regional**  
Simula alertas de risco com base na UF (estado) do endereço consultado.

✅ **Interface Gráfica (JavaFX)**  
O sistema possui uma interface visual amigável, onde o usuário pode:
- Inserir o CEP
- Informar o tipo de imóvel (residencial ou comercial)
- Inserir a quantidade de horas sem energia
- Visualizar as informações de forma organizada

✅ **Cálculo de prejuízo estimado**  
Simula o impacto financeiro da falta de energia com base em:
- Tipo de imóvel (Residencial ou Comercial)
- Horas sem energia

---

## 📁 Estrutura do Projeto

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

## ▶️ Como Executar o Projeto

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/apagao-app.git
```

2. Acesse a pasta do projeto:

```bash
cd apagao-app
```

3. Compile e execute com Maven, especificando a interface JavaFX:

```bash
mvn clean compile exec:java -Dexec.mainClass=com.apagao.AppFX
```

> 🔧 **Atenção**: Certifique-se de configurar corretamente o caminho do JavaFX no `pom.xml` ou como variável `PATH_TO_FX` local.

---

## 📚 APIs Utilizadas

- 🔎 **[ViaCEP](https://viacep.com.br/)** — Consulta de endereço a partir do CEP  
- 🌐 **[Nominatim / OpenStreetMap](https://nominatim.openstreetmap.org/)** — Conversão de endereço em coordenadas geográficas  
- 🗺️ **[Overpass API](https://overpass-api.de/)** — Busca por locais seguros próximos (ex: hospitais, delegacias, abrigos)  
- 🔔 **Simulação local** — Alertas regionais e valores de tarifa simulados, baseados na UF

---

## 📌 Observações

- Este projeto foi desenvolvido com foco acadêmico e modularidade, respeitando boas práticas como:
  - Separação em camadas (controle, serviço, modelo)
  - Uso de padrões REST/JSON
  - Interface gráfica amigável com JavaFX
- Projeto compatível com Java 11+
- Pode ser expandido com:
  - Histórico de alertas e prejuízos
  - Exportação para PDF ou planilha
  - Integração com bancos de dados ou APIs reais de energia elétrica

---
