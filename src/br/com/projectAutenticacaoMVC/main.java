package br.com.projectAutenticacaoMVC;

import br.com.autenticacao.controller.UsuarioController;
import br.com.autenticacaoModel.Usuario;

public class main {

	public static void main() {

		Usuario usuario = new Usuario();
		usuario.setNome("Gui");
		usuario.setEmail("guilherme@proway");
		usuario.setSenha("12345");

		UsuarioController controller = new UsuarioController();
		controller.cadastrar(usuario);

	}
}
