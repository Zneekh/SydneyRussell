package Source_for_this_main.auto_update;



import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.jar.*;
import java.util.regex.Pattern;
public class checking_for_update {
    double updated_version;//stores the version of the upcoming jar.
    double current_version;//stores the current jar version.
    String path_to_jar;//holds to path to the jar file to be used in read_new_jar(String path_to_jar) and in comparing_version() to delete if no new version is available
    File temp_folder;
    private void check_internet(){
        // checks internet connectivity
        Source_for_this_main.internet_connectivity internet =new Source_for_this_main.internet_connectivity();
        internet.website_url ="http://localhost:8888";
        internet.message = "To check for Updates, Please ensure you have wifi connectivity.";
        internet.run_to_web =false;
        internet.run_file=false;
        //internet.start_thread();
    }
    private void look_for_update_jar(){
        //looks for the update_jar to read it.      /Users/zeeshankhan/Documents/updated_jar/SydneyMainJar-3.0.jar
        try{
            check_internet();
            Document website = Jsoup.connect("http://localhost:8888").get();
            Element by_tag = website.select("a").first();
            String url = by_tag.attr("href");
            String download_url  = "http://localhost:8888/"+url;
            URL url_to_web = new URL(download_url);
            InputStream downloading_data = url_to_web.openStream();
            temp_folder = new File("incoming_jar");
            if(!(temp_folder.exists())){
                temp_folder.mkdirs();
            }
            String downloaded_file_name= FilenameUtils.getBaseName(url_to_web.getPath())+".jar";
            Files.copy(downloading_data,Paths.get(String.format("%s/%s",temp_folder,downloaded_file_name)),StandardCopyOption.REPLACE_EXISTING);
            read_new_jar(String.format("%s/%s",temp_folder,downloaded_file_name));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void current_jar(){
        try{
            FileInputStream fs = new FileInputStream("src/main/resources/META-INF/MANIFEST.MF");
            InputStreamReader is = new InputStreamReader(fs);
            System.out.println("jhere");
            BufferedReader reader = new BufferedReader(is);
            String line;
            while((line=reader.readLine())!=null){
                if(line.contains("App-Version")){
                    String regex = "([0-9]+[.]+[0-9]+)";
                    Pattern this_pattern =Pattern.compile(regex);
                    Matcher this_matcher = this_pattern.matcher(line);
                    if(this_matcher.find()){
                        current_version = Double.parseDouble(this_matcher.group());
                        break;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void read_new_jar(String path_to_jar){
        this.path_to_jar =path_to_jar;
        //download the jar from the internet to the "download_directory" and then unzips it to check the version from the manifest-file.
        try{
            URL jar_path = new URL(String.format("jar:file:%s!/",path_to_jar));
            JarURLConnection open_stream_connection = (JarURLConnection) jar_path.openConnection();
            Manifest manifest_file =open_stream_connection.getManifest();
            for(Map.Entry item:manifest_file.getMainAttributes().entrySet()){
                if(item.getKey().toString().contains("App-Version")) {
                    updated_version =Double.parseDouble(item.getValue().toString());
                    System.out.println(updated_version);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void comparing_version(){
        try{
            current_jar();
            look_for_update_jar();
            read_new_jar("incoming_jar/SydneyRussellSchool.jar");
            System.out.println("Current Version: "+current_version);
            System.out.println("Updated Version: "+updated_version);
            //
            if(updated_version>current_version){
                // if an update is available. an alert is shown to inform the user.
                System.out.println("update New Version");
                System.out.println("running "+path_to_jar);
                String command = String.format("java -jar %s",path_to_jar);
                Runtime.getRuntime().exec(command);

                /*update_available_notification show_notification = new update_available_notification();
                show_notification.version_label =new Label(String.valueOf(updated_version));
                show_notification.start();

                 */
            }else if((updated_version<current_version)||(updated_version==current_version)){
                try {
                   // delete_folder(temp_folder);
                    Logger.getLogger(getClass().getName()).log(Level.OFF,"Older version or Same version found");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void delete_folder(File folder){
        try{
            File[] sub_files = folder.listFiles();
            for(File file:sub_files){
                file.delete();
            }
            folder.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[]args) {
        checking_for_update checking = new checking_for_update();
        checking.comparing_version();
    }
}