package Source_for_this_main;

import Source_for_this_main.auto_update.checking_for_update;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import org.tbee.javafx.scene.layout.MigPane;

public class example_run extends Application {
    public  void start(Stage stage) throws InterruptedException {
        new checking_for_update().comparing_version();
        stage.setScene(new Scene(new MigPane(),800,800));
        stage.show();


       /*  mailbox mail = new mailbox();
            mail.run(stage);

        */
        /*VBox x = new VBox();
        x.getChildren().add(new house_points().main());
        stage.setScene(new Scene(x,800,600));
        stage.show();

         */
   /*     update_available_notification d= new update_available_notification();
        d.start();

    */


    }
    public static void main(String[]args){
        launch(args);
    }
}
/*


/*System.out.println(all_scores);
            position = new Text();
            for(Map.Entry<String,Double> item:comparing_data.entrySet()){
                for(int x=0;x<all_scores.size();x++){
                    if(((int)item.getValue().intValue())==all_scores.get(0)){
                        System.out.println(String.format("Fifth is %s",item.getKey()));
                        position.setText(String.format("Fifth is %s",item.getKey()));

                        break;
                    }else if(((int)item.getValue().intValue())==all_scores.get(1)) {
                        System.out.println(String.format("Fourth is %s",item.getKey()));
                        position.setText(String.format("Fourth is %s",item.getKey()));
                        //stacked_meta.getChildren().add(position);
                        break;
                    }else if(((int)item.getValue().intValue())==all_scores.get(2)) {
                        System.out.println(String.format("Third is %s",item.getKey()));
                        position.setText(String.format("Third is %s",item.getKey()));
                        //stacked_meta.getChildren().add(position);
                        break;
                    }else if(((int)item.getValue().intValue())==all_scores.get(3)) {
                        System.out.println(String.format("Second is %s",item.getKey()));
                        position.setText(String.format("Second is %s",item.getKey()));
                        //stacked_meta.getChildren().add(position);
                        break;
                    }
                    else if(((int)item.getValue().intValue())==all_scores.get(4)) {
                        System.out.println(String.format("First is %s",item.getKey()));
                        position.setText(String.format("First is %s",item.getKey()));
                       // stacked_meta.getChildren().add(position);
                        break;
                    }

                    x+=1;
                    break;
                }


            }
             */

