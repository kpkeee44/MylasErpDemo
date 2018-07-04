package mylas.com.erp.demo.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.Holidays;
import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblManRoleTransfer;
import mylas.com.erp.demo.appservices.UserServiceImpl;
import mylas.com.erp.demo.dao.DepartmentDao;
import mylas.com.erp.demo.dao.DesignationDao;
import mylas.com.erp.demo.dao.EmpAttendenceDao;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;
import mylas.com.erp.demo.dao.EmpServicesDao;
import mylas.com.erp.demo.dao.EmployeeDao;
import mylas.com.erp.demo.dao.HolidayDao;
import mylas.com.erp.demo.dao.ManagerServicesDao;
import mylas.com.erp.demo.dao.RoleTrasforDao;
import mylas.com.erp.demo.dao.ServicesDao;
import mylas.com.erp.demo.daoimpl.EmpAttendanceDaoImpl;
import mylas.com.erp.demo.daoimpl.RoletransfoeDaoImpl;
import mylas.com.erp.demo.exceptions.UserBlockedException;
import mylas.com.erp.demo.operations.LoginOperations;
import mylas.com.erp.demo.service.Client;
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
	EmpLeaveRequestDao ers;




	/*
	 * Slide Bar Page Handlers Start
	 */



	@RequestMapping(value="/admin/allemp/register")
	public ModelAndView allEmpPage() {
		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("services", servicesdao.list());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		List<TblDepartment> deptList = deptdao.getDetails();
		List<TblDesignation> designList = designationImpl.getDetails();
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User",user);
		mav.addObject("title", "Employee Regester Page");
		mav.addObject("userClickReg", true);
		String mesg = "hi";
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("employees", emp1);
		mav.addObject("dupmsg", mesg);
		//Departments
		List<TblDepartment> dests = deptdao.getDetails();
		mav.addObject("departments", dests);
		mav.addObject("departments", deptList);
		mav.addObject("designations", designList);
		mav.addObject(user);
		return mav;		
	}




	@RequestMapping(value="/admin/empdep/register")
	public ModelAndView empDepartmentPage() {
		ModelAndView mav = new ModelAndView("departments");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("User", user);

		List<TblDepartment> depts = deptdao.getDetails();
		mav.addObject("departments", depts);
		mav.addObject("services", servicesdao.list());

		return mav;		
	}

	@RequestMapping(value="/admin/empholidays/register")
	public ModelAndView empHolidayPage() {
		ModelAndView mav = new ModelAndView("holidays");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<Holidays> holidays = himpl.viewAll();
		mav.addObject("HolidaysList",holidays);
		mav.addObject("services", servicesdao.list());

		return mav;		
	}

	@RequestMapping(value="/admin/leaverequests/register")
	public ModelAndView empLeaveReqPage() {
		ModelAndView mav = new ModelAndView("allempleaverequests");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);

		List<TblEmpLeavereq> leavereq =  empleavereq.view();
	
		mav.addObject("employees", emp1);
		mav.addObject("empleave", leavereq);
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
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<TblEmpAttendanceNew> attendances =  empattreq.getDetails();
		mav.addObject("User",user);
	
		mav.addObject("employees", emp1);
		mav.addObject("attendancelist",attendances);
		return mav;		
	}



	@RequestMapping(value="/admin/empdesig/register")
	public ModelAndView empDesignationPage() {
		ModelAndView mav = new ModelAndView("designations");

		List<TblDesignation> depts = designationImpl.getDetails();
		mav.addObject("designations", depts);
		mav.addObject("services", servicesdao.list());
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
		mav.addObject("Role",role);

		return mav;		
	}
	/*
	 * SlideBar Page handler Close
	 */

	/*
	 * Master Form Handlers
	 */

	@RequestMapping(value="/admin/allemp/register", method=RequestMethod.POST)
	public ModelAndView EmpPage(HttpServletRequest request, HttpServletResponse response) throws ConstraintViolationException{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		EmpDetails emp = new EmpDetails(null, request.getParameter("cpswd"), null, request.getParameter("empid"), request.getParameter("email"), request.getParameter("firstname"), null, request.getParameter("lastname"), false, null, request.getParameter("pswd"), null, request.getParameter("uname"), null, null,null,null);

		emp.setLoginStatus(UserServiceImpl.Login_Status_Active);
		emp.setRole("MANAGER_ROLE");
		emp.setManagerid(user.getEid());
		emp.setJdate(request.getParameter("joindate"));


		emp.setPhone(request.getParameter("phone"));
		emp.setCompName(request.getParameter("company"));
		emp.setDepartment(request.getParameter("department"));
		emp.setDesignation("Manager");


		ModelAndView mav = new ModelAndView("employees");
		mav.addObject("services", servicesdao.list());
		String mesg = "hi";
		mesg = userDetails.getConnection(emp);
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);




		//Departments
		List<TblDepartment> dests = deptdao.getDetails();
		mav.addObject("departments", dests);


		String role = user.getRole();
		mav.addObject("Role",role);
		
		mav.addObject("employees", emp1);
		

		mav.addObject("dupmsg", mesg);
		mav.addObject("User",user);
		mav.addObject("employee", emp);

		return mav;		
	}

	@RequestMapping(value="/admin/allemp/delete/{id}")
	public ModelAndView deleteEmpPage(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");

		userDetails.deleteDetails(id);
		return mav;

	}

	@RequestMapping(value="/admin/empdep/register", method=RequestMethod.POST)
	public ModelAndView addEmpDepartmentPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("departments");
		TblDepartment tbldep = new TblDepartment(request.getParameter("departmentname"),null,null,null);
		tbldep.setFromdate(request.getParameter("fromdate"));
		tbldep.setActivestate(true);
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		DepartmentService depserv = new DepartmentService();
		depserv.saveDepartment(tbldep);
		List<TblDepartment> depts = depserv.getDetails();
		mav.addObject("departments", depts);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User", user);
		return mav;		
	}

	@RequestMapping(value="/admin/empdesig/register", method=RequestMethod.POST)
	public ModelAndView AddEmpDesignationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("designations");
		TblDesignation tbldes = new TblDesignation(request.getParameter("designationname"), request.getParameter("department"),null,null,null);
		tbldes.setFromdate(request.getParameter("fromdate"));
		tbldes.setActivestate(true);
		DesignationService depdetails = new DesignationService();
		depdetails.saveDetails(tbldes);
		List<TblDesignation> depts = depdetails.getDetails();
		mav.addObject("designations", depts);
		mav.addObject("services", servicesdao.list());
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		return mav;		
	}


	@RequestMapping(value="/admin/allemp/register/{id}/employeedetails")
	public ModelAndView eachEmpDetailsPage(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("employeedetails");
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
		mav.addObject("services", servicesdao.list());
		return mav;		
	}

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
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
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
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public ModelAndView adminRegisterPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UserBlockedException {
		EmpDetails emp = new EmpDetails(null, request.getParameter("confirm"), null, request.getParameter("empid"), request.getParameter("email"), null, null, null, false, null, request.getParameter("password"), null, request.getParameter("username"),null,null,null,null);

		emp.setLoginStatus(UserServiceImpl.Login_Status_Active);
		emp.setRole("ADMIN_ROLE");
		emp.setFname("Admin");
		emp.setLname("Admin");
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
	}

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
	@RequestMapping(value="/admin/departments/delete/{id}")
	public ModelAndView deleteAdminDepartments(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdep/register");

		deptdao.deleteDetails(id);
		return mav;

	}

	@RequestMapping(value="/admin/designations/delete/{id}")
	public ModelAndView deleteAdminDesignations(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdesig/register");

		designationImpl.deleteDetails(id);
		return mav;

	}



	@RequestMapping(value= "/admin/attendance/approve/{id}")
	public ModelAndView empTimesheetApprovePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/employeetimesheets/register");
		String reason = "Approved";
		boolean status = true;
		empattreq.update(status, id);		
		return mav;
	}
	@RequestMapping(value= "/admin/attendance/decline/{id}")
	public ModelAndView empTimesheetdeclinePage(HttpSession session,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/employeetimesheets/register");
		String reason = "Decline";
		boolean status = false;
		empattreq.update(status, id);
		return mav;
	}
	@RequestMapping(value= "/admin/leave/approve/{id}")
	public ModelAndView empLeaveApprovePage(HttpSession session,@PathVariable("id") int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		List<TblEmpLeavereq> leavereq =  empleavereq.view();
		String reason = request.getParameter("reason");
		boolean status = true;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	

		return mav;
	}
	@RequestMapping(value= "/admin/leave/decline/{id}")
	public ModelAndView empLeavedeclinePage(HttpSession session,@PathVariable("id") int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		List<TblEmpLeavereq> leavereq =  empleavereq.view();		
		String reason = request.getParameter("reason");
		boolean status = false;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	

		return mav;
	}
	@RequestMapping(value= "/mytest")
	public ModelAndView testmyPage() {
		ModelAndView mav = new ModelAndView("500");
		System.out.println("inside mytest");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		List<EmpDetails> emp1 = userDetails.getDetails();
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("employees", emp1);


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
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		String dt=request.getParameter("hdate");
		String[]sdt=dt.split("-");		
		String name=request.getParameter("holiday");
		Holidays hday=new Holidays(name, sdt[2], sdt[1], sdt[0]);
		himpl.saveHoliday(hday);
		List<Holidays> holidays = himpl.viewAll();
		mav.addObject("HolidaysList",holidays);
		mav.addObject("services", servicesdao.list());
		return mav;
	}

	@RequestMapping(value= "/holidays/delete/{id}")
	public ModelAndView deleteHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empholidays/register");	
		himpl.deleteHoliday(id);
		return mav;
	}
	@RequestMapping(value= "/holidays/edit/{id}")
	public ModelAndView editHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("holidaysedit");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		Holidays holidays = himpl.getHolidayById(id);
		mav.addObject("Holiday",holidays);
		mav.addObject("services", servicesdao.list());
		return mav;
	}
	@RequestMapping(value= "/holidays/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateHoliday(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empholidays/register");

		String dt=request.getParameter("hdate");
		String[]sdt=dt.split("-");		
		String name=request.getParameter("holiday");
		Holidays hday=new Holidays(name, sdt[2], sdt[1], sdt[0]);
		himpl.updateHOliday(id, hday);
		return mav;
	}
	@RequestMapping(value="/admin/departments/edit/{id}")
	public ModelAndView editAdminDepartments(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("departmentsedit");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		TblDepartment editdep = deptdao.getById(id); 
		mav.addObject("depdetailsforedit",editdep);
		mav.addObject("services", servicesdao.list());
		return mav;

	}
	@RequestMapping(value="/admin/empdep/edit/{id}")
	public ModelAndView updateAdminDepartments(HttpServletRequest request,@PathVariable("id") int id) {

		//Integer id = (Integer) request.getAttribute("DepId");
		ModelAndView mav = new ModelAndView("redirect:/admin/empdep/register");
		deptdao.updateDetails(id, request.getParameter("departmentname"),request.getParameter("todate")); 
		return mav;

	}

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
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		
		mav.addObject("User",user);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		TblDesignation editdep = designationImpl.getById(id);
		List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
		mav.addObject("depdetailsforedit",editdep);
		mav.addObject("services", servicesdao.list());
		return mav;

	}
	@RequestMapping(value="/admin/empdesignation/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateAdmindesignation(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdesig/register");
		designationImpl.updateDetails(id, request.getParameter("designationname"),request.getParameter("department"),request.getParameter("todate"));
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
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);

		String role = user.getRole();
		mav.addObject("Role",role);


		List<TblEmpLeavereq> leavereq =  empleavereq.viewSearch(request.getParameter("uname"), request.getParameter("month"), request.getParameter("status"));
		mav.addObject("employees", emp1);
		mav.addObject("empleave", leavereq);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User",user);
		return mav;  
	}
	@RequestMapping(value="/admin/empdetais/edit/{id}")
	public ModelAndView editEmpDetails(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("employeesedit");
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
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);

		String role = user.getRole();
		mav.addObject("Role",role);
		String mesg = "hi";
		EmpDetails editdep =userDetails.getById(id);
		List<TblDepartment> depts1 = deptdao.getDetails();
		mav.addObject("departments", depts1);
		List<TblDesignation> depts = designationImpl.getDetails();
		mav.addObject("designations", depts);
		mav.addObject("edetais",editdep);
		mav.addObject("services", servicesdao.list());
		mav.addObject("dupmsg", mesg);
		return mav;

	}
	@RequestMapping(value="/admin/updatedetails/edit/{id}")
	public ModelAndView updateEmpDetails(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");
		userDetails.updateEditDetails(id,request.getParameter("firstname"),request.getParameter("lastname"), request.getParameter("uname"),request.getParameter("empid"),request.getParameter("pswd"),request.getParameter("cpswd"),request.getParameter("joindate"),request.getParameter("phone"),request.getParameter("company"),request.getParameter("department"),request.getParameter("relievingdate"));
		return mav;

	}
	
	 @RequestMapping(value="/admin/employee/search")
		public ModelAndView searchBars(HttpServletRequest req) {

			String username = req.getParameter("username");
			String department = req.getParameter("department");
			String designation = req.getParameter("designation");
			List<TblDepartment> deptList = deptdao.getDetails();
			List<TblDesignation> designList = designationImpl.getDetails();
			
			List<EmpDetails> details = userDetails.viewSearch(username, department, designation);
			
			ModelAndView mav = new ModelAndView("employees");
			mav.addObject("services", servicesdao.list());
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
			mav.addObject("empattendances",empattendances);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
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
		public ModelAndView attendanceSearch(HttpServletRequest request) {
			String username = request.getParameter("username");
			System.out.println(request.getParameter("month"));
			ModelAndView mav = new ModelAndView("allemptimesheetrequests");
			mav.addObject("services", servicesdao.list());
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			EmpDetails user=null;
			if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
			}
			
			String role = user.getRole();
			mav.addObject("Role",role);
			List<TblEmpAttendanceNew> attendances =  empattreq.viewSearch(request.getParameter("username"), request.getParameter("month"), request.getParameter("status"));
			List<EmpDetails> emp1 = userDetails.getDetails();
			mav.addObject("User",user);
			mav.addObject("employees", emp1);
			mav.addObject("attendancelist",attendances);
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


}
