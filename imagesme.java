package Source_for_this_main;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class imagesme {
    ArrayList<ImageView> images;
    StackPane stacked_images;
    AnchorPane main_layout;

    private void forward_back_buttons() {
        Button forward = new Button();
        forward.setGraphic(new ImageView("/images/right-arrow.png"));
        forward.setOnAction(e -> {
            next_image();
        });
        Button back = new Button();
        back.setOnAction(e -> {
            backward();
        });
        back.setGraphic(new ImageView("/images/left-arrow.png"));

        main_layout.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                AnchorPane.setRightAnchor(forward, 0d);
                AnchorPane.setBottomAnchor(forward, 3d);
                main_layout.getChildren().add(forward);
                //back
                AnchorPane.setLeftAnchor(back, 0d);
                AnchorPane.setBottomAnchor(back, 3d);
                main_layout.getChildren().add(back);
            }
        });
        main_layout.setOnMouseExited(e -> {
            main_layout.getChildren().remove(forward);
            main_layout.getChildren().remove(back);
        });
    }

    private void string_to_image() {
        String[] all_pics = {"/images/year11.jpg", "/images/housecup.jpg", "/images/sixth-form.jpg", "/images/royal-visit.jpg"};
        images = new ArrayList<>();//from string (all_pics) to imageformat
        for (String xpic : all_pics) {//xpic == pic number x
            ImageView image_view = new ImageView(xpic);
            image_view.fitWidthProperty().bind(new apptry2().window.widthProperty().divide(1.65));//to allow space for house_points
            images.add(image_view);
        }
        stacked_images.getChildren().add(images.get(0));
    }

    public AnchorPane main_slideshow() {
        stacked_images = new StackPane();
        //
        Glow stacked_images_glowup = new Glow();
        stacked_images_glowup.setLevel(0.3);
        stacked_images.setEffect(stacked_images_glowup);
        //
        main_layout = new AnchorPane(stacked_images);
        main_layout.prefHeightProperty().bind(stacked_images.prefHeightProperty());
        main_layout.prefHeightProperty().bind(stacked_images.prefWidthProperty());
        AnchorPane.setLeftAnchor(stacked_images, 0.0);//distance from each corner
        AnchorPane.setRightAnchor(stacked_images, 0.0);
        AnchorPane.setTopAnchor(stacked_images, 0.0);
        AnchorPane.setBottomAnchor(stacked_images, 0.0);
        string_to_image();
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3000),
                (ActionEvent) -> {
                    next_image();

                }));
        t.autoReverseProperty().set(true);
        t.play();
        forward_back_buttons();
        return main_layout;

    }

    public void first_image() {
        var first_item = images.get(0);
        try {

            stacked_images.getChildren().add(first_item);
            stacked_images.getChildren().get(0);

        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                try {

                    stacked_images.getChildren().remove(images.indexOf(images.get(0)));//halts first item from replicating it self
                } catch (IllegalArgumentException f) {
                    f.printStackTrace();
                }
            } else if (e instanceof IndexOutOfBoundsException) {
                stacked_images.getChildren().add(0, images.get(0));
            }

        }

    }

    public void next_image() {

        try {
            var current_item = stacked_images.getChildren().get(0);
            int current_index = images.indexOf(stacked_images.getChildren().get(0));
            var next_item = images.get(current_index + 1);
            next_item.translateXProperty().set(stacked_images.getScaleX());
            var insert_item = new KeyValue(next_item.translateXProperty(), 0, Interpolator.EASE_IN);
            var frame = new KeyFrame(Duration.millis(800), insert_item);
            var timeline = new Timeline(frame);
            stacked_images.getChildren().add(next_item);
            timeline.setOnFinished(e -> {
                stacked_images.getChildren().remove(current_item);
            });
            timeline.play();
        } catch (IndexOutOfBoundsException exception) {
            //
            first_image();
        } catch (IllegalArgumentException e) {
            try {
                stacked_images.getChildren().remove(0);// exception remover
            } catch (IllegalArgumentException f) {
                f.printStackTrace();
            }
        }
    }

    public void backward() {
        var current_item = stacked_images.getChildren().get(0);
        int current_index = images.indexOf(stacked_images.getChildren().get(0));

        try {
            var next_item = images.get(current_index - 1);


            next_item.translateYProperty().set(stacked_images.getScaleY());
            var insert_item = new KeyValue(next_item.translateXProperty(), 0, Interpolator.EASE_IN);
            var frame = new KeyFrame(Duration.millis(800), insert_item);
            var timeline = new Timeline(frame);
            stacked_images.getChildren().add(next_item);
            timeline.setOnFinished(event -> {
                stacked_images.getChildren().remove(current_item);
            });
            timeline.play();
        } catch (IndexOutOfBoundsException e) {
            var last_item = images.get(images.size() - 1);//last image in the list
            stacked_images.getChildren().remove(0);
            stacked_images.getChildren().add(0, last_item);

        } catch (IllegalArgumentException e) {
            try {
                stacked_images.getChildren().remove(current_item);
            } catch (IllegalArgumentException f) {
                f.printStackTrace();
            }

        }
    }
}
