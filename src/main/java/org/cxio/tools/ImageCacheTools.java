package org.cxio.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageCacheTools {

    final public static boolean DEBUG             = true;

    final public static String  DEFAULT_WRITE_URL = "http://52.35.61.6/image-cache/v1/image/";
    final public static String  DEFAULT_READ_URL  = "http://52.32.158.148:8080/";

    public final static BufferedImage readImage(final String url_base, final String id, final String image_format_name) throws IOException {
        final String url_str = (url_base.endsWith("/") ? (url_base + id) : (url_base + "/" + id)) + "." + image_format_name;
        final URL url = new URL(url_str);
        if (DEBUG) {
            System.out.println("Read URL : " + url);
        }
        return ImageIO.read(url);
    }

    public final static String postImage(final String url_base, final String id, final Image image, final String image_format_name, final int image_type) throws IOException {
        if (!image_format_name.equals("png") && !image_format_name.equals("jpg")) {
            throw new IllegalArgumentException("illegal image format name: " + image_format_name);
        }
        final BufferedImage bi = ImageCacheTools.toBufferedImage(image, image_type);
        if (bi == null) {
            throw new IOException("failed to create buffered image for id " + id);
        }
        final String url_str = url_base.endsWith("/") ? (url_base + image_format_name) : (url_base + "/" + image_format_name);
        final URL url = new URL(url_str + "/" + id);
        if (DEBUG) {
            System.out.println("Write URL: " + url);
        }
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "image/" + image_format_name);

        final OutputStream os = conn.getOutputStream();

        ImageIO.write(bi, image_format_name, os);

        os.flush();
        os.close();
        final StringBuilder sb = new StringBuilder();
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        }
        catch (final IOException e) {
            // Ignore, just return empty string
        }
        conn.disconnect();
        return sb.toString();
    }

    public final static BufferedImage toBufferedImage(final Image img, final int image_type) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        final BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), image_type);
        final Graphics2D g = bi.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bi;
    }

}
