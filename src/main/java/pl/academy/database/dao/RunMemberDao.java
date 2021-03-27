package pl.academy.database.dao;

import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public interface RunMemberDao {

    void save(RunMember member) throws SQLException;
    RunMember findById(Long id) throws SQLException;;
    List<RunMember> findALl() throws SQLException;;
    void deleteById(Long id) throws SQLException;

    List<RunMember> findByNameFragment(String fragment) throws SQLDataException;

}

