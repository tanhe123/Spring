package net.xiayule.spring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tan on 14-12-15.
 */
public class ClassPathResource implements Resource {
    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        InputStream is = ClassLoader.getSystemResourceAsStream(this.path);

        if (is == null) {
            throw new FileNotFoundException("class path [" + path + "] cannot be opened because it does not exist");
        }

        return is;
    }
}
