package desmedt.bac.swing.quiz;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    
    int id;
    String question;
    HashMap<String,Boolean> answers = new HashMap<>(4);
    
    public Question(String source) {
        final String[] data = source.split("_");
        id = Integer.parseInt(data[0]) + 1;
        question = data[1];
        answers.put(data[2], true);
        answers.put(data[3], false);
        answers.put(data[4], false);
        answers.put(data[5], false);
    }
    
    public int getId() {
        return id;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String[] getAnswers() {
        List<String> list = Arrays.asList(answers.keySet().toArray(new String[0]));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
    
    public boolean isCorrect(String guess){
        return answers.get(guess);
    }
    
    @Override
    public String toString() {
        
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(" ");
        builder.append(this.id);
        builder.append(". ");
        builder.append(this.question);
        builder.append(System.lineSeparator());
    
        for (Map.Entry<String, Boolean> entry : answers.entrySet()) {
            builder.append(String.format("[%s] ", entry.getValue() ? "V" : "X"));
            builder.append(entry.getKey());
            builder.append(System.lineSeparator());
        }
        
        return builder.toString();
    }
}