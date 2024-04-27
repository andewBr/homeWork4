package by.javaguru.je.jdbc.servise;

import by.javaguru.je.jdbc.dao.SubjectDAO;
import by.javaguru.je.jdbc.entity.SchoolSubjects;

import java.sql.SQLException;
import java.util.List;

public class SubjectsServiceImpl implements GenericService<SchoolSubjects, Long> {

    private SubjectDAO subjectDAO = new SubjectDAO();
    private static SubjectsServiceImpl INSTANCE = new SubjectsServiceImpl();

    public static SubjectsServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public SchoolSubjects findById(Long id) {
        return subjectDAO.findById(id).get();
    }

    @Override
    public List<SchoolSubjects> findAll() {
        return subjectDAO.findAll();
    }

    @Override
    public SchoolSubjects insert(SchoolSubjects schoolSubjects) throws SQLException {
        return subjectDAO.save(schoolSubjects);
    }

    @Override
    public SchoolSubjects update(SchoolSubjects schoolSubjects, Long id) throws SQLException {
        return subjectDAO.update(schoolSubjects, id);
    }

    @Override
    public SchoolSubjects delete(Long id) throws SQLException {
        return subjectDAO.delete(id);
    }
}