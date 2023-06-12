describe('Stations list', () => {

    beforeEach(() => {
      cy.visit('/stations')
    })

    it('station list contains station', () => {
        cy.contains('Unioninkatu')
      })

    it('station link works', () => {
        cy.contains('Unioninkatu').click()
        cy.url().should('include', 'stations/11')
    })

    it('filter working', () => {
      cy.contains('Kaivopuisto')
      cy.contains('Karhupuisto')
      cy.get('#filter').type('ai')
      cy.contains('Kaivopuisto')
      cy.contains('Karhupuisto').should('not.exist') 
    })

})