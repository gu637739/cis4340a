// Rule 14 - Serialization (SER)
// SER10-J: Avoid memory and resource leaks during serialization
// Non-compliant code:
class SensorData implements Serializable {
  // 1 MB of data per instance!
  ...
  public static SensorData readSensorData() {...}
  public static boolean isAvailable() {...}
}
 
class SerializeSensorData {
  public static void main(String[] args) throws IOException {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream("ser.dat")));
      while (SensorData.isAvailable()) {
        // Note that each SensorData object is 1 MB in size
        SensorData sd = SensorData.readSensorData();
        out.writeObject(sd);
      }
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}

// Compliant solution:
class SerializeSensorData {
  public static void main(String[] args) throws IOException {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream("ser.dat")));
      while (SensorData.isAvailable()) {
        // Note that each SensorData object is 1 MB in size
        SensorData sd = SensorData.readSensorData();
        out.writeObject(sd);
        out.reset(); // Reset the stream
      }
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}
