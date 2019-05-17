package DAO;

/**
 *
 * @author mateus
 */
import Persistence.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Models.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void Create(Funcionario newFuncionario) {
        Connection connect = ConnectionBD.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connect.prepareStatement("INSERT INTO users (nome, cargo, salario, salarioLiquido) VALUES (?, ?, ?, ?)");

            stmt.setString(1, newFuncionario.getNome());
            stmt.setString(2, newFuncionario.getCargo());
            stmt.setFloat(3, newFuncionario.getSalario());
            stmt.setFloat(4, newFuncionario.getSalarioLiquido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o novo funcionário!");
        } finally {
            ConnectionBD.closeConnection(connect, stmt);
        }
    }

    public void Update(Funcionario updateFuncionario) {
        Connection connect = ConnectionBD.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connect.prepareStatement("UPDATE users SET nome = ?, cargo = ?, salario = ? WHERE id = ?");

            stmt.setString(1, updateFuncionario.getNome());
            stmt.setString(2, updateFuncionario.getCargo());
            stmt.setFloat(3, updateFuncionario.getSalario());
            stmt.setInt(4, updateFuncionario.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Informações do funcionário atualizadas!");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Erro ao atualizar as informações do funcionário!");
        } finally {
            ConnectionBD.closeConnection(connect, stmt);
        }
    }
    
    public void Delete (Funcionario deleteFuncionario) {
        Connection connect = ConnectionBD.getConnection();  
        PreparedStatement stmt = null;
        
        try {
            stmt = connect.prepareStatement("DELETE FROM users WHERE id = ?");
            
            stmt.setInt(1, deleteFuncionario.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Desculpe, houve um erro ao excluir o funcionário!");
        } finally {
            ConnectionBD.closeConnection(connect, stmt);
        }
    }
    
    public List<Funcionario> listaFuncionario() {
        Connection connect = ConnectionBD.getConnection();  
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Funcionario> listFuncionarios = new ArrayList<>(); 
        
        try {
            stmt = connect.prepareStatement("SELECT * FROM users");
            result = stmt.executeQuery();
            
            while(result.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setSalario(result.getFloat("salario"));
                funcionario.setSalarioLiquido(result.getFloat("salarioLiquido"));
                
                listFuncionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir a lista");
        } finally {
            ConnectionBD.closeConnection(connect, stmt, result);
        }
        
        return listFuncionarios;
    }

    public List<Funcionario> listaFuncionario(String nome) {
        Connection connect = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;

        List<Funcionario> listFuncionarios = new ArrayList<>();

        try {
            stmt = connect.prepareStatement("SELECT * FROM users WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            result = stmt.executeQuery();

            while (result.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setSalario(result.getFloat("salario"));
                funcionario.setSalarioLiquido(result.getFloat("salarioLiquido"));

                listFuncionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o funcionário");
        } finally {
            ConnectionBD.closeConnection(connect, stmt, result);
        }

        return listFuncionarios;
    }
}
