package Source_for_this_main;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.stage.Modality;


import java.awt.*;
import java.net.Socket;
import java.net.*;
import java.util.Optional;

public class internet_connectivity {
    public String message;
    Socket endpoint = new Socket();
    public boolean connected;
    public boolean run_to_web;
    InetSocketAddress port_address;
    public boolean run_file;//used to run Source.run_files().java to WebScrape latest data from the web.Note: Only to be used in Source.SplashScreen().check_internet_and_run_file().
    public String website_url;

    public void start_thread() {
        run_web d = new run_web();
        d.start();
    }

    private void run_website(String website_url_, boolean run_to_web) {
        this.run_to_web = run_to_web;
        this.website_url = website_url_.trim();
        try {
            check_for_internet();
            if (endpoint.isConnected() != false) {
                while (true) {
                    if (run_to_web == true) {
                        Desktop.getDesktop().browse(new URL(website_url_).toURI());
                        break;
                    } else if (run_to_web == false) {
                        System.out.println("Not running to Website as only internet check in progress");
                        if (run_file == true) {
                            System.out.println("Starting Running Files....");
                            new run_files().start();
                            break;
                        } else if (run_file == false) {
                            break;
                            // if false no files will be running and nothing will be updated.....
                        }
                        break;
                    }
                    break;
                }
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        connectivity_error(message);
                    }
                });
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void check_for_internet() {
        try {
            port_address = new InetSocketAddress("www.google.com", 80);
            endpoint.connect(port_address, 1000);
            if (endpoint.isConnected()) {
                connected = true;
            }
        } catch (Exception e) {

            try {
                endpoint.setReuseAddress(true);
            } catch (Exception f) {
                f.printStackTrace();
            }
            //e.printStackTrace();
        } finally {
            try {
                endpoint.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void connectivity_error(String message) {//show to the user when there is no internet connectivity.


        this.message = message;
        Alert notconnected = new Alert(Alert.AlertType.ERROR);
        notconnected.initModality(Modality.APPLICATION_MODAL);
        notconnected.setHeaderText(null);
        message = message;
        //This web page cannot be displayed because your computer is offline.
        notconnected.setContentText(message);
        notconnected.setTitle("You are not connected to the Internet!");
        notconnected.setHeaderText(null);
        Optional<ButtonType> event = notconnected.showAndWait();
        if (event.isPresent()) {
            if (event.get().getButtonData().isDefaultButton()) {
                // notconnected.hide();
                System.out.println("done");
            }
        }
    }

    private class run_web extends Service<String> {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    Thread.sleep(700);
                    run_website(website_url, run_to_web);
                    return "Successfully executed internet_connectivity";
                }
            };
        }
    }
}


