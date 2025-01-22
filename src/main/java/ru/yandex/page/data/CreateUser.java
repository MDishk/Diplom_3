package ru.yandex.page.data;

import net.datafaker.Faker;

public class CreateUser {
    public static UserData createRandomUser() {
        Faker faker = new Faker();
        return new UserData(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().firstName()
        );
    }
}
