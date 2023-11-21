package com.CadastroDB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.CadastroDB.model.PessoaJuridica;
import com.CadastroDB.model.util.ConexaoBD;
import com.CadastroDB.model.util.SequenceManager;


@Repository
public class PessoaJuridicaDAO {

    private ConexaoBD conexaoBD;
    private SequenceManager sequenceManager;

    public PessoaJuridicaDAO() {
        this.conexaoBD = new ConexaoBD();
        this.sequenceManager = new SequenceManager();
    }

    public PessoaJuridica obterPorId(Long id) 
    {
        String sql = "SELECT * FROM PessoaJuridica JOIN Pessoa ON PessoaJuridica.id = Pessoa.id WHERE PessoaJuridica.id = ?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement statement = conexaoBD.getPrepared(sql))
        {
            statement.setLong(1, id);
            try (ResultSet resultSet = conexaoBD.getSelect(statement)) 
            {
                if (resultSet.next()) 
                {
                    return extractPessoaJuridicaFromResultSet(resultSet);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() 
    {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM PessoaJuridica JOIN Pessoa ON PessoaJuridica.id = Pessoa.id";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement statement = conexaoBD.getPrepared(sql);
             ResultSet resultSet = conexaoBD.getSelect(statement)) 
             {
            while (resultSet.next()) 
            {
                pessoas.add(extractPessoaJuridicaFromResultSet(resultSet));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void adicionar(PessoaJuridica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPJ = "INSERT INTO PessoaJuridica (idPessoa, cnpj) VALUES (last_insert_rowid(), ?)";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conexaoBD.getPrepared(sqlPJ)) 
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
                throw new SQLException("Falha ao inserir Pessoa.");
            }
    
            ResultSet generatedKeys = stmtPessoa.getGeneratedKeys();
            if (generatedKeys.next()) {
                long idPessoa = generatedKeys.getLong(1);
    
                // Inserir na tabela PessoaJuridica com o ID da Pessoa
                stmtPessoaJuridica.setLong(1, idPessoa);
                stmtPessoaJuridica.setString(1, pessoa.pegarCnpj());
                stmtPessoaJuridica.executeUpdate();
            } 
            else 
            {
                throw new SQLException("Falha ao obter o ID.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoa) 
    {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPJ = "UPDATE PessoaJuridica SET cnpj=? WHERE id=?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conexaoBD.getPrepared(sqlPJ)) 
        {
    
            stmtPessoa.setString(1, pessoa.pegarNome());
            stmtPessoa.setString(2, pessoa.pegarLogradouro());
            stmtPessoa.setString(3, pessoa.pegarCidade());
            stmtPessoa.setString(4, pessoa.pegarEstado());
            stmtPessoa.setString(5, pessoa.pegarTelefone());
            stmtPessoa.setString(6, pessoa.pegarEmail());
            stmtPessoa.setLong(7, pessoa.pegarId());
    
            stmtPessoaJuridica.setString(1, pessoa.pegarCnpj());
            stmtPessoaJuridica.setLong(2, pessoa.pegarId());
    
            stmtPessoa.executeUpdate();
            stmtPessoaJuridica.executeUpdate();
    
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }    

    public void excluir(Long id) {
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        String sqlPJ = "DELETE FROM PessoaJuridica WHERE id=?";
        try (Connection connection = conexaoBD.gerarConexaoBD();
             PreparedStatement stmtPessoa = conexaoBD.getPrepared(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conexaoBD.getPrepared(sqlPJ)) 
        {

            stmtPessoa.setLong(1, id);
            stmtPessoaJuridica.setLong(1, id);

            stmtPessoa.executeUpdate();
            stmtPessoaJuridica.executeUpdate();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private PessoaJuridica extractPessoaJuridicaFromResultSet(ResultSet resultSet) throws SQLException {
        PessoaJuridica pessoa = new PessoaJuridica();
        pessoa.criarId(resultSet.getLong("id"));
        pessoa.criarNome(resultSet.getString("nome"));
        pessoa.criarLogradouro(resultSet.getString("logradouro"));
        pessoa.criarCidade(resultSet.getString("cidade"));
        pessoa.criarEstado(resultSet.getString("estado"));
        pessoa.criarTelefone(resultSet.getString("telefone"));
        pessoa.criarEmail(resultSet.getString("email"));
        pessoa.criarCnpj(resultSet.getString("cnpj"));
        return pessoa;
    }
}
