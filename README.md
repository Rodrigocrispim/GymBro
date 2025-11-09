 # Proposta Inicial - Gym Bro

## 1. Identificação
- **Grupo**: Rodrigo Crispim, Nuno Franco, Rodrigo Suarez
- **Nome do Projeto**: Gym Bro
- **Universidade:** Iade
- **Link GitHub**: [https://github.com/Rodrigocrispim/GymBro]

## 2. Palavras-chave
Fitness, desenvolvimento pessoal, localização, ofertas de treino, motivação, ginásio, app móvel.

## 3. Breve Descrição da Aplicação e Problema Resolvido
O GymBro é uma aplicação móvel desenhada para conectar pessoas que procuram parceiros ou grupos para treinar, seja em ginásios ou ao ar livre. O problema resolvido é a falta de motivação ou compromisso para treinos solitários, especialmente pensado para pessoas extrovertidas ou com baixa disciplina, oferecendo um sistema de matching e ofertas aprovadas manualmente para criar conexões reais e treinos inesquecíveis.

## 4. Objetivos e Motivação
- **Objetivo Geral**: Desenvolver uma app que facilite a descoberta de parceiros de treino com base em compatibilidade e localização.
- **Objetivos Específicos**: 
  - Criar perfis detalhados com filtros (estilo, nível, horários).
  - Implementar um sistema de ofertas onde usuários podem se candidatar e o criador aprova ou rejeita a candidatura.
  - Integrar funcionalidades mobile, notificações e chats.
- **Motivação**: Aumentar a adesão a rotinas fitness através da socialização e compromisso mútuo.

## 5. Público-Alvo
Pessoas entre 18 e 40 anos, incluindo extrovertidos que gostam de treinar em grupo, indivíduos com baixa motivação que precisam de companhia para não faltarem ao treino, frequentadores de ginásios ou locais ao ar livre em áreas urbanas e até pessoas novas em cidades que queiram reunir-se com pessoas com a mesma mentalidade de treino e superação.

## 6. Pesquisa de Mercado
Foram analisadas apps semelhantes:
- **Fit Buddy**: Matching por objetivos, mas sem integração de localização avançada.
- **Tag Team**: Encontra parceiros fitness, mas falta gestão de candidaturas e UI moderna.
- **BlinkFIT**
- **Sporpartner**

O GymBro diferencia-se ao combinar ofertas com aprovação, suporte a grupos, uso de localização, recomendação e filtragem de ofertas.

## 7. Guiões de Teste
1. **Caso de Utilização Core**: O joão é um jovem de 25 anos, extrovertido mas com baixa motivação para treinar sozinho. Ele treina em ginásios urbanos em lisboa e faz treinos ocasionais de calistenia
 em parques da cidade mas sente falta de compromisso com alguém para manter a rotina de treinos e instala o Gym Bro. O joão abre a app pela primeira vez e é direcionado para a para a página de "Login/Register", ele seleciona "Register" e insere o email, a passaword e o nome. A app carrega a página do seu perfil onde ele completa os seus dados e preferências pessoais. A app valida os dados e atualiza a página principal com recomendações de ofertas baseadas nas suas preferências. O joão pode agora criar uma nova oferta ou candidatar-se a uma existente. 
2. **Caso de Utilização 1**: A Maria seleciona uma oferta compatível com os seus interesses (ex: a do João) e clica "Apply To Join". A app envia a notificação ao João, e a candidatura fica pendente de aprovação e é colocada na secção de "Candidaturas Pendentes".  
3. **Caso de Utilização 2**: O João recebe a notificação, analisa a candidatura e decide aprovar. A redireciona para o chat privado para combinarem o treino e falarem de detalhes. A maria recebe a notificação da sua candidatura aprovada e agora tem um parceiro de treino.

## 8. Descrição da Solução
- **Descrição Genérica**: A app oferece um sistema de perfis, recomendação de ofertas, e um feed de ofertas com candidaturas aprovadas manualmente, integrado com um chat.
- **Enquadramento nas Unidades Curriculares**:
  - **Programação Mobile**: Desenvolvimento do FrontEnd em Android Studio com Kotlin.
  - **POO**: BackEnd em Java para todo o funcionamento da aplicação.
  - **Bases de Dados**: Base de Dados em MySql com tabelas para usuários e interações.
  - **Competências Comunicacionais**: Relatórios, poster e vídeo.
  - **Matemática Discreta**: Análise de compatibilidade e recomendação de ofertas.
- **Requisitos Técnicos**:
  - Frontend: Android Studio (versão Koala/Narwhal), Kotlin, Jetpack Compose.
  - Backend: Spring Boot com arquitetura MVC e REST.
  - BD: PostgreSQL com scripts (create.sql, populate.sql, queries.sql).
  - Ferramentas: GitHub, ClickUp, Figma, Discord.
- **Arquitetura Proposta**: Cliente-servidor com API REST conectando app e BD.
- **Mockups e Interfaces**: 

![Login Page](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Login.png?raw=true)
![image-2.png](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Landing%20page.png?raw=true)
![Create Offer](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Create%20offer.png?raw=true)

- **Poster:**
<img src="https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Poster/Encontra%20o%20teu%20Gym%20Bro%20filtrado%20por%20estilo,%20localiza%C3%A7%C3%A3o%20e%20experi%C3%AAncia...jpg?raw=true" width=300 height=600>

## 9. Documentação da API REST (v1.0)

Esta é a primeira versão da documentação da API REST para a aplicação "Gym Bro". O objetivo desta API é servir de *backend* (Spring Boot) para a aplicação móvel (Kotlin).

A API segue os princípios REST, utilizando verbos HTTP e JSON para a comunicação.


### Módulo: Ofertas

#### 1. Listar todas as Ofertas Abertas

Este *endpoint* é público e devolve uma lista de todas as ofertas de treino que estão atualmente com o estado `ABERTA`. As ofertas `FECHADA` são automaticamente filtradas e não são devolvidas.

**Método:** `GET`

**URL:** `/api/ofertas`

**URLs de Exemplo:**
* **Local (para testes):** `http://localhost:8080/api/ofertas`
* **Público (via ngrok):** `https://[o-teu-link-ngrok].ngrok-free.app/api/ofertas`

## 10. Planeamento e Calendarização
- **Plano de Trabalhos**: 
  - Semana 1 (23/09-05/10): Pesquisa, mockups e 1ª entrega.
  - Semana 2-5 (05/10-26/10): Desenvolvimento do protótipo (app, backend, BD).
  - Semana 6-11 (27/10-09/11): Refinamento e 2ª entrega.
  - Semana 12-14 (10/11-14/12): Versão final e 3ª entrega.
- **Gráfico de Gantt**:
<img src="https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/grafico_gantt/gantt.png?raw=true" width=800 height=500>

  **Metodologia:** Divisão de tarefas no click up, comunicação no discord e atualizações no GitHub.

## 11. Bibliografia
- Documentação MarkDown Syntax: [markdownguide.org](https://www.markdownguide.org/basic-syntax/)

## 12. Dicionário de Dados (Modelo v2.2)

Este Dicionário de Dados detalha a estrutura do Modelo Entidade-Relação (MER) implementado na base de dados `find_partners_db`. O modelo foi otimizado para a aplicação focada em musculação (ex: Push, Pull, Legs) e segue o padrão `snake_case` (minúsculas) para nomes de tabelas e colunas.

A base de dados está estruturalmente dividida em quatro categorias:
1.  **Entidades de Domínio:** As "tabelas-lista" que contêm opções pré-definidas (ex: Localizações, Níveis).
2.  **Entidade Principal:** A tabela `utilizador`, que armazena os dados de autenticação e perfil.
3.  **Entidades Transacionais:** Tabelas que registam ações ou "eventos" (ex: `oferta`, `candidatura`).
4.  **Entidades de Ligação:** As "tabelas-ponte" que resolvem relações Muitos-para-Muitos (N-para-M).

---

### Entidades de Domínio (Tabelas-Lista)

#### Tabela: `localizacao`
**Descrição:** Armazena as localizações (concelhos e distritos) pré-definidas que um utilizador ou oferta pode ter.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `localizacao_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) da localização. |
| `distrito` | `VARCHAR(100)`| - | Sim | O nome do distrito (ex: "Lisboa"). |
| `concelho` | `VARCHAR(100)`| - | Sim | O nome do concelho (ex: "Sintra"). |

#### Tabela: `nivel_treino`
**Descrição:** Armazena os níveis de experiência pré-definidos (ex: Iniciante).

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `nivel_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) do nível. |
| `nivel_nome` | `VARCHAR(45)` | UNIQUE | Sim | O nome descritivo do nível (ex: "Intermédio"). |

#### Tabela: `objetivo_fitness`
**Descrição:** Armazena os objetivos de fitness pré-definidos (ex: Ganho de Massa Muscular).

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `objetivo_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) do objetivo. |
| `objetivo_nome` | `VARCHAR(100)`| UNIQUE | Sim | O nome descritivo do objetivo (ex: "Perda de Peso"). |

#### Tabela: `tipo_treino`
**Descrição:** Armazena os tipos de treino de musculação pré-definidos, conforme o foco do projeto (ex: Push, Pull).

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `tipo_treino_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) do tipo de treino. |
| `nome` | `VARCHAR(50)` | UNIQUE | Sim | O nome do tipo de treino (ex: "Push", "Legs"). |

#### Tabela: `dia_semana`
**Descrição:** Armazena os dias da semana para a funcionalidade de disponibilidade.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `dia_id` | `INT` | **PK** | Sim | Identificador único (1 = Segunda, 7 = Domingo). |
| `nome_dia` | `VARCHAR(20)` | UNIQUE | Sim | O nome do dia (ex: "Segunda-feira"). |

#### Tabela: `periodo_dia`
**Descrição:** Armazena os períodos/horários do dia para a funcionalidade de disponibilidade.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `periodo_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) do período. |
| `periodo_nome` | `VARCHAR(50)` | UNIQUE | Sim | O nome do período (ex: "Manhã (08h-12h)"). |

---

### 2. Entidade Principal

#### Tabela: `utilizador`
**Descrição:** Armazena toda a informação do utilizador, combinando dados de autenticação (login) e dados de perfil (preferências).

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `utilizador_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) do utilizador. |
| `email` | `VARCHAR(255)`| UNIQUE | Sim | Email de login. Tem de ser único. |
| `password_hash` | `VARCHAR(255)`| - | Sim | A password do utilizador (encriptada). |
| `nome_completo` | `VARCHAR(150)`| - | Sim | O nome de exibição do utilizador. |
| `biografia` | `TEXT` | - | Não | Texto descritivo opcional do perfil. |
| `url_foto_perfil` | `VARCHAR(512)` | - | Não | O URL para a imagem de perfil (guardada externamente). |
| `localizacao_id` | `INT` | **FK** (para `localizacao`)| Não | A localização preferida do utilizador (pode ser preenchida *após* o registo). |
| `nivel_id` | `INT` | **FK** (para `nivel_treino`) | Não | O nível de treino preferido do utilizador. |
| `objetivo_id` | `INT` | **FK** (para `objetivo_fitness`) | Não | O objetivo de fitness preferido do utilizador. |

---

### 3. Entidades Transacionais (Ações)

#### Tabela: `oferta`
**Descrição:** Armazena a informação de uma "oferta" (anúncio) criada por um utilizador para encontrar um parceiro de treino.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `oferta_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) da oferta. |
| `criador_id` | `INT` | **FK** (para `utilizador`) | Sim | O ID do utilizador que criou a oferta. |
| `tipo_treino_id` | `INT` | **FK** (para `tipo_treino`) | Sim | O tipo de treino específico desta oferta (ex: 'Legs'). |
| `localizacao_id` | `INT` | **FK** (para `localizacao`) | Sim | A localização específica onde esta oferta é válida. |
| `nivel_id` | `INT` | **FK** (para `nivel_treino`) | Sim | O nível de experiência procurado nesta oferta. |
| `titulo` | `VARCHAR(100)`| - | Sim | O título do "anúncio" (ex: "Parceiro de Push"). |
| `descricao` | `MEDIUMTEXT` | - | Não | Descrição detalhada opcional da oferta. |
| `status_oferta` | `ENUM(...)` | - | Sim | O estado da oferta. ('ABERTA' ou 'FECHADA'). Default: 'ABERTA'. |
| `data_criacao` | `DATETIME` | - | Sim | Data/hora de criação da oferta. Default: Agora. |

#### Tabela: `candidatura`
**Descrição:** Armazena um pedido (candidatura) feito por um utilizador a uma `oferta` específica.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `candidatura_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) da candidatura. |
| `oferta_id` | `INT` | **FK** (para `oferta`) | Sim | O ID da oferta à qual o utilizador se está a candidatar. |
| `candidato_id` | `INT` | **FK** (para `utilizador`) | Sim | O ID do utilizador que está a fazer a candidatura. |
| `status` | `ENUM(...)` | - | Sim | O estado da candidatura ('PENDENTE', 'ACEITE', 'REJEITADA'). Default: 'PENDENTE'. |
| `comentario_inicial` | `TEXT` | - | Não | Mensagem opcional enviada com a candidatura. |
| `data_envio` | `DATETIME` | - | Sim | Data/hora de envio. Default: Agora. |

#### Tabela: `chat_mensagem`
**Descrição:** (Funcionalidade Opcional) Armazena uma mensagem individual de chat. Cada `candidatura` funciona como uma "sala de chat".

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `mensagem_id` | `INT` | **PK** | Sim | Identificador único (auto-incrementado) da mensagem. |
| `candidatura_id` | `INT` | **FK** (para `candidatura`) | Sim | A "sala de chat" (candidatura) a que esta mensagem pertence. |
| `remetente_id` | `INT` | **FK** (para `utilizador`) | Sim | O ID do utilizador que enviou a mensagem. |
| `texto_mensagem`| `MEDIUMTEXT` | - | Sim | O conteúdo da mensagem. |
| `data_envio` | `DATETIME` | - | Sim | Data/hora de envio. Default: Agora. |

---

### 4. Entidades de Ligação (Tabelas-Ponte N-M)

#### Tabela: `utilizador_interesses`
**Descrição:** Tabela-ponte (N-para-M) que armazena os *vários* tipos de treino (ex: Push, Pull) que interessam a *um* utilizador.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `utilizador_id` | `INT` | **PK / FK** (para `utilizador`) | Sim | O ID do utilizador. |
| `tipo_treino_id`| `INT` | **PK / FK** (para `tipo_treino`) | Sim | O ID do tipo de treino que interessa a esse utilizador. |

#### Tabela: `utilizador_disponibilidade`
**Descrição:** Tabela-ponte (N-para-M) que armazena os *vários* horários (pares de dia/período) em que *um* utilizador está disponível.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `utilizador_id` | `INT` | **PK / FK** (para `utilizador`) | Sim | O ID do utilizador. |
| `dia_id` | `INT` | **PK / FK** (para `dia_semana`) | Sim | O ID do dia da semana. |
| `periodo_id` | `INT` | **PK / FK** (para `periodo_dia`) | Sim | O ID do período do dia. |

#### Tabela: `oferta_disponibilidade`
**Descrição:** Tabela-ponte (N-para-M) que armazena os *vários* horários (pares de dia/período) em que *uma* oferta está disponível.

| Nome do Atributo | Tipo de Dados | Chave? | Obrigatório? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| `oferta_id` | `INT` | **PK / FK** (para `oferta`) | Sim | O ID da oferta. |
| `dia_id` | `INT` | **PK / FK** (para `dia_semana`) | Sim | O ID do dia da semana. |
| `periodo_id` | `INT` | **PK / FK** (para `periodo_dia`) | Sim | O ID do período do dia. |

## 13. Personas
1. **Persona n1**: João Martins, 21 anos, estuda Engenharia Informática no Porto. Entre aulas, projetos e horas a programar, o dia de João passa quase sempre sentado, acompanhado de café e fast-food. O espelho e o cansaço constante lembram-no de que precisa mudar, mas o sentimento de vergonha e a ideia de ser julgado pela sua aparência ao entrar no ginásio o bloqueavam de conquistar qualquer progresso.
Depois de mais uma semana a adiar o início no ginásio, João vê um anúncio da app GymBro. Curioso com a ideia de encontrar um parceiro de treino, instala-a.
Dentro da app, cria o seu perfil, partilha timidamente o seu objetivo: “Perder peso, ganhar massa muscular e criar uma rotina saudável” e que gostaria de um parceiro de treino para treinos de força . Em poucos minutos, recebe um “match” com Miguel um estudante de Educação Física, de 18 anos, habituado ao ginásio e disposto a ajudar iniciantes.
Miguel envia-lhe uma mensagem motivadora e oferece-se para treinar com ele 2 vezes por semana. João sente confiança, porque percebe que não está sozinho nesta caminhada.
Nos primeiros treinos, Miguel mostra-lhe os exercícios básicos, corrige a postura e celebra pequenas vitórias com ele. O que antes era motivo de ansiedade, tornar-se gratificante.
Depois de 2 meses, João perdeu peso levando agora um estilo de vida saudavel. O ginásio deixou de ser um lugar assustador e tornou-se parte da sua rotina.
2. **Persona n2**: Ricardo Silva, 23 anos, jogava futebol amador e tinha uma carreira muito promissora. Mas, após uma lesão no joelho durante um jogo, a sua rotina mudou drasticamente. Entre fisioterapia, limitações e medo de voltar a treinar, perdeu massa muscular e confiança.
A sua fisioterapeuta recomenda reforçar o corpo no ginásio antes de regressar ao campo, mas Ricardo receia treinar sozinho. Numa pesquisa rápida na internet, encontra o GymBro e decide tentar.
Ao entrar na app, define o seu propósito: “Recuperar força e estabilidade para voltar ao futebol”. Em poucas horas, encontra um “match” com André, 29 anos, formado em desporto e também ex-lesionado no tornozelo, entendendo assim perfeitamente o que Ricardo está a sentir.
Treino após treino, Ricardo sente-se mais forte e seguro. Depois de 10 semanas, volta ao relvado pela primeira vez para um treino leve. 
O que era medo transforma-se em coragem. Ricardo não só regressa ao futebol, como descobre que pode inspirar outros atletas lesionados a não desistirem.

<img src="https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Poster/Encontra%20o%20teu%20Gym%20Bro%20filtrado%20por%20estilo,%20localiza%C3%A7%C3%A3o%20e%20experi%C3%AAncia...jpg?raw=true" width=300 height=600>

## 14. Conclusão
O projeto GymBro visa criar uma solução inovadora para motivar treinos sociais, com entregas progressivas até dezembro de 2025. Os próximos passos incluem validar o protótipo e expandir funcionalidades como chat, localização, recomendação de ofertas e notificações.
