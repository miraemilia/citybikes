# Helsinki city bike app

## Project description

This project is a pre-assignment for applying to Solita Dev Academy/summer 2022  (further developed for summer 2023 application). The assignment was to create a web application for displaying data from journeys made with city bikes in the Helsinki Capital area.

### Features of the application
#### Backend  
- importing data from files in csv format to a MySQL database(prod) or an in-memory database(dev)
    - data is imported at project startup
    - all stations
    - journey data from one day (31st of May 2021)
- input data validation
    - data type validation
    - journeys with the minimum of 10m of distance and 10sec of duration are imported
- endpoints for:
    - paginated journeys
    - stations
    - number of journeys from/to every station
    - top5 return/departure stations for every station
    - average distance from/to each station
#### Frontend
- listing journeys as a table (25/50/75/100 per page)
- listing stations with possibility to filter
- viewing single station data

## Technologies used:
- Frontend: React, Typescript, MaterialUI
- Backend: Spring
- Database: MySQL, H2
- Tests: Cypress, Jest, Junit

## Prerequisites for running the project:
- [Git](https://github.com/git-guides/install-git)
- [Docker](https://docs.docker.com/get-docker/)

## How to run the project?
- clone the project
- run Docker Desktop
- frontend:
    - nagigate to folder city-bikes-front
    - build image with `docker build -t citybikes .`
    - run with `docker run -p 3000:3000 citybikes` (runs at http://localhost:3000/)
- backend:
    - navigate to folder city-bikes-back
    - add a file named .env with content (replace *):
        ```
        MYSQL_DATABASE: citybikedb
        MYSQL_USER: *
        MYSQL_PASSWORD: *
        H2_USER: *
        H2_PASSWORD: *
        ```
    - production database (MySQL): run with `docker-compose -f docker-compose_db.yml up -d` (runs at http://localhost:3306/)
    - production: run with `docker-compose -f docker-compose_prod.yml up -d` (runs at http://localhost:8080/) (may take over 10 mins)
    - development: run with `docker-compose -f docker-compose_dev.yml up -d` (runs at http://localhost:8080/) (faster option)

## How to run tests?

### E2E testing
- [npm](https://nodejs.org/en/download) required
- both frontend and backend running (see above)
- nagigate to folder city-bikes-front
- run Cypress with `npm run cypress:open`

### Backend testing
- `docker run -it city-bike-back-app mvn test`


### Frontend testing
- `docker run -it citybikes npm run test`

## Changes made in 2023
- pagination added
- ManyToOne relationship between Station (id) and Journey (departure/returnStation) entities -> departure/returnStationName removed from Journey object
- MySQL database
- Spring profiles: dev, prod, test
- unit tests for backend
- integration tests for backend
- improved validation in backend
- Jest tests for frontend
- more e2e tests
- filtering stations in frontend
- stations on map in frontend
- caching enabled to somewhat speed up data import from csv

## TODO:
- settings for running e2e tests in Docker
- more comprehensive testing
- better exception handling
- frontend data validation
- faster import from csv into database (-> enables use of bigger data files)
- handling multiple full csv files
- prettier UI