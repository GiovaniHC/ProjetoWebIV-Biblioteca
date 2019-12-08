package com.biblioteca.model.service;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import com.biblioteca.model.entity.RoleEnum;
import com.biblioteca.model.entity.Usuario;
import com.biblioteca.model.repository.UsuarioRepository;
import com.biblioteca.model.service.UsuarioService;

public class UsuarioTests extends AbstractIntegrationTests {
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/**
	 * Password encoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 *
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 */
	@Autowired
	private UsuarioService usuarioService;

	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/

	// ======== CADASTRAR USUÁRIO =============

	/**
	 *
	 */
	@Test
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustPass() {
		Usuario usuario = new Usuario();
		usuario.setNome("Giovani");
		usuario.setCelular("045998698574");
		usuario.setCpf("12345678914");
		usuario.setEmail("chgiovani5@gmail.com");
		usuario.setPerfil(RoleEnum.ADMINISTRATOR);
		this.usuarioService.cadastrarUsuario(usuario);
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());

	}

	/** teste para verificarse não é possivel um CPF em branco ou null **/
	@Test(expected = ValidationException.class)
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustFailCpfEmBrancoOuNulo() {
		Usuario usuario = new Usuario();
		usuario.setNome("Giovani");
		usuario.setCelular("045998698574");
		usuario.setCpf("");
		usuario.setEmail("chgiovani5@gmail.com");
		usuario.setPerfil(RoleEnum.ADMINISTRATOR);
		this.usuarioService.cadastrarUsuario(usuario);

	}

	// ======== ATIVAR USUÁRIO =============

	/**
	 * Teste para ativar um usuário do sistema com sucesso
	 */
	@Test
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void ativarUsuarioMustPass() {
		this.usuarioService.ativarUsuario("123456789", "123456789", "f357364b-0790-4f2f-bad6-be07de0c864f");

		Usuario usuarioAtivo = this.usuarioRepository.findByEmailIgnoreCase("chgiovani5@gmail.com");
		Assert.assertEquals(true, usuarioAtivo.getAtivo());
	}

	/**
	 * teste para verificar se não é possivel ativar um usuario com token invalido
	 **/
	@Test(expected = IllegalArgumentException.class)
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void ativarUsuarioMustFailTokenInvalido() {
		this.usuarioService.ativarUsuario("123456789", "123456789", "f357364b-0890-4f2f-bad6-be07de0c864f");
	}

	/**
	 * teste para verificar se não é possivel ativar um usuario com token vencido
	 **/
	@Test(expected = IllegalArgumentException.class)
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void ativarUsuarioMustFailTokenVencido() {
		this.usuarioService.ativarUsuario("123456789", "123456789", "f357364b-0790-4f2f-bad6-be07de0c864f");
	}

	/**
	 * teste para verificar se não é possivel ativar um usuario com senhas
	 * diferentes
	 **/
	@Test(expected = IllegalArgumentException.class)
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void ativarUsuarioMustFailSenhasDiferentes() {
		this.usuarioService.ativarUsuario("123456789", "654321789", "f357364b-0790-4f2f-bad6-be07de0c864f");
	}

	// ======== ESQUECI MINHA SENHA =============

	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void enviarEmailRecuperarSenhaUsuarioMustPass() {
		this.usuarioService.enviarEmailRecuperarSenhaUsuario("chgiovani5@gmail.com");
		Usuario usuario = this.usuarioRepository.findByEmailIgnoreCase("chgiovani5@gmail.com");

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getPasswordResetToken());
		Assert.assertNotNull(usuario.getPasswordResetTokenExpiration());
	}

	/**
	 * teste para tentar resetar uma senha de acesso com um email não cadastrado
	 **/
	@Test(expected = IllegalArgumentException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void enviarEmailRecuperarSenhaUsuarioMustFailEmailNaoExiste() {
		this.usuarioService.enviarEmailRecuperarSenhaUsuario("joselito@gmail.com");
	}

	// ======== REDEFINIR MINHA SENHA =============

	/**
	 * Serviço que redefinir senha de um usuário com sucesso
	 * 
	 */
	@Test()
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void redefinirSenhaMustPass() {
		this.usuarioService.redefinirSenha("123456", "123456", "f786c907-032e-451b-ac93-8508dec75a3d");
	}

	// ======== PEGAR USUÁRIO AUTENTICADO =============

	@Test
	@WithUserDetails("chgiovani5@gmail.com")
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void pegarUsuarioAutenticadoTestMustPass() {
		Usuario usuario = this.usuarioService.getAuthenticatedUser();
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());

	}

	// ======== ALTERAR MINHA CONTA =============

	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void alterarUsuarioMustPass() {
		Usuario usuario = new Usuario();
		usuario = this.usuarioService.detalharUsuario(1001);
		usuario.setNome("Giovani o Administrador");
		usuario.setCelular("045998698574");
		usuario.setCpf("12345678914");
		usuario.setPerfil(RoleEnum.ADMINISTRATOR);
		usuario.setAtivo(true);
		this.usuarioService.alterarMinhaConta(usuario, "$2a$10$PqgosXXKYhsMipBjMMeZxOLVDo8CLdq9zW/SUO9VKqojro7WEopq.",
				"$2a$10$PqgosXXKYhsMipBjMMeZxOLVDo8CLdq9zW/SUO9VKqojro7WEopq.");
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());

	}

}