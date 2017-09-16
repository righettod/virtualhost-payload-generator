package eu.righettod.vha;

import burp.IIntruderAttack;
import burp.IIntruderPayloadGenerator;
import burp.IIntruderPayloadGeneratorFactory;

import java.util.ResourceBundle;

/**
 * Handle the payload (here HTTP Request "Host" header values) generation and availability
 */
public class HostHeaderIntruderPayloadGeneratorFactory implements IIntruderPayloadGeneratorFactory {

    /**
     * Extension configuration bundle
     */
    private ResourceBundle cfg;


    /**
     * Constructor
     *
     * @param theCfg Extension configuration bundle
     */
    public HostHeaderIntruderPayloadGeneratorFactory(ResourceBundle theCfg) {
        this.cfg = theCfg;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getGeneratorName() {
        return this.cfg.getString("extension.generator.name");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIntruderPayloadGenerator createNewInstance(IIntruderAttack attack) {
        return new HostHeaderIntruderPayloadGenerator(this.cfg);
    }


}
