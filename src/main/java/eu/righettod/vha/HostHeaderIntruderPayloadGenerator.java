package eu.righettod.vha;

import burp.IIntruderPayloadGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Generate and handle the payloads, here HTTP Request "Host" header values.
 */
public class HostHeaderIntruderPayloadGenerator implements IIntruderPayloadGenerator {

    /**
     * Extension configuration bundle
     */
    private ResourceBundle cfg;

    /**
     * List of probing values that will be used in the HTTP request "Host" header
     */
    final private List<String> probeValues;

    /**
     * Index to follow the iteration on payloads for this class instance
     */
    private int positionIndex = 0;


    /**
     * Constructor
     *
     * @param theCfg Extension configuration bundle
     */
    public HostHeaderIntruderPayloadGenerator(ResourceBundle theCfg) {
        this.cfg = theCfg;
        this.probeValues = Collections.unmodifiableList(this.buildHostTestList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasMorePayloads() {
        return (this.positionIndex < this.probeValues.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getNextPayload(byte[] baseValue) {
        byte[] p = this.probeValues.get(this.positionIndex).getBytes();
        this.positionIndex++;
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.positionIndex = 0;
    }

    /**
     * Build the list of probing values that will be used in the HTTP request "Host" header.
     *
     * @return The list of probing values
     */
    private List<String> buildHostTestList() {
        final List<String> values = new ArrayList<>();

        //Get the list of host names
        List<String> names = Arrays.asList(this.cfg.getString("host.header.names").split(","));

        //Build the combination list
        names.forEach(n -> {
            if (n != null && !n.trim().isEmpty()) {
                for (int p = 1; p <= 65535; p++) {
                    values.add(n + ":" + p);
                }
            }
        });

        return values;
    }

}
