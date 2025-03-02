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
