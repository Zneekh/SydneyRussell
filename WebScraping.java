package Source_for_this_main;



import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


import java.io.*;
import java.net.*;
// public Task<String> reading_data_by_class(String website_url, String classname, String htmlTag, String file_path) {
import java.util.ArrayList;

public class WebScraping{
    ArrayList<String> element_data;
    public Task<Void> reading_data_by_class(String website_url, String classname, String htmlTag, String file_path) {
        Task<Void> this_ = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try{
                    element_data = new ArrayList<>();
                    Document website = Jsoup.connect(website_url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").get();
                    Elements by_class = website.select(classname);
                    for (Element item : by_class) {
                        Elements by_tag = item.select(htmlTag);
                        for(Element tagged:by_tag){
                            if(file_path.equals("src/main/resources/news&updates/upcomingevents-receiving-end")){
                                if(tagged.hasClass("rtw_main")){
                                    element_data.add(tagged.text().toString().trim());
                                }
                            }else{
                                element_data.add(tagged.text().toString().trim());
                            }
                        }
                    }
                    for(String item:element_data) {
                        item = item.replace(",", "");
                    }
                    FileWriter pen = new FileWriter(file_path);
                    BufferedWriter written = new BufferedWriter(pen);
                    for(String item:element_data){
                        written.write(item+"\n");
                    }
                    written.close();
                    //System.out.println("Done for: "+file_path);
                }catch (Exception e){
                    System.out.println("EXception in: "+file_path);

                    e.printStackTrace();
                }
                return null;
            };
        };
        this_.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                try {
                    System.out.println("done for this" + file_path);
                    URL this_url = new URL(website_url);
                    this_.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread this_thread = new Thread(this_);
        this_thread.setDaemon(true);
        this_thread.setName("For WebScraping");
        this_thread.start();
        return this_;
    }
}
