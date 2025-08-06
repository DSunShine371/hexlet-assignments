package exercise;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import exercise.component.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties adminsInfo;

    @GetMapping("/admins")
    public List<String> indexAdmins() {
        HashSet<String> adminEmails = new HashSet<>(adminsInfo.getAdmins());

        return users.stream()
                .filter(user -> adminEmails.contains(user.getEmail()))
                .map(User::getName)
                .sorted()
                .toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
