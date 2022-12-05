package by.masha.cha.model;

import org.springframework.stereotype.Component;

@Component
public class ReiffeisenProject implements IProject {

    private String name;

    @Override
    public String getName() {
        return "ReiffeisenProject";
    }

    @Override
    public boolean checkReady() {
        return false;
    }

}
