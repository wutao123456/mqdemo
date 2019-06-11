package hadoop.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/6/5 21:54
 */
public class TestCompress {

    public static void main(String[] args)throws Exception {

//        compress("C:/Users/admin/Desktop/wc/compress/hello.txt","org.apache.hadoop.io.compress.BZip2Codec");
//        compress("C:/Users/admin/Desktop/wc/compress/hello.txt","org.apache.hadoop.io.compress.GzipCodec");
//        compress("C:/Users/admin/Desktop/wc/compress/hello.txt","org.apache.hadoop.io.compress.DefaultCodec");

        decompress("C:/Users/admin/Desktop/wc/compress/hello.txt.deflate");

    }

    private static void compress(String fileName,String method)throws Exception{
        FileInputStream inputStream = new FileInputStream(fileName);

        Class classCodec = Class.forName(method);

        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(classCodec,new Configuration());

        FileOutputStream outputStream = new FileOutputStream(new File(fileName+codec.getDefaultExtension()));

        CompressionOutputStream cos = codec.createOutputStream(outputStream);

        IOUtils.copyBytes(inputStream,cos,1024*1024,false);

        IOUtils.closeStream(cos);
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);

    }

    private static void decompress(String fileName)throws Exception{
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(fileName));

        if(codec == null){
            System.out.println("can not compress");
            return;
        }

        FileInputStream inputStream = new FileInputStream(new File(fileName));
        CompressionInputStream cis = codec.createInputStream(inputStream);

        FileOutputStream outputStream = new FileOutputStream(new File(fileName+".decode"));

        IOUtils.copyBytes(cis,outputStream,1024*1024,false);

        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(cis);
        IOUtils.closeStream(inputStream);
    }
}
