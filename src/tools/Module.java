package tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Brainy
 */
public class Module {
        private Hashtable i;
        private Hashtable g;
        private int id;
        private Hashtable values;
        private String protocolHash;
        private List names;
        private List f;
        private int startValue;
        private String protocolString;
        private int protocolIndex;
        private List n;

        public String getName() {
            return (String)this.names.get(this.id);
        } 
        
        public Integer getID() {
            return this.id;
        }
        
        public Hashtable getValues() {
            return this.values;
        }
        
        public String getHash() {
            return this.protocolHash;
        }
        
        public Object getValue(String key) {
            if (values == null) {
                return null;
            }
                    
            return values.get(key);
        }

         
        public static Module startUp(String moduleTree) throws Exception {
            Module instance = new Module();
            instance.parseTree(moduleTree);
            return instance;
        }

        private Module() {
            // empty
        }

        private Module(int id) {
            this.id = id;
            this.values = new Hashtable();
        }
        
        private Module(int id, Hashtable g, String protocolHash, List names, List f, Hashtable i) {
            this.id = id;
            this.g = g;
            this.protocolHash = protocolHash;
            this.names = names;
            this.f = f;
            this.i = i;
            
            this.values = new Hashtable();
        }

        private Module create(int id) {
            return new Module(id, this.g, this.protocolHash, this.names, this.f, this.i);
        }

        private void add(List list, int num, Object obj) {
            while (list.size() <= num)  {
                list.add(null);
            }
            list.set(num, obj);
        }

        public Module parse(String packet) {
            DataInput dataInput = null;
            
            try {
                dataInput = new DataInputStream(new ByteArrayInputStream(packet.substring(packet.indexOf("\0") + 1).getBytes("windows-1252")));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            
            Module result;
            try {
                short id = (short)dataInput.readShort();
                Module xb = this.create(id);
                this.read(xb, dataInput, id, xb);
                result = xb;
            }
            catch (Exception e)  {
                e.printStackTrace();
                return null;
            }
            return result;
        }
       
        private Object read(Module xb, DataInput dataInput, int id, Module xb2) throws IOException, Exception  {
            if (xb2 == null) {
                xb2 = this.create(id);
            }

            List list = (List)this.f.get(id);
            
            for (int i = 0; i < list.size(); i++) {
                Integer integer = (Integer)list.get(i);
                switch (integer) {
                    case 0: // Byte
                        return dataInput.readByte();
                    case 1: // Boolean
                        return dataInput.readBoolean();
                    case 2: // Byte
                        return dataInput.readByte();
                    case 3: // Short
                        return dataInput.readShort();
                    case 4: // Integer
                        return dataInput.readInt();
                    case 5: // Long
                        return dataInput.readLong();
                    case 6: // Float
                        return dataInput.readFloat();
                    case 7: // Double
                        return dataInput.readDouble();
                    case 8: // Char
                        return dataInput.readChar();
                    case 9: // String
                        String str = dataInput.readUTF();
                        if (str == null || str.length() == 0 || str.charAt(0) != 0)
                            return str;
                        
                            if (str.length() == 1)
                                return null;
                            return str.substring(1);
                    case 10: // BinaryTree
                            throw new Exception("BinaryTree");
                    case 11: // Array (Start)
                            i++;
                            integer = (Integer)list.get(i);
                            String text = (String)this.names.get(integer);
                            List list2 = new ArrayList();
                            xb2.b(text, list2);
                            while (dataInput.readByte() == 11) {
                                Object obj = this.read(xb, dataInput, integer, null);
                                obj = this.a(xb, integer, obj);
                                list2.add(obj);
                            }
                            i++;
                            break;
                    case 12: // Array (End)
                            throw new Exception("End of array");
                    case 13: // String
                        int len = dataInput.readByte();
                        if (len == 255) return null;
                        if (len >= 128) len = len - 128 << 16 | (dataInput.readByte()) << 8 | (dataInput.readByte());
                        StringBuilder builder = new StringBuilder(len + 2);
                        for (int _i = 0; _i < len; _i++) 
                            builder.append((char)dataInput.readByte());

                        return builder.toString();
                    default:
                            String text2 = (String)this.names.get(integer);
                            Object obj2 = this.read(xb, dataInput, integer, null);
                            obj2 = this.a(xb, integer, obj2);
                            xb2.b(text2, obj2);
                            break;
                }
            }
            return xb2;
        }

        public byte[] writeBytes(Module xb) {
            byte[] result;
            try {
                int num = xb.id;
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                DataOutput writer = new DataOutputStream(stream);
                writer.writeShort((short)num);
                this.write(xb, num, writer);
                result = stream.toByteArray();
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }

       private void write(Object obj, int id, DataOutput dataOutput) throws IOException, Exception {
            List list = (List)this.f.get(id);
            for (int i = 0; i < list.size(); i++) {
                int num = (Integer)list.get(i);
                switch (num) {
                    case 0:
                        dataOutput.writeByte(((Byte)obj).byteValue());
                        break;
                    case 1:
                        dataOutput.writeBoolean(((Boolean)obj).booleanValue());
                        break;
                    case 2:
                        dataOutput.writeByte(((Byte)obj).byteValue());
                        break;
                    case 3:
                        dataOutput.writeShort(((Short)obj).shortValue());
                        break;
                    case 4:
                        dataOutput.writeInt(((Integer)obj).intValue());
                        break;
                    case 5:
                        dataOutput.writeLong(((Long)obj).longValue());
                        break;
                    case 6:
                        dataOutput.writeFloat(((Float)obj).floatValue());
                        break;
                    case 7:
                        dataOutput.writeDouble(((Double)obj).doubleValue());
                        break;
                    case 8:
                        dataOutput.writeChar(((Integer)obj).intValue());
                        break;
                    case 9:
                        String s = (String)obj;
                        if (s == null || s.length() == 0 || s.charAt(0) != 0) {
                            dataOutput.writeUTF(s);
                            break;
                        }
 
                        if (s.length() == 1) {
                            dataOutput.writeUTF(null);
                            break;
                        }
 
                        dataOutput.writeUTF(s.substring(1));
                        break;
                    case 10:
                        throw new Exception("Not implemented yet: BinaryType");
                    case 11:
                        i++;
                        num = (Integer)list.get(i);
                        String text = (String)this.names.get(num);
                     
                        List list2 = (List)((Module)obj).getValue(text);
 
                        if (list2 != null) {
                            for (int j = 0; j < list2.size(); j++) {
                                dataOutput.writeByte(11);
                                this.write(list2.get(j), num, dataOutput);
                            }
                        }
                        dataOutput.writeByte(12);
                        i++;
                        break;
                    case 12:
                        throw new Exception("Not expected: ArrayEnd");
                    case 13:
                       
                        this.writeString((String)obj, dataOutput);
                        break;
                    default:
                  
                                   //   System.out.println((String)this.names.get(num) + "<<<<<<");
                        
                   
                         this.write(((Module)obj).getValue((String)this.names.get(num)), num, dataOutput);
                        break;
                }
            }
        }
       
        private void writeString(String text, DataOutput dataOutput) throws IOException {
            if (text == null) {
                dataOutput.writeByte(255);
            }
            else {
                int len = text.length();
                if (len < 128) {
                    dataOutput.writeByte(len);
                }
                else {
                    if (len > 8388608) {
                        throw new IOException("Encoded string too long: " + String.valueOf(len));
                    }
                    dataOutput.writeByte((int)(len >> 16 | 128));
                    dataOutput.writeByte((int)(len >> 8 & 255));
                    dataOutput.writeByte(len & 255);
                }
                if (len > 0) {
                    for (Character c : text.toCharArray()) {
                        dataOutput.writeByte((int)c);
                    }
                }
            }
        }

        private Object a(Module xb, Integer integer, Object obj) {
            Object obj2 = (this.i != null) ? this.i.get(integer) : null;
            if (obj2 == null)
                return obj;

            if (obj == null)
                xb.a(integer, obj);

            return obj;
        }

        private void a(Integer integer, Object obj) {
            if (this.n == null)
                this.n = new ArrayList();

            this.n.add(integer);
            this.n.add(obj);
        }

        public Module add(String name, Object value) {
            return this.b(name, value);
        }

        public Module createModule(String name) {
            return this.create((Integer)this.g.get(name));
        }

        private Module b(String text, Object obj) {
            if (obj == null)
                this.values.remove(text);
            else
                this.values.put(text, obj);

            return this;
        }

        private void set(String text) {
            this.protocolString = text;
            this.protocolIndex = 0;
        }

        private String getString(String text) {
            int index = this.protocolString.indexOf(text, this.protocolIndex);
            if (index < 0) {
                return null;
            }
            String result = this.protocolString.substring(this.protocolIndex, index);
            this.protocolIndex = index + text.length();
            return result;
        }

        private int convertToInt(String text) {
            String idString = this.getString(text);
            return Integer.parseInt(idString);
        }

        private boolean end(String text) {
            if (protocolString.indexOf(text, protocolIndex) == protocolIndex) {
                this.protocolIndex += text.length();
                return true;
            }
            return false;
        }

        private int b(int num) throws Exception {
            Integer obj = 0;
            do {
                num++;
                if (num >= this.f.size()) {
                    break;
                }
            }
            while (this.f.get(num) == null || ((List)this.f.get(num)).isEmpty() || ((List)this.f.get(num)).get(0).equals(obj));
            if (num < this.f.size()) {
                return num;
            }
            String exception = "Not enough enumeration rules found.";
            throw new Exception(exception);
        }

        private void parseTree(String str) throws Exception {
            String text = ";";
            String text2 = ":";
            this.set(str);
            this.protocolHash = this.getString(text);
            this.startValue = this.convertToInt(text);
            int num = this.startValue;
            this.names = new ArrayList();
            while (!this.end(text)) {
                this.add(this.names, num++, this.getString(text));
            }
            this.g = new Hashtable();
            int i;
            for (i = 0; i < this.names.size(); i++) {
                Object obj = this.names.get(i);
                if (obj != null) {
                    this.g.put(this.names.get(i), i);
                }
            }
            this.f = new ArrayList();
            i = this.startValue;
            while (!this.end(text2)) {
                List list = new ArrayList();
                this.add(this.f, i, list);
                while (!this.end(text)) {
                    list.add(this.convertToInt(text));
                }
                i++;
            }
            int num2 = -1;
            while (!this.end(text)) {
                num2 = this.b(num2);
                String text3 = (String)this.names.get(num2);
                Hashtable hashtable = new Hashtable();
                if (!this.g.containsKey(text3))
                    this.g.put(text3, hashtable);
                
                int num3 = 0;
                while (!this.end(text)) {
                    Object temp = this.getString(text);
                    num3++;
                    hashtable.put(temp, (byte)num3);
                }
            }
            this.set(null);
        }
    }  