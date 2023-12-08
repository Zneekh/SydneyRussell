package Source_for_this_main;
/*
                    The files in this class are run from Source.inter_connectivity for now after run_file==true.
 */


import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/*
rotatingtweet
 */
public class run_files extends Service {

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Task call() {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for_news_updates();
                                Thread.sleep(2000);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception failed) {
                    failed.printStackTrace();
                }
                setOnSucceeded(e->{
                    System.out.println("Done for all");
                    run_files.this.cancel();
                });
                return null;
            }
        };


    }
    // Total number of files == 37
    public void for_news_updates() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com", "div.rotatingtweet", "p", "src/main/resources/news&updates/upcomingevents-receiving-end");
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            try{
                this_task.cancel();
                Thread.sleep(2000);
                school();
            }catch (Exception failed){
                failed.printStackTrace();
            }

        });
        this_task.run();
    }

    public void school() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4797", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/Home.txt");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=44", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/UniformAndEquipment");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=7544", "div.gdl-page-item", "p", "src/main/resources/TextFiles_for_school/StudentDataPrivacy");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=5076", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/SchoolStatus");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?p=8425", "div.gdl-page-item", "p", "src/main/resources/TextFiles_for_school/Catch up Premium");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=5650", "div.gdl-page-content", "p, li", "src/main/resources/TextFiles_for_school/Changes To GCSE");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6547", "div.gdl-page-content", "p, li", "src/main/resources/TextFiles_for_school/Alumni.txt");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=3812", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/Royal Visits.txt");
               //     new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4797", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/Principle");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=3778", "div.gdl-page-content", "strong, p, br ", "src/main/resources/TextFiles_for_school/SRS Values");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=3754", "div.gdl-page-content", "p, li, br, h4", "src/main/resources/TextFiles_for_school/Assesments");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4360", "div.gdl-page-content", "p", "src/main/resources/TextFiles_for_school/Grouping");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=26", "div.gdl-page-content", "p, li, br", "src/main/resources/TextFiles_for_school/OFSTED");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=40", "div.gdl-page-content", "p, li, h2, img", "src/main/resources/TextFiles_for_school/SchoolResults");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=225", "div.accordion-content", "p, strong", "src/main/resources/TextFiles_for_school/School_admission(not_sixthform)");
                    getOnSucceeded();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            try{
                this_task.cancel();
                Thread.sleep(2000);
                hcup();
            }catch (Exception failed){
                failed.printStackTrace();
            }


        });
        this_task.run();
    }
    public void hcup() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try{
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4714", "div.gdl-page-content", "p,br", "src/main/resources/files_for_house_cup/Dragon");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4711", "div.gdl-page-content", "p,br", "src/main/resources/files_for_house_cup/Griffin");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4729", "div.gdl-page-content", "p,br", "src/main/resources/files_for_house_cup/Centaur");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4721", "div.gdl-page-content", "p,br", "src/main/resources/files_for_house_cup/Pegasus");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4717", "div.gdl-page-content", "p,br", "src/main/resources/files_for_house_cup/Phoenix");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4708", "div.gdl-page-content", "p", "src/main/resources/files_for_house_cup/upcoming_events");
                    getOnSucceeded();
                }catch (Exception e){

                    e.printStackTrace();
                }
                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            try {
                this_task.cancel();
                Thread.sleep(2000);
                pupil();
            }catch (Exception failed){
                failed.printStackTrace();
            }
        });
        this_task.run();
      }
    public void pupil() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try{
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4702", "div.gdl-page-content", "p, li", "src/main/resources/Textfiles_for_pupil/help_and_advice");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4687", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_pupil/Library");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=46", "div.gdl-page-content", "p, h3, h5", "src/main/resources/Textfiles_for_pupil/Leadership");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=52", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_pupil/EXTRA_CURRICULAR_ACTIVITIES");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=4698", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_pupil/y8 preferences");
                    getOnSucceeded();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            try{
                this_task.cancel();
                Thread.sleep(2000);
                sixth_form();
            }catch (Exception failed){
                failed.printStackTrace();
            }
        });
        this_task.run();
    }//src/main/resources/Textfiles_for_pupil/daily_schedule
    public void sixth_form() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try{
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6413", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_sixth_form/DressCode");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6419", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_sixth_form/ContactUs");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6407", "div.gdl-page-content", "p, h2", "src/main/resources/Textfiles_for_sixth_form/Curriculum");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=8185", "div.gdl-page-content", "p,li,h2,h4", "src/main/resources/Textfiles_for_sixth_form/bursary_policy");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=8149", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_sixth_form/football_academy");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=7829", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_sixth_form/Alevel_preparation");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6401", "div.gdl-page-content", "p, br", "src/main/resources/Textfiles_for_sixth_form/AboutUs");
                    getOnSucceeded();
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            try{
                this_task.cancel();
                Thread.sleep(2000);
                parents_guardians();
            }catch (Exception failed){
                failed.printStackTrace();
            }
        });
        this_task.run();
    }
    public void parents_guardians() {
        Task this_task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=6313", "div.gdl-page-content", "p, br", "src/main/resources/Textfiles_for_sixth_form/Free School Meals (normal)");
                    new WebScraping().reading_data_by_class("http://www.sydneyrussellschool.com/?page_id=8026", "div.gdl-page-content", "p", "src/main/resources/Textfiles_for_sixth_form/remote_learning");
                    getOnSucceeded();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        this_task.setOnSucceeded(e->{
            this_task.cancel();
        });
        this_task.run();
    }
}