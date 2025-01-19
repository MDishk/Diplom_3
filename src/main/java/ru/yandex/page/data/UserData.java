package ru.yandex.page.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {

    private String email;
    private String password;
    private String name;

    public UserData() {
        this.email = "pupipu@mail.ru";
        this.password = "12pup56";
        this.name = "Satoru";
    }
}
