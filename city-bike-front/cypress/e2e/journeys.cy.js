describe('Journeys page', () => {

    beforeEach(() => {
      cy.visit('http://localhost:3000/journeys')
    })

    it('journey list not empty', () => {
    cy.contains('Viiskulma')
    })

})