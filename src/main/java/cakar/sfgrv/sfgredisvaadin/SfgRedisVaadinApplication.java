package cakar.sfgrv.sfgredisvaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "myapp")
//@WebServlet(name = "MyUIServlet", urlPatterns = "/*", asyncSupported = true)
public class SfgRedisVaadinApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(SfgRedisVaadinApplication.class, args);
    }

}
