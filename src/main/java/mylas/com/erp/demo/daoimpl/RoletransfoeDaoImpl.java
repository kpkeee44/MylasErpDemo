package mylas.com.erp.demo.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblManRoleTransfer;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.RoleTrasforDao;
import mylas.com.erp.demo.service.Client;

@Repository("roleTransfer")
public class RoletransfoeDaoImpl implements RoleTrasforDao {

	Client client = new Client();
	

	
	EmpLeaveRequestService empleavereq = new EmpLeaveRequestService();

	EmpAttendanceDaoImpl empattreq = new EmpAttendanceDaoImpl();

	@Override
	public String save(TblManRoleTransfer roletransfor) {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try{session.save(roletransfor);System.out.println("Table Saved");session.getTransaction().commit();return "Saved";}catch(Exception e) {
			e.getMessage();			session.getTransaction().rollback();
return "Save Failed";
		}

		
		
		

	}

	@Override
	public void updateStatus(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		TblManRoleTransfer emp = session.load(TblManRoleTransfer.class,id);

		emp.setStatus(false);
		session.update(emp);
		session.getTransaction().commit();
		System.out.println("updated table");


	}

	@Override
	public void deleteDetails(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		TblManRoleTransfer emp = session.load(TblManRoleTransfer.class,id);
		session.delete(emp);
		session.getTransaction().commit();


	}

	@Override
	public List<TblManRoleTransfer> viewAll() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		String sqlquery = "SELECT * FROM tbl_man_role_transfer";
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblManRoleTransfer.class);
		List<TblManRoleTransfer> transferrole = query.list();
		session.getTransaction().commit();
		return transferrole;
		
		
	}

	@Override
	public String returnToMainManager(String manid, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeMainManager(String frommanid, String tomanid, int id) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public String returnToMainManager(String manid , int id) {		
		List<EmpDetails> employees = client.getByManid(manid);
		List<TblEmpAttendanceNew> attrequests = empattreq.viewbymanagerid(manid);
		//List<TblEmpLeavereq> leaverequests = empleavereq.viewbyManagerid(manid);
		if(employees != null) {
			for(EmpDetails employee : employees) {
				client.ChangeManager(employee.getId());
			}
		}
		if(attrequests != null) {
			for(TblEmpAttendanceNew att : attrequests) {
				empattreq.ChangeManager(att.getId());
			}
		}
		if(leaverequests != null) {
			for(TblEmpLeavereq employee : leaverequests) {
				empleavereq.ChangeManager(employee.getId());
			}
		}
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		TblManRoleTransfer roletransfer = session.load(TblManRoleTransfer.class,id);

		roletransfer.setStatus(true);
		session.update(roletransfer);
		session.getTransaction().commit();
		return null;
	}*/

	
	/*@Override
	 public String changeMainManager(String frommanid, String tomanid, int id) {
	  List<EmpDetails> employees = client.getByManid(frommanid);
	  List<TblEmpAttendanceNew> attrequests = empattreq.viewbymanagerid(frommanid);
	  List<TblEmpLeavereq> leaverequests = empleavereq.viewbyManagerid(frommanid);
	  
	  if(employees != null) {
	   for(EmpDetails employee : employees) {
	    
	    client.ChangeTransManager(employee.getId(),tomanid);
	   }
	  }
	  if(attrequests != null) {
	   for(TblEmpAttendanceNew att : attrequests) {
	    
	    empattreq.updatetransManager(att.getId(),tomanid);
	   }
	  }
	  if(leaverequests != null) {
	   for(TblEmpLeavereq employee : leaverequests) {
	    
	    empleavereq.updatetransManager(employee.getId(),tomanid);
	   }
	  }
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  session.beginTransaction();

	  TblManRoleTransfer roletransfer = session.load(TblManRoleTransfer.class,id);

	  roletransfer.setStatus(false);
	  session.update(roletransfer);
	  session.getTransaction().commit();
	  return null;
	 }*/
	

}
