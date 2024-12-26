# CryptoTracker

**CryptoTracker** é um aplicativo Android para rastreamento de preços de criptomoedas em tempo real, desenvolvido com foco em boas práticas de arquitetura. Este projeto foi inspirado e construído como parte do curso **"The Best Practice Guide to Android Architecture"** de [Philipp Lackner](https://github.com/philipplackner).

---

## 🌟 **Destaques do Projeto**

- **Acompanhamento em tempo real:** Veja as alterações no mercado de criptomoedas com preços atualizados.
- **Pesquisa eficiente:** Encontre rapidamente a criptomoeda desejada.
- **Dark Mode:** Tema claro e escuro para uma experiência mais personalizada.
- **Visualização detalhada:** Informações específicas sobre cada criptomoeda.

---

## 🔧 **Tecnologias Utilizadas**

Este projeto utiliza ferramentas e padrões modernos do ecossistema Android:

- **Jetpack Compose:** Para uma interface do usuário declarativa e responsiva.
- **Arquitetura MVVM:** Separando responsabilidades entre camadas.
- **Kotlin Coroutines & Flow:** Gerenciamento assíncrono de dados e eventos.
- **Retrofit:** Integração com APIs REST para buscar dados em tempo real.
- **Hilt (Dagger):** Para injeção de dependências.
- **Room Database:** Persistência de dados local.
- **Jetpack Navigation:** Navegação segura e eficiente.
- **Material Design 3:** Para um design moderno e acessível.

---

## 🔄 **Arquitetura do Projeto**

O CryptoTracker foi desenvolvido seguindo uma abordagem de arquitetura limpa:

- **Camada de Apresentação:** Gerenciada por `ViewModels` e `State` com Compose.
- **Camada de Domínio:** Contém as regras de negócio e casos de uso.
- **Camada de Dados:** Gerencia fontes de dados, como APIs e banco de dados local.

Fluxo de dados:

1. **UI** -> Dispara eventos de usuário.
2. **ViewModel** -> Processa eventos e aciona casos de uso.
3. **Use Cases** -> Implementam a lógica de negócio e interagem com os repositórios.
4. **Repositórios** -> Integram fontes de dados (API e Room).
5. **Resposta** -> Atualiza o estado na interface do usuário.

---

## 🌐 **Como Configurar o Projeto**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/CryptoTracker.git
   ```

2. **Abra o projeto no Android Studio:** Certifique-se de usar a versão mais recente.

3. **Configure a API Key:** Se necessário, adicione sua chave de API no arquivo `Constants.kt`:
   ```kotlin
   const val API_KEY = "sua-api-key"
   ```

4. **Sincronize o projeto:** Aguarde o Gradle baixar as dependências.

5. **Execute o aplicativo:** Rode em um emulador ou dispositivo físico.

---

## 🎨 **Imagens do App**

### Tela Inicial
*Uma lista com as criptomoedas mais populares e suas informações principais.*

### Tela de Detalhes
*Gráficos e informações detalhadas sobre uma criptomoeda selecionada.*

> **Nota:** Imagens podem ser adicionadas futuramente para maior clareza.

---

## 🖋️ **O que Aprendi?**

Durante o desenvolvimento deste projeto, foram consolidados conceitos importantes:

- Como estruturar projetos Android com **Clean Architecture**.
- Uso de **Jetpack Compose** para construir UIs declarativas.
- Integração de **Fluxos Reativos** com Kotlin **Flow** e ViewModel.
- Implementação de **injeção de dependência** com Hilt.
- Melhorias no tratamento de estados e side-effects na interface.

---

## ✍️ **Contribuições**

Contribuições são bem-vindas! Caso tenha sugestões, ideias ou encontre problemas, abra uma [issue](https://github.com/seu-usuario/CryptoTracker/issues) ou envie um [pull request](https://github.com/seu-usuario/CryptoTracker/pulls).

---

## 📚 **Licença**

Este projeto é distribuído sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

