package classwork.util.xml;

import classwork.params.entity.template.Fallback;
import classwork.util.AbstractFileReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class XmlFileReader extends AbstractFileReader {

    public XmlFileReader(Object mapper, String fileName) {
        super(mapper, fileName);
    }

    @Override
    public Fallback readFile() {
        Fallback business = null;
        System.out.println("Значение в business: " + business);
        System.out.println("XML   Считываем данные из файла " + fileName);
        XmlMapper o = (XmlMapper)mapper;
        try {
            business = o.readValue(new File(fileName), Fallback.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("XML   Данные считаны с диска из файла " + fileName);
        System.out.println("Значение business: " + business);
        return business;    }
}
