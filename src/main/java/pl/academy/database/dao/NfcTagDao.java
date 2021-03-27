package pl.academy.database.dao;

import pl.academy.database.entity.NfcTag;

import java.sql.SQLException;
import java.util.List;

public interface NfcTagDao {
    void save(NfcTag tag);
    NfcTag findById(Long id);
    List<NfcTag> findAll();
    void deleteById(Long id);
}
