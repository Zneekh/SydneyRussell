package Source_for_this_main;


import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/*
  This class uses Bar Chart to display the score of different teams in the House Cup Competition


 */
public class house_points {
    /*CategoryAxis teams = new CategoryAxis();//used to display team names on the bar
    NumberAxis house_points = new NumberAxis();//used to display number

 */
    Text rank;
    private BarChart house_cup_chart;

    private double current_score;

    XYChart.Data dragon_data;
    XYChart.Data pheonix_data;
    XYChart.Data pegasus_data;
    XYChart.Data centaur_data;
    XYChart.Data griffin_data;
    BarChart<Number, String> bar_chart;
    ArrayList<Integer> all_scores;//used to store compared scores.
    HashMap<Double,String> comparing_data;//
    AnchorPane chart_box;

    private void show_score(){
        try{
            VBox this_box = new VBox();
            this_box.setStyle("-fx-background-color:rgb(255,255,255);"+"-fx-border-width: 0 0 2 2;"+"-fx-border-color:rgb(0,153,0);"+"-fx-border-radius:2px;");
            this_box.maxHeightProperty().bind(bar_chart.getYAxis().heightProperty().subtract(60));
            this_box.setPadding(new Insets(10));
            Label house_points = new Label("House Points");
            house_points.setStyle("-fx-text-fill:rgb(0,0,0);"+"-fx-font-size:17px;");
            this_box.getChildren().add(house_points);
            for(XYChart.Series item:bar_chart.getData()){
                System.out.println("team" +item.getName());
                XYChart.Data this_data = (XYChart.Data)item.getData().get(0);
                Label team = new Label(String.format("%s: %s",this_data.getYValue(),this_data.getXValue()));
                Text team_text = new Text(team.getText().toString());
                this_box.maxWidthProperty().set(team_text.getBoundsInLocal().getWidth()+100);
                switch (item.getName()) {
                    case "Dragon":
                        team.setStyle("-fx-text-fill:rgb(255,0,0);");
                        break;
                    case "Phoenix":
                        team.setStyle("-fx-text-fill: rgb(255,165,0);");
                        break;
                    case "Griffin":
                        team.setStyle("-fx-text-fill:rgb(0,255,0);");
                        break;
                    case "Centaur":
                        team.setStyle("-fx-text-fill:rgb(106,13,173);");
                        break;
                    case "Pegasus":
                        team.setStyle("-fx-text-fill:rgb(102,211,250);");
                        break;
                }
                this_box.getChildren().addAll(team);
            }
            chart_box.setOnMouseEntered(e->{
                AnchorPane.setRightAnchor(this_box,4.0);
                chart_box.getChildren().add(this_box);
            });
            chart_box.setOnMouseExited(e->{
                chart_box.getChildren().remove(this_box);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public AnchorPane main() {
        try {
            chart_box = new AnchorPane();
            CategoryAxis x_axis = new CategoryAxis();
            x_axis.setLabel("Team");
            x_axis.setAnimated(false);
            NumberAxis n_axis = new NumberAxis();
            n_axis.setLabel("House Points");
            bar_chart = new BarChart<>(n_axis, x_axis);
            bar_chart.setLegendSide(Side.BOTTOM);
            bar_chart.setCategoryGap(40);
            bar_chart.setBarGap(0);
            bar_chart.getStylesheets().add("/stylesheets/for_bar_chart.css");//adds specific colour to the bars
            bar_chart.getData().addAll(Dragons(), Phoenix(), Griffin(), Centaur(), Pegasus());
            chart_box.getChildren().add(bar_chart);
            show_score();
            return chart_box;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return main();
    }

    private XYChart.Series Dragons() throws IOException {
        XYChart.Series<Number,String> dragon_series = new XYChart.Series<>();
        dragon_series.setName("Dragon");
        reading_file("/files_for_house_cup/Dragon_score");
        dragon_data = new XYChart.Data<>( current_score,"Dragon");
        dragon_series.getData().add(dragon_data);
        return dragon_series;
    }

    private XYChart.Series Phoenix() {
        XYChart.Series<Number,String> pheonix_series = new XYChart.Series();
        pheonix_series.setName("Phoenix");
        reading_file("/files_for_house_cup/Phoenix_score");
        pheonix_data = new XYChart.Data<>( current_score,"Phoenix");
        pheonix_series.getData().add(pheonix_data);
       return pheonix_series;
    }

    private XYChart.Series Griffin() {
        XYChart.Series<Number,String> griffin_series = new XYChart.Series();
        griffin_series.setName("Griffin");
        reading_file("/files_for_house_cup/Griffin_score");
        griffin_data = new XYChart.Data<Number,String>( current_score,"Griffin");
        griffin_series.getData().add(griffin_data);
        return griffin_series;
    }

    private XYChart.Series Centaur() {
        XYChart.Series<Number,String> centaur_series = new XYChart.Series();
        centaur_series.setName("Centaur");
        reading_file("/files_for_house_cup/Centaur_score");
        centaur_data = new XYChart.Data<Number,String>( current_score,centaur_series.getName());
        centaur_series.getData().addAll(centaur_data);
        return centaur_series;
    }

    private XYChart.Series Pegasus() {
        XYChart.Series<Number,String> pegasus_series = new XYChart.Series();
        pegasus_series.setName("Pegasus");
        reading_file("/files_for_house_cup/Pegasus_score");
        pegasus_data = new XYChart.Data<Number,String>( current_score,"Pegasus");
        pegasus_series.getData().add(pegasus_data);
        return pegasus_series;
    }

    private void compares_scores(){
        try{
            comparing_data = new HashMap<>();//hashmap stores the name of the team and the current score in order to compare it
            comparing_data.put((double)dragon_data.getXValue(),String.valueOf(dragon_data.getYValue()));//added dragon to hashmap
            comparing_data.put((double)pheonix_data.getXValue(),String.valueOf(pheonix_data.getYValue()));
            comparing_data.put((double)griffin_data.getXValue(),String.valueOf(griffin_data.getYValue()));
            comparing_data.put((double)centaur_data.getXValue(),String.valueOf(centaur_data.getYValue()));
            comparing_data.put((double)pegasus_data.getXValue(),String.valueOf(pegasus_data.getYValue()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void reading_file(String file_name) {
        try {
            InputStream in = getClass().getResourceAsStream(file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Scanner scan = new Scanner(reader);
            StringBuilder br = new StringBuilder();
            while (scan.hasNextLine()) {
                br.append(scan.nextLine());
            }
            current_score = Double.parseDouble(br.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// add a compare feature .. check scores and show the first position holder
    /*
    private StackPane to_stackpane(XYChart.Series bar_name,double score) {
        XYChart.Data data = (XYChart.Data) bar_name.getData().get(0);/* finds the data name. this only applies if the XYChart.length is only 1. if more than one, rewrite code with a for loop
                use something like this:
                for(Object item:bar_name.getData()){
                if(item instanceof XYChart.Data){
                    XYChart.Data this_data = (XYChart.Data) item;
                    String data_name = this_data.getYValue().toString();

                }
                }

        StackPane stacked_meta= new StackPane();//
        stacked_meta.maxWidthProperty().set(80);


        //
        String data_name = data.getYValue().toString();
        Text current_score = new Text(String.format("House Points: %s",score));
        Text team_name = new Text("Team: "+data_name);
        double width = team_name.getLayoutBounds().getWidth()+60;
        stacked_meta.maxWidthProperty().set(width+100);
        team_name.setStyle("-fx-fill:white;");
        current_score.setStyle("-fx-fill:white;");
        VBox meta_box = new VBox();//stores the meta data. e.g. Team name, points and positions
        meta_box.maxHeightProperty().set(80);
        meta_box.setPadding(new Insets(1,1,1,5));
        // finding length of the bar

        switch (data_name){// set colour for the background
            case "Dragon":
                meta_box.setStyle("-fx-background-color:rgb(255,0,0);");
                break;
            case "Phoenix":
                meta_box.setStyle("-fx-background-color:rgb(255,165,0);");
                break;
            case "Griffin":
                meta_box.setStyle("-fx-background-color:rgb(0,255,0);");
                break;
            case "Centaur":
                meta_box.setStyle("-fx-background-color:rgb(106,13,173);");
                break;
            case "Pegasus":
                meta_box.setStyle("-fx-background-color:rgb(102,211,250);");
                break;
        }
        //meta_box.setMinSize(width,50);

        meta_box.getChildren().addAll(team_name,current_score);
        stacked_meta.setOnMouseEntered(e->{
           stacked_meta.getChildren().setAll(meta_box);
           stacked_meta.toFront();
        });
        stacked_meta.setOnMouseExited(e->{
            stacked_meta.getChildren().clear();
        });
        chart_box.getChildren().add(stacked_meta);
        return stacked_meta;
    }

     */
}