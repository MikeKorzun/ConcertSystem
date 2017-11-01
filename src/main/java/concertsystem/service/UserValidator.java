package concertsystem.service;

import concertsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
 * Сервис для валидации данных формы при регистрации пользователя
 */

@Component
public class UserValidator implements Validator{

    @Autowired
    private UserService userService;

    // регистрирует какие классы должны поддерживать данную валидацию
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    // описываем правила валидации
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        //проверка на пустой username
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validationErrorUsernameEmpty");
        // проверка на существующий username
        if (userService.findByUsername(user.getUsername())!=null) {
            errors.rejectValue("username", "validationErrorUsernameExist");
        }

        //проверка на пустой password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validationErrorPasswordEmpty");

        //проверка на совпадение паролей при регистрации (confirmPassword == password)
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "validationErrorConfirmedPassword");
        }
    }
}
