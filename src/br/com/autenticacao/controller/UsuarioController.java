package br.com.autenticacao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.autenticacao.DAO.GenericDAO;
import br.com.autenticacao.DAO.UsuarioDAOImpl;
import br.com.autenticacaoModel.Usuario;

public class UsuarioController {

	public List<Usuario> listarTodos() {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			List<Usuario> lista = new ArrayList<Usuario>();

			for (Object object : dao.listarTodos()) {
				lista.add((Usuario) object);

			}
			return lista;

		} catch (Exception ex) {
			System.out.println("Problemas na controller para listar Produtos" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}

	}

	public Usuario listarPorId(int id) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			Usuario produto = (Usuario) dao.listarPorId(id);
			return produto;

		} catch (Exception e) {
			System.out.println("Problemas na Controller para listar Produtos" + e.getMessage());
			e.printStackTrace();
			return null;

		}
	}

	public boolean cadastrar(Usuario produto) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.cadastrar(produto);
			return true;

		} catch (Exception e) {
			System.out.println("Problemas no Controler para cadastrar produto" + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Usuario produto) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.alterar(produto);
			return true;
		} catch (Exception e) {
			System.out.println("Problemas na Controller para alterar Produto" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void excluir(int id) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.excluir(id);
		} catch (Exception e) {
			System.out.println("Problemas na Controller para excliur Produto" + e.getMessage());
			e.printStackTrace();

		}
	}

}
