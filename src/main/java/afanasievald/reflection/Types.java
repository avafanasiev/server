package afanasievald.reflection;

public enum Types {
  BYTE,
  INT,
  BOOLEAN,
  LONG,
  DOUBLE,
  FLOAT,
  SHORT,
  CHAR,
  STRING;

  public static Types getType(Class<?> clazz) {
    String className = clazz.getSimpleName().toUpperCase();
    return Types.valueOf(className);
  }
}
