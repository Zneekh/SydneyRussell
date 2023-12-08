package Source_for_this_main.auto_update;


import java.io.*;



public class deploy_new_jar {
    private void find_all_current_files(){
        try{
            File this_file = new File("SydneyRussellSchool");
            File check_file;
            if(this_file.exists()){

                System.out.println("found it");
            }
            else {
                System.out.println("not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[]args){
        deploy_new_jar jar  = new deploy_new_jar();
        jar.find_all_current_files();
    }
}
