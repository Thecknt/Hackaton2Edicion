package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDb;
import hackaton.Viajes.model.ERole;
import hackaton.Viajes.model.RoleEntity;
import hackaton.Viajes.model.UserEntity;
import hackaton.Viajes.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        //metodo de testeo sin seguridad
        return "Hello world not secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        //metodo para testear la seguridad
        return "hello world from secured point";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDb createUserDb){

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

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
     userRepository.deleteById(Integer.parseInt(id));
     return "Se ha borrado el usuario con exito! ID:".concat(id);
    }

    @GetMapping("/")
    public String indexPage() {
        return "index"; // Retorna el nombre de la plantilla HTML (sin la extensión) que quieres mostrar
    }

    @PostMapping("/submit") // Ejemplo de manejo de una solicitud POST
    public String handleSubmit() {
        // Aquí puedes manejar la lógica de la solicitud POST
        // por ejemplo, procesar datos enviados desde un formulario
        return "redirect:/"; // Redirige a la página principal después de procesar el formulario
    }
}
