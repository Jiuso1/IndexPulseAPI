package com.team.indexpulseapi.controller;

import com.team.indexpulseapi.entity.IndexRequest;
import com.team.indexpulseapi.entity.Status;
import com.team.indexpulseapi.repository.IndexRequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
@RequestMapping("/index_api")
public class IndexApiController {

    final boolean isWindows;//isWindows values true when the service is run by a Windows OS.
    private final IndexRequestRepository indexRequestRepository;//Index request shared repository.

    IndexApiController(IndexRequestRepository indexRequestRepository) {
        isWindows = System.getProperty("os.name").toLowerCase().contains("windows");//If the OS name contains windows, isWindows values true.
        this.indexRequestRepository = indexRequestRepository;//The repository is received and shared with the rest of controllers.
    }

    @GetMapping("/test")
    public void getTest() {
        String output = null;//Console output.
        String command = "cmd.exe /c C:/Users/jesus/AppData/Roaming/npm/gis.cmd google.com " +
                "--path C:/Users/jesus/Documents/Java/upwork/google-indexing-script/service_account.json";
        if (!isWindows) {//If OS isn't Windows:
            //Linux code missing...
        } else {//If OS is Windows:
            try {
                output = execCmd(command);//output values the console output of the given command.
                System.out.println(output);//We print output.
            } catch (IOException e) {//If there was an IOException:
                System.out.println(e.getMessage());//We print the error message.
            }
        }
    }

    @GetMapping("/run")
    public void getRun() {
        String output = null;//Console output.
        String command = "";//Command that CMD is going to run.
        ArrayList<IndexRequest> indexRequestArrayList = indexRequestRepository.findByStatus(Status.NOT_INDEXED);//indexRequestArrayList gets all not indexed index requests.
        IndexRequest indexRequestIterated = null;//Index request variable used to iterate.
        String uploadDirectory = null;//Path where user account JSON files are saved.
        File[] files = null;//Array of user account JSON files.
        int numberOfFiles = 0;//Current number of files in uploadDirectory.
        boolean requested = false;//requested values true when an index request has been correctly requested to Google API.
        String filePath = "";//String variable used to iterate each file path.
        int j = 0;//While counter.

        for (int i = 0; i < indexRequestArrayList.size(); i++) {//For each not indexed index request:
            indexRequestIterated = indexRequestArrayList.get(i);//indexRequestIterated updates.
            uploadDirectory = "C:/Users/jesus/Downloads/uploadExample/" + indexRequestIterated.getUserAccountId();//uploadDirectory values path where this user account JSON files are saved.
            files = new File(uploadDirectory).listFiles();//files updates with current uploadDirectory.

            if (files == null) {//If there are no files:
                System.out.println("No files");
            } else {//If there are files:
                while (j < files.length && !requested) {//While the index request hasn't been requested to Google, and we haven't passed array limits:
                    filePath = files[j].getAbsolutePath().replace('\\', '/');//filePath updates with current JSON file.
                    command = "cmd.exe /c C:/Users/jesus/AppData/Roaming/npm/gis.cmd " +
                            indexRequestIterated.getUrl() + " " +
                            "--path " +
                            filePath;//command updates with current index request URL and file path.
                    try {
                        if (!isWindows) {//If OS isn't Windows:
                            //Linux code missing...
                        } else {//If OS is Windows:
                            output = execCmd(command);//output values the console output of the given command.
                        }
                        System.out.println(command);//We print command.
                        System.out.println(output);//We print output.
                        if (output.contains("Indexing requested successfully")) {//This remains to be verified:
                            requested = true;
                            //Change status missing.
                        }
                    } catch (IOException e) {//If there was an IOException:
                        System.out.println(e.getMessage());//We print the error message.
                    }
                    j++;//The counter increments.
                }
            }
        }
    }

    //Based on source: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
    public String execCmd(String cmd) throws java.io.IOException {
        String error = "";//Error output.
        String normal = "";//Normal output.
        String output = "";//Full output.
        String nextString = "";//Temporal string to save each element.
        Process commandProcess = Runtime.getRuntime().exec(cmd);//Command execution process.
        Scanner errorScanner = new java.util.Scanner(commandProcess.getErrorStream());//Error output scanner.
        Scanner normalScanner = new java.util.Scanner(commandProcess.getInputStream());//Normal output scanner.

        while (errorScanner.hasNext()) {//While we haven't scanned all error strings:
            nextString = errorScanner.next();//nextString values the current errorScanner word.
            if (nextString.contains(".")) {//If nextString contains a dot:
                error += nextString + "\n";//It's saved in error output with an end line.
            } else {//If nextString doesn't contain a dot:
                error += nextString + " ";//It's saved in error output with a space.
            }
        }

        while (normalScanner.hasNext()) {//While we haven't scanned all normal strings:
            nextString = normalScanner.next();//nextString values the current normalScanner word.
            if (nextString.contains(".")) {//If nextString contains a dot:
                normal += nextString + "\n";//It's saved in normal output with an end line.
            } else {//If nextString doesn't contain a dot:
                normal += nextString + " ";//It's saved in normal output with a space.
            }
        }

        //output string is the union of normal and error output:
        output += normal;
        output += error;

        return output;//output is returned.
    }
}
