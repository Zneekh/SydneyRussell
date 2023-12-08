package Source_for_this_main;

/*




    This class can be used to access common features across all pages of the app. For example,
    the image used in title, general image layout (layout used for images across the app) and etcetera.











 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tbee.javafx.scene.layout.MigPane;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class Common_features_for_all {
    final String css_file = "/stylesheets/CommonFeaturesforall_essentials.css";
    private TextField title;
    //images_with_title can also be used for General layout of an image
    /*
    If text for image is not required, just make (String title) is "null" just like in the following example
                         public HBox images_with_title(null, bob,"90")
     */
    public HBox title(MigPane layout_name){
        HBox title_box =new HBox();
        title_box.prefWidthProperty().bind(layout_name.widthProperty().divide(1.7));
        AnchorPane this_pane = new AnchorPane();
        this_pane.prefWidthProperty().bind(title_box.prefWidthProperty());
        //

        title_box.setStyle("-fx-border-width:0 0 2 0;"+"-fx-border-color:rgb(0,153,0);");
        title = new TextField(layout_name.getId());
        AnchorPane.setLeftAnchor(title_box,0d);
        title.setId("title_for_layout");
        title.setPrefHeight(80);
        title.setEditable(false);
        new apptry2().selectable_label(title,"40","\"Times New Roman\", Times, serif");
        Text size_of_field = new Text(title.getText());
        double min_width_of_textfield = size_of_field.getBoundsInLocal().getWidth()+200;
        title.setMinWidth(min_width_of_textfield);
        Source_for_this_main.apptry2 home_button_class = new Source_for_this_main.apptry2();
        Button this_button = home_button_class.home_button();
        AnchorPane.setRightAnchor(this_button,0d);
        this_pane.getChildren().addAll(title,this_button);
        this_pane.widthProperty().addListener((old,this_,now)->{
            // removes this_button if it covers the title
            if((now.intValue()<(min_width_of_textfield+300))) {
                this_pane.getChildren().remove(this_button);
            }
            else{
                if(!(this_pane.getChildren().contains(this_button))){
                    this_pane.getChildren().add(this_button);
                }
            }
        });
        title_box.getChildren().addAll(this_pane);

        layout_name.add(title_box,"wrap");
        return title_box;
    }
    public HBox general_layout_for_image(String path_to_image, String image_text, String height_of_image) {
        /* Text or no text. */
        Stage this_stage = (Stage) apptry2.window;
        HBox image_box = new HBox();
        image_box.setPadding(new Insets(1,10,1,10));
        //
        image_box.minWidthProperty().bind(this_stage.widthProperty().subtract(25));
        image_box.maxWidthProperty().bind(this_stage.widthProperty().divide(2));
        image_box.prefWidthProperty().bind(this_stage.widthProperty().divide(3));
        //image_box.prefWidthProperty().bind(this_stage.widthProperty().divide(2));
        StackPane image_pane = new StackPane();
        ImageView image = new ImageView(path_to_image);
        image.setPreserveRatio(false);
        image.fitHeightProperty().set(Integer.parseInt(height_of_image));
        image.fitWidthProperty().bind(image_box.widthProperty());
        //image.fitWidthProperty()
        image_pane.getChildren().addAll(image);
        TextField image_texts = new TextField(image_text);
        image_texts.setEditable(false);
        StackPane.setMargin(image_texts, new Insets(0, 50, 0, 10));
        image_pane.setAlignment(Pos.CENTER_LEFT);
        image_texts.setStyle("-fx-background-color:transparent,transparent;" + "-fx-text-fill:rgb(255,255,255);" +
                "-fx-highlight-fill:transparent,transparent;" + "-fx-font-weight:bold;" + "-fx-font-size:45px;" + "-fx-font-family:\"Times New Roman\", Times, serif;");
        image_pane.getChildren().add(image_texts);
        image_box.getChildren().addAll(image_pane);
        return image_box;
    }


    /// save a file
    public void save_file_from_internet(String url, String your_file_name) {
        try {
            Runnable download_file = new Runnable() {
                @Override
                public void run() {
                    {
                        try {
                            String user_home = System.getProperty("user.home");
                                System.out.println("For "+user_home);
                                FileSystemView file_system = FileSystemView.getFileSystemView();
                                File directory = new File(String.format("%s/Documents/Sydney Russell School/Downloaded Files",file_system.getDefaultDirectory()));
                                if(!(directory.exists())){
                                    directory.mkdirs();
                                }
                                URL url_to_web = new URL(url);
                                InputStream downloading_data = url_to_web.openStream();
                                Files.copy(downloading_data,Paths.get(String.format("%s/%s",directory,your_file_name)),StandardCopyOption.REPLACE_EXISTING);
                                Desktop.getDesktop().open(new File(String.format("%s/%s",directory,your_file_name)));
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread df = new Thread(download_file);
            df.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //scrollbar
    public ScrollPane scrollbar_for_node(Node node, Boolean for_vertical, Boolean for_horizontal) {
        ScrollPane scroll_pane = new ScrollPane();
        scroll_pane.setPannable(false);

        scroll_pane.setFitToWidth(true);
        if ((for_horizontal == true) && (for_vertical == true)) {
            scroll_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scroll_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        } else if (for_vertical == true) {
            scroll_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scroll_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } else if (for_horizontal == true) {
            scroll_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        } else {
            scroll_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }
        scroll_pane.setContent(node);
        return scroll_pane;
    }
    public void close_window(Scene scene){
        String user_system = System.getProperty("os.name");

        KeyCodeCombination command_w = new KeyCodeCombination(KeyCode.W,KeyCombination.META_DOWN);
        KeyCodeCombination command_f = new KeyCodeCombination(KeyCode.F,KeyCombination.META_DOWN);
        //command_w is used to minimize the scene
        Stage this_stage = (Stage) scene.getWindow();
        scene.setOnKeyPressed(event->{
            if(command_w.match(event)){
                this_stage.setIconified(true);;//minimizes the screen
            }else if(command_f.match(event)){
                if(scene.getRoot().getId()!="mailbox_window"){
                    if(this_stage.isFullScreen()==true){
                        System.out.println("Exiting full Screen");
                        this_stage.setFullScreenExitHint("");
                        this_stage.setFullScreen(false);
                    }else{
                        System.out.println("Entering full Screen");
                        this_stage.setFullScreenExitHint("");
                        this_stage.setFullScreen(true);
                    }
                }
            }
        });



    }
}
