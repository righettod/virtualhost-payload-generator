package burp;

import eu.righettod.vha.HostHeaderIntruderPayloadGeneratorFactory;

import java.util.ResourceBundle;

/**
 * Extension loading entry point
 */
public class BurpExtender implements IBurpExtender, IExtensionStateListener {

    /**
     * {@inheritDoc}
     */
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        //Load extension configuration file from the classpath
        ResourceBundle cfg = ResourceBundle.getBundle("virtualhost-payload-generator-config");
        //Set extension properties
        callbacks.setExtensionName(cfg.getString("extension.name"));
        //Register for unload event
        callbacks.registerExtensionStateListener(this);
        //Register the extension as an Intruder payload generator
        callbacks.registerIntruderPayloadGeneratorFactory(new HostHeaderIntruderPayloadGeneratorFactory(cfg));
    }

    /**
     * {@inheritDoc}
     */
    public void extensionUnloaded() {
        //Not used for the moment...
    }
}