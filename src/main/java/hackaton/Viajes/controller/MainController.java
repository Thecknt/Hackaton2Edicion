package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDTO;
import hackaton.Viajes.exception.ResourceNotFoundException;
import hackaton.Viajes.model.*;
import hackaton.Viajes.repository.UserRepository;
import hackaton.Viajes.service.IClientService;
import hackaton.Viajes.service.IEmployeeService;
import hackaton.Viajes.service.IHotelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@CrossOrigin(value = "http://localhost:5173")
//@CrossOrigin(origins = "*") //Despues hay que eliminar esta linea y poner solo la del front, esto es solo para pruebas
@RequestMapping("/")
@RestController
public class MainController {

    private static final Logger logger =
            LoggerFactory.getLogger(MainController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IEmployeeService IEmployeeService;

    @Autowired
    private IClientService iClientService;

    //@PostMapping("/login")
    //public ResponseEntity<?> login(@RequestBody User user){
        // Aquí deberías buscar al usuario en tu base de datos usando el nombre de usuario proporcionado
        // y luego comparar la contraseña proporcionada con la almacenada en la base de datos.
        // Si las contraseñas coinciden, entonces el inicio de sesión es exitoso.
        // Si no coinciden o si el usuario no se encuentra en la base de datos, entonces el inicio de sesión falla.

        // Este es solo un ejemplo y necesitarás adaptarlo para que funcione con tu base de datos y tu sistema de autenticación.
        //User userInDb = userRepository.findByUsername(user.getUsername());
        //if(userInDb != null && userInDb.getPassword().equals(user.getPassword())){
            //return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        //} else {
            //return new ResponseEntity<>("Error en el inicio de sesión", HttpStatus.UNAUTHORIZED);
        //}
   // }


    //Registro para un nuevo usuario desde el inicio, tambien puede ser usado desde el rol empleado
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        System.out.println("ingreso al metodo");
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        System.out.println("Usuario creado: "+ userEntity.toString());
        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    //Consultar todos los empleados de la base de datos
    @GetMapping("/employee")
    public List<Employee> getEmployees(){
        List<Employee> employees = this.IEmployeeService.employees();
        logger.info("Los empleados en la base de datos son: ");
        employees.forEach((employee -> logger.info(employee.toString())));
        return employees;
    }

    //Agregar un empleado a la base de datos
    @PostMapping("/createEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        logger.info("Empleado guardado en la base de datos: "+ employee);
        return this.IEmployeeService.save(employee);
    }

    //Buscar empleado por Id en la base de datos
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Integer id){
      Employee employee = this.IEmployeeService.findById(id);
      if (employee != null)
             return ResponseEntity.ok(employee);
      else
          throw new ResourceNotFoundException("Empleado no encontrado con el id: "+ id);
    }

    //Editar un empleado en la base de datos
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,
                                                   @RequestBody Employee employeeReceived){
       Employee employee = this.IEmployeeService.findById(id);
        if (employee == null)
            throw new ResourceNotFoundException("cliente no encontrado: "+ id);
        employee.setName(employeeReceived.getName());
        employee.setLastname(employeeReceived.getLastname());
        employee.setDni(employeeReceived.getDni());
        employee.setCeluphone(employeeReceived.getCeluphone());
        employee.setDateOfBird(employeeReceived.getDateOfBird());
        employee.setAddress(employeeReceived.getAddress());
        employee.setNationality(employeeReceived.getNationality());
        employee.setUser(employeeReceived.getUser());
        this.IEmployeeService.save(employee);
        return ResponseEntity.ok(employee);
    }

    //Eliminar un empleado de la base de datos
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id){
        Employee employee = this.IEmployeeService.findById(id);
        if (employee == null)
            throw new ResourceNotFoundException("No se encontro el empleado con el id: "+id);
        this.IEmployeeService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        logger.info("Empleado eliminado con el id: "+ id);
        return ResponseEntity.ok(response);
    }


    //Consultar todos los clientes de la base de datos.
    @GetMapping("/clients")
    public List<Client> getClients(){
        List<Client> clients = this.iClientService.clients();
        logger.info("Estos son los Hoteles en base de datos: ");
        clients.forEach((client -> logger.info(client.toString())));
        return clients;
    }

    //Agregar un cliente en la base de datos
    @PostMapping("/createClients")
    public Client addClient(@RequestBody Client client){
        logger.info("El cliente agregado es: "+ client);
        return this.iClientService.save(client);
    }

    //Buscar Un cliente por ID en la base datos
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findclientById(@PathVariable Integer id){
        Client client = this.iClientService.findById(id);
        if (client != null)
            return ResponseEntity.ok(client);
        else
         throw new ResourceNotFoundException("Este cliente: "+ id + " no ha sido encontrado");
    }

    //Editar un Cliente en la base de datos
    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id
            ,@RequestBody Client clientReceived){
        Client client = this.iClientService.findById(id);
        if (client == null)
            throw new ResourceNotFoundException("cliente no encontrado: "+ id);
        client.setName(clientReceived.getName());
        client.setLastname(clientReceived.getLastname());
        client.setDni(clientReceived.getDni());
        client.setCeluphone(clientReceived.getCeluphone());
        client.setDateOfBird(clientReceived.getDateOfBird());
        client.setAddress(clientReceived.getAddress());
        client.setNationality(clientReceived.getNationality());
        client.setUser(clientReceived.getUser());
        this.iClientService.save(client);
        return ResponseEntity.ok(client);
    }

    //Eliminar un cliente de la base de datos
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Integer id){
        Client client = this.iClientService.findById(id);
        if (client == null)
            throw new ResourceNotFoundException("Cliente no encontrado con el id: "+ id);
        this.iClientService.deleteById(client.getIdClient());
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        logger.info("Cliente eliminado con el id: "+ id);
        return ResponseEntity.ok(response);
    }

    //Consultar Todos los hoteles en la base de datos
    @GetMapping("/hotels")
    public List<Hotel> getHotels(){
        List<Hotel> hotels = this.hotelService.hotels();
        logger.info("Estos son los Hoteles en la base de datos: ");
        hotels.forEach((hotel -> logger.info(hotel.toString())));
        return hotels;
    }

    //Crear un Hotel en la base de datos
    @PostMapping("/createHotels")
    public Hotel addHotel(@RequestBody Hotel hotel){
    logger.info("Hotel a agregar: " + hotel);
        return  this.hotelService.save(hotel);
    }

    //Buscar un Hotel en la base de datos
    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable Integer id){
        Hotel hotel = this.hotelService.findById(id);
        if(hotel != null)
            return ResponseEntity.ok(hotel);
        else
         throw new ResourceNotFoundException("Hotel no encontrado, id:" + id);
    }

    //Editar un Hotel de la base de datos
    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Integer id,
                                             @RequestBody Hotel hotelReceived){
          Hotel hotel = this.hotelService.findById(id);
          if (hotel == null)
              throw new ResourceNotFoundException("No se encontro el hotel con id: "+ id);
          hotel.setName(hotelReceived.getName());
          hotel.setBriefDescription(hotelReceived.getBriefDescription());
          hotel.setPriceCost(hotelReceived.getPriceCost());
          hotel.setServiceDate(hotelReceived.getServiceDate());
          hotel.setStars(hotelReceived.getStars());
          hotel.setNumberOfNights(hotelReceived.getNumberOfNights());
          hotel.setServiceDestination(hotelReceived.getServiceDestination());
          this.hotelService.save(hotel);
          return ResponseEntity.ok(hotel);
    }


    //Eliminar un hotel de la base de datos
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHotel(@PathVariable Integer id){
        Hotel hotel = this.hotelService.findById(id);
        if (hotel == null)
            throw new ResourceNotFoundException("No se encontro el Hotel con id: "+ id);
        this.hotelService.deleteById(hotel.getIdTouristicService());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        logger.info("Hotel eliminado: " + hotel);
        return  ResponseEntity.ok(response);
    }

    //Eliminar usuario de la base de datos
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Integer.parseInt(id));
        return "Se ha borrado el user con id".concat(id);
    }
}
