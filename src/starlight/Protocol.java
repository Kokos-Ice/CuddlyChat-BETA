package starlight;

import java.io.InputStream;
import java.io.IOException;

public class Protocol {
	public static byte[] decode(InputStream in) throws IOException {
		byte first = (byte) in.read();

		if (first == -1) {
			throw new IOException("End of stream");
		}

		int length;

		if (first >= 0) {
			length = first + 1;
		} else {
			length = (first & 0x1F) + 1;
			int count = (first & 0x60) >>> 5;

			for (int i = 0; i < count; i++) {
				length += in.read() << (i << 3) + 5;
			}
		}

		byte[] buffer = new byte[length];
		in.read(buffer, 0, length);
		return buffer;
	}

	public static byte[] encode(byte[] message) {
		int length = message.length - 1;
		byte[] len;

		if (length < 128) {
			len = new byte[] { (byte) length };
		} else {
			int count = 0;

			while (32 << (count + 1 << 3) <= length) {
				count++;
			}

			count++;
			len = new byte[count + 1];
			len[0] = (byte) (count << 5 | 0x80 | length & 0x1F);

			for (int i = 1; i < len.length; i++) {
				len[i] = (byte) (length >>> 8 * (i - 1) + 5);
			}
		}

		byte[] buffer = new byte[len.length + message.length];
		System.arraycopy(len, 0, buffer, 0, len.length);
		System.arraycopy(message, 0, buffer, len.length, message.length);
		return buffer;
	}
}
