package AppRev1.tester;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by aalbutov on 27.10.2017.
 */
public class Tester {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password2"));
    }
}
