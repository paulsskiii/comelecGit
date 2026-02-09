package com.learning.smartwallet.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learning.smartwallet.dto.ExpenseRequestDTO;
import com.learning.smartwallet.dto.ExpenseResponseDTO;
import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.exception.ExpenseNotFoundException;
import com.learning.smartwallet.repository.ExpenseRepository;

@ExtendWith(MockitoExtension.class) // Enables Mockito for this test class
public class ExpenseServiceTest {

    @Mock // Create a fake repository
    private ExpenseRepository repository;

    @InjectMocks // Inject the fake repository into the real service
    private ExpenseService service;

    @Test
    void whenGetExpenseById_andExists_thenReturnExpense() {
        // 1. Arrange (Prepare data)
        Long expenseId = 1L;
        Expense mockExpense = new Expense();
        mockExpense.setId(expenseId);
        mockExpense.setDescription("Groceries");
        mockExpense.setAmount(BigDecimal.valueOf(50.00));

        // STUBBING: Teach the mock how to behave
        // "When repository.findById(1) is called, return an Optional containing our
        // mockExpense"
        when(repository.findById(expenseId)).thenReturn(Optional.of(mockExpense));

        // 2. Act (Call the method we are testing)
        ExpenseResponseDTO result = service.getExpenseById(expenseId);

        // 3. Assert (Verify the result)
        assertNotNull(result); // It shouldn't be null
        assertEquals("Groceries", result.getDescription()); // Name should match
        assertEquals(BigDecimal.valueOf(50.00), result.getAmount()); // Amount should match

        // VERIFICATION: Ensure the service actually called the repo
        verify(repository, times(1)).findById(expenseId);
    }

    @Test
    void whenGetExpenseById_andNotExists_thenThrowException() {
        // 1. Arrange
        Long invalidId = 999L;

        // Teach mock to return empty
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        // 2. Act & Assert
        // We expect this block of code to throw ExpenseNotFoundException
        assertThrows(ExpenseNotFoundException.class, () -> {
            service.getExpenseById(invalidId);
        });

        // Verify repo was called
        verify(repository, times(1)).findById(invalidId);
    }

    @Test
    void whenCreateExpense_thenSaveAndReturn() {
        // 1. Arrange
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setDescription("Netflix");
        dto.setAmount(BigDecimal.valueOf(15.99));
        dto.setCategory("Subscription");

        Expense savedExpense = new Expense();
        savedExpense.setId(1L); // Database usually generates this
        savedExpense.setDescription("Netflix");
        savedExpense.setAmount(BigDecimal.valueOf(15.99));

        // When repo.save is called with ANY Expense object, return the savedExpense we
        // created above
        when(repository.save(any(Expense.class))).thenReturn(savedExpense);

        // 2. Act
        Expense result = service.createExpense(dto);

        // 3. Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Netflix", result.getDescription());
    }

    @Test
    void testArraySize() {

        Expense e1 = new Expense();
        e1.setId(1L);
        e1.setDescription("Netflix");
        e1.setAmount(BigDecimal.valueOf(15.99));
        
        Expense e2 = new Expense();
        e2.setDescription("Netflix2");
        e2.setAmount(BigDecimal.valueOf(15.99));
        
        when(repository.findAll()).thenReturn(Arrays.asList(e1, e2));

        assertEquals(2, repository.findAll().size());
    }

}
