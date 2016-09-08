package net.sorokin.dao.userdao;


import net.sorokin.dao.daoexceptions.NotUniqueUserEmailException;
import net.sorokin.entity.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> selectAll() throws SQLException;

    public User selectByEmail(String email) throws SQLException;

    public int insert(User user);

    public void setDatasource(DataSource datasource) throws Exception;
}
