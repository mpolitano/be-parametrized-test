package utils;

import com.thoughtworks.xstream.XStream;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    XStream xstream;

    public Serializer() {
        xstream = new XStream();
        xstream.allowTypesByRegExp(new String[]{".*"});
    }

    public List<Object> readObjectsFromFile(String file) {
        List<Object> objs = new ArrayList<>();
        try {
            ObjectInputStream ois = xstream.createObjectInputStream(new FileInputStream(file));
            Object o;
            try {
                while (true) {
                    o = ois.readObject();
                    objs.add(o);
                }
            } catch (EOFException e) {
                /* The loop ends here */ }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot deserialize file: " + file);
        }
        return objs;
    }

    public Object clone(Object obj) {
        String objXML = xstream.toXML(obj);
        return xstream.fromXML(objXML);
    }


}
