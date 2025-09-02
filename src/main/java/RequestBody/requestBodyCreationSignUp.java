package RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Random;

import static Constants.constants.*;

public class requestBodyCreationSignUp {

    public static void randomLoginDataGeneration(){

        Random rand = new Random();
        fourDigitNumber = rand.nextInt(1000) + 1000;
        generatedEmailString = rand.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLengthAuthor)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


    }

    public static ObjectNode createSignUpRequest(){
        randomLoginDataGeneration();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("id",fourDigitNumber);
        jsonNode.put("email",generatedEmailString+"@gmail.com");
        jsonNode.put("password","str");


        return jsonNode;
    }
}
