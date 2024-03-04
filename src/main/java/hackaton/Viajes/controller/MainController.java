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
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @PostMapping("/login")
    public String login(){
        return "login";
    }

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

        System.out.println("Usuario creado: "+userEntity.toString());
        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    //Consultar Todos los hoteles en db
    @GetMapping("/hotels")
    public List<Hotel> getHotels(){
        List<Hotel> hotels = this.hotelService.hotels();
        logger.info("Estos son los Hoteles en la base de datos: ");
        hotels.forEach((hotel -> logger.info(hotel.toString())));
        return hotels;
    }

    //Agregar un Hotel, tambien sirve este metodo para actualizar
    @PostMapping("/hotels")
    public Hotel addHotel(@RequestBody Hotel hotel){
    logger.info("Hotel a agregar: " + hotel);
        return  this.hotelService.save(hotel);
    }

    //Buscar un Hotel
    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable int idTuristicService){
        Hotel hotel = this.hotelService.findById(idTuristicService);
        if(hotel != null)
            return ResponseEntity.ok(hotel);
        else
         throw new ResourceNotFoundException("Hotel no encontrado, id:" + idTuristicService);
    }

    //Editar un Hotel
    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Integer idTuristicService,
                                             @RequestBody Hotel hotelReceived){
          Hotel hotel = this.hotelService.findById(idTuristicService);
          if (hotel == null)
              throw new ResourceNotFoundException("No se encontro el hotel con id: "+ idTuristicService);
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


    //Eliminar un hotel
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHotel(@PathVariable Integer idTuristicService){
        Hotel hotel = this.hotelService.findById(idTuristicService);
        if (hotel == null)
            throw new ResourceNotFoundException("No se encontro el Hotel con id: "+ idTuristicService);
        this.hotelService.deleteById(hotel.getIdTouristicService());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        logger.info("Hotel eliminado: " + hotel);
        return  ResponseEntity.ok(response);
    }



    //Guardar un nuevo empleado
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.IEmployeeService.save(employee);
    }

    //Eliminar usuario
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Integer.parseInt(id));
        return "Se ha borrado el user con id".concat(id);
    }
}
