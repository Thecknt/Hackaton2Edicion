package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDb;
import hackaton.Viajes.model.*;
import hackaton.Viajes.repository.UserRepository;
import hackaton.Viajes.service.IClientService;
import hackaton.Viajes.service.IEmployeeService;
import hackaton.Viajes.service.IHotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class MainController {

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
    @GetMapping("/create_User")
    public String createUser() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDb createUserDb){

        if(userRepository.existsByUsername(createUserDb.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: the name of the User its already in use!");
        }

        if(userRepository.existsByEmail(createUserDb.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: the Email its already in use!");
        }

        Set<RoleEntity> roles = createUserDb.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDb.getUsername())
                .password(passwordEncoder.encode(createUserDb.getPassword()))
                .email(createUserDb.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);
     return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/createHotel")
    public String createHotel(@RequestBody Hotel hotel, Model model) {
        hotelService.save(hotel);
        model.addAttribute("hotel", hotel);
        return "hotelView";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@RequestBody Employee employee, Model model){
        IEmployeeService.save(employee);
        model.addAttribute("employee", employee);
        return "redirect:/";
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
