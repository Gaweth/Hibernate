package pl.academy.database.dao;

import pl.academy.database.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    void save(Run run);
    Run findById(Long id);
    List<Run> findAll();
    void deleteById(Long id);
    List<Run> findRunsWithMembersLimitRange(int min, int max);
}
