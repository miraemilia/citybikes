import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event';
import PaginationControl from './PaginationControl';

    describe('journey list tests', () => {

        test('perPage change called', () => {
            
            const handlePerPageMock = jest.fn()
            const handlePageChangeMock = jest.fn()
            render(<PaginationControl 
                handlePerPageChange={handlePerPageMock}
                handlePageChange={handlePageChangeMock}
                perPage={25}
                pageCount={5}
            />)
            const optionsButton = screen.getByRole("button", { name : /25/i})
            userEvent.click(optionsButton)
            userEvent.click(screen.getByText("50"))
            expect(handlePerPageMock).toHaveBeenCalled();

        })

    })