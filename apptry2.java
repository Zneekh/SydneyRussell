package Source_for_this_main;
/*                         For testing purposes					*/

//////////////////////////////// remeber to r_file.close() when finished ::::::::::::::::::::::::::


/*



<!-- For_Staff_Only jar dependency-->
        <dependency>
            <groupId>org.For_Staff_Only</groupId>
            <artifactId>For_Staff_only</artifactId>
            <version>1.0</version>
        </dependency>
 */

import Source_for_this_main.auto_update.update_available_notification;

import javafx.animation.*;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.geometry.Insets;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.tbee.javafx.scene.layout.MigPane;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class apptry2 extends Application {
    Common_features_for_all from_common_features;
    //HBox imagebox;
    private Scene main_scene;
    private Text data_to_display_textsize;
    private TextArea data_to_display;
    private read_file_text data_to_read;//stores data read from textfiles
    StackPane alllayouts;
    HBox image_slideshow_box;
    public static Stage window;
    MenuItem srspe, srstwitter, srsfacebook, schoolweb, sydtv, srs_science, s_Status, p_sStatus;
    public Menu contact_us;
    MenuItem pg_changestogcse, s_changestogcse;
    public final String apptry1css = "/stylesheets/apptry1.css";
    Alert tryforall;
    double testing_int;
    Common_features_for_all common_features_for_all_var;
    private Rectangle2D screen;
    //// !important ------------->>>>>>>>> onlinecomponents == things that require internet availability. e.g. websites.
    // pg == parents/guardians
    //pg_frs == parent/guardians_free school meals
    /// used to separate menuItems
    private Tooltip tooltip;
    private void tooltips(Tooltip tooltip) {
        this.tooltip = tooltip;
        tooltip.setShowDelay(Duration.seconds(1));
        tooltip.setHideOnEscape(true);
        tooltip.setHideDelay(Duration.seconds(4));
        tooltip.setShowDuration(Duration.seconds(5));
        if (tooltip.isActivated()) {
            tooltip.setShowDelay(Duration.seconds(1));
            tooltip.setShowDuration(Duration.seconds(4));
        }
    }
    class menubar {
        private MenuBar main_menubar() throws IOException {
            MenuBar menubar = new MenuBar();
            menubar.setUseSystemMenuBar(true);
            menubar.getMenus().add(school_menu());
            menubar.getMenus().add(housecup());
            menubar.getMenus().add(pupil_menu());
            menubar.getMenus().add(sixth_form());
            menubar.getMenus().add(parent_gaurdian());
            menubar.getMenus().add(websites());
            menubar.getMenus().add(contact_us());
            String os_name = System.getProperty("os.name");
            if(os_name.toLowerCase().startsWith("mac")){
                menubar.setUseSystemMenuBar(true);
                System.out.println("in mac");

            }
          //  Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"Your meessage for now\" with title \"Title\"" });
            return menubar;
        }
        private Menu websites() {
            Menu news = new Menu("News ");
            news.getItems().addAll(toWebs());
            return news;
        }
        private Menu contact_us() {
            contact_us = new Menu("Contact Us");
            new scenes().contactSchool(contact_us);
            return contact_us;
        }
        private Menu parent_gaurdian() {
            Menu parent_guardian = new Menu("Parent/Guardian");
            MenuItem a8p8 = new MenuItem("Attainment 8 & Progress 8");
            a8p8.setOnAction(event -> {
                change_scene_animation(new scenes().a8p8_page());
            });
            //parent_guardian information
            Menu pg_info = new Menu("Parent/Guardian Information");
            MenuItem remote_learning = new MenuItem("Remote Learning");
            remote_learning.setOnAction(event -> {
                change_scene_animation(new scenes().remote_learning());
            });
            MenuItem stdp = new MenuItem("Student Privacy Data");
            stdp.setOnAction(event -> {
                change_scene_animation(new scenes().stdp_page());
            });
            MenuItem ctogcse = new MenuItem("Changes to GCSE");
            ctogcse.setOnAction(event -> {
                change_scene_animation(new scenes().school_ctoGcse_page());
            });
            MenuItem frs = new MenuItem("Free School Meals");
            frs.setOnAction(event -> {
                change_scene_animation(new scenes().frs_page());
            });
            pg_info.getItems().addAll(remote_learning, stdp, ctogcse, frs);
            parent_guardian.getItems().addAll(a8p8, pg_info);
            return parent_guardian;
        }
        private Menu sixth_form() {
            Menu sixtform = new Menu("Sixth Form (16+)");
            MenuItem s_aboutus = new MenuItem("About Us");
            s_aboutus.setOnAction(event -> {
                change_scene_animation(new scenes().sixthform_aboutus_scene());
            });
            MenuItem bursary_policy = new MenuItem("Bursary Policy");
            bursary_policy.setOnAction(event -> {
                change_scene_animation(new scenes().bursary_policy());
            });
            MenuItem football = new MenuItem("FootBall Academy");
            football.setOnAction(e -> {
                change_scene_animation(new scenes().football_academy());
            });
            MenuItem gcepreparation = new MenuItem("GCE Preparation (Y11)");
            gcepreparation.setOnAction(e -> {
                change_scene_animation(new scenes().gce_preparation());
            });
            MenuItem s_curriculum = new MenuItem("Curriculum");
            s_curriculum.setOnAction(event -> {
                change_scene_animation(new scenes().sixthform_curriculum_page());
            });
            MenuItem dress_code = new MenuItem("Dress Code");
            dress_code.setOnAction(event -> {
                change_scene_animation(new scenes().sixthform_dresscode_page());
            });
            MenuItem s_contactus = new MenuItem("Contact Us");
            s_contactus.setOnAction(e -> {
                change_scene_animation(new scenes().sixtform_contactus_page());
            });
            sixtform.getItems().addAll(s_aboutus, bursary_policy, gcepreparation, football, s_curriculum, dress_code, s_contactus);
            return sixtform;
        }

        private Menu pupil_menu() {
            Menu pupil = new Menu("Pupil");
            MenuItem y8_preferences = new MenuItem("Year 8 Preferences");
            y8_preferences.setOnAction(e -> {
                change_scene_animation(new scenes().y8_prefs());
            });
            MenuItem p_stdp = new MenuItem("Student Data Privacy");
            p_stdp.setOnAction(e -> {
                change_scene_animation(new scenes().stdp_page());
            });
            MenuItem extra_curricular = new MenuItem("Extra Curricular");
            extra_curricular.setOnAction(e -> {
                change_scene_animation(new scenes().pupil_extcuri_page());
            });
            MenuItem leadership = new MenuItem("Leadership");
            leadership.setOnAction(e -> {
                change_scene_animation(new scenes().pupil_leadership_scene());
            });
            MenuItem equipment = new MenuItem("Uniform & Equipment");
            equipment.setOnAction(e -> {

                change_scene_animation(new scenes().unif_equipment_page());
            });
            MenuItem library = new MenuItem("Library");
            library.setOnAction(event -> {
                change_scene_animation(new scenes().pupil_library_page());
            });
            MenuItem help = new MenuItem("Help");
            help.setOnAction(event -> {
                change_scene_animation(new scenes().pupil_help_advice_page());
            });
            pupil.getItems().addAll(y8_preferences, p_stdp, extra_curricular,
                    leadership, equipment, library, help);
            return pupil;
        }

        private Menu housecup() {
            Menu house_cup = new Menu("House Cup");
            Menu all_houses = new Menu("House cup");
            MenuItem Dragon = new MenuItem("Dragon", new ImageView("/images/dragon_logo.png"));
            Dragon.setOnAction(event -> {
                change_scene_animation(new scenes().dragon_page());
            });
            MenuItem phoenix = new MenuItem("Phoenix", new ImageView("/images/pheonix.jpg"));
            phoenix.setOnAction(event -> {
                change_scene_animation(new scenes().phoenix_page());
            });
            MenuItem griffin = new MenuItem("Griffin", new ImageView("/images/griffin.png"));
            griffin.setOnAction(e -> {
                change_scene_animation(new scenes().griffin_page());
            });
            MenuItem centaur = new MenuItem("Centaur", new ImageView("/images/centaur_logo.png"));
            centaur.setOnAction(event -> {
                change_scene_animation(new scenes().centaur_page());
            });
            MenuItem pegaus = new MenuItem("Pegasus", new ImageView("/images/pegasus_log.png"));
            pegaus.setOnAction(e -> {
                change_scene_animation(new scenes().pegasus_page());
            });
            MenuItem h_points = new MenuItem("House Points", new ImageView("/images/housepoints.png"));
            h_points.setOnAction(event -> {
                change_scene_animation(new scenes().hpoints_page());

            });
            all_houses.getItems().addAll(Dragon, phoenix, griffin, centaur, pegaus, h_points);
            MenuItem other_eve = new MenuItem("Upcoming Events");
            other_eve.setOnAction(event -> {
                change_scene_animation(new scenes().housecup_otherev_page());
            });

            house_cup.getItems().addAll(all_houses, other_eve);
            return house_cup;
        }

        private Menu school_menu() {
            Menu school = new Menu("School");
          /*  MenuItem theprinciple = new MenuItem("The Principle");
            theprinciple.setOnAction(e -> {
                change_scene_animation(new scenes().school_principle_page());
            });

           */
            MenuItem royal_visit = new MenuItem("Royal Visit");
            royal_visit.setOnAction(e -> {
                change_scene_animation(new scenes().thequeensscene());
            });
            MenuItem srs_values = new MenuItem("SRS Values");
            srs_values.setOnAction(e -> {
                change_scene_animation(new scenes().school_values_page());
            });
            MenuItem alumni = new MenuItem("Alumni");
            alumni.setOnAction(e -> {
                change_scene_animation(new scenes().school_alumni());
            });
            MenuItem catchup = new MenuItem("Catch Up Premium Grant");
            catchup.setOnAction(e -> {
                change_scene_animation(new scenes().catch_up_premium());
            });
            Menu school_curriculum = new Menu("Curriculum");
            MenuItem subjectinfo = new MenuItem("Curriclum OverView");
            subjectinfo.setOnAction(event -> {
                change_scene_animation(new scenes().school_curriclum_overView());
            });
            MenuItem school_assements = new MenuItem("Assessments");
            school_assements.setOnAction(event -> {
                change_scene_animation(new scenes().school_asses_page());
            });
            MenuItem ctogcse = new MenuItem("Changes to GCSE");
            ctogcse.setOnAction(event -> {
                change_scene_animation(new scenes().school_ctoGcse_page());
            });
            MenuItem grouping = new MenuItem("Grouping");
            grouping.setOnAction(event -> {
                change_scene_animation(new scenes().school_grouping_page());
            });
            school_curriculum.getItems().addAll(subjectinfo, school_assements, ctogcse, grouping);
            Menu secondary_ofstedResult = new Menu("Result & OFSTED");
            MenuItem s_result = new MenuItem("Result");
            s_result.setOnAction(event -> {
                change_scene_animation(new scenes().school_result_page());
            });
            MenuItem s_ofsted = new MenuItem("OFSTED");
            s_ofsted.setOnAction(event -> {
                change_scene_animation(new scenes().school_ofsted_page());
            });
            secondary_ofstedResult.getItems().addAll(s_result, s_ofsted);
            MenuItem s_admission = new MenuItem("Admission");
            s_admission.setOnAction(event -> {
                change_scene_animation(new scenes().school_admission_page());
            });
            school.getItems().addAll( royal_visit, srs_values, catchup, alumni, school_curriculum,
                    secondary_ofstedResult, s_admission);
            return school;
        }
    }

    public MigPane main_page() {
        try {

            MigPane main_page = new MigPane();
            //Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"Updates Found \" with title \"Title\"" });
            main_page.setId("Home");
            window.setTitle("The Sydney Russell School - Home");
            new Common_features_for_all().title(main_page);
          //  main_page.setPadding(new Insets(1,5,3,5));
            main_page.setId("Home");
            main_page.add(new menubar().main_menubar(), "wrap");
            Label this_label = new Label("OPEN");
            Text this_text = new Text(String.format("School Status: The Sydney Russell School is %s",this_label.getText().toString()));
            this_text.setStyle("-fx-fill:green;"+"-fx-underline:true;"+"-fx-underline-color:green;"+"-fx-font-size:18px;"+"-fx-font-weight:bold;");
            main_page.add(this_text,"wrap");
            HBox image_and_news_box = new HBox();
            image_and_news_box.prefWidthProperty().bind(window.maxWidthProperty().subtract(20));
            image_and_news_box.maxWidthProperty().bind(image_and_news_box.prefWidthProperty());
            image_and_news_box.setSpacing(5);
            image_and_news_box.setPadding(new Insets(2, 0, 0, 0));
            VBox box = new VBox();
            box.getChildren().addAll( newsd(),imageslideshow());
            image_and_news_box.getChildren().addAll(box,h_points_bar_chart());
            main_page.add(image_and_news_box, "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(main_page, "/TextFiles_for_school/Home.txt");

            return main_page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return main_page();
    }
    public void screen_size(Stage window) {
        screen = Screen.getPrimary().getBounds();
        window.minWidthProperty().set(screen.getWidth() / 2);
        window.maxWidthProperty().set(screen.getWidth());
        window.setWidth(screen.getWidth());
        System.out.println(screen.getWidth());
        window.widthProperty().isEqualTo((int) screen.getWidth());
        //height
        window.setHeight(screen.getHeight());
        window.setWidth(screen.getWidth());
        window.minHeightProperty().set(screen.getHeight() / 2);
        window.maxHeightProperty().set(screen.getHeight());
        System.out.println(window.getHeight());
    }

    public void start(Stage window) throws IOException {
        try {
            this.window = window;


           screen_size(window);
            alllayouts = new StackPane();
            alllayouts.getChildren().add(0, main_page());
            common_features_for_all_var = new Common_features_for_all();
            System.out.println(alllayouts.getChildren().get(0));
            MigPane this_pane = (MigPane)alllayouts.getChildren().get(0);
            this_pane.prefWidthProperty().set(screen.getWidth());
            main_scene = new Scene(common_features_for_all_var.scrollbar_for_node((Node) alllayouts.getChildren().get(0).getParent(), true, false));
            main_scene.getStylesheets().addAll(apptry1css);
            window.setScene(main_scene);
            new Common_features_for_all().close_window(main_scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public Button home_button(){
		try{
			Button to_home = new Button();
			ImageView this_image  = new ImageView("/Images_required_in_certain_places/syd_home_button.png");
			double eight_percent_of_height = (window.getHeight()/100)*8.5;
			this_image.fitHeightProperty().set(eight_percent_of_height);
			to_home.setStyle("-fx-background-color:transparent,transparent;");
			to_home.setGraphic(this_image);
			to_home.setOnAction(e->{
				try {
				      if(to_home.getScene().getRoot().getId()!="mailbox_window"){
                          start(window);// if the home button is not called from mailbox, then go to home screen named as main_page()
                      }else{
				          window.toFront();// if the button is called from mailbox, then bring this app_window with current scene to fron.
                      }
					}catch (Exception failed){
					failed.printStackTrace();
				}
			});
			return to_home;
		}catch (Exception e){
			e.printStackTrace();

		}
		return home_button();
	}


    //
    private VBox newsd() {
        newsboxmostimportant d = new newsboxmostimportant();
        VBox newsslideshow = new VBox();
        newsslideshow.setMaxHeight(80);
        newsslideshow.getStyleClass().add("news-slideshow");
        newsslideshow.getChildren().addAll(d.newsarea());
        return newsslideshow;
    }
    public TextField selectable_label(TextField your_textfield, String font_size, String font_family) {//allows you to create a label that can be selected
           if((your_textfield instanceof TextField)){
               your_textfield.setEditable(false);//make it uneditable
               your_textfield.setStyle("-fx-background-color:transparent,transparent;" + "-fx-fill:rgb(0,0,0);" + String.format("-fx-font-size:%s;", Integer.parseInt(font_size)) +
                       "-fx-highlight-fill:rgb(0,0,0);" + String.format("-fx-font-family:%s;", font_family));
           }
        return your_textfield;
    }
    // 		Main House Points Bar Chart
    private VBox h_points_bar_chart() {// house points bar chart
        house_points house_pointz = new house_points();
        VBox points_box = new VBox();
        VBox this_box =new VBox();
        this_box.getChildren().add(house_pointz.main());
        points_box.getChildren().add(this_box);
        window.widthProperty().addListener((old,this_,new_one)->{
            if((new_one.intValue()<1000)) {
                points_box.getChildren().remove(this_box);
            }
            else{
                if(!(points_box.getChildren().contains(this_box))){
                    points_box.getChildren().add(this_box);
                }
            }
        });
        return points_box;
    }
    ////           slideshow
    private VBox linktoscene() {

        VBox linkbox = new VBox();
        linkbox.prefWidthProperty().set(250);
        linkbox.maxWidthProperty().set(250);
        linkbox.minWidthProperty().set(250);
        linkbox.getStyleClass().add("useful-links");
        linkbox.setSpacing(5);
        TextField usefultitle = new TextField("Links To Follow: ");
        usefultitle.minWidthProperty().bind(linkbox.minWidthProperty());
        selectable_label(usefultitle, "17", "\"Times New Roman\", Times, serif");
        Hyperlink queenhyper = new Hyperlink("Royal Visit");
        queenhyper.setOnAction(e -> {
            change_scene_animation(new scenes().thequeensscene());
        });
        queenhyper.wrapTextProperty().set(true);
        Hyperlink ctogcsehyper = new Hyperlink("Changes to GCSE");
        ctogcsehyper.setOnAction(e -> {
            change_scene_animation(new scenes().school_ctoGcse_page());
        });
        ctogcsehyper.wrapTextProperty().set(true);
        Hyperlink sresulthyper = new Hyperlink("GCSE result");
        sresulthyper.setOnAction(e -> {
            change_scene_animation(new scenes().school_result_page());
        });
        sresulthyper.wrapTextProperty().set(true);
        Hyperlink ofstedhyper = new Hyperlink("Ofsted Report");
        ofstedhyper.setOnAction(e -> {
            change_scene_animation(new scenes().school_ofsted_page());
        });
        ofstedhyper.wrapTextProperty().set(true);
        Hyperlink stdphyper = new Hyperlink("Student Data Privacy");
        stdphyper.setOnAction(e -> {
            System.out.println("put the stdp page  back");
            change_scene_animation(new scenes().stdp_page());
        });
        stdphyper.wrapTextProperty().set(true);

        Hyperlink catchup_hyper = new Hyperlink("Catch Up Premium Grant");
        catchup_hyper.setOnAction(e -> {
            change_scene_animation(new scenes().catch_up_premium());
        });
        catchup_hyper.wrapTextProperty().set(true);
        Hyperlink upcomingevents = new Hyperlink("Upcoming Events");
        upcomingevents.setOnMouseClicked(e -> {
            change_scene_animation(new scenes().housecup_otherev_page());
        });
        upcomingevents.wrapTextProperty().set(true);
        Button open_app = new Button("Open Mail App");
        open_app.setWrapText(true);
        open_app.setOnAction(e -> {
            try {
                mailme();
            } catch (Exception failed) {
                failed.printStackTrace();
            }
        });
        Button to_downloadable = new Button("All Downloadable Files.");
        to_downloadable.setOnAction(e->{
           change_scene_animation(new scenes().all_downloadables());
        });
        linkbox.getChildren().addAll(usefultitle, new Separator(), open_app, new Separator(), to_downloadable,new Separator(),queenhyper, new Separator(), sresulthyper, new Separator(), ofstedhyper,
                new Separator(), stdphyper, new Separator(), catchup_hyper, new Separator(), upcomingevents, new Separator(), ctogcsehyper);
        return linkbox;
    }
    private HBox imageslideshow() {
        image_slideshow_box = new HBox();
        image_slideshow_box.getStyleClass().add("image-slideshow");
        imagesme d = new imagesme();
        image_slideshow_box.getChildren().add(d.main_slideshow());
        return image_slideshow_box;
    }
    public void mailme() {// to call the mail_window
        try {
            Task call_main_window = new Task<Void>() {
                protected Void call() {
                    try {
                        Platform.runLater(new Runnable() {
                            public void run() {
                                mailbox mail_window = new mailbox();//calls the window app on user_request
                                mail_window.run(window);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            Thread mail_window_thread = new Thread(call_main_window);
            mail_window_thread.setName("mail_window_thread");
            mail_window_thread.setDaemon(true);
            mail_window_thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private HBox general_layout_for_any_image(String path_to_image, String image_text, String height_of_image) {
        Common_features_for_all bring_out_image = new Common_features_for_all();
        HBox for_image = (HBox) bring_out_image.general_layout_for_image(path_to_image, image_text, height_of_image);
        return for_image;
    }
    public void change_scene_animation(MigPane pane) {//changes scene with animation
        Task<Void> change_scene = new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            var current = alllayouts.getChildren().get(0);
                            var next = pane;
                            pane.setStyle("-fx-background-color:black;");
                            next.add(new menubar().main_menubar(), " dock north,wrap");
                            pane.prefWidthProperty().set(screen.getWidth()-20);
                            next.translateXProperty().set(alllayouts.getWidth());
                            next.setPadding(new Insets(1,5,1,5));
                            var kv = new KeyValue(next.translateXProperty(), 0, Interpolator.EASE_IN);
                            var kf = new KeyFrame(Duration.millis(1500), kv);
                            Timeline t = new Timeline(kf);
                            alllayouts.getChildren().add(next);
                            next.getStylesheets().add(apptry1css);
                            t.setOnFinished(e -> {
                                alllayouts.getChildren().remove(current);
                                pane.setStyle("-fx-control-inner-background-color:linear-gradient(to left,rgb(255,255,255)99.5%,rgb(0,153,0) 0.5%);" +
                                        "-fx-background-color:-fx-control-inner-background-color;");
                            });
                            t.play();
                            window.setTitle("The Sydney Russell School - " + pane.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return null;
            }
        };
        Thread for_changing_scene = new Thread(change_scene);
        for_changing_scene.setName("Thread used in: \"private void change_scene_animation(MigPane pane)\"");
        for_changing_scene.setDaemon(true);
        for_changing_scene.start();
    }
    public ArrayList<MenuItem> toWebs() {
			/*                     ->>>>>>> Links to essentials sites->>>>>>>
		  https://www.youtube.com/results?search_query=srs+science"
		  https://www.instagram.com/srs_pe/
		  https://www.facebook.com/SydneyRussellSchoolOfficial/?rf=193364027399672
		  https://twitter.com/sydneyr_school?lang=en
		  https://www.youtube.com/channel/UCcXOH_xmQwoklJMrjQ6DERQ
		  http://www.sydneyrussellschool.com/
			 */
        internet_connectivity run_web = new internet_connectivity();
        schoolweb = new MenuItem("School Website", new ImageView("/images/syd_logo.png"));
        schoolweb.setId("School Website");
        schoolweb.setOnAction(e -> {
            try {
                run_web.website_url = "http://www.sydneyrussellschool.com/";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();
            } catch (Exception f) {
                f.printStackTrace();
            }
        });
        sydtv = new MenuItem("SYD TV", new ImageView("/images/sydtv_logo.png"));
        sydtv.setId("SYD TV");
        sydtv.setOnAction(e -> {
            try {
                run_web.website_url = "https://www.youtube.com/channel/UCcXOH_xmQwoklJMrjQ6DERQ";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();
            } catch (Exception f) {
                f.printStackTrace();
            }
        });
        srstwitter = new MenuItem("Twitter", new ImageView("/images/twitter_icon.png"));
        srstwitter.setId("Twitter");
        srstwitter.setOnAction(e -> {
            try {

                run_web.website_url = "https://twitter.com/sydneyr_school?lang=en";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();

            } catch (Exception h) {
            }
        });
        srsfacebook = new MenuItem("Facebook", new ImageView("/images/facebook_logo.png"));
        srsfacebook.setId("Facebook");
        srsfacebook.setOnAction(e -> {
            try {
                run_web.website_url = "https://www.facebook.com/SydneyRussellSchoolOfficial/?rf=193364027399672";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();
            } catch (Exception f) {
                f.printStackTrace();
            }

        });
        srs_science = new MenuItem("SRS Science", new ImageView("/images/srsscience.png"));
        srs_science.setId("SRS Science");
        srs_science.setOnAction(e -> {
            try {
                run_web.website_url = "https://www.youtube.com/channel/UCtaC1k16u0fokRExt9rKo9g";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();

            } catch (Exception j) {
                j.printStackTrace();
            }
        });
        ///
        srspe = new MenuItem("SRS PE", new ImageView("/images/insta_icon.png"));
        srspe.setId("SRS PE");
        srspe.setOnAction(e -> {
            try {
                run_web.website_url = "https://www.instagram.com/srs_pe/";
                run_web.message = "This web page cannot be displayed because your computer is offline.";
                run_web.run_to_web = true;
                run_web.start_thread();
            } catch (Exception h) {
                h.printStackTrace();
            }
        });
        ArrayList<MenuItem> all_news_menuItems = new ArrayList<>();
        all_news_menuItems.add(schoolweb);
        all_news_menuItems.add(sydtv);
        all_news_menuItems.add(srs_science);
        all_news_menuItems.add(srspe);
        all_news_menuItems.add(srstwitter);
        all_news_menuItems.add(srsfacebook);
        return all_news_menuItems;
    }
    private void saved_and_open_file(String url, String your_file_name) {
        Common_features_for_all open_file = new Common_features_for_all();
        open_file.save_file_from_internet(url, your_file_name);
    }
    public class scenes {
        private MigPane all_downloadables(){
            MigPane main_layout = new MigPane();
            main_layout.setId("All Downloadable Files");
            new Common_features_for_all().title(main_layout);
            VBox download_box =new VBox();
            download_box.maxWidthProperty().bind(window.maxWidthProperty().subtract(20));
            download_box.prefWidthProperty().bind(download_box.maxWidthProperty().subtract(20));
            download_box.setId("all_downloadable");
            download_box.setPadding(new Insets(5,10,5,10));
            download_box.setSpacing(10);
            Text privacy_pdf = new Text("Student Data Privacy.");
            Button open_file = new Button("Open and Download SRS-Privacy-Notice-Students");
            open_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/11/SRS-Privacy-Notice-Students.pdf", "SRS-Privacy-Notice-Students.pdf");
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            download_box.getChildren().addAll(privacy_pdf,open_file);
            Text british_values = new Text("British Values");
            Button open_british_values_file = new Button("Download and Open Promoting-British-Values");
            open_british_values_file.setOnAction(event -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/01/Promoting-British-Values.pdf", "thisss.pdf");
            });
            download_box.getChildren().addAll(british_values,open_british_values_file);
            Text catch_up_premium = new Text("Covid-19 Catch up Scheme");
            Button download_file = new Button("Download Catch-Up Premium Funding Statement");
            download_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2021/02/Catch-Up-Premium-Funding-Statement.pdf", "Catch-Up-Premium-Funding-Statement.pdf");
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            download_box.getChildren().addAll(catch_up_premium,download_file);
            Text y8prefs = new Text("Year 8 Preferences");
            Button fast_track = new Button("Download and Open Fast Track Preferences");
            fast_track.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-FT.pdf", "Fast Track Preferences.pdf");
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
            Button upper_band = new Button("Download and Open Upper Band Preferences");
            upper_band.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-UB.pdf", "Upper Band Preferences.pdf");
                } catch (Exception f) {

                }
            });
            Button middle_band = new Button("Download and Open Middle Band Preferences");
            middle_band.setOnAction(e -> {
                try {
                    //http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-MB.pdf
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-MB.pdf", "Middle Band Preferences.pdf");
                } catch (Exception f) {

                }
            });
            download_box.getChildren().addAll(y8prefs,fast_track,upper_band,middle_band);
            Text for_parents_and_students = new Text("For Parents and Students (includes School Day Structure)");
            Button open_lbbd_file = new Button("Download and Open LBBD Prevent Parent Guide");
            open_lbbd_file.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/11/LBBD-Parent-Guide.pdf", "LBBD-Parent-Guide.pdf");
            });
            Button useful_contacts = new Button("Download and Open USEFUL CONTACTS FOR YOUNG PEOPLE");
            useful_contacts.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2021/01/USEFUL-CONTACTS-FOR-YOUNG-PEOPLE.pdf", "USEFUL-CONTACTS-FOR-YOUNG-PEOPLE.pdf");
            });
            Button open_school_file = new Button("Download and Open School Day Structure");
            open_school_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/10/School-Day-Structure.jpg", "School Day Structure.jpg");
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
            Button open_asses_file = new Button("Download and Open Assessment Strategy");
            open_asses_file.setOnAction(e -> {
                saved_and_open_file("http://sydneyrussellschool.com/wp-content/uploads/2021/02/Assessment-Strategy-2020-21-Secondary.pdf", "Assessment Strategy.pdf");
            });
            download_box.getChildren().addAll(for_parents_and_students,open_asses_file,open_school_file,open_lbbd_file,useful_contacts);
            Text school_curriculum = new Text("Curriculum for All years");
            Button y7 = new Button(" Download and Open SRS-Yr-7-Curiculum-Overview");
            y7.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-7-Curiculum-Overview.pdf", "SRS-Yr-7-Curiculum-Overview.pdf");
            });
            Button y8 = new Button("Download and Open SRS-Yr8-Curiculum-Overview");
            y8.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/09/SRSYr8CuriculumOverview.pdf", "SRSYr8CuriculumOverview.pdf");
            });
            Button y9 = new Button("Download and Open SRS-Yr-9-Curiculum-Overview");
            y9.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-9-Curiculum-Overview.pdf", "SRS-Yr-9-Curiculum-Overview.pdf");
            });
            Button y10 = new Button("Downlaod and Open SRS-Yr-10-Curiculum-Overview");
            y10.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-10-Curiculum-Overview.pdf", "SRS-Yr-10-Curiculum-Overview.pdf");
            });
            Button y11 = new Button("Download and Open SRS-Yr-11-Curiculum-Overview");
            y11.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-11-Curiculum-Overview.pdf", "SRS-Yr-11-Curiculum-Overview.pdf");
            });
            Button y12 = new Button("Download and Open SRS-16-Yr-12-Curriculum-Overview");
            y12.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-16-Yr-12-Curriculum-Overview.pdf", "SRS-16-Yr-12-Curriculum-Overview.pdf");
            });
            Button y13 = new Button("Download and Open SRS-16-Yr-13-Curriculum-Overview");
            y13.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-16-Yr-13-Curriculum-Overview.pdf", "SRS-16-Yr-13-Curriculum-Overview.pdf");
            });
            download_box.getChildren().addAll(school_curriculum,y7,y8,y9,y10,y11,y12,y13);
            Text ofsted_title = new Text("Ofsted Reports");
            Button full_ofsted = new Button("Download and Open Full Ofsted Report");
            full_ofsted.setOnAction(e -> {
                saved_and_open_file("http://sydneyrussellschool.com/wp-content/uploads/2013/09/Ofsted-Report.pdf", "Full Ofsted Report.pdf");
            });
            Button ofsted_monitoring_report = new Button("Download and Open Ofsted Monitoring Report");
            ofsted_monitoring_report.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2013/05/The-Sydney-Russell-School-10040361.pdf", "Ofsted Monitoring Report.pdf");
            });
           download_box.getChildren().addAll(ofsted_title,full_ofsted,ofsted_monitoring_report);
            Text dresscode_text = new Text("SixthForm Dresscode");
            Button open_dresscode_file = new Button("Download and Open Sxith Form Dress Code");
            open_dresscode_file.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/11/Dress-Code-16-SRS-2020.pdf", "Dress-Code-16-SRS-2020.pdf");
            });
            download_box.getChildren().addAll(dresscode_text,open_dresscode_file);
            main_layout.add(download_box,"wrap");
            return main_layout;
        }
        private void contactSchool(Menu menu) {
            MenuItem srsphone = new MenuItem("Tel: 0203 959 9900");//, new ImageView("/images/phone_icon.png"));
            srsphone.setOnAction(e -> {
                ///                          srs phone

            });
            MenuItem srsemail = new MenuItem(("Email: office@sydneyrussellschool.com"));//, new ImageView("/images/mail_icon.png"));
            srsemail.setOnAction(e -> {
                //////                  srs email
              //  mailme();// opens email app
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert this_alert = new Alert(Alert.AlertType.CONFIRMATION);
                            this_alert.setHeaderText(null);
                            this_alert.setTitle("To Mail");
                            this_alert.setContentText("Open Sydney Russell School or OS Mail?");
                            ButtonType own_ = new ButtonType("Sydney Russell School Mail (Recommended)");
                            ButtonType os = new ButtonType("Mail (Standard)");
                            this_alert.getButtonTypes().remove(0);
                            this_alert.getButtonTypes().addAll(os,own_);
                            Optional<ButtonType> all_inputs = this_alert.showAndWait();
                            if(all_inputs.get()==own_){
                                mailme();
                            }else if(all_inputs.get()==os){
                                try{
                                    Desktop.getDesktop().open(new File("/Users/zeeshankhan/Documents/Mail.app"));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
            });
            menu.getItems().addAll(srsphone, srsemail);
        }
        // Parent/guardian
        private MigPane a8p8_page() {//a8p8_page == Attainment 8 & Progress 8 // check
            MigPane at8pt8_layout = new MigPane();
            at8pt8_layout.setId("Attainment 8 & Progress 8");
            new Common_features_for_all().title(at8pt8_layout);
            //layout_with_image(at8pt8_layout);
            data_to_read = new read_file_text();
            // 						Add text files to this
            //data_to_read.reading_text(at8pt8_layout, "Textfiles_for_pupil/attaintment8&progress8");
            ;
            //
            return at8pt8_layout;

        }
        private MigPane remote_learning() {//Internet Support
            MigPane remote_learning_layout = new MigPane();
            remote_learning_layout.setId("Remote Learning");
            new Common_features_for_all().title(remote_learning_layout);
            data_to_read = new read_file_text();
            //data_to_read.noi("http://www.sydneyrussellschool.com/?page_id=8026","div.gdl-page-content","p","src/Textfiles_for_sixth_form/remote_learning");
            data_to_read.reading_text(remote_learning_layout, "/Textfiles_for_sixth_form/remote_learning");
            Button read_more = new Button("Download and Open Remote Learning Strategy Specific Guidance for Students");
            read_more.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/09/Appendix-D1-Remote-Learning-Strategy-Specific-guidance-for-students-...pdf", "Appendix-D1-Remote-Learning-Strategy-Specific-guidance-for-students.pdf");
            });
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            button_box.getChildren().addAll(read_more);
            remote_learning_layout.add(button_box, "wrap");
            return remote_learning_layout;
        }

        private MigPane frs_page() {//free school meals
            MigPane frs_layout = new MigPane();
            frs_layout.setId("Free School Meals");
            new Common_features_for_all().title(frs_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(frs_layout, "/Textfiles_for_sixth_form/Free School Meals (normal)");
            return frs_layout;
        }
        ///               sixth Form
        private MigPane sixthform_aboutus_scene() {
            MigPane s_aboutus_layout = new MigPane();
            s_aboutus_layout.setId("SixthForm - About Us");
            new Common_features_for_all().title(s_aboutus_layout);
            s_aboutus_layout.add(general_layout_for_any_image("/images/sixth-form.jpg", null, "250"), "wrap");
            data_to_read = new read_file_text();
            //data_to_read.noi("http://www.sydneyrussellschool.com/?page_id=6401","div.gdl-page-content","p, br","src/Textfiles_for_sixth_form/AboutUs");
            data_to_read.reading_text(s_aboutus_layout, "/Textfiles_for_sixth_form/AboutUs");
            return s_aboutus_layout;
        }
        private MigPane gce_preparation() {
            MigPane gce_prep_layout = new MigPane();
            gce_prep_layout.setId("GCE Preparation (Y11)");
            new Common_features_for_all().title(gce_prep_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(gce_prep_layout, "/Textfiles_for_sixth_form/Alevel_preparation");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Download and Open SRS Sixth Form – A Level Preparation");
            open_file.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/06/GCE-Prep-Yr-11-new.docx", "SRS Sixth Form – A Level Preparation.docx");
            });
            button_box.getChildren().addAll(open_file);
            gce_prep_layout.add(button_box, "wrap");
            return gce_prep_layout;
        }

        private MigPane football_academy() {
            MigPane football_layout = new MigPane();
            football_layout.setId("FootBall Academy");
            new Common_features_for_all().title(football_layout);
            football_layout.add(general_layout_for_any_image("/images/football(0).jpg", null, "300"), "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(football_layout, "/Textfiles_for_sixth_form/football_academy");
            football_layout.add(general_layout_for_any_image("/images/football(1).jpg", null, "300"), "wrap");
            return football_layout;
        }
        private MigPane bursary_policy() {
            MigPane bursary_layout = new MigPane();
            bursary_layout.setId("Bursary Policy");
            new Common_features_for_all().title(bursary_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(bursary_layout, "/Textfiles_for_sixth_form/bursary_policy");
            return bursary_layout;
        }
        private MigPane sixthform_curriculum_page() {
            MigPane s_curriculum = new MigPane();
            s_curriculum.setId("SixthForm - Curriculum");
            new Common_features_for_all().title(s_curriculum);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_curriculum, "/Textfiles_for_sixth_form/Curriculum");
            HBox alevel_entry = new HBox();
            alevel_entry.setSpacing(10);
            alevel_entry.setPadding(new Insets(2));
            Button alevel = new Button("Download and Open A-Level-Entry-Criteria-2021");
            alevel.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/11/A-Level-Entry-Criteria-2021.pdf", "A-Level-Entry-Criteria-2021.pdf");
            });
            Button curriculum = new Button("Download and Open Curriculum-Statement");
            curriculum.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/01/Our-Curriculum-Statement.pdf", "Our-Curriculum-Statement.pdf");
            });
            alevel_entry.getChildren().addAll(alevel, curriculum);
            s_curriculum.add(alevel_entry, "wrap");
            return s_curriculum;
        }
        private MigPane sixtform_contactus_page() {
            MigPane s_contactus_layout = new MigPane();
            s_contactus_layout.setId("SixthForm - Contact Us");
            new Common_features_for_all().title(s_contactus_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_contactus_layout, "/Textfiles_for_sixth_form/ContactUs");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_mail = new Button("Open Mail");
            open_mail.setOnAction(e -> {
                try {
                    mailme();
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            button_box.getChildren().addAll(open_mail);
            s_contactus_layout.add(button_box, "wrap");
            return s_contactus_layout;
        }
        private MigPane sixthform_dresscode_page() {
            MigPane s_dresscode_layout = new MigPane();
            s_dresscode_layout.setId("SixthForm - Dress Code");
            new Common_features_for_all().title(s_dresscode_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_dresscode_layout, "/Textfiles_for_sixth_form/DressCode");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Download and Open Sxith Form Dress Code");
            open_file.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/11/Dress-Code-16-SRS-2020.pdf", "Dress-Code-16-SRS-2020.pdf");
            });
            button_box.getChildren().addAll(open_file);
            s_dresscode_layout.add(button_box, "wrap");
            return s_dresscode_layout;
        }
        //                    pupil
        private MigPane y8_prefs() {
            MigPane y8_pref_layout = new MigPane();
            y8_pref_layout.setId("Y8 Preferences");
            new Common_features_for_all().title(y8_pref_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(y8_pref_layout, "/Textfiles_for_pupil/y8 preferences");
            Button fast_track = new Button("Download and Open Fast Track Preferences");
            fast_track.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-FT.pdf", "Fast Track Preferences.pdf");
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
            Button upper_band = new Button("Download and Open Upper Band Preferences");
            upper_band.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-UB.pdf", "Upper Band Preferences.pdf");
                } catch (Exception f) {

                }
            });

            Button middle_band = new Button("Download and Open Middle Band Preferences");
            middle_band.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2018/02/Pref18-MB.pdf", "Middle Band Preferences.pdf");

                } catch (Exception f) {

                }
            });
            HBox button_box = new HBox();
            button_box.setSpacing(10);
            button_box.setPadding(new Insets(2));
            button_box.getChildren().addAll(fast_track, upper_band, middle_band);
            y8_pref_layout.add(button_box, "wrap");
            return y8_pref_layout;
        }
        private MigPane pupil_extcuri_page() {
            MigPane p_extrac_layout = new MigPane();
            p_extrac_layout.setId("Extra Curricular Activities");
            new Common_features_for_all().title(p_extrac_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(p_extrac_layout, "/Textfiles_for_pupil/EXTRA_CURRICULAR_ACTIVITIES");
            return p_extrac_layout;
        }
        private MigPane pupil_leadership_scene() {
            MigPane p_leadership_layout = new MigPane();
            p_leadership_layout.setId("Leadership");
            new Common_features_for_all().title(p_leadership_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(p_leadership_layout, "/Textfiles_for_pupil/Leadership");
            return p_leadership_layout;
        }
        private MigPane pupil_library_page() {
            MigPane p_library_layout = new MigPane();
            p_library_layout.setId("Pupil - Library");
            new Common_features_for_all().title(p_library_layout);
            data_to_read = new read_file_text();
            //data_to_read.noi("http://www.sydneyrussellschool.com/?page_id=4687","div.gdl-page-content","p","src/Textfiles_for_pupil/Library");
            data_to_read.reading_text(p_library_layout, "/Textfiles_for_pupil/Library");
            //	layout_with_image(p_library_layout);
            return p_library_layout;
        }
        private MigPane pupil_help_advice_page() {
            MigPane p_help_layout = new MigPane();
            p_help_layout.setId("Help & Advice");
            new Common_features_for_all().title(p_help_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(p_help_layout, "/TextFiles_for_pupil/help_and_advice");
            Button open_file = new Button("Download and Open LBBD Prevent Parent Guide");
            open_file.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/11/LBBD-Parent-Guide.pdf", "LBBD-Parent-Guide.pdf");
            });
            Button useful_contacts = new Button("Download and Open USEFUL CONTACTS FOR YOUNG PEOPLE");
            useful_contacts.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2021/01/USEFUL-CONTACTS-FOR-YOUNG-PEOPLE.pdf", "USEFUL-CONTACTS-FOR-YOUNG-PEOPLE.pdf");
            });
            Button find_out_more = new Button("Click Me to find out more! ");
            find_out_more.setOnAction(e -> {
                try {
                    internet_connectivity run_web = new internet_connectivity();
                    run_web.website_url = "http://www.sydneyrussellschool.com/?page_id=4702";
                    find_out_more.setTooltip(new Tooltip(run_web.website_url));
                    run_web.start_thread();
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
            VBox button_box = new VBox();
            button_box.setSpacing(5);
            button_box.setPadding(new Insets(2));
            button_box.getChildren().addAll(open_file, useful_contacts, find_out_more);
            p_help_layout.add(button_box, "wrap");
            return p_help_layout;
        }
        private MigPane housecup_otherev_page() {
            MigPane h_otherev_layout = new MigPane();
            h_otherev_layout.setId("House Cup - Upcoming Events");
            new Common_features_for_all().title(h_otherev_layout);
            h_otherev_layout.add(general_layout_for_any_image("/images_required_in_certain_places/all_houses.png", null, "120"), "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(h_otherev_layout, "/files_for_house_cup/upcoming_events");
            return h_otherev_layout;
        }
        private MigPane dragon_page() {
            MigPane h_dragon_layout = new MigPane();
            h_dragon_layout.setId("House Cup - Dragon");
            new Common_features_for_all().title(h_dragon_layout);
            TextField our_motto = new TextField("Tough Times Never Last!");
            selectable_label(our_motto, "25", "Brushstroke, fantasy;");
            h_dragon_layout.add(our_motto, "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(h_dragon_layout, "/files_for_house_cup/Dragon");
            return h_dragon_layout;
        }
        private MigPane phoenix_page() {
            MigPane h_phoenix_layout = new MigPane();
            h_phoenix_layout.setId("House Cup - Phoenix");
            new Common_features_for_all().title(h_phoenix_layout);
            //	layout_with_image(h_phoenix_layout);
            //data_to_read.noi("http://www.sydneyrussellschool.com/?page_id=4717","div.gdl-page-content","p,br","src/files_for_house_cup/Phoenix");
            data_to_read.reading_text(h_phoenix_layout, "/files_for_house_cup/Phoenix");
            return h_phoenix_layout;
        }
        private MigPane pegasus_page() {
            MigPane h_pegasus_layout = new MigPane();
            h_pegasus_layout.setId("House Cup - Pegasus");
            new Common_features_for_all().title(h_pegasus_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(h_pegasus_layout, "/files_for_house_cup/Pegasus");
            return h_pegasus_layout;
        }
        private MigPane centaur_page() {
            MigPane h_centaur_layout = new MigPane();
            h_centaur_layout.setId("House Cup - Centaur");
            new Common_features_for_all().title(h_centaur_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(h_centaur_layout, "/files_for_house_cup/Centaur");
            return h_centaur_layout;
        }
        private MigPane griffin_page() {
            MigPane h_griffin_layout = new MigPane();
            h_griffin_layout.setId("House Cup - Griffin");
            new Common_features_for_all().title(h_griffin_layout);
            h_griffin_layout.add(general_layout_for_any_image("/Images_required_in_certain_places/griffin_image.jpg", null, "90"), "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(h_griffin_layout, "/files_for_house_cup/Griffin");
            common_features_for_all_var = new Common_features_for_all();
            return h_griffin_layout;
        }
        private MigPane hpoints_page() {
            MigPane hpoints_layout = new MigPane();
            hpoints_layout.setId("House Points");
            new Common_features_for_all().title(hpoints_layout);
            return hpoints_layout;
        }
        private MigPane school_admission_page() {//continue from here
            MigPane s_admission_layout = new MigPane();
            s_admission_layout.setId("Secondary Admissions");
            new Common_features_for_all().title(s_admission_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_admission_layout, "/TextFiles_for_school/School_admission(not_sixthform)");
            return s_admission_layout;
        }
        private MigPane school_result_page() {
            MigPane s_result_layout = new MigPane();
            s_result_layout.setId("GCSE's Results");
            new Common_features_for_all().title(s_result_layout);
            from_common_features = new Common_features_for_all();
            data_to_read.reading_text(s_result_layout, "/TextFiles_for_school/SchoolResults");
            return s_result_layout;
        }
        private MigPane school_ofsted_page() {
            MigPane s_ofsted_layout = new MigPane();
            s_ofsted_layout.setId("OFSTED");
            new Common_features_for_all().title(s_ofsted_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_ofsted_layout, "/TextFiles_for_school/OFSTED");
            VBox button_box = new VBox();
            button_box.setSpacing(15);
            button_box.setPadding(new Insets(2));
            Button full_ofsted = new Button("Download and Open Full Ofsted Report");
            full_ofsted.setOnAction(e -> {
                saved_and_open_file("http://sydneyrussellschool.com/wp-content/uploads/2013/09/Ofsted-Report.pdf", "Full Ofsted Report.pdf");
            });
            Button ofsted_monitoring_report = new Button("Download and Open Ofsted Monitoring Report");
            ofsted_monitoring_report.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2013/05/The-Sydney-Russell-School-10040361.pdf", "Ofsted Monitoring Report.pdf");
            });
            Button parent_view = new Button("Access to Parent View");
            parent_view.setOnAction(e -> {
                try {
                    internet_connectivity run_web = new internet_connectivity();
                    run_web.website_url = "https://parentview.ofsted.gov.uk/";
                    parent_view.setTooltip(new Tooltip(run_web.website_url));
                    run_web.start_thread();
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
            button_box.getChildren().addAll(full_ofsted, ofsted_monitoring_report, parent_view);
            s_ofsted_layout.add(button_box, "wrap");
            return s_ofsted_layout;
        }
        private MigPane school_grouping_page() {
            MigPane s_grouping_layout = new MigPane();
            s_grouping_layout.setId("Grouping");
            new Common_features_for_all().title(s_grouping_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_grouping_layout, "/TextFiles_for_school/Grouping");
            return s_grouping_layout;
        }
        private MigPane school_asses_page() {
            MigPane s_asses_layout = new MigPane();
            s_asses_layout.setId("Assessment");
            new Common_features_for_all().title(s_asses_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_asses_layout, "/TextFiles_for_school/Assesments");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Download and Open Assessment Strategy");
            open_file.setOnAction(e -> {
                saved_and_open_file("http://sydneyrussellschool.com/wp-content/uploads/2021/02/Assessment-Strategy-2020-21-Secondary.pdf", "Assessment Strategy.pdf");
            });
            button_box.getChildren().add(open_file);
            s_asses_layout.add(button_box, "wrap");
            return s_asses_layout;
        }
        private MigPane school_curriclum_overView() {
            MigPane s_info_layout = new MigPane();
            s_info_layout.setId("Subject Information");
            new Common_features_for_all().title(s_info_layout);
            VBox button_box = new VBox();
            button_box.setSpacing(15);
            button_box.setPadding(new Insets(2));
            Button y7 = new Button(" Download and Open SRS-Yr-7-Curiculum-Overview");
            y7.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-7-Curiculum-Overview.pdf", "SRS-Yr-7-Curiculum-Overview.pdf");
            });
            Button y8 = new Button("Download and Open SRS-Yr8-Curiculum-Overview");
            y8.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/09/SRSYr8CuriculumOverview.pdf", "SRSYr8CuriculumOverview.pdf");
            });
            Button y9 = new Button("Download and Open SRS-Yr-9-Curiculum-Overview");
            y9.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-9-Curiculum-Overview.pdf", "SRS-Yr-9-Curiculum-Overview.pdf");
            });
            Button y10 = new Button("Downlaod and Open SRS-Yr-10-Curiculum-Overview");
            y10.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-10-Curiculum-Overview.pdf", "SRS-Yr-10-Curiculum-Overview.pdf");
            });
            Button y11 = new Button("Download and Open SRS-Yr-11-Curiculum-Overview");
            y11.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-Yr-11-Curiculum-Overview.pdf", "SRS-Yr-11-Curiculum-Overview.pdf");
            });
            Button y12 = new Button("Download and Open SRS-16-Yr-12-Curriculum-Overview");
            y12.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-16-Yr-12-Curriculum-Overview.pdf", "SRS-16-Yr-12-Curriculum-Overview.pdf");
            });
            Button y13 = new Button("Download and Open SRS-16-Yr-13-Curriculum-Overview");
            y13.setOnAction(e -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2020/07/SRS-16-Yr-13-Curriculum-Overview.pdf", "SRS-16-Yr-13-Curriculum-Overview.pdf");
            });
            y13.setWrapText(true);
            button_box.getChildren().addAll(y7, y8, y9, y10, y11, y12, y13);
            button_box.minWidthProperty().bind(window.minWidthProperty());
            button_box.maxWidthProperty().bind(window.maxWidthProperty().subtract(270));
            button_box.prefWidthProperty().bind(window.widthProperty().subtract(270));
            HBox full_box = new HBox();
            full_box.minWidthProperty().bind(window.minWidthProperty());
            full_box.maxWidthProperty().bind(window.maxWidthProperty());
            full_box.prefWidthProperty().bind(window.widthProperty().subtract(50));
            VBox for_extra = new VBox();
            for_extra.setSpacing(10);
            Common_features_for_all d = new Common_features_for_all();
            for_extra.getChildren().addAll(linktoscene());
            full_box.getChildren().addAll(button_box, for_extra);
            s_info_layout.add(full_box, "wrap");
            return s_info_layout;
        }

        private MigPane school_values_page() {
            MigPane s_values_layout = new MigPane();
            s_values_layout.setId("SRS Values");
            new Common_features_for_all().title(s_values_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_values_layout, "/TextFiles_for_school/SRS Values");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Download and Open Promoting-British-Values");
            open_file.setOnAction(event -> {
                saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/01/Promoting-British-Values.pdf", "thisss.pdf");
            });
            button_box.getChildren().add(open_file);
            s_values_layout.add(button_box, "wrap");
            return s_values_layout;
        }

     /*   public MigPane school_principle_page() {     // principle scene
            MigPane s_principle_layout = new MigPane();
            s_principle_layout.setId("The Principle");
            new Common_features_for_all().title(s_principle_layout);
            read_file_text data_to_read = new read_file_text();
            data_to_read.reading_text(s_principle_layout, "/TextFiles_for_school/Principle");
            return s_principle_layout;
        }

      */
        private MigPane thequeensscene() {
            MigPane s_royal_layout = new MigPane();
            s_royal_layout.setId("The Royal Visit");
            new Common_features_for_all().title(s_royal_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_royal_layout, "/TextFiles_for_school/Royal Visits.txt");
            s_royal_layout.add(general_layout_for_any_image("/images/for_royal_visit(1).jpg", null, "300"), "wrap");
            return s_royal_layout;
        }
        private MigPane school_alumni() {
            try {
                MigPane alumni_layout = new MigPane();
                alumni_layout.setId("Alumni");
                new Common_features_for_all().title(alumni_layout);
                data_to_read = new read_file_text();
                data_to_read.reading_text(alumni_layout, "/TextFiles_for_school/Alumni.txt");
                Button open_me = new Button("Click me to open the Email (built in App)");
                open_me.setOnAction(e -> {
                    try {
                        mailme();//to open email
                    } catch (Exception failed) {
                        failed.printStackTrace();
                    }
                });
                alumni_layout.add(open_me, "wrap");
                return alumni_layout;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private MigPane school_ctoGcse_page() {            // ctoGcse == changes to gcse
            MigPane s_ctgcse_layout = new MigPane();//s_ctgcse_layout == changes to gcse_layout
            s_ctgcse_layout.setId("Changes to GCSE's");
            new Common_features_for_all().title(s_ctgcse_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_ctgcse_layout, "/TextFiles_for_school/Changes To GCSE");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Download Information for Parents on the new GCSEs");
            open_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2017/07/Information-for-Parents-on-the-new-GCSEs.pdf", "Information for Parents on the new GCSEs.pdf");
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            button_box.getChildren().add(open_file);
            s_ctgcse_layout.add(button_box, "wrap");
            return s_ctgcse_layout;
        }

        private MigPane catch_up_premium() {
            MigPane catchup_layout = new MigPane();//s_ctgcse_layout == changes to gcse_layout
            catchup_layout.setId("Changes to GCSE's");
            new Common_features_for_all().title(catchup_layout);
            data_to_read = new read_file_text();
            data_to_read.reading_text(catchup_layout, "/TextFiles_for_school/Catch up Premium");
            VBox button_box =new VBox();
            Button download_file = new Button("Download Catch-Up Premium Funding Statement");
            download_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2021/02/Catch-Up-Premium-Funding-Statement.pdf", "Catch-Up-Premium-Funding-Statement.pdf");
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            button_box.setPadding(new Insets(2));
            button_box.getChildren().add(download_file);
            catchup_layout.add(button_box, "wrap");
            return catchup_layout;
        }
        private MigPane stdp_page() {//Student Data Privacy == student data privacy
            MigPane s_stdp_layout = new MigPane();//s_ctgcse_layout == changes to gcse_layout
            s_stdp_layout.setId("Student Data Privacy");
            new Common_features_for_all().title(s_stdp_layout);
           data_to_read = new read_file_text();
            data_to_read.reading_text(s_stdp_layout, "/TextFiles_for_school/StudentDataPrivacy");
            VBox button_box = new VBox();
            button_box.setPadding(new Insets(2));
            Button open_file = new Button("Click to Open SRS-Privacy-Notice-Students");
            open_file.setOnAction(e -> {
                try {
                    saved_and_open_file("http://www.sydneyrussellschool.com/wp-content/uploads/2019/11/SRS-Privacy-Notice-Students.pdf", "SRS-Privacy-Notice-Students.pdf");
                } catch (Exception failed) {
                    failed.printStackTrace();
                }
            });
            button_box.getChildren().add(open_file);
            s_stdp_layout.add(button_box, "wrap");
            return s_stdp_layout;
        }
        private MigPane unif_equipment_page() {
            MigPane s_uniform_layout = new MigPane();//s_ctgcse_layout == changes to gcse_layout
            s_uniform_layout.setId("Uniform And Equipment");
            new Common_features_for_all().title(s_uniform_layout);
            s_uniform_layout.add(general_layout_for_any_image("/images/uniform_and_equipmen.png", "Uniform & Equipment", "190"), "wrap");
            data_to_read = new read_file_text();
            data_to_read.reading_text(s_uniform_layout, "/TextFiles_for_school/UniformAndEquipment");
            return s_uniform_layout;
        }
    }
    class read_file_text {
        private void reading_text(MigPane pane, String file_path) {//wrap_or_not refers to whether you would like to place another node after this text or not
            try {
                Task this_task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        try{
                           HBox main_box = new HBox();
                           main_box.prefWidthProperty().bind(window.widthProperty().subtract(30));
                           main_box.setPadding(new Insets(1,5,1,5));
                           VBox text_area_box = new VBox();
                           // read from file
                           InputStream inputStream = this.getClass().getResourceAsStream(file_path);
                           InputStreamReader fs = new InputStreamReader(inputStream);
                           BufferedReader reader = new BufferedReader(fs);
                           StringBuilder main_text = new StringBuilder();
                           String current_text;
                           while((current_text=reader.readLine())!=null) {
                               if (!(current_text.toString().trim().isEmpty())) {
                                   main_text.append(current_text + "\n");
                               }
                           }
                           this_text_area main_text_area = new this_text_area(main_text.toString());
                           text_area_box.getChildren().addAll(main_text_area);;
                           linktoscene().prefHeightProperty().bind(text_area_box.heightProperty());
                           main_box.getChildren().addAll(text_area_box,linktoscene());
                           pane.add(main_box,"wrap");
                          // pane.add(read_text,"wrap");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                this_task.run();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, "Error in .(reading_text(MigPane pane,String file_name){//wrap_or_not refers to whether you would like to place another node after this text or not\\n in apptry2\");\n");
            }
        }
    }

    public class this_text_area extends TextArea {
        public this_text_area(String area_text) {
            super(area_text);
            prefWidthProperty().bind(window.widthProperty().subtract(300));
            wrapTextProperty().set(true);
            setEditable(false);
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    Node text_area_text = lookup(".text");
                    if (text_area_text != null) {
                        prefHeightProperty().bind(
                                Bindings.createDoubleBinding(
                                        new Callable<Double>() {
                                            @Override
                                            public Double call() throws Exception {
                                                return text_area_text.getBoundsInLocal().getHeight();
                                            }
                                        }, text_area_text.boundsInLocalProperty()).add(20));
                        scrollTopProperty().set(text_area_text.getBoundsInLocal().getHeight());
                        this.stop();
                    }
                }
            }.start();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}