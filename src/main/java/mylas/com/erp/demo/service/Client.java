package mylas.com.erp.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblEmployees;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.EmployeeDao;

@Repository("userDetails")
public class Client implements EmployeeDao {




	public String getConnection(EmpDetails emp) throws ConstraintViolationException{
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			int num = (Integer) session.save(emp);

			if(num!=0) {
				System.out.println("Table Updated");
				session.getTransaction().commit();
				return "Employee Saved Please Login!!!";
			}else {
				System.out.println("Table Faied to Update");
				/*			session.getTransaction().commit();
				 */			return "Employee Save Failed";
			}

		}catch(ConstraintViolationException e) {
			System.out.println("Duplicate Entry");
			session.getTransaction().rollback();
			return "This is a Duplicate Entry";
		}
	}

	@Override
	public List<EmpDetails> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from EmpDetails");
		List<EmpDetails> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);


	}

	@Override
	public void updateDetails(EmpDetails empl,int id) {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails emp = session.load(EmpDetails.class,id);
		//emp.setCompName(empl.getCompName());

		session.update(emp);
		session.getTransaction().commit();
		System.out.println("updated table");

	}



	@Override
	public void deleteDetails(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails empdel = session.load(EmpDetails.class, id);
		session.delete(empdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit();
		// return "Deleted_Entry!";

	}


	@Override
	public EmpDetails getById(int id) {
		EmpDetails user;
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		user =  session.get(EmpDetails.class, id);
		session.getTransaction().commit();
		return user;

	}


	@Override
	public EmpDetails getByUName(String empuname) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from EmpDetails");
		List<EmpDetails> emp1 = q.list();
		for(EmpDetails user : emp1) {
			if(user.getUname().equals(empuname)) {
				session.getTransaction().commit();
				return user;	
			}
		}

		return null;

	}

	@Override
	public List<EmpDetails> getByManid(String managerid) {
		String sqlquery = "SELECT * FROM emp_details WHERE managerid ='"+managerid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(EmpDetails.class);
		List<EmpDetails> emps = query.list();
		session.getTransaction().commit();
		return emps;
	}

	@Override
	public String TransferManager(int id,String mantrans) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails employe = session.load(EmpDetails.class, id);
		//employe.setMantrans(mantrans);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}

	}

	@Override
	public List<EmpDetails> getByManIdAndTransManId(String managerid, String transmanid) {
		String sqlquery = "SELECT * FROM emp_details WHERE managerid ='"+managerid+"' AND mantrans='"+transmanid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(EmpDetails.class);
		List<EmpDetails> emps = query.list();
		session.getTransaction().commit();
		return emps;
	}

	@Override
	public String ChangeManager(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails employe = session.load(EmpDetails.class, id);
		//employe.setMantrans(null);;
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}

	}

	@Override
	public String ChangeTransManager(int id, String tomanagerid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails employe = session.load(EmpDetails.class, id);
		//employe.setMantrans(tomanagerid);;
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}
	}

	@Override
	public void updateEditDetails(int id, String firstname, String lastname, String uname, String empid, String pswd,
			String cpswd, String joindate, String phone, String company, String department,String lastworkingday) {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EmpDetails  empdt = session.load(EmpDetails .class, id);
		//empdt.setFname(firstname);empdt.setLname(lastname);empdt.setUname(uname);empdt.setEid(empid);empdt.setPswd(pswd);
		//empdt.setCpswd(cpswd);empdt.setJdate(joindate);empdt.setPhone(phone);empdt.setCompName(company);empdt.setDepartment(department);empdt.setLastworkingday(lastworkingday);
		session.saveOrUpdate(empdt);
		System.out.println("updated");
		session.getTransaction().commit();

	}

	@Override
	public List<EmpDetails> viewSearch(String firstname, String lastname, String department, String designation) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query q = null;
		if(firstname!="" && lastname!="" && department!="" && designation!="") {
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND lname='"+lastname+"'AND department='"+department+"'AND designation='"+designation+"'");
		}

		else if(firstname!="" && lastname!="" && department!="")
		{
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND lname='"+lastname+"'AND department='"+department+"'");	
		}
		else if(firstname!="" && lastname!="" && designation!="")
		{	
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND lname='"+lastname+"'AND designation='"+designation+"'");
		}
		else if(firstname!="" && department!="" && designation!="")
		{	
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND department='"+department+"'AND designation='"+designation+"'");
		}	
		else if(lastname!="" && department!="" && designation!="")
		{	
			q = session.createQuery("from EmpDetails where lname='"+lastname+"'AND department='"+department+"'AND designation='"+designation+"'");
		}	
		else if(firstname!="" && lastname!="")
		{
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND lname='"+lastname+"'");
		}
		else if(firstname!="" && department!="")
		{
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND department='"+department+"'");
		}
		else if(firstname!="" && designation!="")
		{
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'AND designation='"+designation+"'");
		}
		else if(lastname!="" && department!="")
		{
			q = session.createQuery("from EmpDetails where lname='"+lastname+"'AND department='"+department+"'");
		}
		else if(lastname!="" && designation!="")
		{
			q = session.createQuery("from EmpDetails where lname='"+lastname+"'AND designation='"+designation+"'");
		}
		else if(department!="" && designation!="")
		{
			q = session.createQuery("from EmpDetails where department='"+department+"'AND designation='"+designation+"'");
		}
		else if(firstname!="") {
		
			q = session.createQuery("from EmpDetails where fname='"+firstname+"'");
		}
		else if(lastname!="") {
			q = session.createQuery("from EmpDetails where lname='"+lastname+"'");
		}
		else if(department!="") {
			q = session.createQuery("from EmpDetails where  department='"+department+"'");
		}
		else if(designation!="") {
		
			q = session.createQuery("from EmpDetails where designation='"+designation+"'");
			
		}
		List<EmpDetails> empleave = q.list();

		session.getTransaction().commit();
		return (empleave);		
	}
	

	@Override
	public List<EmpDetails> simulateSearchResult(String tagName) {
		// TODO Auto-generated method stub
	
		List<EmpDetails> emp1 = getDetails();
		List<EmpDetails> result = new ArrayList<EmpDetails>();
	
		// iterate a list and filter by tagName
		for (EmpDetails tag : emp1) {
			if (tag.getEmplfirstname().contains(tagName)) {
				result.add(tag);
			}
		}
	

		return result;
		
	}

	@Override
	public List<EmpDetails> simulateSearchResultLastName(String tagName) {
		
	
		List<EmpDetails> emp1 = getDetails();
		List<EmpDetails> result = new ArrayList<EmpDetails>();
	
		// iterate a list and filter by tagName
		for (EmpDetails tag : emp1) {
			if (tag.getEmpllastname().contains(tagName)) {
				result.add(tag);
			}
		}
	

		return result;
		
	}
	
	
	@Override
	public String getMail(String mngid) {
		System.out.println(mngid);
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String tomail = null;
		Query q = session.createQuery("from EmpDetails where eid ='"+mngid+"'");
		System.out.println(q);
		List<EmpDetails> emp1 = q.list();
		System.out.println(emp1);
		for(EmpDetails user : emp1) {
			tomail=user.getEmail();
			session.getTransaction().commit();
				
			}
		System.out.println(tomail);
		return tomail;		
	}

	@Override
	public String saveEmpDetails(int id,String empid, String fname, String lname, String email,String uname, String pswd, String addres,String ph,String adr,
			String crby, String upby, String role, String dept, String des) {
		// TODO Auto-generated method stub
		try(Session  s=HibernateUtil.getSessionFactory().openSession())
		{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_tbl_employee");
query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);query.registerStoredProcedureParameter(2,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(3,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(4,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(5,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(6,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(7,Boolean.class, ParameterMode.IN);query.registerStoredProcedureParameter(8,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(9,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(10,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(11,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(12,Date.class, ParameterMode.IN);
query.registerStoredProcedureParameter(13,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(14,Date.class, ParameterMode.IN);
query.registerStoredProcedureParameter(15,String.class, ParameterMode.IN);query.registerStoredProcedureParameter(16,Integer.class, ParameterMode.IN);
query.registerStoredProcedureParameter(17,Integer.class, ParameterMode.IN);query.registerStoredProcedureParameter(18,String.class, ParameterMode.IN);
query.registerStoredProcedureParameter(19,Integer.class, ParameterMode.OUT);
int deptid=Integer.parseInt(dept);int desid=Integer.parseInt(des);
query.setParameter(1,id);query.setParameter(2,empid);query.setParameter(3,fname);query.setParameter(4,lname);query.setParameter(5,email);query.setParameter(6,pswd);
query.setParameter(7,true);query.setParameter(8,addres);query.setParameter(9,ph);query.setParameter(10,adr);query.setParameter(11,crby);query.setParameter(12,new Date());
query.setParameter(13,upby);query.setParameter(14,new Date());query.setParameter(15,role);
query.setParameter(16,deptid);query.setParameter(17,desid);query.setParameter(18,uname);
query.execute();

int a=(int) query.getOutputParameterValue(19);
	System.out.println(a);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "Employee already Exits";
		}
	return "Employee added successfully";
		
	}

	@Override
	public List<TblEmployees> getDetails1() {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from TblEmployees");
		List<TblEmployees> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);
	
	}
}









