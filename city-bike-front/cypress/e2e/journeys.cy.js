describe('Journeys page', () => {

    beforeEach(() => {
      cy.visit('http://localhost:3000/journeys')
      cy.wait(200)
    })

    it('journey list not empty', () => {
        cy.get('#tablerows > tr').should('not.be.empty')
    })

    it('journey list size 25 per page', () => {
        cy.get('#tablerows > tr').should('have.length', 25)
    })

    it('per page select works', () => {
        cy.get('[data-testid="selectPerPage"]').as('selector')
        cy.get('@selector').should('have.value', 25)
        cy.get('#selectPerPage').click()
        cy.get('[role="option"]').contains('75').click()
        cy.get('@selector').should('have.value', 75)
        
    })

    it('select per page changes number of rows', () => {
        cy.get('[data-testid="selectPerPage"]').as('selector')
        cy.get('@selector').should('have.value', 25)
        cy.get('#selectPerPage').click()
        cy.get('[role="option"]').contains('50').click()
        cy.get('@selector').should('have.value', 50)
        cy.get('#tablerows > tr').should('have.length', 50)
        
    })

    it('page is changed', () => {
        cy.get("#tablerows").contains('td', '#24')
        cy.contains('Elfvik').should('not.exist')
        cy.get('[aria-label="Go to next page').click()
        cy.get("#tablerows").contains('td', '#27')
    })

})