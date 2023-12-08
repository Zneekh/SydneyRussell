package Source_for_this_main;


import Source_for_this_main.auto_update.checking_for_update;
import Source_for_this_main.auto_update.update_available_notification;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.*;


import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javafx.scene.layout.*;


import javafx.util.Duration;

import org.tbee.javafx.scene.layout.MigPane;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.filechooser.FileSystemView;

public class mailbox {
    HBox send_buttonbox;
    private TextField attachment_field;//holds the path to attachment file
    public Stage window;
    private TextField sender_email;
    private TextField receiver_email;
    private TextField users_name;
    private TextArea main_message;
    private TextField bcc_email;
    private String cssfile = "/stylesheets/mailbox.css";
    private TextField cc_email;// who it will be cc'd to
    private Button send_button;
    // private String school_email =" office@sydneyrussellschool.com";
    //private String school_email ="muhammad.khan15@yahoo.com";
    private String school_email = "216171@sydneyrussellschool.com";

    private TextField subject;
    MigPane main_layout;
    final Map<String, String> file_name_and_path = new HashMap<>();


    private void screen_size(Stage window) {

        this.window = window;
        window.setTitle("The Sydney Russell School -Mail");
        Rectangle2D screen_sizes = Screen.getPrimary().getVisualBounds();
        //width
        window.minWidthProperty().set(screen_sizes.getWidth() / 2);
        window.maxWidthProperty().set(screen_sizes.getWidth() / 1.5);
        window.setWidth(window.getWidth() / 2);
        //height
        window.setHeight(screen_sizes.getHeight());
        window.minHeightProperty().set(screen_sizes.getHeight() / 2);
        window.maxHeightProperty().set(screen_sizes.getHeight());
        System.out.println(screen_sizes.getHeight());

    }// create image

    public MigPane general_page() {
        main_layout = new MigPane();
        main_layout.maxWidthProperty().set(window.getWidth()-50);
        main_layout.setPadding(new Insets(2,5,2,5));
        main_layout.setId("Mail");
       // new Common_features_for_all().title(main_layout);;
        //image_titles("/Images_for_page_title/yellow.jpeg",main_layout);
        new menubar().get_menubar(main_layout);
        VBox main_box = new VBox();
        main_box.setPadding(new Insets(2, 5,2,5));
        main_box.maxWidthProperty().bind(window.widthProperty().subtract(20));
        main_box.prefHeightProperty().bind(window.heightProperty());
        //
        main_box.setSpacing(5);
        main_box.setId("main_box");
        // main_box.getChildren().addAll(general_layout_for_box("Tesla",null,"here"));
        //
        StackPane stacked_user_name = new StackPane();
        stacked_user_name.prefWidthProperty().bind(main_box.widthProperty());

        HBox users_name_box = new HBox();
        Label user_label = new Label("Name: ");
        user_label.setMinWidth(50);
        users_name = new TextField();
        users_name.getStyleClass().add("for_email_textfield");
        users_name.setPromptText("Full Name*");
        users_name.setEditable(true);
        users_name.prefHeightProperty().bind(user_label.heightProperty());
        users_name.prefWidthProperty().bind(window.widthProperty().subtract(20));
        users_name.minWidthProperty().bind(user_label.maxWidthProperty());
        users_name_box.getChildren().addAll(user_label, users_name);
        stacked_user_name.getChildren().add(users_name_box);

        //To
        HBox receiver_box = new HBox();
        Label receiver_label = new Label("To: ");
        receiver_label.setMinWidth(50);
        receiver_email = new TextField(school_email);
        receiver_email.getStyleClass().add("for_email_textfield");
        receiver_email.setEditable(false);
        receiver_email.prefHeightProperty().bind(receiver_label.heightProperty());
        receiver_email.prefWidthProperty().bind(window.maxWidthProperty().subtract(20));
        receiver_email.minWidthProperty().bind(receiver_label.maxWidthProperty());
        receiver_box.getChildren().addAll(receiver_label, receiver_email);
        //CC
        StackPane stacked_cc = new StackPane();
        stacked_cc.maxWidthProperty().bind(main_box.widthProperty());
        HBox cc_box = new HBox();
        cc_box.maxWidthProperty().bind(stacked_cc.maxWidthProperty());
        cc_box.setId("sender_box");
        //cc label
        Label cc_label = new Label("CC: ");
        cc_label.setMinWidth(50);
        //
        cc_email = new TextField();
        cc_email.getStyleClass().add("for_email_textfield");
        cc_email.prefHeightProperty().bind(receiver_label.heightProperty());
        cc_email.prefWidthProperty().bind(cc_box.maxWidthProperty().subtract(20));
        cc_email.minWidthProperty().bind(cc_label.maxWidthProperty());
        cc_box.getChildren().addAll(cc_label,cc_email);
        stacked_cc.getChildren().add(cc_box);
        // BCC
        StackPane stacked_bcc = new StackPane();
        stacked_bcc.maxWidthProperty().bind(main_box.widthProperty());
        HBox bcc_box = new HBox();
        bcc_box.maxWidthProperty().bind(stacked_bcc.maxWidthProperty());
        bcc_box.setId("sender_box");
        //cc label
        Label bcc_label = new Label("BCC: ");
        bcc_label.setMinWidth(50);
        bcc_email = new TextField();
        bcc_email.getStyleClass().add("for_email_textfield");
        bcc_email.prefHeightProperty().bind(receiver_label.heightProperty());
        bcc_email.prefWidthProperty().bind(bcc_box.maxWidthProperty().subtract(20));
        bcc_email.minWidthProperty().bind(bcc_label.maxWidthProperty());
        bcc_box.getChildren().addAll(bcc_label,bcc_email);
        stacked_bcc.getChildren().add(bcc_box);
        //sender's box
        StackPane stacked_sender = new StackPane();
        stacked_sender.maxWidthProperty().bind(main_box.widthProperty());
        HBox sender_box = new HBox();
        sender_box.maxWidthProperty().bind(stacked_sender.maxWidthProperty());
        sender_box.setId("sender_box");
        Label sender_label = new Label("From: ");
        sender_label.setMinWidth(50);
        sender_email = new TextField();
        sender_email.getStyleClass().add("for_email_textfield");
        sender_email.setPromptText("Email* ");
        sender_email.prefHeightProperty().bind(receiver_label.heightProperty());
        sender_email.prefWidthProperty().bind(sender_box.maxWidthProperty().subtract(20));
        sender_email.minWidthProperty().bind(sender_label.maxWidthProperty());
        sender_box.getChildren().addAll(sender_label, sender_email);
        stacked_sender.getChildren().add(sender_box);
        //subject
        StackPane stacked_subject = new StackPane();
        stacked_subject.maxWidthProperty().bind(window.maxWidthProperty());
        HBox subject_box = new HBox();
        subject_box.maxWidthProperty().bind(stacked_subject.maxWidthProperty().subtract(10));
        subject_box.setId("subject_box");
        Label subject_label = new Label("Subject: ");
        subject_label.setMinWidth(50);
        subject = new TextField(); //subject_textfield
        subject.getStyleClass().add("for_email_textfield");
        subject.prefHeightProperty().bind(receiver_label.heightProperty());
        subject.prefWidthProperty().bind(subject_box.maxWidthProperty().subtract(20));
        subject.minWidthProperty().bind(subject_label.maxWidthProperty());
        subject.setPromptText("(Optional):");
        subject_box.getChildren().addAll(subject_label, subject);
        stacked_subject.getChildren().add(subject_box);
        //path_to_attachment file textfield
        StackPane stacked_attachment = new StackPane();
        stacked_attachment.maxWidthProperty().bind(window.widthProperty().subtract(50));
        HBox attachment_box = new HBox();
        attachment_box.maxWidthProperty().bind(stacked_attachment.maxWidthProperty());
        attachment_box.setSpacing(10);
        Label path_label = new Label("Path: ");
        path_label.setMinWidth(50);
        attachment_field = new TextField();
        attachment_field.getStyleClass().add("for_email_textfield");
        attachment_field.setEditable(false);//so that the attachment file field cannot be edited
        attachment_field.setId("Attachment_field");//this id will be used to identify for the file not found label
        attachment_field.setPromptText("Selected Files:  ");
        attachment_field.prefHeightProperty().bind(receiver_label.heightProperty());
        attachment_field.prefWidthProperty().bind(attachment_box.widthProperty().subtract(180));
        attachment_field.minWidthProperty().bind(attachment_box.maxWidthProperty().subtract(180));
        attachment_field.maxWidthProperty().bind(attachment_box.maxWidthProperty().subtract(attachment_box.widthProperty().subtract(180)));

        Button clear_button = new Button("Clear All");
        clear_button.setOnAction(e->{
            if(attachment_field.getText()!=""){
                try{
                    attachment_field.setText(null);
                    menubar attached_items = new menubar();


                }catch (Exception failed){
                    failed.printStackTrace();
                }


            }
        });
        Text this_text = new Text(clear_button.getText());
        attachment_box.getChildren().addAll(path_label, attachment_field,clear_button);
        VBox all_components = new VBox();
        all_components.setSpacing(5);
        all_components.getChildren().addAll(attachment_box);
        stacked_attachment.getChildren().addAll(all_components);
        // TextArea box
        main_message = new TextArea();
        main_message.prefWidthProperty().bind(window.maxWidthProperty());
        button_box();//holds the send and attach_file button/
        main_message.prefHeightProperty().bind(window.heightProperty());
        //main_message.setMaxHeight(main_message.getPrefHeight());
        main_message.wrapTextProperty().set(true);
        main_message.setPromptText("Message: ");
        //alert label
        Text alert_label = new Text(" Please Note: 'Path' stores the location of the attachment file that is to be sent with the email." +
                "The location of this file will not be sent with the email to protect your privacy.");
        alert_label.wrappingWidthProperty().bind(window.widthProperty().subtract(20));
        main_box.getChildren().addAll(stacked_user_name, stacked_sender,receiver_box, stacked_cc,stacked_bcc,stacked_subject, stacked_attachment, button_box(),main_message, alert_label);
        main_layout.add(main_box, "wrap");
        return main_layout;
    }
    private HBox button_box(){////holds the send and attach_file button/
        send_buttonbox = new HBox();
        send_buttonbox.setPadding(new Insets(5,0,5,0));
        send_buttonbox.prefWidthProperty().bind(window.maxWidthProperty());
        send_buttonbox.minHeightProperty().set(40);
        send_buttonbox.getStyleClass().add("send_button_box");
        send_buttonbox.setSpacing(10);
        send_buttonbox.setId("Send_Button_Box");
        //
        send_button = new Button("Send");
        send_button.maxHeightProperty().bind(send_buttonbox.maxHeightProperty());
        //

        send_button.setOnAction(e -> {
            send_mail send_message = new send_mail();
            try {
                send_message.now();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        Button attach_file = new Button("Attach File");
        attach_file.maxHeightProperty().bind(send_buttonbox.maxHeightProperty());
        attach_file.setOnAction(e -> {
            menubar upload_attachment = new menubar();
            upload_attachment.attached_file();
        });
        send_buttonbox.getChildren().addAll(send_button, attach_file);
        return send_buttonbox;
    }

    public void run(Stage window) {// to call the window in the app
        this.window = window;
        call_gui d = new call_gui();//calls the gui window
        d.start();
    }
    public void save_email_on_devices() {//save files if mac

            //System.out.println("Saving in "+new JFileChooser().getFileSystemView().getDefaultDirectory().toString());
            String os_name = System.getProperty("os.name");
            if (os_name.toLowerCase().startsWith("mac")) {
                if (Desktop.isDesktopSupported()) {
                    Platform.runLater(new Runnable() {

                        public void run() {
                            try {
                                //JFileChooser j_file_chooser = new JFileChooser();//for windows
                                String user_directory = System.getProperty("user.home");
                                File folder_path = new File(String.format("%s/Documents/Sydney Russell School", user_directory));
                                if (!(folder_path.exists())) {
                                    folder_path.mkdirs();
                                }
                                // open_file_chooser
                                FileChooser open = new FileChooser();
                                open.setInitialDirectory(folder_path);
                                open.getExtensionFilters().add(new FileChooser.ExtensionFilter("textfile", "*.txt"));
                                File main_file = open.showSaveDialog(window);
                                if (!(main_file.exists())) {
                                    main_file.createNewFile();
                                }
                            } catch (Exception e) {
                                Logger.getLogger(getClass().getName()).log(Level.OFF, "Not a Big deal. Just No File Selected;", 0);
                            }
                        }
                    });
                }
            } else {
                try {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("windows or other OS");
                                FileSystemView system_view = FileSystemView.getFileSystemView();//displays the user_home_directory
                                FileChooser file_chooser = new FileChooser();
                                file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("textFile", "*.txt"));
                                file_chooser.setInitialDirectory(system_view.getDefaultDirectory());
                                File new_file_to_add = file_chooser.showSaveDialog(window);
                                if (!new_file_to_add.exists()) {
                                    new_file_to_add.createNewFile();
                                }
                            } catch (IOException exception) {
                                Logger.getLogger(getClass().getName()).log(Level.OFF, "Not a Big deal. Just Ignore Me. This Device won't let me press cancel without an error. It's Kinda annoying. ", 0);
                            }
                        }
                    });
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.OFF, "Not a Big deal. Just Ignore Me. This Device won't let me press cancel without an error. It's Kinda annoying. ", 0);
                }
            }
    }
    public class menubar {
        List<File> all_files;// contains all files chosen to be attached files
        private void onCloserequest() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert close_alert = new Alert(Alert.AlertType.CONFIRMATION);
                    close_alert.initModality(Modality.APPLICATION_MODAL);//will not allow user to interact with the app unless they choose an option
                    close_alert.getButtonTypes().removeAll(close_alert.getButtonTypes());//removes default buttons
                    close_alert.setTitle("Closing Mail");
                    close_alert.setHeaderText(null);
                    close_alert.setContentText("Are you sure you want to exit?");
                    ButtonType no_please = new ButtonType("No");
                    ButtonType yes_please = new ButtonType("Exit");
                    close_alert.getButtonTypes().addAll(no_please, yes_please);
                    Optional<ButtonType> events = close_alert.showAndWait();
                    if (events.get() == yes_please) {
                        Platform.exit();//closes the app
                        System.exit(0);
                    } else {
                        //does not do anything
                    }
                }
            });
        }

        private MenuBar get_menubar(MigPane pane) {
            MenuBar menuBar = new MenuBar();
            menuBar.setUseSystemMenuBar(true);
            menuBar.getMenus().add(file());
            menuBar.getMenus().add(Edit_menu());
            pane.add(menuBar, "wrap");
            return menuBar;
        }

        private Menu file() {
            Menu file = new Menu("File");
            MenuItem new_message = new MenuItem("New Message");
            new_message.setOnAction(event -> {
                call_gui d = new call_gui();//creates an extra message_window for the user
                d.start();
            });
            //
            MenuItem close_window = new MenuItem("_Close");
            close_window.setMnemonicParsing(true);//Mnemonic will not print _ infront of the the text as allow user to use keyboard to control the window
            close_window.setOnAction(e -> {
                onCloserequest();//displays a message to ask user whether they are sure they want to exit
            });
            //
            MenuItem save = new MenuItem("_Save");
            save.setMnemonicParsing(true);
            save.setOnAction(e -> {
                Task<Void> save_all = new Task<Void>() {//will save the file as txt to the system
                    @Override
                    protected Void call() {
                        save_email_on_devices();
                        return null;
                    }
                };
                Thread state = new Thread(save_all);
                state.setDaemon(true);
                state.setName("Saving File Thread");
                state.start();
            });
            MenuItem send_message = new MenuItem("Send Message");
            send_message.setOnAction(e -> {
                send_mail send_email = new send_mail();
                try {
                    send_email.now();//will start the process of sending the email
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            MenuItem minimize = new MenuItem("Minimize");
            minimize.setOnAction(e -> {
                Node this_node = subject;
                Stage this_stage = (Stage) this_node.getScene().getWindow();
                this_stage.setIconified(true);//to minimize the screen... iconified reduces the size of the screen

            });
            file.getItems().addAll(new_message, send_message, save, minimize, close_window);
            return file;
        }

        private void attached_file() {
            try {
                FileChooser file_chooser = new FileChooser();
                //File this_file = file_chooser.showOpenDialog(window);
                all_files = file_chooser.showOpenMultipleDialog(window);
                //String file_path = this_file.getAbsolutePath();//gets the path to the file chosen

                StringBuilder file_name = new StringBuilder();
                for (File file : all_files) {
                    file_name_and_path.put(file.getName(), file.getAbsolutePath());
                    String last_item = all_files.get(all_files.size()-1).getName();
                    if(file.getName().equals(last_item)){
                        file_name.append(file.getName());
                    }else{
                        file_name.append(file.getName() + ",  ");
                    }
                }
                attachment_field.appendText(file_name.toString());
            }catch (Exception e){

                if(e instanceof NullPointerException){
                    Logger.getLogger(getClass().getName()).log(Level.OFF,"No file selected.",0);
                }
                else{
                    e.printStackTrace();
                }
            }

            // attachment_field.setText(file_path);//sets the path to attachment_field
        }

        private Menu Edit_menu() {
            Menu edit = new Menu("Edit");
            // Attachements
            MenuItem attachments = new MenuItem("Attach File");
            attachments.setOnAction(e -> {
                try {
                    attached_file();//attaches the files
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            MenuItem delete_all = new MenuItem("Reset All ");
            delete_all.setOnAction(e -> {
                try {
                    if(((!(users_name.getText().isEmpty()))||(!(sender_email.getText().isEmpty()))||(!(subject.getText().isEmpty()))||(!(main_message.getText().isEmpty()))||(!(attachment_field.getText().isEmpty())))){
                        General_Warning_alert("This step cannot be undone.","Are you sure you want to erase all the information from the Fields?");
                    }
                }catch (Exception failed){
                    if((failed.toString().contains("NullPointerException"))&&(failed.toString().contains("\"javafx.scene.control.TextField.getText()\" is null"))){
                        System.out.println("This error is only shown to inform that no data is found in the textfield and so it cannot be edited.");//
                    }
                    else{
                        failed.printStackTrace();
                    }
                }

            });
            edit.getItems().addAll(attachments, delete_all);
            return edit;
        }
        private void General_Warning_alert(String Title,String message){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert this_alert = new Alert(Alert.AlertType.CONFIRMATION);
                    this_alert.setHeaderText(null);
                    this_alert.setTitle(Title);
                    this_alert.setContentText(message);
                    Optional<ButtonType> buttons = this_alert.showAndWait();
                    if(buttons.get()==ButtonType.OK){
                        users_name.setText(null);
                        sender_email.setText(null);
                        subject.setText(null);
                        main_message.setText(null);
                        attachment_field.setText(null);
                    }else if(buttons.get()==ButtonType.CANCEL){
                        this_alert.hide();
                    }
                }
            });
           // return ResetAll();
           // return General_Warning_alert(message);
        }
    }

    class send_mail {
        private Session access;

        Task send_the_mail;
        private Thread run_task;
        private Label update_label = new Label();
        private Boolean authorised_mail;
        private Boolean subject_available;//looks for the subject of the mail
        private Boolean user_name_available;//looks if user_name is available
        Boolean run_without_subject;

        private Task main_task(Callable run_this) {
            send_the_mail = new Task() {
                @Override
                protected String call() throws Exception {
                    send_button.setDisable(true);//disable the button from user interaction
                    updateMessage(" Sending...");
                    run_this.call();
                    return null;
                }
            };
            update_label.textProperty().bind(send_the_mail.messageProperty());
            updatelabel();//identifies the location update_label on the screen next to next button.
            send_the_mail.setOnCancelled(e -> {//cancels the task when requested
                System.out.println("cancelled for good");
                send_the_mail.cancel();
                new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        (Action) -> {
                            send_buttonbox.getChildren().remove(update_label);//removes the label after some 1000 milli seconds
                        })).play();
                send_button.setDisable(false);
            });
            send_the_mail.setOnFailed(failed -> {//if error is found
                failed.toString();
                send_the_mail.cancel();
                int lad = send_buttonbox.getChildren().indexOf(update_label);
                update_label.textProperty().unbind();
                update_label.setText("  Failed to send email.");
                new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        (Action) -> {
                            send_buttonbox.getChildren().remove(update_label);//removes the label after some 1000 milli seconds
                        })).play();
                send_button.setDisable(false);
            });
            send_the_mail.setOnSucceeded(new EventHandler<WorkerStateEvent>() {//if event is successful and no errors are found
                @Override
                public void handle(WorkerStateEvent e) {//when mail is sent successfully
                    update_label.textProperty().unbind();//
                    update_label.setText("  Message Successfully Sent to " + receiver_email.getText());
                    new Timeline(new KeyFrame(
                            Duration.millis(700),
                            (Action) -> {
                                send_buttonbox.getChildren().remove(update_label);
                            }
                    )).play();
                    try {
                        Thread.sleep(200);//sleep the thread for 20miilis
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    send_button.setDisable(false);
                    send_the_mail.cancel(true);//close the task
                }
            });
            run_task = new Thread(send_the_mail);
            run_task.setName("Email_Sent_Thread");
            run_task.setDaemon(true);//will close when program shuts rather than waiting for user thread.
            run_task.start();
            return send_the_mail;
        }

        private void check_if_user_name_available() {//checks if the sender has entered his name or not
            /*
            The name of the sender is essential as without it, the email will not be sent.
            */
            try {
                if (users_name.getText().trim().isEmpty()) {
                    user_name_available = false;
                } else {
                   // System.out.println("Does: "+users_name.getText().toString().trim().matches("^[a-zA-Z]*$"));
                    if (Pattern.matches("^[a-zA-Z\\s]*$", users_name.getText().toString().trim())) {
                        user_name_available = true;
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("A name can only contain letters.");
                                email_field_empty(users_name, "A name can only contain letters.");
                                send_the_mail.getOnFailed();
                            }
                        });
                    }
                }
            } catch (Exception exception) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
            }

        }

        private void SendMessage() {
            try {
                Transport.send(message());
            } catch (Exception e) {
                e.printStackTrace();
                send_the_mail.cancel(true);
                //e.printStackTrace();
                //e.printStackTrace();
                if (((e instanceof AuthenticationFailedException) && (e instanceof javax.mail.MessagingException)) && (e instanceof javax.mail.MessagingException)) {
                    System.out.println("Problem with email authentication ");
                    /*email_field_empty(attachment_field, "File not found! Please check again.");*/
                    email_field_empty(sender_email, String.format("%s not found.", sender_email.getText().trim()));
                    send_the_mail.getOnFailed();
                } else if ((e instanceof AuthenticationFailedException) && (e instanceof javax.mail.MessagingException)) {
                    System.out.println("authenticatoin failed");
                    custom_exceptions(e);
                } else if ((e instanceof javax.mail.MessagingException)) {
                    e.printStackTrace();
                    send_the_mail.cancel(true);//cancels the task
                    System.out.println("file not found");
                    email_field_empty(attachment_field, String.format("File not found! Please check again."));
                }
            }
        }
        private  void checking_subject(){
            ///checks to see if subject is available for the email.
            if (subject.getText().trim().isEmpty()) {
                subject_available = false;//if subject not available -
            } else {
                subject_available = true;//if subject is available -
            }
        }
        private Properties email_properties() {
            Properties requirements = new Properties();
            requirements.put("mail.smtp.auth", "true");
            requirements.put("mail.smtp.starttls.enable", "true");
            requirements.put("mail.smtp.host", "smtp.gmail.com");
            requirements.put("mail.smtp.port", "587");
            String passy = "mrbwfazwlgiyutzh";
            access = Session.getInstance(requirements, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender_email.getText(), passy);
                }
            });//authorises mail from gmail
            return requirements;
        }

        public Message message() {
            MimeMessage message = new MimeMessage(access);
            String main_text = main_message.getText();
            Multipart multi_part = new MimeMultipart();
            try {
                if(!(bcc_email.getText().toString().equals(""))) {
                    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc_email.getText()));
                }
                if(!(cc_email.getText().toString().equals(""))){
                    message.setRecipient(Message.RecipientType.CC,new InternetAddress(cc_email.getText()));
                }
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver_email.getText()));
                message.setFrom(new InternetAddress(sender_email.getText()));
                message.setSubject(subject.getText());

                if (!(attachment_field.getText().equals(""))) {//to send mail with _attachment
                    try {
                        multi_part = new MimeMultipart();//used to add the attachment to the file
                        //sender's name
                        MimeBodyPart name_part = new MimeBodyPart();
                        name_part.setText(String.format("Email from: %s", users_name.getText()), "utf-8");
                        multi_part.addBodyPart(name_part);
                        //main message
                        MimeBodyPart text_to_send = new MimeBodyPart();
                        text_to_send.setText(main_text, "utf-8");
                        multi_part.addBodyPart(text_to_send);
                        System.out.println("File Name and Path \n"+file_name_and_path);
                        // attachment
                        for (String file_path : file_name_and_path.values()) {
                            MimeBodyPart attached_file = new MimeBodyPart();
                            attached_file.setText("attached_file");// the Id can be clear all the attachment in case it is requested
                            DataSource data_source = new FileDataSource(file_path);
                            attached_file.setDataHandler(new DataHandler(data_source));
                            multi_part.addBodyPart(attached_file);
                        }
                        message.setContent(multi_part);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //without_attachment
                    file_name_and_path.clear();
                    System.out.println("File Name And Path: \n"+file_name_and_path);

                    StringBuilder text_for_mail = new StringBuilder();
                    text_for_mail.append(String.format("From: %s \n",users_name.getText())+main_text);
                    message.setText(text_for_mail.toString(),"UTF-8");
                }
                //after attachment_is_attached
                checking_subject();
                return message;
            } catch (Exception e) {
                if((e instanceof NullPointerException) &&(e.toString().contains("javafx.scene.control.TextField.getText()"))){
                    try{
                        // will send from here if attachments are cleared to send a mail without any attachements
                        System.out.println("Sending Email after attachment removed.");
                        message.setText(main_text, "utf-8");
                        checking_subject();
                        return message;
                   }catch (Exception failed){
                       failed.printStackTrace();
                   }
                }else{
                    custom_exceptions(e);
                    e.printStackTrace();
                }
            }
            return message;
        }

        private Exception custom_exceptions(Exception exception) {
            if (exception instanceof AuthenticationFailedException) {
                email_field_empty(sender_email, String.format("%s not found.", sender_email.getText().trim()));
                send_the_mail.getOnFailed();
            } else if (exception instanceof MessagingException && (sender_email.getText().isEmpty())) {
                email_field_empty(sender_email, "Field must not be empty!");
                send_the_mail.getOnFailed();///cancels sending the email and displays error
            }
            return exception;
        }

        public void now() throws Exception {
            /* message();/*calls the message node to check subject is available or not
            if subject is not available, then subject_available is set to false so the Alert the user can be issued.
            */
            main_task(new Callable() {
                public String call() {
                    check_if_user_name_available();
                    if (user_name_available == true) {
                        email_properties();
                        message();//checks if subject is available or not
                        if (subject_available == false) {// will issue alert to the user to choose they would like to send mail without a subject or not
                            //subject_not_found_exception();
                            send_the_mail.cancel(true);
                            subject_is_empty();
                        } else {
                            SendMessage();//sends the email
                        }
                    } else {
                        email_field_empty(users_name, "Name must not be empty.");
                        send_the_mail.getOnFailed();
                        System.out.println("Error in now()");
                    }
                    return "sent_with_subject";
                }
            });
        }

        private void updatelabel() {//fix this label.. shows checking_for_update on the email process
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        MigPane root = (MigPane) send_buttonbox.getScene().getRoot();//button box as a node to access the root. (you can use any node)
                        Parent parent_ = send_button.getParent();
                        update_label.minWidthProperty().bind(send_button.maxWidthProperty());
                        update_label.maxHeightProperty().bind(send_button.heightProperty());
                        update_label.setId("update_label_important");
                        HBox parent_box = (HBox) parent_;
                        parent_box.getChildren().add(update_label);//adds the update_label next to the button
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        private Label empty_field_notuse(TextField textfield, String message) {//if a field is empty
            //create label to display
            MigPane root = (MigPane) subject.getScene().getRoot();///any node from the scene should work
            Label empty_field_label = new Label(message);
            empty_field_label.getStyleClass().add("empty_label");
            empty_field_label.minWidthProperty().bind(textfield.minWidthProperty());
            empty_field_label.prefHeightProperty().bind(textfield.maxHeightProperty().add(10));
            StackPane d = (StackPane) textfield.getParent().getParent();
            //d.maxWidthProperty().bind(window.widthProperty().subtract(50));
            d.setAlignment(Pos.CENTER_RIGHT);
            d.getChildren().add(empty_field_label);
            if (textfield.getId() == attachment_field.getId()) {//if Id from attachment_field==this.textfield id
                textfield.textProperty().addListener(e -> {
                    try {
                        int empty_field_label_index = d.getChildren().indexOf(empty_field_label);//index of the label
                        if (!textfield.getText().trim().equals("")) {
                            if (!d.getChildren().contains(empty_field_label)) {
                                d.getChildren().add(empty_field_label);
                                d.getChildren().get(d.getChildren().indexOf(empty_field_label));
                            } else {
                                d.getChildren().get(empty_field_label_index);
                            }
                        } else {//if text-area is not empty
                            d.getChildren().remove(empty_field_label);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }
            textfield.textProperty().addListener(e -> {
                try {
                    int empty_field_label_index = d.getChildren().indexOf(empty_field_label);//index of the label
                    if (textfield.getText().trim().equals("")) {
                        if (!d.getChildren().contains(empty_field_label)) {
                            d.getChildren().add(empty_field_label);
                            d.getChildren().get(d.getChildren().indexOf(empty_field_label));
                        } else {
                            d.getChildren().get(empty_field_label_index);
                        }
                    } else {//if text-area is not empty
                        d.getChildren().remove(empty_field_label);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            return empty_field_label;
        }

        private void email_field_empty(TextField textField, String message) {//calls the empty_field_notuse(TextField textfield,String message) function
            Platform.runLater(new Runnable() {
                public void run() {
                    empty_field_notuse(textField, message);
                }
            });
        }
        private void subject_is_empty() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert no_subject = new Alert(Alert.AlertType.ERROR);
                    no_subject.initModality(Modality.APPLICATION_MODAL);
                    no_subject.setGraphic(new ImageView("/images/error_icon.png"));
                    no_subject.setResizable(false);
                    no_subject.getDialogPane().setHeaderText(null);
                    no_subject.setTitle("No Subject Found!");
                    no_subject.getDialogPane().setContentText("Subject is empty! Would you like to continue without a subject?");
                    ButtonType yes_please = new ButtonType("Yes");
                    ButtonType no_please = new ButtonType("No");
                    no_subject.getButtonTypes().remove(0);
                    no_subject.getButtonTypes().addAll(no_please, yes_please);
                    Optional<ButtonType> event = no_subject.showAndWait();
                    if (event.get() == yes_please) {/*
                     if(subject_availible==false- there is no subject entered for the message. In this ask
                     user to send the message without subject or not.
                     if(answer==yes_please):
                       start the main_task() to send the email
                    */
                        try {

                            main_task(new Callable() {
                                @Override
                                public Object call() throws Exception {
                                    SendMessage();
                                    System.out.println("sent without subject");
                                    return null;
                                }
                            }).messageProperty();
                            //main_task(d).messageProperty();
                            //main_task().handle((Event) check_for_subject);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    } else if (event.get() == no_please) {
                        if (send_the_mail.isRunning()) {
                            send_the_mail.cancel(true);//cancel the task
                            System.out.println("line 832");
                        }
                    }
                }
            });

        }
    }

    class call_gui extends Service<Stage> {
        @Override
        protected Task<Stage> createTask() {
            return new Task<Stage>() {
                @Override
                protected Stage call() {
                    Platform.runLater(new Runnable() {//creates a new message window
                        @Override
                        public void run() {
                            try {
                                Stage new_window = new Stage();

                                screen_size(new_window);
                                //
                                Rectangle2D size = Screen.getPrimary().getVisualBounds();
                                Scene sce = new Scene(general_page());
                                sce.getRoot().setId("mailbox_window");
                                //String apptry1css = this.getClass().getResource(new apptry2().apptry1css).toExternalForm();
                                sce.getStylesheets().addAll(cssfile);
                                new_window.setScene(sce);
                                new Common_features_for_all().close_window(sce);
                                new_window.setFullScreen(false);
                                new_window.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return null;
                }
            };
        }
    }
}





