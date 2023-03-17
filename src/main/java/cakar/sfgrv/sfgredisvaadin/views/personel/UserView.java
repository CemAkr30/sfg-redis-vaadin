package cakar.sfgrv.sfgredisvaadin.views.personel;



import cakar.sfgrv.sfgredisvaadin.dto.UserDTO;
import cakar.sfgrv.sfgredisvaadin.service.UserService;
import cakar.sfgrv.sfgredisvaadin.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Set;


@PageTitle("User")
@Route(value = "user", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class UserView extends VerticalLayout {

    private final UserService userService;
    TextField filterTextName = new TextField();

    TextField filterTextSurname = new TextField();

    TextField filterTextIdentityNumber = new TextField();



    TextField saveTextName = new TextField();

    TextField saveTextSurname = new TextField();

    TextField saveTextIdentityNumber = new TextField();


    public UserView(UserService userService) {
        this.userService = userService;
        Grid<UserDTO> grid = new Grid<>(UserDTO.class);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        filterTextName.setWidth("300px");
        filterTextSurname.setWidth("300px");
        filterTextIdentityNumber.setWidth("300px");
        horizontalLayout.add(filterTextName,filterTextSurname,filterTextIdentityNumber);
      // horizontalLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
       // horizontalLayout.setWidth("50%");
        setSpacing(false);
        grid.setColumns("id","name", "surname","identityNumber");
        grid.getColumnByKey("id").setVisible(false);
        grid.getColumnByKey("name").setHeader("Name");
        grid.getColumnByKey("surname").setHeader("Surname");
        grid.getColumnByKey("identityNumber").setHeader("Turkish Identity Number");
        grid.setItems(userService.getAllUser());
        grid.setHeight("450px");
        grid.setSelectionMode(Grid.SelectionMode.MULTI); // Multi select yaparak kayıtları delete edebiliriz
        filterTextName.setPlaceholder("Filter by name...");
        filterTextName.addValueChangeListener(e -> grid.setItems(userService.findByNameStartsWithIgnoreCase(e.getValue())));
        filterTextSurname.setPlaceholder("Filter by surname...");
        filterTextSurname.addValueChangeListener(e -> grid.setItems(userService.findBySurnameStartsWithIgnoreCase(e.getValue())));
        filterTextIdentityNumber.setPlaceholder("Filter by Turkish identityNumber...");
        filterTextIdentityNumber.addValueChangeListener(e -> grid.setItems(userService.findByIdentityNumberStartsWithIgnoreCase(e.getValue())));


        TextField firstNameField = new TextField("First name");
        TextField lastNameField = new TextField("Last name");
        TextField turkishIdentityNumberField = new TextField("Turkish Identity Number");
        FormLayout formLayout = new FormLayout(firstNameField, lastNameField,
                turkishIdentityNumberField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        formLayout.setColspan(turkishIdentityNumberField, 2);

        Button delete = new Button("Delete");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.getStyle().set("margin-inline-end", "auto");

        Button clear = new Button("Clear");
        Button refresh = new Button("Refresh");

        Button createAccount = new Button("Create user");
        createAccount.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttonLayout = new HorizontalLayout(delete,
                createAccount,clear,refresh);
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        createAccount.addClickListener(e -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(firstNameField.getValue());
            userDTO.setSurname(lastNameField.getValue());
            userDTO.setIdentityNumber(turkishIdentityNumberField.getValue());
            boolean controlSave = false;
            try {
              UserDTO dtoSave =   userService.saveUser(userDTO);
                controlSave =  dtoSave.isIslemTuru();
                if(controlSave){
                    Notification notification = Notification
                            .show("Kaydetme İşlemi Gerçekleştirilmiştir");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            }catch (Exception ex) {
                Notification.show(ex.getMessage());
            }
            grid.setItems(userService.getAllUser());
        });

        delete.addClickListener(e -> {
           Set<UserDTO> userDTOSet =  grid.getSelectedItems();
           boolean valueDeleteUser =  userService.deleteUsers(userDTOSet);
            grid.setItems(userService.getAllUser());
            if(valueDeleteUser) {
                Notification notification = Notification
                        .show("Silme İşlemi Gerçekleştirilmiştir");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        clear.addClickListener(e -> {
            firstNameField.clear();
            lastNameField.clear();
            turkishIdentityNumberField.clear();
        });

        refresh.addClickListener(e ->{
           boolean  randomUserCreate =  userService.randomUserCreate();
            grid.setItems(userService.getAllUser());

            if(randomUserCreate) {
                Notification notification = Notification
                        .show("Kaydetme İşlemi Gerçekleştirilmiştir");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        add(horizontalLayout,grid,formLayout, buttonLayout);
    }
}
