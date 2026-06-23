# Projeto da cadeira de Persistência de Objetos(POB)
*  **Curso:** Tecnólogo em Sistemas para Internet(cstsi)
*  **Membros:**
   *  Erick Felipe
   *  Mikael de Moura
   *  Melquisedeque Vital
*  **Objetivo:** Praticar a criação de classes e a persistência dos objetos na memória usando diferentes tecnologias em cada etapa

---

## Descrição do Modelo de Classes(Sistema de Shows)

*  Sistema que gerencia shows que ocorrem em cidades com seus devidos artistas
*  Cada show só pode ocorrer em uma cidade com um artista
*  Cada artista pode ter vários shows agendados
*  Cada cidade pode ter vários shows agendados

### Diagrama UML

![Diagrama UML Sistema de Shows](Show-UML.drawio.png)

### Consultas Realizadas no Banco de Dados(Persistência de Objeto)

*  quais os shows na data X
*  quais os artistas que vao se apresentar na cidade de nome X
*  quais os artistas que tem mais de N shows na cidade X

---

## Etapas do Projeto
### 1º Etapa: db4o

*  Etapa realizada utilizando db4o para fazer a persistência
*  Utilização do SODA(Simple Object Database Acess) e de Filtros Customizados para realizar as consultas
*  Release feito

### 2º Etapa: JPA

*  Etapa realiza utilizando JPA para a persistência
*  Utilização de JPQL(Java Persistence Query Language) para realizar consultas
*  Release feito

### 3º Etapa: JPA com arquitetura MVC e utilização de Facade

* Etapa em progresso
