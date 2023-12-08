package Source_for_this_main;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javafx.stage.StageStyle;
import javafx.util.Duration;


public class SplashScreen extends Application {

    public Stage window;
    private Label loading_label;
    private BorderPane main_pane;
    private ProgressBar main_progress;
    internet_connectivity check_internet = new Source_for_this_main.internet_connectivity();
    private void check_internet_and_run_files() {
        try {

            check_internet.website_url = "http://www.sydneyrussellschool.com/";
            check_internet.message = "This web page cannot be displayed because your computer is offline.";
            check_internet.run_to_web = false;
            check_internet.run_file = true;
            check_internet.start_thread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BorderPane main_layout() {
        try {
            main_pane = new BorderPane();
            main_pane.setPadding(new Insets(5));
            //
            HBox image_box = new HBox();
            image_box.setEffect(new Glow(0.5));
            image_box.setAlignment(Pos.CENTER_RIGHT);
            //Image this_image = new Image(this.getClass().getResourceAsStream("/Images_required_in_certain_places/for_splash_screen.png"));
            ImageView image_to_set = new ImageView("/Images_required_in_certain_places/for_splash_screen.png");

            image_box.getChildren().add(image_to_set);
            main_pane.setCenter(image_box);
            //
            loading_label = new Label("Loading");
            main_progress = new ProgressBar();
            Timeline progress_timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            String current_text = loading_label.getText();
                            loading_label.setText(
                                    (current_text.equals("Loading . . ."))
                                            ? "Loading ."
                                            : current_text + " ."
                            );
                        }
                    }),
                    new KeyFrame(Duration.millis(1000))
            );
            progress_timeline.setCycleCount(Timeline.INDEFINITE);
            progress_timeline.play();
            main_pane.setBottom(loading_label);
            return main_pane;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return main_layout();
    }
    private void main_task() {
        try {
            window.setScene(new Scene(main_layout(), 400, 200));
            window.show();
            check_internet_and_run_files();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    PauseTransition pause = new PauseTransition(Duration.millis(10000));
                    pause.setOnFinished(event -> {
                        try {
                            new speedy_run().start();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    pause.play();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(Stage window) {
        this.window = window;
        window.initStyle(StageStyle.UNDECORATED);
        main_task();
    }

    class speedy_run extends Service {
        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Task call() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                window.hide();
                               // Thread.sleep(2000);
                                Stage this_stage = new Stage();
                                new apptry2().start(this_stage);

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

    public static void main(String[] args) {
        launch(Source_for_this_main.SplashScreen.class, args);
    }
}
