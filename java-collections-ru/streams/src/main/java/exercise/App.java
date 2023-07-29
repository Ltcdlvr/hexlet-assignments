package exercise;

import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .filter(email -> !email.isBlank())
                .filter(email -> email.indexOf('@') > 0)
                .map(email -> email.substring(email.indexOf('@') + 1))
                .filter(email -> !email.isBlank())
                .filter(email -> freeDomains.contains(email))
                .count();
    }
}
// END
