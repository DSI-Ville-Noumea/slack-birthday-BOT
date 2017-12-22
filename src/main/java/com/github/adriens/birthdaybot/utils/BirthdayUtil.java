package com.github.adriens.birthdaybot.utils;

import com.github.adriens.birthdaybot.SlackBirthdayGreeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;

public class BirthdayUtil {

    public final static String DEFAULT_MESSAGE = "Happy Birthday @";
    private final static Logger logger = LoggerFactory.getLogger(BirthdayUtil.class);



    public static String getMessage(String username, String message){
        String msg;
        if(Objects.isNull(message) || message.length() == 0){
            msg = getRandomMessage(username);
            if(Objects.isNull(msg))
                msg = DEFAULT_MESSAGE + username;
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
            logger.error("Wishes file not found " + );
            return DEFAULT_MESSAGE + username;
        }
        finally {
            return message;
        }

    }
}
