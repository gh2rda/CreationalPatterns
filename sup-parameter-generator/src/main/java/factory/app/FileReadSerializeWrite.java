package factory.app;

import classwork.params.da.JsonParameterReader;
import classwork.params.da.JsonParameterSerializeWriter;
import classwork.params.entity.template.Fallback;
import classwork.util.AbstractFileReader;
import classwork.util.AbstractFileWriter;
import factory.method.FileReaderFactory;
import factory.method.FileWriterFactory;

/**
 * @author Evgeni Korolev <SBT-Korolev-EE@mail.ca.sbrf.ru>
 * @date 03.04.2018
 */
public class FileReadSerializeWrite {
	
	
	public static final String TEMP_V_1_OUT = "temp.v1.out";
//	public static final String TEMP_V_1_OUT = "temp.v2.out";

	/**
     * Из файла parameters.json считываем данные
     * После сериазилуем в файл temp.out
     * Далее перегоняем потоком в result_parameters.json
     *
     * Смысл задания - сериализовать объект в файл.
     * Из файла считать - записать в новый файл и сравнить с исходным
     *
	 * fileReader->serializeWriter
	 * parameters.v1.json -> temp.v1.out
	 *
     * */
	
	//todo нужно реализовать паттерн фабричный метод для считывания из файлов разного формата
    public static void main(String... args) throws Exception {
        
//        String fileName = "parameters.v1.json";
        String fileName = "parameters.v1.xml";
		String typeFile = fileName.substring(fileName.lastIndexOf(".")+1);
        Fallback business = null;

		FileReaderFactory readerFactory = FileReaderFactory.newFileReaderFactory(fileName);
		AbstractFileReader fileReader = readerFactory.fileReader(typeFile);
		business = fileReader.readFile();

//		AbstractFileWriter fileXmlWriter = readerFactory.fileWriter("xml");
//		fileXmlWriter.writeFile();

        JsonParameterSerializeWriter mySerializer = new JsonParameterSerializeWriter();
	    System.out.println("Записываем данные в файл " + TEMP_V_1_OUT);
        mySerializer.customSerializeWriter(business, TEMP_V_1_OUT);
	    System.out.println("Данные записаны на диск в файл " + TEMP_V_1_OUT);
	    
    }
}
