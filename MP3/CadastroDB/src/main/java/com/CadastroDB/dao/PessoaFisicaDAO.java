package com.CadastroDB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.CadastroDB.model.PessoaFisica;
import com.CadastroDB.model.util.ConexaoBD;
import com.CadastroDB.model.util.SequenceManager;

@Repository
public class PessoaFisicaDAO {

    private ConexaoBD conexaoBD;
    private SequenceManager sequenceManager;

    public PessoaFisicaDAO() {
        this.conexaoBD = new ConexaoBD();
        this.sequenceManager = new SequenceManager();
    }

    public PessoaFisica obterPorId(Long id) 
    {
        String sql = "SELECT * FROM PessoaFisica JOIN Pessoa ON PessoaFisica.id = Pessoa.id WHERE PessoaFisica.id = ?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement statement = conexaoBD.getPrepared(sql)) 
            {
            statement.setLong(1, id);
            try (ResultSet resultSet = conexaoBD.getSelect(statement)) 
            {
                if (resultSet.next()) 
                {
                    return extractPessoaFisicaFromResultSet(resultSet);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }    

    public List<PessoaFisica> obterTodos() {

        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT PessoaFisica.id, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaFisica.cpf\n" + //
                "FROM PessoaFisica\n" + //
                "JOIN Pessoa ON PessoaFisica.idPessoa = Pessoa.id;";
        try (Connection connection = conexaoBD.gerarConexaoBD();
                PreparedStatement statement = conexaoBD.getPrepared(sql);
                ResultSet resultSet = conexaoBD.getSelect(statement)) 
        {
            while (resultSet.next()) 
            {
                pessoas.add(extractPessoaFisicaFromResultSet(resultSet));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return pessoas;  
    }    

    public void adicionar(PessoaFisica pessoa) 
    {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPF = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";
        try (Connection connection = conexaoBD.gerarConexaoBD();
            PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
            PreparedStatement stmtPessoaFisica = conexaoBD.getPrepared(sqlPF)) 
        {
            stmtPessoa.setString(1, pessoa.pegarNome());
            stmtPessoa.setString(2, pessoa.pegarLogradouro());
            stmtPessoa.setString(3, pessoa.pegarCidade());
            stmtPessoa.setString(4, pessoa.pegarEstado());
            stmtPessoa.setString(5, pessoa.pegarTelefone());
            stmtPessoa.setString(6, pessoa.pegarEmail());

            int affectedRows = stmtPessoa.executeUpdate();

            if (affectedRows == 0) 
            {
                throw new SQLException("Falha ao inserir Pessoa, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmtPessoa.getGeneratedKeys()) 
            {
                if (generatedKeys.next()) 
                {
                    Long id = generatedKeys.getLong(1);
                    stmtPessoaFisica.setLong(1, id);
                    stmtPessoaFisica.setString(2, pessoa.pegarCpf());
                    stmtPessoaFisica.executeUpdate();
                } 
                else 
                {
                    throw new SQLException("Falha ao obter o ID da Pessoa, nenhum ID obtido.");
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoa) 
    {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPF = "UPDATE PessoaFisica SET cpf=? WHERE id=?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conexaoBD.getPrepared(sqlPF)) 
        {
            stmtPessoa.setString(1, pessoa.pegarNome());
            stmtPessoa.setString(2, pessoa.pegarLogradouro());
            stmtPessoa.setString(3, pessoa.pegarCidade());
            stmtPessoa.setString(4, pessoa.pegarEstado());
            stmtPessoa.setString(5, pessoa.pegarTelefone());
            stmtPessoa.setString(6, pessoa.pegarEmail());
            stmtPessoa.setLong(7, pessoa.pegarId());

            stmtPessoaFisica.setString(1, pessoa.pegarCpf());
            stmtPessoaFisica.setLong(2, pessoa.pegarId());

            stmtPessoa.executeUpdate();
            stmtPessoaFisica.executeUpdate();

        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) 
    {
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        String sqlPF = "DELETE FROM PessoaFisica WHERE id=?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conexaoBD.getPrepared(sqlPF)) 
        {

            stmtPessoa.setLong(1, id);
            stmtPessoaFisica.setLong(1, id);

            stmtPessoa.executeUpdate();
            stmtPessoaFisica.executeUpdate();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private PessoaFisica extractPessoaFisicaFromResultSet(ResultSet resultSet) throws SQLException 
    {
        PessoaFisica pessoa = new PessoaFisica();
        pessoa.criarId(resultSet.getLong("id"));
        pessoa.criarNome(resultSet.getString("nome"));
        pessoa.criarLogradouro(resultSet.getString("logradouro"));
        pessoa.criarCidade(resultSet.getString("cidade"));
        pessoa.criarEstado(resultSet.getString("estado"));
        pessoa.criarTelefone(resultSet.getString("telefone"));
        pessoa.criarEmail(resultSet.getString("email"));
        pessoa.criarCpf(resultSet.getString("cpf"));
        return pessoa;
    }
}
