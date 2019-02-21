package factory.app;


import classwork.params.da.JsonParameterSerializeReader;
import classwork.params.da.JsonParameterSerializeWriter;
import classwork.params.entity.template.Fallback;
import classwork.util.AbstractFileReader;
import classwork.util.AbstractFileWriter;
import factory.method.FileReaderFactory;
import factory.method.FileWriterFactory;


public class Converter {

    public static final String INPUT_PARAMETERS = "parameters.v1.";
    //    public static final String INPUT_TYPE_FILE = "json";
    public static String INPUT_TYPE_FILE = "";
    public static final String TEMP_V_1_OUT = "temp.v1.out";
    public static final String RESULT_PARAMETERS = "result_parameters.v1.";
    //    public static final String RESULT_TYPE_FILE = "json";
    public static String RESULT_TYPE_FILE = "";

    /**
     * Из файла parameters.json считываем данные
     * После сериазилуем в файл temp.out
     * Далее перегоняем потоком в result_parameters.json
     * <p>
     * Смысл задания - сериализовать объект в файл.
     * Из файла считать - записать в новый файл и сравнить с исходным
     * <p>
     * fileReader->serializeWriter
     * parameters.v1.json -> temp.v1.out
     */

    //todo нужно реализовать паттерн фабричный метод для считывания из файлов разного формата
    public static void main(String... args) throws Exception {
        INPUT_TYPE_FILE = args[0];
        RESULT_TYPE_FILE = args[1];
        Fallback business = null;

        FileReaderFactory readerFactory = FileReaderFactory.newFileReaderFactory(INPUT_PARAMETERS + INPUT_TYPE_FILE);
        AbstractFileReader fileReader = readerFactory.fileReader(INPUT_TYPE_FILE);
        business = fileReader.readFile();


        JsonParameterSerializeWriter mySerializer = new JsonParameterSerializeWriter();
        System.out.println("Записываем данные в файл " + TEMP_V_1_OUT);
        mySerializer.customSerializeWriter(business, TEMP_V_1_OUT);
        System.out.println("Данные записаны на диск в файл " + TEMP_V_1_OUT);
// ==========================================================

        business = null;
        System.out.println("Значение business: " + business);
        JsonParameterSerializeReader myReader = new JsonParameterSerializeReader();
        System.out.println("СЕРИАЛИЗОВАННЫЕ Считываем данные из файла " + TEMP_V_1_OUT);
        business = myReader.customSerializeReader(TEMP_V_1_OUT);
        System.out.println("СЕРИАЛИЗОВАННЫЕ Данные считаны с диска из файл " + TEMP_V_1_OUT);
        System.out.println("Значение business: " + business);

        FileWriterFactory writerFactory = FileWriterFactory.newFileWriterFactory(RESULT_PARAMETERS, business);
        AbstractFileWriter fileWriter = writerFactory.fileWriter(RESULT_TYPE_FILE);
        fileWriter.writeFile();

    }
}

