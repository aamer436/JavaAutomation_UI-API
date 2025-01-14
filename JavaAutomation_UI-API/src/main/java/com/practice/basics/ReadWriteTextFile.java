package com.practice.basics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTextFile {
    public static void main(String[] args){
        //CWD and Root Dir both give same output
        System.out.println("Current working dir "+ System.getProperty("user.dir"));
        System.out.println("FIle path "+new File(".").getAbsoluteFile());
        System.out.println("Root dir "+new File(".").getAbsoluteFile().getParentFile());
        List<String> textContent = new ArrayList<>();
        try {
            //
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/"+"textfile.txt");
            //FileReader fr = new FileReader(System.getProperty("user.dir")+"/src/main/resources/"+"textfile.txt");
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                textContent.add(line);
            }
            reader.close();
            System.out.println("------------------");
            for(int i=textContent.size()-3;i<textContent.size();i++){
                System.out.println(textContent.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* --------writing the extract contents to a new text file---------
        A writer uses default character encoding of the operating system by default. It also creates a new file if not exist,
        or overwrites the existing one.
        If you want to append text to an existing file, pass a boolean flag of true to constructor of the writer class:
         */
        try {
            //Here using APPEND mode with true flag - so everytime, same file will get updated at the end.
            FileWriter writer = new FileWriter(new File(".").getAbsoluteFile().getParentFile()+"/output/"+"MyFile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(String i: textContent) {
                bufferedWriter.write(i);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
