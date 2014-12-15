package net.xiayule.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tan on 14-12-15.
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
