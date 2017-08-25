/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import model.BaseModel;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao{
    @Autowired
    private SessionFactory sessionFactory;
	
    public SessionFactory getSessionFactory(){
	return this.sessionFactory;
    }
	
    public void setSessionFactory(SessionFactory sessionFactory){
	this.sessionFactory = sessionFactory;
    }
	
    public void insert(BaseModel b){
    	Transaction tr = null;
    	Session session = null;
    	try{
            session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            insert(b,session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void insert(BaseModel b, Session session){
    	try{
    		session.save(b);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public void update(BaseModel b){
    	Transaction tr = null;
    	Session session = null;
    	try{
    		session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            update(b,session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void update(BaseModel b, Session session){
    	try{
    		session.update(b);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public void saveOrUpdate(BaseModel b){
    	Transaction tr = null;
    	Session session = null;
    	try{
    		session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            saveOrUpdate(b,session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void saveOrUpdate(BaseModel b, Session session){
    	try{
    		session.saveOrUpdate(b);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public void delete(BaseModel b, int id){
    	Transaction tr = null;
    	Session session = null;
    	try{
    		session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            delete(b, id, session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void delete(BaseModel b, int id, Session session){
    	try{
    		BaseModel BaseModel = (BaseModel) session.get(b.getClass(), id);
    		session.delete(BaseModel);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public void delete(BaseModel b){
    	Transaction tr = null;
    	Session session = null;
    	try{
    		session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            delete(b,session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void delete(BaseModel b, Session session){
    	try{
    		session.delete(b);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public void deleteAll(BaseModel b){
    	Transaction tr = null;
    	Session session = null;
    	try{
    		session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            deleteAll(b, session);
            tr.commit();
    	}catch(HibernateException e){
    		if(tr !=null){
    			tr.rollback();
    		}
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
    }
    
    public void deleteAll(BaseModel b, Session session){
    	List<BaseModel> List = null;
    	try{
    		 List = getAll(b, session) ;
    		 if(List.size() > 0){
    			 for(BaseModel BaseModel : List){
    				 session.delete(BaseModel);
    			 }
    		 }
    		
    	}catch(Exception e){
    		System.out.print(e);
    	}
        
    }
    
    public BaseModel getById(BaseModel b, int id){
    	Session session = null;
    	BaseModel BaseModel = null;
    	try{
    		session = getSessionFactory().openSession();
    		BaseModel = getById(b, id, session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  BaseModel;
    }
    
    public BaseModel getById(BaseModel b, int id, Session session){
    	BaseModel baseModel = null;
    	try{
    		baseModel = (BaseModel) session.get(b.getClass(), id);
    	}catch(Exception e){
    		System.out.print(e);
    	}
        return baseModel;
    }

    public List<BaseModel> getByParameters(BaseModel b, Map<String, String> params) {
    	Session session = null;
    	List<BaseModel> List = null;
    	try{
    		session = getSessionFactory().openSession();
    		List = getByParameters(b,params,session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  List;
    }
    
    public List<BaseModel> getByParameters(BaseModel b, Map<String, String> params, Session session){
    	List<BaseModel> List = null;
    	try{
    		Criteria crit = session.createCriteria(b.getClass());
                params.keySet().forEach((key) -> {
                    crit.add(Restrictions.like(key, params.get(key)).ignoreCase());
                });
            List = crit.list();
    	}catch(HibernateException e){
    		System.out.print(e);
    	}
        return List;
    }
    
    public List<BaseModel> search(BaseModel b, Map<String, String> params) {
    	Session session = null;
    	List<BaseModel> List = null;
    	try{
    		session = getSessionFactory().openSession();
    		List = search(b,params,session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  List;
    }
    
    public List<BaseModel> search(BaseModel b, Map<String, String> params, Session session){
    	List<BaseModel> List = null;
    	try{
    		Criteria crit = session.createCriteria(b.getClass());
                params.keySet().forEach((key) -> {
                    crit.add(Restrictions.like(key, "%"+params.get(key)+"%").ignoreCase());
                });
            List = crit.list();
    	}catch(HibernateException e){
    		System.out.print(e);
    	}
        return List;
    }
    
    public List<BaseModel> getAll(BaseModel b) {
    	Session session = null;
    	List<BaseModel> List = null;
    	try{
    		session = getSessionFactory().openSession();
    		List = getAll(b, session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  List;
    }
    
    public List<BaseModel> getAll(BaseModel b, Session session){
    	List<BaseModel> liste = null;
    	try{
    	    Criteria crit = session.createCriteria(b.getClass());
            liste = crit.list();
    	}catch(HibernateException e){
    		System.out.print(e);
    	}
        return liste;
    }
    
    public List<BaseModel> getAllWithPagination(BaseModel b, Integer firstResult, Integer maxResult, String orderByParam, Boolean asc) {
    	Session session = null;
    	List<BaseModel> List = null;
    	try{
    		session = getSessionFactory().openSession();
    		List = getAllWithPagination(b, firstResult, maxResult, orderByParam, asc, session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  List;
    }
    
    public List<BaseModel> getAllWithPagination(BaseModel b, Integer firstResult, Integer maxResult, String orderByParam, Boolean asc, Session session){
    	List<BaseModel> List = null;
    	try{
    		Criteria crit = session.createCriteria(b.getClass());
            if (firstResult == null)
                firstResult = 0;
            crit.setFirstResult(firstResult);
            if (maxResult != null)
                crit.setMaxResults(maxResult);
            if (asc != null && !"".equals(orderByParam) && orderByParam != null) {
                if (asc)
                    crit.addOrder(Order.asc(orderByParam));
                else
                    crit.addOrder(Order.desc(orderByParam));
            }
            List = crit.list();
    	}catch(HibernateException e){
    		System.out.print(e);
    	}
    	
        return List;
    }
    
    public int count(BaseModel b) {
    	Session session = null;
    	int nbr = 0;
    	try{
    		session = getSessionFactory().openSession();
    		nbr = count(b, session);
    	}catch(HibernateException e){
    		System.out.print(e);
    	}finally{
    		if(session !=null){
    			session.flush();
    			session.close();
    		}
    	}
        return  nbr;
    }
    
    public int count(BaseModel b, Session session){
    	int nbr = 0;
    	try{
    	    Criteria crit = session.createCriteria(b.getClass());
            nbr = crit.list().size();
    	}catch(HibernateException e){
    		System.out.print(e);
    	}
        return nbr;
    }
}
