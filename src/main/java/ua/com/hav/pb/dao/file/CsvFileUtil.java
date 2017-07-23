package ua.com.hav.pb.dao.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import static java.util.Arrays.asList;

/**
 * Created by sunny on 22.07.2017.
 */
public class CsvFileUtil {

    private File file;
    private List<Integer> parsebleColNumbs;

    public CsvFileUtil(String fileName, List<Integer> parsebleColNumbs) {
        file = new File(fileName);
        this.parsebleColNumbs = parsebleColNumbs;
    }

    public void ensureFile(String comment) {
        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("#" + comment);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<List<String>> read() {
        List<List<String>> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             Scanner scn = new Scanner(fileReader)) {

            while (scn.hasNext()) {
                String nextLine = scn.nextLine().trim();

                if (!(nextLine.length() > 1 && nextLine.substring(0, 2).contains("#"))) {
                    String[] split = nextLine.split("(?<!\\\\),");
                    for (int i = 0; i < split.length; i++) {
                        split[i] = split[i].trim().replace("\\", "");
                        if (parsebleColNumbs.contains(i)) {
                            String str = "";
                            char[] chars = split[i].toCharArray();
                            for (char ch : chars) {
                                if (ch >= '0' && ch <= '9') {
                                    str += ch;
                                }
                            }
                            split[i] = str;
                        }
                    }
                    List<String> line = new ArrayList<>();
                    for (int i = 0; i < split.length; i++) {
                        line.add(split[i]);
                    }
                    lines.add(line);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void write(List<List<String>> lines) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (List<String> line : lines) {
                boolean first = true;
                for (String el : line) {
                    if (first) {
                        first = false;
                    } else {
                        fileWriter.write(",");
                    }
                    fileWriter.write(el.replace(",", "\\,)").trim());
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
