package com.example.administrator.showpdfbyvipdemo;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ================================================
 *
 * @author：Vip 版    本：V4.1.4
 * 创建日期：2018/6/29
 * 描    述：配合在线预览pdf
 * 修订历史：
 * ================================================
 */
public class Base64Encoder extends CharacterEncoder {
    @Override
    protected int bytesPerAtom() {
        return 3;
    }

    @Override
    protected int bytesPerLine() {
        return 57;
    }

    private static final char[] PEM_ARRAY =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
                    'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='};

    @Override
    protected void encodeAtom(OutputStream paramOutputStream,
                              byte[] paramArrayOfByte, int paramInt1, int paramInt2)
            throws IOException {
        int i;
        int j;
        int k;
        if (paramInt2 == 1) {
            i = paramArrayOfByte[paramInt1];
            j = 0;
            k = 0;
            paramOutputStream.write(PEM_ARRAY[(i >>> 2 & 0x3F)]);
            paramOutputStream
                    .write(PEM_ARRAY[((i << 4 & 0x30) + (j >>> 4 & 0xF))]);
            paramOutputStream.write(61);
            paramOutputStream.write(61);
        } else if (paramInt2 == 2) {
            i = paramArrayOfByte[paramInt1];
            j = paramArrayOfByte[(paramInt1 + 1)];
            k = 0;
            paramOutputStream.write(PEM_ARRAY[(i >>> 2 & 0x3F)]);
            paramOutputStream
                    .write(PEM_ARRAY[((i << 4 & 0x30) + (j >>> 4 & 0xF))]);
            paramOutputStream
                    .write(PEM_ARRAY[((j << 2 & 0x3C) + (k >>> 6 & 0x3))]);
            paramOutputStream.write(61);
        } else {
            i = paramArrayOfByte[paramInt1];
            j = paramArrayOfByte[(paramInt1 + 1)];
            k = paramArrayOfByte[(paramInt1 + 2)];
            paramOutputStream.write(PEM_ARRAY[(i >>> 2 & 0x3F)]);
            paramOutputStream
                    .write(PEM_ARRAY[((i << 4 & 0x30) + (j >>> 4 & 0xF))]);
            paramOutputStream
                    .write(PEM_ARRAY[((j << 2 & 0x3C) + (k >>> 6 & 0x3))]);
            paramOutputStream.write(PEM_ARRAY[(k & 0x3F)]);
        }
    }
}
