package mylas.com.erp.demo.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;



import mylas.com.erp.demo.EmpDetails;

import mylas.com.erp.demo.LeaveAddition;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.Tblleavestype;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.LeaveManiplication;
import mylas.com.erp.demo.procedures.EmployeeViewPage;

@Repository("leave")
public class LeaveManiplicatiionImpl implements LeaveManiplication {

	@Override
	public List<Tblleavestype> getDetails() {
		
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tblleavestype where isactive=1");
		List<Tblleavestype> empatt = q.list();
		session.getTransaction().commit();
		System.out.println(empatt);
		return empatt;
	}

	/*@Override
	public String save(String Ltype,int count,String uid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tblleavestype");
		List<Tblleavestype> emp1 = q.list();
		int id=0;
		for(Tblleavestype user : emp1) {
			if(user.getLeavetype().equals(Ltype)) {
				id=user.getId();
			}
		}
		System.out.println(id);
		session.getTransaction().commit();
		Session session1 = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
			session1.beginTransaction();
			//Tblleaves add=new Tblleaves(id, count,true,uid, new Date(),null,null); 
			System.out.println("object created");
			//int num = (Integer) session1.save(add);
			System.out.println(num);
			if(num!=0) {
				System.out.println("LeaveType added successfully!....");
				session1.getTransaction().commit();
				System.out.println("LeaveType added successfully!....");
				return "LeaveType added successfully!....";
			}else {
			
							session.getTransaction().commit();
				 			return "LeaveType already exists";
			}

		}catch(ConstraintViolationException e) {
			System.out.println("Duplicate Entry");
			session1.getTransaction().rollback();
			return "LeaveType already exists";
		}
			
		}*/

	@Override
	public List<LeaveAddition> getDetailsofleavetye(int id) {
		List<LeaveAddition> data=new ArrayList<>();
		try(Session  session=HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			CallableStatement cst = null;
	        ResultSet rs = null;
	        SessionImpl sessionImpl = (SessionImpl) session; Connection con = sessionImpl.connection();
	        cst = con.prepareCall("{call sp_vwLeaves(?)}");
	        cst.setInt(1, id);
            cst.execute();
            rs = cst.getResultSet();
            
            while(rs.next()) {
            	LeaveAddition lea=new LeaveAddition();
            	lea.setId(1);
    			lea.setLeavetypeid(rs.getInt(2));
    			lea.setLeaveType(rs.getString(3));
    			lea.setNumleavedays(rs.getInt(4));
    			lea.setIsactive(rs.getBoolean(5));
    			data.add(lea);
            }
	      
			
			if(!data.isEmpty())
			for(LeaveAddition ll:data)
			{
				System.out.println(ll.getLeavetypeid());
			}
			else {
				System.out.println("nodata");
			}
	}catch(Exception e) {
		System.out.println(e);
	}
		return data;
		
	/*	System.out.println("comes");
		List<LeaveAddition> data=new ArrayList<>();
		try {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlquery = "select t1.id,t2.leavetype, t1.numleavedays, t1.isactive from [dbo].[tblleaves] t1,[dbo].[tblleavestype] t2 where t1.leavetypeid=t2.id and t2.isactive =1";
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		SessionImpl sessionImpl = (SessionImpl) session; Connection con = sessionImpl.connection();
		System.out.println(con);
		ps=con.prepareStatement(sqlquery);
		rs=ps.executeQuery();
		System.out.println(rs+"Result set");
		while(rs.next()){
			System.out.println("whileloop");
			LeaveAddition lea=new LeaveAddition();
			lea.setLeavetypeid(rs.getInt(1));
			lea.setLeaveType(rs.getString(2));
			lea.setNumleavedays(rs.getInt(3));
			lea.setIsactive(rs.getBoolean(4));
			data.add(lea);
			System.out.println(lea);
		}
		
		}catch(Exception e)
		{
			
		}
		System.out.println("end");
		return data;*/
		
		
	
		//return data;
	}

	@Override
	public Tblleaves getDetaisById(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Tblleaves tbldays = session.get(Tblleaves.class, id);
		session.getTransaction().commit();
		return tbldays;
		
	}
	@Override
	public String updateLeave(int id,int day,String eid,String active) throws org.hibernate.exception.ConstraintViolationException {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		Tblleaves deptdel = session.load(Tblleaves.class, id);
		//deptdel.setLeavetype(leave.getLeavetype());
		System.out.println("hi");
		deptdel.setNumleavedays(day);
		//deptdel.setUpdatedby(eid);
		deptdel.setUpdateddate(new Date());
		System.out.println(active);
		if(active.equals("true")) {
			deptdel.setIsactive(true);
		}else {deptdel.setIsactive(false);}
		
		/*deptdel.setUpdateddate(new Date());*/
		System.out.println(eid);
		
		session.saveOrUpdate(deptdel);
		
		/*session.update(deptdel);*/
		System.out.println("saveor updated");
		session.getTransaction().commit();
		System.out.println("commited");
		return "UpDated Successfully";
		}catch(ConstraintViolationException e) {
			System.out.println("exception");
			session.getTransaction().rollback();
			return "LeaveType is Already Exists.Please try Again";
		}catch(PersistenceException e){                                                       
			System.out.println("this is PersistenceException exception throw");   
			session.getTransaction().rollback();
			return "Leave is Already Exists.Please try Again";
         }
	
		
	}

	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Tblleaves ltdel = session.load(Tblleaves.class, id);
		session.delete(ltdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit(); 	
		
	}

	@Override
	public String proexe() {
		try(Session  s=HibernateUtil.getSessionFactory().openSession())
		{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_tbl_holidays");
		query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2,String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4,Boolean.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5,String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7,String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(8,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(9,Integer.class, ParameterMode.OUT);
		
	query.setParameter(1,0);
	query.setParameter(2,"aaaaa");
	query.setParameter(3,new Date());
	query.setParameter(4,false);
	query.setParameter(5,"null");
	query.setParameter(6,new Date());
	query.setParameter(7, "2020");
	query.setParameter(8, new Date());
	query.execute();
	
	int a=(int) query.getOutputParameterValue(9);
	System.out.println(a);
		}
		catch(Exception e)
		{
			
		}
	return null;
	}

	@Override
	public String save(int id,int leavetypeid,int count,int cid,int uid,boolean active) {
		try(Session  s=HibernateUtil.getSessionFactory().openSession())
		{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insupdLeaves");
		query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4,Boolean.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(8,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(9,Integer.class, ParameterMode.OUT);
		
	query.setParameter(1,id);
	query.setParameter(2,leavetypeid);
	query.setParameter(3,count);
	query.setParameter(4,active);
	query.setParameter(5,cid);
	query.setParameter(6,new Date());
	query.setParameter(7,uid);
	query.setParameter(8,new Date());
	query.execute();
	int a=(int) query.getOutputParameterValue(9);
	System.out.println(a);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "Leave is Already Exists.Please try Again";
		}
		return "UpDated Successfully";
	}
	
	
	
}
