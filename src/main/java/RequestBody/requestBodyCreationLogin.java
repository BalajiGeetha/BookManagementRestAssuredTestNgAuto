package RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static Constants.constants.fourDigitNumber;
import static Constants.constants.generatedEmailString;

public class requestBodyCreationLogin {

    public static ObjectNode createLoginRequest(String EmailString){


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("email",EmailString+"@gmail.com");
        jsonNode.put("password","str");


        return jsonNode;
    }
}
