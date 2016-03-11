package eng.soft.schoolfinder.request.libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helper {
	
    // make all conversion static so it is accessible to other class without new object
    public static String getResponse(InputStream reqIS) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(reqIS));
        
        while(true) {
            String line = br.readLine();
            
            if(line != null) {
                builder.append(line);
            }
            else {
                break;
            }
        }
        
        return builder.toString();
    }
    
}
