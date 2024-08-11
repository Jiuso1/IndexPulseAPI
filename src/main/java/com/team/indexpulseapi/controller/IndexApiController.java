package com.team.indexpulseapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
                output = execCmdJiuso("cmd.exe /c C:/Users/jesus/AppData/Roaming/npm/gis.cmd google.com " +
                        "--path C:/Users/jesus/Documents/Java/upwork/google-indexing-script/service_account.json");
                System.out.println(output);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //Source: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String execCmdJiuso(String cmd) throws java.io.IOException {
        String error = "";
        String normal = "";
        String output = "";
        java.util.Scanner errorScanner = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getErrorStream());
        java.util.Scanner normalScanner = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream());

        while (errorScanner.hasNext()) {
            error += errorScanner.next() + "\n";
        }

        while (normalScanner.hasNext()) {
            normal += normalScanner.next() + "\n";
        }

        output += error;
        output += normal;

        return output;
    }
}
