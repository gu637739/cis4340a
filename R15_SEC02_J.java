// Rule 15 - Platform Security (SEC)
// SEC02-J: Do not base security checks on untrusted sources
// Non-compliant code:
public RandomAccessFile openFile(final java.io.File f) {
  askUserPermission(f.getPath());
  // ...
  return (RandomAccessFile)AccessController.doPrivileged(new PrivilegedAction <Object>() {
    public Object run() {
      return new RandomAccessFile(f, f.getPath());
    }
  });
}

//Compliant Solution 
public RandomAccessFile openFile(java.io.File f) {
  final java.io.File copy = new java.io.File(f.getPath());
  askUserPermission(copy.getPath());
  // ...
  return (RandomAccessFile)AccessController.doPrivileged(new PrivilegedAction <Object>() {
    public Object run() {
      return new RandomAccessFile(copy, copy.getPath());
    }
  });
}
