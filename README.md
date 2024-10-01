# Talk to Refugee

<section>
    <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
    <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
    <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">
</section>

O Talk to Refugee é uma plataforma projetada para facilitar a integração de refugiados no Brasil, conectando-os com pessoas dispostas a oferecer ajuda. Em um cenário crescente de migrações forçadas devido a perseguições religiosas; raciais; políticas; guerras civis e graves violações de direitos humanos, o sistema visa superar barreiras culturais e linguísticas, por meio da comunicação eficaz entre refugiados e voluntários. O software oferece um chat em tempo real e uma interface intuitiva, desenvolvida para proporcionar uma experiência de usuário otimizada. Com o objetivo de auxiliar na adaptação e reintegração dos refugiados em suas novas comunidades.


## 🚀 Stack utilizada

* Spring Boot
* Spring Boot Starter Test
* Spring Data JPA
* Spring Validation
* Spring Web
* Spring Security
* JSON Web Token
* Docker
* PostgreSQL
* Flyway
* ControllerAdvice & Problem Details
* Hibernate Validator



## 💻 Rodando localmente

A aplicação back-end é uma API Restful composta principalmente pela lingaguem Java 22 e o ecossistema Spring. A execução do sistema depende da engine de containerização Docker, com a ferramenta já instalada e inicializada, execute os seguintes comandos:

Clone o projeto

    git clone https://github.com/carmassilucas/t2r-backend

Acesse a pasta Docker

    cd ./docker/

Execute o arquivo docker-compose.yml

    docker compose up -d

Instale as dependências com Maven Wrapper

    ./mvnw clean install

Execute o projeto

    ./mvnw exec:java
