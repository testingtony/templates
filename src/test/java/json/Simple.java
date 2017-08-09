package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Simple {
    public String table_name;
    public Integer id;
    public Simple [] children;
    public Person owner;

    public Simple() {

    }

    public Simple(int id, String name) {
        this.id = id;
        this.table_name = name;
    }

    public static String getURL() {
        return "/api/login";
    }

    public String toString() {
        return toString("");
    }

    public String toString(String indent) {
        StringBuilder sb = new StringBuilder(indent).append("// - Simple -\n");
        sb.append(indent).append("id:").append(id).append("\n");
        sb.append(indent).append("name:").append(table_name).append("\n");
        sb.append(indent).append("person: {" + "id:" + owner.id + ", name" + owner.name + "}\n");
        sb.append(indent).append("children: [");
        if (children == null) {
            sb.append(indent).append("null");
        } else {
            sb.append("\n");
            for (Simple child : children) {
                sb.append(child.toString( indent + "   "));
            }
        }
        sb.append(indent).append("]\n");
        return sb.toString();
    }

    public static void main(String [] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

    //    mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Simple root = new Simple(1, "root");
        root.owner = new Person(101, "owner 101");
        Simple l1_1 = new Simple(2, "level 1.1");
        l1_1.owner = new Person(102, "owner 102");
        Simple l1_2 = new Simple(3, "level 1.2");
        l1_2.owner = new Person(103, "owner 103");
        Simple l1_1_1 = new Simple(4, "level 1.1.1");
        l1_1_1.owner = new Person(104, "owner 104");
        l1_1_1.children = new Simple[0];
        root.children = new Simple[] {l1_1, l1_2};
        l1_1.children = new Simple[] {l1_1_1};

        System.out.println(root.toString());

        String s = mapper.writeValueAsString(root);

        System.out.println(s);

        String unmap = "{\"table_name\":\"root\",\"id\":1,\"children\":[{\"table_name\":\"level 1.1\",\"id\":2,\"children\":[{\"table_name\":\"level 1.1.1\",\"id\":4,\"children\":[],\"owner\":{\"id\":104,\"name\":\"owner 104\"}}],\"owner\":{\"id\":102,\"name\":\"owner 102\"}},{\"table_name\":\"level 1.2\",\"id\":3,\"children\":null,\"owner\":{\"id\":103,\"name\":\"owner 103\"}}],\"owner\":{\"id\":101,\"name\":\"owner 101\"}}";

        Simple unmapped = mapper.readValue(unmap, Simple.class);

        System.out.println(unmapped);

        /*
            public String table_name;
    public Integer id;
    public Simple [] children;
    public Person owner;
         */
        Map<String, Object> thing = new HashMap<String, Object>();
        thing.put("table_name", "name");
        thing.put("id", 12);
        Simple [] children = new Simple[]{root};
        thing.put("children", children);
        Map<String, Object> person = new HashMap<>();
        person.put("id", 104);
        person.put("name", "name 104");
        thing.put("owner", person);

        s = mapper.writeValueAsString(thing);

        System.out.println(s);

        unmapped = mapper.readValue(s, Simple.class);

        System.out.println(unmapped);


    }



}
