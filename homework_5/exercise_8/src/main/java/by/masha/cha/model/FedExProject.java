package by.masha.cha.model;

import org.springframework.stereotype.Component;

@Component
public class FedExProject implements IProject {

    @Override
    public String checkReady() {
        return "FedExProject is ready";
    }
}