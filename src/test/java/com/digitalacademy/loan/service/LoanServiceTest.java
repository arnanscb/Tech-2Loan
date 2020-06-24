package com.digitalacademy.loan.service;

import com.digitalacademy.loan.exception.LoanException;
import com.digitalacademy.loan.model.LoanInfoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanServiceTest {
    @InjectMocks
    LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanService = new LoanService();
    }

    @DisplayName("Test get loan info by id equal 1 should return information")
    @Test
    void testGetLoanInfoByIdEqual1() throws Exception {
        LoanInfoModel resp = loanService.getLoanInfoById(1L);

        assertEquals("1", resp.getId().toString());
        assertEquals("Ok", resp.getStatus());
        assertEquals("102-222-2200", resp.getAccountPayable());
        assertEquals("102-222-2200", resp.getAccountReceivable());
        assertEquals(4000000.00, resp.getPrincipalAmount());
    }

    @DisplayName("Test get loan info by id equal 2 should throw Loan Exception loan info noy found")
    @Test
    void testGetLoanInfoByIdEqual2() throws Exception {
        Long reqParam = 2L;
        LoanException thrown = assertThrows(LoanException.class,
                () -> loanService.getLoanInfoById(reqParam),
                "Exception loanInfoById(reqParam) to throw, but it didn't");
        assertEquals(400, thrown.getHttpStatus().value());
        assertEquals("LOAN4002", thrown.getLoanError().getCode());
        assertEquals("Loan information not found", thrown.getLoanError().getMessage());
    }

    @DisplayName("Test get loan info by id equal 3 should throw Exception: Test throw new exception")
    @Test
    void testGetLoanInfoByIdEqual3() throws Exception {
        Long reqParam = 3L;
        Exception thrown = assertThrows(Exception.class,
                () -> loanService.getLoanInfoById(reqParam),
                "Excepted loanInfoById(reqParam) to throw, but it didn't");
        assertEquals("Test throw new exception",thrown.getMessage());
    }
}
