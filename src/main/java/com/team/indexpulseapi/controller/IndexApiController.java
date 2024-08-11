package com.team.indexpulseapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/index_api")
public class IndexApiController {

    final boolean isWindows;

    IndexApiController() {
        isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @GetMapping("/test")
    public void getTest() {
        String output = null;
        if (!isWindows) {
            //AWS Server Linux code.
        } else {
            try {
                output = execCmd("cmd.exe /c C:/Users/jesus/AppData/Roaming/npm/gis.cmd google.com " +
                        "--path C:/Users/jesus/Documents/Java/upwork/google-indexing-script/service_account.json");
                System.out.println(output);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //Based on source: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
    public static String execCmd(String cmd) throws java.io.IOException {
        String error = "";
        String normal = "";
        String output = "";
        String nextString = "";
        java.util.Scanner errorScanner = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getErrorStream());
        java.util.Scanner normalScanner = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream());

        while (errorScanner.hasNext()) {
            nextString = errorScanner.next();
            if (nextString.contains(".")) {
                error += nextString + "\n";
            } else {
                error += nextString + " ";
            }
        }

        while (normalScanner.hasNext()) {
            nextString = normalScanner.next();
            if (nextString.contains(".")) {
                normal += nextString + "\n";
            } else {
                normal += nextString + " ";
            }
        }

        output += normal;
        output += error;

        return output;
    }
}
