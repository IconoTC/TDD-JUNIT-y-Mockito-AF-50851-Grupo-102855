# Curso de TTD con Junit y Mockito

## Instalación

- [JDK](https://www.oracle.com/java/technologies/downloads/)
- [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/packages/release/2026-09/r/eclipse-ide-enterprise-java-and-web-developers)
  - *Help > Eclipse Marketplace ... > [Spring Tools (aka Spring Tool Suite)](https://marketplace.eclipse.org/content/spring-tools-aka-spring-tool-suite)*
  - *[Project Lombok](https://projectlombok.org/downloads/lombok.jar)*

## Kata y ejemplos

- [GildedRose](https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements_es.md)
- [Pet Clinic (Spring)](https://github.com/spring-projects/spring-petclinic)

## Contenedores (instalación opcional)

### Configuración de puertos dinámicos en Windows

    netsh int ipv4 set dynamic tcp start=51000 num=14536

### Instalación Docker Desktop

- [WSL 2 feature on Windows](https://learn.microsoft.com/es-es/windows/wsl/install)
- [Docker Desktop](https://www.docker.com/get-started/)

#### Alternativas a Docker Desktop

- [Podman](https://podman.io/docs/installation)
- [Rancher Desktop](https://rancherdesktop.io/)

### Crear una red en Docker

    docker network create testing

### Desplegar contenedores

#### Servidor de correo electrónico

    docker run -d --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog

    docker network connect testing mailhog

#### SonarQube

    docker run -d --name sonarQube --publish 9000:9000 --network testing sonarqube:latest

#### MySQL (ejemplo)

    docker run -d --name mysql-sakila-test -p 3306:3306 --network testing -e MYSQL_ROOT_PASSWORD=root jamarton/mysql-sakila
