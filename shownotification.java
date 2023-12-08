package Source_for_this_main;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.tbee.javafx.scene.layout.MigPane;

public class shownotification extends Service {
    private String cssstylesheet = ("/stylesheets/for_notification.css");
    public Notifications pushnotification;
    public EventHandler<ActionEvent> notification_event;// action event for notification. if notification clicked. activate the event.
    public Notifications notifyUpdate(String title, String yourmessage, EventHandler Clickevent) {
        Clickevent = this.notification_event;
        Node notificationImage = new ImageView("/Images_required_in_certain_places/for_notification_only.png");
        pushnotification = Notifications.create()
                .position(Pos.TOP_RIGHT)
                .title(title)
                .text(yourmessage)
                .graphic(notificationImage)
                .hideAfter(Duration.INDEFINITE)
                .onAction(notification_event);
        return pushnotification;
    }
    public void newsupdate() throws InterruptedException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage notifyWindow = new Stage();// notifiwindow == notification window
                MigPane notifylayout = new MigPane();
                Scene notifyscene = new Scene(notifylayout, 0.5, 0.5);
                notifyscene.getStylesheets().add(cssstylesheet);
                notifyWindow.setScene(notifyscene);
                notifyWindow.toBack();
                notifyWindow.show();
                notifyUpdate("News Received!", "News Updates received. check out the latest!", notification_event).show();
            }
        });
    }
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            newsupdate();
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