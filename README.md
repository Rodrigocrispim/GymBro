 # Proposta Inicial - GymBro

## 1. Identificação
- **Grupo**: Rodrigo Crispim, Nuno Franco, Rodrigo Suarez
- **Nome do Projeto**: GymBro
- **Link GitHub**: [https://github.com/Rodrigocrispim/GymBro]

## 2. Palavras-chave
Fitness, desenvolvimento pessoal, localização, ofertas de treino, motivação, ginásio, app móvel.

## 3. Breve Descrição da Aplicação e Problema Resolvido
A GymBro é uma aplicação móvel desenhada para conectar pessoas que procuram parceiros ou grupos para treinar, seja em ginásios ou ao ar livre. O problema resolvido é a falta de motivação ou compromisso para treinos solitários, especialmente pensado para pessoas extrovertidas ou com baixa disciplina, oferecendo um sistema de matching e ofertas aprovadas manualmente para criar conexões reais e treinos inesquecíveis.

## 4. Objetivos e Motivação
- **Objetivo Geral**: Desenvolver uma app que facilite a descoberta de parceiros de treino com base em compatibilidade e localização.
- **Objetivos Específicos**: 
  - Criar perfis detalhados com filtros (estilo, nível, horários).
  - Implementar um sistema de ofertas onde usuários podem se candidatar e o criador aprova ou rejeita.
  - Integrar funcionalidades mobile como mapas, notificações e chats.
- **Motivação**: Aumentar a adesão a rotinas fitness através da socialização e compromisso mútuo.

## 5. Público-Alvo
Pessoas entre 18 e 40 anos, incluindo extrovertidos que gostam de treinar em grupo, indivíduos com baixa motivação que precisam de companhia para não faltarem ao treino, frequentadores de ginásios ou locais ao ar livre em áreas urbanas e até pessoas novas em cidades que queiram reunir-se com pessoas com a mesma mentalidade de treino e superação.

## 6. Pesquisa de Mercado
Foram analisados apps semelhantes:
- **Gymder**: Foco em swipe para encontrar parceiros de ginásio, mas sem grupos ou aprovação manual.
- **Fit Buddies**: Matching por objetivos, mas sem integração de localização avançada.
- **Tag Team**: Encontra parceiros fitness, mas carece de chat ou gestão de ofertas.

A GymBro diferencia-se ao combinar ofertas com aprovação, suporte a grupos, uso de localização recomendação e efiltragem de ofertas, etc.

## 7. Guiões de Teste
1. **Caso de Utilização Core**: Criação e edição de perfil.
   - Passo 1: Usuário acessa app e preenche dados (nome, localização, estilo de treino).
   - Passo 2: Salva perfil e verifica recomendações.
2. **Caso de Utilização 1**: Criação e gestão de oferta de treino.
   - Passo 1: Usuário clica no botão "+", cria oferta (ex.: "Treino às 18h, ginásio X").
   - Passo 2: Outro usuário se candidata, criador aprova.
3. **Caso de Utilização 2**: Busca e interação com ofertas.
   - Passo 1: Usuário filtra ofertas por localização.
   - Passo 2: Clica "Candidatar-se" e recebe feedback.

## 8. Descrição da Solução
- **Descrição Genérica**: A app oferece um sistema de perfis, matching tipo Tinder, e um feed de ofertas com candidaturas aprovadas manualmente, integrado com chat opcional e mapas.
- **Enquadramento nas Unidades Curriculares**:
  - **Programação Mobile**: Desenvolvimento em Android Studio com Kotlin e Jetpack Compose.
  - **POO**: Classes para perfis, ofertas e candidaturas.
  - **Bases de Dados**: BD relacional (PostgreSQL) com tabelas para usuários e interações.
  - **Competências Comunicacionais**: Relatórios, poster e vídeo.
  - **Matemática Discreta**: Análise de compatibilidade em matching.
- **Requisitos Técnicos**:
  - Frontend: Android Studio (versão Koala/Narwhal), Kotlin, Jetpack Compose.
  - Backend: Spring Boot com arquitetura MVC e REST.
  - BD: PostgreSQL com scripts (create.sql, populate.sql, queries.sql).
  - Ferramentas: GitHub, ClickUp, Figma, Discord.
- **Arquitetura Proposta**: Cliente-servidor com API REST conectando app e BD.
- **Mockups e Interfaces**: 

## 9. Planeamento e Calendarização
- **Plano de Trabalhos**: 
  - Semana 1 (23/09-28/09): Pesquisa, mockups e 1ª entrega.
  - Semana 2-5 (29/09-26/10): Desenvolvimento do protótipo (app, backend, BD).
  - Semana 6-11 (27/10-09/11): Refinamento e 2ª entrega.
  - Semana 12-14 (10/11-14/12): Versão final e 3ª entrega.
- **Gráfico de Gantt**:
- **Metodologia**: Ágil, com sprints semanais e revisões no ClickUp.

## 10. Conclusão
O projeto GymBro visa criar uma solução inovadora para motivar treinos sociais, com entregas progressivas até dezembro de 2025. Os próximos passos incluem validar o protótipo e expandir funcionalidades como chat e notificações.

## 11. Bibliografia
- Documentação oficial Kotlin: [kotlinlang.org](https://kotlinlang.org)
- Guia Spring Boot: [spring.io](https://spring.io)
- Tutoriais Android: [developer.android.com](https://developer.android.com)

