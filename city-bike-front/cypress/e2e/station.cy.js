describe('Single station view', () => {

    beforeEach(() => {
      cy.visit('/stations/1')
      cy.wait(200)
    })

    it('station found', () => {
        cy.get('html').should('not.contain', 'Station not found')
        cy.contains('Kaivopuisto')
        cy.contains('Meritori 1')
      })

})