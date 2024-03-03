package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDTO;
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

import java.util.List;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@CrossOrigin(value = "http://localhost:5173/")
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")

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

    @GetMapping("/hello")
    public String hello(){
        return "Hello World Not Secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World Secured";
    }

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

    //Agregar un Hotel
    @PostMapping("/createHotel")
    public Hotel addHotel(@RequestBody Hotel hotel){
    logger.info("Hotel a agregar: " + hotel);
        return  this.hotelService.save(hotel);
    }


    //Guardar un nuevo empleado
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.IEmployeeService.save(employee);
    }
    @PostMapping("/create_client")
    private String createClient(@RequestBody Client client, Model model){
        iClientService.save(client);
        model.addAttribute("client", client);
        return "redirect:/create_client";
    }

    @PostMapping("/submit") // Ejemplo de manejo de una solicitud POST
    public String handleSubmit() {
        // Aquí puedes manejar la lógica de la solicitud POST
        // por ejemplo, procesar datos enviados desde un formulario
        return "redirect:/"; // Redirige a la página principal después de procesar el formulario
    }
}
