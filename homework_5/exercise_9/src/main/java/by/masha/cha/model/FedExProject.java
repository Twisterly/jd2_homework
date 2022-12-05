package by.masha.cha.model;

import org.springframework.stereotype.Component;

@Component
public class FedExProject implements IProject {

    private String name;

    @Override
    public String getName() {
        return "FedExProject";
    }

    @Override
    public boolean checkReady() {
        return true;
    }

}