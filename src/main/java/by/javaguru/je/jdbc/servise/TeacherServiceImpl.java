package by.javaguru.je.jdbc.servise;

import by.javaguru.je.jdbc.dao.TeacherDAO;
import by.javaguru.je.jdbc.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements GenericService<Teacher, Long> {

    TeacherDAO teacherDAO = new TeacherDAO();
    private static TeacherServiceImpl INSTANCE = new TeacherServiceImpl();

    public static TeacherServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherDAO.findById(id).get();
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDAO.findAll();
    }

    @Override
    public Teacher insert(Teacher teachers) throws SQLException {
        return teacherDAO.save(teachers);
    }

    @Override
    public Teacher update(Teacher teachers, Long aLong) throws SQLException {
        return teacherDAO.update(teachers, aLong);
    }

    @Override
    public Teacher delete(Long id) throws SQLException {
        return teacherDAO.delete(id);
    }
}