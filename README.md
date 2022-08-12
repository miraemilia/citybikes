# Helsinki city bike app

## Project description

This project is a pre-assignment for applying to Solita Dev Academy/summer 2022. The assignment was to create a web application for displaying data from journeys made with city bikes in the Helsinki Capital area.

### Features of the application
#### Backend  
- importing data from files in csv format to an in-memory database
    - data is imported at project startup
    - all stations
    - 100 rows of journey data
- input data validation
    - data type validation
    - journeys with the minimum of 10m of distance and 10sec of duration are imported
- endpoints for journeys and stations
#### Frontend
- listing journeys as a table
- listing stations

## Technologies used:
- Frontend: React, Typescript, MaterialUI
- Backend: Spring
- Database: H2
- Tests: Cypress

## Prerequisites for running the project:
- [Node](https://nodejs.org/en/download/) (v16.13.1 with npm v8.1.2 were used for development)
- [Docker]()


## Configurations:

## How to run the project?
- clone the project
- Frontend:
    - run `npm install` in the frontend folder
    - start frontend with `npm start` (runs at http://localhost:3000/)
- Backend:
    - nagigate to folder city-bikes-back/backend
    - build image with `docker build -t citybikes .`
    - run with `docker run -p 8080:8080 citybikes`
### How to run tests?
- both frontend and backend running (see above)
- run Cypress with `npm run cypress:open`

## TODO:
- E2E tests
- single station endpoint
- viewing individual station data
- better exception handling
- pagination
- more queries
- containerize backend (and frontend?)
- cleanup

### What next?
- testing for backend
- handling multiple full csv files
- real database (mySql or PostgreSQL?)
- prettier UI