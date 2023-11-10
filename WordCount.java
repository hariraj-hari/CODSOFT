package com.example.codsoft;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class WordCount extends Application {
    String wordCount;
    String[] count;
    int Count;
    Map<String, Integer> wordFrequency;
    Set<String> stopWords;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("WORD COUNTER - CODSOFT");
        Label textInputLabel = new Label("Enter The Text: ");
        Label orlabel = new Label("Or");
        Label wordCountShow = new Label("Word Counts: ");
        Label uniqueWordsLabel = new Label("Unique Words: ");
        Label StopWord = new Label("Stop Word: ");
        TextArea fileReader = new TextArea();
        Button chooser = new Button("Choose File");

        stopWords = new HashSet<>(Arrays.asList("the", "and", "in", "to", "of", "a"));
        wordFrequency = new HashMap<>();

        EventHandler<ActionEvent> fileTexts = e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Texts", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        fileContent.append(line).append("\n");
                    }
                    fileReader.setText(fileContent.toString());
                    wordCount = fileContent.toString();
                    count = wordCount.split("\\s|\\p{Punct}");

                    Count = 0;
                    wordFrequency.clear();

                    for (String word : count) {
                        if (!word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
                            Count++;
                            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                        }
                    }

                    wordCountShow.setText("Word Count: " + Count);
                    uniqueWordsLabel.setText("Unique Words: " + wordFrequency.size());
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        };

        fileReader.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String newText = fileReader.getText();
                int nonStopWordCount = 0;
                int stopWordCount = 0;

                count = new String[0]; // Reset the count
                wordFrequency.clear();

                try (BufferedReader reader = new BufferedReader(new StringReader(newText))) {
                    String line, wordcount;

                    StringBuilder filecontent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        filecontent.append(line).append('\n');
                    }
                    wordcount = filecontent.toString();
                    count = wordcount.split("\\s|\\p{Punct}");

                    for (String content : count) {
                        if (!content.isEmpty()) {
                            if (stopWords.contains(content.toLowerCase())) {
                                stopWordCount++;
                            } else {
                                nonStopWordCount++;
                                wordFrequency.put(content, wordFrequency.getOrDefault(content, 0) + 1);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Count = nonStopWordCount;
                wordCountShow.setText("Word Count (Non-Stop Words): " + nonStopWordCount);
                StopWord.setText("Stop Words Count: " + stopWordCount);
                uniqueWordsLabel.setText("Unique Words: " + wordFrequency.size());
            }
        });


        Image imageIcon = new Image("D:\\file\\application-icon.png");
        stage.getIcons().add(imageIcon);

        chooser.setOnAction(fileTexts);

        GridPane panel = new GridPane();
        panel.add(textInputLabel, 1, 2);
        textInputLabel.setStyle("-fx-font-size: 20px");
        panel.add(fileReader, 3, 2, 2, 3);
        panel.add(orlabel, 1, 3);
        orlabel.setStyle("-fx-margin: 0 3px");
        panel.add(chooser, 1, 4);
        panel.add(wordCountShow, 4, 5);
        panel.add(uniqueWordsLabel, 4, 6);
        panel.add(StopWord, 4, 7);
        panel.setVgap(30);
        panel.setHgap(30);
        Scene scene = new Scene(panel, 750, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOpacity(0.9);
        stage.setIconified(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
