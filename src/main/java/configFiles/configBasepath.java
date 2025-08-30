package configFiles;

import static Constants.constants.baseBookPath;
import static Constants.constants.baseLoginPath;
import static Constants.constants.baseSignUpPath;

public class configBasepath {

    public static String configBaseBookPath(){

        return baseBookPath = configReader.getProperty("baseBookPath");

    }
    public static String configBaseLoginPath(){

        return baseLoginPath = configReader.getProperty("baseLoginPath");

    }
    public static String configBaseSignUpPath(){

        return baseSignUpPath = configReader.getProperty("baseSignUppath");

    }
}
