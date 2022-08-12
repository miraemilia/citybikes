describe('City bike app', () => {

  beforeEach(() => {
    cy.visit('http://localhost:3000')
  })
  
  it('visit front page', () => {
    cy.contains('Helsinki city bike app')
  })
})