package by.masha.cha.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectManager {

    private IProject project;

    @Autowired
    public ProjectManager(IProject project){this.project = project;}

    public String checkProject() {
        return "Now " + project.checkReady();
    }
}
