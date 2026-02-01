package com.mfi.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.mfi.bean.Borrower;
import com.mfi.bean.Installment;
import com.mfi.bean.Loan;
import com.mfi.dao.BorrowerDAO;
import com.mfi.dao.InstallmentDAO;
import com.mfi.dao.LoanDAO;

public class MfiService {

    BorrowerDAO borrowerDAO = new BorrowerDAO();
    LoanDAO loanDAO = new LoanDAO();
    InstallmentDAO installmentDAO = new InstallmentDAO();

    public Borrower viewBorrowerDetails(String borrowerID) {
        if (borrowerID == null)
            return null;

        return borrowerDAO.findBorrower(borrowerID);
    }

    public List<Borrower> viewAllBorrowers() {
        return borrowerDAO.viewAllBorrowers();
    }

    public boolean registerNewBorrower(Borrower borrower) {

        if (borrower == null ||
            borrower.getBorrowerID() == null ||
            borrower.getFullName() == null ||
            borrower.getPrimaryPhone() == null ||
            borrower.getVillageOrArea() == null)
            return false;

        int age = Period.between(
                borrower.getDateOfBirth().toLocalDate(),
                LocalDate.now()).getYears();

        if (age < 18 || age > 70)
            return false;

        if (borrowerDAO.findBorrower(borrower.getBorrowerID()) != null)
            return false;

        borrower.setStatus("ACTIVE");
        return borrowerDAO.insertBorrower(borrower);
    }

    public Loan viewLoanDetails(String loanID) {
        if (loanID == null)
            return null;

        return loanDAO.findLoan(loanID);
    }

    public List<Loan> viewAllLoans() {
        return loanDAO.viewAllLoans();
    }

    public boolean createLoanAccount(Loan loan) {

        if (loan == null ||
            loan.getPrincipalAmount().compareTo(BigDecimal.ZERO) <= 0 ||
            loan.getAnnualInterestRate().compareTo(BigDecimal.ZERO) <= 0 ||
            loan.getTermMonths() <= 0)
            return false;

        Borrower b = borrowerDAO.findBorrower(loan.getBorrowerID());
        if (b == null || !"ACTIVE".equals(b.getStatus()))
            return false;

        loan.setOutstandingPrincipal(loan.getPrincipalAmount());
        loan.setStatus("ACTIVE");

        return loanDAO.insertLoan(loan);
    }


    public List<Installment> listInstallmentsByLoan(String loanID) {
        return installmentDAO.findInstallmentsByLoan(loanID);
    }

    public boolean postRepayment(int installmentID, BigDecimal amount) {

        if (installmentID <= 0 || amount.compareTo(BigDecimal.ZERO) <= 0)
            return false;

        Installment i = installmentDAO.findInstallment(installmentID);
        if (i == null || "PAID".equals(i.getStatus()))
            return false;

        BigDecimal paid = i.getPaidAmount().add(amount);
        i.setPaidAmount(paid);

        if (paid.compareTo(i.getDueAmount()) >= 0)
            i.setStatus("PAID");
        else
            i.setStatus("PARTIAL");

        Installment i1 = installmentDAO.findInstallment(installmentID);
        loanDAO.updateOutstandingPrincipal(i.getLoanID(), amount);

        return true;
    }

    public List<Installment> listOverdueInstallments(Date referenceDate) {
        return installmentDAO.findOverdueInstallments(referenceDate);
    }

    public boolean removeBorrower(String borrowerID) {

        if (borrowerID == null || borrowerID.trim().isEmpty())
            return false;

        if (!loanDAO.findActiveLoansByBorrower(borrowerID).isEmpty())
            return false;

        return borrowerDAO.deleteBorrower(borrowerID);
    }
}
