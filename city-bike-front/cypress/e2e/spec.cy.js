describe('City bike app', () => {

  beforeEach(() => {
    cy.visit('http://localhost:3000')
  })
  
  it('visit front page', () => {
    cy.contains('Helsinki city bike app')
  })

  it('station found on stations page', () => {
    cy.contains('Stations').click()
    cy.contains('Unioninkatu')
  })

  it('station found', () => {
    cy.contains('Stations').click()
    cy.contains('Unioninkatu').click()
    cy.wait(200)
    cy.get('html').should('not.contain', 'Station not found')
    cy.contains('Eteläesplanadi 1')
  })

  it('journeys shown', () => {
    cy.contains('Journeys').click()
    cy.contains('Viiskulma')
  })
})