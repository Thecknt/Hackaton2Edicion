package hackaton.Viajes.model;

public class JwtResponse {
    private String token;

    // Constructor, Getters y Setters


    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}