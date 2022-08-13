# Helsinki city bike app

## Project description

This project is a pre-assignment for applying to Solita Dev Academy/summer 2022. The assignment was to create a web application for displaying data from journeys made with city bikes in the Helsinki Capital area.

### Features of the application
#### Backend  
- importing data from files in csv format to an in-memory database
    - data is imported at project startup
    - all stations
    - journey data from one day (31st of May 2021)
- input data validation
    - data type validation
    - journeys with the minimum of 10m of distance and 10sec of duration are imported
- endpoints for:
    - 100 journeys
    - stations
    - number of journeys from/to every station
    - top5 return/departure stations for every station
    - average distance from/to each station
#### Frontend
- listing journeys as a table
- listing stations
- viewing single station data

## Technologies used:
- Frontend: React, Typescript, MaterialUI
- Backend: Spring
- Database: H2
- Tests: Cypress

## Prerequisites for running the project:
- [Docker](https://docs.docker.com/get-docker/)

## How to run the project?
- clone the project
- Frontend:
    - nagigate to folder city-bikes-front
    - build image with `docker build -t citybikes .`
    - run with `docker run -p 3000:3000 citybikes` (runs at http://localhost:3000/)
- Backend:
    - nagigate to folder city-bikes-back/backend
    - build image with `docker build -t citybikes-back .`
    - run with `docker run -p 8080:8080 citybikes-back` (runs at http://localhost:8080/)
### How to run tests?
- both frontend and backend running (see above)
- run Cypress with `npm run cypress:open`

## TODO:
- more E2E tests
- pagination
- station search
- add relationship between Station (id) and Journey (departure/returnStation) entities -> remove departure/returnStationName from Journey
- better exception handling

### What next?
- unit testing for backend
- real database (mySql or PostgreSQL?)
- handling multiple full csv files
- prettier UI