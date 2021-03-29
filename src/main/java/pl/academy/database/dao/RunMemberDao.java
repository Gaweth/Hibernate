package pl.academy.database.dao;

import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public interface RunMemberDao {
    void save(RunMember member);

    RunMember findById(Long id);

    List<RunMember> findAll();

    void deleteById(Long id);

    List<RunMember> findByNameFragment(String fragment);

    List<RunMember> findByStartNumberAndRunId(Integer startNumber, Long runId);
}




