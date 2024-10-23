package com.serverpet.server.DTO;

public class UserListDTO {

    private Long id;
    private String username;
    private String email;
    private Integer phone;


    public UserListDTO(Long id, String username, String email, Integer phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getPhone() { return phone; }
    public void setPhone(Integer phone) { this.phone = phone; }

}
