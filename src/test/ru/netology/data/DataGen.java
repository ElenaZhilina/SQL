package ru.netology.data;

import lombok.Value;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataGen {
    private DataGen() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    public static User getUser() {
        return new User("vasya", "qwerty123");
    }

    public static String randomLogin() {
        return faker.name().username();
    }

    public static String randomPwd() {
        return faker.internet().password();
    }

    public static User getFakeUser() {
        return new User(randomLogin(), randomPwd());
    }

    public static Code randomCode() {
        return new Code(faker.numerify("######"));
    }

    @Value
    public static class User {
        String login;
        String pwd;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Code {
        String code;
    }

}