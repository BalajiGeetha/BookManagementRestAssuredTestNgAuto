package RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.*;
import static Constants.constants.*;

public class requestBodyCreation {

    public static void randomDataGeneration(){

        Random rand = new Random();
        fourDigitNumber = rand.nextInt(1000) + 1000;
        generatedNameString = rand.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLengthName)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        generatedSAuthorString = rand.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLengthAuthor)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


    }

    public static String createRequest(){
       randomDataGeneration();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("author",generatedSAuthorString);
        jsonNode.put("name",generatedNameString);
        jsonNode.put("published_year",fourDigitNumber);
        jsonNode.put("book_summary","A novel set in the Jazz Age on Long Island, chronicling the mysterious millionaire Jay Gatsby's unrequited love for the beautiful Daisy Buchanan.");

  return jsonNode.toPrettyString();
    }


}
