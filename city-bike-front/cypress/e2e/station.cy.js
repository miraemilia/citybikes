describe('Single station view', () => {

    beforeEach(() => {
      cy.visit('http://localhost:3000/stations/1')
      cy.wait(200)
    })

    it('station found', () => {
        cy.get('html').should('not.contain', 'Station not found')
        cy.contains('Kaivopuisto')
      })

})