package mylas.com.erp.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.Holidays;
import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.TblEmpLeavereq;
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

	static String emailToRecipient, emailSubject, emailMessage;

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
		mav.addObject("User",user);
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
		mav.addObject("User", user);
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
		mav.addObject("User",user);
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
		EmpDetails emp = new EmpDetails(null, request.getParameter("cpswd"), null, request.getParameter("empid"), request.getParameter("email"), request.getParameter("firstname1"), null, request.getParameter("lastname1"), false, null, request.getParameter("pswd"), null, request.getParameter("uname"), null, null,null,null);

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
/*
	@RequestMapping(value="/admin/allemp/delete/{id}")
	public ModelAndView deleteEmpPage(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/allemp/register");

		userDetails.deleteDetails(id);
		return mav;

	}
*/
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
		String msg=depserv.saveDepartment(tbldep);
		List<TblDepartment> depts = depserv.getDetails();
		mav.addObject("departments", depts);
		mav.addObject("services", servicesdao.list());
		mav.addObject("User", user);
		mav.addObject("msg",msg);		
		return mav;		
	}

	@RequestMapping(value="/admin/empdesig/register", method=RequestMethod.POST)
	public ModelAndView AddEmpDesignationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("designations");
		TblDesignation tbldes = new TblDesignation(request.getParameter("designationname"), request.getParameter("department"),null,null,null);
		tbldes.setFromdate(request.getParameter("fromdate"));
		tbldes.setActivestate(true);
		DesignationService depdetails = new DesignationService();
		
		List<TblDesignation> depts = depdetails.getDetails();
		
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
		List<TblDepartment> depts1 = deptdao.getDetails();
		String dgmsg=depdetails.saveDetails(tbldes);
		mav.addObject("departments", depts1);
		mav.addObject("empattendances",empattendances);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		mav.addObject("dgmsg",dgmsg);
		mav.addObject("User", user);
		mav.addObject("designations", depts);
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
		EmpDetails Edetails = null;
		Edetails = userDetails.getById(id);
		System.out.println(Edetails.getEid());
		int a[]=ers.countSum(Edetails.getEid());
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
		/*EmpDetails Edetails = null;*/
		/*Edetails = userDetails.getById(id);*/
		mav.addObject("empID", id);
		mav.addObject("employee",Edetails);
		mav.addObject("User", user);
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
		
		String reason = "Approved";
		boolean status = true;
		empattreq.update(status, id);

		emailSubject = "Time Sheet Request Status For:";
		emailMessage = "A new Time Sheet Request was approved :"+"On: "+new Date()+" By:  "+user.getFname()+" "+user.getLname();
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
		emailMessage = "A new Time Sheet Request was declined :"+"On: "+new Date()+" By:  "+user.getFname()+" "+user.getLname();
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
		List<TblEmpLeavereq> leavereq =  empleavereq.view();
		String reason = request.getParameter("reason");
		boolean status = true;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was approved :"+"On: "+new Date()+" By:  "+user.getFname()+" "+user.getLname();
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
		List<TblEmpLeavereq> leavereq =  empleavereq.view();		
		String reason = request.getParameter("reason");
		boolean status = false;
		String UMsg = empleavereq.update(id,reason,status);
		mav.addObject("empleave", leavereq);
		mav.addObject("UMsg", UMsg+" "+reason);
		mav.addObject("manservices", mandao.list());	
		emailSubject = "Leave Request Status For:";
		emailMessage = "A new Leave Request was declined :"+"On: "+new Date()+" By:  "+user.getFname()+" "+user.getLname();
		emailToRecipient = user.getEmail();
		//System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
		emailsender.javaMailService("bgrao@mylastech.com", "14131f0008", emailToRecipient, emailMessage, emailSubject);
		
		return mav;
	}
	@RequestMapping("/mytest")
	public ModelAndView testmyPage() {
		System.out.println("comes");
		/*int a[]=empleavereq.countSum();
		
		System.out.println(a[0]);
		System.out.println(a[1]);*/
		ModelAndView mav = new ModelAndView("adminindex");
		//EmailDaoImpl email=new EmailDaoImpl();
		
		EmpLeaveRequestService leaveserv = new EmpLeaveRequestService();
		leaveserv.count();
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
		/*String[]sdt=dt.split("-");	*/	
		String name=request.getParameter("holiday");
		Holidays hday=new Holidays(name,dt);
		String msg=himpl.saveHoliday(hday);
		List<Holidays> holidays = himpl.viewAll();
		mav.addObject("HolidaysList",holidays);
		mav.addObject("services", servicesdao.list());
		mav.addObject("msg",msg);
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
		mav.addObject("User",user);
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
		ModelAndView mav = new ModelAndView();
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
		String dt=request.getParameter("hdate");
		String[]sdt=dt.split("-");		
		String name=request.getParameter("holiday");
		Holidays hday=new Holidays(name, dt);
		String msg=himpl.updateHOliday(id, hday);
		if(msg.equalsIgnoreCase("HoliDay UpDated Successfully")) {
			mav = new ModelAndView("redirect:/admin/empholidays/register");
			return mav;
		}else if(msg.equalsIgnoreCase("HoliDay is Already Exists.Please try Again")) {
			mav = new ModelAndView("holidaysedit");
			mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
			mav.addObject("TransferRoleList", transferrole);
			String role = user.getRole();
			mav.addObject("Role",role);
			Holidays holidays = himpl.getHolidayById(id);
			mav.addObject("Holiday",holidays);
			mav.addObject("services", servicesdao.list());
			mav.addObject("msg",msg);
			return mav;
		}
		mav.addObject("msg", "Unable to Update");
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
	@RequestMapping(value="/admin/departments/edit/{id}",method=RequestMethod.POST)
	 public ModelAndView updateAdminDepartments(HttpServletRequest request,@PathVariable("id") int id) {
	  ModelAndView mav = new ModelAndView();
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
	  List<TblDepartment> depts = deptdao.getDetails();
	  String msg=deptdao.updateDetails(id, request.getParameter("departmentname"),request.getParameter("todate"));
	  System.out.println(msg);
	  if(msg.equalsIgnoreCase("Department UpDated Successfully")) {
	   mav = new ModelAndView("redirect:/admin/empdep/register");
	   return mav;
	  }
	  else if(msg.equalsIgnoreCase("Department is Already Exists.Please try Again")) {
	   mav = new ModelAndView("departmentsedit");
	   mav.addObject("empattendances",empattendances);
	   mav.addObject("allempleave", allempleave);
	   mav.addObject("count",count);
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
	/*@RequestMapping(value="/admin/empdesignation/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateAdmindesignation(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/empdesig/register");
		designationImpl.updateDetails(id, request.getParameter("designationname"),request.getParameter("department"),request.getParameter("todate"));
		return mav;

	}*/
	 @RequestMapping(value="/admin/designation/edit/{id}",method=RequestMethod.POST)
	 public ModelAndView updateAdmindesignation(HttpServletRequest request,@PathVariable("id") int id) {
	  ModelAndView mav = new ModelAndView();
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
	  List<TblDesignation> design = designationImpl.getDetails();
	  String dsmsg=designationImpl.updateDetails(id, request.getParameter("designationname"),request.getParameter("department"),request.getParameter("todate"));
	  System.out.println(dsmsg);
	  if(dsmsg.equalsIgnoreCase("Designation UpDated Successfully")) {
	   mav = new ModelAndView("redirect:/admin/empdesig/register");
	   return mav;
	  }
	  else if(dsmsg.equalsIgnoreCase("Designation already exists.Please try Again")) {
	   mav = new ModelAndView("designationsedit");
	   mav.addObject("empattendances",empattendances);
	   mav.addObject("allempleave", allempleave);
	   mav.addObject("count",count);
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


		List<TblEmpLeavereq> leavereq =  empleavereq.viewSearch(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("month"), request.getParameter("status"));
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
		mav.addObject("User",user);
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
		TblEmpLeavereq leavereq=ers.getById(id);
		//int count = ers.countEmployee(user.getEid()) + attimpl.countEmployee(user.getEid());
		//List<EmpDetails> emp1 = userDetails.getDetails();
		//List<TblEmpAttendanceNew> empattendances =  attimpl.getDetails();
		//mav.addObject("empattendances",empattendances);
	//	mav.addObject("allempleave", allempleave);
	//	mav.addObject("count",count);
		mav.addObject("User", user);
		mav.addObject("empleave", leavereq);
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
		ers.updateLeave(id,request.getParameter("leavetype"),request.getParameter("fromdate"),request.getParameter("todate"),request.getParameter("leavereason"),(int)daysNegative,request.getParameter("reason"));
		ModelAndView mav = new ModelAndView("redirect:/admin/leaverequests/register");
		return mav;

	}

	
	@RequestMapping(value="/admin/leavehistory/{id}")
	public ModelAndView empLeavehistory(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("allempleaverequests");
		TblEmpLeavereq leavereq=ers.getById(id);
		mav.addObject("empleavehistory", leavereq);
		return mav;
	}
	@RequestMapping(value="/leavehistory", method=RequestMethod.POST)
	@ResponseBody
	public TblEmpLeavereq leaveHistory(HttpServletRequest request) {
		TblEmpLeavereq leavereq=ers.getById(Integer.parseInt(request.getParameter("id")));
		return leavereq;
		
	}
	@RequestMapping(value= "admin/leavecount/register")
	public ModelAndView addLeave() {
		ModelAndView mav = new ModelAndView("leavesCount");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		mav.addObject("User",user);
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
		mav.addObject("User", user);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		List<Tblleavestype> leaves = leave.getDetails();
		mav.addObject("departments",leaves);
		List<Tblleaves> leavesCount = leave.getDetailsofleavetye();
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
		int a=Integer.parseInt(request.getParameter("count"));
		Tblleaves add=new Tblleaves(request.getParameter("department1"),a);
		String msg=leave.save(add);
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
		List<Tblleavestype> leaves = leave.getDetails();
		mav.addObject("departments",leaves);
		List<Tblleaves> leavesCount = leave.getDetailsofleavetye();
		mav.addObject("DaysList",leavesCount);
		mav.addObject("services", servicesdao.list());
		
		mav.addObject("msg",msg);
		return mav;		
	}
	@RequestMapping(value= "/admin/days/delete/{id}")
	public ModelAndView deleteLeaveTypes(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leavecount/register");	
	System.out.println("hi");
		leave.deleteByid(id);
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
		List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		mav.addObject("services", servicesdao.list());
		mav.addObject("empattendances",empattendances);
		mav.addObject("User",user);
		mav.addObject("allempleave", allempleave);
		mav.addObject("count",count);
		mav.addObject("TransferRoleList", transferrole);
		String role = user.getRole();
		mav.addObject("Role",role);
		Tblleaves tblday = leave.getDetaisById(id);
		mav.addObject("tblday",tblday);
		
		return mav;
	}
	@RequestMapping(value= "/days/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updatedays(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
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
		String name=request.getParameter("type");		
		int day=Integer.parseInt(request.getParameter("count"));
		mav.addObject("services", servicesdao.list());
		Tblleaves lday=new Tblleaves(name,day);
		String msg=leave.updateLeave(id, lday);
		if(msg.equalsIgnoreCase("UpDated Successfully")) {
			mav = new ModelAndView("redirect:/admin/leavecount/register");
			mav.addObject("services", servicesdao.list());
			return mav;
		}else if(msg.equalsIgnoreCase("LeaveType is Already Exists.Please try Again")) {
			mav = new ModelAndView("leavesedit");
			mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
			mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
			mav.addObject("TransferRoleList", transferrole);
			String role = user.getRole();
			mav.addObject("Role",role);
			/*Holidays holidays = himpl.getHolidayById(id);
			mav.addObject("Holiday",holidays);*/
			Tblleaves tblday = leave.getDetaisById(id);
			mav.addObject("tblday",tblday);
			mav.addObject("services", servicesdao.list());
			mav.addObject("msg",msg);
			return mav;
		}
		mav.addObject("msg", "Unable to Update");
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
	@RequestMapping(value="/admin/leavetype/register")
	public ModelAndView leaveTypePage() {
		ModelAndView mav = new ModelAndView("leavestype");
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
		String leavetype=request.getParameter("leavetype");
		Tblleavestype lt=new Tblleavestype(leavetype);
		String msg=ltdi.saveLeaveType(lt);
		List<Tblleavestype> leavetypes =  ltdi.viewAll();
		mav.addObject("leavetypeslist",leavetypes);
		mav.addObject("services", servicesdao.list());
		mav.addObject("msg",msg);
		return mav;
	}

		
	
	@RequestMapping(value= "/admin/leavetype/delete/{id}")
	public ModelAndView deleteLeaveType(HttpServletRequest request,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leavetype/register");	
	
		ltdi.deleteLeaveType(id);
		return mav;
	}
	
	@RequestMapping(value="/admin/leavetype/edit/{id}")
	public ModelAndView editLeaveType(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("leavestypeedit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpDetails user=null;
		if (principal instanceof EmpDetails) {
			user = ((EmpDetails)principal);
		}
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		//List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		//mav.addObject("empattendances",empattendances);
	//	mav.addObject("allempleave", allempleave);
		//mav.addObject("count",count);
		//mav.addObject("TransferRoleList", transferrole);
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
	//	List<TblManRoleTransfer> transferrole = roleTransfer.viewAll();
		//List<TblEmpLeavereq> allempleave = empleavereq.view();
		int count = empleavereq.countEmployee(user.getEid()) + empattreq.countEmployee(user.getEid());
		//List<TblEmpAttendanceNew> empattendances =  empattreq.getDetails();
		List<EmpDetails> emp1 = userDetails.getDetails();
		String lt=request.getParameter("leavetype");
	//	String[]sdt=dt.split("-");		
		//String name=request.getParameter("holiday");
		
		Tblleavestype tlt=new Tblleavestype(lt);
		mav.addObject("User",user);
		
		String msg=ltdi.updateLeaveType(id,tlt);
		if(msg.equalsIgnoreCase("LeaveType UpDated Successfully")) {
			mav = new ModelAndView("redirect:/admin/leavetype/register");
			
			return mav;
		}else if(msg.equalsIgnoreCase("LeaveType is Already Exists.Please try Again")) {
			mav = new ModelAndView("leavestypeedit");
			//mav.addObject("empattendances",empattendances);
			mav.addObject("User",user);
			//mav.addObject("allempleave", allempleave);
			mav.addObject("count",count);
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


}
	
