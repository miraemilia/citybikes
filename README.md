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
- listing stations
- viewing single station data

## Technologies used:
- Frontend: React, Typescript, MaterialUI
- Backend: Spring
- Database: MySQL, H2
- Tests: Cypress

## Prerequisites for running the project:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## How to run the project?
- clone the project
- Frontend:
    - nagigate to folder city-bikes-front
    - build image with `docker build -t citybikes .`
    - run with `docker run -p 3000:3000 citybikes` (runs at http://localhost:3000/)
- Production database (MySQL):
    - navigate to folder city-bikes-back
    - run with `docker-compose -f docker-compose_db.yml up -d` (runs at http://localhost:3306/)
- Backend:
    - nagigate to folder city-bikes-back
    - Production: run with `docker-compose -f docker-compose_prod.yml up -d` (runs at http://localhost:8080/)
    - Development: run with `docker-compose -f docker-compose_dev.yml up -d` (runs at http://localhost:8080/)

### How to run tests?
- both frontend and backend running (see above)
- run Cypress with `npm run cypress:open`

## Changes made in 2023
- pagination added
- ManyToOne relationship between Station (id) and Journey (departure/returnStation) entities -> departure/returnStationName removed from Journey object
- MySQL database
- Spring profiles: dev, prod, test

## TODO:
- unit testing for backend
- more E2E tests
- station search
- better exception handling

### What next?
- handling multiple full csv files
- prettier UI