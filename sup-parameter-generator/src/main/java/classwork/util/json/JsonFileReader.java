package classwork.util.json;

import classwork.params.da.JsonParameterSerializeReader;
import classwork.params.entity.template.Fallback;
import classwork.util.AbstractFileReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileReader extends AbstractFileReader {

    public JsonFileReader(ObjectMapper mapper, String fileName) {
        super(mapper, fileName);
    }

    @Override
    public Fallback readFile() {

        Fallback business = null;
        System.out.println("Значение в business: " + business);
        System.out.println("JSON   Считываем данные из файла " + fileName);
        ObjectMapper o = (ObjectMapper)mapper;
        try {
            business = o.readValue(new File(fileName), Fallback.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON   Данные считаны с диска из файла " + fileName);
        System.out.println("Значение business: " + business);
        return business;
    }
}
