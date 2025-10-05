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
- **Gymder**: Foco em swipe para encontrar parceiros de ginásio, mas sem grupos ou aprovação manual.
- **Fit Buddy**: Matching por objetivos, mas sem integração de localização avançada.
- **Tag Team**: Encontra parceiros fitness, mas falta gestão de candidaturas e UI moderna.

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

![Login Page]([image-1.png](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Create%20offer.png?raw=true)) ![Landing Page]([image-2.png](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Landing%20page.png?raw=true)) ![Create Offer]([image-3.png](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Mockups/Login.png?raw=true))

- **Poster:**

![Poster](https://github.com/Rodrigocrispim/GymBro/blob/main/entrega%201/Poster/GymBro_Poster.png?raw=true)
## 9. Planeamento e Calendarização
- **Plano de Trabalhos**: 
  - Semana 1 (23/09-05/10): Pesquisa, mockups e 1ª entrega.
  - Semana 2-5 (05/10-26/10): Desenvolvimento do protótipo (app, backend, BD).
  - Semana 6-11 (27/10-09/11): Refinamento e 2ª entrega.
  - Semana 12-14 (10/11-14/12): Versão final e 3ª entrega.
- **Gráfico de Gantt**: [Insira aqui o gráfico exportado do ClickUp/Excel ou link].
- **Metodologia**: Divisão de tarefas no click up, comunicação no discord e atualizações no GitHub.

## 10. Conclusão
O projeto GymBro visa criar uma solução inovadora para motivar treinos sociais, com entregas progressivas até dezembro de 2025. Os próximos passos incluem validar o protótipo e expandir funcionalidades como chat e notificações.

## 11. Bibliografia
- Documentação oficial Kotlin: [kotlinlang.org](https://kotlinlang.org)
- Guia Spring Boot: [spring.io](https://spring.io)
- Tutoriais Android: [developer.android.com](https://developer.android.com)
