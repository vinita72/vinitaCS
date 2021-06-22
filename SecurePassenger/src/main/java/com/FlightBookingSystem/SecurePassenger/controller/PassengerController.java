package com.FlightBookingSystem.SecurePassenger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FlightBookingSystem.SecurePassenger.exception.ResourceNotFoundException;
import com.FlightBookingSystem.SecurePassenger.model.Users;
import com.FlightBookingSystem.SecurePassenger.repository.UsersRepository;
import com.FlightBookingSystem.SecurePassenger.service.MongoUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class PassengerController {

	private static final Logger Log =LoggerFactory.getLogger(PassengerController.class);
	
	@Autowired
	private MongoUserDetailsService userservice;
	
	@Autowired
	private UsersRepository repository;
	
	
	 @GetMapping("/")
		public String login(){
			return "authenticated successfully" ;
		}
	
	@GetMapping("/test")
	public String Hello() {
		return "Hello There";
	}
	
	
	@GetMapping("/findAllUsers")
	 public List<Users> findAllUser(){
		  return userservice.getUsers();		
		  } 
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@PostMapping("/users")
	public ResponseEntity<List<Users>> add(@Validated @RequestBody Users users){
		Log.info("insert User Start");
		List<Users> user = userservice.add(users);
		
		if(user.isEmpty()) {
			Log.warn("User Already Available");
			
			return new ResponseEntity("Sorry Users not available.", HttpStatus.NOT_FOUND);
			
			
		}
		
		Log.info("User added successfully ");
		 
		return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
		
	}
	
	// delete employee rest api
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String id){
			Users user = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			
			repository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
	
	
}
	
/*	
	@Autowired
	private UserRepo repository;

	@Autowired
	private PassengerService service;
	
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/")
	public String Hello() {
		return "Hello There";
	}
	
	
	@GetMapping("/welcome")
	public String wel() {
		return "welcome There";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration", "user", new User());
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/registerpassenger", method = RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("user") UserPrincipal users) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());

		UserPrincipal user = new UserPrincipal(users.getUsername(), encodedPassword, authorities);
		jdbcUserDetailsManager.createUser(user);
		return new ModelAndView("redirect:/welcome");
	}

	
	
	
	
	
	
	 * @PostMapping("/registerpassenger") String RegisterPassenger(@RequestBody User
	 * user) throws Exception { String tempUserName = user.getUsername();
	 * if(tempUserName != null && !"".equals(tempUserName)) { User userObj =
	 * service.getUserByUsername(tempUserName); if(userObj != null) { throw new
	 * Exception("Passenger with "+tempUserName+" is already exist"); } } User
	 * userObj = null; userObj = service.saveUser(user);
	 * 
	 * return "Hi " +
	 * user.getUsername()+" your Registration process successfully completed";
	 * 
	 * }
	 
	  
	  
		
		 * @PostMapping("/login") public String loginPassenger(@RequestBody User user)
		 * throws Exception{ String tempUserName = user.getUsername(); String tempPass =
		 * user.getPassword(); User passengerObj = null; if(tempUserName != null &&
		 * tempPass!= null) { passengerObj =
		 * service.fetchPassengerByUsernameAndPassword(tempUserName, tempPass); }
		 * if(passengerObj == null) { throw new Exception("Bad Credential"); } return
		 * "Welcome"; }
		 
	  
	  
	  @GetMapping("/login")
	  public String login(Model model, String error, String logout) {
			if (error != null)
				model.addAttribute("errorMsg", "Your username and password are invalid.");

			if (logout != null)
				model.addAttribute("msg", "You have been logged out successfully.");

			return "login";
		}
	  
	 
	 
		
		 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
		 * login(Model model, String error, String logout) { if (error != null)
		 * model.addAttribute("errorMsg", "Your username and password are invalid.");
		 * 
		 * if (logout != null) model.addAttribute("msg",
		 * "You have been logged out successfully.");
		 * 
		 * return "login"; }
		 
		
		
	
	
	 * @RequestMapping(value = "/registerpassenger", method = RequestMethod.GET)
	 * public ModelAndView register() { return new ModelAndView("registration",
	 * "user", new User()); }
	 
	  
	
	 * @PostMapping("/registerpassenger") public String
	 * processRegister(@ModelAttribute("user") User user) { List<GrantedAuthority>
	 * authorities = new ArrayList<GrantedAuthority>(); authorities.add(new
	 * SimpleGrantedAuthority("ROLE_USER"));
	 * 
	 * String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
	 * 
	 * User userObj = new User(user.getId(), user.getUsername(), encodedPassword,
	 * user.getEmailid()); userObj = service.saveUser(user); return "Hi " +
	 * user.getUsername()+" your Registration process successfully completed"; //
	 * return new // ModelAndView("redirect:/welcome");
	 * 
	 * }
	 
		
		
		
		
		 @GetMapping("/findAllUser")
		 public List<User> findAllUser(){
			  return service.getUser();		
			  }
		 
		 
		 @GetMapping("/addNewEmployee")
			public String add() {
				return "add There";
			}
			
		 
		
		
	}

	
	 * @GetMapping("/AllPassengers") public List<User> findAllPassenger(){ return
	 * service.getUser(); }
	 * 
	 * @DeleteMapping("/deleteBy/{id}") public String deletePassenger(@PathVariable
	 * int id) { repository.deleteById(id); return "passenger deleted with id: "
	 * +id; }
	 * 
	 * @GetMapping("/AllPassenger/{id}") public Optional<User>
	 * getPassenger(@PathVariable int id){ return service.getUserById(id); }
	 * 
	 * 
	 * 
	 * @PutMapping("/updatepassenger/{id}") public ResponseEntity<?>
	 * saveResource(@RequestBody User user,
	 * 
	 * @PathVariable("id") String id) { service.saveUser(user); return
	 * ResponseEntity.ok("resource saved"); }
	 * 
	 * 
	 * 
	 * // fallback method
	 * 
	 * 
	 * @RequestMapping("/PassengerFall") public String readingList(){ return
	 * "Please wait"; }
	 * 
	 * 
	 * 
	 * 
	 * @PostMapping("/registerpassenger") public String
	 * RegisterPassenger(@RequestBody User passenger) throws Exception { String
	 * tempEmail = passenger.getEmailId(); if(tempEmail != null &&
	 * !"".equals(tempEmail)) { User passengerObj =
	 * service.fetchUserByEmailId(tempEmail); if(passengerObj != null) { throw new
	 * Exception("Passenger with "+tempEmail+" is already exist"); } } User
	 * passengerObj = null; passengerObj = service.saveUser(passenger); return "Hi "
	 * +
	 * passenger.getFirstName()+" your Registration process successfully completed";
	 * }
	 * 
	 * 
	 * 
	 * 
	 * @PostMapping("/loginhere") public String loginPassenger(@RequestBody User
	 * passenger) throws Exception{ String tempEmail = passenger.getEmailId();
	 * String tempPass = passenger.getPassword(); User passengerObj = null;
	 * if(tempEmail != null && tempPass!= null) { passengerObj =
	 * service.fetchUserByEmailIdAndPassword(tempEmail, tempPass); } if(passengerObj
	 * == null) { throw new Exception("Bad Credential"); } return "Welcome"; }
	 


//		 @GetMapping("/user")
//		    public String user() {
//		        return ("<h1>Welcome User</h1>");
//		    }
//	
//		 @GetMapping("/")
//		    public String home() {
//		        return ("<h1>Welcome</h1>");
//		    }
//		@GetMapping("/findAllPassenger")
//		public List<Passenger> getPassenger(){
//			return repository.findAll();
//		}
//		
//		@GetMapping("/findAllPassenger/{id}")
//		public Optional<Passenger> getPassenger(@PathVariable int id){
//			return repository.findById(id);
//		}
//		
//		@DeleteMapping("/delete/{id}")
//		public String deletePassenger(@PathVariable int id) {
//			repository.deleteById(id);
//			return "passenger deleted with id: " +id;
//		}


 * public String Pass() { return "Hello Pass"; }
 * 
 */
