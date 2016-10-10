package org.cxio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.SortedMap;

import org.cxio.aspects.datamodels.CartesianLayoutElement;
import org.cxio.core.CxElementReader;
import org.cxio.core.CxElementReader2;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.metadata.MetaDataCollection;
import org.cxio.util.CxioUtil;
import org.junit.Test;

public class CartesianLayoutFragmentReaderETest {

    @Test
    public void test1() throws IOException {
        final String t0 = "[" + TestUtil.NUMBER_VERIFICATION + ",{\"cartesianLayout\":[{\"node\":0,\"x\":\"123\",\"y\":\"456\"}]},"
                + "{\"cartesianLayout\":[{\"node\":1,\"x\":\"3\",\"y\":\"4\",\"z\":\"2\"}]}" + "]";

        final CxElementReader p = CxElementReader.createInstance(t0, CxioUtil.getAllAvailableAspectFragmentReaders());
        final SortedMap<String, List<AspectElement>> r0 = CxElementReader.parseAsMap(p);

        assertTrue("failed to parse " + CartesianLayoutElement.ASPECT_NAME + " aspect", r0.containsKey(CartesianLayoutElement.ASPECT_NAME));

        assertFalse("failed to parse " + CartesianLayoutElement.ASPECT_NAME + " aspect", r0.get(CartesianLayoutElement.ASPECT_NAME).isEmpty());

        assertTrue("failed to parse expected number of " + CartesianLayoutElement.ASPECT_NAME + " aspects", r0.get(CartesianLayoutElement.ASPECT_NAME).size() == 2);

        final List<AspectElement> aspects = r0.get(CartesianLayoutElement.ASPECT_NAME);

        assertTrue("failed to get expected instance", aspects.get(0) instanceof CartesianLayoutElement);

        final CartesianLayoutElement a0 = (CartesianLayoutElement) aspects.get(0);

        assertEquals(a0.getNode(), 0);
        assertTrue(a0.getX().equals("123"));
        assertTrue(a0.getY().equals("456"));
        assertTrue(a0.getZ().equals("0"));
        assertTrue(a0.isZset() == false);

        final CartesianLayoutElement a1 = (CartesianLayoutElement) aspects.get(1);

        assertEquals(a1.getNode(), 1);
        assertTrue(a1.getX().equals("3"));
        assertTrue(a1.getY().equals("4"));
        assertTrue(a1.getZ().equals("2"));
        assertTrue(a1.isZset() == true);
    }
    
    
 /*   @Test
    public void test2() throws IOException {
        final String t0 = "[" + TestUtil.NUMBER_VERIFICATION + ",{\"cartesianLayout\":[{\"node\":0,\"x\":\"123\",\"y\":\"456\"}]},"
                + "{\"cartesianLayout\":[{\"node\":1,\"x\":\"3\",\"y\":\"4\",\"z\":\"2\"}]}" + "]";

        InputStream stream = new ByteArrayInputStream(t0.getBytes(StandardCharsets.UTF_8));
        final CxElementReader2 p =  new CxElementReader2(stream, CxioUtil.getAllAvailableAspectFragmentReaders());

        for ( AspectElement e : p) {
        	System.out.println( e.toString());
        }
    } */
    
    
    @Test
    public void test3() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("hang_test.cx").getFile());
        FileInputStream in = new FileInputStream (file);
        
        CxElementReader2 p =  new CxElementReader2(in, CxioUtil.getAllAvailableAspectFragmentReaders());
        
        MetaDataCollection pre = p.getPreMetadata();
        
        for ( AspectElement e : p) {
        	System.out.println( e.toString());
        }
    }
    

}
