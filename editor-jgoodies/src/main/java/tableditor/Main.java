package tableditor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import tableditor.control.StudentBaseController;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.viewmodel.StudentSharedViewModel;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private StudentPersistenceFacade baseFacade;
    private StudentBaseController presenter;
    
    public Main(StudentPersistenceFacade baseFacade) {
        this.baseFacade = baseFacade;
        this.presenter = new StudentBaseController(new StudentSharedViewModel(baseFacade));        
    }

    @Override
    public void run(String... args) throws Exception {  
        presenter.display();
    }

    public static void main(String[] args) {
        // For Swing application we need to set headless to false.
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false);
		builder.run(args);
    }

}
