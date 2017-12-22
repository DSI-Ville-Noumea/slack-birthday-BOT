package com.github.adriens.birthdaybot.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;

public class BirthdayUtil {

    public static String getMessage(String username, String message){
        String msg;
        if(Objects.isNull(message) || message.length() == 0){
            msg = getRandomMessage(username);
            if(Objects.isNull(msg))
                msg = "Happy birthday @" + username;
        }
        else{
            msg = message;
        }
        return msg;
    }

    public static String getRandomMessage(String username){

        File f = new File("test.txt");
        String message = null;

        try (Scanner fileScanner = new Scanner(f)){

            List<String> messages = new ArrayList<>();

            while(fileScanner.hasNextLine()){
                messages.add(fileScanner.nextLine());
            }

            Random random = new Random();
            int randomInt = random.nextInt(messages.size());

            message =  MessageFormat.format(messages.get(randomInt), username);

        }catch (FileNotFoundException e){

        }
        finally {
            return message;
        }

    }
}
