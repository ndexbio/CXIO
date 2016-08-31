package org.cxio.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.cxio.aspects.datamodels.NodesElement;
import org.cxio.aspects.writers.NodesFragmentWriter;
import org.cxio.core.CxWriter;
import org.cxio.core.interfaces.AspectElement;
import org.junit.Test;

public class NodesFragmentWriterTest {

    @Test
    public void test1() throws IOException {

        final List<AspectElement> l0 = new ArrayList<AspectElement>();
        final OutputStream out0 = new ByteArrayOutputStream();
        final CxWriter w0 = CxWriter.createInstance(out0, false);

        w0.addAspectFragmentWriter(NodesFragmentWriter.createInstance());

        w0.start();
        w0.writeAspectElements(l0);
        w0.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"status\":[{\"error\":\"\",\"success\":true}]}]", out0.toString());

        final NodesElement n0 = new NodesElement("0");
        final NodesElement n1 = new NodesElement("1");
        final NodesElement n2 = new NodesElement("2", "name 2");
        final List<AspectElement> l1 = new ArrayList<AspectElement>();
        l1.add(n0);
        l1.add(n1);
        l1.add(n2);

        final OutputStream out1 = new ByteArrayOutputStream();
        final CxWriter w1 = CxWriter.createInstance(out1, false);

        final NodesFragmentWriter nfw = NodesFragmentWriter.createInstance();

        w1.addAspectFragmentWriter(nfw);

        w1.start();
        w1.writeAspectElements(l1);
        w1.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"nodes\":[{\"@id\":0},{\"@id\":1},{\"@id\":2,\"n\":\"name 2\"}]},{\"status\":[{\"error\":\"\",\"success\":true}]}]", out1.toString());

    }

    @Test
    public void test2() throws IOException {

        final NodesElement n0 = new NodesElement("0");
        final NodesElement n1 = new NodesElement("1");
        final NodesElement n2 = new NodesElement("2", "name 2", "reps 2");
        final List<AspectElement> l1 = new ArrayList<AspectElement>();
        l1.add(n0);
        l1.add(n1);
        l1.add(n2);

        final OutputStream out1 = new ByteArrayOutputStream();
        final CxWriter w1 = CxWriter.createInstance(out1, false);

        final NodesFragmentWriter nfw = NodesFragmentWriter.createInstance();

        w1.addAspectFragmentWriter(nfw);

        w1.start();
        w1.writeAspectElements(l1);
        w1.end(true, "");

        assertEquals("[" + TestUtil.NUMBER_VERIFICATION + ",{\"nodes\":[{\"@id\":0},{\"@id\":1},{\"@id\":2,\"n\":\"name 2\",\"r\":\"reps 2\"}]},{\"status\":[{\"error\":\"\",\"success\":true}]}]",
                     out1.toString());

    }

}
