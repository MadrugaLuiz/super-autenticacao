package br.com.autenticacao;

import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.autenticacao.Model.Usuario;
import br.com.autenticacao.controller.UsuarioController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Implementar um menu com as seguintes opções :
		// cadastrar usuário
		// Realizar Login
		// Opção esqueci minha senha

	

		Scanner scan = new Scanner(System.in);
		UsuarioController controller = new UsuarioController();

		int opcao = 0;
		do {

			System.out.println("\n\n+-------------------------+");
			System.out.println("      Menu de Opções      ");
			System.out.println("[1] Nome do Usuário                ");
			System.out.println("[2] Senha do usuário        ");
			System.out.println("[3] Esqueci minha senha             ");
			System.out.println("[0] Sair                  ");
			System.out.println("Escolha uma opção: ");

			opcao = scan.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("-  Menu de Opções - ");
				System.out.println("Nome para cadastrar ");
				for (Usuario usuario : controller.listarTodos()) {
					System.out.println(usuario.getId() + "    " + usuario.getNome());
				}
				break;
			case 2:
				System.out.println("Senha para cadastrar : ");
				int idBusca = scan.nextInt();
				Usuario usuarioPorId = controller.listarPorId(idBusca);

				if (usuarioPorId != null) {
					System.out.println("Senha: " + usuarioPorId.getSenha());
				} else {
					System.out.println("Senha inexistente " + idBusca);
				}

				break;
			case 3:
				System.out.println("Esqueci minha senha ");
				Usuario usuarioNovo = new Usuario();
				usuarioNovo.setSenha(scan.next());
				controller.cadastrar(usuarioNovo);
				break;

			case 0:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (opcao != 0);

		scan.close();
	}

}
