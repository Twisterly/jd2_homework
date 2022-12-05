package by.masha.cha.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ProjectManager {
    private IProject project1;
    private IProject project2;

    @Autowired
    public ProjectManager(@Qualifier("fedExProject") IProject project1,
                          @Qualifier("reiffeisenProject") IProject project2) {
        this.project1 = project1;
        this.project2 = project2;
    }


    public String checkProject() {
        return "today "
                + project1.getName() + " is ready: " + project1.checkReady() + "; "
                + project2.getName() + " is ready: " + project2.checkReady();
    }
}