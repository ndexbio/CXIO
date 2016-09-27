package org.cxio.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.cxio.aspects.writers.VisualPropertiesFragmentWriter;
import org.cxio.core.CxWriter;
import org.cxio.core.interfaces.AspectElement;
import org.junit.Test;

public class CyVisualPropertiesFragmentWriterTest {

    @Test
    public void test1() throws IOException {
        final List<AspectElement> l0 = new ArrayList<AspectElement>();
        final OutputStream out0 = new ByteArrayOutputStream();
        final CxWriter w = CxWriter.createInstance(out0, false);
        w.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w.start();
        w.writeAspectElements(l0);
        w.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"status\":[{\"error\":\"\",\"success\":true}]}]", out0.toString());

        final CyVisualPropertiesElement c1 = new CyVisualPropertiesElement("nodes:default");

        c1.putProperty("text-opacity", "1.0");
        c1.putProperty("width", "40.0");
        c1.putProperty("background-color", "rgb(204,204,255)");

        final CyVisualPropertiesElement c2 = new CyVisualPropertiesElement("nodes:selected");
        c2.putProperty("background-color", "rgb(255,255,0)");

        final CyVisualPropertiesElement c3 = new CyVisualPropertiesElement("nodes");
        c3.setApplies_to(1L);
        //c3.addAppliesTo("2");
        c3.putProperty("background-color", "rgb(0,0,0)");

        final List<AspectElement> l1 = new ArrayList<AspectElement>();
        l1.add(c1);
        l1.add(c2);
        l1.add(c3);

        final OutputStream out1 = new ByteArrayOutputStream();
        final CxWriter w1 = CxWriter.createInstance(out1, false);
        w1.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w1.start();
        w1.writeAspectElements(l1);
        w1.end(true, "");

        assertEquals("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},"
        		+ "{\"cyVisualProperties\":[{\"properties_of\":\"nodes:default\",\"properties\":{\"background-color\":\"rgb(204,204,255)\",\"text-opacity\":\"1.0\",\"width\":\"40.0\"}},"
        							     + "{\"properties_of\":\"nodes:selected\",\"properties\":{\"background-color\":\"rgb(255,255,0)\"}},"
        							     + "{\"properties_of\":\"nodes\",\"applies_to\":1,\"properties\":{\"background-color\":\"rgb(0,0,0)\"}}]},"
        		+ "{\"status\":[{\"error\":\"\",\"success\":true}]}]",
                     out1.toString());

    }

    @Test
    public void test2() throws IOException {
        final List<AspectElement> l0 = new ArrayList<AspectElement>();
        final OutputStream out0 = new ByteArrayOutputStream();
        final CxWriter w = CxWriter.createInstance(out0, false);
        w.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w.start();
        w.writeAspectElements(l0);
        w.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"status\":[{\"error\":\"\",\"success\":true}]}]", out0.toString());

        final CyVisualPropertiesElement c1 = new CyVisualPropertiesElement("nodes:default");

        c1.putProperty("text-opacity", "1.0");
        c1.putProperty("width", "40.0");
        c1.putProperty("background-color", "rgb(204,204,255)");
        c1.putMapping("NODE_COLOR", "cont", "rgb12,0-0-0\"asdef\"");

        final CyVisualPropertiesElement c2 = new CyVisualPropertiesElement("nodes:selected");
        c2.putProperty("background-color", "rgb(255,255,0)");

        final CyVisualPropertiesElement c3 = new CyVisualPropertiesElement("nodes");
        c3.setApplies_to(1L);
     //   c3.addAppliesTo("2");
        c3.putProperty("background-color", "rgb(0,0,0)");

        final List<AspectElement> l1 = new ArrayList<AspectElement>();
        l1.add(c1);
        l1.add(c2);
        l1.add(c3);

        final OutputStream out1 = new ByteArrayOutputStream();
        final CxWriter w1 = CxWriter.createInstance(out1, false);
        w1.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w1.start();
        w1.writeAspectElements(l1);
        w1.end(true, "");

        assertEquals("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},{\"cyVisualProperties\":[{\"properties_of\":\"nodes:default\",\"properties\":{\"background-color\":\"rgb(204,204,255)\",\"text-opacity\":\"1.0\",\"width\":\"40.0\"},\"mappings\":{\"NODE_COLOR\":{\"type\":\"cont\",\"definition\":\"rgb12,0-0-0\\\"asdef\\\"\"}}},{\"properties_of\":\"nodes:selected\",\"properties\":{\"background-color\":\"rgb(255,255,0)\"}},{\"properties_of\":\"nodes\",\"applies_to\":1,\"properties\":{\"background-color\":\"rgb(0,0,0)\"}}]},{\"status\":[{\"error\":\"\",\"success\":true}]}]",
                     out1.toString());

    }

    @Test
    public void test3() throws IOException {
        final List<AspectElement> l0 = new ArrayList<AspectElement>();
        final OutputStream out0 = new ByteArrayOutputStream();
        final CxWriter w = CxWriter.createInstance(out0, false);
        w.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w.start();
        w.writeAspectElements(l0);
        w.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"status\":[{\"error\":\"\",\"success\":true}]}]", out0.toString());

        final CyVisualPropertiesElement c1 = new CyVisualPropertiesElement("nodes:default");

        c1.putProperty("text-opacity", "1.0");
        c1.putProperty("width", "40.0");
        c1.putProperty("background-color", "rgb(204,204,255)");
        c1.putMapping("NODE_COLOR", "cont", "rgb12,0-0-0\"asdef\"");

        final CyVisualPropertiesElement c2 = new CyVisualPropertiesElement("nodes:selected");
        c2.putProperty("background-color", "rgb(255,255,0)");

        final CyVisualPropertiesElement c3 = new CyVisualPropertiesElement("nodes");
        c3.setApplies_to(1L);
    //    c3.addAppliesTo("2");
        c3.putProperty("background-color", "rgb(0,0,0)");

        final List<AspectElement> l1 = new ArrayList<AspectElement>();
        l1.add(c1);
        l1.add(c2);
        l1.add(c3);

        c1.putDependency("dependency1", "true");

        final OutputStream out1 = new ByteArrayOutputStream();
        final CxWriter w1 = CxWriter.createInstance(out1, false);
        w1.addAspectFragmentWriter(VisualPropertiesFragmentWriter.createInstance());

        w1.start();
        w1.writeAspectElements(l1);
        w1.end(true, "");

        assertEquals("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},{\"cyVisualProperties\":[{\"properties_of\":\"nodes:default\",\"properties\":{\"background-color\":\"rgb(204,204,255)\",\"text-opacity\":\"1.0\",\"width\":\"40.0\"},\"dependencies\":{\"dependency1\":\"true\"},\"mappings\":{\"NODE_COLOR\":{\"type\":\"cont\",\"definition\":\"rgb12,0-0-0\\\"asdef\\\"\"}}},{\"properties_of\":\"nodes:selected\",\"properties\":{\"background-color\":\"rgb(255,255,0)\"}},{\"properties_of\":\"nodes\",\"applies_to\":1,\"properties\":{\"background-color\":\"rgb(0,0,0)\"}}]},{\"status\":[{\"error\":\"\",\"success\":true}]}]",
                     out1.toString());

    }

}
