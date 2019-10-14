package com.herokuapp.schoolmvc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.herokuapp.schoolmvc.model.Employee;
import com.herokuapp.schoolmvc.model.Month;
import com.herokuapp.schoolmvc.model.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ExpenseDAO extends JdbcDaoSupport {

    @Autowired
    public ExpenseDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private EmployeeDAO employeeDao;

    public List<Expense> listAllExpenses(){
        String SQL = ExpenseMapper.BASE_SQL;
        return this.getJdbcTemplate().query(SQL, new ExpenseMapper());
    }

    public List<Expense> listExpensesByEmployeeId(Long empId){
        String SQL = ExpenseMapper.BASE_SQL + "WHERE empId = " + empId;
        return this.getJdbcTemplate().query(SQL, new ExpenseMapper());
    }

    public void newExpense(Long empId, Long manger, Long amount, Month month){
        String SQL = "INSERT INTO Expense(manager, cost, date) VALUES(?, ?, ?)";

        this.getJdbcTemplate().update(SQL);
    }

    public class ExpenseMapper implements RowMapper<Expense> {
 
        public static final String BASE_SQL = "SELECT * FROM Expense e";
     
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {

            Long expenseId = rs.getLong("expenseid");
            Long cost = rs.getLong("cost");
            Date date = rs.getDate("date");
            Employee manager = employeeDao.findEmployeeById(rs.getLong("manager"));

            return new Expense(expenseId, cost, date, manager);
        }
     
    }
}