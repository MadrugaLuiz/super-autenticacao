package br.com.autenticacao.DAO;

import java.util.List;

import br.com.autenticacaoModel.Usuario;
import br.com.autenticacaoUtil.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements GenericDAO {

	// para implementar alguma coisa

	private Connection conn;

	// Construtor vazio da classe ProdutoDAOImpl, iniciando a conexão com o banco
	// de dados atráves da classe ConnectionFactory

	public UsuarioDAOImpl() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Object> listarTodos() {
		List<Object> lista = new ArrayList<Object>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id,nome,email,isAtivo  FROM PRODUTO";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIsAtivo(rs.getBoolean("isAtivo"));
				lista.add(usuario);
			}
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar Produto" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				System.out.println("Problemas na DAO ao fechar conexão!" + e.getMessage());
			}
		}

		return lista;
	}

	public Object listarPorId(int id) {

		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "Select id, nome, email, isAtivo" + "FROM usuario" + "WHERE id = ? ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIsAtivo(rs.getBoolean("isAtivo"));
			}

		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar usuario por id " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				System.out.println("Problemas na DAO ao fechar  conexão " + e.getMessage());
				e.printStackTrace();

			}
		}

		return usuario;
	}

	public boolean cadastrar(Object object) {
		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO usuario (nome,email, senha, isAtivo) VALUES (?,?,MD5(?),?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, usuario.getIsAtivo());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao cadastrar usuario" + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problemas na DAO ao fechar conexão!" + e.getLocalizedMessage());
				e.printStackTrace();
			}
		}

	}

	public boolean alterar(Object object) {
		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "UPDATE produto SET usuario" + "nome = ?" + "email=?" + "senha =?" + "isAtivo=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, usuario.getIsAtivo());
			return false;
		} catch (SQLException ex) {
			System.out.println("Erros na DAO ao alterar Produto!" + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {

				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problemas na DAO ao fechar conexão!" + e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void excluir(int id) {

		String sql = "DELETE FROM usuario WHERE id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException ex) {
			System.out.println("Problemas naDAO ao excluir Produto" + ex.getMessage());

		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problemas naDAO ao fechar conexão!" + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
