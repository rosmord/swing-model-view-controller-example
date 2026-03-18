package tableditor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.viewmodel.StudentSharedViewModel;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private StudentUIBuilder uiBuilder;
    
    public Main(StudentPersistenceFacade baseFacade) {
        this.uiBuilder = new StudentUIBuilder(new StudentSharedViewModel(baseFacade));        
    }

    @Override
    public void run(String... args) throws Exception {  
        uiBuilder.display();
    }

    public static void main(String[] args) {
        // For Swing application we need to set headless to false.
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false);
		builder.run(args);
    }

}
