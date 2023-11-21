package com.CadastroDB.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class ConexaoBD {

    private static final String URL = "jdbc:sqlite:./database/cadastro.db";

    public Connection gerarConexaoBD() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public PreparedStatement getPrepared(String sql) throws SQLException {
        return gerarConexaoBD().prepareStatement(sql);
    }

    public ResultSet getSelect(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public void fecharConexaoBD(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexaoBD(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexaoBD(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
