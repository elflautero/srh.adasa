package dao;

import java.util.List;

import org.hibernate.Session;

import entidades.HibernateUtil;
import entidades.Usuario;


public class UsuarioDao {
	
public void salvarUsuario (Usuario usuario) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(usuario);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuario(String strPesquisa) {
		
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		/*
		TypedQuery<Usuario> q = (TypedQuery<Usuario>) s.createQuery("SELECT u FROM Usuario u JOIN FETCH u.usEndCodigoFK");
		q.setFirstResult(0);
		q.setMaxResults(5);
		List<Usuario> list = q.getResultList();
		*/
		
		List<Usuario> list = s.createQuery(
				"SELECT u FROM Usuario AS u "
				+ "JOIN FETCH u.usEnderecoFK AS e "
				+ "LEFT JOIN FETCH e.endRAFK "
				+ "LEFT JOIN FETCH e.demandas "
				+ "WHERE (u.usNome LIKE '%"+strPesquisa+"%' OR u.usCPFCNPJ LIKE '%"+strPesquisa+"%')"
				).list();
		
		/*
		 * 
		 * "FROM Usuario as u JOIN u.Endereco as e where (u.usNome like '%"+strPesquisa+"%' or u.usCPFCNPJ like '%"+strPesquisa+"%')"
		 * 
		 * 
		TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder o JOIN Item i ON o.id = i.order.id WHERE i.id = :itemId", PurchaseOrder.class);
		q.setParameter("itemId", item2.getId());
		q.getSingleResult();
		*/
		//1 - "SELECT * FROM Usuario JOIN Endereco WHERE us_nome LIKE '%"+ strPesquisa +"%'"
		
		// 2- "SELECT * FROM Usuario WHERE Usuario.us_nome LIKE '%a%'" INNER JOIN Endereco ON Usuario.usEndCodigoFK = Endereco.Cod_Endereco 
				
		/*
		 *	"FROM Usuario as u where (u.usNome like '%"+strPesquisa+"%' or u.usCPFCNPJ like '%"+strPesquisa+"%') "
			  + "JOIN usuario.usEndCodigoFK as endereco"
				).list();
		 */
		
		
		s.beginTransaction();
		
		
		/*
		List<Usuario> list = new ArrayList<Usuario>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		Criteria crit = s.createCriteria(Usuario.class);
		
		Criterion usNomeUsuario = Restrictions.like("usNome", '%' + strPesquisa + '%');
		Criterion usCPFUsuario = Restrictions.like("usCPFCNPJ", '%' + strPesquisa + '%');
		
		LogicalExpression orExp = Restrictions.or(usNomeUsuario,usCPFUsuario);
		
		crit.add(orExp);
		
		
		list = crit.list();
		*/
			
		s.getTransaction().commit();
		s.close();
		return list;
		
		
	}
	
	public void removerUsuario(Integer id) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Usuario u = (Usuario) s.load(Usuario.class, id);
		s.delete(u);
		s.getTransaction().commit();
		s.close();
		
	}

	public void editarUsurario(Usuario usuario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(usuario);
		s.getTransaction().commit();
		s.close();
	}
	
	public void mergeUsuario(Usuario usuario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.merge(usuario);
		s.getTransaction().commit();
		s.close();
	}
	
	public void persistirUsuario(Usuario usuario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.persist(usuario);
		s.getTransaction().commit();
		s.close();
	}

}
