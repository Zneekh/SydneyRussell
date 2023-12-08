package Source_for_this_main;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
//private String stylesheet = ("/stylesheets/apptry1.css");

public class newsboxmostimportant {
    ObservableList<String> from_news;
    ObservableList<String> receiving_list;
    ObservableList<String> updates;//stores the newly imported data
    ArrayList<TextArea> news_areas = new ArrayList<>();
    StackPane stacked_news;
    AnchorPane original_layout;
    private void reading_data_from_news() {
        try {
            InputStream open_stream = this.getClass().getResourceAsStream("/news&updates/News");
            InputStreamReader stream_reader = new InputStreamReader(open_stream);
            BufferedReader reader = new BufferedReader(stream_reader);
            String this_line;
            from_news = FXCollections.<String>observableArrayList();
            while ((this_line = reader.readLine()) != null) {
                if (!(this_line.toString().trim().isEmpty())) {
                    from_news.add(this_line.toString());
                }
            }
            reader.close();
            stream_reader.close();
            open_stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reading_from_receiving_end() {
        try {
            //
            receiving_list = FXCollections.<String>observableArrayList();
            InputStream inputStream = this.getClass().getResourceAsStream("/news&updates/upcomingevents-receiving-end");
            InputStreamReader fs = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(fs);
            String current_string;//create a new string to cache the read line as buffered reader would skip over it due to the double use of readLine()
            while ((current_string = reader.readLine()) != null) {
                if (!(current_string.toString().trim().isEmpty())) {
                    receiving_list.add(current_string);
                }
            }
            reader.close();
            fs.close();
            inputStream.close();
            //
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    // display notification
    private void display_notification(){

        if(updates.size()>0){
                   shownotification open_notification = new shownotification();
                    open_notification.notification_event = new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            int new_item = from_news.indexOf(updates.get(updates.size()-1));
                            stacked_news.getChildren().add(0,news_areas.get(new_item));
                        }
                    };

            open_notification.start();

            System.out.println("Updates Received.");
        }
        else {
            System.out.println("empty box");
        }


    }





    private void run_check_and_write() {// used to see if the any new updates have been found
        try {
            reading_from_receiving_end();
            reading_data_from_news();
            //
            updates = FXCollections.<String>observableArrayList();
            for (int x = 0; x < receiving_list.size(); x++) {
                if (!(from_news.contains((receiving_list.get(x))))) {
                    from_news.add(receiving_list.get(x));
                    updates.add(receiving_list.get(x));
                }
            }
            //write to News file
            FileOutputStream output_stream = new FileOutputStream("src/main/resources/news&updates/News");
            for(String item:from_news){
                String item_new_line = item+"\n";//adds a new line after the line
                byte[] string_to_byte = item_new_line.getBytes();
                output_stream.write(string_to_byte);
            }
            output_stream.close();
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTextArea(){
        /*
        This holds all the data being transported from txt to a node(TextArea);
         */

        run_check_and_write();
        for(String item:from_news){
            TextArea to_display = new TextArea();
            to_display.setText(item);
            to_display.setEditable(false);
            to_display.setWrapText(true);
            news_areas.add(to_display);
        }
        display_notification();
    }
    //

    private void first_item() {// goes to the first item:fade out

        var first_item =news_areas.get(0);
        try {

            stacked_news.getChildren().add(first_item);
            stacked_news.getChildren().get(0);

        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                try {

                    stacked_news.getChildren().remove(news_areas.indexOf(news_areas.get(0)));//halts first item from replicating it self
                } catch (IllegalArgumentException f) {
                    f.printStackTrace();
                }
            } else if (e instanceof IndexOutOfBoundsException) {
                stacked_news.getChildren().add(0, news_areas.get(0));
            }

        }

    }
    public void nextitem() { // to next item through animation
        try {
            int current_index = news_areas.indexOf(stacked_news.getChildren().get(0));
            var accessible_text = news_areas.get(current_index).getText();
            var current_item = stacked_news.getChildren().get(0);
            current_item.setAccessibleText(accessible_text);//accessibleText will be used to set TooltipText for the text-area
            news_areas.get(current_index).tooltipProperty().set(new Tooltip(accessible_text));//tooltip for currentitem
            // nextitem
            var next_items = news_areas.get(current_index + 1);
            next_items.setAccessibleText(news_areas.get(current_index + 1).getText());//accesible text of nextitem
            next_items.tooltipProperty().set(new Tooltip(next_items.getAccessibleText()));//tooltip for nextitem
            next_items.translateXProperty().set(stacked_news.getLayoutX());
            var insert_item = new KeyValue(next_items.translateXProperty(), 0, Interpolator.EASE_IN);
            var frame = new KeyFrame(Duration.millis(1500), insert_item);
            Timeline time_line  = new Timeline(frame);
            stacked_news.getChildren().add(next_items); //add next_item to the list
            time_line.setOnFinished(e -> {
                stacked_news.getChildren().remove(current_item);
            });
            time_line.play();
        } catch (IndexOutOfBoundsException exception) {

          first_item();

        } catch (IllegalArgumentException e) {
            try {
                stacked_news.getChildren().remove(0);// exception remover
            } catch (IllegalArgumentException f) {
             //   f.printStackTrace();
            }
        }
    }

    private void previousItem() {//to previous item through animation.

        try {
            var current_item = stacked_news.getChildren().get(0);
            int current_index = news_areas.indexOf(stacked_news.getChildren().get(0));
            var next_item = news_areas.get(current_index - 1);
            next_item.translateYProperty().set(stacked_news.getScaleY());
            var insert_item = new KeyValue(next_item.translateXProperty(), 0, Interpolator.EASE_IN);
            var frame = new KeyFrame(Duration.millis(1500), insert_item);
            var timeline = new Timeline(frame);
            stacked_news.getChildren().add(next_item);
            timeline.setOnFinished(event -> {
                stacked_news.getChildren().remove(current_item);
            });
            timeline.play();
        } catch (IndexOutOfBoundsException e) {
            first_item();
        } catch (IllegalArgumentException e) {
                stacked_news.getChildren().remove(stacked_news.getChildren().remove(0));

        }
    }

    public AnchorPane newsarea() { //main_layout
        stacked_news = new StackPane();// holds all the textAreas
        stacked_news.minWidthProperty().set(200);
        stacked_news.maxWidthProperty().set(400);
        stacked_news.prefWidthProperty().set(400);
        createTextArea();
        //height
        stacked_news.setStyle("-fx-background-insets:2 2 2 0;" + "-fx-border-width:2px;" +
                "-fx-border-color:linear-gradient(to left,rgb(0,153,0),white);"+"-fx-background-color:rgb(0,0,0);");
        stacked_news.prefHeightProperty().set(80);
        stacked_news.maxHeightProperty().set(80);
        stacked_news.minHeightProperty().set(80);
        //
        original_layout = new AnchorPane(stacked_news);//// original_layout refers to layout for Horizontal Box(HBox) that holds all the stackareas
        original_layout.setPadding(new Insets(0));
        //original_layout.setMaxSize(stackedareas.getMaxWidth(), stackedareas.getMaxHeight());
        AnchorPane.setTopAnchor(stacked_news, 0.0); //aDouble is used to specify the distance from each corner or side
        AnchorPane.setBottomAnchor(stacked_news, 0.0);
        AnchorPane.setLeftAnchor(stacked_news, 0.0);
        AnchorPane.setRightAnchor(stacked_news, 0.0);
        //
        nextAndBackButton();//next and back button to animation when hovered. //
        //main timeline to run the animation.
        Timeline main_timeline = new Timeline();
        main_timeline.setDelay(Duration.millis(2500));
        main_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), (ActionEvent) -> { //Duration.millis(1200)==1200milliseconds wait before frame changes
            nextitem();
        }));
        main_timeline.setCycleCount(Timeline.INDEFINITE);
        main_timeline.autoReverseProperty().setValue(true);
        main_timeline.play();
        return original_layout;

    }
    private void nextAndBackButton() {
        // next button
        Button next = new Button();
        next.setGraphic(new ImageView("images/right-arrow.png"));
        next.setTooltip(new Tooltip("Next"));
        next.setMaxSize(40, 10);
        next.getStyleClass().add("next-button");
        next.setOnMouseEntered(e -> {
            next.setTooltip(new Tooltip("Next"));
        });
        next.setOnAction(e -> {
            nextitem();
        });
// back button
        Button back = new Button();
        back.setGraphic(new ImageView("/images/left-arrow.png"));
        back.setOnMouseEntered(e -> {
            back.setTooltip(new Tooltip("Back"));
        });
        back.setOnAction(e -> {
            previousItem();
        });
        back.setMaxSize(40, 10);
        back.setPrefSize(back.getMaxWidth(), back.getMaxHeight());
        back.getStyleClass().add("next-button");
        /* if news-area hovered with a mouse*/
        original_layout.setOnMouseEntered(new EventHandler<MouseEvent>() {
            //add both next and back button to the layout on top of the textAreas
            public void handle(MouseEvent e) {
                AnchorPane.setRightAnchor(next, 0d); // distance 0 from right side of
                AnchorPane.setBottomAnchor(next, 3d);
                original_layout.getChildren().add(next);
                //add back button
                AnchorPane.setLeftAnchor(back, 0d);
                AnchorPane.setBottomAnchor(back, 3d);
                original_layout.getChildren().add(back);
            }
        });
        original_layout.setOnMouseExited(new EventHandler<MouseEvent>() {
            //remove both the next and back button.
            public void handle(MouseEvent e) {
                original_layout.getChildren().remove(next);
                original_layout.getChildren().remove(back);
            }
        });
    }

}