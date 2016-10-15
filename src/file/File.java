package file;

import java.io.*;

public class File {
    /**
     * Writes the object to the file.
     *
     * @param filename the name of the file
     * @param obj      the object to write
     * @throws IOException
     */
    public static void writeObject(String filename, Object obj)
            throws IOException {
        ObjectOutputStream ostream = null;
        try {
            ostream = new ObjectOutputStream(new FileOutputStream(filename));
            ostream.writeObject(obj);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Reads object from file.
     *
     * @param filename the name of the file
     * @return the object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(String filename)
            throws IOException, ClassNotFoundException {
        ObjectInputStream istream = null;
        Object obj;
        try {
            istream = new ObjectInputStream(new FileInputStream(filename));
            obj = istream.readObject();
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (Exception e) {
            }
        }
        return obj;
    }
}
