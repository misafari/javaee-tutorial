package com.safari.task.dao;

import com.safari.task.dao.conf.MySqlConfiguration;
import com.safari.task.entity.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TaskDataAccess {
    private static TaskDataAccess instance = null;
    private Connection connection = null;

    public static TaskDataAccess getInstance() {
        if (instance == null) instance = new TaskDataAccess();
        return instance;
    }

    private TaskDataAccess() {
    }

    public Optional<Task> findById(long taskId) throws Exception {
        try (var statement = getConnection().prepareStatement("select * from task where id = ?")) {
            statement.setLong(1, taskId);

            var resultSet = statement.executeQuery();

            if (resultSet.getFetchSize() == 0) {
                return Optional.empty();
            }

            if (resultSet.getFetchSize() > 1) {
                throw new Exception("more than 1 item is founded");
            }

            var task = new Task(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getBoolean("is_done")
            );

            return Optional.of(task);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void updateOrSave(Task task) {
        if (task.getId() == 0) insert(task);
        else update(task);
    }

    public void deleteById(long taskId) {
        try (var statement = getConnection().prepareStatement("delete from task where id = ?")) {
            statement.setLong(1, taskId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert(Task task) {
        try (var statement = getConnection().prepareStatement("insert into task(name, is_done) values(?,?)")) {
            statement.setString(1, task.getName());
            statement.setBoolean(2, task.isDone());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(Task task) {
        try (var statement = getConnection().prepareStatement("update task set name = ? and is_done = ? where id = ?")) {
            statement.setString(1, task.getName());
            statement.setBoolean(2, task.isDone());
            statement.setLong(3, task.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        if (connection == null)
            connection = MySqlConfiguration.getInstance().getConnection();

        return connection;
    }
}
