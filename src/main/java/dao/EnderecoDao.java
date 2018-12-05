package dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import entidades.Demanda;
import entidades.Endereco;
import entidades.HibernateUtil;
import entidades.RA;


public class EnderecoDao {
	
	
	public void salvarEndereco (Endereco endereco) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(endereco);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listarEndereco(String strPesquisa) {
	
		List<Endereco> list = new ArrayList<Endereco>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		/*
		list = s.createQuery(
					"SELECT e FROM Endereco AS e "
				+	"JOIN e.demandas "
				+ 	"LEFT OUTER JOIN FETCH e.endRA "
				+ 	"WHERE (e.endLogadouro LIKE '%"+strPesquisa+"%')"
				).list();
				*/
		/*
		list = s.createQuery(
				"SELECT e FROM Endereco AS e "
			+	"JOIN e.demandas "
			+ 	"JOIN e.endRA "
			+ 	"WHERE (e.endLogadouro LIKE '%"+strPesquisa+"%')"
			).list();
		*/
		
		
		Criteria crit = s.createCriteria(Endereco.class, "e");
		crit.createAlias("e.demandas", "d", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("e.endRA", "ra");
		crit.add(Restrictions.like("endLogadouro", '%' + strPesquisa + '%'))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = crit.list();
		
		
		//.setResultTransformer(crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
		
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public void removerEndereco(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Endereco e = (Endereco) s.load(Endereco.class, id);
		s.delete(e);
		s.getTransaction().commit();
		s.close();
	}

	public void editarEndereco(Endereco endereco) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(endereco);
		s.getTransaction().commit();
		s.close();
	}
	
	public void mergeEndereco (Endereco endereco) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.merge(endereco);
		s.getTransaction().commit();
		//s.flush(); // para retornar o id do objeto gravado
		s.close();
	}
	
	
}
