package br.com.gabriel.mvc.shiro;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.shiro.subject.Subject;

public class SecuredClass {
	@Inject
	private Subject currentUser;

	public void requerAutenticacao() {
		System.out.println("SecuredClass.requerAutenticacao()");
		if (currentUser.isAuthenticated()) {

		}
	}

	public void requerPapelGerente1() {
		System.out.println("SecuredClass.requerPapelGerente1()");
		if (currentUser.hasRole("Manager")) {

		}
	}

	public void requerPapelAdminHasRole() {
		System.out.println("SecuredClass.requerPapelAdminHasRole()");
		if (currentUser.hasRole("Admin")) {

		}
	}

	public void requerPapelGerenteEAdminHasRole() {
		System.out.println("SecuredClass.requerPapelGerenteEAdminHasRole()");
		if (currentUser.hasAllRoles(Arrays.asList("Manager", "Admin"))) {

		}
	}

	// Irá gerar uma exceção
	public void requerPapelGerente() {
		System.out.println("SecuredClass.requerPapelGerente()");
		currentUser.checkRole("Manager");
	}

	public void requerPapelAdmin() {
		System.out.println("SecuredClass.requerPapelAdmin()");
		currentUser.checkRole("Admin");
	}

	public void requerPapelGerenteEAdmin() {
		System.out.println("SecuredClass.requerPapelGerenteEAdmin()");
		currentUser.checkRoles(Arrays.asList("Manager", "Admin"));
	}

	public void requiresPermissionReadDoc1() {
		System.out.println("SecuredClass.requiresPermissionReadDoc1()");
		if (currentUser.isPermitted("doc:read")) {
		}
	}

	public void requiresPermissionWriteDoc1() {
		System.out.println("SecuredClass.requiresPermissionWriteDoc1()");
		if (currentUser.isPermitted("doc:write")) {
		}
	}

	public void requiresPermissionReadWriteDoc1() {
		System.out.println("SecuredClass.requiresPermissionReadWriteDoc1()");
		if (currentUser.isPermittedAll("doc:read", "doc:write")) {

		}
	}

	// Irá gerar uma exceção
	public void requiresPermissionReadDoc() {
		currentUser.checkPermission("doc:read");
	}

	public void requiresPermissionWriteDoc() {
		currentUser.checkPermission("doc:write");
	}

	public void requiresPermissionReadWriteDoc() {
		currentUser.checkPermissions("doc:read", "doc:write");
	}

}
