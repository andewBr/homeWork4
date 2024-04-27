package by.javaguru.je.jdbc.dao;

import by.javaguru.je.jdbc.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.javaguru.je.jdbc.utils.ConnectionManager.open;

public class TeacherDAO implements DAO<Long, Teacher> {

    private static String SELECT_TEACHER_BY_ID_SQL = "select name, subject from teachers where id = ?";
    private static String SELECT_ALL_TEACHERS_SQL = "select id, name, subject_id from teacher";
    private static String UPDATE_TEACHER_SQL = "update teacher set name = ?, subject_id = ? where id = ?";
    private static String INSERT_TEACHER_SQL = "insert into teacher (name, subject_id) values (?, ?)";
    private static String DELETE_TEACHER_SQL = "delete from teacher where id = ?";


    @Override
    public Teacher update(Teacher teachers, Long id) throws SQLException {
        Teacher teacher = new Teacher();
        try (Connection connection = open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, teachers.getName());
            preparedStatement.setLong(2, teachers.getsubjectId());
            preparedStatement.setLong(3, id);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    teacher = preparedTeacher(generatedKeys);
                }
            }
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TEACHERS_SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                teachers.add(preparedTeacher(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        try (Connection connection = open()) {
            ResultSet resultSet = connection.prepareStatement(SELECT_ALL_TEACHERS_SQL, Statement.RETURN_GENERATED_KEYS).executeQuery();
            if (resultSet.next()) {
                return Optional.of(preparedTeacher(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Teacher save(Teacher teachers) throws SQLException {
        Teacher teacher = null;
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teachers.getName());
            statement.setLong(2, teachers.getsubjectId());
            int result = statement.executeUpdate();
            if (result == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                teacher = preparedTeacher(generatedKeys);
            }
        }
        return teacher;
    }

    @Override
    public Teacher delete(Long id) throws SQLException {
        Teacher teacher = null;
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    teacher = preparedTeacher(generatedKeys);
                }
            }
        }
        return teacher;
    }

    private Teacher preparedTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getLong(3));
        return teacher;
    }
}