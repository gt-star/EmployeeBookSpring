package com.example.employeebookspring.Service;

import com.example.employeebookspring.exception.IncorrectLastNameException;
import com.example.employeebookspring.exception.IncorrectNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public String validateName(String name){
        if (StringUtils.isAlpha(name)){
            return StringUtils.capitalize(name.toLowerCase());
        }
        throw new IncorrectNameException();
    }
    public String validateLastName(String lastName) {
        String[] surnames = lastName.split("-");
        for (int i = 0; i < surnames.length; i++) {
            if (StringUtils.isAlpha(surnames[i])){
                return StringUtils.capitalize(surnames[i].toLowerCase());
            } else {
                throw new IncorrectLastNameException();
            }
        }
        return String.join("-", surnames);
    }
}
