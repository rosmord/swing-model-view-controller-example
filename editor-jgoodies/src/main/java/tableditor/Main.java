package tableditor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import tableditor.control.StudentBasePresenter;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.viewmodel.StudentViewModel;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private StudentPersistenceFacade baseFacade;
    private StudentBasePresenter presenter;
    
    public Main(StudentPersistenceFacade baseFacade) {
        this.baseFacade = baseFacade;
        this.presenter = new StudentBasePresenter(new StudentViewModel(baseFacade));        
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
