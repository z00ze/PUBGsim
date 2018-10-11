import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class jsonToCSV {

    ExecutorService executor;
    public jsonToCSV(){
        executor = Executors.newFixedThreadPool(16);
        File f = new File("json/");
        ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));
        for(String filename : names){
            this.executor.submit(() -> {
                try {

                    new jsonToCSVWorker(filename, Thread.currentThread().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public void stop(){
        try {
            System.out.println("attempt to shutdown executor");
            this.executor.shutdown();
            this.executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!this.executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            this.executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
