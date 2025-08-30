package RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class requestBodyUpdate {

    public static String updateRequest(String author,String name,int year,String summary){


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("author",author);
        jsonNode.put("name",name);
        jsonNode.put("published_year",year);
        jsonNode.put("book_summary",summary);

        return jsonNode.toPrettyString();
    }
}
