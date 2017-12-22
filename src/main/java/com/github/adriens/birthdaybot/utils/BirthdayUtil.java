package com.github.adriens.birthdaybot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;

public class BirthdayUtil {

    public final static String DEFAULT_MESSAGE = "Happy Birthday @";
    public final static String WISHES_FILENAME = "data/wishes.txt";
    private final static Logger logger = LoggerFactory.getLogger(BirthdayUtil.class);



    public static String getMessage(String username, String message){

        String msg = null;

        if(Objects.isNull(message) || message.length() == 0){
            msg = getRandomMessage(username);
            if(Objects.isNull(msg) || msg.length() == 0)
                msg = DEFAULT_MESSAGE + username;
        }
        else{
            if(!message.contains("@" + username))
                msg = "@" + username + " " + message;
        }
        return msg;
    }

    public static String getRandomMessage(String username){

        File f = new File(WISHES_FILENAME);
        String message = null;

        try (Scanner fileScanner = new Scanner(f)){

            List<String> messages = new ArrayList<>();

            while(fileScanner.hasNextLine()){
                messages.add(fileScanner.nextLine());
            }

            if(messages.size() == 0)
                message = DEFAULT_MESSAGE + username;
            else {
                Random random = new Random();
                int randomInt = random.nextInt(messages.size());

                message = MessageFormat.format(messages.get(randomInt), "@" + username);
            }
        }catch (FileNotFoundException e){
            logger.error("Wishes file not found " + WISHES_FILENAME);
            message = DEFAULT_MESSAGE + username;
        }
        finally {
            return message;
        }

    }
}
