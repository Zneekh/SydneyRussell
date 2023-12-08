package Source_for_this_main.auto_update;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class update_available_notification extends Service {
    Label version_label;//version_label stores the new update version from Class.checking_for_update().comparing_version()
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                show_notification();
                return null;
            }
        };
    }
    public void update_alert(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert update_notification = new Alert(Alert.AlertType.CONFIRMATION);
                update_notification.getButtonTypes().removeAll(update_notification.getButtonTypes());// removes default buttons
                ButtonType update_button = new ButtonType("Update!");
                ButtonType not_now = new ButtonType("Not now");
                update_notification.getButtonTypes().addAll(not_now,update_button);
                update_notification.setHeaderText(null);
                update_notification.setTitle("Update Available!");
                update_notification.setContentText(String.format("Click Update to install Version %s. Use of older versions is not recommended for Data Protection purposes.",version_label.getText().toString()));
                Optional<ButtonType> inputs = update_notification.showAndWait();
                if(inputs.get()==update_button){
                    System.out.println("pressed checking_for_update");
                }else if(inputs.get()==not_now){
                    System.out.println("not now pressed");
                }
            }
        });
    }
    private void show_notification(){
        try{
            update_alert();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
