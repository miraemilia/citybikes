describe('Stations list', () => {

    beforeEach(() => {
      cy.visit('http://localhost:3000/stations')
    })

    it('station list contains station', () => {
        cy.contains('Unioninkatu')
      })

    it('station link works', () => {
        cy.contains('Unioninkatu').click()
        cy.url().should('include', 'stations/11')
    })

})