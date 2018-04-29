package com.example.myapp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    13/03/2015 11:33
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class ReflectionBasedSerializer {
  private static final MyLog log = new MyLog();

  public static String toXmlStringIgnoreEx(Object o) throws RuntimeException {
    try {
      return toXmlString(o, 0, false);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException("Failed to serialize object: " + o.toString() , e);
    }
  }

  public static String toXmlString(Object o) throws IllegalAccessException {
    return toXmlString(o, 0, false);
  }

  public static String toXmlString(Object o, int depth, boolean includingStaticFields) throws IllegalAccessException {
    StringBuilder sb = new StringBuilder();
    try {
      toXmlString(o, o.getClass(), sb, depth, includingStaticFields);
    }
    catch (IllegalAccessException e) {
      throw e;
    }
    catch (Throwable t) {
      log.error("Failed to serialize. Partial: " + sb.toString(), t);
    }
    return sb.toString();
  }

  private static String valueToString(Object obj) {
    final String strValue = obj.toString();
    boolean containsInvalidXmlCharacters = (strValue.contains("<")) || (strValue.contains(">")) || (strValue.contains("\"")) || (strValue.contains("\'")) || (strValue.contains("&"));
    if (containsInvalidXmlCharacters) {
      return "<![CDATA[" + strValue.trim().replaceAll(">\\s*<", "><") + "]]>";
    }
    else {
      return strValue;
    }
  }


  private static void toXmlString(Object o, Class clazz, StringBuilder sb, int depth, boolean includingStaticFields) throws IllegalAccessException {
    if (isPrimitive(o)) {
      sb.append(valueToString(o));
      return;
    }

    if (o instanceof Object[]) {
      Object[] array = (Object[]) o;
      sb.append("<Array size=\"").append(array.length).append("\">");
      for (Object item : array) {
        toXmlString(item, item.getClass(), sb, depth-1, includingStaticFields);
      }
      sb.append("</Array>");
      return;
    }

    Field f[] = getFieldsFromClass(clazz, includingStaticFields);

    sb.append("<Object depth=\"").append(depth).append("\" type=\"").append(clazz.getSimpleName()).append("\" parents=\"").append(calculateParents(clazz)).append("\">");
    sb.append("<Fields>");
    for (Field aF : f) {
      if (aF!=null) {
        aF.setAccessible(true);
        final String fieldName = aF.getName();
        final Object fieldValue = aF.get(o);
        if (!"serialVersionUID".equals(fieldName) && fieldValue!=o) {
          sb.append("<Field>");
          sb.append("<Name>").append(fieldName).append("</Name>");
          if (fieldValue !=null && depth>0) {
            sb.append("<Value>");
            toXmlString(fieldValue, fieldValue.getClass(), sb, depth-1, includingStaticFields);
            sb.append("</Value>");
          }
          else {
            if (isPrimitive(o)) {
              sb.append(valueToString(o));
            }
            else {
              //sb.append("<Value><![CDATA[").append(fieldValue).append("]]></Value>");
              sb.append("<Value>").append(fieldValue).append("</Value>");
            }
          }

          sb.append("</Field>");
        }
      }
    }
    sb.append("</Fields>");

    sb.append("</Object>");
  }

  private static Field[] getFieldsFromClass(Class clazz, boolean includingStaticFields) {
    if (clazz==null) return new Field[0];

    final Field[] fieldsFromParent=getFieldsFromClass(clazz.getSuperclass(), includingStaticFields);

    final Field[] fieldsFromThis=clazz.getDeclaredFields();
    Field[] res = new Field[fieldsFromParent.length+fieldsFromThis.length];
    int index = 0;
    for (Field field : fieldsFromParent) {
      res[index++] = field;
    }

    for (Field field : fieldsFromThis) {
      if (includingStaticFields || !Modifier.isStatic(field.getModifiers())) {
        res[index++] = field;
      }
    }
    return res;
  }

  private static String calculateParents(Class clazz) {
    final StringBuilder result = new StringBuilder();
    addParentsToStringBuilder(result, clazz);
    return result.toString();
  }


  private static void addParentsToStringBuilder(StringBuilder sb, Class clazz) {
    if (clazz==null) return;
    final Class parent=clazz.getSuperclass();
    if (parent==null) return;
    sb.append(parent.getSimpleName()).append(",");
    addParentsToStringBuilder(sb, parent);

  }

  private static boolean isPrimitive(Object o) {
    return
        o instanceof Boolean |
            o instanceof Integer |
            o instanceof Long |
            o instanceof Float |
            o instanceof Double |
            o instanceof String
        ;
  }
}
