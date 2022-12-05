package by.masha.cha.model;

import org.springframework.stereotype.Component;

@Component
public class Company {

    private ProjectManager projectManager;

    public Company(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public String checkProjectManager(){
        return "Readiness: " + projectManager.checkProject();
    }
}
