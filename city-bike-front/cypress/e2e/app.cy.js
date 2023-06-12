describe('City bike app', () => {

  beforeEach(() => {
    cy.visit('/')
  })
  
  it('visit front page', () => {
    cy.contains('Helsinki city bike app')
  })

  it('navigate to stations page', () => {
    cy.contains('Stations').click()
    cy.contains('Stations listing')
  })

  it('navigate to single station page', () => {
    cy.contains('Stations').click()
    cy.contains('Unioninkatu').click()
    cy.wait(200)
    cy.contains('TOP 5 return stations')
  })

  it('navigate to journeys page', () => {
    cy.contains('Journeys').click()
    cy.contains('Journeys per page')
  })

})