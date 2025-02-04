package com.workintech.s17d2.model;

import com.workintech.s17d2.Experience;
import org.springframework.stereotype.Component;

@Component
public class JuniorDeveloper extends Developer{

    public JuniorDeveloper(int id, String name, double salary) {
        super(id, name, salary - (salary * 0.15), Experience.JUNIOR);
    }


}
