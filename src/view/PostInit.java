/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.meldev.dao.EmpresaDao;
import com.meldev.dao.FornecedorDao;
import com.meldev.dao.RoleDao;
import com.meldev.dao.UtilizadorDao;
import com.meldev.model.Empresa;
import com.meldev.model.Fornecedor;
import com.meldev.model.Role;
import com.meldev.model.Utilizador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jbila
 */
public class PostInit {
    
    private final EmpresaDao empresaDao;
    private final RoleDao roleDao;
    private final UtilizadorDao utilizadorDao;
    private final FornecedorDao fornecedorDao;
    
    public PostInit() {
        empresaDao = new EmpresaDao();
        roleDao = new RoleDao();
        utilizadorDao = new UtilizadorDao();
        fornecedorDao=new FornecedorDao();
        
    }
    
    public void saveEmpresa() {
        String descricao = "SOLUCOES INFORMATICAS";
        if (empresaDao.count() == 0) {
            Empresa empresa = new Empresa();
            empresa.setNome(descricao);
            empresa.setDescricao(descricao);
            empresa.setTelefone(descricao);
            empresa.setEmail(descricao + "@gmail.com".toLowerCase());
            empresaDao.save(empresa);
        }
    }
    
    public void saveRole() {
        if (roleDao.count() == 0) {
            List<Role> roles = new ArrayList<>();
            Role adminRole = new Role();
            adminRole.setNome("ADMIN");
            Role standardRole = new Role();
            standardRole.setNome("NORMAL");
            Role vendaRole = new Role();
            vendaRole.setNome("VENDA");
            
            roles.add(standardRole);
            roles.add(vendaRole);
            roles.add(adminRole);
            roleDao.saveAll(roles);
        }
    }
    
    public void saveUser() {
        
        if (utilizadorDao.count() == 0) {
            Role role = roleDao.findByPerfil("ADMIN");
            Empresa empresa = empresaDao.findById(1L);
            
            String password = "ADMIN";
            Utilizador user = new Utilizador();
            user.setUsername("ADMIN");
            user.setPassword(password);
            user.setEmpresa(empresa);
            user.setStatus("ACTIVO");
            user.setRole(role);
            utilizadorDao.save(user);
        }
        
    }
    
    
      public void saveFornecedor() {
        String descricao = "INDIFINIDO";
        if (fornecedorDao.count() == 0) {
            Empresa empresa=empresaDao.findById(1L);
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(descricao);
            fornecedor.setTelefone(descricao);
            fornecedor.setEmail(descricao + "@gmail.com".toLowerCase());
            fornecedor.setEmpresa(empresa);
            fornecedor.setCodigo(descricao);
            fornecedorDao.save(fornecedor);
        }
    }
    
}
