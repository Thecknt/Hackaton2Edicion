package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDTO;
import hackaton.Viajes.exception.ResourceNotFoundException;
import hackaton.Viajes.model.*;
import hackaton.Viajes.repository.UserRepository;
import hackaton.Viajes.service.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

//@CrossOrigin(value = "http://localhost:5173/")
@CrossOrigin(origins = "*") //Despues hay que eliminar esta linea y poner solo la del front, esto es solo para pruebas
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

    @Autowired
    private IEventService eventService;

    @Autowired
    private ICarRentalService carRentalService;

    @Autowired
    private IExcursionService excursionService;

    //Registro para un nuevo usuario desde el inicio, tambien puede ser usado desde el rol empleado
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Optional<UserEntity> userOptional = userRepository.findByUsername(createUserDTO.getUsername());
        if (userOptional.isPresent()){
            throw new ResourceNotFoundException("Ya hiciste un usuario con este nombre: " + createUserDTO.getUsername());
        }


        if (userRepository.existsByEmail(createUserDTO.getEmail())){

            throw new ResourceNotFoundException("El email ya se encuentra utilizado: "+createUserDTO.getEmail());
        }


        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();


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
        employee.setDateOfBirth(employeeReceived.getDateOfBirth());
        employee.setAddress(employeeReceived.getAddress());
        employee.setNationality(employeeReceived.getNationality());
        employee.setPosition(employeeReceived.getPosition());
        employee.setSalary(employeeReceived.getSalary());
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

    //agregar un cliente siendo ROL employee o bien ADMIN
    @PostMapping("/createClient")
    public Client createClient(@RequestBody Client client){
            logger.info("El cliente agregado es: "+ client);
            return this.iClientService.save(client);
    }


    //Agregar un cliente en la base de datos, siendo cliente
    @PostMapping("/addData/{id}")
    public Client addData(@RequestBody Client client, @PathVariable Integer id){
        Optional<UserEntity> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()){
            UserEntity user = userOptional.get();
            client.setUser(user);
            logger.info("El cliente agregado es: "+ client);
            return this.iClientService.save(client);
        }else{
         throw new UsernameNotFoundException("No se encontro al usuario");
        }
    }

    //Buscar Un cliente por ID en la base datos
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Integer id){
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
        client.setDateOfBirth(clientReceived.getDateOfBirth());
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
          hotel.setNameOfHotel(hotelReceived.getNameOfHotel());
          hotel.setDescription(hotelReceived.getDescription());
          hotel.setLocation(hotelReceived.getLocation());
          hotel.setStars(hotelReceived.getStars());
          hotel.setNumberOfNights(hotelReceived.getNumberOfNights());
          hotel.setPriceHotel(hotelReceived.getPriceHotel());
          this.hotelService.save(hotel);
          return ResponseEntity.ok(hotel);
    }


    //Eliminar un hotel de la base de datos
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHotel(@PathVariable Integer id){
        Hotel hotel = this.hotelService.findById(id);
        if (hotel == null)
            throw new ResourceNotFoundException("No se encontro el Hotel con id: "+ id);
        this.hotelService.deleteById(hotel.getIdHotel());
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

    //Consultar Todos los Eventos en la base de datos
    @PostMapping("/event")
    public List<Event> getAllEvents(){
        List<Event> events = this.eventService.events();
        logger.info("Estos son los eventos que hay guardados: ");
        events.forEach((event -> logger.info(event.toString())));
        return events;
    }

    //Crear un Evento en la base de datos
    @PostMapping("/createEvent")
    public Event addEvent(@RequestBody Event event){
        logger.info("El Evento agregado es: "+ event);
        return this.eventService.save(event);
    }

    //Buscar un Evento en la base de datos
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Integer id){
        Event event = this.eventService.findById(id);
        if (event != null)
            return ResponseEntity.ok(event);
        else
            throw new ResourceNotFoundException("Evento no encontrado con el id: "+ id);
    }

    //Editar un Evento en la base de datos
    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id,
                                             @RequestBody Event eventReceived){
        Event event = this.eventService.findById(id);
        if (event == null)
            throw new ResourceNotFoundException("Evento no econtrado con el id: "+id);
        event.setNameOfEvent(eventReceived.getNameOfEvent());
        event.setTypeEvent(eventReceived.getTypeEvent());
        event.setAmountOfTickets(eventReceived.getAmountOfTickets());
        event.setPriceTicket(eventReceived.getPriceTicket());
        this.eventService.save(event);
        return ResponseEntity.ok(event);
    }


    //Eliminar un Evento de la base de datos
    @DeleteMapping("/event/{id}")
   public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Integer id){
        Event event = this.eventService.findById(id);
        if (event == null)
            throw new ResourceNotFoundException("No se encontro el Evento con el id: "+ id);
        this.eventService.deleteById(event.getIdEvent());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
       logger.info("Evento eliminado: " + event);
       return  ResponseEntity.ok(response);
  }

   //Consultar Todos los autos de alquiler en la base de datos
    @PostMapping("/carRental")
    public List<CarRental> getAllCars(){
        List<CarRental> cars = this.carRentalService.listCar();
       logger.info("Estos son los autos que hay guardados: ");
        cars.forEach((car -> logger.info(car.toString())));
        return cars;
   }

    //Crear un auto para rentar en la base de datos
   @PostMapping("/createCarRental")
    public CarRental addCar(@RequestBody CarRental carRental){
        logger.info("El auto agregado es: "+ carRental);
       return this.carRentalService.save(carRental);
    }

   //Buscar un auto en la base de datos
    @GetMapping("/carRental/{id}")
    public ResponseEntity<CarRental> findCarById(@PathVariable Integer id){
        CarRental carRental = this.carRentalService.findById(id);
        if (carRental != null)
            return ResponseEntity.ok(carRental);
        else
            throw new ResourceNotFoundException("Auto de alquiler no encontrado con el id: "+ id);
    }

    //Editar un auto de alquiler en la base de datos
    @PutMapping("/carRental/{id}")
   public ResponseEntity<CarRental> updateCarRental(@PathVariable Integer id,
                                             @RequestBody CarRental carReceived){
        CarRental carRental = this.carRentalService.findById(id);
       if (carRental == null)
            throw new ResourceNotFoundException("El auto de alquiler no fue econtrado con el id: "+id);
        carRental.setTypeOfCar(carReceived.getTypeOfCar());
        carRental.setRent(carReceived.getRent()); //v o f si renta o no el cliente
        carRental.setAmountOfDays(carReceived.getAmountOfDays());
        carRental.setPriceByDay(carReceived.getPriceByDay());
        this.carRentalService.save(carRental);
        return ResponseEntity.ok(carRental);
     }

    //Eliminar un Evento de la base de datos
    @DeleteMapping("/carRental/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCarRental(@PathVariable Integer idCarRental){
        CarRental carRental = this.carRentalService.findById(idCarRental);
        if (carRental == null)
            throw new ResourceNotFoundException("No se encontro el Auto de alquiler con el id: "+ idCarRental);
        this.carRentalService.deleteById(carRental.getIdCarRental());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        logger.info("Auto de alquiler eliminado: " + carRental);
        return  ResponseEntity.ok(response);
    }

    //Consultar Todas las Excursiones en la base de datos
    @PostMapping("/excursion")
    public List<Excursion> getAllExcursions(){
        List<Excursion> excursions = this.excursionService.excursions();
        logger.info("Estos son las excursiones que hay guardados: ");
        excursions.forEach((excursion -> logger.info(excursion.toString())));
        return excursions;
    }

    //Crear una Excursion en la base de datos

    @PostMapping("/createExcursion")

    public Excursion addExcursion(@RequestBody Excursion excursion) {
        logger.info("La Eexcursion agregado es: " + excursion);
        return this.excursionService.save(excursion);
    }


    //Buscar un excursion en la base de datos
    @GetMapping("/excursion/{id}")
    public ResponseEntity<Excursion> findExcursionById(@PathVariable Integer id){
        Excursion excursion = this.excursionService.findById(id);
        if (excursion != null)
            return ResponseEntity.ok(excursion);
        else
            throw new ResourceNotFoundException("Excursion no encontrado con el id: "+ id);
    }

    //Editar una Excursion en la base de datos
    @PutMapping("/excursion/{id}")
    public ResponseEntity<Excursion> updateExcursion(@PathVariable Integer id,
                                                     @RequestBody Excursion excursionReceived){
        Excursion excursion = this.excursionService.findById(id);
        if (excursion == null)
            throw new ResourceNotFoundException("La excursion no fue econtrada con el id: "+id);
        excursion.setTypeCircuit(excursionReceived.getTypeCircuit());
        excursion.setNameOfCircuit(excursionReceived.getNameOfCircuit());
        excursion.setPriceOfCircuit(excursionReceived.getPriceOfCircuit());
        excursion.setAmountOfTickets(excursionReceived.getAmountOfTickets());
        this.excursionService.save(excursion);
        return ResponseEntity.ok(excursion);
    }

    //Eliminar un Evento de la base de datos
    @DeleteMapping("/excursion/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteExcursion(@PathVariable Integer idTouristicService){
        Excursion excursion = this.excursionService.findById(idTouristicService);
        if (excursion == null)
            throw new ResourceNotFoundException("No se encontro la excursion con el id: "+ idTouristicService);
        this.excursionService.deleteById(excursion.getIdExcursion());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        logger.info("Excursion eliminada: " + excursion);
        return  ResponseEntity.ok(response);
    }
}
