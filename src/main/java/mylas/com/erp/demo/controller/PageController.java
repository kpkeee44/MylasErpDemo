package mylas.com.erp.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;

import mylas.com.erp.demo.LeaveAddition;
import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblHolidays;
import mylas.com.erp.demo.TblManRoleTransfer;
import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.Tblleavestype;
import mylas.com.erp.demo.appservices.EmailSender;
import mylas.com.erp.demo.appservices.UserServiceImpl;
import mylas.com.erp.demo.dao.DepartmentDao;
import mylas.com.erp.demo.dao.DesignationDao;
import mylas.com.erp.demo.dao.EmpAttendenceDao;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.EmpServicesDao;
import mylas.com.erp.demo.dao.EmployeeDao;
import mylas.com.erp.demo.dao.HolidayDao;
import mylas.com.erp.demo.dao.LeaveManiplication;
import mylas.com.erp.demo.dao.ManagerServicesDao;
import mylas.com.erp.demo.dao.RoleTrasforDao;
import mylas.com.erp.demo.dao.ServicesDao;
import mylas.com.erp.demo.daoimpl.EmpAttendanceDaoImpl;
import mylas.com.erp.demo.daoimpl.EmpLeaveRequestService;
import mylas.com.erp.demo.daoimpl.LeavesTypeDaoImpl;
import mylas.com.erp.demo.exceptions.UserBlockedException;
import mylas.com.erp.demo.operations.LoginOperations;
import mylas.com.erp.demo.procedures.EmpLeaveRequestJoin;
import mylas.com.erp.demo.procedures.EmployeeViewPage;
import mylas.com.erp.demo.service.DepartmentService;
import mylas.com.erp.demo.service.DesignationService;




@Controller
public class PageController<JavaMailSender> {

	@Autowired
	ServicesDao servicesdao;

	@Autowired
	EmpServicesDao empservicesdao;

	@Autowired
	ManagerServicesDao mandao;
	@Autowired
	EmpLeaveRequestDao empleavereq;

	@Autowired
	EmpAttendanceDaoImpl empattreq;

	@Autowired
	HolidayDao himpl;

	@Autowired
	RoleTrasforDao roleTransfer;

	@Autowired
	EmployeeDao userDetails;

	@Autowired
	DesignationDao designationImpl;

	@Autowired
	DepartmentDao deptdao;

	@Autowired
	EmpAttendenceDao attimpl;
	
	@Autowired
	LeaveManiplication leave;

	@Autowired
	EmpLeaveRequestDao ers;


	EmailSender emailsender = new EmailSender();
	LeavesTypeDaoImpl ltdi = new LeavesTypeDaoImpl();
	EmpLeaveRequestService elrs = new EmpLeaveRequestService();
	Tblleavestype tlt = null;

	static String emailToRecipient, emailSubject, emailMessage;

	/*
	 * Slide Bar Page Handlers Start
	 */

/*--------------------------EMPLOYEE SCREEN------------------*/

	@RequestMapping(value="/admin/allemp/register")
	public ModelAndView allEmpPage() {
		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("services", servicesdao.list());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*	List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();

		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
	//	mav.addObject("TransferRoleList", transferrole);
		List<TblDepartment> deptList = deptdao.getDetails();
		List<TblDesignation> designList = designationImpl.getDetails();
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		mav.addObject("title", "Employee Regester Page");
		mav.addObject("userClickReg", true);
		String mesg = "hi";
	//	List<EmpDetails> emp1 = userDetails.getDetails();
		List<EmployeeViewPage> emp1=userDetails.view(0);
		mav.addObject("employees", emp1);
		mav.addObject("dupmsg", mesg);
		mav.addObject("departments", deptList);
		mav.addObject("designations", designList);
		mav.addObject(user);
		return mav;		
	}
	@RequestMapping(value="/admin/allemp/register", method=RequestMethod.POST)
	public ModelAndView EmpPage(HttpServletRequest request, HttpServletResponse response) throws ConstraintViolationException{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		/*EmpDetails emp = new EmpDetails(null, request.getParameter("cpswd"), null, request.getParameter("empid"), request.getParameter("email"), request.getParameter("firstname1"), null, request.getParameter("lastname1"), false, null, request.getParameter("pswd"), null, request.getParameter("uname"), null, null,null,null);

		emp.setLoginStatus(UserServiceImpl.Login_Status_Active);
		emp.setRole("MANAGER_ROLE");
		emp.setManagerid(user.getEid());
		emp.setJdate(request.getParameter("joindate"));


		emp.setPhone(request.getParameter("phone"));
		emp.setCompName(request.getParameter("company"));
		emp.setDepartment(request.getParameter("department"));
		emp.setDesignation("Manager");*/


		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("services", servicesdao.list());
		//String mesg = "hi";
		System.out.println(request.getParameter("department"));
		String mesg=userDetails.saveEmpDetails(0,request.getParameter("empid"),request.getParameter("firstname1"),request.getParameter("lastname1"),request.getParameter("email"),request.getParameter("uname"),request.getParameter("pswd"),request.getParameter("address"), request.getParameter("phone"),request.getParameter("aadhar"), user.getId(), user.getId(),"MANAGER_ROLE",request.getParameter("department"),request.getParameter("designation"),"true");
		List<TblDesignation> designList = designationImpl.getDetails();
		mav.addObject("designations", designList);
		List<TblDepartment> deptList = deptdao.getDetails();
		mav.addObject("departments", deptList);
	//	mesg = userDetails.getConnection(emp);
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//mav.addObject("TransferRoleList", transferrole);




		//Departments
		/*List<TblDepartment> dests = deptdao.getDetails();
		mav.addObject("departments", dests);
*/

		String role = user.getRole();
		mav.addObject("Role",role);
		List<EmployeeViewPage> emp1 = userDetails.view(0);
		mav.addObject("employees", emp1);


		mav.addObject("dupmsg", mesg);
		mav.addObject("User",user);
		//mav.addObject("employee", emp);

		return mav;		
	}
	/*------------Employee Edit Screen--------------------------------------*/
	@RequestMapping(value="/admin/empdetais/edit/{id}")
	public ModelAndView editEmpDetails(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("employeesedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
	
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//mav.addObject("TransferRoleList", transferrole);
		List<EmployeeViewPage> emp1 = userDetails.view(id);
		EmployeeViewPage editdep=null;
		for(EmployeeViewPage list:emp1)
		{
			editdep=list;
		}
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		String mesg = "hi";
		/*EmpDetails editdep =userDetails.getById(id);*/
		List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
		System.out.println("comes");
		List<TblDesignation> depts = designationImpl.getDetails();
		System.out.println("go");
		mav.addObject("designations", depts);
		mav.addObject("edetais",editdep);
		mav.addObject("services", servicesdao.list());
		mav.addObject("dupmsg", mesg);
		System.out.println("123456");
		return mav;

	}
	@RequestMapping(value="/admin/empdetais/edit/{id}",method=RequestMethod.POST)
	public ModelAndView updateEmpDetails(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		System.out.println(request.getParameter("active"));
		String mesg=userDetails.saveEmpDetails(id,request.getParameter("empid"),request.getParameter("firstname1"),request.getParameter("lastname1"),request.getParameter("email"),request.getParameter("uname"),request.getParameter("pswd"),request.getParameter("address"), request.getParameter("phone"),request.getParameter("aadhar"), user.getId(), user.getId(),"MANAGER_ROLE",request.getParameter("department"),request.getParameter("designation"),request.getParameter("active"));
		//userDetails.updateEditDetails(id,request.getParameter("firstname"),request.getParameter("lastname"), request.getParameter("uname"),request.getParameter("empid"),request.getParameter("pswd"),request.getParameter("cpswd"),request.getParameter("joindate"),request.getParameter("phone"),request.getParameter("company"),request.getParameter("department"),request.getParameter("relievingdate"));
		return mav;

	}
	
	
	/*----------------holiday screen------------------*/



	@RequestMapping(value="/admin/empholidays/register")
	public ModelAndView empHolidayPage() {
		ModelAndView mav = new ModelAndView("holidays");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		mav.addObject("User",user);
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("User", user);
		//mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblHolidays> holidays = himpl.viewAll();
		mav.addObject("HolidaysList",holidays);
		mav.addObject("services", servicesdao.list());
		
		return mav;		
	}
	@RequestMapping(value= "/admin/empholidays/register",method=RequestMethod.POST)
	public ModelAndView saveHoliday(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("holidays");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
	//	mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		
		System.out.println("hai");
		
		String dt1=request.getParameter("hdate");
		//Date dt=new Date(dt1);
		System.out.println(dt1);
		String name=request.getParameter("holiday");
		//(int id,String name,Date dt,boolean active,String eid,Date cdt,String upby,Date update);
		String msg=himpl.saveHoliday(0,name,dt1,request.getParameter("active"),user.getId(),new Date(),user.getId(),null);
		List<TblHolidays> holidays = himpl.viewAll();
		mav.addObject("HolidaysList",holidays);
		mav.addObject("services", servicesdao.list());
		mav.addObject("msg",msg);
		return mav;
	}
	
	/*----------HolidayEdit Screen-------------*/
	@RequestMapping(value= "/holidays/edit/{id}")
	public ModelAndView editHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("holidaysedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("User",user);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		TblHolidays holidays = himpl.getHolidayById(id);
		mav.addObject("Holiday",holidays);
		mav.addObject("services", servicesdao.list());
		return mav;
	}
	
	@RequestMapping(value= "/holidays/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();*/
		List<EmpDetails> emp1 = userDetails.getDetails();
		String dt=request.getParameter("hdate");
		//String[]sdt=dt.split("-");		
		String name=request.getParameter("holiday");
		//Holidays hday=new Holidays(name, dt);
		System.out.println(id);
		String msg=himpl.saveHoliday(id,name,dt,request.getParameter("active"),user.getId(),new Date(),user.getId(),new Date());
		//String msg=himpl.updateHOliday(id, hday);
		if(msg.equalsIgnoreCase("updated successfully")) {
			mav = new ModelAndView("redirect:/admin/empholidays/register");
			return mav;
		}else if(msg.equalsIgnoreCase("Holiday already exists")) {
			mav = new ModelAndView("holidaysedit");
		/*	mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);*/
			mav.addObject("TransferRoleList", transferrole);
			String role = user.getRole();
			mav.addObject("Role",role);
			TblHolidays holidays = himpl.getHolidayById(id);
			mav.addObject("Holiday",holidays);
			mav.addObject("services", servicesdao.list());
			mav.addObject("msg",msg);
			return mav;
		}
		mav.addObject("msg", "Unable to Update");
		return mav;
		}
/*-------------------------------	admin leave requests page---------------------------*/
	
	@RequestMapping(value="/admin/leaverequests/register")
	public ModelAndView empLeaveReqPage() {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*	List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);

	//	List<TblEmpLeavereq> leavereq =  empleavereq.view();

	//	mav.addObject("employees", emp1);
		//mav.addObject("empleave", leavereq);
		 List<EmpLeaveRequestJoin> empleaves = elrs.viewAll(0);
	        mav.addObject("empleaves",empleaves);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User",user);
		return mav;		
	}

	@RequestMapping(value="/admin/employeetimesheets/register")
	public ModelAndView empAttenedancePage() {
		ModelAndView mav = new ModelAndView("allemptimesheetrequests");
		mav.addObject("services", servicesdao.list());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpAttendanceNew> attendances =  empattreq.getDetails();
		mav.addObject("User",user);
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("employees", emp1);
		mav.addObject("attendancelist",attendances);
		return mav;		
	}

	/*---------------Designation Srceen------------------------*/


	@RequestMapping(value="/admin/empdesig/register")
	public ModelAndView empDesignationPage() {
		ModelAndView mav = new ModelAndView("designations");
		System.out.println("1");
		List<TblDesignation> desig = designationImpl.getDetailsview();
	//	List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("designations", desig);
		//mav.addObject("departments", depts1);
		mav.addObject("services", servicesdao.list());
		System.out.println("2");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		System.out.println(3);
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		System.out.println(4);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		System.out.println(5);
		System.out.println("hai");
		System.out.println("6");
		mav.addObject("Role",role);
		mav.addObject("User",user);
		return mav;		
	}
	@RequestMapping(value="/admin/empdesig/register", method=RequestMethod.POST)
	public ModelAndView AddEmpDesignationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("designations");
		//DesignationService depdetails = new DesignationService();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		
		String dsmsg=designationImpl.saveDetails(0,request.getParameter("designationname"),"true",user.getId(),user.getId());
		List<TblDesignation> desig = designationImpl.getDetailsview();
		
		mav.addObject("services", servicesdao.list());
		
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		List<TblDepartment> depts1 = deptdao.getDetails();
		
		mav.addObject("departments", depts1);
		mav.addObject("designations", depts);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("designations", desig);
		mav.addObject("Role",role);
		mav.addObject("dgmsg",dsmsg);
		mav.addObject("User", user);
		
		return mav;		
	}
	/*--------------------------designation Edit Srceeen-------------------*/
	@RequestMapping(value="/admin/designation/edit/{id}")
	public ModelAndView editAdminDesignation(HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("designationsedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();

		mav.addObject("User",user);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		TblDesignation editdep = designationImpl.getById(id);
		List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
		mav.addObject("depdetailsforedit",editdep);
		mav.addObject("services", servicesdao.list());
		return mav;

	}

	@RequestMapping(value="/admin/designation/edit/{id}",method=RequestMethod.POST)
	 public ModelAndView updateAdmindesignation(HttpServletRequest request,@PathVariable("id") int id) {
	  ModelAndView mav = new ModelAndView();
	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  EmpDetails user=null;
	  if (principal instanceof EmpDetails) {
	   user = ((EmpDetails)principal);
	  }
	  List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	  /*List<TblEmpLeavereq> allempleave = empleavereq.view();
	  int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
	  List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails()*/;
	  List<EmpDetails> emp1 = userDetails.getDetails();
	  List<TblDesignation> design = designationImpl.getDetails();
	  String dsmsg=designationImpl.saveDetails(id, request.getParameter("designationname"),request.getParameter("active"),user.getId(),user.getId());
	  System.out.println(dsmsg);
	  if(dsmsg.equalsIgnoreCase("Designation Updated Successfully")) {
	   mav = new ModelAndView("redirect:/admin/empdesig/register");
	   return mav;
	  }
	  else if(dsmsg.equalsIgnoreCase("Designation Name Already Exits")) {
	   mav = new ModelAndView("designationsedit");
	/*   mav.addObject("empattendances",empattendances);
	   mav.addObject("allempleave", allempleave);
	   mav.addObject("count",count);*/
	   mav.addObject("TransferRoleList", transferrole);
	   String role = user.getRole();
	   mav.addObject("Role",role);
	   mav.addObject("User", user);
	   mav.addObject("emp1",emp1);
	   mav.addObject("designations", design);
	   mav.addObject("services", servicesdao.list());
	   mav.addObject("dsmsg", dsmsg);
	   List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
	   TblDesignation editdesign = designationImpl.getById(id);
	   mav.addObject("depdetailsforedit",editdesign);
	   return mav;
	  }
	  mav.addObject("msg", "Unable to Update");
	  return mav;
	 
	 }

	/*@RequestMapping(value="/admin/designations/delete/{id}")
	public ModelAndView deleteAdminDesignations(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdesig/register");

		//designationImpl.deleteDetails(id);
		return mav;

	}*/
	/*
	 * SlideBar Page handler Close
	 */

	/*
	 * Master Form Handlers
	 */

	
/*
	@RequestMapping(value="/admin/allemp/delete/{id}")
	public ModelAndView deleteEmpPage(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");

		userDetails.deleteDetails(id);
		return mav;

	}
*/
	

	


/*	@RequestMapping(value="/admin/allemp/register/{id}/employeedetails")
	public ModelAndView eachEmpDetailsPage(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("useremployee");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		EmpDetails Edetails = null;
		Edetails = userDetails.getById(id);
		System.out.println(Edetails.getEid());
		int a[]=ers.countSum(Edetails.getEid());
		List<LeaveAddition> numofleaves=leave.getDetailsofleavetye();
		Map<String,Integer> using=ers.count(user.getEid());
		Map<String,Integer> pending=new HashMap<>();
		Set<String> keys = using.keySet();
		Iterator itr = keys.iterator();
		int pleave=0;
		for(LeaveAddition li:numofleaves) {
			//System.out.println(li.getLeavetype());
			System.out.println(using);
			while(itr.hasNext())
	        {System.out.println("comes");
	     //   System.out.println(li.getLeavetype());
	       
	        String key=(String) itr.next();
	        System.out.println(key);
		if(li.getLeavetypeid().equals(key) ) {
			System.out.println(li.getNumleavedays());
			pleave=li.getNumleavedays()-using.get(key);
			//pending.put(li.getLeavetypeid(), pleave);
			
			System.out.println(pending);}
	        }
			itr = keys.iterator();
			System.out.println("hai");
			}
		System.out.println(pending);
		mav.addObject("pleave",pending);
		mav.addObject("using",using);
		mav.addObject("nleave",numofleaves);
		mav.addObject("medical",a[0]);
		mav.addObject("casual",a[1]);
		mav.addObject("sick",a[2]);
		mav.addObject("pmedical",10-a[0]);
		mav.addObject("pcasual",10-a[1]);
		mav.addObject("psick",10-a[2]);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		EmpDetails Edetails = null;
		Edetails = userDetails.getById(id);
		mav.addObject("empID", id);
		mav.addObject("employee",Edetails);
		mav.addObject("User", user);
		mav.addObject("services", servicesdao.list());
		return mav;		
	}*/

	/*
	 * Default Pages
	 */
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView indexPage() {
		ModelAndView mav = new ModelAndView("empindex");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("TransferRoleList", transferrole);
		mav.addObject("title", "HomePage");
		mav.addObject("Role",role);
		mav.addObject("User",user);
		mav.addObject("services", servicesdao.list());
		mav.addObject("empservices", empservicesdao.list());
		mav.addObject("manservices", mandao.list());
		mav.addObject("employees", emp1);
		return mav;
	}
	@RequestMapping(value= "/forgot-password")
	public ModelAndView forgotPasswordPage() {
		ModelAndView mav = new ModelAndView("forgot-password");
		mav.addObject("title", "Forgot Password Page");
		mav.addObject("userClickForgotPassword", true);
		return mav;
	}
	@RequestMapping(value= "/register")
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("title", "Register Page");
		mav.addObject("userClickCreatNewAcc", true);
		return mav;
	}
	/*@RequestMapping(value= "/register", method=RequestMethod.POST)
	public ModelAndView adminRegisterPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UserBlockedException {
		
		
		EmpDetails emp = new EmpDetails(null, request.getParameter("confirm"), null, request.getParameter("empid"), request.getParameter("email"), null, null, null, false, null, request.getParameter("password"), null, request.getParameter("username"),null,null,null,null);

		emp.setIsactive(UserServiceImpl.Login_Status_Active);
		emp.setRole("ADMIN_ROLE");
		emp.setEmplfirstname("Admin");
		emp.setEmpllastname("Admin");
		String msg = userDetails.getConnection(emp);
		ModelAndView mav;
		if(msg.equalsIgnoreCase("This is a Duplicate Entry")) {
			mav = new ModelAndView("register");	
		}else {
			mav = new ModelAndView("newlogin");
		}

		mav.addObject("title", "Register Page");
		mav.addObject("regmessage",msg);
		return mav;
	}*/

	/**\
	 * 
	 * User Login and Other Operations
	 * 
	 * 
	 */

	UserServiceImpl userservice = new UserServiceImpl();

	@RequestMapping(value= "/login")
	public ModelAndView loginPageView(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView mav = new ModelAndView("newlogin");
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		mav.addObject("loginoperations", new LoginOperations());
		return mav;
	}

	@RequestMapping(value= "/signin")
	public ModelAndView signinPageView(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView mav = new ModelAndView("lockscreen");
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		mav.addObject("loginoperations", new LoginOperations());
		return mav;
	}

	@RequestMapping(value= "/logout")
	public ModelAndView logoutPageView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/login?logout");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return mav;
	}
	/*@RequestMapping(value="/admin/departments/delete/{id}")
	public ModelAndView deleteAdminDepartments(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdep/register");

		deptdao.deleteDetails(id);
		return mav;

	}
*/
	



	@RequestMapping(value= "/admin/attendance/approve/{id}")
	public ModelAndView empTimesheetApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/employeetimesheets/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		
		String reason = "Approved";
		boolean status = true;
		empattreq.update(status, id);

		emailSubject = "Time Sheet Request Status For:";
		emailMessage = "A new Time Sheet Request was approved :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
		emailToRecipient = user.getEmail();
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		return mav;
	}
	@RequestMapping(value= "/admin/attendance/decline/{id}")
	public ModelAndView empTimesheetdeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/employeetimesheets/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String reason = "Decline";
		boolean status = false;
		empattreq.update(status, id);
		emailSubject = "Time Sheet Request Status For:";
		emailMessage = "A new Time Sheet Request was declined :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
		emailToRecipient = user.getEmail();
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");

		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		return mav;
	}
	@RequestMapping(value= "/admin/leave/approve/{id}")
	public ModelAndView empLeaveApprovePage(HttpSession session,@PathVariable("id") int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	/*	List<TblEmpLeavereq> leavereq =  empleavereq.view();
		String reason = request.getParameter("reason");
		boolean status = true;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);*/
		mav.addObject("manservices", mandao.list());	
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was approved :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
		emailToRecipient = user.getEmail();
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		System.out.println("send mail");
		return mav;
	}
	@RequestMapping(value= "/admin/leave/decline/{id}")
	public ModelAndView empLeavedeclinePage(HttpSession session,@PathVariable("id") int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	//	List<TblEmpLeavereq> leavereq =  empleavereq.view();		
		String reason = request.getParameter("reason");
		boolean status = false;
		//String UMsg = empleavereq.update(id,reason,status);
		//mav.addObject("empleave", leavereq);
		//mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was declined :"+"On: "+new Date()+" By:  "+user.getEmplfirstname()+" "+user.getEmpllastname();
		emailToRecipient = user.getEmail();
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		
		return mav;
	}
	@RequestMapping("/mytest")
	public ModelAndView testmyPage() {
		System.out.println("comes");
		
		ModelAndView mav = new ModelAndView("adminindex");
		/*leave.proexe();
		EmpLeaveRequestService elrs = new EmpLeaveRequestService();
	          String s = elrs.saveLeaveRequest();
		System.out.println(s);*/
		userDetails.view(0);
		return mav;
	}

	
/*
	@RequestMapping(value= "/holidays/delete/{id}")
	public ModelAndView deleteHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empholidays/register");	
		himpl.deleteHoliday(id);
		return mav;
	}*/

	
/*-----------department screen-------------------------------------------------------------------------------------*/
	@RequestMapping(value="/admin/empdep/register")
	public ModelAndView empDepartmentPage() {
		System.out.println("before");
		ModelAndView mav = new ModelAndView("departments");
		System.out.println("after");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User", user);

		List<TblDepartment> depts = deptdao.getDetailsview();
		mav.addObject("departments", depts);
		mav.addObject("services", servicesdao.list());

		return mav;		
	}

	
	@RequestMapping(value="/admin/empdep/register", method=RequestMethod.POST)
	public ModelAndView addEmpDepartmentPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("departments");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		DepartmentService depserv = new DepartmentService();
		 
		String msg = depserv.saveDepartment(0,request.getParameter("departmentname"),"true",user.getId(),user.getId());
		List<TblDepartment> depts = depserv.getDetailsview();
		mav.addObject("departments", depts);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User", user);
	mav.addObject("msg",msg);	
		return mav;		
	}	
/* ---------------------------department edit screen-----------------------------------------------------------*/
	
	@RequestMapping(value="/admin/departments/edit/{id}")
	public ModelAndView editAdminDepartments(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("departmentsedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
	  TblDepartment editdep = deptdao.getById(id); 
		mav.addObject("depdetailsforedit",editdep);
		mav.addObject("services", servicesdao.list());
		return mav;

	}
	

	@RequestMapping(value="/admin/departments/edit/{id}",method=RequestMethod.POST)
	 public ModelAndView updateAdminDepartments(HttpServletRequest request,@PathVariable("id") int id) {
	  ModelAndView mav = new ModelAndView();
	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  EmpDetails user=null;
	  if (principal instanceof EmpDetails) {
	   user = ((EmpDetails)principal);
	  }
	  List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*  List<TblEmpLeavereq> allempleave = empleavereq.view();
	  int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());*/
	//  List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
	  List<EmpDetails> emp1 = userDetails.getDetails();
	  List<TblDepartment> depts = deptdao.getDetails();
	  String msg=deptdao.saveDepartment(id,request.getParameter("departmentname"),request.getParameter("active"),user.getId(),user.getId());
	  System.out.println(msg);
	  if(msg.equalsIgnoreCase("Department Updated Successfully")) {
	   mav = new ModelAndView("redirect:/admin/empdep/register");
	   return mav;
	  }
	  else if(msg.equalsIgnoreCase("Department already exists")) {
	   mav = new ModelAndView("departmentsedit");
	  // mav.addObject("empattendances",empattendances);
	  /* mav.addObject("allempleave", allempleave);
	   mav.addObject("count",count);*/
	   mav.addObject("TransferRoleList", transferrole);
	   String role = user.getRole();
	   mav.addObject("Role",role);
	   mav.addObject("User", user);
	   mav.addObject("emp1",emp1);
	   mav.addObject("departments", depts);
	   mav.addObject("services", servicesdao.list());
	   mav.addObject("msg", msg);
	   TblDepartment editdep = deptdao.getById(id);
	   mav.addObject("depdetailsforedit",editdep);
	   return mav;
	  }
	  mav.addObject("msg", "Unable to Update");
	  return mav;
	 
	 }

	
	
	@RequestMapping(value="/admin/search/register")
	public ModelAndView empLeaveSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);

		String role = user.getRole();
		mav.addObject("Role",role);


		/*List<TblEmpLeavereq> leavereq =  empleavereq.viewSearch(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("month"), request.getParameter("status"));
		mav.addObject("employees", emp1);*/
		//mav.addObject("empleave", leavereq);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User",user);
		return mav;  
	}
	
	

	@RequestMapping(value="/admin/employee/search")
	public ModelAndView searchBars(HttpServletRequest req) {

		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String department = req.getParameter("department1");
		String designation = req.getParameter("designation1");
		List<TblDepartment> deptList = deptdao.getDetails();
		List<TblDesignation> designList = designationImpl.getDetails();

		List<EmpDetails> details = userDetails.viewSearch(firstname, lastname, department, designation);

		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("services", servicesdao.list());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;

		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*	List<TblEmpLeavereq> allempleave = empleavereq.view();
	//	int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		String mesg = "hi";

		mav.addObject("employees", details);


		mav.addObject("dupmsg", mesg);
		mav.addObject("departments", deptList);
		mav.addObject("designations", designList);

		List<TblDepartment> dests = deptdao .getDetails();
		mav.addObject("departments", dests);

		mav.addObject(user);
		return mav;		
	}
	@RequestMapping(value="/admin/employee/timesheetSearch")	 
	public ModelAndView attendance() {
		return new ModelAndView("redirect:/admin/employeetimesheets/register");}
	@RequestMapping(value="/admin/employee/timesheetSearch", method=RequestMethod.POST)
	public ModelAndView attendanceSearch(HttpServletRequest request) {
		
	
		ModelAndView mav = new ModelAndView("allemptimesheetrequests");
		mav.addObject("services", servicesdao.list());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		System.out.println(request.getParameter("month"));
		List<TblEmpAttendanceNew> attendence =  empattreq.viewSearch(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("month"), request.getParameter("status"));
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("User",user);
		mav.addObject("employees", emp1);
		mav.addObject("attendancelist",attendence);
		return mav;		
	}


	@RequestMapping(value= "/403")
	public ModelAndView Page403() {
		ModelAndView mav = new ModelAndView("403errorpage");
		return mav;
	}
	@RequestMapping(value= "/error/404")
	public ModelAndView Page404() {
		ModelAndView mav = new ModelAndView("404errorpage");
		return mav;
	}
	@RequestMapping(value= "/error/500")
	public ModelAndView Page500() {
		ModelAndView mav = new ModelAndView("500errorpage");

		return mav;
	}

	@RequestMapping(value= "admin/leavereq/edit/{id}")
	public ModelAndView empLeaveEdit(HttpSession session, @PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("empleaverequestedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}

		String role = user.getRole();
		mav.addObject("Role",role);
		//List<TblEmpLeavereq> allempleave = ers.view();
		//List<TblEmpLeavereq> leavereq =  ers.viewbyid(id);
		//TblEmpLeavereq leavereq=ers.getById(id);
		//int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		//List<EmpDetails> emp1 = userDetails.getDetails();
		//List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		//mav.addObject("empattendances",empattendances);
	//	mav.addObject("allempleave", allempleave);
	//	mav.addObject("count",count);
		mav.addObject("User", user);
		//mav.addObject("empleave", leavereq);
		//mav.addObject("manservices", mandao.list());
		//mav.addObject("employees", emp1);
		//mav.addObject("count",count);
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	//	mav.addObject("TransferRoleList", transferrole);
		mav.addObject("id",id);
		mav.addObject("services", servicesdao.list());
		mav.addObject(user);
		return mav;
	}
	@RequestMapping(value="/admin/leave/upload/{id}", method=RequestMethod.POST)
	public ModelAndView updateLeaveRequest(HttpServletRequest request,@PathVariable("id") int id) {
		//ModelAndView mav = new ModelAndView("redirect:/admin/search/register");
		String fromdate = request.getParameter("fromdate");
		String[] date = fromdate.split("-");
		String todate = request.getParameter("todate");
		SimpleDateFormat formatfromdate = new SimpleDateFormat("yyyy-mm-dd");
		Date reqfromDate = null;
		Date reqtoDate = null;
		try {
			reqfromDate = formatfromdate.parse(fromdate);
			reqtoDate = formatfromdate.parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate Day1 = LocalDate.parse(fromdate);
		LocalDate Day2 = LocalDate.parse(todate);

		long daysNegative = ChronoUnit.DAYS.between(Day1, Day2);
	//	ers.updateLeave(id,request.getParameter("leavetype"),request.getParameter("fromdate"),request.getParameter("todate"),request.getParameter("leavereason"),(int)daysNegative,request.getParameter("reason"));
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		return mav;

	}

	
	@RequestMapping(value="/admin/leavehistory/{id}")
	public ModelAndView empLeavehistory(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
	//	TblEmpLeavereq leavereq=ers.getById(id);
		//mav.addObject("empleavehistory", leavereq);
		return mav;
	}
/*	@RequestMapping(value="/leavehistory", method=RequestMethod.POST)
	@ResponseBody
	//public TblEmpLeavereq leaveHistory(HttpServletRequest request) {
	//	TblEmpLeavereq leavereq=ers.getById(Integer.parseInt(request.getParameter("id")));
		//return leavereq;
		
	}*/
	
	/*--------------Adding the num of Days in Leave-----------------*/ 
	@RequestMapping(value= "admin/leavecount/register")
	public ModelAndView addLeave() {
		ModelAndView mav = new ModelAndView("leavesCount");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		mav.addObject("User",user);
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		//System.out.println("1");
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//System.out.println("2");
		mav.addObject("User", user);
		//mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<Tblleavestype> leaves = leave.getDetails();
		System.out.println(leaves);
		for(Tblleavestype tbl:leaves) {
			System.out.println(tbl.getLeavetype());
		}
		mav.addObject("leavetype",leaves);
		System.out.println("3");
		//LeaveAddition lea=new LeaveAddition();
		List<LeaveAddition> leavesCount = leave.getDetailsofleavetye(0);
		for(LeaveAddition ll:leavesCount) {
			System.out.println(ll.getNumleavedays());
		}
		System.out.println("4");
		mav.addObject("DaysList",leavesCount);
		mav.addObject("services", servicesdao.list());
		return mav;		
	}
	@RequestMapping(value= "admin/leavecount/register",method=RequestMethod.POST)
	public ModelAndView addLeavepost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("leavesCount");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//int a=Integer.parseInt(request.getParameter("count"));
		System.out.println("control comes");
		String msg=leave.save(0,Integer.parseInt(request.getParameter("leavetype")), Integer.parseInt(request.getParameter("count")), user.getId(), user.getId() ,true);
		//String msg=leave.save(request.getParameter("department1"),a,user.getEid());
		//System.out.println("method call");
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User", user);
		List<Tblleavestype> leaves = leave.getDetails();
		mav.addObject("leavetype",leaves);
		List<LeaveAddition> leavesCount = leave.getDetailsofleavetye(0);
		mav.addObject("DaysList",leavesCount);
		mav.addObject("services", servicesdao.list());
		
		mav.addObject("msg",msg);
		return mav;		
	}
	@RequestMapping(value= "days/edit/{id}")
	public ModelAndView editdayscount(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("leavesedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("services", servicesdao.list());
		mav.addObject("empattendances",empattendances);
		mav.addObject("User",user);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		//Tblleaves tblday = leave.getDetaisById(id);
		List<LeaveAddition> leavesCount = leave.getDetailsofleavetye(id);
		LeaveAddition editdep=null;
		for(LeaveAddition list:leavesCount)
		{
			editdep=list;
		}
		System.out.println(editdep);
		mav.addObject("editdep",editdep);
		
		List<Tblleavestype> tblday = leave.getDetails();
		System.out.println(tblday);
		mav.addObject("tblday",tblday);
		mav.addObject("services", servicesdao.list());
		
		return mav;
	}
	@RequestMapping(value= "days/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updatedays(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());*/
		//List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		//List<EmpDetails> emp1 = userDetails.getDetails();
		String active=request.getParameter("active");
		boolean act=Boolean.parseBoolean(active);
		//System.out.println(id+""+);
		String msg=leave.save(id,Integer.parseInt(request.getParameter("leavetype")), Integer.parseInt(request.getParameter("count")), user.getId(), user.getId() ,act);
		//String msg=leave.save(id, leavetypeid, count, cid, uid, active)
		System.out.println(active);
	   		
		//Tblleaves lday=new Tblleaves(name,day);
		//String msg=leave.updateLeave(id, day,user.getEid(),active);
		if(msg.equalsIgnoreCase("Number of Leaves for the Leave Type UpDated Successfully")) {
			mav = new ModelAndView("redirect:/admin/leavecount/register");
			mav.addObject("services", servicesdao.list());
			return mav;
		}else if(msg.equalsIgnoreCase("LeaveCount for the Leave Type is Already Assigned.Please try Again")) {
			System.out.println("else block");
			mav = new ModelAndView("leavesedit");
			//mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
			/*mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);*/
		//	mav.addObject("TransferRoleList", transferrole);
			String role = user.getRole();
			mav.addObject("Role",role);
			//Holidays holidays = himpl.getHolidayById(id);
			//mav.addObject("Holiday",holidays);
			List<Tblleavestype> tblday = leave.getDetails();
			List<LeaveAddition> leavesCount = leave.getDetailsofleavetye(id);
			LeaveAddition editdep=null;
			for(LeaveAddition list:leavesCount)
			{
				editdep=list;
			}
			mav.addObject("editdep",editdep);
			mav.addObject("tblday",tblday);
			mav.addObject("services", servicesdao.list());
			mav.addObject("msg",msg);
			return mav;
		}
		mav.addObject("msg", "Unable to Update");
		return mav;
	}
	@RequestMapping(value= "/admin/days/delete/{id}")
	public ModelAndView deleteLeaveTypes(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leavecount/register");	
	System.out.println("hi");
		leave.deleteByid(id);
		return mav;
	}
	
	
/*	@RequestMapping(value= "admin/leavedays/register")
	public ModelAndView addLeaveDetails(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");
		LeaveManiplicatiionImpl impl=new LeaveManiplicatiionImpl();
		int a=Integer.parseInt(request.getParameter("count"));
		Tblleaves add=new Tblleaves(request.getParameter("department1"),a);
		impl.save(add);
		
		return mav;
	}
*/
	/*-----------------------------------LeaveType-----------------------------*/
	@RequestMapping(value="/admin/leavetype/register")
	public ModelAndView leaveTypePage() {
		ModelAndView mav = new ModelAndView("leavestype");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	    List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*	List<TblEmpLeavereq> allempleave = empleavereq.view();
        int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("User",user);
	    mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<Tblleavestype> leavetypes =  ltdi.viewAll();
		mav.addObject("leavetypeslist",leavetypes);
		mav.addObject("services", servicesdao.list());
		return mav;		
	}

	@RequestMapping(value= "/admin/leavetype/register",method=RequestMethod.POST)
	public ModelAndView saveLeaveType(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("leavestype");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		/*List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		//mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		String leavetype=request.getParameter("leavetype");
		//Tblleavestype lt=new Tblleavestype(leavetype,true,user.getEid(),new Date(),null,null);
		String msg=ltdi.saveLeaveType(0, request.getParameter("leavetype"),"true",user.getId(),user.getId());                   
		List<Tblleavestype> leavetypes =  ltdi.viewAll();
		mav.addObject("leavetypeslist",leavetypes);
		mav.addObject("services", servicesdao.list());
	 mav.addObject("msg",msg);
		return mav;
	}
	

		
	
/*	@RequestMapping(value= "/admin/leavetype/delete/{id}")
	public ModelAndView deleteLeaveType(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leavetype/register");	
		ltdi.deleteLeaveType(id);
		return mav;
	}
	*/
	@RequestMapping(value="/admin/leavetype/edit/{id}")
	public ModelAndView editLeaveType(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("leavestypeedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	/*	List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
        mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);*/
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		
		Tblleavestype editlt = ltdi.getById(id); 
		mav.addObject("leavestypeedit",editlt);
		mav.addObject("services", servicesdao.list());
		return mav;

	}
	
	
	
	@RequestMapping(value= "/admin/leavetype/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateLeaveType(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		//List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
	//	List<TblEmpLeavereq> allempleave = empleavereq.view();
	//	int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		//List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
	//	List<EmpDetails> emp1 = userDetails.getDetails();
		String lt=request.getParameter("leavetype");
		String active=request.getParameter("active");
		System.out.println(active);
		
		
		
		mav.addObject("User",user);
		
	//String msg=ltdi.updateLeaveType(id,lt,user.getEid(),active);
		String msg=ltdi.saveLeaveType(id, lt, active, user.getId(),user.getId());
		if(msg.equalsIgnoreCase("Leave Type Updated Successfully")) {
			mav = new ModelAndView("redirect:/admin/leavetype/register");
			
			return mav;
		}else if(msg.equalsIgnoreCase("Leavetype Already Exists")) {
			mav = new ModelAndView("leavestypeedit");
			//mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
	//	mav.addObject("allempleave", allempleave);
		//	mav.addObject("count",count);
			//mav.addObject("TransferRoleList", transferrole);
			String role = user.getRole();
			mav.addObject("Role",role);
			 tlt = ltdi.getLeaveTypeById(id);
			mav.addObject("leavestypeedit",tlt);
			mav.addObject("services", servicesdao.list());
			mav.addObject("msg",msg);
			return mav;
		}
		mav.addObject("msg", "Unable to Update");
		return mav;
		
		
	}
	
		@RequestMapping(value= "/admin/profile/register/{id}")
	public ModelAndView empProfileview (@PathVariable("id") int id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		String role = user.getRole();
		ModelAndView mav = new ModelAndView("useremployee2");
		List<EmployeeViewPage> emp1 = userDetails.view(id);
		EmployeeViewPage editdep=null;
		for(EmployeeViewPage list:emp1)
		{
			editdep=list;
		}
		mav.addObject("emp",editdep);
		mav.addObject("Role", role);
		mav.addObject("User", user);
		mav.addObject("services", servicesdao.list());
		return mav;
	}
		
		@RequestMapping(value = "/getTags", method = RequestMethod.GET)
		public @ResponseBody
		List<EmployeeViewPage> getTags(@RequestParam String firstname) {
			System.out.println(firstname+"page");

			return userDetails.simulateSearchResult(firstname);

		}
		@RequestMapping(value = "/lastNames", method = RequestMethod.GET)
		public @ResponseBody
		List<EmployeeViewPage> lastName(@RequestParam String lastname) {
			

			return userDetails.simulateSearchResultLastName(lastname);

		}
	
	

}
