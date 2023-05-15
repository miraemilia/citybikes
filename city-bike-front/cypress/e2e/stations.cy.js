describe('Stations list', () => {

    beforeEach(() => {
      cy.visit('http://localhost:3000/stations')
    })

    it('station list not empty', () => {
        cy.contains('Unioninkatu')
      })

})